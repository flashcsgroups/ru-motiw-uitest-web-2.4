package ru.motiw.web.elements.elementsweb.Tasks.UnionTasks;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Элементы - грид ЗАДАЧИ
 */
public class UnionTasksElements {

    /*xpath по 2.1
    @FindBy(xpath = "//tr[@class='x-toolbar-left-row'][ancestor::div[@id='tree_folders_wrapper']]//input")
    private SelenideElement panelGroupingTasks;
   */

    @FindBy(xpath = "//input[@id='clickcombo-1003-inputEl']")
    private SelenideElement panelGroupingTasks;

/*
    @FindBy(xpath = "(//div[contains(@id,'ext-gen') and contains(@style,'visibility: visible')]//div[contains(@class,'x-combo-list-item')])[14]")
    private SelenideElement groupingFolder;
*/

    @FindBy(xpath = "//ul[@id='clickcombo-1003-picker-listEl']//li[contains(@class, 'x-boundlist-item')][14]")
    private SelenideElement groupingFolder;


    @FindBy(xpath = "//span[contains(@class,'x-tree-node-text ')]")
    private ElementsCollection folderInTheGroup;

    @FindBy(xpath = "//span[contains(text(), \"Добавить папку\")]")
    private SelenideElement addFolder;

    @FindBy(xpath = "(//div[@id='importfolder' and contains(@style,'visibility: visible')]//span)[1]")
    private SelenideElement editFolder;

    @FindBy(xpath = "//img[contains(@class,'x-tree-ec-icon') and contains(@class,'plus')]")
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
     * Элемент отображения дочерних узлов папок
     */
    public SelenideElement getPlusSubsites() {
        return plusSubsites;
    }


}
