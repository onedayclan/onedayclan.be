package com.clanone.onedayclan.audit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@ToString(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Table(indexes = {@Index(columnList = "updated_at")})
public abstract class AbstractUpdatableEntity extends AbstractImmutableEntity{
    @LastModifiedDate
    @Column(name = "updated_at", columnDefinition = "DATETIME")
    protected LocalDateTime updatedAt;
}
