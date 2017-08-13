package ru.motiw.web.elements.elementsweb.Administration.Users;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Страница - Администрирование/Пользователи (Пользователи)
 */
public class UsersElements {


    @FindBy(xpath = "//input[@id='workflow']")
    private SelenideElement workflowCheckBox;

    @FindBy(xpath = "//*[contains (@class, 'x-editor')][contains (@style, 'visible')]//input")
    private SelenideElement visibleEditor;

    @FindBy(xpath = "//input[@id='docflow']")
    private SelenideElement docflowCheckBox;

    @FindBy(xpath = "(//*[contains (@class, 'footer')]//button)[1]")
    private SelenideElement buttonSaveAlias;

    @FindBy(xpath = "//*[contains (@class, 'button_s')]")
    private SelenideElement buttonSearchDepForAlias;

    @FindBy(xpath = "//*[@id='searchField']")
    private SelenideElement fieldSearchDepForAlias;

    @FindBy(xpath = "//*[contains (@class, 'selected')][contains (@class, 'tree-node')]/*[@type='checkbox'] ")
    private SelenideElement selectedCheckBox;

    @FindBy(xpath = "(//body[@id='createuser']/div[3]//a[contains(@id,'button-')])[1]")
    private SelenideElement buttonAddUser;

    @FindBy(xpath = "(//body[@id='createuser']/div[3]//a[contains(@id,'button-')])[2]")
    private SelenideElement buttonDelUser;

    @FindBy(xpath = "(//body[@id='createuser']/div[3]//a[contains(@id,'button-')])[4]")
    private SelenideElement createAnAlias;

    @FindBy(xpath = "//tr/td[2]//input")
    private SelenideElement lastNameField;

    @FindBy(xpath = "//tr/td[3]//input")
    private SelenideElement nameField;

    @FindBy(xpath = "//tr/td[4]//input")
    private SelenideElement patronymicField;

    @FindBy(xpath = "//input[@type='radio' and @name='sex']/.. /../div[2]/input")
    private SelenideElement checkWoman;

    @FindBy(xpath = "(//input[@type='radio' and @name='sex']/.. /../div/input)[1]")
    private SelenideElement checkMan;

    @FindBy(xpath = "//img[contains(@src,'data')]/../input")
    private SelenideElement birthDateField;

    @FindBy(xpath = "//*[contains (@class, 'bwrap')]/*[contains (@class, 'layout')]/div/input")
    private SelenideElement jobTitle;

    @FindBy(xpath = "(//*[@class='x-grid3-body']/div[2]//div)[2]")
    private SelenideElement nameUserLogin;

    @FindBy(xpath = "(//*[@class='x-grid3-body']/div[3]//div)[2]")
    private SelenideElement fieldPassword;

    @FindBy(xpath = "(//*[@class='x-grid3-body']/div[4]//div)[2]")
    private SelenideElement confirmationPassword;

    @FindBy(xpath = "(//*[@class='x-grid3-body']/div[5]//div)[2]")
    private SelenideElement status;

    @FindBy(xpath = "(//*[@class='x-grid3-body']/div[6]//div)[2]")
    private SelenideElement additionalNumber;

    @FindBy(xpath = "(//*[@class='x-grid3-body']/div[7]//div)[2]")
    private SelenideElement userForcedSorting;

    @FindBy(xpath = "//input[contains(@id,'needpasswordchange') and @type='checkbox']")
    private SelenideElement needPasswordChange;

    @FindBy(xpath = "(//table[3]/../table[1]//button)[1]")
    private SelenideElement saveUser;


    /**
     * Чекбокс - Workflow
     */
    public SelenideElement getWorkflow() {
        return workflowCheckBox;
    }

    /**
     * Чекбокс - Docflow
     */
    public SelenideElement getDocflow() {
        return docflowCheckBox;
    }

    /**
     * Поле ввода в форме реквизитов пользователя
     */
    public SelenideElement getVisibleEditor() {
        return visibleEditor;
    }

    /**
     * Кнопка сохранить окна добавления псевдонима в подразделение
     */
    public SelenideElement getButtonSaveAlias() {
        return buttonSaveAlias;
    }

    /**
     * Кнопка поиска окна добавления псевдонима в подразделение
     */
    public SelenideElement getButtonSearchDepForAlias() {
        return buttonSearchDepForAlias;
    }

    /**
     * Поле поиска окна добавления псевдонима в подразделение
     */
    public SelenideElement getFieldSearchDepForAlias() {
        return fieldSearchDepForAlias;
    }

    /**
     * Выбор подразделения в форме
     */
    public SelenideElement getSelectedCheckBox() {
        return selectedCheckBox;
    }

    /**
     * Кнопка - Добавить пользователя
     */
    public SelenideElement getButtonAddUser() {
        return buttonAddUser;
    }

    /**
     * Кнопка - Удалить пользователя
     */
    public SelenideElement getRemoveUser() {
        return buttonDelUser;
    }

    /**
     * Кнопка - Создать псевдоним
     */
    public SelenideElement getCreateAnAlias() {
        return createAnAlias;
    }

    /**
     * Фамилия
     */
    public SelenideElement getLastName() {
        return lastNameField;
    }

    /**
     * Имя
     */
    public SelenideElement getName() {
        return nameField;
    }

    /**
     * Отчество
     */
    public SelenideElement getPatronymic() {
        return patronymicField;
    }

    /**
     * Женщина
     */
    public SelenideElement getCheckWoman() {
        return checkWoman;
    }

    /**
     * Мужчина
     */
    public SelenideElement getCheckMan() {
        return checkMan;
    }

    /**
     * Дата рождения
     */
    public SelenideElement getBirthDateField() {
        return birthDateField;
    }

    /**
     * Должность
     */
    public SelenideElement getJobTitle() {
        return jobTitle;
    }

    /**
     * Имя пользователя (login)
     */
    public SelenideElement getNameUserLogin() {
        return nameUserLogin;
    }

    /**
     * Пароль
     */
    public SelenideElement getPasswordUser() {
        return fieldPassword;
    }

    /**
     * Подтверждение пароля
     */
    public SelenideElement getСonfirmationPassword() {
        return confirmationPassword;
    }

    /**
     * Статус
     */
    public SelenideElement getStatus() {
        return status;
    }

    /**
     * Дополнительный номер
     */
    public SelenideElement getAdditionalNumber() {
        return additionalNumber;
    }

    /**
     * Порядок пользователя при принудительной сортировке
     */
    public SelenideElement getUserForcedSorting() {
        return userForcedSorting;
    }

    /**
     * Сменить пароль при следующем входе
     */
    public SelenideElement getNeedPasswordChange() {
        return needPasswordChange;
    }

    /**
     * Сохранить пользователя
     */
    public SelenideElement getSave() {
        return saveUser;
    }


}
