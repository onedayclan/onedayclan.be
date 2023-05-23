package com.clanone.onedayclan.clazz.adapter.in.web.controller;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.clazz.adapter.in.web.request.ApplyClassRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.request.ClassSearchRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.response.*;
import com.clanone.onedayclan.clazz.application.port.in.ClassPort;
import com.clanone.onedayclan.clazz.domain.enums.ClassListSort;
import com.clanone.onedayclan.common.resolver.LoginUserId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/class")
public class ClassController {

    private final ClassPort classPort;

    @GetMapping("/main")
    public ResponseEntity<OnedayclanResponse<List<LatestClassResponse>>> getLatestClass(){
        return ResponseEntity.ok(OnedayclanResponse.of(classPort.getLatestClass()));
    }

    @GetMapping("/category")
    public ResponseEntity<OnedayclanResponse<List<ClassCategoryListResponse>>> getClassCategoryList() {
        return ResponseEntity.ok(OnedayclanResponse.of(classPort.getClassCategoryList()));
    }

    /**
     *
     * @param categorySeq 1,2,3 (seq 값)
     * @param searchKeyword class tag, class.description, class.name 검색 가능
     * @param sort NEW, DISTANCE
     * @param longitude 기본 값 : 부천
     * @param latitude 기본 값 : 부천
     * @param pageNo
     * @param pageSize 20
     * @return
     */
    @GetMapping("")
    public ResponseEntity<OnedayclanResponse<OnedayclanResponse.PagingResult<ClassListResponse>>> getClassList(@RequestParam(required = false) Long categorySeq,
                                                                                                               @RequestParam(required = false) String searchKeyword,
                                                                                                               @RequestParam(defaultValue = "NEW") ClassListSort sort,
                                                                                                               @RequestParam(defaultValue = "126.78289730993926") double longitude,
                                                                                                               @RequestParam(defaultValue = "37.48403629889928") double latitude,
                                                                                                               @RequestParam(defaultValue = "1") int pageNo,
                                                                                                               @RequestParam(defaultValue = "20") int pageSize){

        Page<ClassListResponse> result = classPort.getMainClassList(ClassSearchRequest.builder()
                .categorySeq(categorySeq)
                .searchKeyword(searchKeyword)
                .sort(sort)
                .longitude(longitude)
                .latitude(latitude)
                .build(), PageRequest.of(pageNo - 1, pageSize));
        return ResponseEntity.ok(OnedayclanResponse.of(result.getContent(), pageNo, result.getTotalElements()));
    }

    @GetMapping("/{classSeq}")
    public ResponseEntity<OnedayclanResponse<ClassDetailResponse>> getClassDetail(@PathVariable long classSeq){
        return ResponseEntity.ok(OnedayclanResponse.of(classPort.getClassDetail(classSeq)));
    }

    @PostMapping("/apply")
    public ResponseEntity<OnedayclanResponse<ApplyClassResponse>> applyClass(@LoginUserId String userId, @RequestBody ApplyClassRequest applyClassRequest){
        return ResponseEntity.ok(OnedayclanResponse.of(classPort.applyClass(userId, applyClassRequest)));
    }
}
