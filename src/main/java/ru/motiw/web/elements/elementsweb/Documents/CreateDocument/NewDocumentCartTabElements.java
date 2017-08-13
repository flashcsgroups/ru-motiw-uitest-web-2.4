package ru.motiw.web.elements.elementsweb.Documents.CreateDocument;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Элементы - форма Создания документа (Документы / Создать документ) - вкладка - Карточка
 */
public class NewDocumentCartTabElements {

    @FindBy(xpath = "//div[@class='x-column-inner']/table[1]")
    private SelenideElement saveDocument;

    @FindBy(xpath = "//div[@class='x-column-inner']/table[2]")
    private SelenideElement saveAndCreateNewDocument;

    @FindBy(xpath = "//div[@class='x-column-inner']/table[4]")
    private SelenideElement createWithResolution;

    @FindBy(xpath = "//div[@class='x-column-inner']/table[3]")
    private SelenideElement searchButton;

    @FindBy(xpath = "//div[@id='addFileButtonDiv']//input")
    private SelenideElement addFileButtonDiv;

    @FindBy(xpath = "//div[@id='btnScaner']//button")
    private SelenideElement btnScaner;

    @FindBy(xpath = "//input[contains(@id,'ext-comp')][ancestor::div[contains(@style,'visibility: visible')]]")
    private SelenideElement inputField;

    @FindBy(xpath = "//input[contains(@id,'taSearchID')][ancestor::div[contains(@style,'visibility: visible')]]")
    private SelenideElement input2Field;

    @FindBy(xpath = "//input[contains(@id,'_name_ext')][ancestor::div[contains(@style,'visibility: visible')]]")
    private SelenideElement input3Field;

    @FindBy(css = "body")
    private SelenideElement ckeBody;

    @FindBy(xpath = "//*[contains (@class,'window-noborder')][contains (@style,'visible')]//td[contains (@class,'cell')][1]")
    private SelenideElement buttonSaveDescription;

    @FindBy(css = "#searchField")
    private SelenideElement searchFieldDepartment;

    @FindBy(xpath = "//*[contains (@class, 'selected')][contains (@class, 'tree-node')]/*[@type='checkbox'] ")
    private SelenideElement selectedCheckBox;

    @FindBy(xpath = "(//button[not(contains(@style,'url'))])[1]")
    private SelenideElement buttonSave;

    @FindBy(xpath = "(//button[not(contains(@style,'url'))])[2]")
    private SelenideElement buttonCancel;

    @FindBy(css = "#SearchEdit")
    private SelenideElement searchEmployee;

    @FindBy(xpath = "//div[contains(text(),'Дата регистрации')]/../../td[2]//div")
    private SelenideElement fieldRegistrationDate;

    @FindBy(xpath = "//div[count(img)=2]//img[2]")
    private SelenideElement newProject;

    @FindBy(xpath = "//div[count(img)=2]//img[1]")
    private SelenideElement selectExistentProject;

    @FindBy(xpath = "(//span[contains(@class,'x-tab-strip')][ancestor::li[contains(@class,'x-tab-strip-active')]])[2]")
    private SelenideElement documentCartTab;

    @FindBy(xpath = "(//div[@class='x-form-field-wrap x-form-field-trigger-wrap'])[2]/child::input")
    private SelenideElement fieldDocumentType;

    /**
     * Выбор списка - Тип документа
     */
    public SelenideElement getFieldDocumentType() {
        return fieldDocumentType;
    }

    /**
     * Вкладка - Карточка документа
     */
    public SelenideElement getDocumentCartTab() {
        return documentCartTab;
    }

    /**
     * Дата регистрации
     */
    public SelenideElement getFieldRegistrationDate() {
        return fieldRegistrationDate;
    }

    /**
     * Создвть новый проект
     */
    public SelenideElement getNewProject() {
        return newProject;
    }

    /**
     * Выбрать существующий проект
     */
    public SelenideElement getSelectExistentProject() {
        return selectExistentProject;
    }

    /**
     * input поля
     */
    public SelenideElement getInputField() {
        return inputField;
    }

    /**
     * input поля 2 (Для полей типа Строка с предопределенным спр-ом)
     */
    public SelenideElement getInput2Field() {
        return input2Field;
    }

    /**
     * input поля 3 (Для полей типа Сотрудник)
     */
    public SelenideElement getInput3Field() {
        return input3Field;
    }

    /**
     * Область редактирования поля типа Текст
     */
    public SelenideElement getCkeBody() {
        return ckeBody;
    }

    /**
     * Кнопка сохранения в форме расширенного текстового редактора (CKE)
     */
    public SelenideElement getButtonSaveDescription() {
        return buttonSaveDescription;
    }

    /**
     * Поле поиска в окне поля - типа "Подразделение"
     */
    public SelenideElement getSearchFieldDepartment() {
        return searchFieldDepartment;
    }

    /**
     * Чебокс подсвеченного узла окна добавления псевдонима в подразделение
     */
    public SelenideElement getSelectedCheckBox() {
        return selectedCheckBox;
    }

    /**
     * Сохранить - в окне поля - типа "Подразделение"
     */
    public SelenideElement getButtonSave() {
        return buttonSave;
    }

    /**
     * Сохранить - в окне поля - типа "Подразделение"
     */
    public SelenideElement getButtonCancel() {
        return buttonCancel;
    }

    /**
     * Поле Поиск в форме выбора пользователей
     */
    public SelenideElement getSearchEmployee() {
        return searchEmployee;
    }

    /**
     * Сохранить
     */
    public SelenideElement getSaveDocument() {
        return saveDocument;
    }

    /**
     * Сохранить и создать новый документ
     */
    public SelenideElement getSaveAndCreateNewDocument() {
        return saveAndCreateNewDocument;
    }

    /**
     * Создать резолюцию
     */
    public SelenideElement getCreateWithResolution() {
        return createWithResolution;
    }

    /**
     * Поиск
     */
    public SelenideElement getSearchButton() {
        return searchButton;
    }

    /**
     * Добавить файл
     */
    public SelenideElement getAddFileButtonDiv() {
        return addFileButtonDiv;
    }

    /**
     * Сканировать
     */
    public SelenideElement getBtnScaner() {
        return btnScaner;
    }


}
