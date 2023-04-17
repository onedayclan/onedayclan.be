package com.clanone.onedayclan.clazz.adapter.in.web.response;

import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassMemberEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class AdminClassMemberResponse {
    private String userId;
    private String name;
    private String organizationName;
    private LocalDateTime createdAt;
    private boolean cancelYn;
    private String cancelMessage;

    public static AdminClassMemberResponse of(ClassMemberEntity classMember) {
        return AdminClassMemberResponse.builder()
                .userId(classMember.getMember().getUserId())
                .name(classMember.getMember().getName())
                .organizationName(classMember.getMember().getConfirmOrganization().getName())
                .cancelYn(classMember.isCancelYn())
                .cancelMessage(classMember.getCancelMessage())
                .createdAt(classMember.getCreatedAt())
                .build();
    }
}
