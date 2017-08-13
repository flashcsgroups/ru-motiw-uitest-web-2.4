package ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards;

/**
 * Модель объекта системы - Тип поля документа "Дата"
 */
public class FieldTypeDateDocument extends FieldDocument {

    private SettingsForDocumentFields defaultValue;
    private boolean editionAvailableWhileCreation;


    /**
     * Значение по умолчанию
     *
     * @return FieldTypeDateDocument
     */
    public SettingsForDocumentFields getDefaultValue() {
        return defaultValue;
    }

    public FieldTypeDateDocument setDefaultValue(SettingsForDocumentFields defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    /**
     * Изменяемое при создании
     *
     * @return FieldTypeDateDocument
     */
    public boolean getEditionAvailableWhileCreation() {
        return editionAvailableWhileCreation;
    }

    public FieldTypeDateDocument setEditionAvailableWhileCreation(boolean editionAvailableWhileCreation) {
        this.editionAvailableWhileCreation = editionAvailableWhileCreation;
        return this;
    }


}
