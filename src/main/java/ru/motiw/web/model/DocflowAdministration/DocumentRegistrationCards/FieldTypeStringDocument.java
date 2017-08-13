package ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards;


import ru.motiw.web.model.Administration.Directories.Directories;

/**
 * Модель объекта системы - Тип поля документа "Строка"
 */
public class FieldTypeStringDocument extends FieldDocument {

    private String nameDirectory;
    private Directories directories;
    private String directoryTemplate;
    private SettingsForDocumentFields selectOnlyFromDictionary;
    private String fieldLength = "";


    /**
     * Название спр-ка - в поле типа "Строка"
     *
     */
    public String getDirectoryName() {
        return nameDirectory;
    }

    public FieldTypeStringDocument setDirectoryName(String nameDirectory) {
        this.nameDirectory = nameDirectory;
        return this;
    }

    /**
     * Справочник
     *
     */
    public Directories getDirectoryForFieldString() {
        return directories;
    }

    public FieldTypeStringDocument setDirectoryForFieldString(Directories directories) {
        this.directories = directories;
        return this;
    }

    /**
     * Шаблон справочника
     *
     */
    public String getDirectoryTemplate() {
        return directoryTemplate;
    }

    public FieldTypeStringDocument setDirectoryTemplate(String directoryTemplate) {
        this.directoryTemplate = directoryTemplate;
        return this;
    }

    /**
     * Выбор только из справочника
     *
     */
    public SettingsForDocumentFields getSelectOnlyFromDictionary() {
        return selectOnlyFromDictionary;
    }

    public FieldTypeStringDocument setSelectOnlyFromDictionary(SettingsForDocumentFields selectOnlyFromDictionary) {
        this.selectOnlyFromDictionary = selectOnlyFromDictionary;
        return this;
    }

    /**
     * Длина поля
     *
     */
    public String getFieldLength() {
        return fieldLength;
    }

    public FieldTypeStringDocument setFieldLength(String fieldLength) {
        this.fieldLength = fieldLength;
        return this;
    }


}
