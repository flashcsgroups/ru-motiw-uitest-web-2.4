package ru.motiw.web.elements.elementsweb.Tasks.TaskForm;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.motiw.web.model.Administration.Users.Employee;

import static com.codeborne.selenide.Selenide.$;

/**
 * Элементы - форма - Выбора пользователей в роли
 */
public class UsersSelectTheFormElements {


    @FindBy(css = "#serverSearch")
    private SelenideElement userSearchField;

    @FindBy(xpath = "//*[@id='btn_save']//button")
    private SelenideElement userSaveButton;

    /**
     * Поиск пользователя в гриде
     */
    public SelenideElement getUserSearchField() {
        return userSearchField;
    }

    /**
     * Добавить пользователя
     */
    public SelenideElement getAddTheUserInTheFormOfChoice(Employee employee) {
        return $(By.xpath("//a[contains(@href,'/userinfo/')][contains(text(),'" + employee.getName() + " "
                + employee.getPatronymic() + "')]/../../..//img[(@onclick)]"));
    }

    /**
     * Сохранить
     */
    public SelenideElement getUserSaveButton() {
        return userSaveButton;
    }
}
