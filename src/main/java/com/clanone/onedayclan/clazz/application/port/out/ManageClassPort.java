package com.clanone.onedayclan.clazz.application.port.out;

import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassEntity;

public interface ManageClassPort {
    ClassEntity insertClass(ClassEntity classEntity);
}
