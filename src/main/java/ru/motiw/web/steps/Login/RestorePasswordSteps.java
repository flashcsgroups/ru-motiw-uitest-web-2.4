package ru.motiw.web.steps.Login;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import ru.motiw.web.steps.BaseSteps;
import ru.motiw.web.elements.elementsweb.Login.RestorePasswordElements;
import ru.motiw.web.model.Administration.Users.Employee;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Стараница сброса пароля пользователя
 */
public class RestorePasswordSteps extends BaseSteps {

    private RestorePasswordElements restorePasswordElements = page(RestorePasswordElements.class);

    /**
     * Новый Пароль
     *
     * @param newPass
     */
    public RestorePasswordSteps selPasswordField(String newPass) {
        restorePasswordElements.getNewPasswordField().setValue(newPass);
        return this;
    }

    /**
     * Подтверждение нового пароля
     *
     * @param newPassConf
     */
    public RestorePasswordSteps selPasswordConfirmField(String newPassConf) {
        restorePasswordElements.getNewPasswordConfirmField().setValue(newPassConf);
        return this;
    }

    /**
     * Смена пароля пользователя
     *
     * @param user передаваемые значения нового пароля пользователя
     */
    public RestorePasswordSteps passwordChange(Employee user) {
        ensurePageLoaded()
                .selPasswordField(user.getNewPassword())
                .selPasswordConfirmField(user.getNewСonfirmationPassword());
        restorePasswordElements.getButtonToSend().click();
        restorePasswordElements.getHome().click();
        return this;
    }

    /**
     * Ожидание элементов - текст - "Вам необходимо сменить" И newpass
     */
    public RestorePasswordSteps ensurePageLoaded() {
        $(By.id("newpass_confirm-inputEl")).shouldBe(Condition.visible);
        $(By.xpath("//label[text()]"))
                .shouldHave(Condition.exactText("Вам необходимо сменить пароль"));
        return this;
    }

}
