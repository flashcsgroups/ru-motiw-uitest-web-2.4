package ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards;

/**
 *  Перечисление -Автоматическое вычисление полей нумератора
 *
 *  Значения: ДА, НЕТ, Значение не задано
 */
public enum AutoCalculationOfNumeratorFields {

    VALUE_IS_NOT_DEFINED("Значение не задано"), NO("Нет"), YES("Да");

    public String nameOfTheEnumerationValues;

    AutoCalculationOfNumeratorFields(String value) {
        this.nameOfTheEnumerationValues = value;
    }

}
