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
     * @param task передаваемые атрибуты, которые заполнялись при создании задачи
     * @param editTask передаваемые атрибуты заполняемые при редактировнаии задачи
     */

    public EditOfTaskMobile editOfTask(Task task, Task editTask) {

        //Переходим на вкладку "Описание"
        $(By.xpath("//div[text()=\"Описание\"]//ancestor::div[contains(@class,\"x-component x-button x-icon-align-top x-widthed x-has-icon\")]")).click();
        taskStepsMobile.verifyElementsOnDescriptionPage();// Ожидание и проверка элементов на вкладке "Описание"

        // Разворачиваем  группу полей  "Название"
        selectGroupTab("Название");
        //Заполняем Название задачи
        setTaskName(editTask.getTaskName());
        setTasksDescription(" ");//Требуется ввести один символ перед началом заполнения поля. Но этот символ даже не вставляется в поле. Какое-то странное поведение setValue
        setTasksDescription(editTask.getDescription());
        // Закрываем  группу полей  "Название"
        selectGroupTab("Название");


        // TODO для проверки раб.группы, надо создавать новых пользователей.

        //Проверять удаление ранее выбранных.

        // Открываем  группу полей  "Кому"
        selectGroupTab("Кому");
        currentUserSelectedInTheRole(task.getAuthors(), newTaskFormElementsMobile.getAuthorsField(), "ext-selectdialog-1"); // - по умолчанию Автор задачи текущий пользователь (admin)
        choiceUserOnTheRole(task.getControllers(), newTaskFormElementsMobile.getСontrollersField(), "ext-selectdialog-2"); // удаляем пользователя выбранного при создании задачи из роли Контролеры задачи
        choiceUserOnTheRole(task.getExecutiveManagers(), newTaskFormElementsMobile.getResponsiblesField(), "ext-selectdialog-3"); // удаляем пользователя выбранного при создании задачи из роли Ответственные руководители
        choiceUserOnTheRole(task.getWorkers(), newTaskFormElementsMobile.getWorkersField(),"ext-selectdialog-4"); // удаляем пользователя выбранного при создании задачи из роли Исполнители задачи

        // Закрываем  группу полей  "Кому"
        selectGroupTab("Кому");

        // TODO изменение Проекта -  надо создавать новый проект.


        // Открываем  группу полей  "Срок"
        selectGroupTab("Срок");
        //Заполняем Даты
        setDateBegin(editTask.getDateBegin())
                .setDateEnd(editTask.getDateEnd());

        // Закрываем  группу полей  "Срок"
        selectGroupTab("Срок");

        // Открываем группу полей "Ещё"
        selectGroupTab("Еще");
        // выключаем Признак - С Докладом
        rangeOfValuesFromTheCheckbox(editTask.getIsWithReport(), newTaskFormElementsMobile.getCheckboxReportRequired(), newTaskFormElementsMobile.getReportRequired());
        // Закрываем  группу полей "Ещё"
        selectGroupTab("Еще");


        //Сохранить
        $(By.xpath("//div[text()=\"Сохранить\"]//ancestor::div[contains(@class,\"x-component x-button x-icon-align-top x-widthed x-has-icon\")]")).click();


        return this;
    }
}
