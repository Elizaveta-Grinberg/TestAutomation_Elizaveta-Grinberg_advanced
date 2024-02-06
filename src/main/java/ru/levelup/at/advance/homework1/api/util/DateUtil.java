package ru.levelup.at.advance.homework1.api.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtil {

    public static String formatDate(LocalDateTime date, String pattern) {
        var dtf = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
        return dtf.format(date);
    }

    public static String getDateAfterNow(int plusDays) {
        return LocalDate.now().plusDays(plusDays).toString();
    }

    public static String getDateBeforeNow(int minusDays) {
        return LocalDate.now().minusDays(minusDays).toString();
    }
}
