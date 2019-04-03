package ru.motiw.mobile.elements.Tasks;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Элементы формы создания задачи
 */

public class NewTaskFormElementsMobile {

    /*
     * Все элементы формы содания задачи
     */
    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//div[contains(@class,'x-body-el x-panel-body-el x-container-body-el x-component-body-el x-layout-auto')]/div")
    private ElementsCollection newTaskFormElements;

    @FindBy(xpath = "//div[contains(@class,'x-component x-button x-widthed x-heighted x-has-text x-icon-align-left')]")
    private SelenideElement buttonCreateTask;

    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//input[@name=\"taskname\"]")
    private SelenideElement TaskName;

    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//div[@name=\"description\"]")
    private SelenideElement descriptionTask;

    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//span[text()='Проект']/../..//input")
    private SelenideElement projectTask;

    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//input[@name=\"numerator\"]")
    private SelenideElement taskNumber;

    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//input[@name=\"priority\"]/ancestor::div[@class=\"x-body-el\"]//div[@class=\"x-expandtrigger x-trigger x-interactive\"]")
    private SelenideElement priority;

    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//input[@name=\"startdate\"]")
    private SelenideElement beginField;

    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//input[@name=\"enddate\"]")
    private SelenideElement endField;

    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//div[contains(text(),'Файлы')]//ancestor::div[contains(@class,\"x-panel x-container x-component small-collapser-panel x-noborder-trbl x-header-position-top x-panel-grey-background x-container-grey-background \")]//input[@class=\"x-input-el\"]")
    private SelenideElement fieldFiles;

    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//input[@type=\"file\"]")
    private SelenideElement inputFiles;

    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//i[contains(@class,'file')]/ancestor::div[contains(@class,\"x-body-wrap-el x-panel-body-wrap-el x-container-body-wrap-el x-component-body-wrap-el \")]//div[@class=\"x-innerhtml\"]")
    private ElementsCollection listOfNameFiles;

    @FindBy(xpath = "(//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//div[contains(text(),'Кому')]//ancestor::div[contains(@class,\"x-panel x-container x-component small-collapser-panel\")]//div[@class=\"x-input-el\"])[1]")
    private SelenideElement authorsField;

    @FindBy(xpath = "(//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//div[contains(text(),'Кому')]//ancestor::div[contains(@class,\"x-panel x-container x-component small-collapser-panel\")]//div[@class=\"x-input-el\"])[2]")
    private SelenideElement сontrollersField;

    @FindBy(xpath = "(//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//div[contains(text(),'Кому')]//ancestor::div[contains(@class,\"x-panel x-container x-component small-collapser-panel\")]//div[@class=\"x-input-el\"])[3]")
    private SelenideElement responsiblesManagersField;

    @FindBy(xpath = "(//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//div[contains(text(),'Кому')]//ancestor::div[contains(@class,\"x-panel x-container x-component small-collapser-panel\")]//div[@class=\"x-input-el\"])[4]")
    private SelenideElement workersField;


    @FindBy(xpath = "//span[text()=\"Обычная задача\"]")
    private SelenideElement simpleTask;

    @FindBy(xpath = "//span[text()=\"Важная задача\"]")
    private SelenideElement importantTask;


    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//input[@name=\"iswithreport\"]/ancestor::div[@class=\"x-font-icon x-icon-el\"]")
    private SelenideElement checkboxReportRequired;


    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//input[@name=\"iswithreport\"]")
    private SelenideElement reportRequired;


    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//input[@name=\"issecret\"]/ancestor::div[@class=\"x-font-icon x-icon-el\"]")
    private SelenideElement checkboxIsSecret;


    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//input[@name=\"isforexamination\"]/ancestor::div[@class=\"x-font-icon x-icon-el\"]")
    private SelenideElement checkboxIsForReview;

    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//input[@name=\"isforexamination\"]")
    private SelenideElement IsForReview;


    @FindBy(xpath = "//span[text()][ancestor::em[contains(@class,'x-tab')]][ancestor::li[not(@style='display: none;')]]")
    private ElementsCollection collectionTabsForTasks;


    /**
     * Коллекция элементов в форме Создания Задачи
     * Коллекция элементов в форме задачи на вкладке Описание созданной задачи
     * @return коллекция элементов
     */
    public ElementsCollection getCollectionElementsFormOfTask() {
        return newTaskFormElements;
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
     * Проект документа
     */

    public SelenideElement getProjectTask() {
        return projectTask;
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
    public SelenideElement getInputForSearchUsers() {
        return $(By.xpath("//div[contains(@id,'ext-selectdialog') and not(contains(@class,\"x-hidden\")) and not(contains(@id,\"floatWrap\"))]//input"));
    }

    /**
     * Кнопка очистки поля ввода в списке формы добавления пользователей
     */
    public SelenideElement getClearTriggerInputForSearchUsers() {
        return $(By.xpath("//div[contains(@id,'ext-selectdialog') and not(contains(@class,\"x-hidden\")) and not(contains(@id,\"floatWrap\"))]//div[@class=\"x-cleartrigger x-trigger x-interactive x-cleartrigger-none x-trigger-none\"]         "));
    }


    /**
     * Элемент любого выбраннного (выделен синим) пользователя в списке формы добавления пользователей
     */
    public SelenideElement getSelectedUserInTheList() {
        return $(By.xpath("//div[contains(@id,'ext-selectdialog') and not(contains(@class,\"x-hidden\")) and not(contains(@id,\"floatWrap\"))]//div[contains(@class,\"x-selected\")]"));
    }


    /**
     * Элемент выбраннного (выделен синим) пользователя в списке формы добавления пользователей
     * @param lastName Фамилия пользователя
     */
    public SelenideElement getSelectedUserInTheList(String lastName) {
        return $(By.xpath("//div[contains(@id,'ext-selectdialog') and not(contains(@class,\"x-hidden\")) and not(contains(@id,\"floatWrap\"))]" +
                "//div[contains(@class,\"x-selected\")]//div[contains(text(),'" + lastName + "')]"));
    }


    /**
     * Набор элементов = кол-во пользователей в списке формы добавления пользователей
     */
    public ElementsCollection getListOfUsers() {
        return $$(By.xpath("//div[contains(@id,'ext-selectdialog') and not(contains(@class,\"x-hidden\")) and not(contains(@id,\"floatWrap\"))]//div[contains(@data-componentid,\"ext-gridcell\")]"));
    }

    /**
     * выбор пользователей в списке формы добавления пользователей
     */
    public SelenideElement getUserFromList(String lastName) {
        return $(By.xpath("//div[contains(@id,'ext-selectdialog') and not(contains(@class,\"x-hidden\")) and not(contains(@id,\"floatWrap\"))]//div[contains(text(),'" + lastName + "')]"));
    }

    /**
     * кнопка "Назначить"
     */
    public SelenideElement getButtonAppointUsers() {
        return $(By.xpath("//div[contains(@id,'ext-selectdialog') and not(contains(@class,\"x-hidden\")) and not(contains(@id,\"floatWrap\"))]//div[contains(@class,\"x-component x-button\")]"));
    }

    /**
     * Поля рабочей группы, при раскрытой группы полей "Кому"
     * Это xpath нужен т.к xpath в getAuthorsField() виден и при закрытой группе полей
    */
    public SelenideElement getFieldOfWorkGroup(String nameField) {
        return  $(By.xpath("//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//span[contains(text(),'" + nameField + "')]/../..//div[@class=\"x-input-el\"]"));
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

    /**
     *  input Пользовательских полей Тип Строка, Тип Целое, Тип Вещественное, Тип Нумератор, Тип Дата
     */
    public SelenideElement getInputInUserField(String nameField) {
        return $(By.xpath("//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//span[text()='" + nameField + "']//ancestor::div[contains(@class,\" x-field\")]//input"));
    }

    /**
     * Кнопка открытия выпадающего списка в Пользовательских строковых полях
     */
    public SelenideElement getTriggerInUserField(String nameField) {
        return $(By.xpath("//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//span[text()='" + nameField + "']//ancestor::div[contains(@class,\" x-field\")]//div[@class=\"x-expandtrigger x-trigger x-interactive\"] "));
    }

    /**
     * Выпадающий список в Пользовательских строковых полях
     */
    public SelenideElement getValueInTheListOfUserField(String valueInTheList) {
        return $(By.xpath("//div[contains(@id,'boundlist') and not(contains(@id,\"floatWrap\")) and not(contains(@class,\"x-hidden-display\"))]//span[text()='" + valueInTheList + "']"));
    }

    /**
     *  input Пользовательских полей Тип Сотрудник
     */
    public SelenideElement getInputInUserFieldTypeEmployee(String nameField) {
        return $(By.xpath("//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//span[text()='" + nameField + "']//ancestor::div[contains(@class,\" x-field\")]//div[@class=\"x-input-el\"]"));
    }

    /**
     *  чекбокс Пользовательского поля Тип Логический  - используется для установки значения
     */
    public SelenideElement getCheckboxInUserField(String nameField) {
        return $(By.xpath("//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//span[text()='" + nameField + "']//ancestor::div[contains(@class,\" x-field\")]//div[@class=\"x-font-icon x-icon-el\"]"));
    }

    /**
     *  чекбокс Пользовательского поля Тип Логический - используется для проверки значения
     */
    public SelenideElement getStateOfCheckboxInUserField(String nameField) {
        return $(By.xpath("//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//span[text()='" + nameField + "']//ancestor::div[contains(@class,\" x-field\")]//input"));
    }

    /**
     *  Набор строковых полей - используется для проверки значения
     */
    public ElementsCollection getSetInputs(String nameField) {
        return $$(By.xpath("//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//span[text()='" + nameField + "']/../..//input"));
    }


    /**
     * Элемент выбраннного (выделен синим) пользователя в списке формы добавления пользователей
     * @param lastName Фамилия пользователя
     */
    public SelenideElement getValueInTheList(String lastName) {
        return $(By.xpath("//div[contains(@id,'ext-selectdialog') and not(contains(@class,\"x-hidden\")) and not(contains(@id,\"floatWrap\"))]" +
                "//div[contains(@class,\"x-selected\")]//div[contains(text(),'" + lastName + "')]"));
    }


    /**
     *  TextArea Пользовательских полей Тип Текст
     */
    public SelenideElement getTextAreaInCustomField(String nameField) {
        return $(By.xpath("//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//span[text()='" + nameField + "']//ancestor::div[contains(@class,\" x-textfield x-field\")]//textarea"));
    }


    /**
     *  TextArea Пользовательских полей Тип Текст
     */
    public ElementsCollection getCollectionOfTextAreaInCustomField(String nameField) {
        return $$(By.xpath("//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//span[text()='" + nameField + "']//ancestor::div[contains(@class,\" x-textfield x-field\")]//textarea"));
    }

    /**
     *  Названия всех пользовательских полей в форме задачи
     */
    public ElementsCollection getNameOfFieldsInCustomFields() {
        return $$(By.xpath("//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//span[text()=\"Тип задачи\"]/ancestor::div[contains(@class,\"x-component-body-el x-scroller x-form-field-separators\")]//span[contains(@id,'ext')]"));
    }

}
