package ru.motiw.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;

public class GenericDate {

    // GET DATE & TIME IN ANY FORMAT

    public static final String DATE_FORMAT_NOW = "dd.MM.yyyy HH:mm:ss";
    public static final String DATE_FORMAT_NOW_UNDERSCORE = "dd_MM_yyyy_HH_mm_ss";
    public static final String TIME_FORMAT_HOUR_NOW = "HH";
    public static final String DATE_FORMAT_WITHOUT_TIME = "dd.MM.yyyy";
    public static final Calendar cal = Calendar.getInstance();
    public static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
    public static final SimpleDateFormat sdfWithUnderScore = new SimpleDateFormat(DATE_FORMAT_NOW_UNDERSCORE);
    public static final SimpleDateFormat timeFormatWithoutDate = new SimpleDateFormat(TIME_FORMAT_HOUR_NOW);
    public static final SimpleDateFormat dateFormatWithoutTime = new SimpleDateFormat(DATE_FORMAT_WITHOUT_TIME);

    /**
     * Метод создания даты равной сейчас (Текущая дата)
     */
    public static String nowDate() {
        return sdf.format(cal.getTime());
    }

    /**
     * Метод создания даты равной сейчас (Текущая дата)
     */
    public String nowDateWithUnderScore() {
        return sdfWithUnderScore.format(Date.from(Instant.now()));
    }

    /**
     * Метод создания даты равной сейчас (Текущее время в формате HH)
     */
    public static String nowHourTime() {
        return timeFormatWithoutDate.format(cal.getTime());
    }


    /**
     * Метод создания даты равной завтра
     */
    public static String tomorrowDate() {
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return sdf.format(cal.getTime());
    }

    /**
     * Метод создания даты равной вчера
     */
    public static String yesterdayDate() {
        cal.add(Calendar.DAY_OF_MONTH, -2);
        return dateFormatWithoutTime.format(cal.getTime());
    }

    /**
     * Метод создания даты равной завтра в формате без указания времени - dd.MM.yyyy
     */

    public static  String tomorrowDateWithoutTime() {
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return dateFormatWithoutTime.format(cal.getTime());

    }


}
