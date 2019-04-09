package ru.motiw.web.steps.DocflowAdministration.DocumentRegistrationCards;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.motiw.web.steps.BaseSteps;
import ru.motiw.web.elements.elementsweb.DocflowAdministration.DocumentRegistrationCards.FormDocRegisterCardsEditFieldsElements;
import ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards.*;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static ru.motiw.utils.ElementUtil.offsetAndRangeOfValuesOnTheList;
import static ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards.SettingsForDocumentFields.*;

/**
 * Форма редактирвоания РКД - вкладка ПОЛЯ
 */
public class FormDocRegisterCardsEditFieldsSteps extends BaseSteps {

    private FormDocRegisterCardsEditFieldsElements formEditRCDFieldsElement = page(FormDocRegisterCardsEditFieldsElements.class);

    /**
     * Выбор только из справочника
     *
     * @param onlyFromDictionary - Да / Нет
     * @return FormDocRegisterCardsEditFieldsSteps
     */
    private FormDocRegisterCardsEditFieldsSteps onlyFromDictionary(SettingsForDocumentFields onlyFromDictionary) {
        if (onlyFromDictionary == null) return this;
        switch (onlyFromDictionary) {
            case YES:
                offsetAndRangeOfValuesOnTheList(formEditRCDFieldsElement.getSelOnlyFromDictionary(), getCollectionOfListItems(),
                        SettingsForDocumentFields.YES.getNameOfTheEnumerationValues());
                break;
            case NO:
                offsetAndRangeOfValuesOnTheList(formEditRCDFieldsElement.getSelOnlyFromDictionary(), getCollectionOfListItems(),
                        SettingsForDocumentFields.NO.getNameOfTheEnumerationValues());
                break;
        }
        return this;
    }

    /**
     * Обязательное поле
     */
    private FormDocRegisterCardsEditFieldsSteps obligatoryFieldDocument(ObligatoryFieldDocument obligatoryFieldDocumentDoc) {
        if (obligatoryFieldDocumentDoc == null) return this;
        switch (obligatoryFieldDocumentDoc) {
            case REQUIRED_WHEN_CREATION:
                offsetAndRangeOfValuesOnTheList(formEditRCDFieldsElement.getObligatoryField(), getCollectionOfListItems(),
                        ObligatoryFieldDocument.REQUIRED_WHEN_CREATION.getNameOfTheEnumerationValues());
                break;
            case REQUIRED_WHEN_SENDING_TO_EXECUTION:
                offsetAndRangeOfValuesOnTheList(formEditRCDFieldsElement.getObligatoryField(), getCollectionOfListItems(),
                        ObligatoryFieldDocument.REQUIRED_WHEN_SENDING_TO_EXECUTION.getNameOfTheEnumerationValues());
                break;
        }
        return this;
    }

    /**
     * Выбор настройки Да при возврате истенности условия
     * <p>
     * Например, настройки поля:
     * Скрывать при (создании; редактировании; поиске и пр..)
     * Использовать при создании связанного документа
     * и etc...
     *
     * @param setupChoiceField истинность выбора настройки поля, иначе настройка поля игнорируется
     * @param fieldSettings    выбираемая настройку поля
     * @return FormDocRegisterCardsEditFieldsSteps
     */
    private FormDocRegisterCardsEditFieldsSteps selectionFieldSettingsYesOrNo(boolean setupChoiceField, SelenideElement fieldSettings) {
        if (setupChoiceField) {
            offsetAndRangeOfValuesOnTheList(fieldSettings, getCollectionOfListItems(),
                    SettingsForDocumentFields.YES.getNameOfTheEnumerationValues());
        } else {
            offsetAndRangeOfValuesOnTheList(fieldSettings, getCollectionOfListItems(),
                    SettingsForDocumentFields.NO.getNameOfTheEnumerationValues());
        }
        return this;
    }

    /**
     * Выбор настройки Да при возврате истенности условия
     * <p>
     * Например, настройки поля:
     * Скрывать при (создании; редактировании; поиске и пр..)
     * Использовать при создании связанного документа
     * и etc...
     *
     * @param setupChoiceField истинность выбора настройки поля, иначе настройка поля игнорируется
     * @param fieldSettings    выбираемая настройку поля
     * @return FormDocRegisterCardsEditFieldsSteps
     */
    private FormDocRegisterCardsEditFieldsSteps selectionFieldSettingsYes(boolean setupChoiceField, SelenideElement fieldSettings) {
        if (setupChoiceField) {
            offsetAndRangeOfValuesOnTheList(fieldSettings, getCollectionOfListItems(),
                    SettingsForDocumentFields.YES.getNameOfTheEnumerationValues());
        }
        return this;
    }

    /**
     * Выбор типа поля документа
     *
     * @param typeOfField передаваемый элемент типа поля
     * @return FormDocRegisterCardsEditFieldsSteps
     */
    private FormDocRegisterCardsEditFieldsSteps selectTheTypeOfField(SelenideElement typeOfField) {
        typeOfField.click();
        return this;
    }

    /**
     * Выбор объекта по имени из списка в форме радаетирвоания поля
     *
     * @param nameOfTheObjectFromTheList
     * @return FormDocRegisterCardsEditFieldsSteps
     */
    private FormDocRegisterCardsEditFieldsSteps selectionOfAnObjectFromTheListByName(SelenideElement element, String
            nameOfTheObjectFromTheList) {
        if (nameOfTheObjectFromTheList == null) {
            return this;
        } else {
            element.click();
            $(By.xpath("//*[text()='" + nameOfTheObjectFromTheList + "']")).click();
        }
        return this;
    }


    /**
     * Выбор значения - Сотрудник - Значение по умолчанию == Текущий пользователь;
     * Дата - Значение по умолчанию == Текущая дата;
     *
     * @return FormDocRegisterCardsEditFieldsSteps
     */
    private FormDocRegisterCardsEditFieldsSteps defaultValueField(SettingsForDocumentFields defValueField) {
        if (defValueField == null) return this;
        switch (defValueField) {
            case CURRENT_USER:
                offsetAndRangeOfValuesOnTheList(formEditRCDFieldsElement.getDefValueField(), getCollectionOfListItems(),
                        CURRENT_USER.getNameOfTheEnumerationValues());
                break;
            case CURRENT_DATE:
                offsetAndRangeOfValuesOnTheList(formEditRCDFieldsElement.getDefValueField(), getCollectionOfListItems(),
                        CURRENT_DATE.getNameOfTheEnumerationValues());
                break;
        }
        return this;
    }

    /**
     * Выбор настройки мульти выбора записей справочника
     *
     * @return FormDocRegisterCardsEditFieldsSteps
     */
    private FormDocRegisterCardsEditFieldsSteps multipleRecords(SettingsForDocumentFields multipleRecords) {
        if (multipleRecords == null) return this;
        switch (multipleRecords) {
            case SINGLE_RECORD:
                offsetAndRangeOfValuesOnTheList(formEditRCDFieldsElement.getDirectoryEntriesSelectionField(), getCollectionOfListItems(),
                        SettingsForDocumentFields.SINGLE_RECORD.getNameOfTheEnumerationValues());
                break;
            case MULTIPLE_RECORDS:
                offsetAndRangeOfValuesOnTheList(formEditRCDFieldsElement.getDirectoryEntriesSelectionField(), getCollectionOfListItems(),
                        SettingsForDocumentFields.MULTIPLE_RECORDS.getNameOfTheEnumerationValues());
                break;
        }
        return this;
    }

    /**
     * Метод добавлений полей документа
     *
     * @param registerCards атрибуты полей документа
     */
    public FormDocRegisterCardsEditFieldsSteps addFieldsDocRegisterCards(DocRegisterCards registerCards) {
        addFieldsRCD(registerCards.getFieldDocumentFields());
        return this;
    }


    /**
     * Метод добавления всех типов полей документа
     *
     * @param fieldsDocs массив типов полей документа
     * @return FormDocRegisterCardsEditFieldsSteps
     */
    private FormDocRegisterCardsEditFieldsSteps addFieldsRCD(FieldDocument[] fieldsDocs) {
        fieldsTabRCD();
        if (fieldsDocs == null) {
            return null;
        } else
            for (FieldDocument fieldDoc : fieldsDocs) {
                formEditRCDFieldsElement.getAddFieldDoc().click(); // Добавить поле
                inputField(formEditRCDFieldsElement.getNameFieldDocument(), fieldDoc.getDocumentFieldName()); // Заполняем Название поля документа из массива
                inputField(formEditRCDFieldsElement.getIdentifierFieldDocument(), fieldDoc.getFieldIdentifierDoc()); // Заполняем Идентификатор поля из массива
                formEditRCDFieldsElement.getFieldTypeField().click(); // Выбор поля - Тип поля

                // 1. ЧИСЛО
                if (fieldDoc.getObjectFieldDocument() instanceof FieldTypeNumberDocument) {
                    selectTheTypeOfField(formEditRCDFieldsElement.getTypeFieldNumber());
                    FieldTypeNumberDocument fieldNumber = (FieldTypeNumberDocument) fieldDoc.getObjectFieldDocument();

                    // 2. ДАТА
                } else if (fieldDoc.getObjectFieldDocument() instanceof FieldTypeDateDocument) {
                    selectTheTypeOfField(formEditRCDFieldsElement.getTypeFieldDate());
                    FieldTypeDateDocument fieldDate = (FieldTypeDateDocument) fieldDoc.getObjectFieldDocument();
                    if (fieldDate.getDefaultValue() == CURRENT_DATE) {
                        defaultValueField(fieldDate.getDefaultValue()); // Значение по умолчанию == Текущая дата
                    }
                    if (fieldDate.getEditionAvailableWhileCreation() || !fieldDate.getEditionAvailableWhileCreation()) {
                        // TODO проблема, тут идет перебор значений НЕТ даже если оно уже вызвано по дефолту - что не верно ибо увеличивает время выполнения теста в разы!!!
                        selectionFieldSettingsYesOrNo(fieldDate.getEditionAvailableWhileCreation(), formEditRCDFieldsElement.getEditionAvailableWhileCreationField()); // Изменяемое при создании
                    }

                    // 3. СТРОКА
                } else if (fieldDoc.getObjectFieldDocument() instanceof FieldTypeStringDocument) {
                    selectTheTypeOfField(formEditRCDFieldsElement.getTypeFieldString()); // Выбор поля типа "Строка"
                    FieldTypeStringDocument fieldString = (FieldTypeStringDocument) fieldDoc.getObjectFieldDocument();
                    if (fieldString.getSelectOnlyFromDictionary() == SettingsForDocumentFields.YES
                            || fieldString.getSelectOnlyFromDictionary() == SettingsForDocumentFields.NO) {
                        onlyFromDictionary(fieldString.getSelectOnlyFromDictionary()); // Выбор только из спр-ка
                        formEditRCDFieldsElement.getTypeFieldStringOrTextDirectory().click();
                        ; // Выбор поля Справочник
                        $(By.xpath("//*[text()='" + fieldString.getDirectoryName() + "']")).click();
                        inputField(formEditRCDFieldsElement.getDirectoryTemplate(), fieldString.getDirectoryTemplate()); // Шаблон спр-ка
                        inputField(formEditRCDFieldsElement.getFieldLength(), fieldString.getFieldLength()); // Длина поля
                    } else {
                        inputField(formEditRCDFieldsElement.getFieldLength(), fieldString.getFieldLength()); // Длина поля
                    }

                    // 4. ТЕКСТ
                } else if (fieldDoc.getObjectFieldDocument() instanceof FieldTypeTextDocument) {
                    selectTheTypeOfField(formEditRCDFieldsElement.getTypeFieldText());
                    FieldTypeTextDocument fieldText = (FieldTypeTextDocument) fieldDoc.getObjectFieldDocument();
                    if (fieldText.getSelectOnlyFromDictionary() == SettingsForDocumentFields.YES
                            || fieldText.getSelectOnlyFromDictionary() == SettingsForDocumentFields.NO) {
                        onlyFromDictionary(fieldText.getSelectOnlyFromDictionary()); // Выбор только из спр-ка
                        formEditRCDFieldsElement.getTypeFieldStringOrTextDirectory().click();
                        // Выбор поля Справочник
                        $(By.xpath("//*[text()='" + fieldText.getDirectoryName() + "']")).click();
                        inputField(formEditRCDFieldsElement.getDirectoryTemplate(), fieldText.getDirectoryTemplate());
                    }
                    // 5. СЛОВАРЬ
                } else if (fieldDoc.getObjectFieldDocument() instanceof FieldTypeDictionaryDocument) {
                    selectTheTypeOfField(formEditRCDFieldsElement.getTypeFieldDictionary());
                    FieldTypeDictionaryDocument fieldDictionary = (FieldTypeDictionaryDocument) fieldDoc.getObjectFieldDocument();
                    selectionOfAnObjectFromTheListByName(formEditRCDFieldsElement.getFieldDictionary(),
                            fieldDictionary.getDictionaryEditor().getDictionaryEditorName()); // Выбор поля "Словарь"
                    // 6. ПОДРАЗДЕЛЕНИЕ
                } else if (fieldDoc.getObjectFieldDocument() instanceof FieldTypeDepartmentDocument) {
                    selectTheTypeOfField(formEditRCDFieldsElement.getTypeFieldDepartment());
                    FieldTypeDepartmentDocument fieldDepartment = (FieldTypeDepartmentDocument) fieldDoc.getObjectFieldDocument();
                    // 7. СОТРУДНИК
                } else if (fieldDoc.getObjectFieldDocument() instanceof FieldTypeEmployeeDocument) {
                    selectTheTypeOfField(formEditRCDFieldsElement.getTypeFieldEmployee());
                    FieldTypeEmployeeDocument fieldEmployee = (FieldTypeEmployeeDocument) fieldDoc.getObjectFieldDocument();
                    if (fieldEmployee.getDefaultValue() == SettingsForDocumentFields.CURRENT_USER) { // Значение по умолчанию == Текущий пользователь
                        defaultValueField(SettingsForDocumentFields.CURRENT_USER);
                    }
                    if (fieldEmployee.getDocumentSuperviser()) { // Контролер документа
                        selectionFieldSettingsYesOrNo(fieldEmployee.getDocumentSuperviser(), formEditRCDFieldsElement.getDocumentSuperviserField());
                    }

                    selectionFieldSettingsYesOrNo(fieldEmployee.getForInformation(), formEditRCDFieldsElement.getForInformationField());

                    if (fieldEmployee.getEditionAvailableWhileCreation()) { // Изменяемое при создании
                        selectionFieldSettingsYesOrNo(fieldEmployee.getEditionAvailableWhileCreation(), formEditRCDFieldsElement.getEditionAvailableWhileCreationField());
                    }
                    // 8. ДОКУМЕНТ
                } else if (fieldDoc.getObjectFieldDocument() instanceof FieldTypeDocumentDocument) {
                    selectTheTypeOfField(formEditRCDFieldsElement.getTypeFieldDocument());
                    FieldTypeDocumentDocument fieldDocument = (FieldTypeDocumentDocument) fieldDoc.getObjectFieldDocument();
                    inputField(formEditRCDFieldsElement.getDisplayNameTemplateField(), fieldDocument.getDisplayNameTemplate()); // Шаблон отображения
                    if (!fieldDocument.getSearchSimiliarDocuments()) {
                    }
                    if (fieldDocument.getSearchSimiliarDocuments()) {
                        formEditRCDFieldsElement.getTabSearch().click(); // Выбор вкладки - Поиск
                        selectionFieldSettingsYesOrNo(fieldDocument.getSearchSimiliarDocuments(), formEditRCDFieldsElement.getSearchSimiliarDocuments()); // Искать похожие документы
                        inputField(formEditRCDFieldsElement.getSearchRulesTemplate(), fieldDocument.getSearchRules()); // Правила поиска
                        formEditRCDFieldsElement.getTabFields().click(); // выбираем вкладку Поле в форме редактирования поля
                    }
                    // 9. НУМЕРАТОР
                } else if (fieldDoc.getObjectFieldDocument() instanceof FieldTypeNumeratorDocument) {
                    selectTheTypeOfField(formEditRCDFieldsElement.getTypeFieldNumerator());
                    FieldTypeNumeratorDocument fieldNumerator = (FieldTypeNumeratorDocument) fieldDoc.getObjectFieldDocument();
                    inputField(formEditRCDFieldsElement.getNumeratorTemplate(), fieldNumerator.getNumeratorTemplateDoc()); // Шаблон нумератора
                    // 9. СПРАВОЧНИК
                } else if (fieldDoc.getObjectFieldDocument() instanceof FieldTypeDirectoryDocument) {
                    selectTheTypeOfField(formEditRCDFieldsElement.getTypeFieldDirectories());
                    FieldTypeDirectoryDocument fieldDirectory = (FieldTypeDirectoryDocument) fieldDoc.getObjectFieldDocument();
                    if (fieldDirectory.getDirectoryEntriesSelection() == SettingsForDocumentFields.SINGLE_RECORD) { // Выбор записей спр-ка == Одна запись
                        formEditRCDFieldsElement.getTypeFieldStringOrTextDirectory().click(); // Выбор поля Справочник
                        $(By.xpath("//*[text()='" + fieldDirectory.getNameDirectoryDoc() + "']")).click();
                        multipleRecords(fieldDirectory.getDirectoryEntriesSelection());
                        inputField(formEditRCDFieldsElement.getDirectoryTemplate(), fieldDirectory.getDirectoryTemplate()); // Шаблон спр-ка
                    } else if (fieldDirectory.getDirectoryEntriesSelection() == SettingsForDocumentFields.MULTIPLE_RECORDS) { // Выбор записей справочника == Несколько записей
                        selectionOfAnObjectFromTheListByName(formEditRCDFieldsElement.getTypeFieldStringOrTextDirectory(), fieldDirectory.getNameDirectoryDoc()); // Выбор проинициализированного спр-ка из списка
                        multipleRecords(fieldDirectory.getDirectoryEntriesSelection()); // Выбор настройки мульти выбора записей справочника
                        inputField(formEditRCDFieldsElement.getDirectoryTemplate(), fieldDirectory.getDirectoryTemplate()); // Шаблон спр-ка

                    }
                }

                selectionFieldSettingsYesOrNo(fieldDoc.getEditableField(), formEditRCDFieldsElement.getEditableField()); // Редактируемость поля
                obligatoryFieldDocument(fieldDoc.getObligatoryFieldDoc()); // Обязательное поле

                // Уникальное поле
                selectionFieldSettingsYesOrNo(fieldDoc.getUniqueField(), formEditRCDFieldsElement.getUniqueField());

                /*
                Настройки скрытия полей
                 */
                selectionFieldSettingsYes(fieldDoc.getHideForCreationField(), formEditRCDFieldsElement.getHideForCreation()); // Скрывать при создании
                selectionFieldSettingsYes(fieldDoc.getHideInTablesField(), formEditRCDFieldsElement.getHideInTables()); // Скрывать в таблицах
                selectionFieldSettingsYes(fieldDoc.getHideForSearchField(), formEditRCDFieldsElement.getHideForSearch()); // Скрывать при поиске
                selectionFieldSettingsYes(fieldDoc.getHideInCardField(), formEditRCDFieldsElement.getHideInCards()); // Скрывать в карточке
                // Использовать при создании связанного документа
                //selectionFieldSettingsYes(fieldDoc.getUsedToCreateTheLinkedDocument(), formEditRCDFieldsElement.getUsedToCreateTheLinkedDocumentField());

                formEditRCDFieldsElement.getButtonSaveField().click(); // Сохранить поле документа

                // Перемещение поля типа СТРОКА - на 1-ю позицию
                if (fieldDoc.getObjectFieldDocument() instanceof FieldTypeStringDocument && fieldDoc.getUniqueField()) {
                    List<SelenideElement> elementsIndexing = new ArrayList<>();
                    for (SelenideElement selenideElement : formEditRCDFieldsElement.getListFieldsDocument()) {
                        elementsIndexing.add(selenideElement);
                    }
                    int sizeFields;
                    sizeFields = elementsIndexing.size();
                    for (int i = 0; i < sizeFields; i++) formEditRCDFieldsElement.getMoveFieldUp().click();
                }
                // Проверяем отображение добавленного поля в гриде
                $(By.xpath("//table[contains(@id,'treeview')]//td[1]/div[text()='" + fieldDoc.getDocumentFieldName() + "']"))
                        .shouldBe(Condition.exactText("" + fieldDoc.getDocumentFieldName() + ""));
            }
        return this;
    }

    /**
     * Производим выбор вкладки - ПОЛЯ
     * @return FormDocRegisterCardsEditFieldsSteps
     */
    public FormDocRegisterCardsEditFieldsSteps fieldsTabRCD() {
        formEditRCDFieldsElement.getFieldsTab().click();
        return page(FormDocRegisterCardsEditFieldsSteps.class);
    }


}
