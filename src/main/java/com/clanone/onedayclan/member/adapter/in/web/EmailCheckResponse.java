package com.clanone.onedayclan.member.adapter.in.web;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmailCheckResponse {
    private boolean available;
}
