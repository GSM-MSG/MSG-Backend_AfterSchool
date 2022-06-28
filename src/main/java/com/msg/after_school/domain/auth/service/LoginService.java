package com.msg.after_school.domain.auth.service;

import com.msg.after_school.global.security.utils.ConfigUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final ConfigUtils configUtils;

    public ResponseEntity execute() {
        String authUrl = configUtils.googleInitUrl();
        URI redirectUri = null;
        try {
            redirectUri = new URI(authUrl);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(redirectUri);
            return new ResponseEntity(headers, HttpStatus.SEE_OTHER);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }
}
