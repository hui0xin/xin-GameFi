package com.xin.gameFi.common.exception;

import lombok.Getter;

/**
 * create          2021-08-12 11:54 上午
 */
@Getter
public class BizException extends RuntimeException {

    private int code;

    /**
     * 构造函数
     */
    public BizException() {
        super();
    }

    /**
     * 信息
     *
     * @param msg
     */
    public BizException(final String msg) {
        super(msg);
    }

    /**
     * @param msg   信息
     * @param cause 原因
     */
    public BizException(final String msg, final Throwable cause) {
        super(msg, cause);
    }

}
