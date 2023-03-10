package com.clanone.onedayclan.customer.adapter.out.persistence;

import com.clanone.onedayclan.customer.adapter.in.web.response.FaqResponse;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.FaqEntity;
import com.clanone.onedayclan.customer.adapter.out.persistence.repository.FaqRepository;
import com.clanone.onedayclan.customer.application.port.out.GetFaqPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FaqAdapter implements GetFaqPort {

    private final FaqRepository faqRepository;

    @Override
    public List<FaqEntity> getFaqList() {
        return faqRepository.findByShowYnOrderByCreatedAtAsc(true);
    }
}
