package com.clanone.onedayclan.clazz.adapter.out.persistence.adapter;

import com.clanone.onedayclan.clazz.adapter.out.persistence.repository.ClassReviewRepository;
import com.clanone.onedayclan.clazz.application.port.out.GetClassReviewPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ClassReviewAdapter implements GetClassReviewPort {

    private final ClassReviewRepository classReviewRepository;

    @Override
    public long getParticipatedClassByUser(long memberSeq, LocalDateTime startAt, LocalDateTime endAt) {
        return classReviewRepository.countByMemberSeqAndCreatedAtBetween(memberSeq,startAt,endAt);
    }
}
