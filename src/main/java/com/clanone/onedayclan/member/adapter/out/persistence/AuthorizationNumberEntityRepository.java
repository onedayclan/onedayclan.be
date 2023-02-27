package com.clanone.onedayclan.member.adapter.out.persistence;

import com.clanone.onedayclan.member.adapter.out.persistence.entity.AuthorizationNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AuthorizationNumberEntityRepository extends JpaRepository<AuthorizationNumberEntity, Long> {
    AuthorizationNumberEntity findByPhoneNumberAndAuthorizationNumberAndValidAtAfterAndUsedYn(String phoneNumber, String authorizationNumber, LocalDateTime now, boolean usedYn);

}
