package ru.motiw.mobile.model.Task;

/**
 * Вкладки в форме созданной задачи
 */


public enum TabsOfTask {

    FILES_TAB("Файлы"), ACTIONS_TAB("Действия"), DESCRIPTION_TAB("Описание");

    private String nameTab;

    TabsOfTask(String nameTab) {this.nameTab = nameTab;}

    public String getNameTab() {return  nameTab;}

}
