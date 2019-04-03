package ru.motiw.mobile.steps.Tasks;

import ru.motiw.mobile.elements.Tasks.TaskElementsMobile;
import ru.motiw.web.model.Tasks.Task;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import static ru.motiw.mobile.model.Task.InnerGroupTabs.*;
import static ru.motiw.mobile.model.Task.TabsOfTask.DESCRIPTION_TAB;


public class EditOfTaskMobile extends NewTaskStepsMobile {

    private TaskStepsMobile taskStepsMobile = page(TaskStepsMobile.class);
    private TaskElementsMobile taskElementsMobile = page(TaskElementsMobile.class);


    /**
     * Редактирование задачи
     * @param task передаваемые атрибуты, которые заполнялись при создании задачи
     * @param editTask передаваемые атрибуты заполняемые при редактировнаии задачи
     */

    public EditOfTaskMobile editOfTask(Task task, Task editTask) {

        //Переходим на вкладку "Описание"
        taskStepsMobile.openTab(DESCRIPTION_TAB);
        taskStepsMobile.verifyElementsOnDescriptionPage();// Ожидание и проверка элементов на вкладке "Описание"

        //------------- Проверка Заполнения системных полей
        // Разворачиваем  группу полей  "Название"
        selectGroupTab(NAME);
        //Заполняем Название задачи
        setTaskName(editTask.getTaskName());
        setTasksDescription(" ");//Требуется ввести один символ перед началом заполнения поля. Но этот символ даже не вставляется в поле. Какое-то странное поведение
        setTasksDescription(editTask.getDescription());
        // Закрываем  группу полей  "Название"
        selectGroupTab(NAME);

        //------------- Проверка удаления ранее выбранных пользователей
        // Открываем  группу полей  "Кому"
        selectGroupTab(TO_WHOM);
        //currentUserSelectedInTheRole(task.getAuthorDefault(), newTaskFormElementsMobile.getAuthorsField()); // - по умолчанию Автор задачи текущий пользователь (admin)
        choiceUserOnTheRole(task.getAuthors(), newTaskFormElementsMobile.getAuthorsField()); // удаляем пользователя выбранного при создании задачи из роли  Авторы задачи
        choiceUserOnTheRole(task.getControllers(), newTaskFormElementsMobile.getСontrollersField()); // удаляем пользователя выбранного при создании задачи из роли Контролеры задачи
        choiceUserOnTheRole(task.getExecutiveManagers(), newTaskFormElementsMobile.getResponsiblesField()); // удаляем пользователя выбранного при создании задачи из роли Ответственные руководители
        choiceUserOnTheRole(task.getWorkers(), newTaskFormElementsMobile.getWorkersField()); // удаляем пользователя выбранного при создании задачи из роли Исполнители задачи

        //------------- Проверка добавления пользователей из атрибутов заполняемых при редактировнаии задач
        choiceUserOnTheRole(editTask.getAuthors(), newTaskFormElementsMobile.getAuthorsField()); // Добавляем нового пользователя
        choiceUserOnTheRole(editTask.getControllers(), newTaskFormElementsMobile.getСontrollersField()); // Добавляем нового пользователя
        choiceUserOnTheRole(editTask.getExecutiveManagers(), newTaskFormElementsMobile.getResponsiblesField()); // Добавляем нового пользователя
        choiceUserOnTheRole(editTask.getWorkers(), newTaskFormElementsMobile.getWorkersField()); // Добавляем нового пользователя

        // Закрываем  группу полей  "Кому"
        selectGroupTab(TO_WHOM);

        // TODO изменение Проекта -  надо создавать новый проект.

        // Открываем  группу полей  "Срок"
        selectGroupTab(DATE);
        //Заполняем Даты
        setDateBegin(editTask.getDateBegin())
                .setDateEnd(editTask.getDateEnd());

        // Закрываем группу полей  "Срок"
        selectGroupTab(DATE);

        // Открываем  группу полей  "Тип задачи"
        selectGroupTab(TYPE_TASK);
        // Проверка удаления значений в пользовательских полях (нужно для поля типа "Сотрудник")
        removeValueInCustomFields(task.getTaskFields());
        // Заполнение пользовательских полей
        setValueInCustomFields(editTask.getTaskFields());

        // Закрываем группу полей  "Тип задачи"
        selectGroupTab(TYPE_TASK);

        // Открываем группу полей "Файлы"
        selectGroupTab(FILES);
        addAttachFiles(editTask.getFileName());
        taskStepsMobile.deleteFile(task.getFileName()); // Удаляем файлы прикрепленные при создании задачи
        // Закрываем  группу полей "Файлы"
        selectGroupTab(FILES);

        //------------- Проверка изменения системных атрибутов
        // Открываем группу полей "Ещё"
        selectGroupTab(MORE);
        // выключаем Признак - С Докладом
        rangeOfValuesFromTheCheckbox(editTask.getIsWithReport(), newTaskFormElementsMobile.getCheckboxReportRequired(), newTaskFormElementsMobile.getReportRequired());
        // Закрываем  группу полей "Ещё"
        selectGroupTab(MORE);
        sleep(1000);

        //Сохранить
        taskElementsMobile.getButtonOfSave().click();

        return this;
    }
}
