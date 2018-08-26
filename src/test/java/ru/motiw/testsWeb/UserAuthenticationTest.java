package ru.motiw.testsWeb;

import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.motiw.data.dataproviders.UserAuthentication;
import ru.motiw.data.listeners.ScreenShotOnFailListener;
import ru.motiw.web.steps.Home.InternalSteps;
import ru.motiw.web.steps.Login.LoginStepsSteps;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;

@Listeners({ScreenShotOnFailListener.class, TextReport.class})
@Report
public class UserAuthenticationTest extends UserAuthentication {

    private LoginStepsSteps loginPageSteps;
    private InternalSteps internalPage;

    @BeforeClass
    public void beforeTest() {
        loginPageSteps = page(LoginStepsSteps.class);
        internalPage = page(InternalSteps.class);
    }

    @Test(priority = 1, dataProvider = "verifyFailAuthorizationWeb", dataProviderClass = UserAuthentication.class)
    public void notSuccessfulAuthorization(String login, String password) throws Exception {
        loginPageSteps.loginAs(login, password);
        sleep(500);
        assertTrue("Log in to the dataproviders fails", loginPageSteps.isNotLoggedIn());
    }

    @Test(description  = "Проверяем валидную авторизацию в системе", priority = 2)
    public void successfulAuthorization() throws Exception {
        loginPageSteps.loginAs(ADMIN);
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue("Inside page is loaded and displayed correctly", loginPageSteps.isLoggedIn());
        assertTrue("We check the user login that was used to login", loginPageSteps.newUserIsLoggedInAs(ADMIN));
        // Выход из системы
        internalPage.logout();
        assertTrue(loginPageSteps.isNotLoggedIn());
    }


}