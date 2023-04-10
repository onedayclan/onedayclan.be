package com.clanone.onedayclan.clazz.application.port.out;

import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassCategoryEntity;

public interface GetClassPort {
    ClassCategoryEntity getClassCategory(long categorySeq);
}
