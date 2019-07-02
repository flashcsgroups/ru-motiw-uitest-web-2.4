package ru.motiw.mobile.steps.Tasks.ValidationSteps;


import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import ru.motiw.mobile.model.Task.TabsOfTask;
import ru.motiw.mobile.steps.Tasks.CardTaskStepsMobile;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Tasks.Action;

import static com.codeborne.selenide.Selenide.$;

/**
 * Проверки на странице - Форма задачи (Лента действий)
 */
public class ValidateActionsStepsMobile extends CardTaskStepsMobile {

    /**
     * Проверяем отображение созданного Действия
     */
    public void checkAddedActions(Action[] actions) {
        if (actions != null) {
            openTab(TabsOfTask.ACTIONS_TAB); //Переходим на вкладку "Действия"
            for (Action action : actions) {
                verifyDisplayAddedActions(action.getActionText(), action.getAuthorAction(), action.getTimeOfAddAction()); // Проверяем созданные Действия
            }
        }
    }


    /**
     * Проверяем отображение добавленного текста в ленту действий
     *
     * @param user пользователь добавивший действие
     */
    public void verifyDisplayAddedActions(String action, Employee user, String timeOfAddAction) {

        $(By.xpath("//div[contains(@id,\"ext-actionlist-item\")]//div[text()='" + action + "']")).waitUntil(Condition.visible, 10000);

        //Проверка что в действии есть имя пользователя
        $(By.xpath("//div[contains(@id,\"ext-actionlist-item\")]//div[text()='" + action + "']//ancestor::div[@class=\"action-list-item  \"]//div[@class=\"action-list-item-name\"]")).shouldHave(Condition.exactText(user.getLastName()));

        //Дата добавления действия
//      $(By.xpath("//div[contains(@id,\"ext-actionlist-item\")]//div[text()='" + action + "']//ancestor::div[@class=\"action-list-item  \"]//div[@class=\"small-text\"]")).shouldHave(Condition.text("Сегодня" + " " + timeOfAddAction));  todo убрано, т.к  пока нет возмоожности выставлять актуальную дату на сервере из-за отсутствия лицензий

        //Фото
        $(By.xpath("//div[contains(@id,\"ext-actionlist-item\")]//div[text()='" + action + "']//ancestor::div[@class=\"action-list-item  \"]//div[@class=\"action-list-item-photo\"]")).shouldBe(Condition.visible);
    }


}
