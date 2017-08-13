package ru.motiw.web.model.Administration.FieldsObject;

import ru.motiw.web.model.Administration.TasksTypes.SettingsForTaskTypeListFields;

/**
 * Модель объекта системы - Тип поля "Строка"
 */
public class TypeListFieldsString extends FieldObject {

    private SettingsForTaskTypeListFields listChoice;
    private String valuesList = "";


    /**
     * Нужен ли выбор из списка
     */
    public SettingsForTaskTypeListFields getIsListChoice() {
        return listChoice;
    }

    public TypeListFieldsString setIsListChoice(SettingsForTaskTypeListFields listChoice) {
        this.listChoice = listChoice;
        return this;
    }

    /**
     * Поле ввода значений для выбора из списка (Список значений)
     */
    public String getValuesList() {
        return valuesList;
    }

    public TypeListFieldsString setValuesList(String valuesList) {
        this.valuesList = valuesList;
        return this;
    }

}

