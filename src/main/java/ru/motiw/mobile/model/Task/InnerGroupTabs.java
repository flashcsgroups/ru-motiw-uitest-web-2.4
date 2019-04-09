package ru.motiw.mobile.model.Task;

/**
 * Внутренние закладки групп полей на вкладке "Описание" в форме задачи
 */


public enum InnerGroupTabs {

    NAME("Название"), TO_WHOM("Кому"), DATE("Срок"), TYPE_TASK("Тип задачи"), FILES("Файлы"), MORE("Еще");

    private String name;

    InnerGroupTabs(String name) { this.name = name;}

    public String getNameOfGroupTab() {return name;}
}
