package ru.motiw.mobile.steps;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import ru.motiw.mobile.elements.Internal.InternalElementsMobile;
import ru.motiw.mobile.elements.Login.LoginPageElementsMobile;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.steps.BaseSteps;

import static com.codeborne.selenide.Selenide.page;

/*
 * Страница авторизации - Mobile
 */
public class LoginStepsMobile extends BaseSteps {

    private LoginPageElementsMobile loginPageElementsMobile = page(LoginPageElementsMobile.class);
    private InternalElementsMobile internalElementsMobile = page(InternalElementsMobile.class);


    /**
     * вводим Логин и Пароль пользователя
     *
     * @param login input text login
     * @param password input text password
     */
    public void loginAs(String login, String password) {
        loginPageElementsMobile.getLogin().clear();
        loginPageElementsMobile.getLogin().setValue(login);
        loginPageElementsMobile.getLogin().clear();
        loginPageElementsMobile.getPassword().setValue(password);
        loginPageElementsMobile.getLogin().click();
    }

    /**
     * Вводим Login пользователя
     *
     * @param login input text login
     * @return
     */
    public LoginStepsMobile setLoginField(String login) {
        loginPageElementsMobile.getLogin().setValue(login);
        return this;
    }

    /**
     * Вводим пароль пользователя
     *
     * @param password input text password
     */
    public LoginStepsMobile setPasswordField(String password) {
        loginPageElementsMobile.getPassword().setValue(password);
        return this;
    }

    /**
     * Авторизация под пользователем
     *
     * @param user pass and login users
     */
    public LoginStepsMobile loginAs(Employee user) {
        setLoginField(user.getLoginName());
        setPasswordField(user.getPassword());
        loginPageElementsMobile.getLogon().click();
        return this;
    }


    /**
     * Проверяем то, что мы разлогинены - ПДА-интерфейс
     */
    public boolean isNotLoggedInMobile() {
        try {
            loginPageElementsMobile.getLogon().shouldBe(Condition.visible);
            loginPageElementsMobile.getPassword().shouldBe(Condition.visible);
            loginPageElementsMobile.getLogin().shouldBe(Condition.visible);
            return true;
        } catch (TimeoutException to) {
            return false;
        }
    }


}
