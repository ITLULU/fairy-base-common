package com.fairy.base.common.exception;

import com.fairy.base.common.enums.ResultEnums;

/**
 * @author huanglulu
 * @version 1.0
 * @date 2022/4/29 22:40
 */
public class GateWayException extends RuntimeException {

    private String msg;
    private String code;

    public GateWayException(ResultEnums enums) {
        this.msg = enums.getResultMsg();
        this.code = enums.getResultCode();
    }

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
}
