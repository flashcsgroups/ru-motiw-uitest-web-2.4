package ru.motiw.web.elements.elementsweb.Tasks.TaskForm;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Элементы формы - задача ИРГ
 */
public class IWGFormElements {


    @FindBy(xpath = "//input[contains (@id, 'iwgname')]")
    private SelenideElement inputIwgName;

    @FindBy(xpath = "//input[contains (@id, 'tasktemplate')]")
    private SelenideElement inputIwgTaskTemplate;

    @FindBy(xpath = "//input[contains (@id, 'tasktype')]")
    private SelenideElement inputIwgTaskType;

    @FindBy(xpath = "//input[contains (@id, 'sysacts')]")
    private SelenideElement inputIwgSysActionsInParentTask;

    @FindBy(xpath = "(//span[contains(@id,'button')][ancestor::div[contains(@id,'grid')]]/span/span[2])[1]")
    private SelenideElement buttonIwgAddRespPerson;

    @FindBy(xpath = "(//span[contains(@id,'button')][ancestor::div[contains(@id,'grid')]]/span/span[2])[2]")
    private SelenideElement buttonIwgAddWorker;

    @FindBy(xpath = "(//span[contains(@id,'button')][ancestor::div[contains(@id,'grid')]]/span/span[2])[3]")
    private SelenideElement buttonIwgAddController;

    @FindBy(xpath = "(//span[contains(@id,'button')][ancestor::div[contains(@id,'panel') and contains(@class,'x-plain bottom-panel')]]/span/span[2])[1]")
    private SelenideElement buttonIwgSave;

    @FindBy(xpath = "(//span[contains(@id,'button')][ancestor::div[contains(@id,'panel') and contains(@class,'x-plain bottom-panel')]]/span/span[2])[2]")
    private SelenideElement buttonIwgCancel;

    /**
     * Названия ИРГ
     */
    public SelenideElement getInputIwgName() {
        return inputIwgName;
    }

    /**
     * Шаблона ИРГ
     */
    public SelenideElement getInputIwgTaskTemplate() {
        return inputIwgTaskTemplate;
    }

    /**
     * поле - Типа задачи ИРГ
     */
    public SelenideElement getInputIwgTaskType() {
        return inputIwgTaskType;
    }

    /**
     * Чекбокс сист. действия в родительской задаче
     */
    public SelenideElement getInputIwgSysActionsInParentTask() {
        return inputIwgSysActionsInParentTask;
    }

    /**
     * Добавить - ИРГ - ОР задачи
     */
    public SelenideElement getButtonIwgAddRespPerson() {
        return buttonIwgAddRespPerson;
    }

    /**
     * Добавить - ИРГ -  Исполнитель задачи
     */
    public SelenideElement getButtonIwgAddWorker() {
        return buttonIwgAddWorker;
    }

    /**
     * Добавить - ИРГ - Контролер задачи
     */
    public SelenideElement getButtonIwgAddController() {
        return buttonIwgAddController;
    }

    /**
     * Сохранить
     */
    public SelenideElement getButtonIwgSave() {
        return buttonIwgSave;
    }

    /**
     * Отмена
     */
    public SelenideElement getButtonIwgCancel() {
        return buttonIwgCancel;
    }
}
