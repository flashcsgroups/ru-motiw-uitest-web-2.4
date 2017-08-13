package ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards;


import ru.motiw.web.model.Administration.Directories.Directories;

/**
 * Модель объекта системы - Тип поля документа "Справочник"
 */
public class FieldTypeDirectoryDocument extends FieldDocument {

    private String nameDirectory = "";
    private Directories directories;
    private String directoryTemplate = "";
    private SettingsForDocumentFields directoryEntriesSelection;


    /**
     * Название спр-ка
     */
    public String getNameDirectoryDoc() {
        return nameDirectory;
    }

    public FieldTypeDirectoryDocument setNameDirectoryDoc(String nameDirectory) {
        this.nameDirectory = nameDirectory;
        return this;
    }

    /**
     * Справочник
     */
    public Directories getDirectoryDoc() {
        return directories;
    }

    /**
     * Шаблон справочника
     */
    public String getDirectoryTemplate() {
        return directoryTemplate;
    }

    public FieldTypeDirectoryDocument setDirectoryTemplate(String directoryTemplate) {
        this.directoryTemplate = directoryTemplate;
        return this;
    }

    /**
     * Выбор записей спр-ка
     *
     * @return значение перечисления настроек полей документа
     */
    public SettingsForDocumentFields getDirectoryEntriesSelection() {
        return directoryEntriesSelection;
    }

    public FieldTypeDirectoryDocument setDirectoryEntriesSelection(SettingsForDocumentFields directoryEntriesSelection) {
        this.directoryEntriesSelection = directoryEntriesSelection;
        return this;
    }


}
