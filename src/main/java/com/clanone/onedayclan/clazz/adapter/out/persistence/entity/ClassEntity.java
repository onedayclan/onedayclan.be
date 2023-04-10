package com.clanone.onedayclan.clazz.adapter.out.persistence.entity;

import com.clanone.onedayclan.audit.AbstractUpdatableEntity;
import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassCreateRequest;
import com.clanone.onedayclan.clazz.domain.enums.ClassStatus;
import com.clanone.onedayclan.common.adapter.out.persistence.entity.ImageEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Entity
@Table(name = "class")
@NoArgsConstructor
public class ClassEntity extends AbstractUpdatableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poster_seq", referencedColumnName = "seq")
    private ImageEntity poster;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thumbnail_seq", referencedColumnName = "seq")
    private ImageEntity thumbnail;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_seq", referencedColumnName = "seq")
    private ClassCategoryEntity category;

    @Column(nullable = false, length = 200)
    private String name;

    private Integer organizationFee;

    @Column(nullable = false)
    private int normalFee;

    @Column(nullable = false, length = 20)
    private String teacherName;

    @Column(nullable = false)
    private int limitPeople;

    @Column(nullable = false)
    private LocalDateTime startAt;

    @Column(nullable = false)
    private LocalDateTime endAt;

    @Column(nullable = false)
    private LocalDateTime applicationEndAt;

    @Column(nullable = false)
    private boolean offlineYn;

    @Column(length = 500)
    private String offlineLink;

    private long latitude;

    private long longitude;

    @Column(length = 500)
    private String location;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false, length = 500)
    private String progress;

    @Column(nullable = false, length = 500)
    private String rule;

    @Column(nullable = false)
    private boolean showYn;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClassStatus status;

    @Builder
    public ClassEntity(ImageEntity poster, ImageEntity thumbnail, ClassCategoryEntity category, String name, Integer organizationFee, int normalFee, String teacherName, int limitPeople, LocalDateTime startAt, LocalDateTime endAt, LocalDateTime applicationEndAt, boolean offlineYn, String offlineLink, long latitude, long longitude, String location, String description, String progress, String rule, boolean showYn, ClassStatus status) {
        this.poster = poster;
        this.thumbnail = thumbnail;
        this.category = category;
        this.name = name;
        this.organizationFee = organizationFee;
        this.normalFee = normalFee;
        this.teacherName = teacherName;
        this.limitPeople = limitPeople;
        this.startAt = startAt;
        this.endAt = endAt;
        this.applicationEndAt = applicationEndAt;
        this.offlineYn = offlineYn;
        this.offlineLink = offlineLink;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
        this.description = description;
        this.progress = progress;
        this.rule = rule;
        this.showYn = showYn;
        this.status = status;
    }

    public static ClassEntity of(AdminClassCreateRequest request, ClassCategoryEntity category, ImageEntity thumbnail) {
        ClassEntity classEntity = ClassEntity.builder()
                .name(request.getName())
                .category(category)
                .organizationFee(request.getOrganizationFee())
                .normalFee(request.getNormalFee())
                .teacherName(request.getTeacherName())
                .limitPeople(request.getLimitPeople())
                .startAt(request.getStartAt())
                .endAt(request.getEndAt())
                .applicationEndAt(request.getApplicationEndAt())
                .offlineYn(request.isOfflineYn())
                .offlineLink(request.getOfflineLink())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .location(request.getLocation())
                .description(request.getDescription())
                .progress(request.getProgress())
                .rule(request.getRule())
                .showYn(request.isShowYn())
                .status(ClassStatus.IN_PROGRESS)
                .build();

        if(Objects.nonNull(thumbnail)) {
            classEntity.updateThumbnail(thumbnail);
        }

        return classEntity;
    }

    public void updateThumbnail(ImageEntity thumbnail) {
        this.thumbnail = thumbnail;
    }
}
