package ru.motiw.web.elements.elementsweb.DocflowAdministration.DocumentRegistrationCards;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Форма редактирования РКД - МАРШРУТНЫЕ СХЕМЫ
 */
public class FormDocRegisterCardsEditConnectedRoutesElements {

    @FindBy (xpath= " //li[text()='Все']")
    private SelenideElement selectAllRoutes;

    @FindBy (xpath= "//input[@value='Только подключенные']")
    private SelenideElement useRoutesInput;

    @FindBy (xpath= "//div[@class='x-box-target']//div[@class='x-form-trigger x-form-trigger-default x-form-arrow-trigger x-form-arrow-trigger-default ']")
    private SelenideElement comboRoutes;

    @FindBy(xpath = "//div[contains(@id,'checkbox')]//input[not(ancestor::fieldset)]/..")
    private SelenideElement useAllPossibleRoutes;

    @FindBy(xpath = "(//div[contains(@id,'tabbar')]/following-sibling::*)[1]//a[3]//span")
    private SelenideElement connectedRoutesTab;


    /**
     * Вкладка - Маршруты согласования
     */
    public SelenideElement getConnectedRoutesTab() {
        return connectedRoutesTab;
    }

    /**
     * Использовать все возможные маршруты
     */
    public SelenideElement getUseAllPossibleRoutes() {
        return useAllPossibleRoutes;
    }

    public SelenideElement routesInput() {
        return  useRoutesInput;
    }

    public SelenideElement ComboRoutes() {
        return comboRoutes;
    }

    public SelenideElement AllRoutes() {
        return selectAllRoutes;
    }

}
