package com.clanone.onedayclan.clazz.application.port.out;

import com.clanone.onedayclan.clazz.adapter.in.web.response.ApplyClassResponse;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassMemberEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassTagEntity;

import java.util.List;

public interface ManageClassPort {
    ClassEntity insertClass(ClassEntity classEntity);
    List<ClassTagEntity> insertClassTagList(List<ClassTagEntity> classTagList);
    void deleteClassTagList(long classSeq);
    ClassMemberEntity applyClass(ClassMemberEntity classMemberEntity);
}
