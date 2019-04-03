package ru.motiw.web.model.Administration.FieldsObject;

import ru.motiw.web.model.Administration.Directories.DirectoriesField;
import ru.motiw.web.model.Administration.TasksTypes.ObligatoryField;
import ru.motiw.web.model.Administration.TasksTypes.SettingsForTaskTypeListFields;
import ru.motiw.web.model.Administration.Users.Employee;

/**
 * Модель объекта - Поле, для объектов Справочники, Таблицы и Типы задач и etc..
 */
public class FieldObject {


    private String fieldName = "";
    private String fieldID = "";
    private ObligatoryField obligatoryField;
    private boolean hideInSearch;
    private FieldObject field;
    private SettingsForTaskTypeListFields isUniqueField;
    private String valueField = "";
    private Employee[] valueEmployeeField;
    private boolean valueBooleanField;
    private DirectoriesField[] valueDirectoriesField;


    /**
     * Название поля
     */
    public String getFieldName() {
        return fieldName;
    }

    public FieldObject setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    /**
     * Идентификатор поля
     */
    public String getFieldID() {
        return fieldID;
    }

    public FieldObject setFieldID(String fieldID) {
        this.fieldID = fieldID;
        return this;
    }

    /**
     * Тип поля
     */
    public FieldObject getFieldType() {
        return field;
    }

    public FieldObject setFieldType(FieldObject field) {
        this.field = field;
        return this;
    }

    /**
     * Обязательность поля
     * объекты - Типы задач, Типы таблиц, Справочники
     */
    public ObligatoryField getObligatory() {
        return obligatoryField;
    }

    public FieldObject setObligatory(ObligatoryField obligatoryField) {
        this.obligatoryField = obligatoryField;
        return this;
    }

    /**
     * Скрывать при поиске?
     * для объекта - Тип задачи
     */
    public boolean getIsHideInSearch() {
        return hideInSearch;
    }

    public FieldObject setIsHideInSearch(boolean hideInSearch) {
        this.hideInSearch = hideInSearch;
        return this;
    }

    /**
     * Уникальное поле
     */
    public SettingsForTaskTypeListFields getIsUniqueField() {
        return isUniqueField;
    }

    public FieldObject setIsUniqueField(SettingsForTaskTypeListFields isUniqueField) {

        this.isUniqueField = isUniqueField;
        return this;
    }

    /**
     * Значение поля задачи  - любой примитивный тип данных
     */
    public String getValueField() {
        return valueField;
    }

    public FieldObject setValueField(String valueField) {
        this.valueField = valueField;
        return this;
    }


    /**
     * Значение поля задачи  - тип Логический
     */
    public boolean getValueBooleanField() {
        return valueBooleanField;
    }

    public FieldObject setValueBooleanField(Boolean valueBooleanField) {
        this.valueBooleanField = valueBooleanField;
        return this;
    }


    /**
     * Значение поля задачи  - тип Сотрудник
     */
    public Employee[] getValueEmployeeField() {
        return valueEmployeeField;
    }

    public FieldObject setValueEmployeeField(Employee[] employees) {
        this.valueEmployeeField = employees;
        return this;
    }



    /**
     * Значение поля задачи  - тип Справочник
     */
    public DirectoriesField[] getValueDirectoriesField() {
        return valueDirectoriesField;
    }

    public FieldObject setValueDirectoriesField(DirectoriesField[] valueDirectoriesField) {
        this.valueDirectoriesField = valueDirectoriesField;
        return this;
    }



}
