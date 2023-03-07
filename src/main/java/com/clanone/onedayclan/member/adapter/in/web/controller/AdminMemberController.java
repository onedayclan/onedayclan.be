package com.clanone.onedayclan.member.adapter.in.web.controller;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.OnedayclanResponse.PagingResult;
import com.clanone.onedayclan.common.application.service.utils.DateUtil;
import com.clanone.onedayclan.member.adapter.in.web.request.MemberSearchRequest;
import com.clanone.onedayclan.member.adapter.in.web.response.MemberDetailResponse;
import com.clanone.onedayclan.member.adapter.in.web.response.MemberSearchResponse;
import com.clanone.onedayclan.member.application.port.in.FindMemberPort;
import com.clanone.onedayclan.member.domain.enums.MemberStatusType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                                                                                                     @RequestParam(defaultValue = "1") int pageNo,
                                                                                                     @RequestParam(defaultValue = "10") int pageSize) {
        Page<MemberSearchResponse> result = findMemberPort.searchMemberList(MemberSearchRequest.builder()
                .userId(userId)
                .name(name)
                .status(status)
                .searchStartAt(Objects.isNull(createdStartAt) ? null : DateUtil.parseLocalDateTimeByYYYYMMDD(createdStartAt))
                .searchEndAt(Objects.isNull(createdEndAt) ? null : DateUtil.parseLocalDateTimeByYYYYMMDD(createdEndAt))
                .organizationSeq(organizationSeq)
                .build(), PageRequest.of(pageNo-1, pageSize));
        return ResponseEntity.ok(OnedayclanResponse.of(result.getContent(), pageNo, result.getTotalElements()));
    }

    @GetMapping("/normal/{memberSeq}")
    public ResponseEntity<OnedayclanResponse<MemberDetailResponse>> getNormalMember(@PathVariable long memberSeq) {
        return ResponseEntity.ok(OnedayclanResponse.of(findMemberPort.findMember(memberSeq)));
    }

    @GetMapping("/organization")
    public ResponseEntity<OnedayclanResponse<PagingResult<MemberSearchResponse>>> getOrganizationMemberList(@RequestParam(required = false) String userId,
                                                                                                       @RequestParam(required = false) String name,
                                                                                                       @RequestParam(required = false) MemberStatusType status,
                                                                                                       @RequestParam(required = false) String createdStartAt,
                                                                                                       @RequestParam(required = false) String createdEndAt,
                                                                                                       @RequestParam(defaultValue = "1") int pageNo,
                                                                                                       @RequestParam(defaultValue = "10") int pageSize) {
        Page<MemberSearchResponse> result = findMemberPort.searchOrganizationMemberList(MemberSearchRequest.builder()
                .userId(userId)
                .name(name)
                .status(status)
                .searchStartAt(Objects.isNull(createdStartAt) ? null : DateUtil.parseLocalDateTimeByYYYYMMDD(createdStartAt))
                .searchEndAt(Objects.isNull(createdEndAt) ? null : DateUtil.parseLocalDateTimeByYYYYMMDD(createdEndAt))
                .build(), PageRequest.of(pageNo-1, pageSize));
        return ResponseEntity.ok(OnedayclanResponse.of(result.getContent(), pageNo, result.getTotalElements()));
    }
}
