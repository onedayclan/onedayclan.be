package com.clanone.onedayclan.clazz.application.port.out;

import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassReviewEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassReviewImageEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassReviewQuestionCheckEntity;

import java.util.List;

public interface ManageClassReviewPort {
    void insertClassReview(ClassReviewEntity classReview);
    void insertClassReviewQuestion(List<ClassReviewQuestionCheckEntity> question);
    void insertClassReviewImage(List<ClassReviewImageEntity> reviewImageEntityList);
}
