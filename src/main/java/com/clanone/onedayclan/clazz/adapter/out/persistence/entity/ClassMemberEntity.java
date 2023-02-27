package com.clanone.onedayclan.clazz.adapter.out.persistence.entity;

import com.clanone.onedayclan.audit.AbstractUpdatableEntity;
import com.clanone.onedayclan.clazz.domain.enums.AttendanceCheck;
import com.clanone.onedayclan.member.adapter.out.persistence.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "class_member")
@NoArgsConstructor
public class ClassMemberEntity extends AbstractUpdatableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_seq", referencedColumnName = "seq")
    private MemberEntity member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_seq", referencedColumnName = "seq")
    private ClassEntity clazz;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AttendanceCheck attendanceCheck;

    @Column(nullable = false)
    private boolean cancelYn;

    @Column(length = 200)
    private String cancelMessage;

    @Builder
    public ClassMemberEntity(MemberEntity member, ClassEntity clazz, AttendanceCheck attendanceCheck, boolean cancelYn, String cancelMessage) {
        this.member = member;
        this.clazz = clazz;
        this.attendanceCheck = attendanceCheck;
        this.cancelYn = cancelYn;
        this.cancelMessage = cancelMessage;
    }

}
