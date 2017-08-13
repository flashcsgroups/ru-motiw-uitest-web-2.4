package ru.motiw.web.elements.elementsweb.Administration.Directories;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Форма редактирования - Справочники
 */
public class DirectoriesEditElements {

    @FindBy(xpath = "//a/ancestor::div[contains(@id,'tabbar')]/a[1]")
    private SelenideElement settingsTab;

    @FindBy(xpath = "//input[@id='quotient-inputEl']")
    private SelenideElement recordsAccessibility;

    @FindBy(css = "#visible_quotient-inputEl")
    private SelenideElement recordAccessSetting;

    @FindBy(xpath = "(//tr[contains(@id,'radiofield')]//input)[1]")
    private SelenideElement rbHierarchical;

    @FindBy(xpath = "(//tr[contains(@id,'radiofield')]//input)[2]")
    private SelenideElement rbFlat;

    @FindBy(xpath = "(//input[contains(@id,'search_type')])[1]")
    private SelenideElement useSearchSystem;

    @FindBy(xpath = "(//input[contains(@id,'search_type')])[2]")
    private SelenideElement useSearchBD;

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
     * Общедоступность записей
     */
    public SelenideElement getRecordsAccessibility() {
        return recordsAccessibility;
    }

    /**
     * Настройка доступа к записям
     */
    public SelenideElement getRecordAccessSetting() {
        return recordAccessSetting;
    }

    /**
     * Способ отображения
     * Иерархический
     */
    public SelenideElement getRbHierarchical() {
        return rbHierarchical;
    }

    /**
     * Способ отображения
     * Линейный
     */
    public SelenideElement getRbFlat() {
        return rbFlat;
    }

    /**
     * Настройки поиска
     * Использовать поисковую систему
     */
    public SelenideElement getUseSearchSystem() {
        return useSearchSystem;
    }

    /**
     * Настройки поиска
     * Использовать БД
     */
    public SelenideElement getUseSearchBD() {
        return useSearchBD;
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
     * Настройки закладок
     */
    public SelenideElement getHandlersAndMailNotifySettingsTab() {
        return handlersAndMailNotifySettingsTab;
    }
}
