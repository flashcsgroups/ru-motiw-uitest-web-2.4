package ru.motiw.web.model.Administration.TasksTypes;

/**
 * Перечисления настроек полей для типа объектов - Справочник; Типы задач; Типы таблиц;
 */
public enum SettingsForTaskTypeListFields {

    VALUE_IS_NOT_DEFINED("Значение не задано"), NO("Нет"), YES("Да"), OBJECT_LINK("Задача");

    private String nameOfTheEnumerationValues;

    SettingsForTaskTypeListFields(String nameOfTheEnumerationValues) {
        this.nameOfTheEnumerationValues = nameOfTheEnumerationValues;
    }

    public String getNameOfTheEnumerationValues() {
        return this.nameOfTheEnumerationValues;
    }
}

