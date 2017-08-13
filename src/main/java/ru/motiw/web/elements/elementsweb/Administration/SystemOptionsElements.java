package ru.motiw.web.elements.elementsweb.Administration;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Элементы раздела - Настройки системы
 */
public class SystemOptionsElements {

    @FindBy(xpath = "(//a//span[text()][ancestor::a[contains(@id,'tab')]])[4]")
    private SelenideElement tabTasks;

    @FindBy(xpath = "//*[@id='allowDeleteHimself-inputEl']")
    private SelenideElement allowToDeleteFromTasks;

    @FindBy(xpath = "//*[@id='allowSecretTasks-inputEl']")
    private SelenideElement creatingASecretTask;

    @FindBy(xpath = "//a[contains(@id,'button')][ancestor::span[contains(@id,'panel')]]//span[2]")
    private SelenideElement save;

    @FindBy(xpath = "//*[@id='button-1005-btnIconEl']")
    private SelenideElement ok;

    /**
     * tab - Задачи
     */
    public SelenideElement getTabTasks() {
        return tabTasks;
    }

    /**
     * Опция - Уделение себя из задач
     */
    public SelenideElement getAllowToDeleteFromTasks() {
        return allowToDeleteFromTasks;
    }

    /**
     * Опция - Создание секретных задач
     */
    public SelenideElement getCreatingASecretTask() {
        return creatingASecretTask;
    }

    /**
     * Сохранить
     */
    public SelenideElement getSave() {
        return save;
    }

    /**
     * Клик alert "Ok"
     */
    public SelenideElement getOk() {
        return ok;
    }
}
