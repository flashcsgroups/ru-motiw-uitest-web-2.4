package ru.motiw.web.model.Administration.FieldsObject;



/**
 * Модель объекта системы - Тип поля "Множественная ссылка на справочник"
 */
public class TypeListFieldsMultiDirectory extends FieldObject {

    private String nameDirectory = "";
    private String nameDirectoryField = "";
    private FieldObject field;
    private FieldObject linkedWithField;
    private String displayNameTemplate = "";

    /**
     * Поле спр-ка
     */
    public FieldObject getFieldDirectory() {
        return field;
    }

    public TypeListFieldsMultiDirectory setFieldDirectory(FieldObject field) {
        this.field = field;
        return this;
    }

    /**
     * Связан с полем
     */
    public FieldObject getLinkedWithField() {
        return linkedWithField;
    }

    public TypeListFieldsMultiDirectory setLinkedWithField(FieldObject linkedWithField) {
        this.linkedWithField = linkedWithField;
        return this;
    }

    /**
     * Шаблон отображения
     */
    public String getDisplayNameTemplate() {
        return displayNameTemplate;
    }

    public TypeListFieldsMultiDirectory setDisplayNameTemplate(String displayNameTemplate) {
        this.displayNameTemplate = displayNameTemplate;
        return this;
    }

    /**
     * Название спр-ка
     */
    public String getDirectoryName() {
        return nameDirectory;
    }

    public TypeListFieldsMultiDirectory setDirectoryName(String nameDirectory) {
        this.nameDirectory = nameDirectory;
        return this;
    }

    /**
     * Название поля спр-ка
     */
    public String getNameDirectoryField() {
        return nameDirectoryField;
    }

    public TypeListFieldsMultiDirectory setNameDirectoryField(String nameDirectoryField) {
        this.nameDirectoryField = nameDirectoryField;
        return this;
    }


}
