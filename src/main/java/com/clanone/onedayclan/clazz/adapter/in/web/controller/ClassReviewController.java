package com.clanone.onedayclan.clazz.adapter.in.web.controller;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.clazz.adapter.in.web.response.ClassReviewQuestionResponse;
import com.clanone.onedayclan.clazz.application.port.in.ClassReviewPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
