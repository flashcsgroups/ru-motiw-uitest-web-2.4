package ru.motiw.mobile.steps.Tasks;


import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import ru.motiw.mobile.elements.Tasks.TaskElementsMobile;
import ru.motiw.mobile.model.Task.TabsOfTask;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Tasks.Action;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;


/**
 * Страница - Форма задачи (Лента действий)
 */
public class TaskActionsStepsMobile extends TaskStepsMobile {
    private TaskElementsMobile taskElementsMobile = page(TaskElementsMobile.class);

    /**
     * Добавление объекта - Действие
     * @param actions кол-во передаваемых действий с атрибутами (текст действия;)
     */
    public void postAction(Action[] actions) {
        openTab(TabsOfTask.ACTIONS_TAB); //Переходим на вкладку "Действия"
        if (actions != null) {
            for (Action action : actions) {
                $(By.xpath("//div[text()=\"Добавить действие\"]//ancestor::div[contains(@class,\"x-component x-button x-icon-align-top x-widthed x-has-icon\")]")).click();
                sleep(500);
                taskElementsMobile.getInputForAddComment().setValue(action.getActionText());
                $(By.xpath("//div[text()=\"Сохранить\"]//ancestor::div[contains(@class,\"x-component x-button x-has-text\")]")).click();
                verifyDisplayAddedActions(action.getActionText(), action.getAuthorAction(),action.getTimeOfAddAction()); //  Проверяем отображение добавлого текста в ленту действий
            }
        }
    }


    /**
     * Проверяем отображение созданного Действия
     */
    public void checkNewActions(Action[] actions) {
        if (actions != null) {
            openTab(TabsOfTask.ACTIONS_TAB); //Переходим на вкладку "Действия"
            for (Action action : actions) {
                verifyDisplayAddedActions(action.getActionText(), action.getAuthorAction(),action.getTimeOfAddAction()); // Проверяем созданные Действия
            }
        }
    }


    /**
     * Проверяем отображение добавленного текста в ленту действий
     * @param user пользователь добавивший действие
     */
    public TaskActionsStepsMobile verifyDisplayAddedActions (String action, Employee user, String timeOfAddAction){

        $(By.xpath("//div[contains(@id,\"ext-actionlist-item\")]//div[text()='" + action  + "']")).waitUntil(Condition.visible, 10000);

        //Проверка что в действии есть имя пользователя
        $(By.xpath("//div[contains(@id,\"ext-actionlist-item\")]//div[text()='" + action  + "']//ancestor::div[@class=\"action-list-item  \"]//div[@class=\"action-list-item-name\"]")).shouldHave(Condition.exactText(user.getLastName()));

        //Дата добавления действия
        $(By.xpath("//div[contains(@id,\"ext-actionlist-item\")]//div[text()='" + action  + "']//ancestor::div[@class=\"action-list-item  \"]//div[@class=\"small-text\"]")).shouldHave(Condition.text("Сегодня" + " " + timeOfAddAction));

        //Фото
        $(By.xpath("//div[contains(@id,\"ext-actionlist-item\")]//div[text()='" + action  + "']//ancestor::div[@class=\"action-list-item  \"]//div[@class=\"action-list-item-photo\"]")).shouldBe(Condition.visible);
        return this;
    }
    }
