package com.fairy.base.common.result;

import com.fairy.base.common.enums.ResultEnums;

/**
 * 通用结果返回
 *
 * @author hll
 * @version 1.0
 * @date 2022/1/25 13:39
 */
public class FairyResult<T> {
    private boolean success;
    private String code;
    private String msg;
    private T data;

    public FairyResult(boolean success, String code, String msg, T data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static class FairyResultBuilder<T> {
        private boolean success;
        private String code;
        private String msg;
        private T data;

        public FairyResult.FairyResultBuilder success(boolean success) {
            this.success = success;
            return this;
        }

        public FairyResult.FairyResultBuilder code(String code) {
            this.code = code;
            return this;
        }

        public FairyResult.FairyResultBuilder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public FairyResult.FairyResultBuilder data(T data) {
            this.data = data;
            return this;
        }

        public FairyResult build() {
            return new FairyResult(this.success, this.code, this.msg, this.data);
        }

        @Override
        public String toString() {
            return "FairyResultBuilder{" +
                    "success=" + success +
                    ", code='" + code + '\'' +
                    ", msg='" + msg + '\'' +
                    ", data=" + data +
                    '}';
        }
    }

    public static <T> FairyResult<T> success() {
        FairyResult result = new FairyResult.FairyResultBuilder()
                .code(ResultEnums.SUCCESS.getResultCode())
                .success(true)
                .msg(ResultEnums.SUCCESS.getResultMsg())
                .build();
        return result;
    }

    public static <T> FairyResult<T> fail() {
        FairyResult result = new FairyResult.FairyResultBuilder()
                .code(ResultEnums.SYSTEM_ERROR.getResultCode())
                .success(false)
                .msg(ResultEnums.SYSTEM_ERROR.getResultMsg())
                .build();
        return result;
    }

    public static <T> FairyResult<T> fail(ResultEnums enums, T data) {
        FairyResult result = new FairyResult.FairyResultBuilder()
                .code(enums.getResultCode())
                .success(false)
                .msg(enums.getResultMsg())
                .data(data)
                .build();
        return result;
    }

    public static <T> FairyResult<T> fail(String code, String msg, T data) {
        FairyResult result = new FairyResult.FairyResultBuilder()
                .code(code)
                .success(false)
                .msg(msg)
                .data(data)
                .build();
        return result;
    }

    public static <T> FairyResult<T> success(T data, String message) {
        FairyResult result = new FairyResult.FairyResultBuilder()
                .code(ResultEnums.SUCCESS.getResultCode())
                .success(true)
                .msg(message)
                .data(data)
                .build();
        return result;
    }

}
