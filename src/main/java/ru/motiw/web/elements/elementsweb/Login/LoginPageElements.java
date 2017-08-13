package ru.motiw.web.elements.elementsweb.Login;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Элементы - страница авторизации
 */
public class LoginPageElements {

    
    @FindBy(css = "#login")
    private SelenideElement loginField;
    
    @FindBy(css = "#pass")
    private SelenideElement passwordField;
    
    @FindBy(css = "#subm")
    private SelenideElement logon;


    public SelenideElement getLogin() {
        return loginField;
    }

    public SelenideElement getPassword() {
        return passwordField;
    }

    public SelenideElement getLogon() {
        return logon;
    }
}
