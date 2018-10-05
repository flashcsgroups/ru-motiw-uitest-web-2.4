package ru.motiw.mobile.steps.Tasks;

import org.openqa.selenium.By;
import ru.motiw.web.model.Tasks.Task;

import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;


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
        setTaskName(task.getTaskName());
        setTasksDescription(" ");//Требуется ввести один символ перед началом заполнения поля. Но этот символ даже не вставляется в поле. Какое-то странное поведение setValue
        setTasksDescription(task.getDescription());
        // Закрываем  группу полей  "Название"
        selectGroupTab("Название");


        // TODO для проверки раб.группы, надо создавать новых пользователей.
        // TODO изменение Проекта -  надо создавать новый проект.





        // Открываем  группу полей  "Срок"
        selectGroupTab("Срок");
        //Заполняем Даты
        setDateBegin(task.getDateBegin())
                .setDateEnd(task.getDateEnd());

        // Закрываем  группу полей  "Срок"
        selectGroupTab("Срок");

        // Открываем группу полей "Ещё"
        selectGroupTab("Еще");
        // выключаем Признак - С Докладом
        rangeOfValuesFromTheCheckbox(task.getIsWithReport(), newTaskFormElementsMobile.getCheckboxReportRequired(), newTaskFormElementsMobile.getReportRequired());
        // Закрываем  группу полей "Ещё"
        selectGroupTab("Еще");


        //Сохранить
        $(By.xpath("//div[text()=\"Сохранить\"]//ancestor::div[contains(@class,\"x-component x-button x-icon-align-top x-widthed x-has-icon\")]")).click();


        return this;
    }
}
