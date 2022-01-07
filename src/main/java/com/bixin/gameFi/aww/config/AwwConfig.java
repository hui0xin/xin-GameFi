package com.bixin.gameFi.aww.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhangcheng
 * create   2021/12/6
 */
@Data
@Component
@ConfigurationProperties(prefix = "gamefi.aww")
public class AwwConfig {

    private Common common = new Common();
    private Websocket websocket = new Websocket();
    private Content content = new Content();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Common {
        private String contractAddress;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Websocket {
        private String websocketHost;
        private String websocketPort;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Content {
        private String marketModule;
        private String armModule;
        private String awwModule;
        private String awwGameModule;
        private String scriptsModule;

        private String nftName;
        private String titlePageImage;
        private String description;

        private Long imageId;
        private String imageData;
        private String nftMeta;
        private String nftBody;
        private String scripts;
        //        image-info-api: "https://test.kikoswap.com/v1/nft/image/group/1"
        private String imageInfoApi;
    }

}
