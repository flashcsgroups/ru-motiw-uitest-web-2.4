package ru.motiw.mobile.steps.Folders;

import org.openqa.selenium.By;
import ru.motiw.mobile.elements.Internal.InternalElementsMobile;
import ru.motiw.mobile.steps.InternalStepsMobile;
import ru.motiw.web.model.Tasks.Folder;
import ru.motiw.web.model.Tasks.Task;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/*
 * Страница грида - Папка
 */

public class GridOfFoldersSteps extends InternalStepsMobile {

    private InternalElementsMobile internalElementsMobile = page(InternalElementsMobile.class);



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
        $(By.xpath("//div[contains(@id,\"ext-tasklist-item\")]//div[text()='" + task.getTaskName() + "']")).click();
        sleep(500);
        return page(GridOfFoldersSteps.class);
    }

    /**
     * Проверяем исчезновение задачи в гриде раздела - Задачи
     *
     * @param task return values of attributes of the task
     * @return
     */
    public GridOfFoldersSteps checkDisappearTaskInGrid(Task task, Folder folderTask) {

        // Если после завершения задачи мы не перешли в папку, то переходим в созданную папку
        if (!internalElementsMobile.getMainTitle().is(text(folderTask.getNameFolder()))) {
            goToHome();
            // входим в созданную папку
            $(By.xpath("//div[@class=\"m-folder\"]//div[text()='" + folderTask.getNameFolder() + "']")).click();
            sleep(2000);//ожидание грида
        }

        $(By.xpath("//div[contains(@id,\"ext-tasklist-item\")]//div[text()='" + task.getTaskName() + "']"))
                .shouldNotBe(visible);
        return this;
    }

}

