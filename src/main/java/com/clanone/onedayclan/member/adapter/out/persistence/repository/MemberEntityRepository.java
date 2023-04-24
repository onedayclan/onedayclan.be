package com.clanone.onedayclan.member.adapter.out.persistence.repository;

import com.clanone.onedayclan.member.adapter.out.persistence.entity.MemberEntity;
import com.clanone.onedayclan.member.domain.enums.MemberOrganizationStatus;
import com.clanone.onedayclan.member.domain.enums.MemberStatusType;
import com.clanone.onedayclan.member.domain.enums.MemberType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberEntityRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByUserId(String id);
    Optional<MemberEntity> findByNameAndPhone(String name, String phone);
    boolean existsByUserId(String id);
    boolean existsByUserIdAndPassword(String userId, String password);
    long countByConfirmOrganizationSeqAndType(long organizationSeq, MemberType type);
    Page<MemberEntity> findByOrganizationStatus(MemberOrganizationStatus status, Pageable pageable);
    Optional<MemberEntity> findByUserIdAndNameAndPhone(String userId, String name, String phone);
}
