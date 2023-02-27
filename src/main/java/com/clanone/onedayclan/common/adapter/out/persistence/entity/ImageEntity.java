package com.clanone.onedayclan.common.adapter.out.persistence.entity;

import com.clanone.onedayclan.audit.AbstractUpdatableEntity;
import com.clanone.onedayclan.common.domain.enums.ImageType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "image")
@NoArgsConstructor
public class ImageEntity extends AbstractUpdatableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @Column(nullable = false, length = 100)
    private String fileName;

    @Column(nullable = false, length = 500)
    private String url;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ImageType type;

    @Builder
    public ImageEntity(String fileName, String url) {
        this.fileName = fileName;
        this.url = url;
        this.type = ImageType.NONE;
    }
}
