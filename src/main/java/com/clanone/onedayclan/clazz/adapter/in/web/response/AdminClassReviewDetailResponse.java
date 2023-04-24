package com.clanone.onedayclan.clazz.adapter.in.web.response;

import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassReviewEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassReviewQuestionCheckEntity;
import com.clanone.onedayclan.common.application.service.utils.ImageUtil;
import lombok.Builder;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class AdminClassReviewDetailResponse {
    private String userId;
    private String userName;
    private String organizationName;
    private String phone;
    private int star;
    private String choiceQuestion;
    private List<String> reviewImageList;

    public static AdminClassReviewDetailResponse of(ClassReviewEntity review) {
        return AdminClassReviewDetailResponse.builder()
                .userId(review.getMember().getUserId())
                .userName(review.getMember().getName())
                .organizationName(review.getMember().getConfirmOrganization().getName())
                .phone(review.getMember().getPhone())
                .star(review.getStar())
                .choiceQuestion(Strings.join(review.getQuestionCheckList().stream().map(ClassReviewQuestionCheckEntity::getQuestionTitle).collect(Collectors.toList()), ','))
                .reviewImageList(review.getImageList().stream().map(s -> ImageUtil.getS3Bucket() + s.getImage().getUrl()).collect(Collectors.toList()))
                .build();
    }
}
