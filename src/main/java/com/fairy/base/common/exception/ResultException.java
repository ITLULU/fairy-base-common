package com.fairy.base.common.exception;

import com.fairy.base.common.enums.ResultEnums;

/**
 * 自定义异常
 *
 * @author hll
 * @version 1.0
 * @date 2022/3/17 15:09
 */
public class ResultException extends RuntimeException {

    private String msg;
    private String code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static ResultException create(Throwable e) {
        ResultException exception = new ResultException();
        exception.setCode(ResultEnums.FAILED.getResultCode());
        exception.setMsg(e.getMessage());
        return exception;
    }

    public static ResultException create(ResultEnums enums) {
        ResultException exception = new ResultException();
        exception.setMsg(enums.getResultMsg());
        exception.setCode(enums.getResultCode());
        return exception;
    }

    public static ResultException create(String code, String msg) {
        ResultException exception = new ResultException();
        exception.setMsg(msg);
        exception.setCode(code);
        return exception;
    }

    public static ResultException create(String msg) {
        ResultException exception = new ResultException();
        exception.setMsg(msg);
        exception.setCode(ResultEnums.FAILED.getResultCode());
        return exception;
    }

}


