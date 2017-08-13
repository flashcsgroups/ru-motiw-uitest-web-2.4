package ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards;


import ru.motiw.web.model.Administration.Directories.Directories;

/**
 * Модель объекта системы - Тип поля документа "Текст"
 */
public class FieldTypeTextDocument extends FieldDocument {

    private String nameDirectory;
    private Directories directories;
    private String directoryTemplate;
    private SettingsForDocumentFields selectOnlyFromDictionary;


    /**
     * Название спр-ка - в поле типа "Текст"
     *
     */
    public String getDirectoryName() {
        return nameDirectory;
    }

    public FieldTypeTextDocument setDirectoryName(String nameDirectory) {
        this.nameDirectory = nameDirectory;
        return this;
    }

    /**
     * Справочник
     *
     */
    public Directories getDirectoryForFieldText() {
        return directories;
    }

    public FieldTypeTextDocument setDirectoryForFieldText(Directories directories) {
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

    public FieldTypeTextDocument setDirectoryTemplate(String directoryTemplate) {
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

    public FieldTypeTextDocument setSelectOnlyFromDictionary(SettingsForDocumentFields selectOnlyFromDictionary) {
        this.selectOnlyFromDictionary = selectOnlyFromDictionary;
        return this;
    }


}
