package com.clanone.onedayclan.customer.adapter.out.persistence.entity;

import com.clanone.onedayclan.audit.AbstractUpdatableEntity;
import com.clanone.onedayclan.customer.domain.enums.FaqCategory;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "faq")
@NoArgsConstructor
public class FaqEntity extends AbstractUpdatableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FaqCategory category;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(nullable = false)
    private boolean showYn;

    @Builder
    public FaqEntity(String title, FaqCategory category, String content, boolean showYn) {
        this.title = title;
        this.category = category;
        this.content = content;
        this.showYn = showYn;
    }
}
