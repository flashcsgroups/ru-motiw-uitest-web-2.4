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
     * @return GridOfFoldersSteps
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

        //sleep(2000);
        // Если после завершения задачи мы не перешли в папку, то переходим в созданную папку
        if (!internalElementsMobile.getMainTitle().is(text(folderTask.getNameFolder()))) {
            goToHome();
            // входим в созданную папку
            $(By.xpath("//div[@class=\"m-folder\"]//div[text()='" + folderTask.getNameFolder() + "']")).click();
            sleep(500);//ожидание грида
        }

        // Если пакет amq ещё не пришел, то грид может не обновиться. Поэтому делаем так:
        // сначала проверяем на отображение завершенной задачи в гриде.
        // И если завершенная задача продолжает отображаться, то перезагружаем страницу и проверяем снова.
        if(($(By.xpath("//div[contains(@id,\"ext-tasklist-item\")]//div[text()='" + task.getTaskName() + "']")).isDisplayed())) {
            refresh();
            sleep(5000);// Todo может недожидаться на самом деле грида, и проходить. надо ожидание грида нормально реализовать
            $(By.xpath("//div[contains(@id,\"ext-tasklist-item\")]//div[text()='" + task.getTaskName() + "']")).shouldNotBe(visible);
        }


        return this;
    }

}

