package com.moti.domain.goal.controller;

import com.moti.domain.goal.controller.dto.request.CreateGoalRequest;
import com.moti.domain.goal.controller.dto.response.GoalResponse;
import com.moti.domain.goal.controller.dto.response.SimpleGoalResponse;
import com.moti.domain.goal.domain.type.Status;
import com.moti.domain.goal.service.GoalService;
import com.moti.domain.user.domain.User;
import com.moti.shared.auth.AuthenticationPrincipal;
import com.moti.shared.response.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<CommonResponse<List<SimpleGoalResponse>>> getGoals(
            @AuthenticationPrincipal User user,
            @RequestParam(name = "status", required = false) List<Status> statusList
    ) {
        return ResponseEntity
                .ok()
                .body(CommonResponse.ok(
                        goalService.getGoals(user, statusList)
                ));
    }

    @PatchMapping("/{id}/success")
    public ResponseEntity<Void> success(
            @AuthenticationPrincipal User user,
            @PathVariable Long id
    ) {
        goalService.success(user, id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PatchMapping("/{id}/fail")
    public ResponseEntity<Void> fail(
            @AuthenticationPrincipal User user,
            @PathVariable Long id
    ) {
        goalService.fail(user, id);
        return ResponseEntity
                .noContent()
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoal(
            @AuthenticationPrincipal User user,
            @PathVariable Long id
    ) {
        goalService.deleteGoal(user, id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
