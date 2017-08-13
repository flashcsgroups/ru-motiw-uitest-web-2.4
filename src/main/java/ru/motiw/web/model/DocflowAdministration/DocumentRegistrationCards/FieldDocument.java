package ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards;


import ru.motiw.web.model.Document.Document;

/**
 * Модель объекта системы - Поля Документа
 */
public abstract class FieldDocument extends Document {

    private String documentFieldName = "";
    private String fieldIdentifierDocument = "";
    private ObligatoryFieldDocument obligatoryFieldDocument;
    private boolean editableField;
    private boolean uniqueField;
    private boolean hideForCreationField;
    private boolean hideInTablesField;
    private boolean hideForSearchField;
    private boolean hideInCardField;
    private boolean usedToCreateTheLinkedDocument;
    private FieldDocument fieldDocument;


    /**
     * Название поля документа
     *
     * @return FieldDocument
     */
    public String getDocumentFieldName() {
        return documentFieldName;
    }

    public FieldDocument setDocumentFieldName(String documentFieldName) {
        this.documentFieldName = documentFieldName;
        return this;
    }

    /**
     * Идентификатор поля документа
     *
     * @return FieldDocument
     */
    public String getFieldIdentifierDoc() {
        return fieldIdentifierDocument;
    }

    public FieldDocument setFieldIdentifierDoc(String fieldIDDoc) {
        this.fieldIdentifierDocument = fieldIDDoc;
        return this;
    }

    /**
     * Объект - Тип поля документа
     */
    public FieldDocument getObjectFieldDocument() {
        return fieldDocument;
    }

    public FieldDocument setObjectFieldDocument(FieldDocument fieldDocument) {
        this.fieldDocument = fieldDocument;
        return this;
    }

    /**
     * Изменяемое при редактировании
     *
     * @return FieldDocument
     */
    public boolean getEditableField() {
        return editableField;
    }

    public FieldDocument setEditableField(boolean editableField) {
        this.editableField = editableField;
        return this;
    }

    /**
     * Обязательность поля документа
     *
     * @return FieldDocument
     */
    public ObligatoryFieldDocument getObligatoryFieldDoc() {
        return obligatoryFieldDocument;
    }

    public FieldDocument setObligatoryFieldDoc(ObligatoryFieldDocument obligatoryFieldTypeTask) {
        this.obligatoryFieldDocument = obligatoryFieldTypeTask;
        return this;
    }

    /**
     * Уникальность поля
     *
     * @return FieldDocument
     */
    public boolean getUniqueField() {
        return uniqueField;
    }

    public FieldDocument setUniqueField(boolean uniqueField) {
        this.uniqueField = uniqueField;
        return this;
    }

    /**
     * Скрывать при создании
     *
     * @return FieldDocument
     */
    public boolean getHideForCreationField() {
        return hideForCreationField;
    }

    public FieldDocument setHideForCreationField(boolean hideForCreationField) {
        this.hideForCreationField = hideForCreationField;
        return this;
    }

    /**
     * Скрывать в таблицах
     *
     * @return FieldDocument
     */
    public boolean getHideInTablesField() {
        return hideInTablesField;
    }

    public FieldDocument setHideInTablesField(boolean hideInTablesField) {
        this.hideInTablesField = hideInTablesField;
        return this;
    }


    /**
     * Скрывать при поиске
     *
     * @return FieldDocument
     */
    public boolean getHideForSearchField() {
        return hideForSearchField;
    }

    public FieldDocument setHideForSearchField(boolean hideForSearchField) {
        this.hideForSearchField = hideForSearchField;
        return this;
    }

    /**
     * Скрывать в карточке
     *
     * @return FieldDocument
     */
    public boolean getHideInCardField() {
        return hideInCardField;
    }

    public FieldDocument setHideInCardField(boolean hideInCardField) {
        this.hideInCardField = hideInCardField;
        return this;
    }

    /**
     * Использовать при создании связанного документа
     *
     * @return FieldDocument
     */
    public boolean getUsedToCreateTheLinkedDocument() {
        return usedToCreateTheLinkedDocument;
    }

    public FieldDocument setUsedToCreateTheLinkedDocument(boolean usedToCreateTheLinkedDocument) {
        this.usedToCreateTheLinkedDocument = usedToCreateTheLinkedDocument;
        return this;
    }




}
