package ru.motiw.testsWeb.Tasks;

import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.motiw.data.BaseTest;
import ru.motiw.data.listeners.ScreenShotOnFailListener;
import ru.motiw.web.model.Administration.Users.Department;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Administration.Users.Module;
import ru.motiw.web.model.Tasks.Folder;
import ru.motiw.web.steps.Administration.Users.UsersSteps;
import ru.motiw.web.steps.Home.InternalSteps;
import ru.motiw.web.steps.Login.LoginStepsSteps;
import ru.motiw.web.steps.Tasks.UnionTasksSteps;

import static com.codeborne.selenide.Selenide.page;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;
import static ru.motiw.data.dataproviders.Administration.getRandomDepartment;
import static ru.motiw.data.dataproviders.Administration.getRandomEmployer;
import static ru.motiw.data.dataproviders.Tasks.getRandomArrayFolders;
import static ru.motiw.web.steps.Administration.Users.DepartmentSteps.goToURLDepartments;
import static ru.motiw.web.steps.Tasks.UnionTasksSteps.goToUnionTasks;

@Listeners({ScreenShotOnFailListener.class, TextReport.class})
@Report
public class UnionTasksTest extends BaseTest {

    private LoginStepsSteps loginPageSteps;
    private InternalSteps internalPageSteps;
    private UsersSteps userPageSteps;
    private UnionTasksSteps unionTasksSteps;

    @BeforeClass
    public void beforeTest() {
        loginPageSteps = page(LoginStepsSteps.class);
        internalPageSteps = page(InternalSteps.class);
        userPageSteps = page(UsersSteps.class);
        unionTasksSteps = page(UnionTasksSteps.class);
    }

    // Масси объекта - Папка
    Folder[] folder = getRandomArrayFolders();

    /*
      * Инициализация переменных - Подразделение
      */
    Department department = getRandomDepartment();

    /*
     * Инициализация переменных - Пользователь
     */
    Employee user = getRandomEmployer()
            .setNeedsPasswordChange(false) // сбросить признак - "Сменить пароль при следующем входе" (true - изменяем значение; false - оставляем без изменений)
            .setDepartment(department) // определяем пользователя в подразделение
            .setModule(Module.COMPLETE);

    @Test(priority = 1)
    public void verifyCreateFolder() {
        loginPageSteps.loginAs(BaseTest.ADMIN);
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPageSteps.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице

        //---------------------------------------------------------------- Задачи/Задачи
        goToUnionTasks();
        unionTasksSteps.beforeAddFolder(20)
                // Добавляем Папки(/у)
                .addFolders(new Folder[]{
                        folder[0].setNameFolder("wD_Smart_Box " + BaseTest.randomString(4)).setUseFilter(true).setFilterField("Начало").setChooseRelativeValue(true)
                                .setSharedFolder(false).setAddSharedFolderForAll(false).setAddSharedFolderForNewUsers(false),
                        folder[1].setNameFolder("Обычная папка " + BaseTest.randomString(4)).setUseFilter(false).setParentFolder(folder[0]).setSharedFolder(false)
                                .setAddSharedFolderForAll(false).setAddSharedFolderForNewUsers(false),
                        // Общая папка
                        folder[2].setNameFolder("Общая папка " + BaseTest.randomString(4)).setUseFilter(false).setSharedFolder(true)
                                .setAddSharedFolderForAll(false).setAddSharedFolderForNewUsers(false),
                        // Добавить всем
                        folder[3].setNameFolder("Общая папка + Добавить всем " + BaseTest.randomString(4)).setUseFilter(false).setParentFolder(folder[1])
                                .setSharedFolder(false).setAddSharedFolderForAll(true).setAddSharedFolderForNewUsers(false),
                        // Добавить для новых пользователей
                        folder[4].setNameFolder("Общая папка + Добавлять для нов. польз. " + BaseTest.randomString(4)).setUseFilter(false).setSharedFolder(true)
                                .setAddSharedFolderForAll(false).setAddSharedFolderForNewUsers(true),

                });

        internalPageSteps.logout();
        Assert.assertTrue(loginPageSteps.isNotLoggedIn());
        // TODO 0001 - Редактирование Папки + Удаление чистка после теста!!!
    }

    @Test(dependsOnMethods = {"verifyCreateFolder"})
    public void verifyDisplaySharedFolderTheNewUser() {
        loginPageSteps.loginAs(BaseTest.ADMIN);
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPageSteps.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPageSteps.isLoggedIn());

        // Создаем подразделения для пользователей
        goToURLDepartments().createDepartment(department);

        userPageSteps.createUser(user); // Создание пользователя

        // Выход из системы
        internalPageSteps.logout();
        assertTrue(loginPageSteps.isNotLoggedIn()); // Проверка того, что пользователь разлогинен

        /*
         * Верификация папки - ОП (общая папка)- под вновь созданными пользователями
         */
        loginPageSteps.loginAs(user);

        //---------------------------------------------------------------- Задачи/Задачи
        goToUnionTasks().checkTheMapASharedFolderFromTheNewlyCreatedUser(folder[4], 20);

        internalPageSteps.logout(); // Выход из системы
        assertTrue(loginPageSteps.isNotLoggedIn()); // Проверка того, что пользователь разлогинен

        // Авторищируемся вновь под ADMIN - Удаляем Подразделение и Пользователя
        loginPageSteps.loginAs(BaseTest.ADMIN);
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPageSteps.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPageSteps.isLoggedIn());

        goToURLDepartments();
        userPageSteps.deleteUser(user)
                .deleteDepartment(department);

        // Выход из Системы
        internalPageSteps.logout();
        assertTrue(loginPageSteps.isNotLoggedIn());

    }

}
