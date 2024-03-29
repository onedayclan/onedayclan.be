package com.clanone.onedayclan.member.adapter.out.persistence.entity;

import com.clanone.onedayclan.audit.AbstractUpdatableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "find_password")
@NoArgsConstructor
@Getter
public class FindPasswordEntity extends AbstractUpdatableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false)
    private String authorizationCode;

    @Column(nullable = false)
    private boolean usedYn;

    @Column(nullable = false)
    private LocalDateTime validAt;

    public void changeUsedYn() {
        this.usedYn = true;
    }

    @Builder
    public FindPasswordEntity(String email, String authorizationCode, LocalDateTime validAt) {
        this.email = email;
        this.authorizationCode = authorizationCode;
        this.validAt = validAt;
    }
}
