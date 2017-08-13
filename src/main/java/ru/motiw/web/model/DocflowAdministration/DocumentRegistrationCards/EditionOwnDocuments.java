package ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards;

/**
 * Настройки - Редактирование своих документов
 * значения: "Значения не выбрано", "Нет", "Да", "Пользователи с правами редактирования своих документов"
 */
public enum EditionOwnDocuments {

    VALUE_IS_NOT_DEFINED("Значение не выбрано"), NO("Нет"), YES("Да"),
    USER_RIGHT_EDIT_THEIR_DOCUMENTS("Пользователи с правами редактирования своих документов");

    private String nameOfTheEnumerationValues;

    private EditionOwnDocuments(String nameOfTheEnumerationValues) {
        this.nameOfTheEnumerationValues = nameOfTheEnumerationValues;
    }

    public String getNameOfTheEnumerationValues() {
        return this.nameOfTheEnumerationValues;
    }

}
