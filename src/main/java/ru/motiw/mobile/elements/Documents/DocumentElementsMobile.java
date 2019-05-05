package ru.motiw.mobile.elements.Documents;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

/**
 * Форма созданного документа
 */
public class DocumentElementsMobile {

    @FindBy(xpath = "//div[contains(@id,\"ext-formdialog\") and contains(@class,\"x-dialog\") and not (contains(@class,\"x-hidden\"))]")
    private SelenideElement formOfResolution;

    @FindBy(xpath = "//div[contains(@id,\"ext-resolutionlist-item\")]")
    private ElementsCollection itemInResolutionList;


    /**
     *  Форма резолюции отображаемаемая в данный момент
     * @return
     */
    public SelenideElement getFormOfResolution() {
        return formOfResolution;
    }

    /**
     * Карточки предпросмотра резолюции в списке резолюций
     * @return
     */
    public ElementsCollection getItemInResolutionList() {
        return itemInResolutionList;
    }

    /**
     * Кнопки на тулбаре
     * @param nameOfButton название кнопки
     * @return
     */
    public SelenideElement getButtonOfTab(String nameOfButton) {
        return $(By.xpath("//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\")) and not(contains(@id,\"ext-object-toolbar\"))]" +
                "//div[text()='" + nameOfButton + "']//ancestor::div[contains(@class,\"x-component x-button x-icon-align-top x-widthed x-has-icon\")]"));
    }

    /**
     *  input поля Тип Сотрудник в форме резолюции
     */
    public SelenideElement getInputEmployeeFieldInFormOfCreateResolution(String nameField) {
        return $(By.xpath("//div[contains(@id,\"ext-formdialog\") and contains(@class,\"x-dialog\") and not (contains(@class,\"x-hidden\"))]" +
                "//span[text()='" + nameField + "']//ancestor::div[contains(@class,\"x-field\")]//div[@class=\"x-input-el\"]"));

    }

    /**
     *  textarea в форме резолюции
     */
    public SelenideElement getTextareaInFormOfCreateResolution(String nameField) {
        return $(By.xpath("//div[contains(@id,\"ext-formdialog\") and contains(@class,\"x-dialog\") and not (contains(@class,\"x-hidden\"))]" +
                "//span[text()='" + nameField + "']//ancestor::div[contains(@class,\"x-field\")]//textarea"));

    }

}
