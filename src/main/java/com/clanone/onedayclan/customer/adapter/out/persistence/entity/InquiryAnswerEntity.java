package com.clanone.onedayclan.customer.adapter.out.persistence.entity;

import com.clanone.onedayclan.audit.AbstractUpdatableEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "inquiry_answer")
@NoArgsConstructor
public class InquiryAnswerEntity extends AbstractUpdatableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_seq", referencedColumnName = "seq")
    private InquiryEntity inquiry;

    @Column(nullable = false, length = 500)
    private String content;

    @Builder
    public InquiryAnswerEntity(InquiryEntity inquiry, String content) {
        this.inquiry = inquiry;
        this.content = content;
    }
}
