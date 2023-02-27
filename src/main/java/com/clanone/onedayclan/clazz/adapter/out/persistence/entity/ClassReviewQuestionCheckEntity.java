package com.clanone.onedayclan.clazz.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "class_review_question_check")
@NoArgsConstructor
public class ClassReviewQuestionCheckEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @ManyToOne
    @JoinColumn(name = "class_review", referencedColumnName = "seq")
    private ClassReviewEntity review;

    @ManyToOne
    @JoinColumn(name = "class_review_question_seq", referencedColumnName = "seq")
    private ClassReviewQuestionEntity question;

    @Builder
    public ClassReviewQuestionCheckEntity(ClassReviewEntity review, ClassReviewQuestionEntity question) {
        this.review = review;
        this.question = question;
    }
}
