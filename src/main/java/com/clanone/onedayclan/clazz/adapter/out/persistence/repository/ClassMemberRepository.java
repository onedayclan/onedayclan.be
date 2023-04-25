package com.clanone.onedayclan.clazz.adapter.out.persistence.repository;

import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassMemberEntity;
import com.clanone.onedayclan.clazz.domain.enums.AttendanceCheck;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassMemberRepository extends JpaRepository<ClassMemberEntity, Long>, ClassMemberCustomRepository {
    Optional<ClassMemberEntity> findByClazzSeqAndMemberSeq(long classSeq, long memberSeq);
    Page<ClassMemberEntity> findByClazzSeq(long classSeq, Pageable pageable);
    Long countByClazzSeq(long classSeq);
    boolean existsByMemberSeqAndClazzSeq(long memberSeq, long clazzSeq);
    boolean existsByMemberUserIdAndClazzSeqAndAttendanceCheck(String userId, long classSeq, AttendanceCheck attendanceCheck);
}
