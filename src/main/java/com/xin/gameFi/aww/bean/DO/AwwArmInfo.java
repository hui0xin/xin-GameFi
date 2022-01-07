package com.xin.gameFi.aww.bean.DO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AwwArmInfo {
    /**
     * 主键id
     */
    private Long id;

    /**
     * NFT id (链上)
     */
    private Long armId;

    /**
     * 名称
     */
    private String name;

    /**
     * 稀有度
     */
    private Byte rarity;

    /**
     * 体力值
      */
    private Byte stamina;

    /**
     * 附加胜率
     */
    private Byte winRateBonus;

    /**
     * 图片链接
     */
    private String imageLink;

    /**
     * 已创建
     */
    private Boolean created;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     * 图片数据
     */
    private String imageData;

}