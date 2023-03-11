package com.clanone.onedayclan.customer.adapter.out.persistence.repository;

import com.clanone.onedayclan.customer.adapter.out.model.FaqSearchModel;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.FaqEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FaqCustomRepository {
    Page<FaqEntity> getFaqListForAdmin(FaqSearchModel model, Pageable pageable);
}
