package com.clanone.onedayclan.customer.application.service;

import com.clanone.onedayclan.customer.adapter.in.web.response.FaqResponse;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.FaqEntity;
import com.clanone.onedayclan.customer.application.port.in.FaqPort;
import com.clanone.onedayclan.customer.application.port.out.GetFaqPort;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FaqService implements FaqPort{

    private final GetFaqPort getFaqPort;

    @Override
    public List<FaqResponse> getFaqList() {
        List<FaqEntity> faqList = getFaqPort.getFaqList();
        return faqList.stream().map(FaqResponse::of).collect(Collectors.toList());
    }
}
