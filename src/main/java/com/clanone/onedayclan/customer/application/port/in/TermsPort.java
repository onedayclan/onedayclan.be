package com.clanone.onedayclan.customer.application.port.in;

import com.clanone.onedayclan.customer.adapter.in.web.response.AdminTermsDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminTermsResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.TermsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TermsPort {
    List<TermsResponse> getTerms();
    Page<AdminTermsResponse> getTermsList(Pageable pageable);
    AdminTermsDetailResponse getTermsForAdmin(long termsSeq);
}
