package com.clanone.onedayclan.customer.adapter.in.web.controller;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.OnedayclanResponse.PagingResult;
import com.clanone.onedayclan.common.application.service.utils.DateUtil;
import com.clanone.onedayclan.customer.adapter.in.web.request.FaqManageRequest;
import com.clanone.onedayclan.customer.adapter.in.web.request.FaqSearchRequest;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminFaqDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminFaqResponse;
import com.clanone.onedayclan.customer.application.port.in.FaqPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(value = "/admin/faq")
@RequiredArgsConstructor
public class AdminFaqController {

    private final FaqPort faqPort;

    @GetMapping("")
    public ResponseEntity<OnedayclanResponse<PagingResult<AdminFaqResponse>>> getFaqList(@RequestParam(required = false) String title,
                                                                                         @RequestParam(required = false) String searchStartAt,
                                                                                         @RequestParam(required = false) String searchEndAt,
                                                                                         @RequestParam(defaultValue = "1") int pageNo,
                                                                                         @RequestParam(defaultValue = "10") int pageSize) {
        Page<AdminFaqResponse> faq = faqPort.getFaqListForAdmin(FaqSearchRequest.builder()
                .title(title)
                .searchStartAt(Objects.isNull(searchStartAt) ? null : DateUtil.parseLocalDateTimeByYYYYMMDD(searchStartAt))
                .searchEndAt(Objects.isNull(searchEndAt) ? null : DateUtil.parseLocalDateTimeByYYYYMMDD(searchEndAt))
                .build(), PageRequest.of(pageNo-1, pageSize));
        return ResponseEntity.ok(OnedayclanResponse.of(faq.getContent(), pageNo, faq.getTotalElements()));
    }

    @GetMapping("/{faqSeq}")
    public ResponseEntity<OnedayclanResponse<AdminFaqDetailResponse>> getFaq(@PathVariable long faqSeq) {
        return ResponseEntity.ok(OnedayclanResponse.of(faqPort.getFaq(faqSeq)));
    }

    @PostMapping("")
    public ResponseEntity<OnedayclanResponse<AdminFaqDetailResponse>> insertFaq(@Valid @RequestBody FaqManageRequest request) {
        return ResponseEntity.ok(OnedayclanResponse.of(faqPort.insertFaq(request)));
    }

    @PutMapping("/{faqSeq}")
    public ResponseEntity<OnedayclanResponse<AdminFaqDetailResponse>> updateFaq(@PathVariable long faqSeq, @Valid @RequestBody FaqManageRequest request) {
        return ResponseEntity.ok(OnedayclanResponse.of(faqPort.updateFaq(request, faqSeq)));
    }

    @DeleteMapping("/{faqSeq}")
    public ResponseEntity<OnedayclanResponse<Void>> deleteFaq(@PathVariable long faqSeq) {
        faqPort.deleteFaq(faqSeq);
        return ResponseEntity.ok(OnedayclanResponse.success());
    }
}
