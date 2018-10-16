package ru.motiw.mobile.steps.Tasks;

import org.openqa.selenium.By;
import ru.motiw.web.model.Tasks.Task;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;


public class EditOfTaskMobile extends NewTaskStepsMobile {

    //private NewTaskStepsMobile newTaskStepsMobile = page(NewTaskStepsMobile.class);
    private TaskStepsMobile taskStepsMobile = page(TaskStepsMobile.class);




    /**
     * Редактирование задачи
     * @param task передаваемые атрибуты задачи
     */

    public EditOfTaskMobile editOfTask(Task task) {

        //Переходим на вкладку "Описание"
        $(By.xpath("//div[text()=\"Описание\"]//ancestor::div[contains(@class,\"x-component x-button x-icon-align-top x-widthed x-has-icon\")]")).click();
        taskStepsMobile.verifyElementsOnDescriptionPage();// Ожидание и проверка элементов на вкладке "Описание"

        // Разворачиваем  группу полей  "Название"
        selectGroupTab("Название");
        //Заполняем Название задачи
        setTaskName(task.getTaskName())
                .setTasksDescription(task.getDescription());
        // Закрываем  группу полей  "Название"
        selectGroupTab("Название");

        //Сохранить
        $(By.xpath("//div[text()=\"Сохранить\"]//ancestor::div[contains(@class,\"x-component x-button x-icon-align-top x-widthed x-has-icon\")]")).click();


        return this;
    }
}
