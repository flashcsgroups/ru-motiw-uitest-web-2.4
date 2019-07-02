package ru.motiw.mobile.steps.Tasks;


import org.openqa.selenium.By;
import ru.motiw.mobile.model.Task.TabsOfTask;
import ru.motiw.web.model.Tasks.Action;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;


/**
 * Страница - Форма задачи (Лента действий)
 */
public class TaskActionsStepsMobile extends TaskStepsMobile {

    /**
     * Добавление объекта - Действие
     *
     * @param actions кол-во передаваемых действий с атрибутами (текст действия;)
     */
    public void postAction(Action[] actions) {
        openTab(TabsOfTask.ACTIONS_TAB); //Переходим на вкладку "Действия"
        if (actions != null) {
            for (Action action : actions) {
                $(By.xpath("//div[text()=\"Добавить действие\"]//ancestor::div[contains(@class,\"x-component x-button x-icon-align-top x-widthed x-has-icon\")]")).click();
                sleep(500);
                taskElementsMobile.getInputForAddComment().setValue(action.getActionText());
                internalElementsMobile.getButtonInFormOfExecutionOperations("Сохранить").click();
                validateActionsStepsMobile.verifyDisplayAddedActions(action.getActionText(), action.getAuthorAction(), action.getTimeOfAddAction()); //  Проверяем отображение добавлого текста в ленту действий
            }
        }
    }
}
