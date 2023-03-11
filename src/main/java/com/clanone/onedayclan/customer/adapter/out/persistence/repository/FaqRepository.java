package com.clanone.onedayclan.customer.adapter.out.persistence.repository;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.FaqEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaqRepository extends JpaRepository<FaqEntity,Long>, FaqCustomRepository {
    List<FaqEntity> findByShowYnOrderByCreatedAtAsc(boolean showYn);
}
