package com.clanone.onedayclan.customer.adapter.out.persistence.repository;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.InquiryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRepository extends JpaRepository<InquiryEntity, Long> {
}
