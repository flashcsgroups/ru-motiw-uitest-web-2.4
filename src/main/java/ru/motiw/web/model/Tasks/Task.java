package ru.motiw.web.model.Tasks;

import ru.motiw.web.model.Administration.TasksTypes.TasksTypes;
import ru.motiw.web.model.Administration.Users.Department;
import ru.motiw.web.model.Administration.Users.Employee;

/**
 * Модель объекта системы - Задача
 */
public class Task {

    private Department counterDepartment;
    private Project project;
    private TasksTypes taskType;
    private String taskName;
    private String description;
    private boolean important;
    private boolean withReport;
    private boolean secret;
    private boolean forReview;
    private String dateBegin;
    private String dateEnd;
    private Employee[] authors;
    private Employee[] controllers;
    private Employee[] executiveManagers;
    private Employee[] workers;
    private Checkpoint[] checkpoints;
    private IWG[] iwg;
    private Event[] events;
    private Employee[] contacts;
    private Action[] actions;
    private String[] fileName;


    /**
     * Подразделеие нумератора
     */
    public Department getСounterDepartment() {
        return counterDepartment;
    }

    public Task setСounterDepartment(Department counterDepartment) {
        this.counterDepartment = counterDepartment;
        return this;
    }

    /**
     * Проект
     */
    public Project getProject() {
        return project;
    }

    public Task setProject(Project project) {
        this.project = project;
        return this;
    }

    /**
     * Тип задачи
     */
    public TasksTypes getTaskType() {
        return taskType;
    }

    public Task setTaskType(TasksTypes taskType) {
        this.taskType = taskType;
        return this;
    }

    /**
     * Название задачи
     */
    public String getTaskName() {
        return taskName;
    }

    public Task setTaskName(String taskName) {
        this.taskName = taskName;
        return this;
    }

    /**
     * Описание задачи
     */
    public String getDescription() {
        return description;
    }

    public Task setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Важная задача - true Обычная - false
     */
    public boolean getIsImportant() {
        return important;
    }

    public Task setIsImportant(boolean important) {
        this.important = important;
        return this;
    }

    /**
     * C докладом - true Без доклада - false
     */
    public boolean getIsWithReport() {
        return withReport;
    }

    public Task setIsWithReport(boolean withReport) {
        this.withReport = withReport;
        return this;
    }

    /**
     * Секретная задача - true Нет - false
     *
     * @return
     */
    public boolean getIsSecret() {
        return secret;
    }

    public Task setIsSecret(boolean secret) {
        this.secret = secret;
        return this;
    }

    /**
     * Только для озакомления - true Нет - false
     */
    public boolean getIsForReview() {
        return forReview;
    }

    public Task setIsForReview(boolean forReview) {
        this.forReview = forReview;
        return this;
    }

    /**
     * Начало
     */
    public String getDateBegin() {
        return dateBegin;
    }

    public Task setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
        return this;
    }

    /**
     * Окончание
     */
    public String getDateEnd() {
        return dateEnd;
    }

    public Task setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
        return this;
    }

    /**
     * Авторы - массив объектов - типа сотрудник
     *
     * @return
     */
    public Employee[] getAuthors() {
        return authors;
    }

    public Task setAuthors(Employee[] authors) {
        this.authors = authors;
        return this;
    }

    /**
     * Контролеры - массив объектов - типа сотрудник
     */
    public Employee[] getControllers() {
        return controllers;
    }

    public Task setControllers(Employee[] controllers) {
        this.controllers = controllers;
        return this;
    }

    /**
     * Ответственные руководители - массив объектов - типа сотрудник
     */
    public Employee[] getExecutiveManagers() {
        return executiveManagers;
    }

    public Task setExecutiveManagers(Employee[] executiveManagers) {
        this.executiveManagers = executiveManagers;
        return this;
    }

    /**
     * Исполнители - массив объектов - типа сотрудник
     */
    public Employee[] getWorkers() {
        return workers;
    }

    public Task setWorkers(Employee[] workers) {
        this.workers = workers;
        return this;
    }

    /**
     * Контрольные точки - массив объектов - контрольная точка
     */
    public Checkpoint[] getCheckpoints() {
        return checkpoints;
    }

    public Task setCheckpoints(Checkpoint[] checkpoints) {
        this.checkpoints = checkpoints;
        return this;
    }

    /**
     * Изолированные рабочие группы - массив объектов - изолированная рабочая группа
     */
    public IWG[] getIWG() {
        return iwg;
    }

    public Task setIWG(IWG[] iwg) {
        this.iwg = iwg;
        return this;
    }

    /**
     * События - массив объектов - Событие
     */
    public Event[] getEvents() {
        return events;
    }

    public Task setEvents(Event[] events) {
        this.events = events;
        return this;
    }

    /**
     * Контакты - массив объектов - Сотрудник
     */
    public Employee[] getContacts() {
        return contacts;
    }

    public Task setContacts(Employee[] contacts) {
        this.contacts = contacts;
        return this;
    }

    /**
     * Действия задачи
     */
    public Action[] getActions() {
        return actions;
    }

    public Task setActions(Action[] actions) {
        this.actions = actions;
        return this;
    }

    /**
     * Файлы задачи
     */
    public String[] getFileName() {
        return fileName;
    }

    public Task setFileName(String[] fileName) {
        this.fileName = fileName;
        return this;
    }

}
