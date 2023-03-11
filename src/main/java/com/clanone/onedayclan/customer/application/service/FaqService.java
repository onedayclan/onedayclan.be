package com.clanone.onedayclan.customer.application.service;

import com.clanone.onedayclan.customer.adapter.in.web.request.FaqManageRequest;
import com.clanone.onedayclan.customer.adapter.in.web.request.FaqSearchRequest;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminFaqDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminFaqResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.FaqResponse;
import com.clanone.onedayclan.customer.adapter.out.model.FaqSearchModel;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.FaqEntity;
import com.clanone.onedayclan.customer.application.port.in.FaqPort;
import com.clanone.onedayclan.customer.application.port.out.GetFaqPort;
import com.clanone.onedayclan.customer.application.port.out.ManageFaqPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FaqService implements FaqPort{

    private final GetFaqPort getFaqPort;
    private final ManageFaqPort manageFaqPort;

    @Override
    public List<FaqResponse> getFaqList() {
        List<FaqEntity> faqList = getFaqPort.getFaqList();
        return faqList.stream().map(FaqResponse::of).collect(Collectors.toList());
    }

    @Override
    public Page<AdminFaqResponse> getFaqListForAdmin(FaqSearchRequest request, Pageable pageable) {
        return getFaqPort.getFaqListForAdmin(FaqSearchModel.builder()
                .title(request.getTitle())
                .searchStartAt(request.getSearchStartAt())
                .searchEndAt(request.getSearchEndAt())
                .build(), pageable);
    }

    @Override
    public AdminFaqDetailResponse getFaq(long faqSeq) {
        return AdminFaqDetailResponse.of(getFaqPort.getFaq(faqSeq));
    }

    @Override
    public AdminFaqDetailResponse insertFaq(FaqManageRequest request) {
        return AdminFaqDetailResponse.of(manageFaqPort.saveFaq(FaqEntity.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .category(request.getCategory())
                .showYn(request.isShowYn())
                .build()));
    }

    @Override
    @Transactional
    public AdminFaqDetailResponse updateFaq(FaqManageRequest request, long faqSeq) {
        FaqEntity faq = getFaqPort.getFaq(faqSeq);
        faq.update(request);
        return AdminFaqDetailResponse.of(faq);
    }

    @Override
    @Transactional
    public void deleteFaq(long faqSeq) {
        manageFaqPort.deleteFaq(faqSeq);
    }
}
