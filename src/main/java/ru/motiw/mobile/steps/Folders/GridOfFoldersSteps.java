package ru.motiw.mobile.steps.Folders;

import org.openqa.selenium.By;
import ru.motiw.web.model.Tasks.Folder;
import ru.motiw.web.model.Tasks.Task;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

/*
 * Страница грида - Папка
 */

public class GridOfFoldersSteps {

    /**
     * Проверяем отображение созданной задачи в гриде раздела - Задачи
     *
     * @param task       return values of attributes of the task
     * @param folderTask наименование папки в к-й будет содержаться созданный объект - Задача
     * @return TasksReportsStepsPDA
     */
    public GridOfFoldersSteps checkDisplayTaskGrid(Task task, Folder folderTask) {
        // входим в созданную папку
        $(By.xpath("//div[@class=\"m-folder\"]//div[text()='" + folderTask.getNameFolder() + "']")).click();
        sleep(2000);//ожидание грида
        $(By.xpath("//div[contains(@id,\"ext-tasklist-item\")]//div[text()='" + task.getTaskName() + "']"))
                .shouldHave(exactText(task.getTaskName())); // проверяем отображение созданной задачи в гриде (отображается наименование задачи)
        return this;
    }

    /**
     * Открываем задачу в гриде задач
     *
     * @param task return values of attributes of the task
     * @return
     */
    public GridOfFoldersSteps openTaskInGrid(Task task) {
        $(By.xpath("//div[@id='mainblock']/table[3]//tr//span[text()='" + task.getTaskName() + "']/..")).click();
        $(By.xpath("//ul[@class='ui-listview']//a[contains(text(),'" + task.getTaskName() + "')]"))
                .shouldHave(exactText("" + task.getTaskName() + ""));
        return page(GridOfFoldersSteps.class);
    }

    /**
     * Проверяем исчезновение задачи в гриде раздела - Задачи
     *
     * @param task return values of attributes of the task
     * @return
     */
    public GridOfFoldersSteps checkDisappearTaskInGrid(Task task) {
        $(By.xpath("//div[@id='mainblock']/table[3]//tr//span[text()='" + task.getTaskName() + "']"))
                .shouldNotBe(visible);
        return this;
    }

}

