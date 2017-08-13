package ru.motiw.web.elements.elementsweb.Login;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Элементы - форма восстановления пароля / сброс пароля пользователя
 */
public class RestorePasswordElements {


    @FindBy(id = "newpass-inputEl")
    private SelenideElement newPasswordField;

    @FindBy(id = "newpass_confirm-inputEl")
    private SelenideElement newPasswordConfirmField;

    @FindBy(xpath = "//a[contains(@id,'button')]")
    private SelenideElement buttonToSend;

    @FindBy(xpath = "//a[@href='/user/']")
    private SelenideElement home;

    /**
     * Поле ввода нового пароля
     */
    public SelenideElement getNewPasswordField() {
        return newPasswordField;
    }

    /**
     * Поле ввода подтверждения пароля
     */
    public SelenideElement getNewPasswordConfirmField() {
        return newPasswordConfirmField;
    }

    /**
     * Кнопка - Отправить
     */
    public SelenideElement getButtonToSend() {
        return buttonToSend;
    }

    /**
     * Кнопка - Стартовая страница
     */
    public SelenideElement getHome() {
        return home;
    }

}
