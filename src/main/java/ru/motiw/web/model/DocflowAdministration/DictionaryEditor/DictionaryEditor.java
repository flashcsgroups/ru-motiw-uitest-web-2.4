package ru.motiw.web.model.DocflowAdministration.DictionaryEditor;


import ru.motiw.web.model.AccessRights;

/**
 * Модель объекта - Редактор словарей
 */
public class DictionaryEditor {


    private String dictionaryEditorName = "";
    private AccessRights accessDiction;
    private DictionaryEditorField[] dictionaryEditorFields;


    /**
     * Конструктор - Словарь
     * @param dictionaryEditorName
     * @param accessDiction
     * @param dictionaryEditorFields
     */
    public DictionaryEditor(String dictionaryEditorName, AccessRights accessDiction, DictionaryEditorField[] dictionaryEditorFields) {
        this.dictionaryEditorName = dictionaryEditorName;
        this.accessDiction = accessDiction;
        this.dictionaryEditorFields = dictionaryEditorFields;
    }

    /**
     * Название Словаря
     *
     */
    public String getDictionaryEditorName() {
        return dictionaryEditorName;
    }

    public DictionaryEditor setDictionaryEditorName(
            String dictionaryEditorName) {
        this.dictionaryEditorName = dictionaryEditorName;
        return this;
    }

    /**
     * Права доступа к Словарю
     *
     */
    public AccessRights getAccessDiction() {
        return accessDiction;
    }

    public DictionaryEditor setAccessDiction(AccessRights accessDiction) {
        this.accessDiction = accessDiction;
        return this;
    }

    /**
     * Массив полей - Редактора словарей
     *
     */
    public DictionaryEditorField[] getDictionaryEditorFields() {
        return dictionaryEditorFields;
    }

    public DictionaryEditor setDictionaryEditorFields(DictionaryEditorField[] dictionaryEditorFields) {
        this.dictionaryEditorFields = dictionaryEditorFields;
        return this;
    }


}
