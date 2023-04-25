package com.clanone.onedayclan.clazz.application.port.in;

import com.clanone.onedayclan.clazz.adapter.in.web.request.WriteClassReviewRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.response.ClassReviewQuestionResponse;

import java.util.List;

public interface ClassReviewPort {
    List<ClassReviewQuestionResponse> getClassReviewQuestion();
    void writeClassReview(String userId, long classSeq, WriteClassReviewRequest writeClassReviewRequest);
}
