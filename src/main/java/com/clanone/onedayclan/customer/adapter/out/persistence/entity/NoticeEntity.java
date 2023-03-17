package com.clanone.onedayclan.customer.adapter.out.persistence.entity;

import com.clanone.onedayclan.audit.AbstractUpdatableEntity;
import com.clanone.onedayclan.common.adapter.out.persistence.entity.ImageEntity;
import com.clanone.onedayclan.customer.adapter.in.web.request.NoticeCreateRequest;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.Objects;

@Getter
@Entity
@Table(name = "notice")
@NoArgsConstructor
public class NoticeEntity extends AbstractUpdatableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_seq", referencedColumnName = "seq")
    private ImageEntity image;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(nullable = false)
    private boolean showYn;

    @Builder
    public NoticeEntity(ImageEntity image, String title, String content, boolean showYn) {
        this.image = image;
        this.title = title;
        this.content = content;
        this.showYn = showYn;
    }

    public boolean hasImage() {
        return Objects.nonNull(this.image);
    }

    public void connectImage(ImageEntity image) {
        this.image = image;
    }

    public void updateInfo(NoticeCreateRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
        this.showYn = request.isShowYn();
    }

    public void updateImage(ImageEntity image) {
        this.image = image;
    }

    public boolean isImageChanged(Long imageSeq) {
        if(this.image == null){
            return imageSeq != null;
        } else {
            return this.image.getSeq() != imageSeq;
        }

    }
}
