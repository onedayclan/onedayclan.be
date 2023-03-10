package com.clanone.onedayclan.customer.adapter.in.web.controller;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.common.resolver.LoginUserId;
import com.clanone.onedayclan.customer.adapter.in.web.request.PostInquiryRequest;
import com.clanone.onedayclan.customer.adapter.in.web.response.InquiryListResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.InquiryResponse;
import com.clanone.onedayclan.customer.application.port.in.InquiryPort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class InquiryController {

    private final InquiryPort inquiryPort;

    @PostMapping("/inquiry")
    public ResponseEntity<OnedayclanResponse<Void>> postInquiry(@LoginUserId String userId, @Valid @RequestBody PostInquiryRequest inquiryRequest) {
        inquiryPort.postInquiry(inquiryRequest, userId);
        return ResponseEntity.ok(OnedayclanResponse.success());
    }

    @GetMapping("/inquiry/{seq}")
    public ResponseEntity<OnedayclanResponse<InquiryResponse>> inquiryAnswer(@PathVariable("seq") long seq, @LoginUserId String userId){
        return ResponseEntity.ok(OnedayclanResponse.of(inquiryPort.inquiryAnswer(seq, userId)));
    }

    @GetMapping("/inquiry")
    public ResponseEntity<OnedayclanResponse<List<InquiryListResponse>>> getInquiryList(@LoginUserId String userId) {
        return ResponseEntity.ok(OnedayclanResponse.of(inquiryPort.inquiryList(userId)));
    }

    @DeleteMapping("/inquiry/{seq}")
    public ResponseEntity<OnedayclanResponse<Void>> deleteInquiry(@LoginUserId String userId,@PathVariable("seq") long seq) {
        inquiryPort.deleteInquiry(userId, seq);
        return ResponseEntity.ok(OnedayclanResponse.success());
    }
}
