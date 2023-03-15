package com.clanone.onedayclan.customer.adapter.in.web.controller;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.OnedayclanResponse.PagingResult;
import com.clanone.onedayclan.common.application.service.utils.DateUtil;
import com.clanone.onedayclan.customer.adapter.in.web.request.TogetherClassAnswerCreateRequest;
import com.clanone.onedayclan.customer.adapter.in.web.request.TogetherClassSearchRequest;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminTogetherClassDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminTogetherClassResponse;
import com.clanone.onedayclan.customer.application.port.in.TogetherClassPort;
import com.clanone.onedayclan.customer.domain.enums.TogetherClassCategory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/admin/together/class")
@RequiredArgsConstructor
public class AdminTogetherClassController {

    private final TogetherClassPort togetherClassPort;

    @GetMapping("")
    public ResponseEntity<OnedayclanResponse<PagingResult<AdminTogetherClassResponse>>> getTogetherList(@RequestParam(required = false) String name,
                                                                                                        @RequestParam(required = false) String userId,
                                                                                                        @RequestParam(required = false) TogetherClassCategory category,
                                                                                                        @RequestParam(required = false) Boolean answerYn,
                                                                                                        @RequestParam(required = false) Long organizationSeq,
                                                                                                        @RequestParam(required = false) String searchStartAt,
                                                                                                        @RequestParam(required = false) String searchEndAt,
                                                                                                        @RequestParam(defaultValue = "1") int pageNo,
                                                                                                        @RequestParam(defaultValue = "10") int pageSize) {
        Page<AdminTogetherClassResponse> togetherClassList = togetherClassPort.getTogetherClassListForAdmin(TogetherClassSearchRequest.builder()
                .name(name)
                .userId(userId)
                .category(category)
                .answerYn(answerYn)
                .organizationSeq(organizationSeq)
                .searchStartAt(Objects.isNull(searchStartAt) ? null : DateUtil.parseLocalDateTimeByYYYYMMDD(searchStartAt))
                .searchEndAt(Objects.isNull(searchEndAt) ? null : DateUtil.parseLocalDateTimeByYYYYMMDD(searchEndAt))
                .build(), PageRequest.of(pageNo-1, pageSize));
        return ResponseEntity.ok(OnedayclanResponse.of(togetherClassList.getContent(), pageNo, togetherClassList.getTotalElements()));
    }

    @GetMapping("/{togetherClassSeq}")
    public ResponseEntity<OnedayclanResponse<AdminTogetherClassDetailResponse>> getTogetherClass(@PathVariable long togetherClassSeq) {
        return ResponseEntity.ok(OnedayclanResponse.of(togetherClassPort.getTogetherClassForAdmin(togetherClassSeq)));
    }

    @PatchMapping("/{togetherClassSeq}/answer/apply")
    public ResponseEntity<OnedayclanResponse<Void>> applyTogetherClassAnswer(@PathVariable long togetherClassSeq, @Valid @RequestBody TogetherClassAnswerCreateRequest request) {
        togetherClassPort.applyTogetherClassAnswer(togetherClassSeq, request);
        return ResponseEntity.ok(OnedayclanResponse.success());
    }

    @PatchMapping("/{togetherClassSeq}/answer/delete")
    public ResponseEntity<OnedayclanResponse<Void>> deleteTogetherClassAnswer(@PathVariable long togetherClassSeq) {
        togetherClassPort.deleteTogetherClassAnswer(togetherClassSeq);
        return ResponseEntity.ok(OnedayclanResponse.success());
    }
}
