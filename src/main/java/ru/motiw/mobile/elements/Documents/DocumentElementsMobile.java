package ru.motiw.mobile.elements.Documents;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Форма созданного документа
 */
public class DocumentElementsMobile {

    /**
     * Кнопки на тулбаре
     * @param nameOfButton название кнопки
     * @return
     */
    public SelenideElement getButtonOfTab(String nameOfButton) {
        return $(By.xpath("//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\")) and not(contains(@id,\"ext-object-toolbar\"))]" +
                "//div[text()='" + nameOfButton + "']//ancestor::div[contains(@class,\"x-component x-button x-icon-align-top x-widthed x-has-icon\")]"));
    }

}
