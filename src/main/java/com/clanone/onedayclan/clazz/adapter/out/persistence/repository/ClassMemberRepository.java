package com.clanone.onedayclan.clazz.adapter.out.persistence.repository;

import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassMemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassMemberRepository extends JpaRepository<ClassMemberEntity, Long>, ClassMemberCustomRepository {
    Optional<ClassMemberEntity> findByClazzSeqAndMemberSeq(long classSeq, long memberSeq);
    Page<ClassMemberEntity> findByClazzSeq(long classSeq, Pageable pageable);
}
