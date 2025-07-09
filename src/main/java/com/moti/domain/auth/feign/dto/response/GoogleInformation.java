package com.moti.domain.auth.feign.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GoogleInformation {
    private String email;
    private String name;
    private String picture;
}
