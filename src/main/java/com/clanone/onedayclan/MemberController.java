package com.clanone.onedayclan;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/member")
    public ResponseEntity<OnedayclanResponse<Member>> getMember(){
        return ResponseEntity.ok(OnedayclanResponse.of(memberService.getMember()));
    }

    @PostMapping("/member")
    public ResponseEntity<OnedayclanResponse<Void>> saveMember(){
        memberService.saveMember();
        return ResponseEntity.ok(OnedayclanResponse.success());
    }

    @GetMapping("/member/list")
    public ResponseEntity<OnedayclanResponse<List<Member>>> getMemberList(){
        return ResponseEntity.ok(OnedayclanResponse.of(memberService.getMemberList()));
    }

    @GetMapping("/member/page/list")
    public ResponseEntity<OnedayclanResponse<OnedayclanResponse.PagingResult<Member>>> getMemberListPaging(){
        Page<Member> memberList = memberService.getMemberListPaging();
        return ResponseEntity.ok(OnedayclanResponse.of(memberList.getContent(), 1, memberList.getTotalElements()));
    }

    @GetMapping("/member/notfoundexcdeption")
    public ResponseEntity<OnedayclanResponse<Member>> throwException(){
        return ResponseEntity.ok(OnedayclanResponse.of(memberService.throwNotFoundException()));
    }

    @GetMapping("/member/badrequestexception")
    public ResponseEntity<OnedayclanResponse<Member>> throwException2(){
        return ResponseEntity.ok(OnedayclanResponse.of(memberService.throwBadRequestException()));
    }
}
