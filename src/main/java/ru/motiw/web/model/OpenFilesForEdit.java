package ru.motiw.web.model;

/**
 *  Перечисление возможности онлайн редактирования файлов
 *
 *  Значения: ДА, НЕТ, Значение не задано
 */
public enum OpenFilesForEdit {

    VALUE_IS_NOT_DEFINED("Значение не задано"), VALUE_IS_NOT_SELECTED("Значение не выбрано"), YES ("Да"), NO("Нет");


    private String nameOfTheEnumerationValues;

    OpenFilesForEdit(String nameOfTheEnumerationValues) {
        this.nameOfTheEnumerationValues = nameOfTheEnumerationValues;
    }

    public String getNameOfTheEnumerationValues() {
        return nameOfTheEnumerationValues;
    }


}