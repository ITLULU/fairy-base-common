package com.fairy.base.common.exception;

import com.fairy.base.common.enums.ResultEnums;
import lombok.Data;

/**
 * @author huanglulu
 * @version 1.0
 * @date 2022/4/29 22:40
 */
@Data
public class GateWayException extends RuntimeException {

    private String msg;
    private String code;

    public GateWayException(ResultEnums enums) {
        this.msg = enums.getResultMsg();
        this.code = enums.getResultCode();
    }


}
