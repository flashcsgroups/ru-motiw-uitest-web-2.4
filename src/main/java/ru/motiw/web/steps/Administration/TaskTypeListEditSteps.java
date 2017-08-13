package ru.motiw.web.steps.Administration;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.motiw.web.steps.BaseSteps;
import ru.motiw.web.elements.elementsweb.Administration.TaskTypeListEditElements;
import ru.motiw.web.elements.elementsweb.Administration.TaskTypeListFieldsElements;
import ru.motiw.web.model.Administration.FieldsObject.*;
import ru.motiw.web.model.Administration.TaskTypeListEditObject;
import ru.motiw.web.model.Administration.TasksTypes.ComputeModeNumerator;
import ru.motiw.web.model.Administration.TasksTypes.ObligatoryField;
import ru.motiw.web.model.Administration.TasksTypes.SettingsForTaskTypeListFields;
import ru.motiw.web.model.OpenFilesForEdit;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.motiw.utils.ElementUtil.offsetAndRangeOfValuesOnTheList;
import static ru.motiw.utils.ElementUtil.scrollToAndClick;
import static ru.motiw.web.model.Administration.TasksTypes.ComputeModeNumerator.NOT_CALCULATED;
import static ru.motiw.web.model.Administration.TasksTypes.ComputeModeNumerator.WHEN_CREATING_TASK;
import static ru.motiw.web.model.Administration.TasksTypes.ObligatoryField.*;
import static ru.motiw.web.model.Administration.TasksTypes.SettingsForTaskTypeListFields.NO;
import static ru.motiw.web.model.Administration.TasksTypes.SettingsForTaskTypeListFields.*;
import static ru.motiw.web.model.Administration.TasksTypes.SettingsForTaskTypeListFields.YES;

/**
 * Форма редактирования объектов - Типы Таблиц/Типы задач/Справочники, раздела Администрирование
 */
public abstract class TaskTypeListEditSteps extends BaseSteps {

    private TaskTypeListEditElements taskTypeListEditElements = page(TaskTypeListEditElements.class);
    private TaskTypeListFieldsElements fieldsElements = page(TaskTypeListFieldsElements.class);

    /**
     * Выбор название Справочника / Таблицы из списка - для полей типа Ссылка и Множественная ссылка
     *
     * @param field                        передаваемый локатор для взаимодействием с полем (Справочник: / Таблица: / Поле:)
     * @param theNameOfTheBookOrFieldGuide передаваемое значение - Название спр-ка (или Таблицы) / название поля справочника (или Таблицы)
     * @return TaskTypesEditSteps
     */
    private void selectionOfAnObjectFromTheListByName(SelenideElement field, String theNameOfTheBookOrFieldGuide) {
        field.click();
        scrollToAndClick("//ul[@class='x-list-plain']/../ancestor::div[not(contains(@style,'display: none'))]//li[text()='" + theNameOfTheBookOrFieldGuide + "']");
        sleep(200);
    }

    /**
     * Выбор типа поля объекта
     *
     * @param typeOfField передаваемое значение типа поля
     */
    private void selectTheTypeOfField(SelenideElement typeOfField) {
        typeOfField.click();
    }

    /**
     * Обязательность полей объектов
     *
     * @param oblField передаваемое зн-ие обязательности поля
     */
    private TaskTypeListEditSteps selObligatoryField(ObligatoryField oblField) {
        if (oblField != null)
            switch (oblField) {
                case YES:
                    offsetAndRangeOfValuesOnTheList(fieldsElements.getObligatoryField(), getCollectionOfListItems(),
                            ObligatoryField.YES.getNameOfTheEnumerationValues());
                    break;
                case NO:
                    offsetAndRangeOfValuesOnTheList(fieldsElements.getObligatoryField(), getCollectionOfListItems(),
                            ObligatoryField.NO.getNameOfTheEnumerationValues());
                    break;
                case OBLIGATORY_ON_CREATE:
                    offsetAndRangeOfValuesOnTheList(fieldsElements.getObligatoryField(), getCollectionOfListItems(),
                            OBLIGATORY_ON_CREATE.getNameOfTheEnumerationValues());
                    break;
                case OBLIGATORY_ON_CLOSE:
                    offsetAndRangeOfValuesOnTheList(fieldsElements.getObligatoryField(), getCollectionOfListItems(),
                            OBLIGATORY_ON_CLOSE.getNameOfTheEnumerationValues());
                    break;
                case REQUIRED_WHEN_SENDING_THE_REPORT:
                    offsetAndRangeOfValuesOnTheList(fieldsElements.getObligatoryField(), getCollectionOfListItems(),
                            REQUIRED_WHEN_SENDING_THE_REPORT.getNameOfTheEnumerationValues());
                    break;
                case MANDATORY_WHEN_COMPLETING_AND_SUBMITTING_THE_REPORT:
                    offsetAndRangeOfValuesOnTheList(fieldsElements.getObligatoryField(), getCollectionOfListItems(),
                            MANDATORY_WHEN_COMPLETING_AND_SUBMITTING_THE_REPORT.getNameOfTheEnumerationValues());
                    break;
                default:
                    break;
            }
        return this;
    }

    /**
     * Выбор настроек для полей объекта
     *
     * @param fieldSetting выбираемая настройка поля
     * @param fromList     массив элементов из списка
     */
    private TaskTypeListEditSteps selectionEnumerationFieldSettingsForTheTaskTypeList(SelenideElement fieldSetting, SettingsForTaskTypeListFields fromList) {
        if (fromList != null)
            switch (fromList) {
                case YES:
                    offsetAndRangeOfValuesOnTheList(fieldSetting, getCollectionOfListItems(),
                            SettingsForTaskTypeListFields.YES.getNameOfTheEnumerationValues());
                    break;
                case NO:
                    offsetAndRangeOfValuesOnTheList(fieldSetting, getCollectionOfListItems(),
                            SettingsForTaskTypeListFields.NO.getNameOfTheEnumerationValues());
                    break;
                case OBJECT_LINK:
                    offsetAndRangeOfValuesOnTheList(fieldSetting, getCollectionOfListItems(),
                            OBJECT_LINK.getNameOfTheEnumerationValues());
                    break;
                case VALUE_IS_NOT_DEFINED:
                    offsetAndRangeOfValuesOnTheList(fieldSetting, getCollectionOfListItems(),
                            SettingsForTaskTypeListFields.VALUE_IS_NOT_DEFINED.getNameOfTheEnumerationValues());
                    break;
                default:
                    break;
            }
        return this;
    }

    /**
     * Сохранить объект
     */
    public TaskTypeListEditSteps saveChangesOnObject(TaskTypeListEditObject object) {
        taskTypeListEditElements.getSaveObject().click();
        checkingMessagesConfirmationOfTheObject($(By.xpath("//div[count(div)=3]/div[2]//div[contains(@id,'messagebox') and (@data-errorqtip)]")),
                "Изменения сохранены", taskTypeListEditElements.getOkAddObject());
        assertThat("We check the display of the created object in the grid", verifyOutTheObjectCreation(object.getObjectTypeName()));
        return this;
    }

    /**
     * Проверяем отображение созданного объекта в гриде
     */
    private boolean verifyOutTheObjectCreation(String objectName) {
        try {
            checkOutTheObjectCreation(objectName);
            return true;
        } catch (ElementNotFound e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Скрывать при поиске (для полей объекта Тип задачи)
     */
    private void selIsHideInSearch(boolean hideInSearch) {
        if (hideInSearch) {
            offsetAndRangeOfValuesOnTheList(fieldsElements.getIsHideInSearch(), getCollectionOfListItems(),
                    YES.getNameOfTheEnumerationValues());
        }
    }

    /**
     * Выбор значения поля - Открывать файлы для редактирования
     *
     * @param filesForEdit передаваемое значение параметра редактирования файла
     */
    private TaskTypeListEditSteps selOpenFilesForEdit(OpenFilesForEdit filesForEdit) {
        if (filesForEdit == null) return this;
        switch (filesForEdit) {
            case VALUE_IS_NOT_DEFINED:
                offsetAndRangeOfValuesOnTheList(fieldsElements.getOpenFileForEdit(), getCollectionOfListItems(),
                        OpenFilesForEdit.VALUE_IS_NOT_DEFINED.getNameOfTheEnumerationValues());
                break;
            case NO:
                offsetAndRangeOfValuesOnTheList(fieldsElements.getOpenFileForEdit(), getCollectionOfListItems(),
                        OpenFilesForEdit.NO.getNameOfTheEnumerationValues());
                break;
            case YES:
                offsetAndRangeOfValuesOnTheList(fieldsElements.getOpenFileForEdit(), getCollectionOfListItems(),
                        OpenFilesForEdit.YES.getNameOfTheEnumerationValues());
                break;
            default:
                break;
        }
        return this;
    }

    /**
     * Ссылка на объект (для поля Целое)
     *
     * @param objectReference передаваемое значение параметра Ссылка на объект
     */
    private TaskTypeListEditSteps linkObject(SettingsForTaskTypeListFields objectReference) {
        switch (objectReference) {
            case YES:
                offsetAndRangeOfValuesOnTheList(fieldsElements.getLinkObject(), getCollectionOfListItems(),
                        ObligatoryField.YES.getNameOfTheEnumerationValues());
                break;
            case NO:
                offsetAndRangeOfValuesOnTheList(fieldsElements.getLinkObject(), getCollectionOfListItems(),
                        NO.getNameOfTheEnumerationValues());
                break;
            case OBJECT_LINK:
                offsetAndRangeOfValuesOnTheList(fieldsElements.getLinkObject(), getCollectionOfListItems(),
                        OBJECT_LINK.getNameOfTheEnumerationValues());
                break;
            default:
                break;
        }
        return this;
    }

    /**
     * Режим вычисления поля типа Нумертаор
     *
     * @param calculationMode передаваемое значение вычисления поля типа Нумератор
     */
    private TaskTypeListEditSteps selCalculationMode(ComputeModeNumerator calculationMode) {
        switch (calculationMode) {
            case NOT_CALCULATED:
                offsetAndRangeOfValuesOnTheList(fieldsElements.getCalculationMode(), getCollectionOfListItems(),
                        NOT_CALCULATED.getComputeModeNumerator());
                break;
            case WHEN_CREATING_TASK:
                offsetAndRangeOfValuesOnTheList(fieldsElements.getCalculationMode(), getCollectionOfListItems(),
                        WHEN_CREATING_TASK.getComputeModeNumerator());
                break;
        }
        return this;
    }

    /**
     * Добавление полей объекта
     *
     * @param fieldsObject атрибуты полей объекта
     */
    public TaskTypeListEditSteps addAllFieldsTaskTypeList(TaskTypeListEditObject fieldsObject) {
        if (fieldsObject == null) {
            return this;
        }
        for (FieldObject fieldObject : fieldsObject.getObjectTypeFields()) {

            taskTypeListEditElements.getAddField().click(); // Добавить поле

            inputField(fieldsElements.getNameField(), fieldObject.getFieldName()); // заполняем Название поля документа из массива
            inputField(fieldsElements.getIdentifier(), fieldObject.getFieldID()); // заполняем Идентификатор поля из массива
            fieldsElements.getChoiceTypeField().click(); // Выбор поля - Тип поля

            // СТРОКА
            if (fieldObject.getFieldType() instanceof TypeListFieldsString) {
                selectTheTypeOfField(fieldsElements.getTypeFieldString());
                TypeListFieldsString fieldString = (TypeListFieldsString) fieldObject.getFieldType();
                if (fieldString.getIsListChoice() == SettingsForTaskTypeListFields.YES) {
                    selectionEnumerationFieldSettingsForTheTaskTypeList(fieldsElements.getSelectionFromList(), fieldString.getIsListChoice()); // Выбор из списка
                    fieldsElements.getFieldListValue().setValue(fieldString.getValuesList()); // Список значений
                } else if (fieldString.getIsListChoice() == NO) {
                    fieldsElements.getFieldListValue().setValue(fieldString.getValuesList()); // Список значений
                }
            }
            // ТЕКСТ
            else if (fieldObject.getFieldType() instanceof TypeListFieldsText) {
                selectTheTypeOfField(fieldsElements.getTypeFieldText());
                TypeListFieldsText fieldText = (TypeListFieldsText) fieldObject.getFieldType();
            }
            // ЦЕЛОЕ
            else if (fieldObject.getFieldType() instanceof TypeListFieldsInteger) {
                selectTheTypeOfField(fieldsElements.getTypeFieldInteger());
                TypeListFieldsInteger fieldInteger = (TypeListFieldsInteger) fieldObject.getFieldType();
                if (fieldInteger.getObjectLink() == OBJECT_LINK) {
                    linkObject(fieldInteger.getObjectLink()); // Ссылка на объект
                }
            }
            // ВЕЩЕСТВЕННОЕ
            else if (fieldObject.getFieldType() instanceof TypeListFieldsDouble) {
                selectTheTypeOfField(fieldsElements.getTypeFieldDouble());
                TypeListFieldsDouble fieldsDouble = (TypeListFieldsDouble) fieldObject.getFieldType();
            }
            // ДАТА
            else if (fieldObject.getFieldType() instanceof TypeListFieldsDate) {
                selectTheTypeOfField(fieldsElements.getTypeFieldData());
                TypeListFieldsDate fieldsDate = (TypeListFieldsDate) fieldObject.getFieldType();
            }
            // ФАЙЛ
            else if (fieldObject.getFieldType() instanceof TypeListFieldsFile) {
                selectTheTypeOfField(fieldsElements.getTypeFieldFile());
                TypeListFieldsFile fieldsFile = (TypeListFieldsFile) fieldObject.getFieldType();
                if (fieldsElements.getOpenFileForEdit().exists() && fieldsFile.getOpenFilesForEdit() == OpenFilesForEdit.YES
                        || fieldsFile.getOpenFilesForEdit() == OpenFilesForEdit.NO) {
                    selOpenFilesForEdit(fieldsFile.getOpenFilesForEdit());
                }
            }
            // СПРАВОЧНИК
            else if (fieldObject.getFieldType() instanceof TypeListFieldsDirectory) {
                selectTheTypeOfField(fieldsElements.getTypeFieldReferenceToTheDictionary());
                TypeListFieldsDirectory fieldsDirectory = (TypeListFieldsDirectory) fieldObject.getFieldType();

                selectionOfAnObjectFromTheListByName(fieldsElements.getChoiceFieldDictionary(),
                        fieldsDirectory.getDirectoryName()); // Выбор спр-ка из списка

                if (fieldsDirectory.getFieldDirectory() != null) {
                    selectionOfAnObjectFromTheListByName(fieldsElements.getFieldSelectionGuide(),
                            fieldsDirectory.getFieldDirectory().getFieldName()); // Выбор поля - Поле (в форме редактирования поля - Сслыка и Множественная ссылка)
                } else {
                    selectionOfAnObjectFromTheListByName(fieldsElements.getFieldSelectionGuide(),
                            fieldsDirectory.getNameDirectoryField()); // Выбор поля - Поле (в форме редактирования поля - Сслыка и Множественная ссылка)
                }
                if (fieldsElements.getDicTemplate().exists()) {
                    fieldsElements.getDicTemplate().setValue(fieldsDirectory.getDisplayNameTemplate()); // Шаблон отображения
                }

            }
            // МНОЖЕСТВЕННАЯ ССЫЛКА НА СПР-К
            else if (fieldObject.getFieldType() instanceof TypeListFieldsMultiDirectory) {
                selectTheTypeOfField(fieldsElements.getTypeFieldMultipleReferenceToTheDictionary());
                TypeListFieldsMultiDirectory fieldsMultiDir = (TypeListFieldsMultiDirectory) fieldObject.getFieldType();
                selectionOfAnObjectFromTheListByName(fieldsElements.getChoiceFieldDictionary(),
                        fieldsMultiDir.getDirectoryName()); // Выбор спр-ка

                if (fieldsMultiDir.getFieldDirectory() != null) {
                    selectionOfAnObjectFromTheListByName(fieldsElements.getFieldSelectionGuide(),
                            fieldsMultiDir.getFieldDirectory().getFieldName()); // Выбор поля - Поле (в форме редактирования поля - Сслыка и Множественная ссылка)

                } else {
                    selectionOfAnObjectFromTheListByName(fieldsElements.getFieldSelectionGuide(),
                            fieldsMultiDir.getNameDirectoryField()); // Выбор поля - Поле (в форме редактирования поля - Сслыка и Множественная ссылка)
                }
                if (fieldsElements.getDicTemplate().exists()) {
                    fieldsElements.getDicTemplate().setValue(fieldsMultiDir.getDisplayNameTemplate()); // Шаблон отображения
                }

            }
            // ЛОГИЧЕСКОЕ
            else if (fieldObject.getFieldType() instanceof TypeListFieldsBoolean) {
                selectTheTypeOfField(fieldsElements.getTypeFieldBoolean());
                TypeListFieldsBoolean fieldsDate = (TypeListFieldsBoolean) fieldObject.getFieldType();
            }
            // ТЕЛЕФОН
            else if (fieldObject.getFieldType() instanceof TypeListFieldsPhone) {
                selectTheTypeOfField(fieldsElements.getTypeFieldPhone());
                TypeListFieldsPhone fieldsPhone = (TypeListFieldsPhone) fieldObject.getFieldType();
            }
            // EMAIL
            else if (fieldObject.getFieldType() instanceof TypeListFieldsEmail) {
                selectTheTypeOfField(fieldsElements.getTypeFieldEmail());
                TypeListFieldsEmail fieldsEmail = (TypeListFieldsEmail) fieldObject.getFieldType();

            }
            // ИЗОБРАЖЕНИЕ
            else if (fieldObject.getFieldType() instanceof TypeListFieldsImage) {
                selectTheTypeOfField(fieldsElements.getTypeFieldImage());
                TypeListFieldsImage fieldsImage = (TypeListFieldsImage) fieldObject.getFieldType();

            }
            // ЦВЕТ
            else if (fieldObject.getFieldType() instanceof TypeListFieldsColor) {
                selectTheTypeOfField(fieldsElements.getTypeFieldColor());
                TypeListFieldsColor fieldsColor = (TypeListFieldsColor) fieldObject.getFieldType();

            }
            // ВЛОЖЕННЫЙ СПРАВОЧНИК
            else if (fieldObject.getFieldType() instanceof TypeListFieldsEnclosedDirectory) {
                selectTheTypeOfField(fieldsElements.getTypeFieldEnclosedDirectory());
                TypeListFieldsEnclosedDirectory fieldsEnclosedDirectory = (TypeListFieldsEnclosedDirectory) fieldObject.getFieldType();
                selectionOfAnObjectFromTheListByName(fieldsElements.getChoiceFieldDictionary(),
                        fieldsEnclosedDirectory.getDirectoryName()); // Выбор спр-ка
            }
            // ПОДРАЗДЕЛЕНИЕ
            else if (fieldObject.getFieldType() instanceof TypeListFieldsDepartment) {
                selectTheTypeOfField(fieldsElements.getTypeFieldDepartment());
                TypeListFieldsDepartment fieldsDepartment = (TypeListFieldsDepartment) fieldObject.getFieldType();
            }

            // ССЫЛКА НА БИБЛИОТЕКУ
            else if (fieldObject.getFieldType() instanceof TypeListFieldsLibraryLink) {
                selectTheTypeOfField(fieldsElements.getTypeFieldLibrarylink());
                TypeListFieldsLibraryLink fieldsLibraryLink = (TypeListFieldsLibraryLink) fieldObject.getFieldType();
                // ССЫЛКА НА ЗАДАЧУ
            } else if (fieldObject.getFieldType() instanceof TypeListFieldsReferenceTask) {
                selectTheTypeOfField(fieldsElements.getTypeFieldReferenceTask());
                TypeListFieldsReferenceTask fieldsReferenceTask = (TypeListFieldsReferenceTask) fieldObject.getFieldType();

            }
            // НУМЕРАТОР
            else if (fieldObject.getFieldType() instanceof TypeListFieldsNumerator) {
                selectTheTypeOfField(fieldsElements.getTypeFieldNumerator());
                TypeListFieldsNumerator fieldsNumerator = (TypeListFieldsNumerator) fieldObject.getFieldType();
                fieldsElements.getSelNumeratorTemp().setValue(fieldsNumerator.getNumeratorTemplate()); // Шаблон нумератора
                selCalculationMode(fieldsNumerator.getComputeMode()); // Режим вычисления
            }
            // ССЫЛКА НА ОБЪЕКТ
            else if (fieldObject.getFieldType() instanceof TypeListFieldsLink) {
                selectTheTypeOfField(fieldsElements.getTypeFieldLinkObject());
                TypeListFieldsLink fieldsObjectLink = (TypeListFieldsLink) fieldObject.getFieldType();

            }
            // ТАБЛИЦА
            else if (fieldObject.getFieldType() instanceof TypeListFieldsTable) {
                selectTheTypeOfField(fieldsElements.getTypeFieldTable());
                TypeListFieldsTable fieldsTable = (TypeListFieldsTable) fieldObject.getFieldType();

                selectionOfAnObjectFromTheListByName(fieldsElements.getChoiceFieldTable(),
                        fieldsTable.getTypesOfTables()); // Выбор объекта Таблица из списка
                selectionOfAnObjectFromTheListByName(fieldsElements.getFieldSelectionGuide(),
                        fieldsTable.getFieldTable().getFieldName()); // Выбор поля для объекта - Таблица

                fieldsElements.getDicTemplate().setValue(fieldsTable.getNumeratorTemplate()); // Шаблон отображения

            }
            // СОТРУДНИК
            else if (fieldObject.getFieldType() instanceof TypeListFieldsEmployee) {
                selectTheTypeOfField(fieldsElements.getTypeFieldEmployee());
                TypeListFieldsEmployee fieldsEmployee = (TypeListFieldsEmployee) fieldObject.getFieldType();
            }

            selObligatoryField(fieldObject.getObligatory()); // Обязательность поля
            selIsHideInSearch(fieldObject.getIsHideInSearch()); // Скрывать при поиске
            selectionEnumerationFieldSettingsForTheTaskTypeList(fieldsElements.getUnique(), fieldObject.getIsUniqueField()); // Уникальное поле (Справочник)
            saveField(); // Сохранить поле
            $(By.xpath("//table[contains(@id,'gridview')]//td[1]/div[text()='" + fieldObject.getFieldName() + "']"))
                    .waitUntil(visible, 8000); // Проверяем отображение добавленного поля в гриде
        }
        return this;
    }

    /**
     * Сохранить поле объекта
     */
    private TaskTypeListEditSteps saveField() {
        fieldsElements.getSaveField().click();
        calculateNewValues();
        return this;
    }

    /**
     * Вычислить новые значения для сохраненных полей? - при вводе Шаблона отображения
     * исключение - срабатывает для полей типа Справочник при вводе - Шаблона отображения
     */
    private void calculateNewValues() {
        try {
            (new WebDriverWait(getWebDriver(), 0, 50))
                    .until(ExpectedConditions.elementToBeClickable(By
                            .xpath("//div[contains(@id,'messagebox')]//div[count(a)=4]/a[2]//span[1]")));
            fieldsElements.getCalculationNewValues().click();

        } catch (TimeoutException e) {

        }
    }


}
