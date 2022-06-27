package com.msg.after_school.domain.auth.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.msg.after_school.domain.auth.data.dto.GoogleLoginDto;
import com.msg.after_school.domain.auth.data.dto.GoogleLoginRequest;
import com.msg.after_school.domain.auth.data.dto.GoogleLoginResponse;
import com.msg.after_school.domain.auth.data.dto.TokenDto;
import com.msg.after_school.domain.auth.exception.GoogleOAuthFailedException;
import com.msg.after_school.domain.auth.utils.GSMProvider;
import com.msg.after_school.domain.user.data.entity.User;
import com.msg.after_school.domain.user.repository.UserRepository;
import com.msg.after_school.global.security.JwtTokenProvider;
import com.msg.after_school.global.security.utils.ConfigUtils;
import com.msg.after_school.global.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RedirectService {
    private final ConfigUtils configUtils;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final GSMProvider gsmProvider;

    public TokenDto execute(String code) {
        RestTemplate restTemplate = new RestTemplate();
        GoogleLoginRequest reqParam = GoogleLoginRequest.builder()
                .clientId(configUtils.getGoogleClientId())
                .clientSecret(configUtils.getGoogleSecret())
                .code(code)
                .redirectUri(configUtils.getGoogleRedirectUri())
                .grantType("authorization_code")
                .build();

        try {
            gsmProvider.setupUsers();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity httpRequestEntity = new HttpEntity(reqParam, headers);
            ResponseEntity<String> apiResponseJson = restTemplate.postForEntity(configUtils.getGoogleAuthUrl() + "/token", httpRequestEntity, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            GoogleLoginResponse googleLoginResponse = objectMapper.readValue(apiResponseJson.getBody(), new TypeReference<GoogleLoginResponse>() {});

            String jwtToken = googleLoginResponse.getIdToken();

            String requestUrl = UriComponentsBuilder.fromHttpUrl(configUtils.getGoogleAuthUrl() + "/tokeninfo").queryParam("id_token", jwtToken).toUriString();

            String resultJson = restTemplate.getForObject(requestUrl, String.class);

            if(resultJson != null) {
                GoogleLoginDto userInfoDto = objectMapper.readValue(resultJson, new TypeReference<GoogleLoginDto>() {});
                JSONObject gsmUser = gsmProvider.findGSMUser(userInfoDto.getEmail());
                if (gsmUser == null){
                    return null;
                }
                String access = jwtTokenProvider.generateAccessToken(userInfoDto.getEmail());
                String refresh = jwtTokenProvider.generateRefreshToken(userInfoDto.getEmail());
                Integer accessExp = 60 * 15;
                Integer refreshExp = 60 * 60 * 24 * 7;
                Optional<User> findUser = userRepository.findUserByEmail(userInfoDto.getEmail());

                if (findUser.isPresent()) {
                    User user = findUser.orElseThrow(UserNotFoundException::new);
                    user.updateRefreshToken(refresh);
                } else {
                    User user = User.builder()
                            .email(userInfoDto.getEmail())
                            .class_((Long) gsmUser.get("class"))
                            .grade((Long) gsmUser.get("grade"))
                            .name((String) gsmUser.get("name"))
                            .num((Long) gsmUser.get("num"))
                            .userImg(userInfoDto.getPicture())
                            .refreshToken(refresh)
                            .build();
                    userRepository.save(user);
                }
                return TokenDto.builder()
                        .accessToken(access)
                        .refreshToken(refresh)
                        .accessExp(accessExp)
                        .refreshExp(refreshExp)
                        .build();
            }
            else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
