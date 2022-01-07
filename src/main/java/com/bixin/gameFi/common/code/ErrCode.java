package com.bixin.gameFi.common.code;

/**
 * 错误code
 * @author: xin
 **/
public interface ErrCode {
    /**
     * 错误代号
     */
    int getCode();

    /**
     * 具体信息
     */
    String getMessage();
}
