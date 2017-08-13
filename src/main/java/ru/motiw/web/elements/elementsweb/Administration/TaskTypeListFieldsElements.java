package ru.motiw.web.elements.elementsweb.Administration;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Элементы полей типов объектов
 */
public class TaskTypeListFieldsElements {

    @FindBy(xpath = "//li[text()='Строка']")
    private SelenideElement typeFieldString;

    @FindBy(xpath = "//li[text()='Текст']")
    private SelenideElement typeFieldText;

    @FindBy(xpath = "//li[text()='Целое']")
    private SelenideElement typeFieldInteger;

    @FindBy(xpath = "//li[text()='Вещественное']")
    private SelenideElement typeFieldDouble;

    @FindBy(xpath = "//li[text()='Дата']")
    private SelenideElement typeFieldData;

    @FindBy(xpath = "//li[text()='Файл']")
    private SelenideElement typeFieldFile;

    @FindBy(xpath = "//li[text()='Cсылка на справочник']")
    private SelenideElement typeFieldReferenceToTheDictionary;

    @FindBy(xpath = "//li[text()='Множественная ссылка на справочник']")
    private SelenideElement typeFieldMultipleReferenceToTheDictionary;

    @FindBy(xpath = "//li[text()='Логический']")
    private SelenideElement typeFieldBoolean;

    @FindBy(xpath = "//li[text()='Телефон']")
    private SelenideElement typeFieldPhone;

    @FindBy(xpath = "//li[text()='Email']")
    private SelenideElement typeFieldEmail;

    @FindBy(xpath = "//li[text()='Изображение']")
    private SelenideElement typeFieldImage;

    @FindBy(xpath = "//li[text()='Цвет']")
    private SelenideElement typeFieldColor;

    @FindBy(xpath = "//li[text()='Подразделение']")
    private SelenideElement typeFieldDepartment;

    @FindBy(xpath = "//li[text()='Таблица']")
    private SelenideElement typeFieldTable;

    @FindBy(xpath = "//li[text()='Сотрудник']")
    private SelenideElement typeFieldEmployee;

    @FindBy(xpath = "//li[text()='Cсылка на задачу']")
    private SelenideElement typeFieldReferenceTask;

    @FindBy(xpath = "//li[text()='Нумератор']")
    private SelenideElement typeFieldNumerator;

    @FindBy(xpath = "//li[text()='Ссылка на объект']")
    private SelenideElement typeFieldLinkObject;

    @FindBy(xpath = "//li[text()='Ссылка на библиотеку']")
    private SelenideElement typeFieldLibrarylink;

    @FindBy(xpath = "//li[text()='Вложенный справочник']")
    private SelenideElement typeFieldEnclosedDirectory;

    @FindBy(id = "dialog_form_name-inputEl")
    private SelenideElement nameField;

    @FindBy(id = "dialog_form_identifier-inputEl")
    private SelenideElement nameIdentifier;

    @FindBy(css = "#dialog_form_type-inputEl")
    private SelenideElement choiceTypeField;

    @FindBy(css = "#dialog_form_isnecessary-inputEl")
    private SelenideElement obligatoryField;

    @FindBy(xpath = "//span[contains(@id,'-btnIconEl')] [ancestor::div[contains(@id,'window-')]]")
    private SelenideElement saveField;

    @FindBy(id = "dialog_form_ref_type-inputEl")
    private SelenideElement linkObject;

    @FindBy(css = "#dialog_form_files_edit-inputEl")
    private SelenideElement openFileForEdit;

    @FindBy(css = "#dialog_form_dictionary-inputEl")
    private SelenideElement choiceFieldDictionary;

    @FindBy(css = "#dialog_form_table-inputEl")
    private SelenideElement choiceFieldTable;

    @FindBy(css = "#dialog_form_is_unique-inputEl")
    private SelenideElement unique;

    @FindBy(css = "#dialog_form_field-inputEl")
    private SelenideElement selectField;

    @FindBy(css = "#dialog_form_isselect-inputEl")
    private SelenideElement selectionFromList;

    @FindBy(css = "#dialog_form_listval-inputEl")
    private SelenideElement fieldListVal;

    @FindBy(xpath = "//input[@id='dialog_form_dictionary_template-inputEl']/ancestor::table[not(contains(@style,'display: none'))]//input")
    private SelenideElement dicTemplate;

    @FindBy(xpath = "//div[contains(@id,'messagebox')]//div[count(a)=4]/a[2]//span[1]")
    private SelenideElement calculatNewValues;

    @FindBy(css = "#dialog_form_numerator_compute_mode-inputEl")
    private SelenideElement calculationMode;

    @FindBy(css = "#dialog_form_numerator_template-inputEl")
    private SelenideElement selNumeratorTemp;

    @FindBy(css = "#dialog_form_ishideinsearch-inputEl")
    private SelenideElement isHideInSearch;

    /**
     * Ввод Названия поля
     */
    public SelenideElement getNameField() {
        return nameField;
    }

    /**
     * Ввод Идентификатора поля
     */
    public SelenideElement getIdentifier() {
        return nameIdentifier;
    }

    /**
     * Выбор Тип поля
     */
    public SelenideElement getChoiceTypeField() {
        return choiceTypeField;
    }

    /**
     * Обязательное поле
     */
    public SelenideElement getObligatoryField() {
        return obligatoryField;
    }

    /**
     * Сохранить поле
     */
    public SelenideElement getSaveField() {
        return saveField;
    }

    /**
     * Строка
     */
    public SelenideElement getTypeFieldString() {
        return typeFieldString;
    }

    /**
     * Текст
     */
    public SelenideElement getTypeFieldText() {
        return typeFieldText;
    }

    /**
     * Целое
     */
    public SelenideElement getTypeFieldInteger() {
        return typeFieldInteger;
    }

    /**
     * Вещественное
     */
    public SelenideElement getTypeFieldDouble() {
        return typeFieldDouble;
    }

    /**
     * Дата
     */
    public SelenideElement getTypeFieldData() {
        return typeFieldData;
    }

    /**
     * Файл
     */
    public SelenideElement getTypeFieldFile() {
        return typeFieldFile;
    }

    /**
     * Ссылка на справочник
     */
    public SelenideElement getTypeFieldReferenceToTheDictionary() {
        return typeFieldReferenceToTheDictionary;
    }

    /**
     * Множественная ссылка на справочник
     */
    public SelenideElement getTypeFieldMultipleReferenceToTheDictionary() {
        return typeFieldMultipleReferenceToTheDictionary;
    }

    /**
     * Логический
     */
    public SelenideElement getTypeFieldBoolean() {
        return typeFieldBoolean;
    }

    /**
     * Телефон
     */
    public SelenideElement getTypeFieldPhone() {
        return typeFieldPhone;
    }

    /**
     * Email
     */
    public SelenideElement getTypeFieldEmail() {
        return typeFieldEmail;
    }

    /**
     * Изображение
     */
    public SelenideElement getTypeFieldImage() {
        return typeFieldImage;
    }

    /**
     * Цвет
     */
    public SelenideElement getTypeFieldColor() {
        return typeFieldColor;
    }

    /**
     * Подразделение
     */
    public SelenideElement getTypeFieldDepartment() {
        return typeFieldDepartment;
    }

    /**
     * Таблица
     */
    public SelenideElement getTypeFieldTable() {
        return typeFieldTable;
    }

    /**
     * Сотрудник
     */
    public SelenideElement getTypeFieldEmployee() {
        return typeFieldEmployee;
    }

    /**
     * Ссылка на задачу
     */
    public SelenideElement getTypeFieldReferenceTask() {
        return typeFieldReferenceTask;
    }

    /**
     * Нумератор
     */
    public SelenideElement getTypeFieldNumerator() {
        return typeFieldNumerator;
    }

    /**
     * Ссылка на объект
     */
    public SelenideElement getTypeFieldLinkObject() {
        return typeFieldLinkObject;
    }

    /**
     * Ссылка на библиотеку
     */
    public SelenideElement getTypeFieldLibrarylink() {
        return typeFieldLibrarylink;
    }

    /**
     * Ссылка на объект
     */
    public SelenideElement getLinkObject() {
        return linkObject;
    }

    /**
     * Вложенный справочник
     */
    public SelenideElement getTypeFieldEnclosedDirectory() {
        return typeFieldEnclosedDirectory;
    }

    /**
     * Уникальное поле
     */
    public SelenideElement getUnique() {
        return unique;
    }

    /**
     * "Выбор из списка" для поля типа "Строка"
     */
    public SelenideElement getSelectionFromList() {
        return selectionFromList;
    }

    /**
     * Поле - "Список значений" для поля типа "Строка"
     */
    public SelenideElement getFieldListValue() {
        return fieldListVal;
    }

    /**
     * Открывать файлы для редактирования
     */
    public SelenideElement getOpenFileForEdit() {
        return openFileForEdit;
    }

    /**
     * выбор поля "Справочник:"
     */
    public SelenideElement getChoiceFieldDictionary() {
        return choiceFieldDictionary;
    }

    /**
     * выбор поля "Таблицы:"
     */
    public SelenideElement getChoiceFieldTable() {
        return choiceFieldTable;
    }

    /**
     * Клик "Поле:" для поле типа "Ссылка на справочник" и "Множественная ссылка на справочник"
     */
    public SelenideElement getFieldSelectionGuide() {
        return selectField;
    }

    /**
     * Шаблон отображения (Справочник/Таблица)
     */
    public SelenideElement getDicTemplate() {
        return dicTemplate;
    }

    /**
     * Вычислить новые значения для сохраненных полей? - при вводе Шаблона отображения
     */
    public SelenideElement getCalculationNewValues() {
        return calculatNewValues;
    }

    /**
     * Режим вычисления
     */
    public SelenideElement getCalculationMode() {
        return calculationMode;
    }

    /**
     * Поле - "Шаблон нумератора:"
     */
    public SelenideElement getSelNumeratorTemp() {
        return selNumeratorTemp;
    }

    /**
     * Скрывать при поиске
     */
    public SelenideElement getIsHideInSearch() {
        return isHideInSearch;
    }


}
