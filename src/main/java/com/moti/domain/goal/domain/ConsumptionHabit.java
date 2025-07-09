package com.moti.domain.goal.domain;

import com.moti.shared.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ConsumptionHabit extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consumption_habit_id")
    private Long id;

    @Column(nullable = false)
    private String habit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "goal_id")
    private Goal goal;

    public ConsumptionHabit(String habit,Goal goal) {
        this.habit = habit;
        this.goal = goal;
    }
}
