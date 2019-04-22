package ru.motiw.mobile.elements.Internal;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Элементы на странице гридов папки
 */
public class GridOfFolderElementsMobile {

    /**
     * Элемент для открытия объекта (задача или документа) в гриде папки
     * Переход в картчоку объекта (задача или документа)
     */
    public SelenideElement getFolder(String nameOfFolder) {
        return $(By.xpath("//div[@class=\"m-folder\"]//div[text()='" + nameOfFolder + "']"));
    }

    /**
     * Элемент для открытия объекта (задача или документа) в гриде папки
     * Используется для перехода в картчоку объекта (задача или документа)
     *
     * @param nameOfItem отображаемое название объекта
     * @return
     */
    public SelenideElement getItemInTheGrid(String nameOfItem) {
        return $(By.xpath("//div[contains(@id,\"ext-tasklist-item\")]//div[text()='" + nameOfItem + "']"));
    }


    /**
     * Признак нового документа в гриде папки
     *
     * @param nameOfItem - отображаемое название объекта
     * @return
     */
    public SelenideElement getMarkOfNewItem(String nameOfItem) {
        return $(By.xpath("//div[contains(@id,\"ext-tasklist-item\")]//div[text()='" + nameOfItem + "']" +
                "/ancestor::div[@class=\"x-dock x-dock-horizontal\"]" +
                "//div[@class=\"x-icon-el x-font-icon x-mi mi-custom-new-task\"]"));
    }

    /**
     * Кнопка открытия конт.меню объекта(задача или документа) в гриде папки
     *
     * @param nameOfObject название задачи или документа, по которому идинтифицируем объект в гриде
     * @return
     */
    public SelenideElement getButtonOfOpenContextMenu(String nameOfObject) {
        return $(By.xpath("//div[contains(@id,\"ext-tasklist-item\")]//div[text()='" + nameOfObject + "']" +
                "/ancestor::div[contains(@id,\"ext-tasklist-item\")]" +
                "//div[contains(@class,\"x-item-no-tap white-background\") and not (contains(@class,\"x-pressed\")) and not (contains(@class,\"hidden\"))]"));
    }

    /**
     * Кнопка закрытия конт.меню объекта(задача или документа) в гриде папки
     *
     * @param nameOfObject название задачи или документа, по которому идинтифицируем объект в гриде
     * @return
     */
    public SelenideElement getButtonOfCloseContextMenu(String nameOfObject) {
        return $(By.xpath("//div[contains(@id,\"ext-tasklist-item\")]//div[text()='" + nameOfObject + "']" +
                "/ancestor::div[contains(@id,\"ext-tasklist-item\")]" +
                "//div[contains(@class,\"x-item-no-tap white-background\") and (contains(@class,\"x-pressed\")) and not (contains(@class,\"hidden\"))]"));
    }


    /**
     * Кнопка конт.меню операций для объекта (задачи\документа) в гриде папки
     *
     * @return
     */
    public SelenideElement getContextMenu() {
        return $(By.xpath("//div[contains(@id,\"ext-dynamicmenu\") and contains(@class,\"x-menu\")]"));
    }

    /**
     * Операция в конт.меню объекта (задачи\документа) в гриде папки
     * @param nameOfOperation
     * @return
     */
    public SelenideElement getOperationInContextMenu(String nameOfOperation) {
        return $(By.xpath("//div[contains(@id,\"ext-dynamicmenu\") and contains(@class,\"x-menu\")]//div[text()='" + nameOfOperation + "']"));
    }

    /**
     * Набор видимых объектов (задачи\документа) в гриде папки
     *
     * @return
     */
    public ElementsCollection getAllItemsInTheGridOfFolder() {
        return $$(By.xpath("//div[contains(@id,\"ext-tasklist\") and not (contains(@class,\"x-hidden\"))]//div[contains(@id,\"ext-tasklist-item\")]"));
    }

}
