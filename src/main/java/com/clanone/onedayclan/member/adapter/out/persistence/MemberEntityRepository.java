package com.clanone.onedayclan.member.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberEntityRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByUserId(String id);

}
