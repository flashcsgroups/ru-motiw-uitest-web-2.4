package ru.motiw.web.elements.elementsweb.DocflowAdministration.DocumentRegistrationCards;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Грид - РЕГИСТРАЦИОННЫЕ КАРТОЧКИ ДОКУМЕНТОВ(.../user/tab/user/DocRegisterCards)
 */
public class GridDocRegisterCardsElements {


    @FindBy(xpath = "//div[count(a)=3]/a[1]//span")
    private SelenideElement addOnRegCards;

    @FindBy(xpath = "//div[count(a)=3]/a[2]//span")
    private SelenideElement editOnRegCards;

    @FindBy(xpath = "//div[count(a)=3]/a[3]//span")
    private SelenideElement deleteOnRegCards;

    /**
     * Добавить Регистрационную карточку документа
     */
    public SelenideElement getAddOnRegCards() {
        return addOnRegCards;
    }

    /**
     * Редактировать Регистрационную карточку документа
     */
    public SelenideElement getEditOnRegCards() {
        return editOnRegCards;
    }

    /**
     * Удалить Регистрационную карточку документа
     */
    public SelenideElement getDeleteOnRegCards() {
        return deleteOnRegCards;
    }


}
