package com.msg.after_school.domain.auth.data.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoogleLoginRequest {
    private String clientId;
    private String redirectUri;
    private String clientSecret;
    private String scope;
    private String code;
    private String grantType;
}
