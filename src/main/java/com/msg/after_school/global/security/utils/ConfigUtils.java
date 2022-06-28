package com.msg.after_school.global.security.utils;

import com.msg.after_school.global.security.properties.GoogleProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ConfigUtils {
    private final GoogleProperties googleProperties;

    public String googleInitUrl() {
        Map<String, Object> params = new HashMap<>();
        params.put("client_id", getGoogleClientId());
        params.put("redirect_uri", getGoogleRedirectUri());
        params.put("response_type", "code");
        params.put("scope", getScopeUrl());
        String paramStr = params.entrySet().stream()
                .map(param -> param.getKey() + "=" + param.getValue())
                .collect(Collectors.joining("&"));
        return getGoogleLoginUrl()
                + "/o/oauth2/v2/auth"
                + "?"
                + paramStr;
    }

    public String getGoogleAuthUrl() {
        return googleProperties.getAuthUrl();
    }

    public String getGoogleLoginUrl() {
        return googleProperties.getLoginUrl();
    }

    public String getGoogleClientId() {
        return googleProperties.getClientId();
    }

    public String getGoogleRedirectUri() {
        return googleProperties.getRedirectUrl();
    }

    public String getGoogleSecret() {
        return googleProperties.getClientSecret();
    }

    public String getScopeUrl() {
        return googleProperties.getScope().replaceAll(", ", "%20");
    }
}
