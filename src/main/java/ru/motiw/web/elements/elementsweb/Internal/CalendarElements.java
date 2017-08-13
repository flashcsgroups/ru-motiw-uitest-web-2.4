package ru.motiw.web.elements.elementsweb.Internal;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Элементы раздела - Календарь
 */
public class CalendarElements {


    @FindBy(xpath = "//span[contains(@id,'extdd')][ancestor::li[@class='x-tree-node']]")
    private SelenideElement clickCheckBox;

    @FindBy(id = "hiddenrefresh")
    private SelenideElement fremSystemCal;

    /**
     * Чек-бокс системных календарей
     */
    public SelenideElement getClickCheckBox() {
        return clickCheckBox;
    }

    /**
     * Фрейм системных календарей
     */
    public SelenideElement getFremSystemCal() {
        return fremSystemCal;
    }
}
