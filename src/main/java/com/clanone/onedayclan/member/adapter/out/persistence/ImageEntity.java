package com.clanone.onedayclan.member.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "image")
@Getter
@NoArgsConstructor
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String url;
    private String type;

    @Builder
    public ImageEntity(String fileName, String url, String type) {
        this.fileName = fileName;
        this.url = url;
        this.type = type;
    }
}
