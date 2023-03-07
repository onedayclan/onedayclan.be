package com.clanone.onedayclan.member.adapter.in.web.response;

import com.clanone.onedayclan.member.adapter.out.persistence.entity.MemberEntity;
import com.clanone.onedayclan.member.domain.enums.MemberOrganizationStatus;
import com.clanone.onedayclan.member.domain.enums.MemberStatusType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Builder
public class MemberDetailResponse {
    private String userId;
    private String name;
    private String phone;
    private OrganizationResponse organization;
    private OrganizationResponse requestOrganization;
    private MemberStatusType status;
    private MemberOrganizationStatus organizationStatus;
    private LocalDateTime inactiveAt;
    private LocalDateTime penaltyAt;
    private String displayMessage;
    private LocalDateTime displayMessageStartAt;
    private LocalDateTime displayMessageEndAt;
    private String memo;

    public static MemberDetailResponse of(MemberEntity member) {
        return MemberDetailResponse.builder()
                .userId(member.getUserId())
                .name(member.getName())
                .phone(member.getPhone())
                .organization(Objects.isNull(member.getConfirmOrganization()) ? null : OrganizationResponse.builder()
                        .seq(member.getConfirmOrganization().getSeq())
                        .name(member.getConfirmOrganization().getName())
                        .build())
                .requestOrganization(Objects.isNull(member.getRequestOrganization()) ? null : OrganizationResponse.builder()
                        .seq(member.getRequestOrganization().getSeq())
                        .name(member.getRequestOrganization().getName())
                        .build())
                .status(member.getStatus())
                .organizationStatus(member.getOrganizationStatus())
                .inactiveAt(member.getInactiveAt())
                .penaltyAt(member.getPenaltyEndAt())
                .displayMessage(member.getDisplayMessage())
                .displayMessageStartAt(member.getDisplayMessageStartAt())
                .displayMessageEndAt(member.getDisplayMessageEndAt())
                .memo(member.getMemo())
                .build();
    }
}
