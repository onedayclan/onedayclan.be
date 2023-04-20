package com.clanone.onedayclan.clazz.application.port.out;

import com.clanone.onedayclan.clazz.adapter.in.web.response.CancelClassMessageResponse;
import com.clanone.onedayclan.clazz.application.model.ScheduledClassModel;

import java.util.List;

public interface GetClassMemberPort {
    Long getClassApplicationPeople(long classSeq);
    boolean existsClassMember(long memberSeq, long classSeq);
    List<CancelClassMessageResponse> getClassMemberByMemberSeqByCanceledTrue(long memberSeq);
    List<ScheduledClassModel> getScheduledClassModel(String userId);
}
