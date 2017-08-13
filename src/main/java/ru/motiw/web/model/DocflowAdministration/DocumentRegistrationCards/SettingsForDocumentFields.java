package ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards;

/**
 * Настройки для полей документа
 */
public enum SettingsForDocumentFields {


    YES("Да"), NO("Нет"), SINGLE_RECORD("Одна запись"), MULTIPLE_RECORDS("Несколько записей"), CURRENT_USER("Текущий пользователь"),
    CURRENT_DATE("Текущая дата");

    private String nameOfTheEnumerationValues;

    private SettingsForDocumentFields(String nameOfTheEnumerationValues) {
        this.nameOfTheEnumerationValues = nameOfTheEnumerationValues;
    }

    public String getNameOfTheEnumerationValues() {
        return this.nameOfTheEnumerationValues;
    }

}
