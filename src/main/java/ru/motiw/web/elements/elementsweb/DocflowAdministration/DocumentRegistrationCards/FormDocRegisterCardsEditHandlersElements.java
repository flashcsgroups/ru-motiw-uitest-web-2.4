package ru.motiw.web.elements.elementsweb.DocflowAdministration.DocumentRegistrationCards;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Форма редактирования РКД - ОБРАБОТЧИКИ
 */
public class FormDocRegisterCardsEditHandlersElements {


    @FindBy(xpath = "(//div[contains(@id,'tabbar')]/following-sibling::*)[1]//a[5]//span")
    private SelenideElement handlersTab;

    /**
     * Вкладка - Обработчики
     */
    public SelenideElement getHandlersTab() {
        return handlersTab;
    }

}
