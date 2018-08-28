package ru.motiw.web.elements.elementsweb.DocflowAdministration.DocumentRegistrationCards;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Форма редактирования РКД - Шаблоны отображения
 */
public class FormDocRegisterCardsEditTemplateElements {


    @FindBy(xpath = "(//div[contains(@id,'tabbar')]/following-sibling::*)[1]//a[10]//span")
    private SelenideElement TemplateSettingsTab;

    @FindBy(xpath = "(//input[contains(@id,'textfield')][ancestor::div[contains(@id,'container')]])[1]")
    private SelenideElement setDisplayNameTemplate;

    /**
     * Вкладка - Шаблоны отображения
     */
    public SelenideElement getTemplateSettingsTab() {
        return TemplateSettingsTab;
    }

/**
* Шаблон отображения в веб-интерфейсе
* */
    public SelenideElement getDisplayNameTemplate() {
        return setDisplayNameTemplate;
    }
}
