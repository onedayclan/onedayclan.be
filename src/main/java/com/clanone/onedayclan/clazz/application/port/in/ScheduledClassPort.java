package com.clanone.onedayclan.clazz.application.port.in;

import com.clanone.onedayclan.clazz.adapter.in.web.response.ScheduledClassInfoResponse;
import com.clanone.onedayclan.clazz.adapter.in.web.response.ScheduledClassResponse;

import java.util.List;

public interface ScheduledClassPort {
    ScheduledClassInfoResponse getScheduledClassInfo(String userId);
    List<ScheduledClassResponse> getScheduledClassList(String userId);
}
