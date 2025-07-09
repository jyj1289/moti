package com.moti.domain.auth.service;

import com.moti.domain.auth.controller.dto.response.TokenResponse;
import com.moti.domain.auth.feign.GoogleAuthClient;
import com.moti.domain.auth.feign.GoogleInformationClient;
import com.moti.domain.auth.feign.dto.request.GoogleAuthRequest;
import com.moti.domain.auth.feign.dto.response.GoogleInformation;
import com.moti.domain.user.domain.User;
import com.moti.domain.user.domain.UserRepository;
import com.moti.shared.config.properties.GoogleOAuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GoogleAuthService {

    private final GoogleOAuthProperties googleOAuthProperties;
    private final GoogleAuthClient googleAuthClient;
    private final GoogleInformationClient googleInformationClient;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    private static final String QUERY_STRING = "?client_id=%s&redirect_uri=%s&response_type=code&" +
            "scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile";

    public String getLink() {
        return googleOAuthProperties.getGoogle().getBaseUrl()
                + String.format(QUERY_STRING, googleOAuthProperties.getGoogle().getClientId(), googleOAuthProperties.getGoogle().getRedirectUri());
    }

    public TokenResponse auth(String code) {
        String accessToken = googleAuthClient
                .getAccessToken(createGoogleAuthRequest(code))
                .getAccessToken();
        GoogleInformation information = googleInformationClient.getUserInformation("Bearer " + accessToken);

        String email = information.getEmail();
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            userRepository.save(
                    new User(information.getName(), email, information.getPicture())
            );
        }

        return TokenResponse.builder()
                .accessToken(tokenService.generateAccessToken(email))
                .refreshToken(tokenService.generateRefreshToken(email))
                .build();
    }

    private GoogleAuthRequest createGoogleAuthRequest(String code) {
        return GoogleAuthRequest.builder()
                .code(code)
                .clientId(googleOAuthProperties.getGoogle().getClientId())
                .redirectUri(googleOAuthProperties.getGoogle().getRedirectUri())
                .clientSecret(googleOAuthProperties.getGoogle().getClientSecret())
                .build();
    }
}
