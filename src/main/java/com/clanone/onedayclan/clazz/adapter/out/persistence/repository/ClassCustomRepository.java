package com.clanone.onedayclan.clazz.adapter.out.persistence.repository;

import com.clanone.onedayclan.clazz.adapter.in.web.response.AdminClassResponse;
import com.clanone.onedayclan.clazz.adapter.out.persistence.model.ClassSearchModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClassCustomRepository {
    Page<AdminClassResponse> searchMemberList(ClassSearchModel optionModel, Pageable pageable);
}
