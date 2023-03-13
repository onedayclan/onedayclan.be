package com.clanone.onedayclan.customer.adapter.in.web.controller;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.OnedayclanResponse.PagingResult;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminTermsDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminTermsResponse;
import com.clanone.onedayclan.customer.application.port.in.TermsPort;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/terms")
@RequiredArgsConstructor
public class AdminTermsController {
    private final TermsPort termsPort;

    @GetMapping("")
    public ResponseEntity<OnedayclanResponse<PagingResult<AdminTermsResponse>>> getTermsList(@RequestParam(defaultValue = "1") int pageNo,
                                                                                             @RequestParam(defaultValue = "10") int pageSize) {
        Page<AdminTermsResponse> terms = termsPort.getTermsList(PageRequest.of(pageNo-1, pageSize));
        return ResponseEntity.ok(OnedayclanResponse.of(terms.getContent(), pageNo, terms.getTotalElements()));
    }

    @GetMapping("/{termsSeq}")
    public ResponseEntity<OnedayclanResponse<AdminTermsDetailResponse>> getTerms(@PathVariable long termsSeq) {
        return ResponseEntity.ok(OnedayclanResponse.of(termsPort.getTermsForAdmin(termsSeq)));
    }
}
