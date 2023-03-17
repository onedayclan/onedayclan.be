package com.clanone.onedayclan.customer.application.service;

import com.clanone.onedayclan.customer.adapter.in.web.request.TermsCreateRequest;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminTermsDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminTermsResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.TermsResponse;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.TermsEntity;
import com.clanone.onedayclan.customer.application.port.in.TermsPort;
import com.clanone.onedayclan.customer.application.port.out.GetTermsPort;
import com.clanone.onedayclan.customer.domain.enums.TermsType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TermService implements TermsPort {

    private final GetTermsPort getTermsPort;

    @Override
    public List<TermsResponse> getTerms() {
        List<TermsEntity> allTermList = getTermsPort.getAllTermsList();

        Map<TermsType, TermsEntity> resultMap = new HashMap<>();
        for (TermsEntity term : allTermList) {
            resultMap.putIfAbsent(term.getType(), term);
        }
        return resultMap.values().stream().map(TermsResponse::of).collect(Collectors.toList());
    }

    @Override
    public Page<AdminTermsResponse> getTermsList(Pageable pageable) {
        return getTermsPort.getTermsList(pageable);
    }

    @Override
    public AdminTermsDetailResponse getTermsForAdmin(long termsSeq) {
        return AdminTermsDetailResponse.of(getTermsPort.getTerms(termsSeq));
    }

    @Override
    @Transactional
    public AdminTermsDetailResponse updateTermsForAdmin(long termsSeq, TermsCreateRequest request) {
        TermsEntity terms = getTermsPort.getTerms(termsSeq);
        terms.update(request);
        return AdminTermsDetailResponse.of(terms);
    }
}
