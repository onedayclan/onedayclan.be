package com.clanone.onedayclan.member.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "authorization_number")
@Getter
@NoArgsConstructor
public class AuthorizationNumberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String phoneNumber;

    private String authorizationNumber;

    private boolean usedYn;

    @ColumnDefault("false")
    private LocalDateTime validAt;

    public void use() {
        this.usedYn = true;
    }

    @Builder
    public AuthorizationNumberEntity(String phoneNumber, String authorizationNumber, LocalDateTime validAt) {
        this.phoneNumber = phoneNumber;
        this.authorizationNumber = authorizationNumber;
        this.validAt = validAt;
    }
}
