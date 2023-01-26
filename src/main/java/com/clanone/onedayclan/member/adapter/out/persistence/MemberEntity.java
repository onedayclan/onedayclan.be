package com.clanone.onedayclan.member.adapter.out.persistence;

import com.clanone.onedayclan.member.domain.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column
    private String id;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String phone;

    @Builder
    public MemberEntity(String id, String password, String name, String email, String phone) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
