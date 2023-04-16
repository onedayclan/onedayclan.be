package com.clanone.onedayclan.clazz.application.port.out;

import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassCategoryEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassMemberEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassTagEntity;

import java.util.List;

public interface GetClassPort {
    ClassCategoryEntity getClassCategory(long categorySeq);
    ClassEntity getClass(long classSeq);
    List<ClassTagEntity> getClassTagList(long classSeq);
    ClassMemberEntity getClassMember(long classSeq, long memberSeq);
    List<ClassEntity> getFiveLatestClass();
}
