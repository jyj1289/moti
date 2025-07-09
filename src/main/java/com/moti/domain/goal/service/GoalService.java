package com.moti.domain.goal.service;

import com.moti.domain.goal.controller.dto.request.CreateGoalRequest;
import com.moti.domain.goal.controller.dto.response.GoalResponse;
import com.moti.domain.goal.controller.dto.response.SimpleGoalResponse;
import com.moti.domain.goal.domain.*;
import com.moti.domain.goal.domain.exception.AlreadyFailedException;
import com.moti.domain.goal.domain.exception.AlreadySuccessException;
import com.moti.domain.goal.domain.exception.GoalNotFoundException;
import com.moti.domain.goal.domain.type.Status;
import com.moti.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GoalService {

    private final GoalRepository goalRepository;
    private final ConsumptionHabitRepository consumptionHabitRepository;
    private final ImprovementMethodRepository improvementMethodRepository;

    public Long createdGoal(User user, CreateGoalRequest request) {
        Goal goal = Goal.builder()
                .name(request.getName())
                .endDate(request.getEndDate())
                .problem(request.getProblem())
                .analysis(request.getAnalysis())
                .consumptionType(request.getConsumptionType())
                .user(user)
                .build();
        Goal saved = goalRepository.save(goal);
        generateConsumptionHabit(request.getConsumptionHabits(), saved);
        genearteImprovementMethod(request.getImprovementMethods(), saved);

        return saved.getId();
    }

    public List<SimpleGoalResponse> getGoals(User user, List<Status> statusList) {
        if (statusList == null) {
            return goalRepository.findAllByUser(user).stream()
                    .map(SimpleGoalResponse::new)
                    .toList();
        }

        return goalRepository.findAllByUser(user).stream()
                .filter(goal -> statusList.contains(goal.getStatus()))
                .map(SimpleGoalResponse::new)
                .toList();
    }

    public GoalResponse getGoal(User user, Long id) {
        Goal goal = goalRepository.findByIdAndUser(id, user)
                .orElseThrow(GoalNotFoundException::new);
        return new GoalResponse(goal);
    }

    @Transactional
    public void success(User user, Long id) {
        Goal goal = goalRepository.findByIdAndUser(id, user)
                .orElseThrow(GoalNotFoundException::new);
        validate(goal);
        goal.success();
        user.increasePoint(1500);
    }

    @Transactional
    public void fail(User user, Long id) {
        Goal goal = goalRepository.findByIdAndUser(id, user)
                .orElseThrow(GoalNotFoundException::new);
        validate(goal);
        goal.fail();
    }

    private void validate(Goal goal) {
        if (goal.getStatus().equals(Status.SUCCESS)) {
            throw new AlreadySuccessException();
        } else if (goal.getStatus().equals(Status.FAILED)) {
            throw new AlreadyFailedException();
        }
    }

    private void generateConsumptionHabit(List<String> consumptionHabitList, Goal goal) {
        for (String consumptionHabit : consumptionHabitList) {
            consumptionHabitRepository.save(new ConsumptionHabit(consumptionHabit, goal));
        }
    }

    private void genearteImprovementMethod(List<String> improvementMethodList, Goal goal) {
        for (String improvementMethod : improvementMethodList) {
            improvementMethodRepository.save(new ImprovementMethod(improvementMethod, goal));
        }
    }
}
