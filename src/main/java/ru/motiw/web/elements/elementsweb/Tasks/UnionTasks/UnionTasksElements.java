package ru.motiw.web.elements.elementsweb.Tasks.UnionTasks;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Элементы - грид ЗАДАЧИ
 */
public class UnionTasksElements {

    @FindBy(xpath = "//input[@id='clickcombo-1003-inputEl']")
    private SelenideElement panelGroupingTasks;

    @FindBy(xpath = "//ul[@id='clickcombo-1003-picker-listEl']//li[contains(@class, 'x-boundlist-item')][14]")
    private SelenideElement groupingFolder;


    @FindBy(xpath = "//span[contains(@class,'x-tree-node-text ')]")
    private ElementsCollection folderInTheGroup;

    @FindBy(xpath = "//span[contains(text(), \"Добавить папку\")]")
    private SelenideElement addFolder;

    @FindBy(xpath = "(//div[@id='importfolder' and contains(@style,'visibility: visible')]//span)[1]")
    private SelenideElement editFolder;

    @FindBy(xpath = "//tr[@aria-expanded=\"false\"]//div[contains(@class,\"plus x-tree-expander\")]")
    private SelenideElement plusSubsites;


    /**
     * Панель списка группировок на ПУГЗ (панель управления группировкой задач)
     */
    public SelenideElement getPanelGroupingTasks() {
        return panelGroupingTasks;
    }

    /**
     * Выбрать группировка - Папка - на ПУГЗ
     */
    public SelenideElement getGroupingFolder() {
        return groupingFolder;
    }

    /*
     * Коллекция иерархий папок (Папки в гриде)
     */
    public ElementsCollection getFolderInTheGroup() {
        return folderInTheGroup;
    }

    /*
     * Добавить - Папка
     */
    public SelenideElement getAddFolder() {
        return addFolder;
    }

    /**
     * Редактировать - Папка
     */
    public SelenideElement getEditFolder() {
        return editFolder;
    }

    /**
     * Элемент раскрытия узлов папок
     */
    public SelenideElement getPlusSubsites() {
        return plusSubsites;
    }


}
