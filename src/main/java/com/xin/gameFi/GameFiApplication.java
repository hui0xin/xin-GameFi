package com.xin.gameFi;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * ido服务端
 */
@Slf4j
@EnableScheduling
@EnableConfigurationProperties
@SpringBootApplication
@MapperScan({"com.xin.gameFi.aww.core.mapper"})
public class GameFiApplication {

    public static void main(String[] args) {

        SpringApplication.run(GameFiApplication.class, args);

        log.info("GameFiApplication running ...");

    }

}
