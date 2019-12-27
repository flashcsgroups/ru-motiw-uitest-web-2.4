package ru.motiw.testsWeb.Tasks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.motiw.data.dataproviders.Tasks;
import ru.motiw.data.listeners.ScreenShotOnFailListener;
import ru.motiw.web.elements.elementsweb.Tasks.GridTemplateOfTaskElements;
import ru.motiw.web.model.Tasks.TemplateOfTask;
import ru.motiw.web.steps.Home.InternalSteps;
import ru.motiw.web.steps.Login.LoginStepsSteps;
import ru.motiw.web.steps.Tasks.TemplateOfTaskSteps;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.motiw.web.model.URLMenu.TASK_TEMPLATES;
import static ru.motiw.web.steps.BaseSteps.openSectionOnURL;

@Listeners({ScreenShotOnFailListener.class, TextReport.class})
@Report
public class CreateTemplateOfTask extends Tasks {

    private LoginStepsSteps loginPageSteps;
    private InternalSteps internalPageSteps;
    private TemplateOfTaskSteps templateOfTaskSteps;
    private GridTemplateOfTaskElements gridTemplateOfTaskElements;

    @BeforeClass
    public void beforeTest() {
        loginPageSteps = page(LoginStepsSteps.class);
        internalPageSteps = page(InternalSteps.class);
        templateOfTaskSteps = page(TemplateOfTaskSteps.class);
        gridTemplateOfTaskElements = page(GridTemplateOfTaskElements.class);
    }


    /**
     * Проверка создания шаблона задачи
     * @param templates шаблоны задачи
     */
    @Test(priority = 1, dataProvider = "objectDataTemplateOfTask", dataProviderClass = Tasks.class)
    public void verifyCreateTemplateOfTask(TemplateOfTask[] templates) {

        loginPageSteps.loginAs(ADMIN);
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPageSteps.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице

        //---------------------------------------------------------------- Шаблоны задач
        openSectionOnURL(TASK_TEMPLATES.getMenuURL());
        sleep(2000);
        gridTemplateOfTaskElements.getAddTemplateButton().waitUntil(Condition.visible, 5000);

        //Добавляем шаблон
        templateOfTaskSteps.createTemplateOfTask(templates);

        //Проверяем созданный шаблон в списке
        templateOfTaskSteps.verifyTemplateOfTask(templates);

        //Проверяем карточку созданного шаблона
        templateOfTaskSteps.verifyCreatedTemplate(templates);

    }


}

