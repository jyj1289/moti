package com.moti.domain.auth.controller;

import com.moti.domain.auth.controller.dto.response.TokenResponse;
import com.moti.domain.auth.service.GoogleAuthService;
import com.moti.domain.user.domain.User;
import com.moti.shared.auth.AuthenticationPrincipal;
import com.moti.shared.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final GoogleAuthService googleAuthService;

    @GetMapping("/google/link")
    public ResponseEntity<CommonResponse<String>> getGoogleAuthUrl() {
        return ResponseEntity
                .ok()
                .body(CommonResponse.ok(googleAuthService.getLink()));
    }

    @PostMapping("/google")
    public ResponseEntity<CommonResponse<TokenResponse>> authWithGoogle(@RequestParam String code) {
        return ResponseEntity
                .ok()
                .body(CommonResponse.ok(googleAuthService.auth(code)));
    }

    @PostMapping("/refresh")
    public ResponseEntity<CommonResponse<TokenResponse>> refreshAccessToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String refreshToken) {
        return ResponseEntity
                .ok()
                .body(CommonResponse.ok(
                        googleAuthService.refresh(refreshToken)
                ));
    }

    @DeleteMapping
    public ResponseEntity<Void> logout(@AuthenticationPrincipal User user) {
        googleAuthService.logout(user);
        return ResponseEntity
                .noContent()
                .build();
    }
}
