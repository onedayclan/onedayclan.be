package com.clanone.onedayclan.clazz.adapter.out.persistence.repository;

import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassTagRepository extends JpaRepository<ClassTagEntity, Long> {
    List<ClassTagEntity> findByClazzSeq(long classSeq);
    void deleteAllByClazzSeq(long classSeq);
}
