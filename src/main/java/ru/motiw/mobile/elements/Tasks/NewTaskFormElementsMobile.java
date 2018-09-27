package ru.motiw.mobile.elements.Tasks;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Элементы формы создания задачи
 */

public class NewTaskFormElementsMobile {

    /*
     * Все элементы формы содания задачи
     */
    @FindBy(xpath = "//div[contains(@class,'x-body-el x-panel-body-el x-container-body-el x-component-body-el x-layout-auto')]/div")
    private ElementsCollection newTaskFormElements;

    @FindBy(xpath = "//a[contains (@onclick, 'selectproject')]")
    private SelenideElement selectProject;

    @FindBy(xpath = "//*[contains (@class, 'x-window-plain')]//button")
    private SelenideElement OKButtonInConfirmationFormTaskCreation;

    @FindBy(xpath = "//a[contains (@href, 'newproject')]/img")
    private SelenideElement buttonNewProject;

    @FindBy(xpath = "//div[@name=\"description\"]")
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

    @FindBy(xpath = "//div[contains(@class,'x-component x-button x-widthed x-heighted x-has-text x-icon-align-left')]")
    private SelenideElement buttonCreateTask;

    @FindBy(xpath = "//input[@name=\"taskname\"]")
    private SelenideElement TaskName;

    @FindBy(xpath = "//a[contains (@onclick, 'selectproject')]/../../../../../../following-sibling::div[2]//*[contains (@class, 'col-value')]")
    private SelenideElement taskNumber;

    @FindBy(xpath = "//input[@name=\"priority\"]/ancestor::div[@class=\"x-body-el\"]//div[@class=\"x-expandtrigger x-trigger x-interactive\"]")
    private SelenideElement priority;

    @FindBy(xpath = "//input[@name=\"startdate\"]")
    private SelenideElement beginField;

    @FindBy(xpath = "//input[@name=\"enddate\"]")
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

    @FindBy(xpath = "//span[text()=\"Обычная задача\"]")
    private SelenideElement simpleTask;

    @FindBy(xpath = "//span[text()=\"Важная задача\"]")
    private SelenideElement importantTask;

    @FindBy(xpath = "//div[@name=\"description\"]")
    private SelenideElement Description;


    @FindBy(xpath = "//input[@name=\"iswithreport\"]/ancestor::div[@class=\"x-font-icon x-icon-el\"]")
    private SelenideElement checkboxReportRequired;


    @FindBy(xpath = "//input[@name=\"iswithreport\"]")
    private SelenideElement reportRequired;


    @FindBy(xpath = "//input[@name=\"issecret\"]/ancestor::div[@class=\"x-font-icon x-icon-el\"]")
    private SelenideElement checkboxIsSecret;

    @FindBy(xpath = "//input[@name=\"issecret\"]")
    private SelenideElement isSecret;

    @FindBy(xpath = "//input[@name=\"issecret\"]")
    private boolean w;

    @FindBy(xpath = "//input[@name=\"isforexamination\"]/ancestor::div[@class=\"x-font-icon x-icon-el\"]")
    private SelenideElement checkboxIsForReview;

    @FindBy(xpath = "//input[@name=\"isforexamination\"]")
    private SelenideElement IsForReview;


    @FindBy(xpath = "//span[text()][ancestor::em[contains(@class,'x-tab')]][ancestor::li[not(@style='display: none;')]]")
    private ElementsCollection collectionTabsForTasks;


    /**
     * Набор элементов в форме Создания Задачи
     *
     * @return коллекция элементов в форме Создания Задачи
     */
    public ElementsCollection getCollectionNewTaskformElements() {
        return newTaskFormElements;
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
    public SelenideElement getTaskName() {
        return TaskName;
    }

    /**
     * Номер задачи
     */
    public SelenideElement getTaskNumber() {
        return taskNumber;
    }

    /**
     * Приоритет - выпадающий спискок
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
     * поле - Тип задачи
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
    public SelenideElement getDescription() {
        return Description;
    }


    /**
     * чексбокс - С докладом
     * Используется для установки состояния.
     */

    public SelenideElement getCheckboxReportRequired() {
        return checkboxReportRequired;
    }



    /**
     * признак - С докладом
     * Используется для проверки состояния.
     */

    public SelenideElement getReportRequired() {
        return reportRequired;
    }


    /**
     * признак - Секретная
     * Используется для установки состояния.
     */


    public SelenideElement getCheckboxIsSecret() {
        return checkboxIsSecret;
    }


    /**
     * признак - Секретная
     * Используется для проверки состояния.
     */

    public SelenideElement getIsSecret() {
        return isSecret;
    }


    /**
     * признак - Для ознакомления
     * Используется для установки состояния.
     */

    public SelenideElement getCheckboxIsForReview() {
        return checkboxIsForReview;
    }


    /**
     * признак - Для ознакомления
     * Используется для проверки состояния.
     */

    public SelenideElement getIsForReview() {
        return IsForReview;
    }

}
