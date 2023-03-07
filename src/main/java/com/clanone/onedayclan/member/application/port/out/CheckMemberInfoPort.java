package com.clanone.onedayclan.member.application.port.out;

public interface CheckMemberInfoPort {
    boolean checkPassword(String userId, String password);
}
