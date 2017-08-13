package ru.motiw.testsWeb.Administration;

import com.automation.remarks.testng.VideoListener;
import com.automation.remarks.video.annotations.Video;
import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.motiw.data.BaseTest;
import ru.motiw.data.listeners.ScreenShotOnFailListener;
import ru.motiw.web.steps.Administration.SearchSystemSteps;
import ru.motiw.web.steps.Administration.SystemInformationSteps;
import ru.motiw.web.steps.Home.InternalSteps;
import ru.motiw.web.steps.Login.LoginStepsSteps;

import static com.codeborne.selenide.Selenide.page;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;
import static ru.motiw.web.steps.Administration.SearchSystemSteps.goToURLSearchSystemPage;
import static ru.motiw.web.steps.Administration.SystemInformationSteps.goToURLSystemInfo;


@Listeners({ScreenShotOnFailListener.class, TextReport.class, VideoListener.class})
@Report
public class SystemInformationTest extends BaseTest {

    private LoginStepsSteps loginPageSteps;
    private InternalSteps internalPage;
    private SystemInformationSteps systemInformationSteps;
    private SearchSystemSteps searchSystemSteps;

    @BeforeClass
    public void beforeTest() {
        loginPageSteps = page(LoginStepsSteps.class);
        internalPage = page(InternalSteps.class);
        systemInformationSteps = page(SystemInformationSteps.class);
        searchSystemSteps = page(SearchSystemSteps.class);
    }

    @Video(name = "Неработоспособные службы системы")
    @Test(priority = 1)
    public void verifyNotRedSystemInfo() throws Exception {
        loginPageSteps.loginAs(ADMIN);
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPageSteps.isLoggedIn());

        // Переход в раздел Администрирование/Информация о системе
        goToURLSystemInfo();
        assertTrue("all services are running, and are shown in green",
                systemInformationSteps.checkingOfSystemServices());

        // Выход из системы
        internalPage.logout();
        // Проверка - пользователь разлогинен
        assertTrue(loginPageSteps.isNotLoggedIn());
    }

    @Video(name = "Ошибки в индексах - Поискова система")
    @Test(priority = 2)
    public void verifyNotIndexingErrors() throws Exception {
        loginPageSteps.loginAs(ADMIN);
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPageSteps.isLoggedIn());

        // Переход в раздел Администрирование/Поисковая система
        goToURLSearchSystemPage();
        assertTrue("The absence of errors in the search indexes",
                searchSystemSteps.checkNotIndexingErrors()); // Проверяем отсутствие ошибок в индексах

        // Выход из системы
        internalPage.logout();
        // Проверка - пользователь разлогинен
        assertTrue(loginPageSteps.isNotLoggedIn());
    }


}