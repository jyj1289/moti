package com.moti.domain.goal.controller.dto.response;

import com.moti.domain.goal.domain.ConsumptionHabit;
import com.moti.domain.goal.domain.Goal;
import com.moti.domain.goal.domain.ImprovementMethod;
import com.moti.domain.goal.domain.type.ConsumptionType;
import com.moti.domain.goal.domain.type.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class GoalResponse {

    private final String name;
    private final LocalDate endDate;
    private final String problem;
    private final String analysis;
    private final ConsumptionType consumptionType;
    private final Status status;
    private final List<String> consumptionHabitList;
    private final List<String> improvementMethodList;

    public GoalResponse(Goal goal) {
        this.name = goal.getName();
        this.endDate = goal.getEndDate();
        this.problem = goal.getProblem();
        this.analysis = goal.getAnalysis();
        this.consumptionType = goal.getConsumptionType();
        this.status = goal.getStatus();
        this.consumptionHabitList = goal.getConsumptionHabitList()
                .stream()
                .map(ConsumptionHabit::getHabit)
                .toList();
        this.improvementMethodList = goal.getImprovementMethodList()
                .stream()
                .map(ImprovementMethod::getMethod)
                .toList();
    }
}
