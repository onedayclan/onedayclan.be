package com.clanone.onedayclan.member.adapter.out.persistence;

import com.clanone.onedayclan.member.application.port.out.ConfirmAuthorizationNumberPort;
import com.clanone.onedayclan.member.application.port.out.SendAuthorizationNumberPort;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@AllArgsConstructor
@Component
public class AuthorizationNumberAdapter implements ConfirmAuthorizationNumberPort, SendAuthorizationNumberPort {

    private final AuthorizationNumberEntityRepository authorizationNumberEntityRepository;

    @Override
    public AuthorizationNumberEntity getAuthorizationNumber(String phoneNumber, String authorizationNumber, LocalDateTime now) {
        return authorizationNumberEntityRepository.findByPhoneNumberAndAuthorizationNumberAndValidAtAfterAndUsedYn(phoneNumber, authorizationNumber, now, false);
    }

    @Override
    public void saveAuthorizationNumber(String phoneNumber, String authorizationNumber, LocalDateTime validAt) {
        AuthorizationNumberEntity authorizationNumberEntity = AuthorizationNumberEntity.builder()
                .authorizationNumber(authorizationNumber)
                .phoneNumber(phoneNumber)
                .validAt(validAt)
                .build();
        authorizationNumberEntityRepository.save(authorizationNumberEntity);
    }
}
