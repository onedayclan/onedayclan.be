package com.clanone.onedayclan.member.application.port.in;

import com.clanone.onedayclan.member.adapter.in.web.request.PasswordResetRequest;

public interface PasswordPort {
    void resetPassword(PasswordResetRequest passwordResetRequest);
}
