package com.clanone.onedayclan.clazz.adapter.out.persistence.adapter;

import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassCategoryEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.repository.ClassCategoryRepository;
import com.clanone.onedayclan.clazz.adapter.out.persistence.repository.ClassRepository;
import com.clanone.onedayclan.clazz.application.port.out.GetClassPort;
import com.clanone.onedayclan.clazz.application.port.out.ManageClassPort;
import com.clanone.onedayclan.clazz.application.service.exception.ClassCategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClassAdapter implements ManageClassPort, GetClassPort {

    private final ClassRepository classRepository;
    private final ClassCategoryRepository classCategoryRepository;

    @Override
    public ClassEntity insertClass(ClassEntity classEntity) {
        return classRepository.save(classEntity);
    }

    @Override
    public ClassCategoryEntity getClassCategory(long categorySeq) {
        return classCategoryRepository.findById(categorySeq).orElseThrow(() -> {throw new ClassCategoryNotFoundException();});
    }
}
