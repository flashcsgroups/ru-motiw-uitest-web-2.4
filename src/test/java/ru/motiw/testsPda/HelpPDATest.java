package ru.motiw.testsPda;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.testng.TextReport;
import ru.motiw.web.elements.elementspda.LoginStepsPDA;
import ru.motiw.web.steps.BaseSteps;
import ru.motiw.web.elements.elementspda.HelpHtmlStepsPDA;
import ru.motiw.web.elements.elementspda.InternalStepsPDA;
import ru.motiw.data.dataproviders.Tasks;
import ru.motiw.data.listeners.ScreenShotOnFailListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


@Listeners({ScreenShotOnFailListener.class, TextReport.class})
public class HelpPDATest extends Tasks {

    @Test(priority = 1)
    public void verifyElementsHelp() throws Exception {
        LoginStepsPDA loginPagePDA = Selenide.open(BaseSteps.PDA_PAGE_URL, LoginStepsPDA.class);
        loginPagePDA.loginAsAdmin(ADMIN);
        InternalStepsPDA internalPagePDA = loginPagePDA.goToInternalMenu(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 4 (Tasks; Create Tasks; Today; Document)",
                internalPagePDA.hasMenuUserComplete());

        // Инициализируем стр. формы создание задачи и переходим на нее
        HelpHtmlStepsPDA helpPage = internalPagePDA.goToHelpHtml();
        helpPage.checkPresenceElementsOfAid(); // Проверяем общее количество элементов помощи
        helpPage.visibleElementsTextHelp(); // Проверяем отображение текста в элементах помощи
        assertEquals(19, helpPage.results().size()); // проверяем кол-во элементов на стр-це помощи

        internalPagePDA.logout(); // Выход из системы
        assertTrue(loginPagePDA.isNotLoggedInPDA());

    }

}
