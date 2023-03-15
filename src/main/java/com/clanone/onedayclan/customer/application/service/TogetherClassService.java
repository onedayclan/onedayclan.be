package com.clanone.onedayclan.customer.application.service;

import com.clanone.onedayclan.customer.adapter.in.web.request.ApplyTogetherClassRequest;
import com.clanone.onedayclan.customer.adapter.in.web.request.TogetherClassAnswerCreateRequest;
import com.clanone.onedayclan.customer.adapter.in.web.request.TogetherClassSearchRequest;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminTogetherClassDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminTogetherClassResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.TogetherClassDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.TogetherClassResponse;
import com.clanone.onedayclan.customer.adapter.out.model.TogetherClassSearchModel;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.TogetherClassEntity;
import com.clanone.onedayclan.customer.application.port.in.TogetherClassPort;
import com.clanone.onedayclan.customer.application.port.out.GetTogetherClassPort;
import com.clanone.onedayclan.customer.application.port.out.ManageTogetherClassPort;
import com.clanone.onedayclan.member.application.port.out.GetMemberPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return getTogetherClassPort.getTogetherClassList(userId).stream().map(TogetherClassResponse::of).collect(Collectors.toList());
    }

    @Override
    public TogetherClassDetailResponse getTogetherClassDetail(String userId, long seq) {
        TogetherClassEntity togetherClassDetail = getTogetherClassPort.getTogetherClassDetail(userId, seq);
        return TogetherClassDetailResponse.of(togetherClassDetail);
    }

    @Override
    public Page<AdminTogetherClassResponse> getTogetherClassListForAdmin(TogetherClassSearchRequest request, Pageable pageable) {
        return getTogetherClassPort.getTogetherListForAdmin(TogetherClassSearchModel.builder()
                        .name(request.getName())
                        .userId(request.getUserId())
                        .category(request.getCategory())
                        .answerYn(request.getAnswerYn())
                        .organizationSeq(request.getOrganizationSeq())
                        .searchStartAt(request.getSearchStartAt())
                        .searchEndAt(request.getSearchEndAt())
                .build(), pageable);
    }

    @Override
    public AdminTogetherClassDetailResponse getTogetherClassForAdmin(long togetherClassSeq) {
        return getTogetherClassPort.getTogetherForAdmin(togetherClassSeq);
    }

    @Override
    @Transactional
    public void applyTogetherClassAnswer(long togetherClassSeq, TogetherClassAnswerCreateRequest request) {
        TogetherClassEntity togetherClass = getTogetherClassPort.getTogetherClassById(togetherClassSeq);
        togetherClass.answer(request.getAnswer());
    }

    @Override
    @Transactional
    public void deleteTogetherClassAnswer(long togetherClassSeq) {
        TogetherClassEntity togetherClass = getTogetherClassPort.getTogetherClassById(togetherClassSeq);
        togetherClass.deleteAnswer();
    }
}
