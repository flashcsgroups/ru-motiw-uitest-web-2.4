package ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards;

/**
 * Модель объекта системы - Тип поля документа "Документ"
 */
public class FieldTypeDocumentDocument extends FieldDocument {

    private String displayNameTemplate = "";
    private boolean searchSimiliarDocuments;
    private String searchRules = "";


    /**
     * Шаблон отображения
     *
     * @return
     */
    public String getDisplayNameTemplate() {
        return displayNameTemplate;
    }

    public FieldTypeDocumentDocument setDisplayNameTemplate(String displayNameTemplate) {
        this.displayNameTemplate = displayNameTemplate;
        return this;
    }

    /**
     * Искать похожие документы
     *
     * @return
     */
    public boolean getSearchSimiliarDocuments() {
        return searchSimiliarDocuments;
    }

    public FieldTypeDocumentDocument setSearchSimiliarDocuments(boolean searchSimiliarDocuments) {
        this.searchSimiliarDocuments = searchSimiliarDocuments;
        return this;
    }

    /**
     * Правила поиска
     *
     * @return
     */
    public String getSearchRules() {
        return searchRules;
    }

    public FieldTypeDocumentDocument setSearchRules(String searchRules) {
        this.searchRules = searchRules;
        return this;
    }


}
