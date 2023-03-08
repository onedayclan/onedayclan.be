package com.clanone.onedayclan.member.adapter.in.web.request;

import com.clanone.onedayclan.common.application.service.utils.DateUtil;
import com.clanone.onedayclan.member.domain.enums.MemberStatusType;
import lombok.Getter;

@Getter
public class MemberUpdateRequest {
    private Long organizationSeq;
    private MemberStatusType status;
    private String penaltyAt;
    private String displayMessage;
    private String displayMessageStartAt;
    private String displayMessageEndAt;
    private String memo;
}
