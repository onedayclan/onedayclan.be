package com.clanone.onedayclan.clazz.adapter.out.persistence.adapter;

import com.clanone.onedayclan.clazz.adapter.in.web.response.CancelClassMessageResponse;
import com.clanone.onedayclan.clazz.adapter.out.persistence.repository.ClassMemberRepository;
import com.clanone.onedayclan.clazz.application.model.ScheduledClassDetailModel;
import com.clanone.onedayclan.clazz.application.model.ScheduledClassModel;
import com.clanone.onedayclan.clazz.application.port.out.GetClassMemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClassMemberAdapter implements GetClassMemberPort {

    private final ClassMemberRepository classMemberRepository;

    @Override
    public Long getClassApplicationPeople(long classSeq) {
        return classMemberRepository.countByClazzSeq(classSeq);
    }

    @Override
    public boolean existsClassMember(long memberSeq, long classSeq) {
        return classMemberRepository.existsByMemberSeqAndClazzSeq(memberSeq, classSeq);
    }

    @Override
    public List<CancelClassMessageResponse> getClassMemberByMemberSeqByCanceledTrue(long memberSeq) {
        return classMemberRepository.getCancelClassMessage(memberSeq);
    }

    @Override
    public List<ScheduledClassModel> getScheduledClassModel(String userId) {
        return classMemberRepository.getScheduledClassModel(userId);
    }

    @Override
    public ScheduledClassDetailModel getScheduledClassDetailModel(String userId, long classSeq) {
        return classMemberRepository.getScheduledClassDetailModel(userId, classSeq);
    }
}
