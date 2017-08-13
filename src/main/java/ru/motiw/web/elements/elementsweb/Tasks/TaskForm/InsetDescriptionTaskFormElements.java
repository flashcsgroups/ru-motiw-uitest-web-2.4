package ru.motiw.web.elements.elementsweb.Tasks.TaskForm;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Элементы формы - ЗАДАЧА - вкладка Описание
 */
public class InsetDescriptionTaskFormElements {


    @FindBy(xpath = "//a[contains (@onclick, 'selectproject')]")
    private SelenideElement selectProject;

    @FindBy(xpath = "//*[contains (@class, 'x-window-plain')]//button")
    private SelenideElement OKButtonInConfirmationFormTaskCreation;

    @FindBy(xpath = "//a[contains (@href, 'newproject')]/img")
    private SelenideElement buttonNewProject;

    @FindBy(xpath = "//*[contains (@onclick, 'Edit')]")
    private SelenideElement descriptionTask;

    @FindBy(xpath = "//*[contains (@class,'window-noborder')][contains (@style,'visible')]//td[contains (@class,'cell')][1]")
    private SelenideElement buttonSaveDescription;

    @FindBy(xpath = "//*[@id='btnAddIWG']//button")
    private SelenideElement buttonAddIWG;

    @FindBy(css = "body")
    private SelenideElement ckeBody;

    @FindBy(xpath = "//*[contains (@onclick, 'author')]")
    private SelenideElement authors;

    @FindBy(xpath = "//*[contains (@onclick, 'controller')]")
    private SelenideElement controllers;

    @FindBy(xpath = "//*[contains (@onclick, 'respperson')]")
    private SelenideElement resppersons;

    @FindBy(xpath = "//*[contains (@onclick, 'worker')]")
    private SelenideElement workers;

    @FindBy(xpath = "(//*[contains (@type, 'button')])[1]")
    private SelenideElement buttonSaveCreateTask;

    @FindBy(xpath = "(//*[contains (@type, 'button')])[2]")
    private SelenideElement buttonCreateTask;

    @FindBy(xpath = "//a[contains (@onclick, 'selectproject')]/../../../../../../following-sibling::div[1]//*[contains (@class, 'col-value')]")
    private SelenideElement clickTaskName;

    @FindBy(xpath = "//a[contains (@onclick, 'selectproject')]/../../../../../../following-sibling::div[2]//*[contains (@class, 'col-value')]")
    private SelenideElement taskNumber;

    @FindBy(xpath = "//a[contains (@onclick, 'selectproject')]/../../../../../../following-sibling::div[4]//*[contains (@class, 'col-value')]")
    private SelenideElement priority;

    @FindBy(xpath = "//a[contains (@onclick, 'selectproject')]/../../../../../../following-sibling::div[5]//*[contains (@class, 'col-value')]")
    private SelenideElement beginField;

    @FindBy(xpath = "//a[contains (@onclick, 'selectproject')]/../../../../../../following-sibling::div[6]//*[contains (@class, 'col-value')]")
    private SelenideElement endField;

    @FindBy(xpath = "//a[contains (@onclick, 'selectproject')]/../../../../../../following-sibling::div[13]//*[contains (@class, 'col-value')]")
    private SelenideElement fieldTaskType;

    @FindBy(xpath = "//*[contains (@onclick, 'author')]/../../../td[2]/div")
    private SelenideElement authorsField;

    @FindBy(xpath = "//*[contains (@onclick, 'controller')]/../../../td[2]/div")
    private SelenideElement сontrollersField;

    @FindBy(xpath = "//*[contains (@onclick, 'respperson')]/../../../td[2]/div")
    private SelenideElement executiveManagersField;

    @FindBy(xpath = "//*[contains (@onclick, 'worker')]/../../../td[2]/div")
    private SelenideElement workersField;

    @FindBy(xpath = "//*[contains (@class, 'x-editor')][contains (@style, 'visible')]//input")
    private SelenideElement editorField;

    @FindBy(xpath = "//*[contains (@class, 'inner')]/*[contains (@class, 'combo-list')][1]")
    private SelenideElement simpleTask;

    @FindBy(xpath = "//*[contains (@class, 'inner')]/*[contains (@class, 'combo-list')][2]")
    private SelenideElement importantTask;

    @FindBy(xpath = "//li[contains (@id, 'tab_description')]//span[contains (@class, 'strip')]/span")
    private SelenideElement planningDescription;

    @FindBy(xpath = "//span[text()][ancestor::em[contains(@class,'x-tab')]][ancestor::li[not(@style='display: none;')]]")
    private ElementsCollection collectionTabsForTasks;


    /**
     * Набор вкладок в форме задачи
     *
     * @return коллекция вкладок в форме Задача
     */
    public ElementsCollection getCollectionTabsForTasks() {
        return collectionTabsForTasks;
    }

    /**
     * Кнопка выбора проекта
     *
     * @return элемент кнопка - Выбрать проект
     */
    public SelenideElement getSelectProject() {
        return selectProject;
    }

    /**
     * Кнопка Ок - в форме оповещение о созданной задаче
     */
    public SelenideElement getOKButtonInConfirmationFormTaskCreation() {
        return OKButtonInConfirmationFormTaskCreation;
    }

    /**
     * Кнопка - Создать новый проект
     */
    public SelenideElement getButtonNewProject() {
        return buttonNewProject;
    }

    /**
     * Кнопка - Описание задачи
     */
    public SelenideElement getDescriptionTask() {
        return descriptionTask;
    }

    /**
     * Кнопка сохранения описания
     */
    public SelenideElement getButtonSaveDescription() {
        return buttonSaveDescription;
    }

    /**
     * Кнопка добавления ИРГ
     */
    public SelenideElement getButtonAddIWG() {
        return buttonAddIWG;
    }

    /**
     * Область редактирования описания
     */
    public SelenideElement getCkeBody() {
        return ckeBody;
    }

    /**
     * Кнопка редактирования авторов
     */
    public SelenideElement getAuthors() {
        return authors;
    }

    /**
     * Кнопка редактирования контролеров
     */
    public SelenideElement getControllers() {
        return controllers;
    }

    /**
     * Кнопка редактирования ответственных руководителей
     */
    public SelenideElement getResppersons() {
        return resppersons;
    }

    /**
     * Кнопка редактирования исполнителей
     */
    public SelenideElement getWorkers() {
        return workers;
    }

    /**
     * Кнопка Сохранить и создать новую задачу
     */
    public SelenideElement getButtonSaveCreateTask() {
        return buttonSaveCreateTask;
    }

    /**
     * Кнопка Сохранить задачу
     */
    public SelenideElement getButtonCreateTask() {
        return buttonCreateTask;
    }

    /**
     * Поле Название задачи
     */
    public SelenideElement getClickTaskName() {
        return clickTaskName;
    }

    /**
     * Номер задачи
     */
    public SelenideElement getTaskNumber() {
        return taskNumber;
    }

    /**
     * Приоритет
     */
    public SelenideElement getPriority() {
        return priority;
    }

    /**
     * поле - Начало задачи
     *
     * @return
     */
    public SelenideElement getBeginField() {
        return beginField;
    }

    /**
     * поле - Окончание задачи
     *
     * @return
     */
    public SelenideElement getEndField() {
        return endField;
    }

    /**
     * поле - Тип задачт
     *
     * @return
     */
    public SelenideElement getFieldTaskType() {
        return fieldTaskType;
    }

    /**
     * поле - Авторы
     */
    public SelenideElement getAuthorsField() {
        return authorsField;
    }

    /**
     * поле - Контролеры
     */
    public SelenideElement getСontrollersField() {
        return сontrollersField;
    }

    /**
     * поле - Ответственные руководители
     */
    public SelenideElement getExecutiveManagersField() {
        return executiveManagersField;
    }

    /**
     * поле - Исполнители
     */
    public SelenideElement getWorkersField() {
        return workersField;
    }

    /**
     * Поле ввода для любого поля задачи
     */
    public SelenideElement getEditorField() {
        return editorField;
    }

    /**
     * признак - Важная задача
     */
    public SelenideElement getImportantTask() {
        return importantTask;
    }

    /**
     * признак - Обычная задача
     */
    public SelenideElement getSimpleTask() {
        return simpleTask;
    }

    /**
     * Вкладка - Описание
     */
    public SelenideElement getPlanningDescription() {
        return planningDescription;
    }

}
