package ru.motiw.web.model.Administration;


import ru.motiw.web.model.Administration.FieldsObject.FieldObject;

/**
 * Грид объектов - Справочники / Типы задач / Типы объектов
 */
public abstract class TaskTypeListEditObject {

    private String nameTableType;
    private FieldObject[] typesOfTablesFields;


    public TaskTypeListEditObject(String nameTableType) {
        this.nameTableType = nameTableType;
    }

    /**
     * Название - Объекта системы
     */
    public String getObjectTypeName() {
        return nameTableType;
    }

    /**
     * Поля - массив объектов
     */
    public FieldObject[] getObjectTypeFields() {
        return typesOfTablesFields;
    }

    public TaskTypeListEditObject setTaskTypeListObjectFields(FieldObject[] typesOfTablesFields) {
        this.typesOfTablesFields = typesOfTablesFields;
        return this;
    }


}
