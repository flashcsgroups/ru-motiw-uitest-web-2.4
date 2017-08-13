package ru.motiw.web.model.Administration.TasksTypes;

/**
 * Перечисление признаков обязательности поля (Обязательное)
 * Значения - Необязательное, Обязательное при создании, Обязательное завершении, Обязательное при отправке доклада,
 * Обязательное при завершении и отправке доклада, Да, Нет
 *
 * Используется для объектов - Типы задач, Типы таблиц, Справочники и etc...
 */
public enum ObligatoryField {

    NOT_OBLIGATORY("Необязательное"), OBLIGATORY_ON_CREATE("Обязательное при создании"), OBLIGATORY_ON_CLOSE("Обязательное при завершении"),
    REQUIRED_WHEN_SENDING_THE_REPORT("Обязательное при отправке доклада"), MANDATORY_WHEN_COMPLETING_AND_SUBMITTING_THE_REPORT("Обязательное при завершении и отправке доклада"),
    YES("Да"), NO("Нет");

    private String nameOfTheEnumerationValues;

    ObligatoryField(String nameOfTheEnumerationValues) {
        this.nameOfTheEnumerationValues = nameOfTheEnumerationValues;
    }

    public String getNameOfTheEnumerationValues() {
        return this.nameOfTheEnumerationValues;
    }
}
