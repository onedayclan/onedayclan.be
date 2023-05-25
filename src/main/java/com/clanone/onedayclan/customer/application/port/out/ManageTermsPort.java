package com.clanone.onedayclan.customer.application.port.out;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.TermsEntity;

public interface ManageTermsPort {
    TermsEntity saveTerms(TermsEntity terms);
}
