package ru.motiw.web.model;

/**
 * Корректировка даты
 * Значения: "Не корректировать", "Установить время", "Сместить в рабочем интервале", "Сместить в периоде"
 */
public enum CorrectionMethod {

    DO_NOT_ADJUST("Не корректировать"), SET_TIME("Установить время"), OFFSET_IN_THE_INTERVAL("Сместить в рабочем интервале"),
    OFFSET_IN_THE_PERIOD("Сместить в периоде");

    private String nameOfTheEnumerationValues;


    CorrectionMethod(String nameOfTheEnumerationValues) {
        this.nameOfTheEnumerationValues = nameOfTheEnumerationValues;
        return;
    }

    public String getNameOfTheEnumerationValues() {
        return nameOfTheEnumerationValues;
    }

}
