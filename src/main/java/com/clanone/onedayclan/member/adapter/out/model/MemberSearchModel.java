package com.clanone.onedayclan.member.adapter.out.model;

import com.clanone.onedayclan.member.domain.enums.MemberStatusType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MemberSearchModel {
    private String userId;
    private String name;
    private MemberStatusType status;
    private LocalDateTime searchStartAt;
    private LocalDateTime searchEndAt;
    private long organizationSeq;
}
