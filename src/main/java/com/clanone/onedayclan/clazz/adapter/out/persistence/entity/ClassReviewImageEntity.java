package com.clanone.onedayclan.clazz.adapter.out.persistence.entity;

import com.clanone.onedayclan.common.adapter.out.persistence.entity.ImageEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@Table(name = "class_review_image")
@NoArgsConstructor
public class ClassReviewImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_review_seq", referencedColumnName = "seq")
    private ClassReviewEntity review;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_seq", referencedColumnName = "seq")
    private List<ImageEntity> images;

    @Builder
    public ClassReviewImageEntity(ClassReviewEntity review, List<ImageEntity> images) {
        this.review = review;
        this.images = images;
    }
}
