package com.clanone.onedayclan.member.application.port.out;

import com.clanone.onedayclan.member.adapter.out.persistence.AuthorizationNumberEntity;

import java.time.LocalDateTime;

public interface ConfirmAuthorizationNumberPort {
    AuthorizationNumberEntity getAuthorizationNumber(String phoneNumber, String authorizationNumber, LocalDateTime now);
}
