package com.moti.domain.goal.domain.type;

import com.moti.shared.enumeration.EnumProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConsumptionType implements EnumProperty {
    SHOPPING("쇼핑중독");

    private final String description;
}
