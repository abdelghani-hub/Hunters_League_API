package com.youcode.hunters_league.utils;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

public class LocalDateTimeUtil {
    public static LocalDateTime getStartOfWeek(LocalDateTime date) {
        return date.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    public static LocalDateTime getEndOfWeek(LocalDateTime date) {
        return date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))
                .withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999999999);
    }
}
