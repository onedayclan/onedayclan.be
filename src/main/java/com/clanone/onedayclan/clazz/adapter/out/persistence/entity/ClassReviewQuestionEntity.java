package com.clanone.onedayclan.clazz.adapter.out.persistence.entity;

import com.clanone.onedayclan.audit.AbstractUpdatableEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "class_review_question")
@NoArgsConstructor
public class ClassReviewQuestionEntity extends AbstractUpdatableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @Column(nullable = false, length = 300)
    private String question;

    @Column(nullable = false)
    private boolean usedYn;

    @Builder
    public ClassReviewQuestionEntity(String question, boolean usedYn) {
        this.question = question;
        this.usedYn = usedYn;
    }
}
