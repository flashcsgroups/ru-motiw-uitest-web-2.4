package ru.motiw.testsWeb.Administration;

import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.motiw.data.dataproviders.Administration;
import ru.motiw.data.listeners.ScreenShotOnFailListener;
import ru.motiw.web.model.Administration.Directories.Directories;
import ru.motiw.web.model.Administration.TypesOfTables.TypesOfTables;
import ru.motiw.web.steps.Administration.Directories.DirectoriesEditSteps;
import ru.motiw.web.steps.Administration.Directories.DirectoriesSteps;
import ru.motiw.web.steps.Administration.TypesOfTables.TypesOfTablesEditSteps;
import ru.motiw.web.steps.Home.InternalSteps;
import ru.motiw.web.steps.Login.LoginStepsSteps;

import static com.codeborne.selenide.Selenide.page;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;
import static ru.motiw.web.steps.Administration.TypesOfTables.TypesOfTablesSteps.goToURLTypesOfTables;

@Listeners({ScreenShotOnFailListener.class, TextReport.class})
@Report
public class TypesOfTablesTest extends Administration {

    private TypesOfTablesEditSteps formEditTable;
    private LoginStepsSteps loginPageSteps;
    private InternalSteps internalPageSteps;

    @BeforeClass
    public void beforeTest() {
        formEditTable = page(TypesOfTablesEditSteps.class);
        loginPageSteps = page(LoginStepsSteps.class);
        internalPageSteps = page(InternalSteps.class);
    }

    @Test(priority = 1, dataProvider = "objectDataTypesOfTable", dataProviderClass = Administration.class)
    public void checkTheCreationOfAnObjectTypeTable(Directories directories, TypesOfTables typesOfTables) throws Exception {
        loginPageSteps.loginAs(ADMIN);
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPageSteps.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице

        // Переход в раздел Администрирование/Справочники
        DirectoriesSteps.goToURLDirectories().addObjectTaskTypeList(directories.getObjectTypeName());  // Добавляем объект - Справочник

        DirectoriesEditSteps directoriesEditSteps = new DirectoriesEditSteps().directoriesEditPage();

        assertTrue("check display the form directories editing", directoriesEditSteps.loadedDirectoriesEditPage());

        // Добавляем поля и сохранеяем объект
        directoriesEditSteps.addAllFieldsTaskTypeList(directories).saveChangesOnObject(directories);

        //------------------------------------------------- Администрирование/Типы таблиц
        goToURLTypesOfTables().addObjectTaskTypeList(typesOfTables.getObjectTypeName());
        // Добавляем настройки и поля Типы таблицы
        formEditTable.addAllFieldsTaskTypeList(typesOfTables).saveChangesOnObject(typesOfTables);

        internalPageSteps.logout();
        // Проверка - пользователь разлогинен
        assertTrue(loginPageSteps.isNotLoggedIn());


    }


}
