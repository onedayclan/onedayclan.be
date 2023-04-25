package com.clanone.onedayclan.clazz.adapter.out.persistence.adapter;

import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassReviewEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassReviewImageEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassReviewQuestionCheckEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassReviewQuestionEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.repository.ClassReviewImageRepository;
import com.clanone.onedayclan.clazz.adapter.out.persistence.repository.ClassReviewQuestionCheckRepository;
import com.clanone.onedayclan.clazz.adapter.out.persistence.repository.ClassReviewQuestionRepository;
import com.clanone.onedayclan.clazz.adapter.out.persistence.repository.ClassReviewRepository;
import com.clanone.onedayclan.clazz.application.port.out.GetClassReviewQuestionPort;
import com.clanone.onedayclan.clazz.application.port.out.ManageClassReviewPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClassReviewQuestionAdapter implements GetClassReviewQuestionPort, ManageClassReviewPort {

    private final ClassReviewRepository classReviewRepository;
    private final ClassReviewQuestionRepository classReviewQuestionRepository;
    private final ClassReviewQuestionCheckRepository classReviewQuestionCheckRepository;
    private final ClassReviewImageRepository classReviewImageRepository;

    @Override
    public List<ClassReviewQuestionEntity> getClassReviewQuestionList() {
        return classReviewQuestionRepository.findByUsedYnOrderByCreatedAtDesc(true);
    }

    @Override
    public List<ClassReviewQuestionEntity> getClassReviewQuestionBySeqList(List<Long> questionSeq) {
        return classReviewQuestionRepository.findBySeqIn(questionSeq);
    }

    @Override
    public void insertClassReview(ClassReviewEntity classReview) {
        classReviewRepository.save(classReview);
    }

    @Override
    public void insertClassReviewQuestion(List<ClassReviewQuestionCheckEntity> question) {
        classReviewQuestionCheckRepository.saveAll(question);
    }

    @Override
    public void insertClassReviewImage(List<ClassReviewImageEntity> reviewImageEntityList) {
        classReviewImageRepository.saveAll(reviewImageEntityList);
    }
}
