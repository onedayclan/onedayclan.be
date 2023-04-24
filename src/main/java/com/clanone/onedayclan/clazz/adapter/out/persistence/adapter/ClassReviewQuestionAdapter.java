package com.clanone.onedayclan.clazz.adapter.out.persistence.adapter;

import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassReviewQuestionEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.repository.ClassReviewQuestionRepository;
import com.clanone.onedayclan.clazz.application.port.out.GetClassReviewQuestionPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClassReviewQuestionAdapter implements GetClassReviewQuestionPort {

    private final ClassReviewQuestionRepository classReviewQuestionRepository;

    @Override
    public List<ClassReviewQuestionEntity> getClassReviewQuestionList() {
        return classReviewQuestionRepository.findByUsedYnOrderByCreatedAtDesc(true);
    }
}
