package ru.motiw.mobile.steps.Tasks;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static ru.motiw.mobile.model.Task.TabsOfTask.DESCRIPTION_TAB;

/**
 * Завершение задачи
 */

public class CloseTaskStepsMobile extends TaskStepsMobile {

    /**
     * Завершение задачи
     */
    public void closeTask() {
        openTab(DESCRIPTION_TAB); //Переходим на вкладку "Описание"
        taskElementsMobile.getButtonOfFinalizeExecution().click();
        sleep(500);
        taskElementsMobile.getInputForAddComment().setValue("Завершение задачи");
        $(By.xpath("//div[text()=\"Завершить выполнение\"]//ancestor::div[contains(@class,\"x-component x-button x-has-text\")]")).click();
        $(By.xpath("//div[contains(@class,'x-component x-layout-box-item x-layout-vbox-item x-toast-text')]//div"))
                .waitUntil(Condition.visible, 15000)
                .shouldHave(Condition.exactText("Задача завершена."));
    }

}
