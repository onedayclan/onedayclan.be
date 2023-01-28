package com.clanone.onedayclan.member.application.port.out;

import java.time.LocalDateTime;

public interface SendAuthorizationNumberPort {
    void saveAuthorizationNumber(String phoneNumber, String authorizationNumber, LocalDateTime validAt);
}
