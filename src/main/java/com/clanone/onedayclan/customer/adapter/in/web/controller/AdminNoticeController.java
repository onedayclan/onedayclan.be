package com.clanone.onedayclan.customer.adapter.in.web.controller;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.OnedayclanResponse.PagingResult;
import com.clanone.onedayclan.common.application.service.utils.DateUtil;
import com.clanone.onedayclan.customer.adapter.in.web.request.NoticeCreateRequest;
import com.clanone.onedayclan.customer.adapter.in.web.request.NoticeSearchRequest;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminNoticeDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminNoticeResponse;
import com.clanone.onedayclan.customer.application.port.in.NoticePort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/admin/notice")
@RequiredArgsConstructor
public class AdminNoticeController {

    private final NoticePort noticePort;

    @GetMapping("")
    public ResponseEntity<OnedayclanResponse<PagingResult<AdminNoticeResponse>>> getNoticeList(@RequestParam(required = false) String title,
                                                                                               @RequestParam(required = false) String searchStartAt,
                                                                                               @RequestParam(required = false) String searchEndAt,
                                                                                               @RequestParam(defaultValue = "1") int pageNo,
                                                                                               @RequestParam(defaultValue = "10") int pageSize) {
        Page<AdminNoticeResponse> result = noticePort.getNoticeListForAdmin(NoticeSearchRequest.builder()
                .title(title)
                .searchStartAt(Objects.isNull(searchStartAt) ? null : DateUtil.parseLocalDateTimeByYYYYMMDD(searchStartAt))
                .searchEndAt(Objects.isNull(searchEndAt) ? null : DateUtil.parseLocalDateTimeByYYYYMMDD(searchEndAt))
                .build(), PageRequest.of(pageNo-1, pageSize));
        return ResponseEntity.ok(OnedayclanResponse.of(result.getContent(), pageNo, result.getTotalElements()));
    }

    @GetMapping("/{noticeSeq}")
    public ResponseEntity<OnedayclanResponse<AdminNoticeDetailResponse>> getNotice(@PathVariable long noticeSeq) {
        return ResponseEntity.ok(OnedayclanResponse.of(noticePort.getNoticeForAdmin(noticeSeq)));
    }

    @PostMapping("")
    public ResponseEntity<OnedayclanResponse<AdminNoticeDetailResponse>> insertNotice(@Valid @RequestBody NoticeCreateRequest request) {
        return ResponseEntity.ok(OnedayclanResponse.of(noticePort.insertNotice(request)));
    }

    @DeleteMapping("/{noticeSeq}")
    public ResponseEntity<OnedayclanResponse<Void>> deleteNotice(@PathVariable long noticeSeq) {
        noticePort.deleteNotice(noticeSeq);
        return ResponseEntity.ok(OnedayclanResponse.success());
    }
}
