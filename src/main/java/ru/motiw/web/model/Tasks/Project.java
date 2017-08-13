package ru.motiw.web.model.Tasks;

/**
 * Модель объекта системы - "Проект"
 */
public class Project {

    private String nameProject;
    private String description;
    private String client;
    private String endDate;

    /**
     * Проект
     *
     */
    public String getNameProject() {
        return nameProject;
    }

    public Project setNameProject(String nameProject) {
        this.nameProject = nameProject;
        return this;
    }

    /**
     * Описание
     *
     */
    public String getDescription() {
        return description;
    }

    public Project setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Заказчик
     *
     * @return
     */
    public String getСlient() {
        return client;
    }

    public Project setСlient(String client) {
        this.client = client;
        return this;
    }

    /**
     * Дата окончания проекта
     *
     */
    public String getEndDate() {
        return endDate;
    }

    public Project setEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

}
