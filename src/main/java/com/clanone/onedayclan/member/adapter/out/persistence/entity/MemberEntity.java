package com.clanone.onedayclan.member.adapter.out.persistence.entity;

import com.clanone.onedayclan.audit.AbstractUpdatableEntity;
import com.clanone.onedayclan.common.application.service.utils.DateUtil;
import com.clanone.onedayclan.member.adapter.in.web.request.MemberUpdateRequest;
import com.clanone.onedayclan.member.domain.enums.MemberOrganizationStatus;
import com.clanone.onedayclan.member.domain.enums.MemberStatusType;
import com.clanone.onedayclan.member.domain.enums.MemberType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
public class MemberEntity extends AbstractUpdatableEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(nullable = false, length = 100)
    private String userId;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(length = 15)
    private String phone;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "confirmOrganizationSeq", referencedColumnName = "seq")
    private OrganizationEntity confirmOrganization;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requestOrganizationSeq", referencedColumnName = "seq")
    private OrganizationEntity requestOrganization;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberOrganizationStatus organizationStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberStatusType status;

    private LocalDateTime inactiveAt;

    @Column(length = 500)
    private String displayMessage;

    private LocalDateTime displayMessageStartAt;

    private LocalDateTime displayMessageEndAt;

    @Column(length = 500)
    private String memo;

    private LocalDateTime penaltyStartAt;

    private LocalDateTime penaltyEndAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberType type;

    @Builder
    public MemberEntity(String userId, String password, String name, String phone, OrganizationEntity confirmOrganization, OrganizationEntity requestOrganization, MemberOrganizationStatus organizationStatus, String displayMessage, LocalDateTime displayMessageStartAt, LocalDateTime displayMessageEndAt, String memo, MemberType type) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.confirmOrganization = confirmOrganization;
        this.requestOrganization = requestOrganization;
        this.organizationStatus = organizationStatus;
        this.status = MemberStatusType.NORMAL;
        this.displayMessage = displayMessage;
        this.displayMessageStartAt = displayMessageStartAt;
        this.displayMessageEndAt = displayMessageEndAt;
        this.memo = memo;
        this.type = type;
    }

    public void changePassword(String password){
        this.password = password;
    }

    public void updateMemberInfo(MemberUpdateRequest request) {
        this.status = request.getStatus();
        this.displayMessage = request.getDisplayMessage();

        if(Objects.nonNull(request.getDisplayMessageStartAt()) && Objects.nonNull(request.getDisplayMessageEndAt())) {
            LocalDateTime[] displayMessageAt = DateUtil.parseDurationByYYYYMMDD(request.getDisplayMessageStartAt(), request.getDisplayMessageEndAt());
            this.displayMessageStartAt = displayMessageAt[0];
            this.displayMessageEndAt = displayMessageAt[1];
        }

        if(isPenaltyChanged(request.getPenaltyAt())) {
            if(Objects.isNull(request.getPenaltyAt())) {
                this.penaltyEndAt = null;
                this.penaltyStartAt = null;
            }
            this.penaltyEndAt = LocalDateTime.of(LocalDate.parse(request.getPenaltyAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalTime.parse("23:59:59"));
            this.penaltyStartAt = LocalDateTime.of(LocalDate.now(), LocalTime.parse("00:00:00"));
        }

        this.memo = request.getMemo();
    }

    public boolean isPenaltyChanged(String penaltyAt) {
        if(this.penaltyEndAt == null) {
            return Objects.nonNull(penaltyAt);
        }
        return !this.penaltyEndAt.equals(LocalDateTime.of(LocalDate.parse(penaltyAt, DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalTime.MAX));
    }

    public void updateOrganization(OrganizationEntity organization) {
        this.confirmOrganization = organization;
    }

    public boolean isOrganizationChanged(Long organizationSeq) {
        if(this.confirmOrganization == null) {
            return organizationSeq != null;
        }
        return this.confirmOrganization.getSeq() != organizationSeq;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
