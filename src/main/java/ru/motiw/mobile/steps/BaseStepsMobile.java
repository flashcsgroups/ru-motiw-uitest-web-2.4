package ru.motiw.mobile.steps;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertEquals;

public abstract class BaseStepsMobile {
    public static final String MOBILE_PAGE_URL = baseUrl + "/m";




    /**
     * Реализация переход по разделам системы напрямую по точному URL
     *
     * @param URL передаваемая ссылка для навигации по меню
     */
    public static void openSectionOnURLMobile(String URL) {
        //switchTo().defaultContent();
        open(URL);
        assertEquals(baseUrl + URL, getWebDriver().getCurrentUrl());
        //switchTo().frame($(By.cssSelector("#flow")));
    }


}
