package ru.motiw.mobile.elements.Login;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Элементы - страница авторизации
 */
public class LoginPageElementsMobile {

    
    @FindBy(xpath = "//span[contains(text(),'Имя')]//ancestor::div[1]//input")
    private SelenideElement loginField;
    
    @FindBy(xpath = "//span[contains(text(),'Пароль')]//ancestor::div[1]//input")
    private SelenideElement passwordField;
    
    @FindBy(xpath = "//div[@class=\"x-body-el x-container-body-el x-component-body-el x-layout-box x-align-center x-pack-center x-layout-hbox x-horizontal\"]//.")
    private SelenideElement logon;


    public SelenideElement getLogin() { return loginField; }

    public SelenideElement getPassword() {
        return passwordField;
    }

    public SelenideElement getLogon() {
        return logon;
    }
}
