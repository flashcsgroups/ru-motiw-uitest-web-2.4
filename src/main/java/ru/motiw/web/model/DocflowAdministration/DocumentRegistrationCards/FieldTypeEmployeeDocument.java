package ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards;

/**
 * Модель объекта системы - Тип поля документа "Сотрудник"
 */
public class FieldTypeEmployeeDocument extends FieldDocument {

    private SettingsForDocumentFields defaultValue;
    private boolean editionAvailableWhileCreation;
    private boolean documentSuperviser;
    private boolean forInformation;


    /**
     * Значение по умолчанию;
     *
     */
    public SettingsForDocumentFields getDefaultValue() {
        return defaultValue;
    }

    public FieldTypeEmployeeDocument setDefaultValue(SettingsForDocumentFields defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    /**
     * Изменяемое при создании
     *
     */
    public boolean getEditionAvailableWhileCreation() {
        return editionAvailableWhileCreation;
    }

    public FieldTypeEmployeeDocument setEditionAvailableWhileCreation(boolean editionAvailableWhileCreation) {
        this.editionAvailableWhileCreation = editionAvailableWhileCreation;
        return this;
    }

    /**
     * Контролер документа
     *
     */
    public boolean getDocumentSuperviser() {
        return documentSuperviser;
    }

    public FieldTypeEmployeeDocument setDocumentSuperviser(boolean documentSuperviser) {
        this.documentSuperviser = documentSuperviser;
        return this;
    }

    /**
     * Для сведения
     *
     */
    public boolean getForInformation() {
        return forInformation;
    }

    public FieldTypeEmployeeDocument setForInformation(boolean forInformation) {
        this.forInformation = forInformation;
        return this;
    }


}
