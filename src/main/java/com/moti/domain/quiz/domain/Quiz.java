package com.moti.domain.quiz.domain;

import com.moti.shared.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Quiz extends BaseTimeEntity {

    @Id
    @Column(name = "quiz_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private Long correctAnswer;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    private List<Answer> answerList;
}
