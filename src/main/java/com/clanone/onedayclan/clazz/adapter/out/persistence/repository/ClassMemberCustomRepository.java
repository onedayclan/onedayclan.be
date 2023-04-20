package com.clanone.onedayclan.clazz.adapter.out.persistence.repository;

import com.clanone.onedayclan.clazz.adapter.in.web.response.AdminClassMemberListResponse;
import com.clanone.onedayclan.clazz.adapter.in.web.response.CancelClassMessageResponse;
import com.clanone.onedayclan.clazz.adapter.out.persistence.model.ClassMemberSearchModel;
import com.clanone.onedayclan.clazz.application.model.ScheduledClassModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClassMemberCustomRepository {
    Page<AdminClassMemberListResponse> searchClassMemberList(ClassMemberSearchModel optionModel, Pageable pageable);
    List<CancelClassMessageResponse> getCancelClassMessage(long memberSeq);
    List<ScheduledClassModel> getScheduledClassModel(String userId);

}
