package ru.motiw.web.model;

/**
 * Направление смещения даты при попадании на нерабочее время.
 * Значения: "Дата не меняется", "Дата сдвигается вперед", "Дата сдвигается назад"
 */
public enum ShiftDirection {
    DATE_DOES_NOT_MOVE, DATE_MOVES_FORWARD, DATE_MOVES_BACK;
}
