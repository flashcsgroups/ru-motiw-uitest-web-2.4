package ru.motiw.mobile.steps.Tasks;


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
    public TaskActionsStepsMobile saveActionsInTheTape(String textAction) {

        $(By.xpath("//div[text()=\"Добавить действие\"]//ancestor::div[contains(@class,\"x-component x-button x-icon-align-top x-widthed x-has-icon\")]")).click();
        sleep(500);
        $(By.xpath("//textarea")).setValue(textAction);
        $(By.xpath("//div[text()=\"Сохранить\"]//ancestor::div[contains(@class,\"x-component x-button x-has-text\")]")).click();
        sleep(5000);
        /*
        //несколько действий ?
        int n = 5;
        while (n > 0) {
            action.setValue(textAction);
            save.click();
            $(By.xpath("//ul[@class='ui-listview']//div/span[text()='" + textAction + "']")).shouldBe(Condition.visible);
            n--;
        }*/
        return this;
    }
}
