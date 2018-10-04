package ru.motiw.mobile.elements.Tasks;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.motiw.web.model.Administration.Users.Employee;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

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


    @FindBy(xpath = "//*[contains (@class,'window-noborder')][contains (@style,'visible')]//td[contains (@class,'cell')][1]")
    private SelenideElement buttonSaveDescription;

    @FindBy(xpath = "//*[@id='btnAddIWG']//button")
    private SelenideElement buttonAddIWG;

    @FindBy(css = "body")
    private SelenideElement ckeBody;

    @FindBy(xpath = "(//*[contains (@type, 'button')])[1]")
    private SelenideElement buttonSaveCreateTask;

    @FindBy(xpath = "//div[contains(@class,'x-component x-button x-widthed x-heighted x-has-text x-icon-align-left')]")
    private SelenideElement buttonCreateTask;

    @FindBy(xpath = "//input[@name=\"taskname\"]")
    private SelenideElement TaskName;

    @FindBy(xpath = "//div[@name=\"description\"]")
    private SelenideElement descriptionTask;

    @FindBy(xpath = "//input[@name=\"numerator\"]")
    private SelenideElement taskNumber;


    @FindBy(xpath = "//input[@name=\"priority\"]/ancestor::div[@class=\"x-body-el\"]//div[@class=\"x-expandtrigger x-trigger x-interactive\"]")
    private SelenideElement priority;

    @FindBy(xpath = "//input[@name=\"startdate\"]")
    private SelenideElement beginField;

    @FindBy(xpath = "//input[@name=\"enddate\"]")
    private SelenideElement endField;

    @FindBy(xpath = "//input[@name=\"id_tasktype\"]/ancestor::div[@class=\"x-body-el\"]//div[@class=\"x-expandtrigger x-trigger x-interactive\"]")
    private SelenideElement fieldTaskType;

    @FindBy(xpath = "//div[contains(text(),'Файлы')]//ancestor::div[contains(@class,\"x-panel x-container x-component small-collapser-panel x-noborder-trbl x-header-position-top x-panel-grey-background x-container-grey-background \")]//input[@class=\"x-input-el\"]")
    private SelenideElement fieldFiles;

    @FindBy(xpath = "//input[@type=\"file\"]")
    private SelenideElement inputFiles;

    @FindBy(xpath = "//i[contains(@class,'file')]/ancestor::div[contains(@class,\"x-body-wrap-el x-panel-body-wrap-el x-container-body-wrap-el x-component-body-wrap-el \")]//div[@class=\"x-innerhtml\"]")
    private ElementsCollection listOfNameFiles;

    @FindBy(xpath = "(//div[contains(text(),'Кому')]//ancestor::div[contains(@class,\"x-panel x-container x-component small-collapser-panel\")]//div[@class=\"x-input-el\"])[1]")
    private SelenideElement authorsField;

    @FindBy(xpath = "(//div[contains(text(),'Кому')]//ancestor::div[contains(@class,\"x-panel x-container x-component small-collapser-panel\")]//div[@class=\"x-input-el\"])[2]")
    private SelenideElement сontrollersField;

    @FindBy(xpath = "(//div[contains(text(),'Кому')]//ancestor::div[contains(@class,\"x-panel x-container x-component small-collapser-panel\")]//div[@class=\"x-input-el\"])[3]")
    private SelenideElement responsiblesManagersField;

    @FindBy(xpath = "(//div[contains(text(),'Кому')]//ancestor::div[contains(@class,\"x-panel x-container x-component small-collapser-panel\")]//div[@class=\"x-input-el\"])[4]")
    private SelenideElement workersField;

    @FindBy(xpath = "//*[contains (@class, 'x-editor')][contains (@style, 'visible')]//input")
    private SelenideElement editorField;

    @FindBy(xpath = "//span[text()=\"Обычная задача\"]")
    private SelenideElement simpleTask;

    @FindBy(xpath = "//span[text()=\"Важная задача\"]")
    private SelenideElement importantTask;


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
     * Поле - Описание задачи
     */
    public SelenideElement getDescriptionTask() {
        return descriptionTask;
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
     * поле - Файлы
     *
     * @return
     */

    public SelenideElement getFieldFiles() {
        return fieldFiles;
    }

    /**
     * input добавление Файла
     */

    public SelenideElement getInputFiles() {
        return inputFiles;
    }

    /**
     *коллекция элементов из списка добавленных файлов
     */

    public ElementsCollection getListOfNameFiles() {
        return listOfNameFiles;
    }

    /**
     * Поле ввода в списке формы добавления пользователей
     */
    public SelenideElement getInputForSearchUsers (String componentId) {
        return $(By.xpath("//div[@data-componentid='" + componentId + "']//input"));
    }


    /**
     * Набор элементов = кол-во пользователей в списке формы добавления пользователей
     */
    public ElementsCollection getListOfUsers(String componentId ) {
        return $$(By.xpath("//div[@data-componentid='" + componentId + "']//div[contains(@data-componentid,\"ext-gridcell\")]"));
    }

    /**
     * выбор пользователей в списке формы добавления пользователей
     */
    public SelenideElement getUserFromList (String componentId, Employee employee) {
        return $(By.xpath("//div[@data-componentid='" + componentId + "']//div[contains(text(),'" + employee.getLastName() + "')]"));
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
    public SelenideElement getResponsiblesField() {
        return responsiblesManagersField;
    }

    /**
     * поле - Исполнители
     */
    public SelenideElement getWorkersField() {
        return workersField;
    }


    /**
     * кнопка "Назначить"
     */
    public SelenideElement getButtonAppointUsers(String componentId) {
        return $(By.xpath("//div[@data-componentid='" + componentId + "']//div[contains(@class,\"x-component x-button\")]"));
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
