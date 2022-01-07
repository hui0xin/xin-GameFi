package com.bixin.gameFi.aww.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author zhangcheng
 * create   2021/12/6
 */
@Getter
@AllArgsConstructor
public enum AwwEventType {

    PLACE_ORDER_EVENT("ARMPlaceOrderEvent"),
    TAKE_ORDER_EVENT("ARMTakeOrderEvent"),
    CANCEL_ORDER_EVENT("ARMCancelOrderEvent"),

    ;

    private String desc;

    public static AwwEventType of(String desc) {
        Optional<AwwEventType> typeOptional = Arrays.stream(AwwEventType.values()).filter(p -> desc.equalsIgnoreCase(p.getDesc())).findFirst();
        return typeOptional.isPresent() ? typeOptional.get() : null;

    }

}
