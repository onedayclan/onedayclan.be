package com.clanone.onedayclan.customer.adapter.in.web.controller;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.NoticeDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.NoticeResponse;
import com.clanone.onedayclan.customer.application.port.in.NoticePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/notice")
public class NoticeController {

    private final NoticePort noticePort;

    @GetMapping("")
    public ResponseEntity<OnedayclanResponse<List<NoticeResponse>>> getNoticeList(@RequestParam(required = false) boolean main) {
        if (main) {
            return ResponseEntity.ok(OnedayclanResponse.of(noticePort.getMainNoticeList()));
        }
        else {
            return ResponseEntity.ok(OnedayclanResponse.of(noticePort.getNoticeList()));
        }
    }

    @GetMapping("/{seq}")
    public ResponseEntity<OnedayclanResponse<NoticeDetailResponse>> getNotice(@PathVariable long seq){
        return ResponseEntity.ok(OnedayclanResponse.of(noticePort.getNotice(seq)));
    }
}
