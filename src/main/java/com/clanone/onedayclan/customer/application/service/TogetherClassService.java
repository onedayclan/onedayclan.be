package com.clanone.onedayclan.customer.application.service;

import com.clanone.onedayclan.customer.adapter.in.web.request.ApplyTogetherClassRequest;
import com.clanone.onedayclan.customer.adapter.in.web.response.TogetherClassDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.TogetherClassResponse;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.TogetherClassEntity;
import com.clanone.onedayclan.customer.application.port.in.TogetherClassPort;
import com.clanone.onedayclan.customer.application.port.out.GetTogetherClassPort;
import com.clanone.onedayclan.customer.application.port.out.ManageTogetherClassPort;
import com.clanone.onedayclan.member.application.port.out.GetMemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TogetherClassService implements TogetherClassPort {

    private final ManageTogetherClassPort manageTogetherClassPort;
    private final GetTogetherClassPort getTogetherClassPort;
    private final GetMemberPort getMemberPort;

    @Override
    public void applyTogetherClass(String userId, ApplyTogetherClassRequest request) {
        TogetherClassEntity togetherClassEntity = TogetherClassEntity.builder()
                .member(getMemberPort.getMemberByUserId(userId))
                .title(request.getTitle())
                .category(request.getCategory())
                .limitPeople(request.getLimitPeople())
                .content(request.getContent())
                .build();
        manageTogetherClassPort.insertClassTogether(togetherClassEntity);
    }

    @Override
    public List<TogetherClassResponse> getTogetherClass(String userId) {
        return getTogetherClassPort.getTogetherClassList(userId).stream().map(togetherClassEntity -> TogetherClassResponse.builder()
                    .seq(togetherClassEntity.getSeq())
                    .title(togetherClassEntity.getTitle())
                    .createdAt(togetherClassEntity.getCreatedAt())
                    .answerYn(togetherClassEntity.getAnswer() == null ? "미답변" : "답변완료")
                    .build()
        ).collect(Collectors.toList());
    }

    @Override
    public TogetherClassDetailResponse getTogetherClassDetail(String userId, long seq) {
        TogetherClassEntity togetherClassDetail = getTogetherClassPort.getTogetherClassDetail(userId, seq);
        return TogetherClassDetailResponse.of(togetherClassDetail);
    }
}
