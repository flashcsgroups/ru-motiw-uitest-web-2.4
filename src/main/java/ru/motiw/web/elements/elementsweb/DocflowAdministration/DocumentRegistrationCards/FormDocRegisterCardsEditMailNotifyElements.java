package ru.motiw.web.elements.elementsweb.DocflowAdministration.DocumentRegistrationCards;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Форма редактирования РКД - НАСТРОЙКИ ПОЧТОВЫХ УВЕДОМЛЕНИЙ
 */
public class FormDocRegisterCardsEditMailNotifyElements {


    @FindBy(xpath = "(//div[contains(@id,'tabbar')]/following-sibling::*)[1]//a[8]//span")
    private SelenideElement mailNotifyTemplatesSettingsTab;

    /**
     * Вкладка - Настройки почтовых уведомлений
     */
    public SelenideElement getMailNotifyTemplatesSettingsTab() {
        return mailNotifyTemplatesSettingsTab;
    }
}
