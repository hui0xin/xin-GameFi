package com.xin.gameFi.test.aww;

import com.xin.gameFi.GameFiApplication;
import com.xin.gameFi.aww.biz.AWWContractBiz;
import com.xin.gameFi.core.contract.ContractBiz;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;
import java.math.BigInteger;

@ActiveProfiles("local")
@SpringBootTest(classes = GameFiApplication.class)
class NftInitServiceTest {
    @Resource
    private AWWContractBiz awwContractService;
    @Resource
    private ContractBiz contractService;

    @Test
    void initNFTMarket() {
        awwContractService.initNFTMarket(new BigInteger("0"), new BigInteger("50"));
    }

    @Test
    @SneakyThrows
    void createNFT() {
//        awwContractService.createNFT();
    }

    @Test
    @SneakyThrows
    void tempCreateNFT() {
        awwContractService.tempCreateNFT();
    }

    @Test
    void sellNFT() {
        assert awwContractService.sellNFT();
    }

    @Test
    void mintArm() {
        awwContractService.playerMintArm();
    }

}