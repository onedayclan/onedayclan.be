package com.clanone.onedayclan.clazz.adapter.in.web.response;

import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassReviewQuestionEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClassReviewQuestionResponse {
    private long seq;
    private String question;

    public static ClassReviewQuestionResponse of(ClassReviewQuestionEntity questionEntity){
        return ClassReviewQuestionResponse.builder()
                .seq(questionEntity.getSeq())
                .question(questionEntity.getQuestion())
                .build();
    }
}
