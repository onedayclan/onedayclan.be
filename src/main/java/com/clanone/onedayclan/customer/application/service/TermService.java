package com.clanone.onedayclan.customer.application.service;

import com.clanone.onedayclan.customer.adapter.in.web.response.TermResponse;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.TermsEntity;
import com.clanone.onedayclan.customer.application.port.in.TermPort;
import com.clanone.onedayclan.customer.application.port.out.GetTermPort;
import com.clanone.onedayclan.customer.domain.enums.TermsType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TermService implements TermPort {

    private final GetTermPort getTermPort;

    @Override
    public List<TermResponse> getTerm() {
        List<TermsEntity> allTermList = getTermPort.getAllTermList();

        Map<TermsType, TermsEntity> resultMap = new HashMap<>();
        for (TermsEntity term : allTermList) {
            resultMap.putIfAbsent(term.getType(), term);
        }
        return resultMap.values().stream().map(TermResponse::of).collect(Collectors.toList());
    }
}
