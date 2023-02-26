package com.clanone.onedayclan.clazz.adapter.out.persistence.entity;

import com.clanone.onedayclan.member.adapter.out.persistence.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "class_review")
@NoArgsConstructor
public class ClassReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @ManyToOne
    @JoinColumn(name = "class_seq", referencedColumnName = "seq")
    private ClassEntity clazz;

    @ManyToOne
    @JoinColumn(name = "member_seq", referencedColumnName = "seq")
    private MemberEntity member;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(nullable = false)
    private int star;

    @Column(nullable = false, length = 500)
    private String wishClass;

    @Builder
    public ClassReviewEntity(ClassEntity clazz, MemberEntity member, String content, int star, String wishClass) {
        this.clazz = clazz;
        this.member = member;
        this.content = content;
        this.star = star;
        this.wishClass = wishClass;
    }
}
