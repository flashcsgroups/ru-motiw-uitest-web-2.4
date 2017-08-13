package ru.motiw.web.steps.Administration.TasksTypes;


import org.openqa.selenium.By;
import ru.motiw.web.elements.elementsweb.Administration.TaskTypeListElements;
import ru.motiw.web.steps.Administration.TaskTypeListSteps;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static ru.motiw.web.model.URLMenu.TYPE_TASK;

/**
 * Типы задач
 */
public class TaskTypesSteps extends TaskTypeListSteps {

    private TaskTypeListElements taskTypeListElements = page(TaskTypeListElements.class);

    /**
     * Добавление объекты - Типы задач
     *
     * @param nameTaskType передаваемое название для объекта
     */
    @Override
    public void addObjectTaskTypeList(String nameTaskType) {
        super.addObjectTaskTypeList(nameTaskType);
    }

    /**
     * Редактирование - Типы задач
     *
     * @param nameTaskType передаваемое название для объекта
     */
    @Override
    public void editObjectTaskTypeList(String nameTaskType) {
        super.editObjectTaskTypeList(nameTaskType);
    }

    /**
     * Удаление - Типы задач
     *
     * @param taskType передаваемое название для объекта
     */
    @Override
    public void removeObjectTaskTypeList(String taskType) {
        super.removeObjectTaskTypeList(taskType);
        checkingMessagesConfirmationOfTheObject($(By.xpath("//div[contains(@id,'messagebox')]/ancestor::div[contains(@id,'container')]//div[text()]")),
                "Вы действительно хотите удалить тип задачи \"" + taskType + "" + "\"?",
                taskTypeListElements.getConfirmationButtonObjectDeletion());
        $(By.xpath("//*[contains(text(),'" + taskType + "')][ancestor::table]/..")).shouldNotBe(visible);
    }

    /**
     * Переход в меню - Администрирование/Типы задач
     */
    public static TaskTypesSteps goToURLTaskTypes() {
        openSectionOnURL(TYPE_TASK.getMenuURL());
        return page(TaskTypesSteps.class);
    }
}
