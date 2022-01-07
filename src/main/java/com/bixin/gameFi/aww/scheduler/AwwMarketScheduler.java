package com.bixin.gameFi.aww.scheduler;

import com.bixin.gameFi.aww.bean.DO.AwwArmInfo;
import com.bixin.gameFi.aww.bean.DO.AwwMarket;
import com.bixin.gameFi.aww.bean.dto.AwwChainMarketDto;
import com.bixin.gameFi.aww.bean.dto.ChainResourceDto;
import com.bixin.gameFi.aww.config.AwwConfig;
import com.bixin.gameFi.aww.service.IAwwArmInfoService;
import com.bixin.gameFi.aww.service.IAwwMarketService;
import com.bixin.gameFi.aww.service.IAwwStoreService;
import com.bixin.gameFi.common.utils.JacksonUtil;
import com.bixin.gameFi.common.utils.LocalDateTimeUtil;
import com.bixin.gameFi.core.contract.ContractBiz;
import com.bixin.gameFi.core.redis.RedisCache;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhangcheng
 * create  2021/12/9
 */
@Slf4j
@Component
public class AwwMarketScheduler {

    @Resource
    AwwConfig awwConfig;
    @Resource
    RedisCache redisCache;
    @Resource
    IAwwMarketService awwMarketService;
    @Resource
    IAwwStoreService awwStoreService;
    @Resource
    IAwwArmInfoService awwArmInfoService;
    @Resource
    ContractBiz contractService;


    private static final long PROCESSING_EXPIRE_TIME = 30 * 1000L;
    private static final long LOCK_EXPIRE_TIME = 0L;
    private static final String GET_NFT_MARKET_LOCK = "aww_nft_market_lock";


    private static final String separator = "::";
    private static final String awwSuffix = separator + "ARMMarket" + separator + "ARMSelling";


    //        @Scheduled(cron = "0/10 * * * * ?")
    @Scheduled(fixedDelay = 10000)
    public void awwNftMarketList() {
        redisCache.tryGetDistributedLock(
                GET_NFT_MARKET_LOCK,
                UUID.randomUUID().toString(),
                PROCESSING_EXPIRE_TIME,
                LOCK_EXPIRE_TIME,
                this::pullAwwNftMarketList);
    }

    private void pullAwwNftMarketList() {
        String resource = contractService.listResource(awwConfig.getCommon().getContractAddress());
        ChainResourceDto chainResourceDto = JacksonUtil.readValue(resource, new TypeReference<>() {
        });
        if (Objects.isNull(chainResourceDto) || Objects.isNull(chainResourceDto.getResult())
                || Objects.isNull(chainResourceDto.getResult().getResources())) {
            log.error("AwwMarketScheduler get chain resource is empty {}", chainResourceDto);
            return;
        }
        // TODO: 2022/1/5 debug
        log.info("AwwMarketScheduler resource info: {}", resource);
        String awwKey = awwConfig.getCommon().getContractAddress() + awwSuffix;

        List<AwwMarket> awwMarketList = new ArrayList<>();
        chainResourceDto.getResult().getResources().forEach((key, value) -> {
            try {
                if (!key.startsWith(awwKey)) {
                    return;
                }
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) value;
                if (CollectionUtils.isEmpty(map) || !map.containsKey("json")) {
                    log.error("AwwMarketScheduler resource json is empty ");
                    return;
                }
                AwwChainMarketDto awwChainMarketDto = JacksonUtil.readValue(JacksonUtil.toJson(map.get("json")), new TypeReference<>() {
                });
                if (CollectionUtils.isEmpty(awwChainMarketDto.getItems())) {
                    log.warn("AwwMarketScheduler resource items is empty ");
                    return;
                }
                awwChainMarketDto.getItems().forEach(item -> awwMarketList.add(buildAwwMarket(item)));
            } catch (Exception e) {
                log.error("AwwMarketScheduler exception {}, {}", key, value, e);
            }
        });

        updateAwwMarketInfos(awwMarketList);

    }

    private void updateAwwMarketInfos(List<AwwMarket> awwMarketList) {
        if (CollectionUtils.isEmpty(awwMarketList)) {
            log.warn("AwwMarketScheduler market infos is empty ");
            awwMarketService.deleteAll();
            return;
        }
        List<AwwMarket> oldMarkets = awwMarketService.selectAll();
        if (CollectionUtils.isEmpty(oldMarkets)) {
            insertArmInfos(awwMarketList);
            return;
        }

        Map<Long, List<AwwMarket>> newMarketMap = awwMarketList.stream().collect(Collectors.groupingBy(AwwMarket::getChainId));
        Map<Long, List<AwwMarket>> oldMarketMap = oldMarkets.stream().collect(Collectors.groupingBy(AwwMarket::getChainId));

        List<Long> delIds = new ArrayList<>();
        List<AwwMarket> updates = new ArrayList<>();
        List<AwwMarket> inserts = new ArrayList<>();

        Long currentTime = LocalDateTimeUtil.getMilliByTime(LocalDateTime.now());
        oldMarketMap.entrySet().forEach(entry -> {
            Long chainId = entry.getKey();
            List<AwwMarket> oldMarketList = entry.getValue();
            AwwMarket awwOldMarket = oldMarketList.get(0);
            List<AwwMarket> newMarketList = newMarketMap.get(chainId);
            if (CollectionUtils.isEmpty(newMarketList)) {
                delIds.add(awwOldMarket.getId());
            } else {
                AwwMarket awwNewMarket = newMarketList.get(0);
                awwNewMarket.setId(awwOldMarket.getId());
                awwNewMarket.setAwwId(awwOldMarket.getAwwId());
                awwNewMarket.setAwwName(awwOldMarket.getAwwName());
                awwNewMarket.setIcon(awwOldMarket.getIcon());
                awwNewMarket.setCreateTime(awwOldMarket.getCreateTime());
                awwNewMarket.setUpdateTime(currentTime);
                updates.add(awwNewMarket);
            }
        });
        log.info("AwwMarketScheduler update arm info,insertIds:{}, delIds:{}, updateIds:{}",
                inserts.stream().map(AwwMarket::getId).collect(Collectors.toList()),
                delIds,
                updates.stream().map(AwwMarket::getId).collect(Collectors.toList())
        );
        inserts.addAll(awwMarketList.stream()
                .filter(p -> !oldMarkets.stream().map(market -> market.getChainId()).collect(Collectors.toList()).contains(p.getChainId()))
                .collect(Collectors.toList()));

        if (!CollectionUtils.isEmpty(delIds)) {
            awwMarketService.deleteById(delIds);
        }
        if (!CollectionUtils.isEmpty(updates)) {
            updates.forEach(p -> awwMarketService.updateById(p));
        }
        if (!CollectionUtils.isEmpty(inserts)) {
            insertArmInfos(inserts);
        }
    }

    private void insertArmInfos(List<AwwMarket> awwMarketList) {
        List<Long> armIds = awwMarketList.stream().map(AwwMarket::getChainId).collect(Collectors.toList());
        List<AwwArmInfo> awwArmInfos = awwArmInfoService.selectAll(armIds);
        if (CollectionUtils.isEmpty(awwArmInfos)) {
            log.error("AwwMarketScheduler awwArmInfo is empty ");
            return;
        }
        Map<Long, List<AwwArmInfo>> armInfoMap = awwArmInfos.stream().collect(Collectors.groupingBy(AwwArmInfo::getArmId));
        awwMarketList.stream().forEach(p -> {
            List<AwwArmInfo> awwInfos = armInfoMap.get(p.getChainId());
            if (CollectionUtils.isEmpty(awwInfos)) {
                return;
            }
            AwwArmInfo awwArmInfo = awwInfos.get(0);
//            p.setId(awwArmInfo.getId());
            p.setAwwId(awwArmInfo.getId());
            p.setAwwName(awwArmInfo.getName());
            p.setIcon(awwArmInfo.getImageLink());
            awwMarketService.insert(p);
        });
    }


    private AwwMarket buildAwwMarket(AwwChainMarketDto.Item item) {
        AwwChainMarketDto.NFTVec vec = item.getNft().getVec().get(0);
        AwwChainMarketDto.TypeMeta typeMeta = vec.getType_meta();
        AwwChainMarketDto.BodyMeta bodyMeta = vec.getBody();
        Long currentTime = LocalDateTimeUtil.getMilliByTime(LocalDateTime.now());
        AwwMarket.AwwMarketBuilder marketBuilder = AwwMarket.builder()
                .chainId(item.getId())
//                .id()
//                .awwId()
//                .awwName()
//                .icon()
                .owner(item.getSeller())
                .address(awwConfig.getCommon().getContractAddress())
                .rarity(typeMeta.getRarity())
                .sellPrice(item.getSelling_price())
                .stamina(typeMeta.getStamina())
                .usedStamina(bodyMeta.getUsed_stamina())
                .winRateBonus(typeMeta.getWin_rate_bonus())
                .createTime(currentTime)
                .updateTime(currentTime);
        return marketBuilder.build();
    }

}
