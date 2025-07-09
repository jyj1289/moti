package com.moti.domain.auth.feign;

import com.moti.domain.auth.feign.dto.response.GoogleInformation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "GoogleInformationClient", url = "https://www.googleapis.com/oauth2/v1/userinfo")
public interface GoogleInformationClient {

    @GetMapping("?alt=json")
    GoogleInformation getUserInformation(@RequestHeader("Authorization") String accessToken);
}
