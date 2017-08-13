package ru.motiw.web.elements.elementsweb.Administration;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Настройки ЭП (Электронная подпись)
 */
public class SettingsECPElements {


    @FindBy(xpath = "(//input[contains(@id,'checkbox')] [ancestor::div[contains(@id,'tabpanel')]])[1]")
    private SelenideElement useECP;

    /**
     * Использовать ЭЦП
     */
    public SelenideElement getUseECP() {
        return useECP;
    }
}
