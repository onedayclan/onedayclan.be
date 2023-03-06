package com.clanone.onedayclan.member.adapter.in.web.controller;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.OnedayclanResponse.PagingResult;
import com.clanone.onedayclan.member.adapter.in.web.request.MemberSearchRequest;
import com.clanone.onedayclan.member.adapter.in.web.response.MemberSearchResponse;
import com.clanone.onedayclan.member.application.port.in.FindMemberPort;
import com.clanone.onedayclan.member.domain.enums.MemberStatusType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


@RestController
@RequestMapping(value = "/admin/member")
@AllArgsConstructor
public class AdminMemberController {

    private final FindMemberPort findMemberPort;

    @GetMapping("/normal")
    public ResponseEntity<OnedayclanResponse<PagingResult<MemberSearchResponse>>> getNormalMemberList(@RequestParam(required = false) String userId,
                                                                                                     @RequestParam(required = false) String name,
                                                                                                     @RequestParam(required = false) MemberStatusType status,
                                                                                                     @RequestParam(required = false) String createdStartAt,
                                                                                                     @RequestParam(required = false) String createdEndAt,
                                                                                                     @RequestParam(defaultValue = "0") long organizationSeq,
                                                                                                     @RequestParam(defaultValue = "0") int pageNo,
                                                                                                     @RequestParam(defaultValue = "10") int pageSize) {
        Page<MemberSearchResponse> result = findMemberPort.searchMemberList(MemberSearchRequest.builder()
                .userId(userId)
                .name(name)
                .status(status)
                .searchStartAt(Objects.isNull(createdStartAt) ? null : LocalDateTime.parse(createdStartAt, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .searchEndAt(Objects.isNull(createdEndAt) ? null : LocalDateTime.parse(createdEndAt, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .organizationSeq(organizationSeq)
                .build(), PageRequest.of(pageNo, pageSize));
        return ResponseEntity.ok(OnedayclanResponse.of(result.getContent(), pageNo, result.getTotalElements()));
    }
}
