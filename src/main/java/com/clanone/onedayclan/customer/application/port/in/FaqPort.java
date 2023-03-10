package com.clanone.onedayclan.customer.application.port.in;

import com.clanone.onedayclan.customer.adapter.in.web.response.FaqResponse;

import java.util.List;

public interface FaqPort {
    List<FaqResponse> getFaqList();
}
