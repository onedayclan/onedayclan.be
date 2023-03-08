package com.clanone.onedayclan.customer.adapter.out.persistence.entity;

import com.clanone.onedayclan.audit.AbstractImmutableEntity;
import com.clanone.onedayclan.customer.domain.enums.TermsType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "terms")
@NoArgsConstructor
public class TermsEntity extends AbstractImmutableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TermsType type;

    @Builder
    public TermsEntity(String content, TermsType type) {
        this.content = content;
        this.type = type;
    }
}
