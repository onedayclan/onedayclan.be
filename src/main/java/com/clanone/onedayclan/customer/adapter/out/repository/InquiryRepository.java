package com.clanone.onedayclan.customer.adapter.out.repository;

import com.clanone.onedayclan.customer.adapter.out.entity.InquiryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRepository extends JpaRepository<InquiryEntity, Long> {
}
