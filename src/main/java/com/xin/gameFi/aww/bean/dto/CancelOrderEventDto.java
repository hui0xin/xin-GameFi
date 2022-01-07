package com.xin.gameFi.aww.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * create  2021/12/7
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CancelOrderEventDto {

    private long id;
    private String seller;
    private BigDecimal selling_price;
    private TokenCode pay_token_code;
    private long time;

}
