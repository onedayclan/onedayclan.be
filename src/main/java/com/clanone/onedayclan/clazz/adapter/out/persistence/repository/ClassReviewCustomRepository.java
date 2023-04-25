package com.clanone.onedayclan.clazz.adapter.out.persistence.repository;

import com.clanone.onedayclan.clazz.adapter.in.web.response.AdminClassReviewInfoResponse;

import java.util.List;

public interface ClassReviewCustomRepository {
    List<AdminClassReviewInfoResponse> getReviewInfoByClassSeq(List<Long> classSeqList);
}
