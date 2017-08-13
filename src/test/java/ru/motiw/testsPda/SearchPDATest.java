package ru.motiw.testsPda;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.testng.TextReport;
import ru.motiw.data.BaseTest;
import ru.motiw.data.dataproviders.Tasks;
import ru.motiw.data.listeners.ScreenShotOnFailListener;
import ru.motiw.web.elements.elementspda.InternalStepsPDA;
import ru.motiw.web.elements.elementspda.SearchStepsPDA;
import ru.motiw.web.steps.BaseSteps;
import ru.motiw.web.elements.elementspda.LoginStepsPDA;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

@Listeners({ScreenShotOnFailListener.class, TextReport.class})
public class SearchPDATest extends Tasks {

    @Test(priority = 1)
    public void verifySearchContact() throws Exception {
        LoginStepsPDA loginPagePDA = Selenide.open(BaseSteps.PDA_PAGE_URL, LoginStepsPDA.class);
        loginPagePDA.loginAsAdmin(ADMIN);
        InternalStepsPDA internalPagePDA = loginPagePDA.goToInternalMenu(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 4 (Tasks; Create Tasks; Today; Document)",
                internalPagePDA.hasMenuUserComplete());
        SearchStepsPDA searchPagePDA = internalPagePDA.goToSearch(); // Переходим в раздел Поиска
        searchPagePDA.searchContact(BaseTest.EMPLOYEE_ADMIN); // проверяем поиск Контакта пользователя по Фамилии

        internalPagePDA.logout(); // Выход из системы
        assertTrue(loginPagePDA.isNotLoggedInPDA());
    }


}


