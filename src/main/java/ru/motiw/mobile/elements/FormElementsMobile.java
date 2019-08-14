package ru.motiw.mobile.elements;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Форма задачи/документа
 */
public class FormElementsMobile {

    /**
     * Тулбар меню
     */
    public SelenideElement getToolbarOfMenu() {
        return $(By.xpath("//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\")) and not(contains(@id,\"ext-object-toolbar\"))]" +
                "//div[contains(@class,\"x-toolbar x-container x-component x-noborder-trbl x-toolbar-side-toolbar\")]"));
    }

    /**
     * Кнопка очистки поля ввода в списке формы добавления пользователей
     */
    public SelenideElement getClearTriggerInputForSearchUsers() {
        return $(By.xpath("//div[contains(@id,'ext-selectdialog') and not(contains(@class,\"x-hidden\")) and not(contains(@id,\"floatWrap\"))]//div[@class=\"x-cleartrigger x-trigger x-interactive x-cleartrigger-none x-trigger-none\"]         "));
    }

    /**
     * Поле ввода в списке формы добавления пользователей
     */
    public SelenideElement getInputForSearchUsers() {
        return $(By.xpath("//div[contains(@id,'ext-selectdialog') and not(contains(@class,\"x-hidden\")) and not(contains(@id,\"floatWrap\"))]//input"));
    }


    /**
     * Набор элементов = кол-во пользователей в списке формы добавления пользователей
     */
    public ElementsCollection getListOfUsers() {
        return $$(By.xpath("//div[contains(@id,'ext-selectdialog') and not(contains(@class,\"x-hidden\")) and not(contains(@id,\"floatWrap\"))]//div[contains(@data-componentid,\"ext-gridcell\")]"));
    }



    /**
     * выбор пользователей в списке формы добавления пользователей
     */
    public SelenideElement getUserFromList(String lastName) {
        return $(By.xpath("//div[contains(@id,'ext-selectdialog') and not(contains(@class,\"x-hidden\")) and not(contains(@id,\"floatWrap\"))]//div[contains(text(),'" + lastName + "')]"));
    }

    /**
     * кнопка "Назначить"
     */
    public SelenideElement getButtonAppointUsers() {
        return $(By.xpath("//div[contains(@id,'ext-selectdialog') and not(contains(@class,\"x-hidden\")) and not(contains(@id,\"floatWrap\"))]//div[contains(@class,\"x-component x-button\")]"));
    }



}
