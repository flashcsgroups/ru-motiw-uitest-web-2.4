package ru.motiw.web.elements.elementsweb.DocflowAdministration.DocumentRegistrationCards;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Форма редактирования РКД - ПОЛЯ
 */
public class FormDocRegisterCardsEditFieldsElements {


    @FindBy(xpath = "//table[contains(@id,'treeview')]//td[1]/..")
    private ElementsCollection listFieldsDocument;

    @FindBy(xpath = "(//div[contains(@id,'tabbar')]/following-sibling::*)[1]//a[4]//span")
    private SelenideElement fieldsTab;

    @FindBy(xpath = "//div[count(a)=5]/a[1]//span")
    private SelenideElement addFieldDoc;

    @FindBy(xpath = "//div[count(a)=5]/a[2]//span")
    private SelenideElement editFieldDoc;

    @FindBy(xpath = "//div[count(a)=5]/a[3]//span")
    private SelenideElement delFieldDoc;

    @FindBy(xpath = "//div[count(a)=5]/a[4]//span")
    private SelenideElement moveFieldUp;

    @FindBy(xpath = "//div[count(a)=5]/a[5]//span")
    private SelenideElement moveFieldDown;

    @FindBy(xpath = "(//a[contains(@id,'button')][ancestor::div[contains(@id,'toolbar') and contains(@class,'x-toolbar x-docked x-toolbar-default')]]//span[string-length(text())>=3])[2]")
    private SelenideElement clickSaveAsAllChangesInDocument;

    @FindBy(xpath = "(//a[contains(@id,'button')][ancestor::div[contains(@id,'toolbar') and contains(@class,'x-toolbar x-docked x-toolbar-default')]]//span[string-length(text())>=3])[3]")
    private SelenideElement clickDelDocument;

    @FindBy(xpath = "(//a[contains(@id,'button')][ancestor::div[contains(@id,'toolbar') and contains(@class,'x-toolbar x-docked x-toolbar-default')]]//span[string-length(text())>=3])[4]")
    private SelenideElement backAndDiscardChanges;

    @FindBy(xpath = "//input[@name='fieldname']")
    private SelenideElement nameFieldDocument;

    @FindBy(xpath = "//input[@name='fieldid']")
    private SelenideElement identifierFieldDocument;

    @FindBy(xpath = "//input[@name='fieldtype']")
    private SelenideElement fieldTypeField;

    @FindBy(xpath = "//input[@name='id_format']")
    private SelenideElement formatFieldNumber;

    @FindBy(xpath = "//input[@name='isedited']")
    private SelenideElement editableField;

    @FindBy(xpath = "//input[@name='isnecessary']")
    private SelenideElement obligatoryField;

    @FindBy(xpath = "//input[@name='isunique']")
    private SelenideElement uniqueField;

    @FindBy(xpath = "//input[@name='ishideincreate']")
    private SelenideElement hideForCreation;

    @FindBy(xpath = "//input[@name='ishideintable']")
    private SelenideElement hideInTables;

    @FindBy(xpath = "//input[@name='ishideinsearch']")
    private SelenideElement hideForSearch;

    @FindBy(xpath = "//input[@name='ishideincard']")
    private SelenideElement hideInCards;

    @FindBy(xpath = "//input[@name='isuseforlinkeddoc']")
    private SelenideElement usedToCreateTheLinkedDocumentField;

    @FindBy(xpath = "//input[@name='default_value']")
    private SelenideElement defValueField;

    @FindBy(xpath = "//input[@name='isedited_increate']")
    private SelenideElement editionAvailableWhileCreationField;

    @FindBy(xpath = "//input[@name='emp_is_controller']")
    private SelenideElement clickDocumentSuperviserField;

    @FindBy(xpath = "//input[@name='emp_for_information']")
    private SelenideElement forInformationField;

    @FindBy(xpath = "//input[@name='document_template']")
    private SelenideElement displayNameTemplateField;

    @FindBy(xpath = "//input[@name='selectmode']")
    private SelenideElement directoryEntriesSelectionField;

    @FindBy(xpath = "//input[@name='dictionary_template']")
    private SelenideElement directoryTemplate;

    @FindBy(xpath = "//input[@name='select_only_dictionary']")
    private SelenideElement selOnlyFromDictionary;

    @FindBy(xpath = "//input[@name='fieldlength']")
    private SelenideElement fieldLength;

    @FindBy(xpath = "//li[text()='Число']")
    private SelenideElement typeFieldNumber;

    @FindBy(xpath = "//li[text()='Дата']")
    private SelenideElement typeFieldDate;

    @FindBy(xpath = "//li[text()='Строка']")
    private SelenideElement typeFieldString;

    @FindBy(xpath = "//input[@name='id_meta_dictionary']/../../div[2]")
    private SelenideElement typeFieldStringOrTextDirectory;

    @FindBy(xpath = "//li[text()='Текст']")
    private SelenideElement typeFieldText;

    @FindBy(xpath = "//li[text()='Словарь']")
    private SelenideElement typeFieldDictionary;

    @FindBy(xpath = "//span[contains(@id,'button')]/span[string-length(text())>=5]/../../../..//div/input[@name='id_cataloguetype']")
    private SelenideElement clickFieldDictionary;

    @FindBy(xpath = "//li[text()='Подразделение']")
    private SelenideElement typeFieldDepartment;

    @FindBy(xpath = "//li[text()='Сотрудник']")
    private SelenideElement typeFieldEmployee;

    @FindBy(xpath = "//li[text()='Документ']")
    private SelenideElement typeFieldDocument;

    @FindBy(xpath = "//div[contains(@id,'tabbar')]//div[count(a)=2]/a[2]//span")
    private SelenideElement tabSearch;

    @FindBy(xpath = "//div[contains(@id,'tabbar')]//div[count(a)=2]/a[1]//span")
    private SelenideElement tabFields;

    @FindBy(name = "search_documents")
    private SelenideElement searchSimiliarDocuments;

    @FindBy(xpath = "//textarea[@name='search_template']")
    private SelenideElement searchRulesTemplate;

    @FindBy(xpath = "//li[text()='Нумератор']")
    private SelenideElement typeFieldNumerator;

    @FindBy(xpath = "//input[@name='numerator_template']")
    private SelenideElement numeratorTemplate;

    @FindBy(xpath = "//li[text()='Справочник']")
    private SelenideElement typeFieldDirectories;

    @FindBy(xpath = "(//span[string-length(*[text()])>=3]/ancestor::div[contains(@id,'toolbar')]//a)[5]//span")
    private SelenideElement buttonSaveField;

    @FindBy(xpath = "(//span[string-length(*[text()])>=3]/ancestor::div[contains(@id,'toolbar')]//a)[6]//span")
    private SelenideElement buttonCancelField;

    /**
     * Вкладка - Поля
     */
    public SelenideElement getFieldsTab() {
        return fieldsTab;
    }

    /**
     * Добавить поле
     */
    public SelenideElement getAddFieldDoc() {
        return addFieldDoc;
    }

    /**
     * Редактировать поле
     */
    public SelenideElement getEditFieldDoc() {
        return editFieldDoc;
    }

    /**
     * Удалить поле
     */
    public SelenideElement getDelFieldDoc() {
        return delFieldDoc;
    }

    /**
     * Переместить поле вврех
     */
    public SelenideElement getMoveFieldUp() {
        return moveFieldUp;
    }

    /**
     * Переместить поле вниз
     */
    public SelenideElement getMoveFieldDown() {
        return moveFieldDown;
    }

    /**
     * Сохранить как - изменения по документу
     */
    public SelenideElement getClickSaveAsAllChangesInDocument() {
        return clickSaveAsAllChangesInDocument;
    }

    /**
     * Удалить документ
     */
    public SelenideElement getDeleteDocument() {
        return clickDelDocument;
    }

    /**
     * Вернуться без сохранения
     */
    public SelenideElement getBackAndDiscardChanges() {
        return backAndDiscardChanges;
    }

    /**
     * Название поля
     */
    public SelenideElement getNameFieldDocument() {
        return nameFieldDocument;
    }

    /**
     * Идентификатор поля
     */
    public SelenideElement getIdentifierFieldDocument() {
        return identifierFieldDocument;
    }

    /**
     * выбор поля - Тип поля
     */
    public SelenideElement getFieldTypeField() {
        return fieldTypeField;
    }

    /**
     * Формат типа поля - Числа
     */
    public SelenideElement getFormatFieldNumber() {
        return formatFieldNumber;
    }

    /**
     * Изменяемое при редактировании
     */
    public SelenideElement getEditableField() {
        return editableField;
    }

    /**
     * Обязательное поле
     */
    public SelenideElement getObligatoryField() {
        return obligatoryField;
    }

    /**
     * Уникальное поле
     */
    public SelenideElement getUniqueField() {
        return uniqueField;
    }

    /**
     * Скрывать при создании
     */
    public SelenideElement getHideForCreation() {
        return hideForCreation;
    }

    /**
     * Скрывать в таблицах
     */
    public SelenideElement getHideInTables() {
        return hideInTables;
    }

    /**
     * Скрывать при поиске
     */
    public SelenideElement getHideForSearch() {
        return hideForSearch;
    }

    /**
     * Скрывать в карточке
     */
    public SelenideElement getHideInCards() {
        return hideInCards;
    }

    /**
     * Использовать при создании связанного документа
     */
    public SelenideElement getUsedToCreateTheLinkedDocumentField() {
        return usedToCreateTheLinkedDocumentField;
    }

    /**
     * Значение по умолчанию (для полей типа - Дата и Сотрудник)
     */
    public SelenideElement getDefValueField() {
        return defValueField;
    }

    /**
     * Изменяемое при создании (поля типа - Дата; Сотрудник; Нумератор)
     */
    public SelenideElement getEditionAvailableWhileCreationField() {
        return editionAvailableWhileCreationField;
    }

    /**
     * Контролер документа
     */
    public SelenideElement getDocumentSuperviserField() {
        return clickDocumentSuperviserField;
    }

    /**
     * Для сведения
     */
    public SelenideElement getForInformationField() {
        return forInformationField;
    }

    /**
     * Шаблон отображения
     */
    public SelenideElement getDisplayNameTemplateField() {
        return displayNameTemplateField;
    }

    /**
     * Выбор записей справочника
     */
    public SelenideElement getDirectoryEntriesSelectionField() {
        return directoryEntriesSelectionField;
    }

    /**
     * Шаблон справочника
     */
    public SelenideElement getDirectoryTemplate() {
        return directoryTemplate;
    }

    /**
     * Выбор только из справочника
     */
    public SelenideElement getSelOnlyFromDictionary() {
        return selOnlyFromDictionary;
    }

    /**
     * Длина поля
     */
    public SelenideElement getFieldLength() {
        return fieldLength;
    }

    /**
     * ===ТИПЫ ПОЛЕЙ
     * Число
     */
    public SelenideElement getTypeFieldNumber() {
        return typeFieldNumber;
    }

    /**
     * Дата
     */
    public SelenideElement getTypeFieldDate() {
        return typeFieldDate;
    }

    /**
     * Строка
     */
    public SelenideElement getTypeFieldString() {
        return typeFieldString;
    }

    /**
     * Выбор поля - Справочник для поля типа Строка/Текст/Справочник
     */
    public SelenideElement getTypeFieldStringOrTextDirectory() {
        return typeFieldStringOrTextDirectory;
    }

    /**
     * Текст
     */
    public SelenideElement getTypeFieldText() {
        return typeFieldText;
    }

    /**
     * Словарь
     */
    public SelenideElement getTypeFieldDictionary() {
        return typeFieldDictionary;
    }

    /**
     * Выбор поля Словарь для выбора значения
     */
    public SelenideElement getFieldDictionary() {
        return clickFieldDictionary;
    }

    /**
     * Подразделение
     */
    public SelenideElement getTypeFieldDepartment() {
        return typeFieldDepartment;
    }

    /**
     * Сотрудник
     */
    public SelenideElement getTypeFieldEmployee() {
        return typeFieldEmployee;
    }

    /**
     * Документ
     */
    public SelenideElement getTypeFieldDocument() {
        return typeFieldDocument;
    }

    /**
     * Вкладка - Поиск - в форме редактирования поля типа "Документ"
     */
    public SelenideElement getTabSearch() {
        return tabSearch;
    }

    /**
     * Вкладка - Поля - в форме редактирования поля типа "Документ"
     */
    public SelenideElement getTabFields() {
        return tabFields;
    }

    /**
     * Искать похожие документы
     */
    public SelenideElement getSearchSimiliarDocuments() {
        return searchSimiliarDocuments;
    }

    /**
     * Правила поиска
     */
    public SelenideElement getSearchRulesTemplate() {
        return searchRulesTemplate;
    }

    /**
     * Нумератор
     */
    public SelenideElement getTypeFieldNumerator() {
        return typeFieldNumerator;
    }

    /**
     * Шаблон нумератора
     */
    public SelenideElement getNumeratorTemplate() {
        return numeratorTemplate;
    }

    /**
     * Справочник
     */
    public SelenideElement getTypeFieldDirectories() {
        return typeFieldDirectories;
    }

    /**
     * Сохранить поле
     */
    public SelenideElement getButtonSaveField() {
        return buttonSaveField;
    }

    /**
     * Отменить - сохранение поля
     */
    public SelenideElement getButtonCancelField() {
        return buttonCancelField;
    }

    /**
     * Список полей документа
     */
    public ElementsCollection getListFieldsDocument() {
        return listFieldsDocument;
    }


}
