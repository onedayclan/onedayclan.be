package com.clanone.onedayclan.customer.adapter.out.persistence;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.TermsEntity;
import com.clanone.onedayclan.customer.adapter.out.persistence.repository.TermRepository;
import com.clanone.onedayclan.customer.application.port.out.GetTermPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TermAdapter implements GetTermPort {

    private final TermRepository termRepository;

    @Override
    public List<TermsEntity> getAllTermList() {
        return termRepository.findAllByOrderByTypeAscCreatedAtDesc();
    }
}
