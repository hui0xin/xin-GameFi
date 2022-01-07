package com.xin.gameFi.core.handler;

import com.xin.gameFi.common.code.GameErrCode;
import com.xin.gameFi.common.exception.BizException;
import com.xin.gameFi.common.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * create          2021-08-12 11:54 上午
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 请求数据存在问题
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler({MissingServletRequestParameterException.class, HttpMessageNotReadableException.class})
    @ResponseBody
    public R handlerMessageNotReadable(HttpServletRequest request, Exception ex) {
        log.error("BadRequestException [path={}][code={}][msg={}]",
                request.getRequestURI(),
                GameErrCode.DATA_VALIDATION_FAILURE.getCode(),
                GameErrCode.DATA_VALIDATION_FAILURE.getMessage(),
                ex);
        Map<String, Object> result = new TreeMap<>();
        result.put("error", ex.getMessage());
        return R.failure(GameErrCode.DATA_VALIDATION_FAILURE, result);
    }

    /**
     * 数据校验失败
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseBody
    public R handlerMethodArgumentNotValid(HttpServletRequest request, HttpServletResponse response,
                                           Exception ex) {
        log.error("MethodArgumentNotValidException [path={}][code={}][msg={}]",
                request.getRequestURI(),
                GameErrCode.DATA_BIND_VALIDATION_FAILURE.getCode(),
                GameErrCode.DATA_BIND_VALIDATION_FAILURE.getMessage(),
                ex);
        if (ex instanceof BindException) {
            BindException bindException = (BindException) ex;
            BindingResult bindingResult = bindException.getBindingResult();
            Map<String, String> result = new TreeMap<>();
            List<FieldError> list = bindingResult.getFieldErrors();
            for (FieldError error : list) {
                result.put(error.getField(), error.getDefaultMessage());
            }
            return R.failure(GameErrCode.DATA_BIND_VALIDATION_FAILURE, result);
        }
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException notValidException = (MethodArgumentNotValidException) ex;
            BindingResult bindingResult = notValidException.getBindingResult();
            Map<String, String> result = new TreeMap<>();
            List<FieldError> list = bindingResult.getFieldErrors();
            for (FieldError error : list) {
                result.put(error.getField(), error.getDefaultMessage());
            }
            return R.failure(GameErrCode.DATA_BIND_VALIDATION_FAILURE, result);
        }
        return R.failure(GameErrCode.DATA_BIND_VALIDATION_FAILURE);
    }

    /**
     * 处理业务异常，返回业务异常的错误信息
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public R handleBusinessException(HttpServletRequest request, HttpServletResponse response,
                                     BizException ex) {
        log.error("BizException [path={}][code={}][msg={}]",
                request.getRequestURI(),
                ex.getCode(),
                ex.getMessage(),
                ex);
        return R.failure(ex.getCode(), ex.getMessage());
    }

    /**
     * 处理其他未知异常 针对未知异常，由于业务代码不打印日志，在这里打印详细错误日志
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public R handleException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        log.error("Exception [path={}]", request.getRequestURI(), ex);
        return R.failure(GameErrCode.SYSTEM_ERROR);
    }


}