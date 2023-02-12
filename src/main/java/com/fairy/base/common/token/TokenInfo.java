package com.fairy.base.common.token;

import lombok.Data;

import java.util.Map;

/**
 * @author hll
 * @version 1.0
 * @date 2023/2/12 21:02
 */
@Data
public class TokenInfo {
    private String access_token;

    private String token_type;

    private String refresh_token;

    private String scope;

    private Map<String,String> additionalInfo;
}
