package com.clanone.onedayclan.customer.adapter.out.persistence.repository;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.TermsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermsRepository extends JpaRepository<TermsEntity, Long> {
    List<TermsEntity> findAllByOrderByTypeAscCreatedAtDesc();
    Page<TermsEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
