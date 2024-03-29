package com.clanone.onedayclan.customer.application.port.out;

import com.clanone.onedayclan.customer.adapter.in.web.response.AdminTogetherClassDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminTogetherClassResponse;
import com.clanone.onedayclan.customer.adapter.out.model.TogetherClassSearchModel;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.TogetherClassEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GetTogetherClassPort {
    List<TogetherClassEntity> getTogetherClassList(String userId);
    TogetherClassEntity getTogetherClassDetail(String userId, long seq);
    Page<AdminTogetherClassResponse> getTogetherListForAdmin(TogetherClassSearchModel model, Pageable pageable);
    AdminTogetherClassDetailResponse getTogetherForAdmin(long togetherClassSeq);
    TogetherClassEntity getTogetherClassById(long togetherClassSeq);
}
