package ru.motiw.web.elements.elementsweb.Documents.CreateDocument;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Элементы - форма Создания документа (Документы / Создать документ) - вкладка - Маршруты
 */
public class NewDocumentRouteTabElements {

    @FindBy(css = "#routeframe")
    private SelenideElement frameRoute;

    @FindBy(xpath = "(//span[contains(@class,'x-tab-strip')][ancestor::li[contains(@id,'documentRoutes')]])[2]")
    private SelenideElement routeTab;

    @FindBy(css = "#cb_route_name")
    private SelenideElement orderOfConsideration;

    @FindBy(xpath = "//input[@id='cb_route_name']/../img")
    private SelenideElement listOfRoutes;

    @FindBy(xpath = "//table//td//span[contains(text(),'1.')]/../../..//img[@onclick]")
    private SelenideElement addAUserToBlockDiagram;

    @FindBy(xpath = "//table//td//span[contains(text(),'1.')]/ancestor::tr/td[5]")
    private SelenideElement reviewDuration;

    @FindBy(xpath = "//tr[contains(@id,'numberfield')]//input")
    private SelenideElement inputReviewDuration;

    /**
     * Добавить пользователя
     */
    public SelenideElement getAddAUserToBlockDiagram() {
        return addAUserToBlockDiagram;
    }

    /**
     * Выбор поля - Порядок рассмотрения
     */
    public SelenideElement getOrderOfConsideration() {
        return orderOfConsideration;
    }

    /**
     * Выбор списка - Маршруты
     */
    public SelenideElement getListOfRoutes() {
        return listOfRoutes;
    }

    /**
     * Фрейм - МС
     */
    public SelenideElement getFrameRoute() {
        return frameRoute;
    }

    /**
     * Вкладка - Маршрут
     */
    public SelenideElement getRouteTab() {
        return routeTab;
    }

    /**
     * Длительность рассмотрения
     */
    public SelenideElement getReviewDuration() {
        return reviewDuration;
    }

    /**
     * Поле ввода - Длительность рассмотрения
     */
    public SelenideElement getInputReviewDuration() {
        return inputReviewDuration;
    }

}
