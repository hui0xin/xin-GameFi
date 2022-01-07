package com.bixin.gameFi.aww.biz;

import com.bixin.gameFi.aww.bean.DO.AwwArmInfo;
import com.bixin.gameFi.aww.config.AwwConfig;
import com.bixin.gameFi.aww.core.mapper.AwwArmInfoMapper;
import com.bixin.gameFi.aww.core.wrapDDL.AwwArmInfoDDL;
import com.bixin.gameFi.common.code.GameErrCode;
import com.bixin.gameFi.common.exception.GameException;
import com.bixin.gameFi.common.utils.TypeArgsUtil;
import com.bixin.gameFi.core.contract.ContractBiz;
import com.google.common.collect.Lists;
import com.novi.serde.Bytes;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.mutable.MutableInt;
import org.springframework.stereotype.Component;
import org.starcoin.bean.ScriptFunctionObj;
import org.starcoin.utils.AccountAddressUtils;
import org.starcoin.utils.BcsSerializeHelper;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AWWContractBiz {

    @Resource
    private AwwConfig awwConfig;
    @Resource
    private AwwArmInfoMapper awwArmInfoMapper;
    @Resource
    private ContractBiz contractService;

    /**
     * 1.部署NFT Market
     * 2.部署NFT Scripts
     * 3.初始化config
     *
     * @return
     */
    public void initNFTMarket(BigInteger creatorFee, BigInteger platformFee) {
        if (!contractService.deployContract(awwConfig.getCommon().getContractAddress(), "contract/aww/" + awwConfig.getContent().getAwwModule() + ".mv", null)) {
            log.error("AWW 部署失败");
            throw new GameException(GameErrCode.CONTRACT_DEPLOY_FAILURE);
        }
        // 部署nft合约
        if (!deployNFTContractWithImage()) {
            log.error("ARM合约 部署失败");
            throw new GameException(GameErrCode.CONTRACT_DEPLOY_FAILURE);
        }
        if (!contractService.deployContract(awwConfig.getCommon().getContractAddress(), "contract/aww/" + awwConfig.getContent().getMarketModule() + ".mv", null)) {
            log.error("AWW Market部署失败");
            throw new GameException(GameErrCode.CONTRACT_DEPLOY_FAILURE);
        }
        if (!deployAWWGameContract()) {
            log.error("AWW Game部署失败");
            throw new GameException(GameErrCode.CONTRACT_DEPLOY_FAILURE);
        }
        if (!contractService.deployContract(awwConfig.getCommon().getContractAddress(), "contract/aww/" + awwConfig.getContent().getScriptsModule() + ".mv", null)) {
            log.error("AWW Scripts部署失败");
            throw new GameException(GameErrCode.CONTRACT_DEPLOY_FAILURE);
        }
        ScriptFunctionObj scriptFunctionObj = ScriptFunctionObj
                .builder()
                .moduleAddress(awwConfig.getCommon().getContractAddress())
                .moduleName(awwConfig.getContent().getScriptsModule())
                .functionName("init_config")
                .args(Lists.newArrayList(
                        BcsSerializeHelper.serializeU128ToBytes(creatorFee),
                        BcsSerializeHelper.serializeU128ToBytes(platformFee)
                ))
                .build();
        if (!contractService.callFunction(awwConfig.getCommon().getContractAddress(), scriptFunctionObj)) {
            log.error("ARM Config初始化失败");
            throw new GameException(GameErrCode.CONTRACT_CALL_FAILURE);
        }
    }

    public void tempCreateNFT() {
//        initGame();
        initMarket();
        for (long i = 1; i <= 10; i++) {
            AwwArmInfo armInfoDo = new AwwArmInfo();
            armInfoDo.setId(i);
            armInfoDo.setName("ARM#" + i);
            armInfoDo.setRarity((byte) 1);
            armInfoDo.setStamina((byte) 11);
            armInfoDo.setWinRateBonus((byte) 30);
            if (!mintKikoCatNFTWithImage(armInfoDo)) {
                log.error("ARM {} mint失败", armInfoDo.getName());
                throw new GameException(GameErrCode.CONTRACT_CALL_FAILURE);
            }
        }
    }

    /**
     * 1.部署NFT合约
     * 2.mint所有NFT
     * 3.盲盒首发
     *
     * @return
     */
    public void createNFT() {
        // 部署nft合约
        if (!deployNFTContractWithImage()) {
            log.error("ARM合约 部署失败");
            throw new GameException(GameErrCode.CONTRACT_DEPLOY_FAILURE);
        }

        // mint nft + 盲盒
        AwwArmInfoDDL selectAwwArmInfo = new AwwArmInfoDDL();
        selectAwwArmInfo.createCriteria().andCreatedEqualTo(Boolean.FALSE);
        // 取出该组下所有待铸造NFT
        List<AwwArmInfo> awwArmInfoDos = awwArmInfoMapper.selectByDDL(selectAwwArmInfo);
        if (CollectionUtils.isEmpty(awwArmInfoDos)) {
            return;
        }
        MutableInt armId = new MutableInt(1);
        // 获取该组最后一个id
        selectAwwArmInfo = new AwwArmInfoDDL();
        selectAwwArmInfo.createCriteria().andCreatedEqualTo(Boolean.TRUE);
        ;
        List<AwwArmInfo> createdAwwArmInfos = awwArmInfoMapper.selectByDDL(selectAwwArmInfo);
        if (!CollectionUtils.isEmpty(createdAwwArmInfos)) {
            createdAwwArmInfos.sort(Comparator.comparingLong(AwwArmInfo::getArmId).reversed());
            armId.setValue(createdAwwArmInfos.get(0).getArmId() + 1);
        }
        awwArmInfoDos.stream().sorted(Comparator.comparingLong(AwwArmInfo::getId)).forEach(awwArmInfoDo -> {
            awwArmInfoDo.setArmId(armId.longValue());
            awwArmInfoMapper.updateByPrimaryKeySelective(awwArmInfoDo);
            // 铸造NFT，存放图片url
            if (!mintKikoCatNFTWithImage(awwArmInfoDo)) {
                log.error("ARM {} mint失败", awwArmInfoDo.getName());
                throw new GameException(GameErrCode.CONTRACT_CALL_FAILURE);
            }
            log.info("ARM {} mint成功", awwArmInfoDo.getName());
            awwArmInfoDo.setCreated(true);
            awwArmInfoDo.setUpdateTime(System.currentTimeMillis());
            awwArmInfoMapper.updateByPrimaryKeySelective(awwArmInfoDo);
            armId.add(1);
        });


        // 市场创建resource
        if (!initMarket()) {
            log.error("ARM 市场初始化失败, 设置币种");
            throw new GameException(GameErrCode.CONTRACT_CALL_FAILURE);
        }
    }

    public void transferNFT(long startNftId, long endNftId, String toAddress) {
        AwwArmInfoDDL selectAwwArmInfo = new AwwArmInfoDDL();
        selectAwwArmInfo.createCriteria().andCreatedEqualTo(Boolean.TRUE);
        // 取出该组下所有待铸造NFT
        List<AwwArmInfo> awwArmInfoDos = awwArmInfoMapper.selectByDDL(selectAwwArmInfo);
        if (CollectionUtils.isEmpty(awwArmInfoDos)) {
            return;
        }
        awwArmInfoDos = awwArmInfoDos.stream().sorted(Comparator.comparingLong(AwwArmInfo::getArmId)).collect(Collectors.toList());
        for (AwwArmInfo awwArmInfoDo : awwArmInfoDos) {
            if (awwArmInfoDo.getArmId() < startNftId || awwArmInfoDo.getArmId() > endNftId) {
                continue;
            }
            try {
                if (!transferNFT(awwArmInfoDo.getArmId(), toAddress)) {
                    log.info("NFT转移失败，nftId:{}", awwArmInfoDo.getArmId());
                    return;
                }
            } catch (Exception e) {
                log.error("NFT转移失败，nftId:{}", awwArmInfoDo.getArmId(), e);
                return;
            }
        }
    }

    /**
     * 部署NFT Token，封面为图片url
     *
     * @return
     */
    private boolean deployNFTContractWithImage() {
        String moduleName = awwConfig.getContent().getArmModule();
        String address = awwConfig.getCommon().getContractAddress();
        String path = "contract/aww/" + moduleName + ".mv";
        ScriptFunctionObj scriptFunctionObj = ScriptFunctionObj
                .builder()
                .moduleAddress(address)
                .moduleName(moduleName)
                .functionName("init_with_image")
                .tyArgs(Lists.newArrayList())
                .args(Lists.newArrayList(
                        Bytes.valueOf(BcsSerializeHelper.serializeString(awwConfig.getContent().getNftName())),
                        Bytes.valueOf(BcsSerializeHelper.serializeString(awwConfig.getContent().getTitlePageImage())),
                        Bytes.valueOf(BcsSerializeHelper.serializeString(awwConfig.getContent().getDescription()))
                ))
                .build();
        return contractService.deployContract(address, path, scriptFunctionObj);
    }


    /**
     * 部署AWW GAME
     *
     * @return
     */
    private boolean deployAWWGameContract() {
        String moduleName = awwConfig.getContent().getAwwGameModule();
        String address = awwConfig.getCommon().getContractAddress();
        String path = "contract/aww/" + moduleName + ".mv";
        ScriptFunctionObj scriptFunctionObj = ScriptFunctionObj
                .builder()
                .moduleAddress(address)
                .moduleName(moduleName)
                .functionName("init_game")
                .tyArgs(Lists.newArrayList())
                .args(Lists.newArrayList())
                .build();
        return contractService.deployContract(address, path, scriptFunctionObj);
    }

    /**
     * mint NFT，存放图片url
     */
    private boolean mintKikoCatNFTWithImage(AwwArmInfo awwArmInfoDo) {
        String address = awwConfig.getCommon().getContractAddress();

        ScriptFunctionObj scriptFunctionObj = ScriptFunctionObj
                .builder()
                .moduleAddress(address)
                .moduleName(awwConfig.getContent().getArmModule())
                .functionName("mint_with_image")
                .args(Lists.newArrayList(
                        Bytes.valueOf(BcsSerializeHelper.serializeString(awwArmInfoDo.getName())),
                        Bytes.valueOf(BcsSerializeHelper.serializeString(awwConfig.getContent().getImageInfoApi() + awwArmInfoDo.getId())),
                        Bytes.valueOf(BcsSerializeHelper.serializeString(awwConfig.getContent().getDescription())),
                        Bytes.valueOf(BcsSerializeHelper.serializeU8(awwArmInfoDo.getRarity())),
                        Bytes.valueOf(BcsSerializeHelper.serializeU8(awwArmInfoDo.getStamina())),
                        Bytes.valueOf(BcsSerializeHelper.serializeU8(awwArmInfoDo.getWinRateBonus()))
                ))
                .build();
        return contractService.callFunction(address, scriptFunctionObj);
    }

    /**
     * 部署NFT Token，封面为图片元数据
     *
     * @return
     */
    private boolean deployNFTContractWithImageData() {
        String moduleName = awwConfig.getContent().getArmModule();
        String address = awwConfig.getCommon().getContractAddress();
        String path = "contract/aww/" + moduleName + ".mv";
        ScriptFunctionObj scriptFunctionObj = ScriptFunctionObj
                .builder()
                .moduleAddress(address)
                .moduleName(moduleName)
                .functionName("init_with_image_data")
                .tyArgs(Lists.newArrayList())
                .args(Lists.newArrayList(
                        Bytes.valueOf(BcsSerializeHelper.serializeString(awwConfig.getContent().getNftName())),
                        Bytes.valueOf(BcsSerializeHelper.serializeString(awwConfig.getContent().getImageData())),
                        Bytes.valueOf(BcsSerializeHelper.serializeString(awwConfig.getContent().getDescription()))
                ))
                .build();
        return contractService.deployContract(address, path, scriptFunctionObj);
    }

    /**
     * mint NFT，存放图片元数据
     */
    private boolean mintKikoCatNFTWithImageData(AwwArmInfo awwArmInfoDo) {
        String address = awwConfig.getCommon().getContractAddress();

        ScriptFunctionObj scriptFunctionObj = ScriptFunctionObj
                .builder()
                .moduleAddress(address)
                .moduleName(awwConfig.getContent().getArmModule())
                .functionName("mint_with_image_data")
                .args(Lists.newArrayList(
                        Bytes.valueOf(BcsSerializeHelper.serializeString(awwArmInfoDo.getName())),
                        Bytes.valueOf(BcsSerializeHelper.serializeString(awwArmInfoDo.getImageData())),
                        Bytes.valueOf(BcsSerializeHelper.serializeString(awwConfig.getContent().getDescription())),
                        Bytes.valueOf(BcsSerializeHelper.serializeU8(awwArmInfoDo.getRarity())),
                        Bytes.valueOf(BcsSerializeHelper.serializeU8(awwArmInfoDo.getStamina())),
                        Bytes.valueOf(BcsSerializeHelper.serializeU8(awwArmInfoDo.getWinRateBonus()))
                ))
                .build();
        return contractService.callFunction(address, scriptFunctionObj);
    }

    /**
     * 将NFT转移至address
     *
     * @param nftId
     * @param toAddress
     * @return
     */
    private boolean transferNFT(long nftId, String toAddress) {
        String address = awwConfig.getCommon().getContractAddress();
        ScriptFunctionObj scriptFunctionObj = ScriptFunctionObj
                .builder()
                .moduleAddress("0x00000000000000000000000000000001")
                .moduleName("NFTGalleryScripts")
                .functionName("transfer")
                .args(Lists.newArrayList(
                        BcsSerializeHelper.serializeU64ToBytes(nftId),
                        BcsSerializeHelper.serializeAddressToBytes(AccountAddressUtils.create(toAddress))
                ))
                .tyArgs(Lists.newArrayList(
                        TypeArgsUtil.parseTypeObj(awwConfig.getContent().getNftMeta()),
                        TypeArgsUtil.parseTypeObj(awwConfig.getContent().getNftBody())
                ))
                .build();
        return contractService.callFunction(address, scriptFunctionObj);
    }

    /**
     * 初始化市场
     */
    private boolean initMarket() {
        String moduleName = awwConfig.getContent().getScriptsModule();
        String address = awwConfig.getCommon().getContractAddress();
        ScriptFunctionObj scriptFunctionObj = ScriptFunctionObj
                .builder()
                .moduleAddress(address)
                .moduleName(moduleName)
                .functionName("init_market")
                .args(Lists.newArrayList())
                .tyArgs(Lists.newArrayList())
                .build();
        return contractService.callFunction(address, scriptFunctionObj);
    }

    public void playerMintArm() {
        String moduleName = awwConfig.getContent().getScriptsModule();
        String address = awwConfig.getCommon().getContractAddress();
        ScriptFunctionObj scriptFunctionObj = ScriptFunctionObj
                .builder()
                .moduleAddress(address)
                .moduleName(moduleName)
                .functionName("arm_mint")
                .tyArgs(Lists.newArrayList())
                .args(Lists.newArrayList())
                .build();
        if (!contractService.callFunction("0xC445808B4AecA9a5779908386a8673de", scriptFunctionObj)) {
            log.error("buy arm 失败");
            throw new GameException(GameErrCode.CONTRACT_CALL_FAILURE);
        }
    }

    public void fight() {
        String moduleName = awwConfig.getContent().getScriptsModule();
        String address = awwConfig.getCommon().getContractAddress();
        ScriptFunctionObj scriptFunctionObj = ScriptFunctionObj
                .builder()
                .moduleAddress(address)
                .moduleName(moduleName)
                .functionName("fight")
                .tyArgs(Lists.newArrayList())
                .args(Lists.newArrayList(
                        BcsSerializeHelper.serializeU64ToBytes(5L),
                        Bytes.valueOf(BcsSerializeHelper.serializeU8((byte) 0)))
                )
                .build();
        if (!contractService.callFunction("0xC445808B4AecA9a5779908386a8673de", scriptFunctionObj)) {
            log.error("战斗 失败");
            throw new GameException(GameErrCode.CONTRACT_CALL_FAILURE);
        }
    }

    public boolean sellNFT() {
        String moduleName = awwConfig.getContent().getScriptsModule();
        String address = awwConfig.getCommon().getContractAddress();
        ScriptFunctionObj scriptFunctionObj = ScriptFunctionObj
                .builder()
                .moduleAddress(address)
                .moduleName(moduleName)
                .functionName("arm_place_order")
                .args(Lists.newArrayList(
                        BcsSerializeHelper.serializeU64ToBytes(5L),
                        BcsSerializeHelper.serializeU128ToBytes(BigInteger.valueOf(200000000000L))
                ))
                .tyArgs(Lists.newArrayList())
                .build();
        return contractService.callFunction("0xC445808B4AecA9a5779908386a8673de", scriptFunctionObj);
    }

}
