package com.xin.gameFi.aww.controller;

import com.xin.gameFi.aww.bean.DO.AwwArmInfo;
import com.xin.gameFi.aww.bean.DO.AwwMarket;
import com.xin.gameFi.aww.bean.DO.AwwMatchRecords;
import com.xin.gameFi.aww.bean.vo.AwwMatchRecordsVO;
import com.xin.gameFi.aww.common.contants.PathConstant;
import com.xin.gameFi.aww.service.IAwwArmInfoService;
import com.xin.gameFi.common.response.P;
import com.xin.gameFi.aww.service.IAwwMarketService;
import com.xin.gameFi.aww.service.IAwwStoreService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * create  2021/12/9
 */
@RestController
@RequestMapping(PathConstant.AWW_REQUEST_PATH_PREFIX + "/store")
public class AwwStoreController {

    @Resource
    IAwwStoreService awwStoreService;
    @Resource
    IAwwMarketService awwMarketService;
    @Resource
    IAwwArmInfoService awwArmInfoService;

    @GetMapping("/getSelling")
    public P getSelling(@RequestParam(value = "address", defaultValue = "") String address,
                        @RequestParam(value = "pageSize", defaultValue = "20") long pageSize,
                        @RequestParam(value = "pageNum", defaultValue = "1") long pageNum) {

        if (pageNum <= 0 || pageSize <= 0 || StringUtils.isEmpty(address)) {
            return P.failed("parameter is invalid");
        }
        List<AwwMarket> awwMarkets = awwMarketService.selectByPages(true,
                address,
                null,
                null,
                null,
                pageSize + 1,
                pageNum,
                0);
        if (CollectionUtils.isEmpty(awwMarkets)) {
            return P.success(null, false);
        }

        boolean hasNext = false;
        if (awwMarkets.size() > pageSize) {
            awwMarkets = awwMarkets.subList(0, awwMarkets.size() - 1);
            hasNext = true;
        }

        return P.success(awwMarkets, hasNext);
    }

    @GetMapping("/getSellRecords")
    public P getSellRecords(@RequestParam(value = "address", defaultValue = "") String address,
                            @RequestParam(value = "pageSize", defaultValue = "20") long pageSize,
                            @RequestParam(value = "pageNum", defaultValue = "1") long pageNum) {

        if (pageNum <= 0 || pageSize <= 0 || StringUtils.isEmpty(address)) {
            return P.failed("parameter is invalid");
        }
        List<AwwMatchRecords> matchRecords = awwStoreService.selectByPages(true, address, "", pageSize + 1, pageNum, 0);
        if (CollectionUtils.isEmpty(matchRecords)) {
            return P.success(null, false);
        }

        boolean hasNext = false;
        if (matchRecords.size() > pageSize) {
            matchRecords = matchRecords.subList(0, matchRecords.size() - 1);
            hasNext = true;
        }

        List<Long> ids = matchRecords.stream().map(AwwMatchRecords::getAwwId).collect(Collectors.toList());
        List<AwwArmInfo> awwArmInfos = awwArmInfoService.selectAllByIds(ids);
        Map<Long, List<AwwArmInfo>> armInfoMap = awwArmInfos.stream()
                .collect(Collectors.groupingBy(AwwArmInfo::getId));

        List<AwwMatchRecordsVO> list = new ArrayList<>();
        matchRecords.forEach(p -> {
            List<AwwArmInfo> armInfos = armInfoMap.get(p.getAwwId());
            if (CollectionUtils.isEmpty(armInfos)) {
                return;
            }
            AwwArmInfo armInfo = armInfos.get(0);
            AwwMatchRecordsVO recordsVO = AwwMatchRecordsVO.builder()
                    .chainId(armInfo.getArmId())
                    .rarity(Integer.valueOf(armInfo.getRarity()))
                    .stamina(Integer.valueOf(armInfo.getStamina()))
                    .build();

            BeanUtils.copyProperties(p, recordsVO);
            list.add(recordsVO);
        });


        return P.success(list, hasNext);
    }


}
