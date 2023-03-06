package com.clanone.onedayclan.member.application.port.out;

public interface UpdateMemberInfoPort {
    void resetPassword(String email, String password);
}
