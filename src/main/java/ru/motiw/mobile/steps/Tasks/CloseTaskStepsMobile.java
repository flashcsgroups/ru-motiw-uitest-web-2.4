package ru.motiw.mobile.steps.Tasks;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import ru.motiw.mobile.elements.Tasks.TaskElementsMobile;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

/**
 *Завершение задачи
 */

public class CloseTaskStepsMobile  extends TaskStepsMobile {

    private TaskElementsMobile taskElementsMobile = page(TaskElementsMobile.class);


    /**
     *Завершение задачи
     */
    public void closeTask() {
        openTab("Описание"); //Переходим на вкладку "Описание"
        taskElementsMobile.getButtonOfFinalizeExecution().click();
        sleep(500);
        taskElementsMobile.getInputForAddComment().setValue("Завершение задачи");
        $(By.xpath("//div[text()=\"Завершить выполнение\"]//ancestor::div[contains(@class,\"x-component x-button x-has-text\")]")).click();
        $(By.xpath("//div[contains(@class,'x-component x-layout-box-item x-layout-vbox-item x-toast-text')]")).waitUntil(Condition.visible, 5000);
        $(By.xpath("//div[contains(@class,'x-component x-layout-box-item x-layout-vbox-item x-toast-text')]//div")).shouldHave(Condition.exactText("Задача завершена."));

    }

}
