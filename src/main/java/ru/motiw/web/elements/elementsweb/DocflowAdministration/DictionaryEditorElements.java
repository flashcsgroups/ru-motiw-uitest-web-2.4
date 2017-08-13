package ru.motiw.web.elements.elementsweb.DocflowAdministration;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Администрирование ДО/Редактор словарей
 */
public class DictionaryEditorElements {

    @FindBy(xpath = "//table[contains(@id,'treeview')]")
    private ElementsCollection tableGridTreeView;

    @FindBy(xpath = "(//a//preceding-sibling::span)[1]")
    private SelenideElement addDictionaryEditor;

    @FindBy(xpath = "(//a//preceding-sibling::span)[2]")
    private SelenideElement editDictionaryEditor;

    @FindBy(xpath = "(//a//preceding-sibling::span)[3]")
    private SelenideElement delDictionaryEditor;

    @FindBy(id = "dict_name-inputEl")
    private SelenideElement nameDictionaryEditor;

    @FindBy(xpath = "(//tr[count(td)=3]//td) [1]//span[@data-ref=\"displayEl\"]")
    private SelenideElement clickAccessAvailableToAll;

    /*
    @FindBy(xpath = "(//tr[count(td)=3]//td)[2]//span[@data-ref]")
    private SelenideElement clickReadOnly;
    */

    @FindBy(xpath = "(//tr[count(td)=3]//td) [2]//span[@data-ref=\"displayEl\"]")
    private SelenideElement clickReadOnly;


    @FindBy(xpath = "(//tr[count(td)=3]//td) [3]//span[@data-ref=\"displayEl\"]")
    private SelenideElement clickPersonal;

    @FindBy(xpath = "//span[@id='bAddWord-btnEl']")
    private SelenideElement addDictionaryEditorItem;

    @FindBy(xpath = "//span[@id='bEditWord-btnWrap']")
    private SelenideElement editDictionItem;

    @FindBy(xpath = "//span[@id='bDeleteWord']")
    private SelenideElement delDictionItem;

    @FindBy(xpath = "//span[@id='bUpWord']")
    private SelenideElement upDictionItem;

    @FindBy(xpath = "//span[@id='bDownWord']")
    private SelenideElement downDictionItem;

    @FindBy(xpath = "//input[@id='name_word-inputEl']")
    private SelenideElement nameDictionaryEditorItem;

    @FindBy(xpath = "//textarea[@id='value_word-inputEl']")
    private SelenideElement descriptionDictionaryEditorItem;

    @FindBy(xpath = "(//span[contains(@id,'button')])[8]")
    private SelenideElement saveDictionaryEditorItem;

    @FindBy(xpath = "(//span[contains(@id,'button')])[7]")
    private SelenideElement cancelDictionaryEditorItem;

    @FindBy(xpath = "(//span[contains(@id,'bSave-')]/span[last()])[2]")
    private SelenideElement saveChanges;

    @FindBy(xpath = "//span[@id='bSaveAs-btnIconEl']")
    private SelenideElement saveAsChanges;

    @FindBy(xpath = "//div[count(a)=3 and contains(@id,'toolbar')]/a[3]//preceding-sibling::span")
    private SelenideElement backWithoutSaving;


    /**
     * Список объектов - Словарь
     */
    public ElementsCollection getTableGridTreeView() {
        return tableGridTreeView;
    }

    /**
     * Добавить словарь
     */
    public SelenideElement getAddDictionaryEditor() {
        return addDictionaryEditor;
    }

    /**
     * Редактировать словарь
     */
    public SelenideElement getEditDictionaryEditor() {
        return editDictionaryEditor;
    }

    /**
     * Удалить словарь
     */
    public SelenideElement getDelDictionaryEditor() {
        return delDictionaryEditor;
    }

    /**
     * Название словаря
     */
    public SelenideElement getNameDictionaryEditor() {
        return nameDictionaryEditor;
    }

    /**
     * Отображение - Общедоступная
     */
    public SelenideElement getClickAccessAvailableToAll() {
        return clickAccessAvailableToAll;
    }

    /**
     * Отображение - Только для чтения
     */
    public SelenideElement getClickReadOnly() {
        return clickReadOnly;
    }

    /**
     * Отображение - Личный словарь
     */
    public SelenideElement getClickPersonal() {
        return clickPersonal;
    }

    /**
     * Доабвить значение словаря
     */
    public SelenideElement getAddDictionaryEditorItem() {
        return addDictionaryEditorItem;
    }

    /**
     * Редактировать значения словаря
     */
    public SelenideElement getEditDictionItem() {
        return editDictionItem;
    }

    /**
     * Удалить значение словаря
     */
    public SelenideElement getDelDictionItem() {
        return delDictionItem;
    }

    /**
     * Переместить вверх значение словаря
     */
    public SelenideElement getUpDictionItem() {
        return upDictionItem;
    }

    /**
     * Переместить вниз значение словаря
     */
    public SelenideElement getDownDictionItem() {
        return downDictionItem;
    }

    /**
     * Название элемента словаря
     */
    public SelenideElement getNameDictionaryEditorItem() {
        return nameDictionaryEditorItem;
    }

    /**
     * Описание элемента словаря
     */
    public SelenideElement getDescriptionDictionaryEditorItem() {
        return descriptionDictionaryEditorItem;
    }

    /**
     * Сохранить элемента словаря
     */
    public SelenideElement getSaveDictionaryEditorItem() {
        return saveDictionaryEditorItem;
    }

    /**
     * Отменить сохранения элемента словаря
     */
    public SelenideElement getCancelDictionaryEditorItem() {
        return cancelDictionaryEditorItem;
    }

    /**
     * Сохранить изменения по объекту Словарь
     */
    public SelenideElement getSaveChanges() {
        return saveChanges;
    }

    /**
     * Сохранить как
     */
    public SelenideElement getSaveAsChanges() {
        return saveAsChanges;
    }

    /**
     * Вернуться без сохранения
     */
    public SelenideElement getBackWithoutSaving() {
        return backWithoutSaving;
    }
}

