package ru.motiw.web.steps.DocflowAdministration;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import ru.motiw.web.steps.BaseSteps;
import ru.motiw.web.elements.elementsweb.DocflowAdministration.DictionaryEditorElements;
import ru.motiw.web.model.AccessRights;
import ru.motiw.web.model.DocflowAdministration.DictionaryEditor.DictionaryEditor;
import ru.motiw.web.model.DocflowAdministration.DictionaryEditor.DictionaryEditorField;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static ru.motiw.web.model.URLMenu.DICTIONARY_EDITOR;

/**
 * Администрирование ДО/Редактор словарей
 */
public class DictionaryEditorSteps extends BaseSteps {

    private DictionaryEditorElements dictionaryEditorElements = page(DictionaryEditorElements.class);

    /**
     * Переход в меню - Администрирование ДО/Редактор словарей
     */
    public static DictionaryEditorSteps goToURLDictionaryEditor() {
        openSectionOnURL(DICTIONARY_EDITOR.getMenuURL());
        return page(DictionaryEditorSteps.class);
    }

    /**
     * Добавить объект - Редактор словарей
     */
    private DictionaryEditorSteps addDictionaryEditor() {
        dictionaryEditorElements.getAddDictionaryEditor().click();
        $(dictionaryEditorElements.getNameDictionaryEditor()).shouldBe(visible);
        return this;
    }

    /**
     * Вводим название - Словаря
     */
    private DictionaryEditorSteps setNameDictionaryEditor(String nameDictionText) {
        dictionaryEditorElements.getNameDictionaryEditor().clear();
        dictionaryEditorElements.getNameDictionaryEditor().setValue(nameDictionText);
        return this;
    }

    /**
     * Метод выбора уровня доступа к Словарь
     */
    private DictionaryEditorSteps setRadioButtAccess(AccessRights accessRights) {
        if (accessRights == AccessRights.AVAILABLE_TO_ALL) {
            dictionaryEditorElements.getClickAccessAvailableToAll().click();
        } else if (accessRights == AccessRights.PERSONAL) {
            dictionaryEditorElements.getClickPersonal().click();
        } else if (accessRights == AccessRights.READONLY) {
            dictionaryEditorElements.getClickReadOnly().click();
        }
        return this;
    }

    /**
     * Добавить элемент словаря
     */
    private DictionaryEditorSteps addDictionaryEditorItem() {
        dictionaryEditorElements.getAddDictionaryEditorItem().click();
        $(dictionaryEditorElements.getNameDictionaryEditorItem().shouldBe(visible));
        return this;
    }

    /**
     * Вводим название и описание Элемента словаря
     */
    private DictionaryEditorSteps addDictionItemFields(DictionaryEditorField[] dictionItem) {
        int countElement = 1;
        if (dictionItem == null) {
            return this;
        } else
            for (DictionaryEditorField aDictionItem : dictionItem) {
                addDictionaryEditorItem(); // Доавть элемент словаря
                dictionaryEditorElements.getNameDictionaryEditorItem().setValue(aDictionItem.getDictionaryEditorElement()); // Название элемента словаря
                dictionaryEditorElements.getDescriptionDictionaryEditorItem().setValue(aDictionItem.getDescriptionElement()); // Описание элемента словаря
                dictionaryEditorElements.getSaveDictionaryEditorItem().click(); // Сохранить элемент словаря
                // Проверям отображение элемента словаря в гриде Словаря
                $(By.xpath("(//div[contains(@id,'dict_words-body')]//td[1]/div)[" + countElement + "]"))
                        .shouldBe(Condition.exactText("" + aDictionItem.getDictionaryEditorElement() + ""));
                $(By.xpath("(//div[contains(@id,'dict_words-body')]//td[2]/div)[" + countElement + "]"))
                        .shouldBe(Condition.exactText("" + aDictionItem.getDescriptionElement() + ""));
                countElement++;
            }
        return this;
    }


    /**
     * Сохранить все изменения
     *
     * @return DictionaryEditorSteps
     */
    private DictionaryEditorSteps saveDictionaryEditor() {
        $(dictionaryEditorElements.getSaveChanges()).shouldBe(Condition.visible);
        dictionaryEditorElements.getSaveChanges().click();
        return this;
    }

    /**
     * Проверяем, что созданный объект сохранился и отображается в гриде - Редактор словарей
     *
     * @return DictionaryEditorSteps
     */
    private DictionaryEditorSteps verifyDictionaryEditor(String dictionaryEditor) {
        $(By.xpath("//table//span[contains(@class,'x-tree-node-text') and contains(text(),'" + dictionaryEditor + "')]")).shouldBe(visible);
        return this;
    }

    /**
     * Добавить Словарь
     *
     * @param directoriesEditor атрибуты объекта Словарь
     */
    public void addDictionaryEditor(DictionaryEditor directoriesEditor) {
        addDictionaryEditor()
                .setNameDictionaryEditor(directoriesEditor.getDictionaryEditorName()) // Название словаря
                .setRadioButtAccess(directoriesEditor.getAccessDiction()) // Определяем уровень доступа к объекту
                .addDictionItemFields(directoriesEditor.getDictionaryEditorFields()) // Добавляем элементы словаря
                .saveDictionaryEditor() // Сохранить все изменения
                .verifyDictionaryEditor(directoriesEditor.getDictionaryEditorName()); // Проверяем создание Словаря
    }

}
