package ru.motiw.web.steps.DocflowAdministration.DocumentRegistrationCards;

import org.openqa.selenium.By;
import ru.motiw.web.steps.BaseSteps;
import ru.motiw.web.elements.elementsweb.DocflowAdministration.DocumentRegistrationCards.GridDocRegisterCardsElements;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static ru.motiw.web.model.URLMenu.DOCUMENT_REGISTER_CARDS;

/**
 * грид Регистрационных карточек - Администрирование ДО/Регистрационные карточки документов (.../user/tab/user/DocRegisterCards)
 */
public class GridDocRegisterCardsSteps extends BaseSteps {

    private GridDocRegisterCardsElements gridDocRegisterCardsElements = page(GridDocRegisterCardsElements.class);


    /**
     * Ожидание маски - при открытии формы редактирования
     */
    private void waitForTaskMaskDRC() {
        $(By.xpath("//div[contains(@id,'ext-element') and contains(@class,'mask')]")).waitUntil(disappear, 8000);
    }

    /**
     * Переход в меню - Администрирование ДО/Регистрационные карточки документов
     */
    public static GridDocRegisterCardsSteps goToURLGridDocRegisterCards() {
        openSectionOnURL(DOCUMENT_REGISTER_CARDS.getMenuURL());
        return page(GridDocRegisterCardsSteps.class);
    }

    /**
     * Ожидание созданного объекта в гриде DocRegisterCards
     *
     * @return GridDocRegisterCardsElements
     */
    public GridDocRegisterCardsSteps verifyDocRegisterCards(
            String ObjectDocRegisterCards) {
        $(By.xpath("//div[@class='x-grid-item-container']//span[contains(text(),'"
                + ObjectDocRegisterCards + "')]")).shouldBe(visible);
        return this;
    }

    /**
     * Добавить объект - Регистрационная карточка документа
     */
    public FormDocRegisterCardsEditGeneralSteps addDocRegisterCards() {
        $(gridDocRegisterCardsElements.getAddOnRegCards()).shouldBe(visible).click();
        waitForTaskMaskDRC();
        return page(FormDocRegisterCardsEditGeneralSteps.class);
    }


}
