package com.clanone.onedayclan.member.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberEntityRepository extends JpaRepository<MemberEntity, Long> {
}
