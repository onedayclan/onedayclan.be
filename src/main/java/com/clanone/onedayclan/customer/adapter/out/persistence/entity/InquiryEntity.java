package com.clanone.onedayclan.customer.adapter.out.persistence.entity;

import com.clanone.onedayclan.audit.AbstractUpdatableEntity;
import com.clanone.onedayclan.member.adapter.out.persistence.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Entity
@Table(name = "inquiry")
@NoArgsConstructor
public class InquiryEntity extends AbstractUpdatableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_seq", referencedColumnName = "seq")
    private MemberEntity member;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(nullable = false)
    private boolean answerYn;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean deleteYn;

    @Builder
    public InquiryEntity(MemberEntity member, String title, String content, boolean answerYn) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.answerYn = answerYn;
    }

    public void delete() {
        this.deleteYn = true;
    }

    public void answered() {
        this.answerYn = true;
    }
    public void notAnswered(){
        this.answerYn = false;
    }
}
