package com.bixin.gameFi.common.response;

import com.bixin.gameFi.common.code.ErrCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhangcheng
 * create          2021-08-12 11:54 上午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class R<T> implements Serializable {

    //返回code ，0表示成功，>0表示失败,<0系统保留"
    private int code = 0;

    //提示信息
    private String msg = "";

    //详细提示信息
    private String detailMsg = "";

   //返回结果数据
    private T data;

    /** 成功 */
    public static R success() {
        return success(null);
    }

    /** 成功 返回数据 */
    public static <T> R success(final T data) {
        return build(0, "", "", data);
    }

    /** 失败 返回code和msg */
    public static R failure(final int code, final String msg) {
        return failure(code, msg, "");
    }

    /** 失败 返回 ErrCode */
    public static R failure(final ErrCode ErrCode) {
        return failure(ErrCode, "");
    }

    /**
     * 失败 返回 ErrCode 和 错误详情
     */
    public static R failure(final ErrCode ErrCode, final String detailMsg) {
        return failure(ErrCode.getCode(), ErrCode.getMessage(), detailMsg);
    }

    /**
     * 失败 返回 ErrCode 和 具体数据
     */
    public static <T> R failure(final ErrCode ErrCode, final T data) {
        return build(ErrCode.getCode(), ErrCode.getMessage(), "", data);
    }

    /**
     * 失败 返回 msg
     */
    public static R failure(final String msg) {
        return failure(-1, msg, "");
    }

    /**
     * 失败 返回 msg 和 错误详情
     */
    public static R failure(final String msg, final String detailMsg) {
        return failure(-1, msg, detailMsg);
    }

    /**
     * 失败 返回 code，msg 和 detailMsg
     */
    public static R failure(final int code, final String msg, final String detailMsg) {
        return build(code, msg, detailMsg, null);
    }

    /**
     * 生成返回结果
     */
    public static <T> R build(final int code, final String msg, final String detailMsg, final T data) {
        return new R<>(code, msg, detailMsg, data);
    }

}