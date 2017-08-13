package ru.motiw.web.model.Administration.FieldsObject;

import ru.motiw.web.model.Administration.TasksTypes.SettingsForTaskTypeListFields;

/**
 * Модель объекта системы - Тип поля "Целое"
 */

public class TypeListFieldsInteger extends FieldObject {

    private SettingsForTaskTypeListFields objectLink;
    private boolean formatNumber;


    /**
     * Ссылка на объект; true == Задача; false == Нет
     */
    public SettingsForTaskTypeListFields getObjectLink() {
        return objectLink;
    }

    public TypeListFieldsInteger setObjectLink(SettingsForTaskTypeListFields objectLink) {
        this.objectLink = objectLink;
        return this;
    }

    /**
     * Формат; true == Другой формат; false == Значение по умолчанию
     */
    public boolean getFormatNumber() {
        return formatNumber;
    }

    public TypeListFieldsInteger setFormatNumber(boolean formatNumber) {
        this.formatNumber = formatNumber;
        return this;
    }

}
