package ru.motiw.web.elements.elementspda.Task;

import org.openqa.selenium.By;
import ru.motiw.web.model.Tasks.Folder;
import ru.motiw.web.model.Tasks.Task;
import ru.motiw.web.steps.BaseSteps;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.page;

/*
 * Страница грид - Задачи/Задачи
 */
public class TasksReportsStepsPDA extends BaseSteps {


    /**
     * Проверяем отображение созданной задачи в гриде раздела - Задачи
     *
     * @param task       return values of attributes of the task
     * @param folderTask наименование папки в к-й будет содержаться созданный объект - Задача
     * @return TasksReportsStepsPDA
     */
    public TasksReportsStepsPDA checkDisplayTaskGrid(Task task, Folder folderTask) {
        // входим в созданную папку
        $(By.xpath("//table[@class='ui-shadow']//tr//td[3]//span[text()='" + folderTask.getNameFolder() + "']")).click();
        $(By.xpath("//div[@id='mainblock']/table[3]//tr//span[text()='" + task.getTaskName() + "']"))
                .shouldHave(exactText(task.getTaskName())); // проверяем отображение созданной задачи в гриде (отображается наименование задачи)
        return this;
    }

    /**
     * Открываем задачу в гриде задач
     *
     * @param task return values of attributes of the task
     * @return
     */
    public TaskDescriptionStepsPDA openTaskInGrid(Task task) {
        $(By.xpath("//div[@id='mainblock']/table[3]//tr//span[text()='" + task.getTaskName() + "']/..")).click();
        $(By.xpath("//ul[@class='ui-listview']//a[contains(text(),'" + task.getTaskName() + "')]"))
                .shouldHave(exactText("" + task.getTaskName() + ""));
        return page(TaskDescriptionStepsPDA.class);
    }

    /**
     * Проверяем исчезновение задачи в гриде раздела - Задачи
     *
     * @param task return values of attributes of the task
     * @return
     */
    public TasksReportsStepsPDA checkDisappearTaskInGrid(Task task) {
        $(By.xpath("//div[@id='mainblock']/table[3]//tr//span[text()='" + task.getTaskName() + "']"))
                .shouldNotBe(visible);
        return this;
    }

}
