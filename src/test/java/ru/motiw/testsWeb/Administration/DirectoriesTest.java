package ru.motiw.testsWeb.Administration;

import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.motiw.data.dataproviders.Administration;
import ru.motiw.data.listeners.ScreenShotOnFailListener;
import ru.motiw.web.model.Administration.Directories.Directories;
import ru.motiw.web.steps.Administration.Directories.DirectoriesEditSteps;
import ru.motiw.web.steps.Administration.Directories.NewRecordDirectoriesSteps;
import ru.motiw.web.steps.Home.InternalSteps;
import ru.motiw.web.steps.Login.LoginStepsSteps;

import static com.codeborne.selenide.Selenide.page;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;
import static ru.motiw.web.steps.Administration.Directories.DirectoriesSteps.goToURLDirectories;
import static ru.motiw.web.steps.Home.InternalSteps.goToInternalPage;

@Listeners({ScreenShotOnFailListener.class, TextReport.class})
@Report
public class DirectoriesTest extends Administration {

    private LoginStepsSteps loginPageSteps;
    private InternalSteps internalPage;
    private NewRecordDirectoriesSteps newRecordDirectoriesSteps;

    @BeforeClass
    public void beforeTest() {
        loginPageSteps = page(LoginStepsSteps.class);
        internalPage = page(InternalSteps.class);
        newRecordDirectoriesSteps = page(NewRecordDirectoriesSteps.class);
    }

    @Test(priority = 1, dataProvider = "objectDataDirectories", dataProviderClass = Administration.class)
    public void checkCreateDirectories(Directories directories) throws Exception {
        loginPageSteps.loginAs(ADMIN);
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице


        // Переход в раздел Администрирование/Справочники
        goToURLDirectories().addObjectTaskTypeList(directories.getObjectTypeName());  // Добавляем объект - Справочник

        DirectoriesEditSteps directoriesEdit = new DirectoriesEditSteps().directoriesEditPage();

        assertTrue("check display the form directories editing", directoriesEdit.loadedDirectoriesEditPage());

        // Добавляем поля объекта
        directoriesEdit.addAllFieldsTaskTypeList(directories);
        // Добавляем настройки
        directoriesEdit.setRecordsAvailableDirectories(directories)
                .setMappingDevice(directories)
                .setSearchSettings(directories)
                .saveChangesOnObject(directories);

        goToInternalPage(); // Инициализируем внутренюю стр. системы и переходим на нее

        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPageSteps.isLoggedIn());
        internalPage.logout();
        // Проверка - пользователь разлогинен
        assertTrue(loginPageSteps.isNotLoggedIn());
    }

    @Test(priority = 2, dataProvider = "objectRandomDataDirectories", dataProviderClass = Administration.class)
    public void checkAddRecordsAtDirectories(Directories registerCardDirectories, Directories directories) throws Exception {

        loginPageSteps.loginAs(ADMIN);
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице


        // Переход в раздел Администрирование/Справочники
        goToURLDirectories().addObjectTaskTypeList(registerCardDirectories.getObjectTypeName());  // Добавляем объект - Справочник

        DirectoriesEditSteps directoriesEdit = new DirectoriesEditSteps().directoriesEditPage();

        assertTrue("check display the form directories editing", directoriesEdit.loadedDirectoriesEditPage());

        // Добавляем поля объекта
        directoriesEdit.addAllFieldsTaskTypeList(registerCardDirectories);
        // Добавляем настройки
        directoriesEdit.setRecordsAvailableDirectories(registerCardDirectories)
                .setMappingDevice(registerCardDirectories)
                .setSearchSettings(registerCardDirectories)
                .saveChangesOnObject(registerCardDirectories);

        // Добавление записи справочника
        newRecordDirectoriesSteps.goTo(directories);
    }

}
