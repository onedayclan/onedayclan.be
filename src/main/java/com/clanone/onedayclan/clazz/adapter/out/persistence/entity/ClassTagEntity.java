package com.clanone.onedayclan.clazz.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "class_tag")
@NoArgsConstructor
public class ClassTagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_seq", referencedColumnName = "seq")
    private ClassEntity clazz;

    @Column(nullable = false, length = 50)
    private String name;

    @Builder
    public ClassTagEntity(ClassEntity clazz, String name) {
        this.clazz = clazz;
        this.name = name;
    }
}
