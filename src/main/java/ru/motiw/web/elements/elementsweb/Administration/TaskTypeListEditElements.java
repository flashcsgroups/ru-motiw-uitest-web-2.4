package ru.motiw.web.elements.elementsweb.Administration;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Общая форма редактирования объектов
 */
public class TaskTypeListEditElements {

    @FindBy(xpath = "//span[contains(@id, 'bAddField-btnIconEl')]")
    private SelenideElement addField;

    @FindBy(xpath = "//span[@id='button-1010-btnIconEl']")
    private SelenideElement saveObject;

    @FindBy(xpath = "//*[contains (@class, 'message-box')]//a[1]")
    private SelenideElement OkAddObject;

    /**
     * ОК - Подтверждение добавления объекта
     */
    public SelenideElement getOkAddObject() {
        return OkAddObject;
    }

    /**
     * Кнопка - Добавить Поле
     */
    public SelenideElement getAddField() {
        return addField;
    }

    /**
     * Нажать кнопку Сохранить объект
     */
    public SelenideElement getSaveObject() {
        return saveObject;
    }

}
