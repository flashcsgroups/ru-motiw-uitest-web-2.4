package ru.motiw.mobile.elements.Tasks;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Форма созданной задачи
 */
public class TaskElementsMobile {

    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\")) and not(contains(@id,\"ext-object-toolbar\"))]" +
            "//div[contains(@class,\"x-body-el x-toolbar-body-el x-container-body-el x-component-body-el x-layout-box x-align-center x-pack-start x-layout-vbox x-vertical x-size-monitored x-paint-monitored x-toolbar-side-toolbar-body-el\")]/div")
    private ElementsCollection menuOfTask;

    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\")) and not(contains(@id,\"ext-object-toolbar\"))]" +
            "//div[contains(@class,\"x-toolbar x-container x-component x-noborder-trbl x-toolbar-side-toolbar\")]")
    private SelenideElement toolbarOfMenu;

    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\")) and not(contains(@id,\"ext-object-toolbar\"))]" +
            "//div[text()=\"Файлы\"]//ancestor::div[contains(@class,\"x-component x-button x-icon-align-top x-widthed x-has-icon\")]")
    private SelenideElement buttonOfFiles;

    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\")) and not(contains(@id,\"ext-object-toolbar\"))]" +
            "//div[text()=\"Файлы\"]//ancestor::div[contains(@class,\"x-pressed\")]")
    private SelenideElement pressedButtonOfFiles;

    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\")) and not(contains(@id,\"ext-object-toolbar\"))]" +
            "//div[text()=\"Действия\"]//ancestor::div[contains(@class,\"x-component x-button x-icon-align-top x-widthed x-has-icon\")]")
    private SelenideElement buttonOfActions;

    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\")) and not(contains(@id,\"ext-object-toolbar\"))]" +
            "//div[text()=\"Действия\"]//ancestor::div[contains(@class,\"x-pressed\")]")
    private SelenideElement pressedButtonOfActions;

    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\")) and not(contains(@id,\"ext-object-toolbar\"))]" +
            "//div[text()=\"Описание\"]//ancestor::div[contains(@class,\"x-component x-button x-icon-align-top x-widthed x-has-icon\")]")
    private SelenideElement buttonOfDescription;

    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\")) and not(contains(@id,\"ext-object-toolbar\"))]" +
            "//div[text()=\"Описание\"]//ancestor::div[contains(@class,\"x-pressed\")]")
    private SelenideElement pressedButtonOfDescription;

    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\")) and not(contains(@id,\"ext-object-toolbar\"))]" +
            "//div[text()=\"Сохранить\"]//ancestor::div[contains(@class,\"x-component x-button x-icon-align-top x-widthed x-has-icon\")]")
    private SelenideElement buttonOfSave;

    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\")) and not(contains(@id,\"ext-object-toolbar\"))]" +
            "//div[text()=\"Добавить действие\"]//ancestor::div[contains(@class,\"x-component x-button x-icon-align-top x-widthed x-has-icon\")]")
    private SelenideElement buttonOfAddAction;

    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\")) and not(contains(@id,\"ext-object-toolbar\"))]" +
            "//div[text()=\"Завершить выполнение\"]//ancestor::div[contains(@class,\"x-component x-button x-icon-align-top x-widthed x-has-icon\")]")
    private SelenideElement buttonOfFinalizeExecution;

    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\")) and not(contains(@id,\"ext-object-toolbar\"))]" +
            "//div[contains(@class,\"x-body-el x-toolbar-body-el x-container-body-el x-component-body-el x-layout-box x-align-center x-pack-start x-layout-vbox x-vertical x-size-monitored x-paint-monitored x-toolbar-side-toolbar-body-el\")]" +
            "//hr[@class=\"hr-toolbar-class\"]")
    private SelenideElement elementAmongButtonsOfMenu;

    @FindBy(xpath = "//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\")) and not(contains(@id,\"ext-object-toolbar\"))]" +
            "//div[@class=\"x-component x-label x-layout-box-item x-layout-vbox-item counter-label\"]/div[@class=\"x-innerhtml\"]")
    private SelenideElement numbersOnElementCounterFiles;

    @FindBy(xpath = "//textarea[@placeholder=\"Ваш комментарий\"]")
    private SelenideElement inputForAddComment;


    /**
     * Все элементы меню
     * @return элементы меню форме задачи
     */
    public ElementsCollection getMenuOfTask() {
        return menuOfTask;
    }

    /**
     * Тулбар меню формы задачи
     */

    public SelenideElement getToolbarOfMenu() {
        return toolbarOfMenu;
    }

    /**
     * Кнопка перехода на вкладку "Файлы"
     */
    public SelenideElement getButtonOfFilesTab() {
        return buttonOfFiles;
    }

    /**
     * Кнопка перехода на вкладку "Файлы" - Нажатая (Выделенная)
     */
    public SelenideElement getPressedButtonOfFiles() {
        return pressedButtonOfFiles;
    }


    /**
     * Кнопка перехода на вкладку "Действия"
     */
    public SelenideElement getButtonOfActionsTab() {
        return buttonOfActions;
    }

    /**
     * Кнопка перехода на вкладку "Действия" - Нажатая (Выделенная)
     */
    public SelenideElement getPressedButtonOfActions() {
        return pressedButtonOfActions;
    }


    /**
     * Кнопка перехода на вкладку "Описание"
     */
    public SelenideElement getButtonOfDescriptionTab() {
        return buttonOfDescription;
    }

    /**
     * Кнопка перехода на вкладку "Описание" - Нажатая (Выделенная)
     */
    public SelenideElement getPressedButtonOfDescription() {
        return pressedButtonOfDescription;
    }

    /**
     * Кнопка "Сохранить"
     */
    public SelenideElement getButtonOfSave() {
        return buttonOfSave;
    }

    /**
     * Кнопка перехода на вкладку "Добавить действие"
     */
    public SelenideElement getButtonOfAddAction() {
        return buttonOfAddAction;
    }

    /**
     * Кнопка перехода на вкладку "Завершить выполнение"
     */
    public SelenideElement getButtonOfFinalizeExecution() {
        return buttonOfFinalizeExecution;
    }

    /**
     * Элемент визуально разграничивающий кнопки вкладок и кнопки действий меню
     */
    public SelenideElement getElementAmongButtonsOfMenu() {
        return elementAmongButtonsOfMenu;
    }

    /**
     * Цифры на Элементе-счетчике кол-ва файлов в задаче
     */
    public SelenideElement getNumbersOnElementCounterFiles() {
        return numbersOnElementCounterFiles;
    }


    /**
     * Поле ввода комментрия (форма добавления действия,форма добавления комментрия при завершении задачи)
     */
    public SelenideElement getInputForAddComment() {
        return inputForAddComment;
    }

}
