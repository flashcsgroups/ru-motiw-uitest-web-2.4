package ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards;

/**
 * Настройки - Редактирование своих документов
 * значения: "Значения не задано", "Нет", "Пользователи с правами инициирования",
 * "Пользователи с правами создания связанных документов"
 */
public enum CreationOfLinkedDocuments {

    VALUE_IS_NOT_SELECTED("Значение не выбрано"), NO("Нет"), USERS_WITH_RIGHT("Пользователи с правами инициирования"),
    USERS_WITH_RIGHT_LINKED_DOCUMENTS_CREATION("Пользователи с правами создания связанных документов");

    private String nameOfTheEnumerationValues;

    CreationOfLinkedDocuments(String nameOfTheEnumerationValues) {
        this.nameOfTheEnumerationValues = nameOfTheEnumerationValues;
    }

    public String getNameOfTheEnumerationValues() {
        return this.nameOfTheEnumerationValues;
    }


}
