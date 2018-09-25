package ru.motiw.mobile.elements.Tasks;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Форма созданной задачи
 */
public class TaskElementsMobile {

    @FindBy(xpath = "//div[contains(@class,\"x-body-el x-toolbar-body-el x-container-body-el x-component-body-el x-layout-box x-align-center x-pack-start x-layout-vbox x-vertical x-size-monitored x-paint-monitored x-toolbar-side-toolbar-body-el\")]/div")
    private ElementsCollection menuOfTask;

    @FindBy(xpath = "//div[contains(@class,\"x-toolbar x-container x-component x-noborder-trbl x-toolbar-side-toolbar\")]")
    private SelenideElement toolbarOfMenu;

    @FindBy(xpath = "//div[contains(@class,'x-body-el x-panel-body-el x-container-body-el x-component-body-el x-layout-auto x-scroller')]/div")
    private ElementsCollection allElementsOnDescriptionPage;


    /**
     * Все элементы меню
     * @return элементы меню форме задачи
     */
    public ElementsCollection getMenuOfTask() {
        return menuOfTask;
    }


    /**
     * Тулбар меню формы задачи
     */

    public SelenideElement getToolbarOfMenu() {
        return toolbarOfMenu;
    }


    /**
     * Коллекция элементов в форме задачи на вкладке Описание
     */

    public ElementsCollection getElementsOnDescriptionPage() {
        return allElementsOnDescriptionPage;
    }



}
