package com.fairy.base.common.enums;

/**
 * 性别枚举
 *
 * @author hll
 * @version 1.0
 * @date 2022/6/28 16:13
 */
public enum GenderEnums {
    MEN("0", "男"),
    WOMMEN("1", "女"),
    ELSE("-1", "未知");

    private String code;
    private String msg;

    GenderEnums(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static GenderEnums getGender(String code){
        for (GenderEnums genderEnums:GenderEnums.values()){
            if(genderEnums.code.equals(code)){
                return genderEnums;
            }
        }
        return ELSE;
    }
}
