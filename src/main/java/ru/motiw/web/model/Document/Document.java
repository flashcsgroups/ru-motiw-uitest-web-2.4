package ru.motiw.web.model.Document;

import ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards.FieldDocument;
import ru.motiw.web.model.DocflowAdministration.DictionaryEditor.DictionaryEditorField;
import ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards.DocRegisterCards;
import ru.motiw.web.model.DocflowAdministration.RouteSchemeEditor.RouteSchemeEditor;
import ru.motiw.web.model.Tasks.Project;
import ru.motiw.web.model.Administration.Users.Department;
import ru.motiw.web.model.Administration.Users.Employee;

/**
 * Модель объекта - Документ (форма создания / редактирования документа)
 */
public class Document {

    private DocRegisterCards documentType;
    private FieldDocument[] docFields;
    private String dateRegistration = "";
    private Project project;
    private Department[] valueDepartment;
    private Employee[] valueEmployee;
    private String valueField = "";
    private DictionaryEditorField valueDictionaryEditor;
    private RouteSchemeEditor routeScheme;


    /**
     * Тип документа
     *
     */
    public DocRegisterCards getDocumentType() {
        return documentType;
    }

    public Document setDocumentType(DocRegisterCards documentType) {
        this.documentType = documentType;
        return this;
    }

    /**
     * Системное поле - Дата регистрации
     */
    public String getDateRegistration() {
        return dateRegistration;
    }

    public Document setDateRegistration(String dateRegistration) {
        this.dateRegistration = dateRegistration;
        return this;
    }

    /**
     * Проект документа
     *
     */
    public Project getProject() {
        return project;
    }

    public Document setProject(Project project) {
        this.project = project;
        return this;
    }

    /**
     * Поля Типа документа
     *
     */
    public FieldDocument[] getDocumentFields() {
        return docFields;
    }

    public Document setDocumentFields(FieldDocument[] docFields) {
        this.docFields = docFields;
        return this;
    }

    /**
     * Значение для поля типа - "Словарь"
     *
     */
    public DictionaryEditorField getValueDictionaryEditor() {
        return valueDictionaryEditor;
    }

    public Document setValueDictionaryEditor(DictionaryEditorField valueDictionaryEditor) {
        this.valueDictionaryEditor = valueDictionaryEditor;
        return this;
    }

    /**
     * Значение для поля типа - "Подразделение"
     *
     */
    public Department[] getValueDepartment() {
        return valueDepartment;
    }

    public Document setValueDepartment(Department[] valueDepartment) {
        this.valueDepartment = valueDepartment;
        return this;
    }

    /**
     * Значение для поля типа - "Сотрудник"
     *
     */
    public Employee[] getValueEmployee() {
        return valueEmployee;
    }

    public Document setValueEmployee(Employee[] valueEmployee) {
        this.valueEmployee = valueEmployee;
        return this;
    }

    /**
     * Значения поля документа
     *
     */
    public String getValueField() {
        return valueField;
    }

    public Document setValueField(String valueField) {
        this.valueField = valueField;
        return this;
    }

    /**
     * Название - Маршрутной схемы
     *
     */
    public RouteSchemeEditor getRouteSchemeForDocument() {
        return routeScheme;
    }

    public Document setRouteSchemeForDocument(RouteSchemeEditor routeScheme) {
        this.routeScheme = routeScheme;
        return this;
    }

}
