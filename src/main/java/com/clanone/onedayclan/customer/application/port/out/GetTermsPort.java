package com.clanone.onedayclan.customer.application.port.out;

import com.clanone.onedayclan.customer.adapter.in.web.response.AdminTermsResponse;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.TermsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GetTermsPort {
    List<TermsEntity> getAllTermsList();
    Page<AdminTermsResponse> getTermsList(Pageable pageable);
    TermsEntity getTerms(long termsSeq);
}
