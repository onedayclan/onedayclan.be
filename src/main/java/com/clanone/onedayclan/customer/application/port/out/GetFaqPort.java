package com.clanone.onedayclan.customer.application.port.out;

import com.clanone.onedayclan.customer.adapter.in.web.response.FaqResponse;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.FaqEntity;

import java.util.List;

public interface GetFaqPort {
    List<FaqEntity> getFaqList();
}
