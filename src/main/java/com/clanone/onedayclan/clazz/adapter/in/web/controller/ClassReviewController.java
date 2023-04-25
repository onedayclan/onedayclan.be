package com.clanone.onedayclan.clazz.adapter.in.web.controller;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.clazz.adapter.in.web.request.WriteClassReviewRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.response.ClassReviewQuestionResponse;
import com.clanone.onedayclan.clazz.application.port.in.ClassReviewPort;
import com.clanone.onedayclan.common.resolver.LoginUserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/class/review")
public class ClassReviewController {

    private final ClassReviewPort classReviewPort;

    @GetMapping("/question")
    public ResponseEntity<OnedayclanResponse<List<ClassReviewQuestionResponse>>> getClassReviewQuestion(){
        return ResponseEntity.ok(OnedayclanResponse.of(classReviewPort.getClassReviewQuestion()));
    }

    @PostMapping("/{classSeq}")
    public ResponseEntity<OnedayclanResponse<Void>> writeReview(@LoginUserId String userId,
                                                                @PathVariable long classSeq,
                                                                @RequestBody WriteClassReviewRequest writeClassReviewRequest){

        classReviewPort.writeClassReview(userId, classSeq, writeClassReviewRequest);
        return ResponseEntity.ok(OnedayclanResponse.success());
    }
}
