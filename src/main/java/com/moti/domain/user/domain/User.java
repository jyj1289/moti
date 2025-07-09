package com.moti.domain.user.domain;

import com.moti.shared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String picture;

    @Column(nullable = false)
    private int point;

    public User(String name, String email, String picture) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.point = 0;
    }
}
