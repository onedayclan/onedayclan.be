package com.clanone.onedayclan.customer.adapter.in.web.controller;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.OnedayclanResponse.PagingResult;
import com.clanone.onedayclan.common.application.service.utils.DateUtil;
import com.clanone.onedayclan.customer.adapter.in.web.request.InquiryAnswerCreateRequest;
import com.clanone.onedayclan.customer.adapter.in.web.request.InquirySearchRequest;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminInquiryDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminInquiryResponse;
import com.clanone.onedayclan.customer.application.port.in.InquiryPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/admin/inquiry")
@RequiredArgsConstructor
public class AdminInquiryController {

    private final InquiryPort inquiryPort;

    @GetMapping("")
    public ResponseEntity<OnedayclanResponse<PagingResult<AdminInquiryResponse>>> getInquiryList(@RequestParam(required = false) String name,
                                                                                                 @RequestParam(required = false) String userId,
                                                                                                 @RequestParam(required = false) String searchStartAt,
                                                                                                 @RequestParam(required = false) String searchEndAt,
                                                                                                 @RequestParam(required = false) Boolean answerYn,
                                                                                                 @RequestParam(required = false) Boolean deleteYn,
                                                                                                 @RequestParam(defaultValue = "1") int pageNo,
                                                                                                 @RequestParam(defaultValue = "10") int pageSize) {
        Page<AdminInquiryResponse> inquiryList = inquiryPort.getInquiryListForAdmin(InquirySearchRequest.builder()
                .name(name)
                .userId(userId)
                .searchStartAt(Objects.isNull(searchStartAt) ? null : DateUtil.parseLocalDateTimeByYYYYMMDD(searchStartAt))
                .searchEndAt(Objects.isNull(searchEndAt) ? null : DateUtil.parseLocalDateTimeByYYYYMMDD(searchEndAt))
                .answerYn(answerYn)
                .deleteYn(deleteYn)
                .build(), PageRequest.of(pageNo-1, pageSize));
        return ResponseEntity.ok(OnedayclanResponse.of(inquiryList.getContent(), pageNo, inquiryList.getTotalElements()));
    }

    @GetMapping("/{inquirySeq}")
    public ResponseEntity<OnedayclanResponse<AdminInquiryDetailResponse>> getInquiry(@PathVariable long inquirySeq) {
        return ResponseEntity.ok(OnedayclanResponse.of(inquiryPort.getInquiryForAdmin(inquirySeq)));
    }

    @PostMapping("/{inquirySeq}/answer")
    public ResponseEntity<OnedayclanResponse<Void>> insertInquiryAnswer(@PathVariable long inquirySeq, @Valid @RequestBody InquiryAnswerCreateRequest request) {
        inquiryPort.insertInquiryAnswer(inquirySeq, request);
        return ResponseEntity.ok(OnedayclanResponse.success());
    }

    @DeleteMapping("/{inquirySeq}/answer")
    public ResponseEntity<OnedayclanResponse<Void>> deleteInquiryAnswer(@PathVariable long inquirySeq) {
        inquiryPort.deleteInquiryAnswer(inquirySeq);
        return ResponseEntity.ok(OnedayclanResponse.success());
    }
}

