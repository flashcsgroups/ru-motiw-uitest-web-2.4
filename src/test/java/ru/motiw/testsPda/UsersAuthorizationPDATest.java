package ru.motiw.testsPda;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.testng.TextReport;
import ru.motiw.data.BaseTest;
import ru.motiw.data.dataproviders.UserAuthentication;
import ru.motiw.web.elements.elementspda.LoginStepsPDA;
import ru.motiw.web.steps.BaseSteps;
import ru.motiw.web.elements.elementspda.InternalStepsPDA;
import ru.motiw.data.listeners.ScreenShotOnFailListener;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;


@Listeners({ScreenShotOnFailListener.class, TextReport.class})

public class UsersAuthorizationPDATest extends UserAuthentication {

    @Test(priority = 3)
    public void verifyLoginSuccess() throws Exception {
        LoginStepsPDA loginPagePDA = Selenide.open(BaseSteps.PDA_PAGE_URL, LoginStepsPDA.class);
        loginPagePDA.loginAsAdmin(BaseTest.ADMIN);
        InternalStepsPDA internalPagePDA = loginPagePDA.goToInternalMenu(); // Проверяем отображение п.м. системы
        assertThat("Check that the displayed menu item 4 (Tasks; Create Tasks; Today; Document)",
                internalPagePDA.hasMenuUserComplete());
        internalPagePDA.logout(); // Выход из системы
    }

    @Test(priority = 1, dataProvider = "verifyFailAuthorization", dataProviderClass = UserAuthentication.class)
    public void verifyFailAuthorization(String login, String password) throws Exception {
        LoginStepsPDA loginPagePDA = open(BaseSteps.PDA_PAGE_URL, LoginStepsPDA.class);
        loginPagePDA.loginAs(login, password);
        assertTrue(loginPagePDA.isNotLoggedInPDA());
        $(By.cssSelector("#error")).shouldBe(Condition.exactText("Доступ запрещен"));

    }

    @Test(priority = 2, dataProvider = "secondVerifyFailAuthorization", dataProviderClass = UserAuthentication.class)
    public void secondVerifyFailAuthorization(String login, String pass) throws Exception {
        LoginStepsPDA loginPagePDA = open(BaseSteps.PDA_PAGE_URL, LoginStepsPDA.class);
        loginPagePDA.loginAs(login, pass);
        assertTrue(loginPagePDA.isNotLoggedInPDA());
    }


}