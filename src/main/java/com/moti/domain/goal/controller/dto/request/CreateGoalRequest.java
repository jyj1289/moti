package com.moti.domain.goal.controller.dto.request;

import com.moti.domain.goal.domain.type.ConsumptionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class CreateGoalRequest {

    @NotBlank(message = "필수값입니다.")
    private String name;

    @NotNull(message = "필수값입니다.")
    private LocalDate endDate;

    @NotBlank(message = "필수값입니다.")
    private String problem;

    @NotBlank(message = "필수값입니다.")
    private String analysis;

    @NotNull(message = "필수값입니다.")
    private ConsumptionType consumptionType;

    @NotNull(message = "필수값입니다.")
    private List<String> consumptionHabits;

    @NotNull(message = "필수값입니다.")
    private List<String> improvementMethods;
}


