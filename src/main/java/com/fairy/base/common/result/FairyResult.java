package com.fairy.base.common.result;

import com.fairy.base.common.enums.ResultEnums;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用结果返回
 *
 * @author hll
 * @version 1.0
 * @date 2022/1/25 13:39
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FairyResult<T> {
    private boolean success;
    private String code;
    private String msg;
    private T data;

    public static <T> FairyResult<T> success() {
        FairyResult result = FairyResult.builder()
                .code(ResultEnums.SUCCESS.getResultCode())
                .success(true)
                .msg(ResultEnums.SUCCESS.getResultMsg())
                .build();
        return result;
    }

    public static <T> FairyResult<T> fail() {
        FairyResult result = FairyResult.builder()
                .code(ResultEnums.SYSTEM_ERROR.getResultCode())
                .success(false)
                .msg(ResultEnums.SYSTEM_ERROR.getResultMsg())
                .build();
        return result;
    }

    public static <T> FairyResult<T> fail(ResultEnums enums, T data) {
        FairyResult result = FairyResult.builder()
                .code(enums.getResultCode())
                .success(false)
                .msg(enums.getResultMsg())
                .data(data)
                .build();
        return result;
    }

    public static <T> FairyResult<T> fail(String code, String msg, T data) {
        FairyResult result = FairyResult.builder()
                .code(code)
                .success(false)
                .msg(msg)
                .data(data)
                .build();
        return result;
    }

    public static <T> FairyResult<T> success(T data, String message) {
        FairyResult result = FairyResult.builder()
                .code(ResultEnums.SUCCESS.getResultCode())
                .success(true)
                .msg(message)
                .data(data)
                .build();
        return result;
    }

}
