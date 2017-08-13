package ru.motiw.web.elements.elementsweb.Administration.Users;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Страница - Администрирование/Пользователи (Подразделения)
 */
public class DepartmentElements {


    @FindBy(id = "searchQueryEdit")
    private SelenideElement search;

    @FindBy(xpath = "//tr[@data-recordid]//span")
    private ElementsCollection arrayDepartments;

    @FindBy(xpath = "//tr[not(contains(@class, 'expanded'))]//img[contains (@class, 'expander')]")
    private SelenideElement closedNode;

    @FindBy(xpath = "(//div[contains(@id,'panel-')]//a[1])[1]")
    private SelenideElement buttonAddDep;

    @FindBy(xpath = "(//div[contains(@id,'panel-')]//a[2])[1]")
    private SelenideElement buttonEditDep;

    @FindBy(xpath = "(//div[contains(@id,'panel-')]//a[3])[1]")
    private SelenideElement buttonRemoveDep;

    @FindBy(id = "dep_name-inputEl")
    private SelenideElement nameDepartment;

    @FindBy(id = "add_number-inputEl")
    private SelenideElement conditionalNumber;

    @FindBy(id = "counter-inputEl")
    private SelenideElement counter;

    @FindBy(id = "reset_date-inputEl")
    private SelenideElement resetDate;

    @FindBy(id = "numerator_template-inputEl")
    private SelenideElement numeratorTemplate;

    @FindBy(xpath = "(//span[contains(@id,'button') and @class='x-btn-wrap'] [ancestor::div[@id='editDepWin']])[1]/span/span[last()]")
    private SelenideElement saveDepartment;

    @FindBy(xpath = "(//span[contains(@id,'button') and @class='x-btn-wrap'] [ancestor::div[@id='editDepWin']])[2]/span/span[last()]")
    private SelenideElement cancellationOfCreatingDivisions;

    @FindBy(xpath = "//*[contains (@class, 'x-mask')]")
    private SelenideElement maskDepartment;

    @FindBy(xpath = "//*[contains (@class, 'message')][contains (@class, 'closable')]/div[contains (@id, 'toolbar')]//a[2]//span[contains (@class, 'icon')]")
    private SelenideElement savePermissionYes;

    @FindBy(xpath = "//span[contains(@id,'-btnWrap')]//span[text()='Да']/../span[contains(@id,'-btnIconEl')]")
    private SelenideElement oKRemoveDelete;

    @FindBy(xpath = "//tr[not(contains(@class, 'expanded'))]/td/div/img[contains (@class, 'expander')]")
    private SelenideElement disclosureComboUnitsUser;

    @FindBy(xpath = "//div[count(div)=3]/div[2]//div[contains(@id,'messagebox') and (@data-errorqtip)]")
    private SelenideElement getExpectedMessageTextToDialog;

    /**
     * Строка поиска
     */
    public SelenideElement getSearch() {
        return search;
    }

    /**
     * Выбрать корневое подразделение
     */
    public SelenideElement getFirstDepartments() {
        SelenideElement firstDepartments = arrayDepartments.first();
        return firstDepartments;
    }

    /**
     * Свернутый элемент дерева подразделений
     */
    public SelenideElement getClosedNode() {
        return closedNode;
    }

    /**
     * Добавить подразделение
     */
    public SelenideElement getButtonAddDepartment() {
        return buttonAddDep;
    }

    /**
     * Кнопка - Редактировать подразделение
     */
    public SelenideElement getEditDepartment() {
        return buttonEditDep;
    }

    /**
     * Кнопка - Удаление подразделения
     */
    public SelenideElement getRemoveDepartment() {
        return buttonRemoveDep;
    }

    /**
     * Наименование подразделения
     */
    public SelenideElement getNameDepartment() {
        return nameDepartment;
    }

    /**
     * Условный номер
     */
    public SelenideElement getConditionalNumber() {
        return conditionalNumber;
    }

    /**
     * Значение счетчика
     */
    public SelenideElement getCounter() {
        return counter;
    }

    /**
     * Шаблон нумератора
     */
    public SelenideElement getResetDate() {
        return resetDate;
    }

    /**
     * Дата обнуления
     */
    public SelenideElement getNumeratorTemplate() {
        return numeratorTemplate;
    }

    /**
     * Сохранить подразделение
     */
    public SelenideElement getSaveDepartment() {
        return saveDepartment;
    }

    /**
     * Отменить сохранение подразделения
     */
    public SelenideElement getCancellationOfCreatingDivisions() {
        return cancellationOfCreatingDivisions;
    }

    /**
     * Маска при клике подразделений
     */
    public SelenideElement getMaskDepartment() {
        return maskDepartment;
    }

    /**
     * Кнопка - Да, в форме предупреждений - Вы уверены, что хотите удалить подразделение? или
     * Сохранить дополнительные полномочия пользователей?
     */
    public SelenideElement getSavePermissionYes() {
        return savePermissionYes;
    }

    /**
     * Кнопка Ок - в диалоге подтверждения удаления подразделения
     */
    public SelenideElement getOKRemoveDelete() {
        return oKRemoveDelete;
    }

    /**
     * Комбик для раскрытия подразделений
     */
    public SelenideElement getDisclosureComboUnitsUser() {
        return disclosureComboUnitsUser;
    }

    /**
     * Элемент - Сообщение в диалоге при ДнД/Удалении - Подразделение
     */
    public SelenideElement getExpectedMessageTextToDialog() {
        return getExpectedMessageTextToDialog;
    }

}