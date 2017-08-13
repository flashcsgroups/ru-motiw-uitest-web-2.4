package ru.motiw.web.elements.elementsweb.Administration.TypesOfTables;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Форма редактирования - Типы таблиц
 */
public class TypesOfTablesEditElements {

    @FindBy(xpath = "//a/ancestor::div[contains(@id,'tabbar')]/a[1]")
    private SelenideElement settingsTab;

    @FindBy(xpath = "//a/ancestor::div[contains(@id,'tabbar')]/a[2]")
    private SelenideElement fieldsTab;

    @FindBy(xpath = "//a/ancestor::div[contains(@id,'tabbar')]/a[3]")
    private SelenideElement handlersTab;

    @FindBy(xpath = "//a/ancestor::div[contains(@id,'tabbar')]/a[4]")
    private SelenideElement handlersAndMailNotifySettingsTab;


    /**
     * Вкладка - Настройки
     */
    public SelenideElement getSettingsTab() {
        return settingsTab;
    }

    /**
     * Вкладка - Поля
     */
    public SelenideElement getFieldsTab() {
        return fieldsTab;
    }

    /**
     * Вкладка - Обработчики
     */
    public SelenideElement getHandlersTab() {
        return handlersTab;
    }

    /**
     * Вкладка - Настройки почтовых уведомлений И Настройки закладок (только для объектов - Справочники И Типы таблиц)
     */
    public SelenideElement getHandlersAndMailNotifySettingsTab() {
        return handlersAndMailNotifySettingsTab;
    }


}
