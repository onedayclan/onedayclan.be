package com.clanone.onedayclan.member.adapter.in.web.controller;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.common.resolver.LoginUserId;
import com.clanone.onedayclan.member.adapter.in.web.request.*;
import com.clanone.onedayclan.member.adapter.in.web.response.*;
import com.clanone.onedayclan.member.application.port.in.FindMemberPort;
import com.clanone.onedayclan.member.application.port.in.JoinMemberPort;
import com.clanone.onedayclan.member.application.port.in.LoginMemberPort;
import com.clanone.onedayclan.member.application.port.in.PasswordPort;
import com.clanone.onedayclan.member.domain.Member;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class MemberController {

    private final JoinMemberPort joinMemberPort;
    private final LoginMemberPort loginMemberPort;
    private final FindMemberPort findMemberPort;
    private final PasswordPort passwordPort;

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
                memberJoinRequest.getOrganizationSeq(),
                memberJoinRequest.getPhone()));
        return ResponseEntity.ok(OnedayclanResponse.success());
    }

    @GetMapping("/auth/organization")
    public ResponseEntity<OnedayclanResponse<List<OrganizationResponse>>> organizationList() {
        return ResponseEntity.ok(OnedayclanResponse.of(joinMemberPort.getOrganizationList()));
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<OnedayclanResponse<Void>> logout(@Valid @RequestBody LogoutRequest logoutRequest) {
        loginMemberPort.logout(logoutRequest.getAccessToken());
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

    @PostMapping("/auth/password/reset")
    public ResponseEntity<OnedayclanResponse<Void>> resetPassword(@Valid @RequestBody PasswordResetRequest passwordResetRequest) {
        passwordPort.resetPassword(passwordResetRequest);
        return ResponseEntity.ok(OnedayclanResponse.success());
    }

    @GetMapping("/user/info")
    public ResponseEntity<OnedayclanResponse<MemberInfoResponse>> memberInfo(@LoginUserId String userId){
        return ResponseEntity.ok(OnedayclanResponse.of(findMemberPort.getMemberInfo(userId)));
    }
}
