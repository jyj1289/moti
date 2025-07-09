package com.moti.domain.goal.domain.type;

import com.moti.shared.enumeration.EnumProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConsumptionType implements EnumProperty {
    SAVING("절약형"),
    INVESTMENT("투자형"),
    FLEX("플렉서형"),
    EXPERIENCE("경험형"),
    FOOD("미식형"),
    IMPROVEMENT("자기계발형"),
    SHOPPING("쇼핑러");

    private final String description;
}
