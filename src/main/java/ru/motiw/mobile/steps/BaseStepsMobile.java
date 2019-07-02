package ru.motiw.mobile.steps;

import ru.motiw.mobile.elements.FormElementsMobile;
import ru.motiw.mobile.elements.Internal.GridOfFolderElementsMobile;
import ru.motiw.mobile.elements.Tasks.TaskElementsMobile;
import ru.motiw.mobile.model.Document.TypeOfLocation;
import ru.motiw.web.steps.BaseSteps;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.fail;

public abstract class BaseStepsMobile extends BaseSteps {
    public static final String MOBILE_PAGE_URL = baseUrl + "/m";
    private GridOfFolderElementsMobile gridOfFolderElementsMobile = page(GridOfFolderElementsMobile.class);
    private TaskElementsMobile taskElementsMobile = page(TaskElementsMobile.class);
    FormElementsMobile formElementsMobile = page(FormElementsMobile.class);


    /**
     * Реализация переход по разделам системы напрямую по точному URL
     *
     * @param URL передаваемая ссылка для навигации по меню
     */
    public static void openSectionOnURLMobile(String URL) {
        open(URL);
        assertEquals(baseUrl + URL, getWebDriver().getCurrentUrl());
    }


    /**
     * Определить текущие расположение - где в данный момент мы находимся (конт.меню в гриде папки или карточка задачи/документа)
     *
     * @return
     */
    public TypeOfLocation getCurrentLocation() {

        TypeOfLocation currentLocation = null;


        if (!gridOfFolderElementsMobile.getAllItemsInTheGridOfFolder().isEmpty()) // Если мы в гриде
        {
            currentLocation = TypeOfLocation.GRID_FOLDER;
        } else if (taskElementsMobile.getToolbarOfMenu().is(visible)) // Если в карточке
        {
            currentLocation = TypeOfLocation.PAGE_CARD;
        }

        if (currentLocation == null) {
            fail("текущие расположение не определено");
        }

        return currentLocation;
    }

}
