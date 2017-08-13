package ru.motiw.web.elements.elementsweb.DocflowAdministration.DocumentRegistrationCards;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Форма редактирования РКД - РЕЗОЛЮЦИИ
 */
public class FormDocRegisterCardsEditResolutionsElements {


    @FindBy(xpath = "(//div[contains(@id,'tabbar')]/following-sibling::*)[1]//a[7]//span")
    private SelenideElement resolutionsTab;


    /**
     * Вкладка - Резолюции
     */
    public SelenideElement getResolutionsTab() {
        return resolutionsTab;
    }

}
