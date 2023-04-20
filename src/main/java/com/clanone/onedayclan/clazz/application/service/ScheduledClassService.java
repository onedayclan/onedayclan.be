package com.clanone.onedayclan.clazz.application.service;

import com.clanone.onedayclan.clazz.adapter.in.web.response.CancelClassMessageResponse;
import com.clanone.onedayclan.clazz.adapter.in.web.response.ScheduledClassInfoResponse;
import com.clanone.onedayclan.clazz.adapter.in.web.response.ScheduledClassResponse;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassMemberEntity;
import com.clanone.onedayclan.clazz.application.model.ScheduledClassModel;
import com.clanone.onedayclan.clazz.application.port.in.ScheduledClassPort;
import com.clanone.onedayclan.clazz.application.port.out.GetClassMemberPort;
import com.clanone.onedayclan.clazz.application.port.out.GetClassReviewPort;
import com.clanone.onedayclan.member.adapter.out.persistence.entity.MemberEntity;
import com.clanone.onedayclan.member.application.port.out.GetMemberPort;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduledClassService implements ScheduledClassPort {

    private final GetClassReviewPort getClassReviewPort;
    private final GetMemberPort getMemberPort;
    private final GetClassMemberPort getClassMemberPort;

    @Override
    public ScheduledClassInfoResponse getScheduledClassInfo(String userId) {
        MemberEntity member = getMemberPort.getMemberByUserId(userId);
        long participatedClassCount = getClassReviewPort.getParticipatedClassByUser(member.getSeq(),
                LocalDateTime.of(LocalDate.now().getYear(), 1, 1,0,0),
                LocalDateTime.of(LocalDate.now().getYear(), 12, 31,23,59));

        return ScheduledClassInfoResponse.builder()
                .attendanceCount(participatedClassCount)
                .penaltyStartAt(member.getPenaltyStartAt())
                .penaltyEndAt(member.getPenaltyEndAt())
                .displayMessage(member.getDisplayMessage())
                .cancelMessageByClass(getClassMemberPort.getClassMemberByMemberSeqByCanceledTrue(member.getSeq()))
                .build();
    }

    @Override
    public List<ScheduledClassResponse> getScheduledClassList(String userId) {
        return getClassMemberPort.getScheduledClassModel(userId).stream().map(ScheduledClassResponse::of).collect(Collectors.toList());
    }
}
