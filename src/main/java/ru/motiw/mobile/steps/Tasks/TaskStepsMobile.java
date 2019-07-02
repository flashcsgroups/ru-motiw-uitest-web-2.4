package ru.motiw.mobile.steps.Tasks;

import ru.motiw.web.model.Tasks.Task;

import static com.codeborne.selenide.Condition.text;
import static ru.motiw.mobile.model.Task.TabsOfTask.DESCRIPTION_TAB;


public class TaskStepsMobile extends NewTaskStepsMobile {

    /**
     * Проверка созданой задачи
     *
     * @param valueTask атрибуты задачи
     * @return UnionMessageSteps
     */
    public TaskStepsMobile verifyCreatedTask(Task valueTask) throws Exception {
        if (valueTask == null) {
            return null;
        } else
            internalElementsMobile.getMainTitle().shouldHave((text(valueTask.getTaskName()))); // Название задачи в хедере
        //Переходим на вкладку "Описание"
        openTab(DESCRIPTION_TAB);
        verifyElementsOnDescriptionPage();// Ожидание и проверка элементов на вкладке "Описание"
        validateThatInGroupFields()
                .existFieldsWhenGroupsClosed()
                .existFieldsWhenGroupsOpen(valueTask)
                .valuesInInputsExistWhenGroupsClosed(valueTask)
                .valuesInInputsExistWhenGroupsOpen(valueTask)
                .valuesInInputsExistOnGroupTabMore();
        validateFilesStepsMobile.verifyFilesInTask(valueTask); // Проверка скачивания файлов и текста в просмотрике файлов (каруселе)
        return this;
    }
}
