package com.clanone.onedayclan.clazz.adapter.out.persistence.entity;

import com.clanone.onedayclan.audit.AbstractImmutableEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "class_category")
@NoArgsConstructor
public class ClassCategoryEntity extends AbstractImmutableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @Column(length = 45)
    private String name;

    @Builder
    public ClassCategoryEntity(String name) {
        this.name = name;
    }
}
