package com.clanone.onedayclan.member.application.port.out;

public interface CheckEmailPort {
    boolean existsEmail(String email);
}
