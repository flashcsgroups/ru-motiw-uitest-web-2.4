package ru.motiw.mobile.steps.Tasks;


import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import ru.motiw.web.model.Tasks.Action;
import ru.motiw.web.model.Tasks.Task;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;




/**
 * Страница - Форма задачи (Лента действий)
 */
public class TaskActionsStepsMobile extends TaskStepsMobile {



    /**
     * Добавление объекта - Действие
     * @param actions кол-во передаваемых действий с атрибутами (текст действия;)
     */
    public void postAction(Action[] actions) {
        openTab("Действия"); //Переходим на вкладку "Действия"
        if (actions != null) {
            for (Action action : actions) {
                $(By.xpath("//div[text()=\"Добавить действие\"]//ancestor::div[contains(@class,\"x-component x-button x-icon-align-top x-widthed x-has-icon\")]")).click();
                sleep(500);
                $(By.xpath("//textarea")).setValue(action.getActionText());//
                $(By.xpath("//div[text()=\"Сохранить\"]//ancestor::div[contains(@class,\"x-component x-button x-has-text\")]")).click();
                verifyDisplayAddedActions(action.getActionText()); //  Проверяем отображение добавлого текста в ленту действий
            }
        }
    }


    /**
     * Проверяем отображение созданного Действия
     */
    public void checkNewActions(Action actions) {
        openTab("Действия"); //Переходим на вкладку "Действия"
        verifyDisplayAddedActions(actions.getActionText()); // Проверяем созданные Действия
    }


        /**
         * Добавляем текст в ленту действий
         */
        public TaskActionsStepsMobile verifyAddActionsInTheTape (Task task){
            postAction(task.getActions());

            postAction(new Action[]{ });

            return this;
        }

    /**
     * Проверяем отображение добавлого текста в ленту действий
     */
    public TaskActionsStepsMobile verifyDisplayAddedActions (String action){

        $(By.xpath("//div[contains(@id,\"ext-actionlist-item\")]//div[text()='" + action  + "']")).waitUntil(Condition.visible, 5000);

        return this;
    }
    }


