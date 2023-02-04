package com.clanone.onedayclan.audit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Table(indexes = {@Index(columnList = "created_at")})
public abstract class AbstractImmutableEntity implements Serializable {
    @CreatedDate
    @Column(updatable = false, name = "created_at", columnDefinition = "DATETIME")
    protected LocalDateTime createdAt;
}
