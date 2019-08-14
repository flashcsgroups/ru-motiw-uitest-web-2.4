package ru.motiw.mobile.elements.Internal;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.motiw.web.steps.BaseSteps;

import static com.codeborne.selenide.Selenide.$;


/**
 * Внутреняя страница системы
 */
public class InternalElementsMobile extends BaseSteps {

    /*
     * Кнопка главного меню
     */
    @FindBy(xpath = "//div[contains(@class,\"x-titlebar-left\")]//div[@class=\"x-component x-button no-blue-alt x-has-icon x-icon-align-left x-arrow-align-right x-button-alt x-component-alt x-layout-box-item x-layout-hbox-item\"]")
    private SelenideElement buttonMainMenu;


    /*
     * Ссылки на все пункты в меню
     */
    @FindBy(xpath = "//div[@class=\"x-body-el x-menu-body-el x-panel-body-el x-container-body-el x-component-body-el x-scroller x-layout-box x-align-stretch x-layout-vbox x-vertical x-pack-start x-menu-outer-border-trbl x-panel-outer-border-trbl x-container-outer-border-trbl x-component-outer-border-trbl\"]/div")
    private ElementsCollection menuElements;

    /*
     * Кнопка "Домой"
     */
    @FindBy(xpath = "//div[@class=\"x-icon-el x-font-icon x-mi mi-home home-btn-icon\"]//ancestor::div[@data-componentid=\"ext-main-navigatebutton-2\"]")
    private SelenideElement buttonGoHome;

    /*
     * Кнопка "Назад"
     */
    @FindBy(xpath = "//div[@data-componentid=\"ext-main-navigatebutton-1\"]")
    private SelenideElement buttonGoBack;

    /*
     * Title (общий на все формы)
     */
    @FindBy(xpath = "//div[contains(@id,\"ext-title\")]//div")
    private SelenideElement mainTitle;

    @FindBy(xpath = "//div[contains(@class,'x-toast x-sheet x-panel')]//div[@class=\"x-innerhtml\"]")
    private SelenideElement toastWithText;

    /*
     * Выход из системы
     */

    @FindBy(xpath = "//div[contains(text(),'Выход')]/ancestor::div[contains(@class,\"x-component x-button\")]")
    private SelenideElement logout;
    /*
     * Создать задачу
     */

    @FindBy(xpath = "//div[contains(text(),'Создать задачу')]/ancestor::div[contains(@class,\"x-component x-button\")]")
    private SelenideElement createTask;

    public ElementsCollection getMenuElementsMobile() {
        return menuElements;
    }

    /**
     * Кнопка открытия главного меню
     * @return
     */
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
    /**
     *  Title (общий на все формы)
     */
    public SelenideElement getMainTitle() {
        return mainTitle;
    }

    /**
     *  div с текстом во всплывающем Toast-е (общий на все формы)
     */
    public SelenideElement getToastWithText() {
        return toastWithText;
    }


    /**
     * Кнопки в Форме добавления текста при выполении операции (Добавление действия, операции с документом)
     */
    public SelenideElement getButtonInFormOfExecutionOperations (String nameOfOperation) {
        return $(By.xpath("//div[contains(@class,'x-dialog x-panel x-container x-component') and not(contains(@class,\"hidden\"))]" +
                "//div[text()='" + nameOfOperation + "']" +
                "//ancestor::div[contains(@class,\"x-component x-button x-has-text x-icon-align-left x-arrow-align-right\")]  "));
    }

}
