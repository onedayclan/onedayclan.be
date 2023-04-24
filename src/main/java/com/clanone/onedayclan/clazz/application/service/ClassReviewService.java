package com.clanone.onedayclan.clazz.application.service;

import com.clanone.onedayclan.clazz.adapter.in.web.response.ClassReviewQuestionResponse;
import com.clanone.onedayclan.clazz.application.port.in.ClassReviewPort;
import com.clanone.onedayclan.clazz.application.port.out.GetClassReviewQuestionPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassReviewService implements ClassReviewPort {

    private final GetClassReviewQuestionPort getClassReviewQuestionPort;

    @Override
    public List<ClassReviewQuestionResponse> getClassReviewQuestion() {
        return getClassReviewQuestionPort.getClassReviewQuestionList()
                .stream()
                .map(ClassReviewQuestionResponse::of)
                .collect(Collectors.toList());
    }
}
