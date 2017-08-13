package ru.motiw.testsWeb.Administration;

import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.motiw.data.dataproviders.Administration;
import ru.motiw.data.listeners.ScreenShotOnFailListener;
import ru.motiw.web.model.Administration.Directories.Directories;
import ru.motiw.web.model.Administration.TasksTypes.TasksTypes;
import ru.motiw.web.model.Administration.TypesOfTables.TypesOfTables;
import ru.motiw.web.steps.Administration.Directories.DirectoriesEditSteps;
import ru.motiw.web.steps.Administration.TasksTypes.TaskTypesEditSteps;
import ru.motiw.web.steps.Administration.TypesOfTables.TypesOfTablesEditSteps;
import ru.motiw.web.steps.Home.InternalSteps;
import ru.motiw.web.steps.Login.LoginStepsSteps;

import static com.codeborne.selenide.Selenide.page;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;
import static ru.motiw.web.steps.Administration.Directories.DirectoriesSteps.goToURLDirectories;
import static ru.motiw.web.steps.Administration.TasksTypes.TaskTypesSteps.goToURLTaskTypes;
import static ru.motiw.web.steps.Administration.TypesOfTables.TypesOfTablesSteps.goToURLTypesOfTables;

@Listeners({ScreenShotOnFailListener.class, TextReport.class})
@Report
public class TasksTypeTest extends Administration {

    private LoginStepsSteps loginPageSteps;
    private InternalSteps internalPageSteps;
    private TaskTypesEditSteps formEditTaskTypes;
    private TypesOfTablesEditSteps formEditTable;

    @BeforeClass
    public void beforeTest() {
        loginPageSteps = page(LoginStepsSteps.class);
        internalPageSteps = page(InternalSteps.class);
        formEditTaskTypes = page(TaskTypesEditSteps.class);
        formEditTable = page(TypesOfTablesEditSteps.class);
    }

    @Test(priority = 1, dataProvider = "objectDataTasksTypes", dataProviderClass = Administration.class)
    public void verifyCreateTaskTypes(Directories directories, TypesOfTables typesOfTables,
                                      TasksTypes tasksTypes) throws Exception {
        loginPageSteps.loginAs(ADMIN);
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPageSteps.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPageSteps.isLoggedIn());

        //------------------------------------------------- Администрирование/Справочники
        goToURLDirectories().addObjectTaskTypeList(directories.getObjectTypeName());  // Добавляем объект - Справочник
        DirectoriesEditSteps directoriesEditSteps = new DirectoriesEditSteps().directoriesEditPage();
        assertTrue("check display the form directories editing", directoriesEditSteps.loadedDirectoriesEditPage());
        // Добавляем поля объекта
        directoriesEditSteps.addAllFieldsTaskTypeList(directories).saveChangesOnObject(directories);

        //------------------------------------------------- Администрирование/Типы таблиц
        goToURLTypesOfTables().addObjectTaskTypeList(typesOfTables.getObjectTypeName());
        // Добавляем настройки и поля Типы таблицы
        formEditTable.addAllFieldsTaskTypeList(typesOfTables).saveChangesOnObject(typesOfTables);

        //------------------------ Администрирование/Типы задач
        goToURLTaskTypes().addObjectTaskTypeList(tasksTypes.getObjectTypeName());

        formEditTaskTypes.addAllFieldsTaskTypeList(tasksTypes);
        // Направление смещение даты
        formEditTaskTypes.setDirectionOfDisplacementOfTheDate(tasksTypes)
                .setUseEP(tasksTypes) // Настройки ЭП
                .setDateCorrection(tasksTypes) // Корректировка даты
                .setIsTaskTypeChangeDisabled(tasksTypes)  // Запретить изменение типа для созданной задачи
                .setOnlyTheSameTypeIWG(tasksTypes) // Создавать подзадачи ИРГ только родительского типа
                .setIsCloseTaskWithNotReadyCheckpointsDisabled(tasksTypes)  // Запретить закрытие задач с неготовыми контрольными точками
                .setOpenFilesForEdit(tasksTypes)  // Открывать файлы для редактирования
                .setAttachFilesAndDescriptionOfTheAction(tasksTypes)  // Добавлять файлы в Лента действий / Описание

                .saveChangesOnObject(tasksTypes);




        // разлогиниться
        internalPageSteps.logout();
        // Проверка - пользователь разлогинен
        assertTrue(loginPageSteps.isNotLoggedIn());

    }

}
