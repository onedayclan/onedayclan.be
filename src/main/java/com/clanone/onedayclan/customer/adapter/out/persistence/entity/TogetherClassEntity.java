package com.clanone.onedayclan.customer.adapter.out.persistence.entity;

import com.clanone.onedayclan.audit.AbstractUpdatableEntity;
import com.clanone.onedayclan.customer.domain.enums.TogetherClassCategory;
import com.clanone.onedayclan.member.adapter.out.persistence.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "together_class")
@NoArgsConstructor
public class TogetherClassEntity extends AbstractUpdatableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @ManyToOne
    @JoinColumn(name = "member_seq", referencedColumnName = "seq")
    private MemberEntity member;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TogetherClassCategory category;

    private int limitPeople;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(nullable = false, length = 500)
    private String answer;

    @Builder
    public TogetherClassEntity(MemberEntity member, String title, TogetherClassCategory category, int limitPeople, String content, String answer) {
        this.member = member;
        this.title = title;
        this.category = category;
        this.limitPeople = limitPeople;
        this.content = content;
        this.answer = answer;
    }
}
