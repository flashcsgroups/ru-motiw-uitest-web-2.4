package ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards;

/**
 * Модель объекта системы - Тип поля документа "Нумератор"
 */
public class FieldTypeNumeratorDocument extends FieldDocument {

    private String numeratorTemplateDoc = "";
    private boolean editionAvailableWhileCreation;
    private String counterTemplate = "";

    /**
     * Поле - Шаблон нумератора документа
     */
    public String getNumeratorTemplateDoc() {
        return numeratorTemplateDoc;
    }

    public FieldTypeNumeratorDocument setNumeratorTemplateDoc(
            String numeratorTemplateDoc) {
        this.numeratorTemplateDoc = numeratorTemplateDoc;
        return this;
    }

    /**
     * Изменяемое при создании
     */
    public boolean getEditionAvailableWhileCreation() {
        return editionAvailableWhileCreation;
    }

    public FieldTypeNumeratorDocument setEditionAvailableWhileCreation(boolean editionAvailableWhileCreation) {
        this.editionAvailableWhileCreation = editionAvailableWhileCreation;
        return this;
    }

    /**
     * Шаблон счетчика
     */
    public String getCounterTemplate() {
        return counterTemplate;
    }

    public FieldTypeNumeratorDocument setCounterTemplate(String counterTemplate) {
        this.counterTemplate = counterTemplate;
        return this;
    }


}
