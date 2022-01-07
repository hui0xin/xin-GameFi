package com.bixin.gameFi.aww.bean.vo;

import com.bixin.gameFi.aww.bean.DO.AwwMatchRecords;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * @author zhangcheng
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
