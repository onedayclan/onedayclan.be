package com.clanone.onedayclan.member.adapter.in.web;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.member.application.port.in.JoinMemberPort;
import com.clanone.onedayclan.member.domain.Member;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MemberController {

    private final JoinMemberPort joinMemberPort;

    @PostMapping("/member/join")
    public ResponseEntity<OnedayclanResponse<Void>> joinMember(@RequestBody MemberJoinRequest memberJoinRequest) {
        joinMemberPort.joinMember(new Member(memberJoinRequest.getId(),
                memberJoinRequest.getName(),
                memberJoinRequest.getPassword(),
                memberJoinRequest.getEmail(),
                memberJoinRequest.getPhone()));
        return ResponseEntity.ok(OnedayclanResponse.success());
    }
}
