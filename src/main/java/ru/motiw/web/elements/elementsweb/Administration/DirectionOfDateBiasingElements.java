package ru.motiw.web.elements.elementsweb.Administration;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Направление смещение даты при попадании в нерабочее время
 */
public class DirectionOfDateBiasingElements {


    @FindBy(xpath = "//input[@id='direction-no-inputEl']")
    private SelenideElement dateDoesNotMove;

    @FindBy(xpath = "//input[@id='direction-forward-inputEl']")
    private SelenideElement dateMovesForward;

    @FindBy(xpath = "//input[@id='direction-back-inputEl']")
    private SelenideElement dateMovesBack;

    /**
     * Направление смещения даты при попадании на нерабочее время:
     * Дата не меняется
     */
    public SelenideElement getDateDoesNotMove() {
        return dateDoesNotMove;
    }

    /**
     * Направление смещения даты при попадании на нерабочее время
     * Дата сдвигается вперед
     */
    public SelenideElement getDateMovesForward() {
        return dateMovesForward;
    }

    /**
     * Направление смещения даты при попадании на нерабочее время
     * Дата сдвигается назад
     */
    public SelenideElement getDateMovesBack() {
        return dateMovesBack;
    }
}
