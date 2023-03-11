package com.clanone.onedayclan.customer.application.port.in;

import com.clanone.onedayclan.customer.adapter.in.web.response.TermResponse;

import java.util.List;

public interface TermPort {
    List<TermResponse> getTerm();
}
