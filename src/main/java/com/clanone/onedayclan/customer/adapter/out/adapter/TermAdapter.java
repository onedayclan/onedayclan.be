package com.clanone.onedayclan.customer.adapter.out.adapter;

import com.clanone.onedayclan.customer.adapter.in.web.response.AdminTermsResponse;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.TermsEntity;
import com.clanone.onedayclan.customer.adapter.out.persistence.repository.TermsRepository;
import com.clanone.onedayclan.customer.application.exception.TermsNotFoundException;
import com.clanone.onedayclan.customer.application.port.out.GetTermsPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TermAdapter implements GetTermsPort {

    private final TermsRepository termsRepository;

    @Override
    public List<TermsEntity> getAllTermsList() {
        return termsRepository.findAllByOrderByTypeAscCreatedAtDesc();
    }

    @Override
    public Page<AdminTermsResponse> getTermsList(Pageable pageable) {
        Page<TermsEntity> terms = termsRepository.findAllByOrderByCreatedAtDesc(pageable);
        return new PageImpl<>(terms.getContent().stream().map(AdminTermsResponse::of).collect(Collectors.toList()), pageable, terms.getTotalElements());
    }

    @Override
    public TermsEntity getTerms(long termsSeq) {
        return termsRepository.findById(termsSeq).orElseThrow(() -> {throw new TermsNotFoundException();});
    }
}
