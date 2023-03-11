package com.clanone.onedayclan.customer.application.port.out;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.TermsEntity;

import java.util.List;

public interface GetTermPort {
    List<TermsEntity> getAllTermList();
}
