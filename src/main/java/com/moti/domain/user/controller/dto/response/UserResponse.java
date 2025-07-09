package com.moti.domain.user.controller.dto.response;

import com.moti.domain.user.domain.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserResponse {

    public final String name;
    public final String email;
    public final String picture;
    public final int point;

    public UserResponse(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.point = user.getPoint();
    }
}
