package com.xin.gameFi.aww.bean.vo;

import com.xin.gameFi.aww.bean.DO.AwwMatchRecords;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * create  2022/1/6
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AwwMatchRecordsVO extends AwwMatchRecords {

    private Long chainId;

    private Integer rarity;

    private Integer stamina;

}
