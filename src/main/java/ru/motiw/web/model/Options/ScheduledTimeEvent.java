package ru.motiw.web.model.Options;

/**
 * Время проведения
 * Не повторяется; Каждый день; По рабочим дням (с понедельника по пятницу); По понедельникам, средам и пятницам
 * По вторникам и четвергам; Каждую неделю; Каждый месяц; Каждый год
 */
public enum ScheduledTimeEvent {
    DOESNT_REPAET, EVERY_DAY, ON_WEEKDAY, ON_MONDAYS_WEDNESDAYS_AND_FRIDAYS,
    ON_TUESDAYS_AND_THURSDAYS, EVERY_WEEK, EVERY_MONTH, EVERY_YEAR
}
