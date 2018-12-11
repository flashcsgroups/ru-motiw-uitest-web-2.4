package ru.motiw.mobile.elements.Login;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Элементы - страница авторизации
 */
public class LoginPageElementsMobile {

    
    @FindBy(xpath = "//div[contains(@id,\"ext-login-panel\") and not(contains(@class,\"hidden\"))]//span[contains(text(),'Имя')]//ancestor::div[1]//input")
    private SelenideElement loginField;
    
    @FindBy(xpath = "//div[contains(@id,\"ext-login-panel\") and not(contains(@class,\"hidden\"))]//span[contains(text(),'Пароль')]//ancestor::div[1]//input")
    private SelenideElement passwordField;
    
    @FindBy(xpath = "//div[contains(@id,\"ext-login-panel\") and not(contains(@class,\"hidden\"))]//div[@class=\"x-body-el x-container-body-el x-component-body-el x-layout-box x-align-center x-pack-center x-layout-hbox x-horizontal\"]//.")
    private SelenideElement logon;

    @FindBy(xpath = "//div[contains(@id,\"mask\") and not(contains(@class,\"hidden\"))]//div[@class=\"x-loading-spinner-outer\"]")
    private SelenideElement maskOfLoading;

    @FindBy(xpath = "//div[contains(@class,'x-toast x-sheet x-panel')]")
    private SelenideElement toastOfNewTask;

    @FindBy(xpath = "//div[contains(@class,'x-toast x-sheet x-panel')]//a")
    private SelenideElement textOnToastOfNewTask;

    public SelenideElement getLogin() { return loginField; }

    public SelenideElement getPassword() {
        return passwordField;
    }

    public SelenideElement getLogon() {
        return logon;
    }

    //Маска загрузки
    public SelenideElement getMaskOfLoading() {
        return maskOfLoading;
    }


    //Всплывающий Toast на новую задачу
    public SelenideElement getToastOfNewTask() {
        return toastOfNewTask;
    }

    //Текст во всплывающем Toast-е на новую задачу
    public SelenideElement getTextOnToastOfNewTask() {
        return textOnToastOfNewTask;
    }

}
