package com.clanone.onedayclan.clazz.application.service;

import com.clanone.onedayclan.clazz.adapter.in.web.request.WriteClassReviewRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.response.ClassReviewQuestionResponse;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassReviewEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassReviewImageEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassReviewQuestionCheckEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassReviewQuestionEntity;
import com.clanone.onedayclan.clazz.application.port.in.ClassReviewPort;
import com.clanone.onedayclan.clazz.application.port.out.GetClassPort;
import com.clanone.onedayclan.clazz.application.port.out.GetClassReviewQuestionPort;
import com.clanone.onedayclan.clazz.application.port.out.ManageClassReviewPort;
import com.clanone.onedayclan.clazz.application.service.exception.MemberNotAttendanceClassException;
import com.clanone.onedayclan.common.application.port.out.ImagePort;
import com.clanone.onedayclan.member.application.port.out.GetMemberPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassReviewService implements ClassReviewPort {

    private final GetClassReviewQuestionPort getClassReviewQuestionPort;
    private final ManageClassReviewPort manageClassReviewPort;
    private final GetClassPort getClassPort;
    private final GetMemberPort getMemberPort;
    private final ImagePort imagePort;

    @Override
    public List<ClassReviewQuestionResponse> getClassReviewQuestion() {
        return getClassReviewQuestionPort.getClassReviewQuestionList()
                .stream()
                .map(ClassReviewQuestionResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void writeClassReview(String userId, long classSeq, WriteClassReviewRequest writeClassReviewRequest) {

        if (!getClassPort.checkMemberAttendClass(userId, classSeq)){
            throw new MemberNotAttendanceClassException();
        }

        ClassReviewEntity classReview = ClassReviewEntity.builder()
                .clazz(getClassPort.getClass(classSeq))
                .member(getMemberPort.getMemberByUserId(userId))
                .content(writeClassReviewRequest.getContent())
                .star(writeClassReviewRequest.getStar())
                .wishClass(writeClassReviewRequest.getWishClass())
                .build();
        manageClassReviewPort.insertClassReview(classReview);

        List<ClassReviewQuestionEntity> questions = getClassReviewQuestionPort.getClassReviewQuestionBySeqList(writeClassReviewRequest.getQuestions());
        List<ClassReviewQuestionCheckEntity> checkedQuestion = questions.stream().map(question ->
                        ClassReviewQuestionCheckEntity.of(classReview, question))
                .collect(Collectors.toList());
        manageClassReviewPort.insertClassReviewQuestion(checkedQuestion);

        List<Long> images = writeClassReviewRequest.getImages();
        List<ClassReviewImageEntity> classReviewImageList = imagePort.getImageListBySeq(images).stream().map(image ->
                        ClassReviewImageEntity.of(classReview, image))
                .collect(Collectors.toList());
        manageClassReviewPort.insertClassReviewImage(classReviewImageList);

    }
}
