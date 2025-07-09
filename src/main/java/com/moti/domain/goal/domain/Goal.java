package com.moti.domain.goal.domain;

import com.moti.domain.goal.domain.type.ConsumptionType;
import com.moti.domain.goal.domain.type.Status;
import com.moti.domain.user.domain.User;
import com.moti.shared.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Goal extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String problem;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String analysis;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConsumptionType consumptionType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @OneToMany(mappedBy = "goal", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ConsumptionHabit> consumptionHabitList;

    @OneToMany(mappedBy = "goal", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ImprovementMethod> improvementMethodList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public void success() {
        this.status = Status.SUCCESS;
    }

    public void fail() {
        this.status = Status.FAILED;
    }

    @Builder
    public Goal(String name, LocalDate endDate, String problem, String analysis, ConsumptionType consumptionType, User user) {
        this.name = name;
        this.endDate = endDate;
        this.problem = problem;
        this.analysis = analysis;
        this.consumptionType = consumptionType;
        this.status = Status.ONGOING;
        this.user = user;
    }
}
