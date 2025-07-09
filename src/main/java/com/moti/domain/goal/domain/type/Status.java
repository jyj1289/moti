package com.moti.domain.goal.domain.type;

import com.moti.shared.enumeration.EnumProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status implements EnumProperty {
    SUCCESS("성공"),
    FAILED("실패"),
    ONGOING("진행중");

    private final String description;
}
