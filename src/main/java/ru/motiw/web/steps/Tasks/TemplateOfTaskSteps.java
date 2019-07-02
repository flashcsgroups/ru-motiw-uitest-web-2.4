package ru.motiw.web.steps.Tasks;


import com.codeborne.selenide.Condition;
import ru.motiw.mobile.steps.Tasks.ValidationSteps.ValidateValuesOfFieldsStepsMobile;
import ru.motiw.web.elements.elementsweb.Tasks.GridTemplateOfTaskElements;
import ru.motiw.web.elements.elementsweb.Tasks.TemplateOfTaskElements;
import ru.motiw.web.model.Tasks.TemplateOfTask;
import ru.motiw.web.steps.BaseSteps;

import static com.codeborne.selenide.Selenide.page;


/**
 * Шаги -  ШАБЛОНЫ ЗАДАЧ
 */
public class TemplateOfTaskSteps extends BaseSteps {

    private GridTemplateOfTaskElements gridTemplateOfTaskElements = page(GridTemplateOfTaskElements.class);;
    private TemplateOfTaskElements templateOfTaskElements = page(TemplateOfTaskElements.class);
    private ValidateValuesOfFieldsStepsMobile validateValuesOfFieldsStepsMobile = page(ValidateValuesOfFieldsStepsMobile.class);


    /**
     * Проверка значений в пользовательских полях
     */
    private void verifyValueInFields(TemplateOfTask taskTypeListEditObjects) {
        if (taskTypeListEditObjects == null) {
            return;
        }
            // Название шаблона
            if (taskTypeListEditObjects.getName() != null) {
                validateValuesOfFieldsStepsMobile.verifyValueInInputOfField(taskTypeListEditObjects.getName(), templateOfTaskElements.getInputTemplateName());
            }

    }

    //Добавление записи
    public void createTemplateOfTask(TemplateOfTask[] templateOfTasks) {
        if(templateOfTasks == null){
            return;
        }
        for(TemplateOfTask templateOfTask : templateOfTasks) {

            //Добавить
            gridTemplateOfTaskElements.getAddTemplateButton().click();
            templateOfTaskElements.getInputTemplateName().waitUntil(Condition.visible, 2000);

            //Заполнение полей
            templateOfTaskElements.getInputTemplateName().setValue(templateOfTask.getName());

            //Сохранение
            templateOfTaskElements.getButtonSave().click();
            gridTemplateOfTaskElements.getAddTemplateButton().waitUntil(Condition.visible, 5000);

        }
    }


    //Проверка добавленой записи в гриде
    public void verifyTemplateOfTask(TemplateOfTask[] templateOfTasks) {
        for(TemplateOfTask templateOfTask : templateOfTasks) {
            gridTemplateOfTaskElements.getRecordOfTemplateInTheGrid(templateOfTask.getName()).shouldBe(Condition.visible);
        }
        }

    //Проверяем карточку созданного шаблона
    public void verifyCreatedTemplate(TemplateOfTask[] templateOfTasks) {
        for (TemplateOfTask templateOfTask : templateOfTasks) {
            //Открытие карточки шаблона
            gridTemplateOfTaskElements.getRecordOfTemplateInTheGrid(templateOfTask.getName()).doubleClick();
           //Проверка значений в полях
           verifyValueInFields(templateOfTask);
           //Выход из карточки
            templateOfTaskElements.getButtonExitWithoutSave().click();
        }
    }

}
