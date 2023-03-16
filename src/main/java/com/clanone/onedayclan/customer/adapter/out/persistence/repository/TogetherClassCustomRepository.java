package com.clanone.onedayclan.customer.adapter.out.persistence.repository;

import com.clanone.onedayclan.customer.adapter.in.web.response.AdminTogetherClassDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminTogetherClassResponse;
import com.clanone.onedayclan.customer.adapter.out.model.TogetherClassSearchModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TogetherClassCustomRepository {
    Page<AdminTogetherClassResponse> getTogetherListForAdmin(TogetherClassSearchModel model, Pageable pageable);
    AdminTogetherClassDetailResponse getTogetherForAdmin(long togetherClassSeq);
}
