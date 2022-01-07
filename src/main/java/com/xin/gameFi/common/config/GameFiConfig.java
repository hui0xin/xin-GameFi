package com.xin.gameFi.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * create   2021/12/6
 */
@Data
@Component
@ConfigurationProperties(prefix = "gamefi.common")
public class GameFiConfig {

    private String url;
    private int chainId;

}
