package com.bixin.gameFi.aww.bean.dto;

import com.bixin.gameFi.aww.bean.DO.AwwMatchRecords;
import com.bixin.gameFi.common.utils.LocalDateTimeUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zhangcheng
 * create  2021/12/7
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TakeOrderEventDto {

    private long id;
    private String seller;
    private String buyer;
    private BigDecimal selling_price;
    private BigDecimal platform_fee;
    private TokenCode pay_token_code;
    private long time;

    public static AwwMatchRecords of(TakeOrderEventDto dto) {
        Long currentTime = LocalDateTimeUtil.getMilliByTime(LocalDateTime.now());
        AwwMatchRecords.AwwMatchRecordsBuilder builder = AwwMatchRecords.builder()
                .sellAddress(dto.getSeller())
                .buyAddress(dto.getBuyer())
                .awwId(dto.getId())
//                .groupId()
//                .icon()  
//                .groupName()
//                .awwName()
                .price(dto.getSelling_price())
                .fee(dto.getPlatform_fee())
                .matchTime(dto.getTime())
                .createTime(currentTime)
                .updateTime(currentTime);

        return builder.build();
    }

}

