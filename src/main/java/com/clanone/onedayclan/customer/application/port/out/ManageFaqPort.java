package com.clanone.onedayclan.customer.application.port.out;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.FaqEntity;

public interface ManageFaqPort {
    FaqEntity saveFaq(FaqEntity entity);
    void deleteFaq(long faqSeq);
}
