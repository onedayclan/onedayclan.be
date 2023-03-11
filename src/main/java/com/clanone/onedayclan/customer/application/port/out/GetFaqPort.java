package com.clanone.onedayclan.customer.application.port.out;

import com.clanone.onedayclan.customer.adapter.in.web.response.AdminFaqResponse;
import com.clanone.onedayclan.customer.adapter.out.model.FaqSearchModel;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.FaqEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GetFaqPort {
    List<FaqEntity> getFaqList();
    Page<AdminFaqResponse> getFaqListForAdmin(FaqSearchModel model, Pageable pageable);
    FaqEntity getFaq(long faqSeq);
}
