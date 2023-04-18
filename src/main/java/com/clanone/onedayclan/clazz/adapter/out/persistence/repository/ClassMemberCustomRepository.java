package com.clanone.onedayclan.clazz.adapter.out.persistence.repository;

import com.clanone.onedayclan.clazz.adapter.in.web.response.AdminClassMemberListResponse;
import com.clanone.onedayclan.clazz.adapter.out.persistence.model.ClassMemberSearchModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClassMemberCustomRepository {
    Page<AdminClassMemberListResponse> searchClassMemberList(ClassMemberSearchModel optionModel, Pageable pageable);
}
