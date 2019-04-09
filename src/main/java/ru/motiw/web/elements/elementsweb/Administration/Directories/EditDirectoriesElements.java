package ru.motiw.web.elements.elementsweb.Administration.Directories;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

/**
 * Страница системы - Cправочник
 */

public class EditDirectoriesElements {

    @FindBy(xpath = "//span[@id=\"id_btn_add_record-btnEl\"]//span[@id=\"id_btn_add_record-btnIconEl\"]")
    private SelenideElement addRecordButton;

    @FindBy(xpath = "//td[@class=\"x-form-item-body x-form-display-field-body \"]/div[text()=\"Запись успешно сохранена\"]")
    private SelenideElement recordInGrid;

    @FindBy(xpath = "//*[contains (@class, 'x-editor')]//input")
    private SelenideElement editorFieldProject;

    @FindBy(xpath = "//span[@id=\"saveBtn-btnEl\"]/span[text()=\"Сохранить\"]/following-sibling::span ")
    private SelenideElement saveButton;

    @FindBy(xpath = "//td[@class=\"x-form-item-body x-form-display-field-body \"]/div[text()=\"Запись успешно сохранена\"]")
    private SelenideElement successSave;

    @FindBy(xpath = "//div[contains(@class,\"x-toolbar x-docked x-toolbar-footer\")]//span[text()=\"OK\"]//following-sibling::span")
    private SelenideElement okButton;


    /**
     * кнопка добавления записи -  Добавить
     */
    public SelenideElement getAddRecordButton() {
        return addRecordButton;
    }

    /**
     * Запись в гриде справочника
     */
    public SelenideElement getRecordInGrid(String nameOfField) {
        return $(By.xpath("//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-headerId')]/div[text()='" + nameOfField + "']"));
    }

    /**
     * Поле ввода -  Строка
     */
    public SelenideElement getEditorField() {
        return editorFieldProject;
    }


    /**
     * Кнопка "Сохранить"
     */
    public SelenideElement getSaveButton() {
        return saveButton;
    }


    /**
     * Окно после добавления записи "Запись успешно сохранена"
     */
    public SelenideElement getTextOfSuccessefullSave() {
        return successSave;
    }


    /**
     * Кнопка - "ОК" в форме подтверждения
     */
    public SelenideElement getOkButton() {
        return okButton;
    }



    /**
     * Строка
     */
    public SelenideElement getTypeFieldString(String nameOfField) {
        return $(By.xpath("//div[contains(text(),'" + nameOfField + "')]//ancestor::tr//div[@class=\"x-grid-cell-inner \"] "));


    }

}
