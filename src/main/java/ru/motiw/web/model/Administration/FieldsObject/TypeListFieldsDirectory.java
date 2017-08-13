package ru.motiw.web.model.Administration.FieldsObject;

import ru.motiw.web.model.Administration.Directories.Directories;

/**
 * Модель объекта системы - Тип поля "Ссылка на справочник"
 */
public class TypeListFieldsDirectory extends FieldObject {

    private String nameDirectory = "";
    private String nameDirectoryField = "";
    private Directories directories;
    private FieldObject field;
    private FieldObject linkedWithField;
    private String displayNameTemplate = "";


    /**
     * Название спр-ка
     */
    public String getDirectoryName() {
        return nameDirectory;
    }

    public TypeListFieldsDirectory setDirectoryName(String nameDirectory) {
        this.nameDirectory = nameDirectory;
        return this;
    }

    /**
     * Название поля спр-ка
     */
    public String getNameDirectoryField() {
        return nameDirectoryField;
    }

    public TypeListFieldsDirectory setNameDirectoryField(String nameDirectoryField) {
        this.nameDirectoryField = nameDirectoryField;
        return this;
    }

    /**
     * Справочник
     */
    public Directories getDirectories() {
        return directories;
    }

    public TypeListFieldsDirectory setDirectories(Directories directories) {
        this.directories = directories;
        return this;
    }

    /**
     * Поле спр-ка
     */
    public FieldObject getFieldDirectory() {
        return field;
    }

    public TypeListFieldsDirectory setFieldDirectory(FieldObject field) {
        this.field = field;
        return this;
    }

    /**
     * Связан с полем
     */
    public FieldObject getLinkedWithField() {
        return linkedWithField;
    }

    public TypeListFieldsDirectory setLinkedWithField(FieldObject linkedWithField) {
        this.linkedWithField = linkedWithField;
        return this;
    }

    /**
     * Шаблон отображения
     */
    public String getDisplayNameTemplate() {
        return displayNameTemplate;
    }

    public TypeListFieldsDirectory setDisplayNameTemplate(String displayNameTemplate) {
        this.displayNameTemplate = displayNameTemplate;
        return this;
    }



}
