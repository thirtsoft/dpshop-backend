package com.dp.dpshopbackend.message.response;

import lombok.Data;

@Data
public class ResponseToken {
    private String token_type;
    private String access_token;
    private int expires_in;
}
