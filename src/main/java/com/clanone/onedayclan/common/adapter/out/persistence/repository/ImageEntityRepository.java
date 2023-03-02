package com.clanone.onedayclan.common.adapter.out.persistence.repository;

import com.clanone.onedayclan.common.adapter.out.persistence.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageEntityRepository extends JpaRepository<ImageEntity, Long> {
}
