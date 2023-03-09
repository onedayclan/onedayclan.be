package com.clanone.onedayclan.member.adapter.out.persistence.entity;

import com.clanone.onedayclan.audit.AbstractImmutableEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "organization")
@NoArgsConstructor
public class OrganizationEntity extends AbstractImmutableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @Column(nullable = false, length = 45)
    private String name;

    @Builder
    public OrganizationEntity(String name) {
        this.name = name;
    }

    public void updateOrganizationName(String name){
        this.name = name;
    }
}
