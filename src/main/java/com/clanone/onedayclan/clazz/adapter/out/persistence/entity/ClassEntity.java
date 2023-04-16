package com.clanone.onedayclan.clazz.adapter.out.persistence.entity;

import com.clanone.onedayclan.audit.AbstractUpdatableEntity;
import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassCreateRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassUpdateRequest;
import com.clanone.onedayclan.clazz.domain.enums.ClassStatus;
import com.clanone.onedayclan.common.adapter.out.persistence.entity.ImageEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private double latitude;

    private double longitude;

    @Column(length = 500)
    private String location;

    @Column(length = 100)
    private String locationDetail;

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
    public ClassEntity(ImageEntity thumbnail, ClassCategoryEntity category, String name, Integer organizationFee, int normalFee, String teacherName, int limitPeople, LocalDateTime startAt, LocalDateTime endAt, LocalDateTime applicationEndAt, boolean offlineYn, String offlineLink, double latitude, double longitude, String location, String locationDetail, String description, String progress, String rule, boolean showYn, ClassStatus status) {
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
        this.locationDetail = locationDetail;
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
                .startAt(LocalDateTime.parse(request.getStartAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .endAt(LocalDateTime.parse(request.getEndAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .applicationEndAt(LocalDateTime.parse(request.getApplicationEndAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .offlineYn(request.isOfflineYn())
                .offlineLink(request.getOfflineLink())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .location(request.getLocation())
                .locationDetail(request.getLocationDetail())
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

    public void update(AdminClassUpdateRequest request, ClassCategoryEntity category, ImageEntity thumbnail) {
        if(Objects.nonNull(thumbnail)) {
            this.thumbnail = thumbnail;
        }
        this.category = category;
        this.name = request.getName();
        this.organizationFee = request.getOrganizationFee();
        this.normalFee = request.getNormalFee();
        this.teacherName = request.getTeacherName();
        this.limitPeople = request.getLimitPeople();
        this.startAt = LocalDateTime.parse(request.getStartAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.endAt = LocalDateTime.parse(request.getEndAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.applicationEndAt = LocalDateTime.parse(request.getApplicationEndAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));;
        this.offlineYn = request.isOfflineYn();
        this.offlineLink = request.getOfflineLink();
        this.latitude = request.getLatitude();
        this.longitude = request.getLongitude();
        this.location = request.getLocation();
        this.locationDetail = request.getLocationDetail();
        this.description = request.getDescription();
        this.progress = request.getProgress();
        this.rule = request.getRule();
        this.showYn = request.isShowYn();
    }

    public void finish() {
        this.status = ClassStatus.END_BEFORE_CHECK;
        this.updatedAt = LocalDateTime.now();
    }
}
