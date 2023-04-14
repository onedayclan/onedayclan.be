package com.clanone.onedayclan.clazz.application.port.out;

import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassCategoryEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassEntity;

import java.util.List;

public interface GetClassPort {
    ClassCategoryEntity getClassCategory(long categorySeq);
    List<ClassEntity> getFiveLatestClass();
}
