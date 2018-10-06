package ru.motiw.testMobile;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.motiw.data.dataproviders.Tasks;
import ru.motiw.data.listeners.ScreenShotOnFailListener;
import ru.motiw.mobile.steps.Folders.GridOfFoldersSteps;
import ru.motiw.mobile.steps.InternalStepsMobile;
import ru.motiw.mobile.steps.LoginStepsMobile;
import ru.motiw.mobile.steps.Tasks.NewTaskStepsMobile;
import ru.motiw.mobile.steps.Tasks.TaskStepsMobile;
import ru.motiw.web.model.Administration.Users.Department;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Tasks.Folder;
import ru.motiw.web.model.Tasks.Task;
import ru.motiw.web.steps.Home.InternalSteps;
import ru.motiw.web.steps.Login.LoginStepsSteps;
import ru.motiw.web.steps.Tasks.UnionTasksSteps;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;
import static ru.motiw.web.steps.Tasks.UnionTasksSteps.goToUnionTasks;

@Listeners({ScreenShotOnFailListener.class, TextReport.class})
@Report
public class CreateTaskMobileTest extends Tasks {


    private LoginStepsSteps loginPageSteps;
    private InternalSteps internalPageSteps;
    private InternalStepsMobile internalPageStepsMobile;
    private UnionTasksSteps unionTasksSteps;
    private TaskStepsMobile taskStepsMobile;
    private LoginStepsMobile loginStepsMobile;
    private NewTaskStepsMobile newTaskStepsMobile;
    private GridOfFoldersSteps gridOfFoldersSteps;

    @BeforeClass
    public void beforeTest() {
        loginPageSteps = page(LoginStepsSteps.class);
        internalPageSteps = page(InternalSteps.class);
        internalPageStepsMobile = page(InternalStepsMobile.class);
        unionTasksSteps = page(UnionTasksSteps.class);
        taskStepsMobile =page(TaskStepsMobile.class);
        loginStepsMobile = page(LoginStepsMobile.class);
        newTaskStepsMobile = page(NewTaskStepsMobile.class);
        gridOfFoldersSteps = page(GridOfFoldersSteps.class);
    }


    // Атрибуты задачи для редактирования задачи
    Task editTask = getRandomObjectTask();

    // Папка
    Folder[] folder = getRandomArrayFolders();


    @Test(priority = 1, dataProvider = "objectDataTask", dataProviderClass = Tasks.class)
    public void aPreconditionForFurtherVerification(Department department, Employee[] author, Employee[] resppers, Employee[] controller, Employee[] worker,
                                                    Employee[] IWGWorker, Employee[] IWGResppers, Employee[] IWGСontroller, Task task) {
        loginPageSteps.loginAs(ADMIN);
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPageSteps.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице

        //---------------------------------------------------------------- Задачи/Задачи
        goToUnionTasks();
        unionTasksSteps.beforeAddFolder(20);
        // Добавляем Папки(/у)
        unionTasksSteps.addFolders(new Folder[]{folder[0].setNameFolder("wD_Smart_Box " + randomString(4))
                .setUseFilter(true).setFilterField("Начало").setChooseRelativeValue(true)
                .setSharedFolder(false).setAddSharedFolderForAll(false).setAddSharedFolderForNewUsers(false)});

        /*
        //---------------------------------------------------------------- Настройки системы
        goToURLSystemOptionsPage()
                // Выбор опции - Секретная задача == Да
                .selectCreatingASecretTask()
                // Выбор опции - Удаление себя из задач == Да
                .selectionRemoveYourselfFromTheTasks();

        internalPageSteps.logout();
        assertTrue(loginPageSteps.isNotLoggedIn());
        */

        // Выход
        internalPageSteps.logout();
        // Проверка - пользователь разлогинен
        assertTrue(loginPageSteps.isNotLoggedIn());
        clearBrowserCache(); //чистим кеш, т.к после логаута в вебе пользователь все равно остается залогинен (баг после работы в user/tab/user/uniontasks/)
    }


    @Test(priority = 2, dataProvider = "objectDataTaskPDA", dataProviderClass = Tasks.class)
    public void verifyCreateTaskMobile(Task task) throws Exception {


        //LoginStepsPDA loginPagePDA = open(BaseSteps.PDA_PAGE_URL, LoginStepsPDA.class);
       // LoginStepsMobile loginStepsMobile = open(BaseStepsMobile.MOBILE_PAGE_URL, LoginStepsMobile.class); - эти две строчки с такой инициализациеей
        // заменил на инициализацию в начале см. private LoginStepsMobile loginStepsMobile; Если все будет хорошо - можно будет убрать эти строчки.


        //Переход в мобильную версию по ссылке в форме авторизации
        $(By.xpath("//a[@class=\"m_link\"]")).waitUntil(visible, 10000);
        $(By.xpath("//a[@class=\"m_link\"]")).click();

        $(By.xpath("//span[contains(text(),'Имя')]//ancestor::div[1]//input")).waitUntil(Condition.visible, 10000);
        // Авторизация
        loginStepsMobile.loginAs(ADMIN);
        // Ожидание скрытия маски загрузки
        $(By.xpath("//div[@class=\"x-loading-spinner-outer\"]")).waitUntil(Condition.disappear, 10000);
        // Ожидание кнопки Главного Меню
        $(By.xpath("//div[@class=\"x-component x-button no-blue-alt x-has-icon x-icon-align-left x-arrow-align-right x-button-alt x-component-alt x-layout-box-item x-layout-hbox-item\"][1]")).waitUntil(Condition.visible, 10000);

        internalPageStepsMobile.goToInternalMenu(); // Открываем главное меню
        assertThat("Check that the displayed menu item 9 (User Info; Tasks And Documents; Create Tasks; Today; Search; Settings; Help; Exit; Go To Full Version)",
               internalPageStepsMobile.hasMenuUserComplete());

        //----------------------------------------------------------------ФОРМА - создания Задачи
        newTaskStepsMobile.goToCreateOfNewTask().creatingTask(task);
        taskStepsMobile.fieldsWhenGroupsClosed(); //проверка наличия полей при закрытых группах полей
        taskStepsMobile.fieldsWhenGroupsOpen();//проверка наличия полей при открытых группах полей
        taskStepsMobile.verifyValueWhenGroupsClosed(task); //проверка введенных значений в полях при закрытых группах полей
        taskStepsMobile.verifyValueWhenGroupsOpen(task); //проверка введенных значений в полях при открытых группах полей
        newTaskStepsMobile.saveTask();

        //Ждем пока исчезнит маска загрузки
        $(By.xpath("(//div[@class=\"x-loading-spinner-outer\"])[2]")).waitUntil(Condition.disappear, 10000);// маска загрузки в форме задачи
        // маска загрузки (//div[@class="x-loading-spinner-outer"])[2] в форме задачи - динамический элемент.
        // сколько октроется загрузок - столько будет этих элементов - лучше особо не привязываться к нему. Можно будет прибегать refresh

        //Проверяем появление toast "Создана задача"
        $(By.xpath("//div[contains(@class,'x-toast x-sheet x-panel')]")).waitUntil(Condition.visible, 10000);
        $(By.xpath("//div[contains(@class,'x-toast x-sheet x-panel')]//a")).shouldHave(Condition.text("Создана задача №"));

        //Переходим по ссылке в появившемся toast в созданную задачу
        $(By.xpath("//div[contains(@class,'x-toast x-sheet x-panel')]//a")).click();

        //----------------------------------------------------------------ФОРМА - Задачи
        // Проверяем отображение значений в форме созданой задачи
        taskStepsMobile.verifyCreateTask(task);
        // Переходим на корневую страницу папок
        internalPageStepsMobile.goToHome();

        //----------------------------------------------------------------ГРИД - Папка
        sleep(500); //ожидание папок;
        // Проверяем отображение созданной задачи в гриде папки
        gridOfFoldersSteps.checkDisplayTaskGrid(task, folder[0]);
        // Выход из системы
        internalPageStepsMobile.logout();
    }

/*
    @Test(priority = 3, dataProvider = "objectDataTaskPDA", dataProviderClass = Tasks.class)
    public void checkEditingTaskPDA(Tasks task) throws Exception {
        // Авторизация
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

        internalPagePDA.goToHome(); // Домашняя стр-ца

        TasksReportsStepsPDA tasksReportsPagePDA = internalPagePDA.goToTaskReports(); // переходим в грид - Задачи/Задачи
        tasksReportsPagePDA.checkDisplayTaskGrid(task, folder[0]); // Проверяем отображение созданной задачи в гриде Задач
        tasksReportsPagePDA.openTaskInGrid(task); // открываем форму в гриде задач

        //----------------------------------------------------------------ФОРМА - Задачи - Атрибуты

        taskForm.openFormEditTask(task, EMPLOYEE_ADMIN); // открываем форму редактирования атрибутов задачи
        taskDescriptionPagePDA.editAttributesOfTasks(editTask); // редактируем атрибуты задачи
        taskForm.saveActionsInTheTape(randomString(15)); // добавляем пользовательский текст в задачу и проверяем его сохранение
        taskDescriptionPagePDA.editWorkingGroupInTask(EMPLOYEE_ADMIN); // редактируем РГ задачи (удаляем пользователей)

        internalPagePDA.logout(); // Выход из системы
        assertTrue(loginPagePDA.isNotLoggedInPDA());

    }


    @Test(priority = 4, dataProvider = "objectDataTaskPDA", dataProviderClass = Tasks.class)
    public void verifyCompletionOfTheTaskPDA(Tasks task) throws Exception {
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

        taskForm.openShapeCreatedTask(task); // Открываем форму созданной задачу
        assertTrue(taskForm.resultsDisplayButtons()); // Проверяем отображения кнопок в форме задачи
        internalPagePDA.goToHome();
        TasksReportsStepsPDA tasksReportsPagePDA = internalPagePDA.goToTaskReports(); // переходим в грид - Задачи/Задачи
        taskForm.closeTask(task, randomString(15), folder[0]); // Закрываем задачу (отправляем в архив)

        internalPagePDA.goToHome(); // Возвращаемся домой (внутренняя страница)
        internalPagePDA.goToTaskReports(); // переходим в грид задач

        tasksReportsPagePDA.checkDisappearTaskInGrid(task);

        internalPagePDA.logout(); // Выход из системы
        assertTrue(loginPagePDA.isNotLoggedInPDA());


    }
*/

}
