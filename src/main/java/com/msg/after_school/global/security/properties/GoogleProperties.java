package com.msg.after_school.global.security.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@AllArgsConstructor
@ConfigurationProperties(prefix = "googles")
public class GoogleProperties {
    private final String authUrl;
    private final String loginUrl;
    private final String redirectUrl;
    private final String clientId;
    private final String clientSecret;
    private final String scope;
}
