package ru.motiw.testsPda;

import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.motiw.data.dataproviders.Tasks;
import ru.motiw.data.listeners.ScreenShotOnFailListener;
import ru.motiw.web.elements.elementspda.InternalStepsPDA;
import ru.motiw.web.elements.elementspda.LoginStepsPDA;
import ru.motiw.web.elements.elementspda.Task.TaskActionsStepsPDA;
import ru.motiw.web.steps.BaseSteps;
import ru.motiw.web.elements.elementspda.Task.NewTaskStepsPDA;
import ru.motiw.web.elements.elementspda.Task.TaskDescriptionStepsPDA;
import ru.motiw.web.elements.elementspda.Task.TasksReportsStepsPDA;
import ru.motiw.web.elements.elementspda.TodayStepsPDA;
import ru.motiw.web.model.Tasks.Folder;
import ru.motiw.web.model.Tasks.Task;
import ru.motiw.web.steps.Home.InternalSteps;
import ru.motiw.web.steps.Login.LoginStepsSteps;
import ru.motiw.web.steps.Tasks.UnionTasksSteps;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;
import static ru.motiw.web.steps.Tasks.UnionTasksSteps.goToUnionTasks;

@Listeners({ScreenShotOnFailListener.class, TextReport.class})
public class TodayPDATest extends Tasks {

    private LoginStepsSteps loginPageSteps;
    private InternalSteps internalPage;
    private UnionTasksSteps unionTasksSteps;

    @BeforeClass
    public void beforeTest() {
        loginPageSteps = page(LoginStepsSteps.class);
        internalPage = page(InternalSteps.class);
        unionTasksSteps = page(UnionTasksSteps.class);
    }

    /*
      Инициализируем модель - Задача #2 (атрибуты и лента для редактирования)
     */
    Task editTask = getRandomObjectTask();

    /*
     Инициализируем текст для Ленты действий задачи
     */
    String textActions = randomString(15);

    // Папка
    Folder[] folder = getRandomArrayFolders();

    @Test(priority = 1)
    public void createFolderForTasks() {

        loginPageSteps.loginAs(ADMIN);

        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице

        //---------------------------------------------------------------- Задачи/Задачи
        goToUnionTasks();
        unionTasksSteps.beforeAddFolder(19);
        // Добавляем Папки
        unionTasksSteps.addFolders(new Folder[]{folder[0].setNameFolder("wD_Smart_Box " + randomString(4)).setUseFilter(true).setFilterField("Начало").setChooseRelativeValue(true)
                .setSharedFolder(false).setAddSharedFolderForAll(false).setAddSharedFolderForNewUsers(false)});

        internalPage.logout();
        assertTrue(loginPageSteps.isNotLoggedIn());
    }

    @Test(priority = 1, dataProvider = "objectDataTaskPDA", dataProviderClass = Tasks.class)
    public void verifyInfoToday(Task task) throws Exception {
        LoginStepsPDA loginPagePDA = open(BaseSteps.PDA_PAGE_URL, LoginStepsPDA.class);
        loginPagePDA.loginAsAdmin(ADMIN);

        InternalStepsPDA internalPagePDA = loginPagePDA.goToInternalMenu(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 4 (Tasks; Create Tasks; Today; Document)",
                internalPagePDA.hasMenuUserComplete());

        // Инициализируем стр. формы создание задачи и переходим на нее
        NewTaskStepsPDA newTaskPagePDA = internalPagePDA.goToCreateTask();

        //----------------------------------------------------------------ФОРМА - создания Задачи
        newTaskPagePDA.creatingTask(task);
        TaskDescriptionStepsPDA taskDescriptionPagePDA = newTaskPagePDA.goToPreview(); // Инициализируем стр. формы предпросмотра задачи и переходим на нее

        //----------------------------------------------------------------ФОРМА - Предпросмотр создания задачи
        taskDescriptionPagePDA.inputValidationFormTask(task); // Проверяем отображение значений в форме предпросмотра создания задачи

        //----------------------------------------------------------------ФОРМА - Задачи
        TaskActionsStepsPDA taskForm = taskDescriptionPagePDA.goToTask(); // Инициализируем стр. формы - Созданной задачи и переходим на нее
        taskForm.openShapeCreatedTask(task); // Открываем созданную задачу
        assertTrue(taskForm.resultsDisplayButtons()); // Проверяем отображения кнопок в форме задачи
        internalPagePDA.goToHome();
        TasksReportsStepsPDA tasksReportsPagePDA = internalPagePDA.goToTaskReports(); // переходим в грид - Задачи/Задачи
        tasksReportsPagePDA.checkDisplayTaskGrid(task, folder[0]); // Проверяем отображение созданной задачи в гриде Задач
        tasksReportsPagePDA.openTaskInGrid(task); // открываем форму в гриде задач

        //----------------------------------------------------------------ФОРМА - Задачи - Атрибуты

        taskForm.openFormEditTask(task, EMPLOYEE_ADMIN); // открываем форму редактирования атрибутов задачи
        taskDescriptionPagePDA.editAttributesOfTasks(editTask); // редактируем задачу
        taskForm.saveActionsInTheTape(textActions); // добавляем пользовательский текст в задачу и проверяем его сохранение
        internalPagePDA.goToHome();
        TodayStepsPDA todayPagePDA = internalPagePDA.goToToday(); // Переходим на стр.
        todayPagePDA.verifyInformationDisplaySectionToday(textActions);

        internalPagePDA.logout(); // Выход из системы
        assertTrue(loginPagePDA.isNotLoggedInPDA());

    }


}
