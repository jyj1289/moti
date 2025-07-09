package com.moti.domain.goal.controller;

import com.moti.domain.goal.controller.dto.request.CreateGoalRequest;
import com.moti.domain.goal.controller.dto.response.GoalResponse;
import com.moti.domain.goal.service.GoalService;
import com.moti.domain.user.domain.User;
import com.moti.shared.auth.AuthenticationPrincipal;
import com.moti.shared.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/goals")
@RestController
public class GoalController {

    private final GoalService goalService;

    @PostMapping
    public ResponseEntity<Void> createGoal(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid CreateGoalRequest request
    ) {
        Long id = goalService.createdGoal(user, request);
        return ResponseEntity
                .created(URI.create("/goals/" + id))
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<GoalResponse>> getGoal(
            @AuthenticationPrincipal User user,
            @PathVariable Long id
    ) {
        return ResponseEntity
                .ok()
                .body(CommonResponse.ok(
                        goalService.getGoal(user, id)
                ));
    }
}
