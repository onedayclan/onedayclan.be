package com.clanone.onedayclan.customer.application.port.in;

import com.clanone.onedayclan.customer.adapter.in.web.request.FaqManageRequest;
import com.clanone.onedayclan.customer.adapter.in.web.request.FaqSearchRequest;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminFaqDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminFaqResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.FaqResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FaqPort {
    List<FaqResponse> getFaqList();
    Page<AdminFaqResponse> getFaqListForAdmin(FaqSearchRequest request, Pageable pageable);
    AdminFaqDetailResponse getFaq(long faqSeq);
    AdminFaqDetailResponse insertFaq(FaqManageRequest request);
    AdminFaqDetailResponse updateFaq(FaqManageRequest request, long faqSeq);
    void deleteFaq(long faqSeq);
}
