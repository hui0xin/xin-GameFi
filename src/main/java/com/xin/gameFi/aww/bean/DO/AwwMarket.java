package com.xin.gameFi.aww.bean.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AwwMarket {
    private Long id;

    private Long chainId;

    private Long awwId;

    private String awwName;

    private String owner;

    private String address;

    private Integer rarity;

    private BigDecimal sellPrice;

    private Integer stamina;

    private Integer usedStamina;

    private Integer winRateBonus;

    private String icon;

    private Long createTime;

    private Long updateTime;


}