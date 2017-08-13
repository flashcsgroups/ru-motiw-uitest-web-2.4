package ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards;

/**
 * Настройки изменения признака "Окончательная версия".
 * Значения - "Значения не задано", "Нет", "Да"
 */
public enum SettingsFinalVersion {


    VALUE_IS_NOT_DEFINED("Значение не выбрано"), NO("Нет"), YES("Да");

    private String nameOfTheEnumerationValues;

    private SettingsFinalVersion(String nameOfTheEnumerationValues) {
        this.nameOfTheEnumerationValues = nameOfTheEnumerationValues;
    }

    public String getNameOfTheEnumerationValues() {
        return this.nameOfTheEnumerationValues;
    }

}
