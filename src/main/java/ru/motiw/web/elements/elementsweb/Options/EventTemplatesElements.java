package ru.motiw.web.elements.elementsweb.Options;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Шаблон событий
 */
public class EventTemplatesElements {


    @FindBy(xpath = "//div[contains(@id,'toolbar')][count(a)=2]/a[1]//span[contains(@id,'btnIconEl')]")
    private SelenideElement saveEventTemplate;

    @FindBy(css = "#eventTemplateName")
    private SelenideElement nameEventTemplate;

    /**
     * Название шаблона событий в форме - Шаблоны событий
     */
    public SelenideElement getInputNameEventTemplate() {
        return nameEventTemplate;
    }

    /**
     * Сохранить Шаблон событий
     */
    public SelenideElement getSaveEventTemplate() {
        return saveEventTemplate;
    }

}
