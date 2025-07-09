package com.moti.domain.goal.controller.dto.response;

import com.moti.domain.goal.domain.Goal;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class SimpleGoalResponse {

    private final Long id;
    private final String name;
    private final LocalDate endDate;

    public SimpleGoalResponse(Goal goal) {
        this.id = goal.getId();
        this.name = goal.getName();
        this.endDate = goal.getEndDate();
    }
}
