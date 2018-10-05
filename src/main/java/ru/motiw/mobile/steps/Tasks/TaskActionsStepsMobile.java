package ru.motiw.mobile.steps.Tasks;


import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Страница - Форма задачи (Лента действий)
 */

public class TaskActionsStepsMobile {

    /**
     * Добавляем текст в ленту действий
     *
     * @param textAction input text for feed action tasks
     */
    public TaskActionsStepsMobile verifyAddActionsInTheTape(String textAction) {


        int n = 2;
        while (n > 0) {
            $(By.xpath("//div[text()=\"Добавить действие\"]//ancestor::div[contains(@class,\"x-component x-button x-icon-align-top x-widthed x-has-icon\")]")).click();
            sleep(500);
            $(By.xpath("//textarea")).setValue(textAction);//todo текст сйечас одинаковый, нужны разные..
            $(By.xpath("//div[text()=\"Сохранить\"]//ancestor::div[contains(@class,\"x-component x-button x-has-text\")]")).click();
            $(By.xpath("//div[contains(@id,\"ext-actionlist-item\")]//div[text()='" + textAction + "']")).waitUntil(Condition.visible, 5000);
            n--;
        }

        return this;
    }
}
