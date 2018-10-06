package ru.motiw.mobile.elements.Internal;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.motiw.web.steps.BaseSteps;



/**
 * Внутреняя страница системы
 */
public class InternalElementsMobile extends BaseSteps {

    /*
     * Кнопка главного меню
     */
    @FindBy(xpath = "//div[@class=\"x-component x-button no-blue-alt x-has-icon x-icon-align-left x-arrow-align-right x-button-alt x-component-alt x-layout-box-item x-layout-hbox-item\"][1]")
    private SelenideElement buttonMainMenu;


    /*
     * Ссылки на все пункты в меню
     */
    @FindBy(xpath = "//div[@class=\"x-body-el x-menu-body-el x-panel-body-el x-container-body-el x-component-body-el x-scroller x-layout-box x-align-stretch x-layout-vbox x-vertical x-pack-start x-menu-outer-border-trbl x-panel-outer-border-trbl x-container-outer-border-trbl x-component-outer-border-trbl\"]/div")
    private ElementsCollection menuElements;

    /*
     * Кнопка "Домой"
     */
    @FindBy(xpath = "(//div[@class=\"x-component x-button noselect x-has-icon x-icon-align-left x-arrow-align-right x-button-raised x-component-raised x-button-alt x-component-alt x-button-round x-component-round x-layout-box-item x-layout-hbox-item\"])[2]")
    private SelenideElement buttonGoHome;

    /*
     * Кнопка "Назад"
     */
    @FindBy(xpath = "(//div[@class=\"x-component x-button noselect x-has-icon x-icon-align-left x-arrow-align-right x-button-raised x-component-raised x-button-alt x-component-alt x-button-round x-component-round x-layout-box-item x-layout-hbox-item\"])[1]")
    private SelenideElement buttonGoBack;


    /*
     * Выход из системы
     */
    @FindBy(xpath = "//div[contains(text(),'Выход')]/ancestor::div[contains(@class,\"x-component x-button\")]")
    private SelenideElement logout;

    /*
     * Задачи/Задачи
     */
    @FindBy(xpath = "//a[@href='/tasksreports/']")
    private SelenideElement menuTaskReports;

    /*
     * Создать задачу
     */
    @FindBy(xpath = "//div[contains(text(),'Создать задачу')]/ancestor::div[contains(@class,\"x-component x-button\")]")
    private SelenideElement createTask;

    /*
     * Помощь
     */
    @FindBy(xpath = "//li[@class='help-but']/a")
    private SelenideElement helpHtml;

    /*
     * Настройки
     */
    @FindBy(xpath = "//li[@class='option-but']/a")
    private SelenideElement options;

    /*
     * Сегодня
     */
    @FindBy(xpath = "//a[contains(@href, '/today/')]")
    private SelenideElement today;

    /*
     * Документы
     */
    @FindBy(xpath = "//a[contains(@href, '/documents/')]")
    private SelenideElement documents;

    /*
     * Домой
     */
    @FindBy(xpath = "//a/img[contains(@src,'home')]")
    private SelenideElement home;

    /*
     * Поле - Поиск
     */
    @FindBy(xpath = "//input[@name='search']")
    private SelenideElement search;

    public ElementsCollection getMenuElementsMobile() {
        return menuElements;
    }

    public SelenideElement getButtonMainMenu() {
        return buttonMainMenu;
    }


    public SelenideElement getButtonGoHome() {
        return buttonGoHome;
    }

    public SelenideElement getButtonGoBack() {
        return buttonGoBack;
    }


    public SelenideElement getCreateTaskMobile() {return createTask;}

    /*
     * Выход из системы
     */
    public SelenideElement getLogout() {
        return logout;
    }



}
