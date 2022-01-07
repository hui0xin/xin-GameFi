package com.bixin.gameFi.common.exception;

import com.bixin.gameFi.common.code.ErrCode;
import lombok.Getter;

/**
 * @Description: 业务异常类
 * @author: 系统
 */
@Getter
public class GameException extends BizException {

    private int code;

    private String message;

    public GameException() {
        super();
    }

    public GameException(final ErrCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public GameException(final ErrCode errorCode, final Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
}
