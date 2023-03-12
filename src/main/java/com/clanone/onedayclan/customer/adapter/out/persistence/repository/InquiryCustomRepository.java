package com.clanone.onedayclan.customer.adapter.out.persistence.repository;

import com.clanone.onedayclan.customer.adapter.in.web.response.AdminInquiryResponse;
import com.clanone.onedayclan.customer.adapter.out.model.InquirySearchModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InquiryCustomRepository {
    Page<AdminInquiryResponse> getInquiryListForAdmin(InquirySearchModel model, Pageable pageable);
}
