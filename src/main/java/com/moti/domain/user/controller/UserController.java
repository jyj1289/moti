package com.moti.domain.user.controller;

import com.moti.domain.user.controller.dto.response.UserResponse;
import com.moti.domain.user.domain.User;
import com.moti.domain.user.service.UserService;
import com.moti.shared.auth.AuthenticationPrincipal;
import com.moti.shared.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<CommonResponse<UserResponse>> getUser(@AuthenticationPrincipal User user) {
        return ResponseEntity
                .ok()
                .body(CommonResponse.ok(
                        new UserResponse(user)
                ));
    }
}
