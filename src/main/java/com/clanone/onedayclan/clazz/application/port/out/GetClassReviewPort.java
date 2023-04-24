package com.clanone.onedayclan.clazz.application.port.out;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface GetClassReviewPort {
    long getParticipatedClassByUser(long memberSeq, LocalDateTime startAt, LocalDateTime endAt);
}
