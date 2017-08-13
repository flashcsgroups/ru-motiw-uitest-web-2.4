package ru.motiw.web.elements.elementsweb.Administration;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Элементы страницы - Поисковая система
 */
public class SearchSystemElements {


    @FindBy(xpath = "//*[@id='indexingInfo']//td[6]")
    private ElementsCollection elementsCollectionIndexingErrors;


    @FindBy(xpath = "//*[@id='indexingInfo']//td[6]//a[contains(@href,'indexingErrors')]")
    private SelenideElement indexingErrors;

    /**
     * Массив элементов всех объектов в колонке "Ошибок индексирования"
     */
    public ElementsCollection getElementsCollectionIndexingErrors() {
        return elementsCollectionIndexingErrors;
    }

    /**
     * Массив элементов - ссылки на ошибки индексирования в колонке "Ошибок индексирования"
     */
    public SelenideElement getIndexingErrors() {
        return indexingErrors;
    }


}
