package com.clanone.onedayclan.member.adapter.out.persistence.entity;

import com.clanone.onedayclan.audit.AbstractUpdatableEntity;
import com.clanone.onedayclan.member.domain.enums.MemberOrganizationStatus;
import com.clanone.onedayclan.member.domain.enums.MemberType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

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
    private boolean activeYn;

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
        this.activeYn = true;
        this.displayMessage = displayMessage;
        this.displayMessageStartAt = displayMessageStartAt;
        this.displayMessageEndAt = displayMessageEndAt;
        this.memo = memo;
        this.type = type;
    }

    public void changePassword(String password){
        this.password = password;
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
