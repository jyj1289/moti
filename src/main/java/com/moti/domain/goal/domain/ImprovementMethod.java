package com.moti.domain.goal.domain;

import com.moti.shared.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ImprovementMethod extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "improvement_method_id")
    private Long id;

    @Column(nullable = false)
    private String method;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "goal_id")
    private Goal goal;

    public ImprovementMethod(String method, Goal goal) {
        this.method = method;
        this.goal = goal;
    }
}
