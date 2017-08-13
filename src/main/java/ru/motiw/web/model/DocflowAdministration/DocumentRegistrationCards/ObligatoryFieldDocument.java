package ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards;

/**
 * Настройки перечисления признаков поля - "Обязательное поле"
 * Значения - "Необязательное", "Обязательное при создании", "Обязательное при переводе на исполнение"
 */
public enum ObligatoryFieldDocument {

    OPTIONAL("Необязательное"), REQUIRED_WHEN_CREATION("Обязательное при создании"),
    REQUIRED_WHEN_SENDING_TO_EXECUTION("Обязательное при переводе на исполнение"), AUTO_CALCULATED("Автовычисляемое");


    private String nameOfTheEnumerationValues;

    private ObligatoryFieldDocument(String nameOfTheEnumerationValues) {
        this.nameOfTheEnumerationValues = nameOfTheEnumerationValues;
    }

    public String getNameOfTheEnumerationValues() {
        return this.nameOfTheEnumerationValues;
    }

}
