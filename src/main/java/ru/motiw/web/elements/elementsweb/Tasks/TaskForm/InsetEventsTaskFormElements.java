package ru.motiw.web.elements.elementsweb.Tasks.TaskForm;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Элементы формы - ЗАДАЧА - вкладка События
 */
public class InsetEventsTaskFormElements {

    @FindBy(xpath = "(//table/thead)[3]//a")
    private ElementsCollection gridColumnsEvent;

    @FindBy(xpath = "//table[@id='btnAddEvent']//button")
    private SelenideElement addEvent;

    /**
     * Грид колонок
     *
     * @return возвращается массив элементов грида колонок вкладка - Событие
     */
    public ElementsCollection getGridColumnsEvent() {
        return gridColumnsEvent;
    }

    /**
     * Добавить Событие
     */
    public SelenideElement getAddEvent() {
        return addEvent;
    }

}
