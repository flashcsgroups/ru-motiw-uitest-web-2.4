package ru.motiw.web.model.Document;

import ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards.FieldDocument;
import ru.motiw.web.model.DocflowAdministration.DictionaryEditor.DictionaryEditorField;
import ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards.DocRegisterCards;
import ru.motiw.web.model.DocflowAdministration.RouteSchemeEditor.RouteSchemeEditor;
import ru.motiw.web.model.Tasks.Project;
import ru.motiw.web.model.Administration.Users.Department;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Tasks.Task;

/**
 * Модель объекта - Документ (форма создания / редактирования документа)
 */
public class Document {
    public ExecutionOfDocument[] executionOfDocument;
    private boolean onExecution = false;
    private DocRegisterCards documentType;
    private FieldDocument[] docFields;
    private Employee authorOfDocument;
    private String dateRegistration = "";
    private Project project;
    private Department[] valueDepartment;
    private Employee[] valueEmployee;
    private String valueField = "";
    private String[] valueFiles;
    private DictionaryEditorField valueDictionaryEditor;
    private RouteSchemeEditor routeScheme;
    private Integer numberOfFiles;
    private Task[] resolutionOfDocument;

    /**
     * Тип документа
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
     */
    public String getValueField() {
        return valueField;
    }

    public Document setValueField(String valueField) {
        this.valueField = valueField;
        return this;
    }

    /**
     * Значения в системном поля типа "Файл"
     */

    public String[] getValueFiles() {
        return valueFiles;
    }

    public Document setValueFiles(String[] valueFiles) {
        this.valueFiles = valueFiles;
        return this;
    }


    /**
     * Название - Маршрутной схемы
     */
    public RouteSchemeEditor getRouteSchemeForDocument() {
        return routeScheme;
    }

    public Document setRouteSchemeForDocument(RouteSchemeEditor routeScheme) {
        this.routeScheme = routeScheme;
        return this;
    }

    /**
     * АВТОР ДОКУМЕНТА
     */
    public Employee getAuthorOfDocument() {
        return authorOfDocument;
    }

    public Document setAuthorOfDocument(Employee authorOfDocument) {
        this.authorOfDocument = authorOfDocument;
        return this;
    }

    /**
     * Резолюция документа
     * Основные атрибуты резолюции являются частью объекта Task, но также для резолюции могут быть специфические атрибуты. см. класс Resolution
     *
     * @return
     */
    public Task[] getResolutionOfDocument() {
        return resolutionOfDocument;
    }

    public Document setResolutionOfDocument(Task[] resolutionOfDocument) {
        this.resolutionOfDocument = resolutionOfDocument;
        return this;
    }

    /**
     * Кол-во прикрепленных файлов
     * При каждом обращении к методу будет считаться кол-во прикрепляемых файлов
     */
    public int getNumberOfFiles() {
        if (numberOfFiles == null) {
            setNumberOfFiles();
        }
        return numberOfFiles;
    }

    private void setNumberOfFiles() {

        int numberOfFile = 0;
        for (String s : getValueFiles()) {
            numberOfFile++;
        }

        this.numberOfFiles = numberOfFile;
    }


    /**
     * Выполняемые операции в карточке документа
     *
     * @return
     */
    public ExecutionOfDocument[] getExecutionOfDocument() {
        return executionOfDocument;
    }

    public Document setExecutionOfDocument(ExecutionOfDocument[] executionOfDocument) {
        this.executionOfDocument = executionOfDocument;
        return this;
    }

    /**
     * Этап Документа
     * на рассмотрении - false
     * на исполнении - true
     *
     * @return
     */
    public boolean isOnExecution() {
        return onExecution;
    }

    public Document setOnExecution(boolean onExecution) {
        this.onExecution = onExecution;
        return this;
    }
}
