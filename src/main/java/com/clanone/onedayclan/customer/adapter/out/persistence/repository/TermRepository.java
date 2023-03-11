package com.clanone.onedayclan.customer.adapter.out.persistence.repository;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.TermsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermRepository extends JpaRepository<TermsEntity, Long> {
    List<TermsEntity> findAllByOrderByTypeAscCreatedAtDesc();
}
