package ru.motiw.web.elements.elementsweb.Tasks.TaskForm;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Событие
 */
public class EventElements {


    @FindBy(css = "#eventName")
    private SelenideElement eventName;

    @FindBy(css = "#eventDescription")
    private SelenideElement eventDescription;

    @FindBy(css = "#eventPlace")
    private SelenideElement eventPlace;

    @FindBy(css = "#eventTemplateList")
    private SelenideElement eventTemplate;

    @FindBy(css = "#btnSaveEvent")
    private SelenideElement saveEvent;


    /**
     * Шаблон события
     */
    public SelenideElement getEventTemplate() {
        return eventTemplate;
    }

    /**
     * Название события
     */
    public SelenideElement getInputEventName() {
        return eventName;
    }

    /**
     * Описание события
     */
    public SelenideElement getInputEventDescription() {
        return eventDescription;
    }

    /**
     * Место проведения
     */
    public SelenideElement getInputEventPlace() {
        return eventPlace;
    }

    /**
     * Сохранить
     */
    public SelenideElement getSaveEvent() {
        return saveEvent;
    }

}
