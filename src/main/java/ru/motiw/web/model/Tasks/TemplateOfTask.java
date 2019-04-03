package ru.motiw.web.model.Tasks;


/**
 * Модель объекта системы - ШАБЛОН ЗАДАЧ
 */

public class TemplateOfTask {

    private String name = "";

    /**
     * Название ШАБЛОНА ЗАДАЧ
     */
    public String getName() {
        return name;
    }

    public TemplateOfTask setName(String name) {
        this.name = name;
        return this;
    }



}
