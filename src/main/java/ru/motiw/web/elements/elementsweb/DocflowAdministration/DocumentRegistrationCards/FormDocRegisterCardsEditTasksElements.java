package ru.motiw.web.elements.elementsweb.DocflowAdministration.DocumentRegistrationCards;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Форма редактирования РКД - ЗАДАЧИ
 */
public class FormDocRegisterCardsEditTasksElements {


    @FindBy(xpath = "//textarea[contains(@id,'textarea')]")
    private SelenideElement copyingFieldsWhenCreatingTask;

    @FindBy(xpath = "(//fieldset)[6]/div/div/descendant::input[1]")
    private SelenideElement authorsObjectives;

    @FindBy(xpath = "(//fieldset)[6]/div/div/descendant::input[2]")
    private SelenideElement controllersOfTasks;

    @FindBy(xpath = "(//fieldset)[6]/div/div/descendant::input[3]")
    private SelenideElement decisionMakersOfTasks;

    @FindBy(xpath = "(//fieldset)[6]/div/div/descendant::input[4]")
    private SelenideElement executorsOfTasks;

    @FindBy(xpath = "(//div[contains(@id,'tabbar')]/following-sibling::*)[1]//a[6]//span")
    private SelenideElement tasksTab;

    /**
     * Копирование полей при создании задачи
     */
    public SelenideElement getCopyingFieldsWhenCreatingTask() {
        return copyingFieldsWhenCreatingTask;
    }

    /**
     * Поля документа, содержащие...
     * Авторов задач
     */
    public SelenideElement getAuthorsObjectives() {
        return authorsObjectives;
    }

    /**
     * Поля документа, содержащие...
     * Контролеров задач
     */
    public SelenideElement getControllersOfTasks() {
        return controllersOfTasks;
    }

    /**
     * Поля документа, содержащие...
     * Ответственных руководителей задач
     */
    public SelenideElement getDecisionMakersOfTasks() {
        return decisionMakersOfTasks;
    }

    /**
     * Поля документа, содержащие...
     * Исполнителей задач
     */
    public SelenideElement getExecutorsOfTasks() {
        return executorsOfTasks;
    }

    /**
     * Вкладка - Задачи
     */
    public SelenideElement getTasksTab() {
        return tasksTab;
    }

}
