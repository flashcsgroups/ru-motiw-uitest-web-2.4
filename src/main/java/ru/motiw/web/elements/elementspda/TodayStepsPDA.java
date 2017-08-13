package ru.motiw.web.elements.elementspda;


import org.openqa.selenium.By;
import ru.motiw.web.steps.BaseSteps;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

/*
 * Страница - Сегодня
 */
public class TodayStepsPDA extends BaseSteps {


    /**
     * Проверяем отображения изменений в разделе Сегодня
     *
     * @param textAction input text for feed action tasks
     * @return
     */
    public TodayStepsPDA verifyInformationDisplaySectionToday(String textAction) {
        $(By.xpath("//p[contains(text(),'" + textAction + "')]")).shouldBe(visible);
        return this;
    }


}
