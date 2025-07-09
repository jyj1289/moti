package com.moti.domain.auth.feign;

import com.moti.domain.auth.feign.dto.request.GoogleAuthRequest;
import com.moti.domain.auth.feign.dto.response.GoogleTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "GoogleAuthClient", url = "https://oauth2.googleapis.com/token")
public interface GoogleAuthClient {

    @PostMapping
    GoogleTokenResponse getAccessToken(GoogleAuthRequest request);
}
