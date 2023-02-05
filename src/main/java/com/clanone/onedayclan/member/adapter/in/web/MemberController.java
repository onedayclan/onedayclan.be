package com.clanone.onedayclan.member.adapter.in.web;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.member.application.port.in.FindMemberPort;
import com.clanone.onedayclan.member.application.port.in.JoinMemberPort;
import com.clanone.onedayclan.member.application.port.in.LoginMemberPort;
import com.clanone.onedayclan.member.domain.Member;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
public class MemberController {

    private final JoinMemberPort joinMemberPort;
    private final LoginMemberPort loginMemberPort;
    private final FindMemberPort findMemberPort;

    @PostMapping("/auth/login")
    public ResponseEntity<OnedayclanResponse<TokenResponse>> login(@Valid @RequestBody MemberLoginRequest memberLoginRequest) {
        return ResponseEntity.ok(OnedayclanResponse.of(loginMemberPort.login(memberLoginRequest.getId(), memberLoginRequest.getPassword())));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<OnedayclanResponse<TokenResponse>> refresh(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(OnedayclanResponse.of(loginMemberPort.refresh(refreshTokenRequest.getRefreshToken())));
    }

    @PostMapping("/auth/join")
    public ResponseEntity<OnedayclanResponse<Void>> joinMember(@Valid @RequestBody MemberJoinRequest memberJoinRequest) {
        joinMemberPort.joinMember(new Member(memberJoinRequest.getId(),
                memberJoinRequest.getName(),
                memberJoinRequest.getPassword(),
                memberJoinRequest.getOrganization(),
                memberJoinRequest.getPhone()));
        return ResponseEntity.ok(OnedayclanResponse.success());
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<OnedayclanResponse<Void>> logout(@Valid @RequestBody LogoutRequest logoutRequest) {
        loginMemberPort.logout(logoutRequest.getAccessToken());
        return ResponseEntity.ok(OnedayclanResponse.success());
    }

    @GetMapping("/get")
    public ResponseEntity<OnedayclanResponse<Void>> get() {
        return ResponseEntity.ok(OnedayclanResponse.success());
    }

    @PostMapping("/auth/find/id")
    public ResponseEntity<OnedayclanResponse<MemberFindResponse>> findId(@Valid @RequestBody FindIdRequest findIdRequest) {
        return ResponseEntity.ok(OnedayclanResponse.of(findMemberPort.findId(findIdRequest)));
    }

    @GetMapping("/auth/email/check")
    public ResponseEntity<OnedayclanResponse<EmailCheckResponse>> checkEmail(@RequestParam String email) {
        return ResponseEntity.ok(OnedayclanResponse.of(joinMemberPort.checkAvailableEmail(email)));
    }
}
