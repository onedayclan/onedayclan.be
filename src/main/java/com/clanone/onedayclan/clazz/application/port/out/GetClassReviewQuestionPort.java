package com.clanone.onedayclan.clazz.application.port.out;

import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassReviewQuestionEntity;

import java.util.List;

public interface GetClassReviewQuestionPort {
    List<ClassReviewQuestionEntity> getClassReviewQuestionList();
    List<ClassReviewQuestionEntity> getClassReviewQuestionBySeqList(List<Long> questionSeq);
}
