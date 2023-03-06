package com.clanone.onedayclan.member.adapter.out.persistence.repository;

import com.clanone.onedayclan.member.adapter.out.persistence.entity.FindPasswordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface FindPasswordEntityRepository extends JpaRepository<FindPasswordEntity, Long> {
    Optional<FindPasswordEntity> findByAuthorizationCode(String authorizationCode);
}
