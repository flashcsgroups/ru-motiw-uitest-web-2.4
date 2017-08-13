package ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards;


import ru.motiw.web.model.DocflowAdministration.DictionaryEditor.DictionaryEditor;

/**
 * Модель объекта системы - Тип поля документа "Словарь"
 */
public class FieldTypeDictionaryDocument extends FieldDocument {

    private DictionaryEditor dictionaryEditor;


    /**
     * Выбор объекта Словарь
     *
     */
    public DictionaryEditor getDictionaryEditor() {
        return dictionaryEditor;
    }

    public FieldTypeDictionaryDocument setDictionaryEditor(DictionaryEditor dictionaryEditor) {
        this.dictionaryEditor = dictionaryEditor;
        return this;
    }

}
