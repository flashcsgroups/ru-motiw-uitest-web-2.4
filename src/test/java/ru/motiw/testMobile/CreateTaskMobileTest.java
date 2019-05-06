package ru.motiw.testMobile;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.motiw.data.dataproviders.TasksMobile;
import ru.motiw.data.listeners.ScreenShotOnFailListener;
import ru.motiw.mobile.steps.Folders.GridOfFoldersSteps;
import ru.motiw.mobile.steps.InternalStepsMobile;
import ru.motiw.mobile.steps.LoginStepsMobile;
import ru.motiw.mobile.steps.Tasks.*;
import ru.motiw.web.elements.elementsweb.Tasks.GridTemplateOfTaskElements;
import ru.motiw.web.model.Administration.Directories.Directories;
import ru.motiw.web.model.Administration.TasksTypes.TasksTypes;
import ru.motiw.web.model.Administration.Users.Department;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Tasks.Folder;
import ru.motiw.web.model.Tasks.Task;
import ru.motiw.web.model.Tasks.TemplateOfTask;
import ru.motiw.web.steps.Administration.Directories.DirectoriesEditSteps;
import ru.motiw.web.steps.Administration.Directories.NewRecordDirectoriesSteps;
import ru.motiw.web.steps.Administration.TasksTypes.TaskTypesEditSteps;
import ru.motiw.web.steps.Administration.Users.UsersSteps;
import ru.motiw.web.steps.Home.InternalSteps;
import ru.motiw.web.steps.Login.LoginStepsSteps;
import ru.motiw.web.steps.Tasks.TemplateOfTaskSteps;
import ru.motiw.web.steps.Tasks.UnionTasksSteps;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static java.lang.Thread.sleep;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.motiw.web.model.URLMenu.TASK_TEMPLATES;
import static ru.motiw.web.steps.Administration.Directories.DirectoriesSteps.goToURLDirectories;
import static ru.motiw.web.steps.Administration.TasksTypes.TaskTypesSteps.goToURLTaskTypes;
import static ru.motiw.web.steps.Administration.Users.DepartmentSteps.goToURLDepartments;
import static ru.motiw.web.steps.BaseSteps.openSectionOnURL;
import static ru.motiw.web.steps.Tasks.UnionTasksSteps.goToUnionTasks;

@Listeners({ScreenShotOnFailListener.class, TextReport.class})
@Report
public class CreateTaskMobileTest extends TasksMobile {

    private InternalStepsMobile internalPageStepsMobile;
    private LoginStepsSteps loginPageSteps;
    private InternalSteps internalPageSteps;
    private UnionTasksSteps unionTasksSteps;
    private TaskTypesEditSteps formEditTaskTypes;
    private UsersSteps userPageSteps;
    private NewRecordDirectoriesSteps newRecordDirectoriesSteps;
    private GridTemplateOfTaskElements gridTemplateOfTaskElements;
    private TemplateOfTaskSteps templateOfTaskSteps;
    private TaskStepsMobile taskStepsMobile;
    private LoginStepsMobile loginStepsMobile;
    private NewTaskStepsMobile newTaskStepsMobile;
    private GridOfFoldersSteps gridOfFoldersSteps;
    private TaskActionsStepsMobile taskActionsStepsMobile;
    private EditOfTaskMobile editOfTaskMobile;
    private CloseTaskStepsMobile closeTaskStepsMobile;


    @BeforeClass
    public void beforeTest() {
        internalPageStepsMobile = page(InternalStepsMobile.class);
        taskStepsMobile = page(TaskStepsMobile.class);
        loginStepsMobile = page(LoginStepsMobile.class);
        newTaskStepsMobile = page(NewTaskStepsMobile.class);
        gridOfFoldersSteps = page(GridOfFoldersSteps.class);
        taskActionsStepsMobile = page(TaskActionsStepsMobile.class);
        editOfTaskMobile = page(EditOfTaskMobile.class);
        closeTaskStepsMobile = page(CloseTaskStepsMobile.class);
        loginPageSteps = page(LoginStepsSteps.class);
        internalPageSteps = page(InternalSteps.class);
        unionTasksSteps = page(UnionTasksSteps.class);
        formEditTaskTypes = page(TaskTypesEditSteps.class);
        userPageSteps = page(UsersSteps.class);
        newRecordDirectoriesSteps = page(NewRecordDirectoriesSteps.class);
        gridTemplateOfTaskElements = page(GridTemplateOfTaskElements.class);
        templateOfTaskSteps = page(TemplateOfTaskSteps.class);
    }


    /**
     * Подготовка данных в web-интерфейсе
     */
    @Test(dataProvider = "objectDataTaskNewType", dataProviderClass = TasksMobile.class)
    public void preconditionInWeb(Department departments, Employee[] author, Employee[] executiveManagers, Employee[] controller, Employee[] worker, Directories registerCardDirectories, Directories directories, TasksTypes tasksTypes, Folder[] folder, TemplateOfTask[] templateOfTasks) {
        loginPageSteps.loginAs(ADMIN);
        //---------------------------------------------------------------- Задачи/Задачи - Создание Папок
        if (folder != null) {
            goToUnionTasks();
            unionTasksSteps.beforeAddFolder(20);
            // Добавляем Папки(/у)
            unionTasksSteps.addFolders(new Folder[]{folder[0].setNameFolder("wD_Smart_Box " + randomString(4))
                    .setUseFilter(true).setFilterField("Начало").setChooseRelativeValue(true)
                    .setSharedFolder(false).setAddSharedFolderForAll(false).setAddSharedFolderForNewUsers(false)});
        }
        //------------------------------------------------------------------Подразделения и Пользователи
        if (departments != null & author != null || executiveManagers != null || controller != null || worker != null) {
            goToURLDepartments().createDepartment(departments);
            if (departments != null & author != null) {
                userPageSteps
                        // Авторы задачи
                        .createUser(author[0].setDepartment(departments))
                        .createUser(author[1].setDepartment(departments));
            }
            if (departments != null & executiveManagers != null) {
                userPageSteps
                        // Ответственные руководители задачи
                        .createUser(executiveManagers[0].setDepartment(departments))
                        .createUser(executiveManagers[1].setDepartment(departments));
            }
            if (departments != null & controller != null) {
                userPageSteps
                        // Контролеры задачи
                        .createUser(controller[0].setDepartment(departments))
                        .createUser(controller[1].setDepartment(departments));
            }
            if (departments != null & worker != null) {
                userPageSteps
                        // Исполнители задачи
                        .createUser(worker[0].setDepartment(departments))
                        .createUser(worker[1].setDepartment(departments));
            }
        }
        //------------------------------------------------- Администрирование/Справочники
        if (registerCardDirectories != null) {
            goToURLDirectories().addObjectTaskTypeList(registerCardDirectories.getObjectTypeName());  // Добавляем объект - Справочник
            DirectoriesEditSteps directoriesEditSteps = new DirectoriesEditSteps().directoriesEditPage();
            assertTrue("check display the form directories editing", directoriesEditSteps.loadedDirectoriesEditPage());
            // Добавляем поля объекта
            directoriesEditSteps.addAllFieldsTaskTypeList(registerCardDirectories).saveChangesOnObject(registerCardDirectories);
        }
        //------------------------------------------------- Справочники
        if (directories != null) {
        //Добавление записей в справочник для последующей проверки выбора из списка в АРМе
            newRecordDirectoriesSteps.goTo(directories);
        }
        //----------------- Создание типа задачи с набором пользовательских полей (новый тип задачи)
        // ---------------- ТИПЫ ЗАДАЧ
        if (tasksTypes != null) {
            goToURLTaskTypes().addObjectTaskTypeList(tasksTypes.getObjectTypeName());

            formEditTaskTypes.addAllFieldsTaskTypeList(tasksTypes)
                    .saveChangesOnObject(tasksTypes);
        }
        // ---------------- Шаблоны ЗАДАЧ
        if (templateOfTasks != null) {
            openSectionOnURL(TASK_TEMPLATES.getMenuURL());
            gridTemplateOfTaskElements.getAddTemplateButton().waitUntil(Condition.visible, 5000);
            //Добавляем шаблон
            templateOfTaskSteps.createTemplateOfTask(templateOfTasks);
        }
        // Выход
        internalPageSteps.logout();
        clearBrowserCache(); //чистим кеш, т.к после логаута в вебе пользователь все равно остается залогинен (баг после работы в user/tab/user/uniontasks/)
    }


    @Test(priority = 1, dataProvider = "objectDataEditTask", dataProviderClass = TasksMobile.class, dependsOnMethods = "preconditionInWeb")
    public void verifyCreateAndEditingTask(Task task, Task editTask, Folder[] folder) throws Exception {
        loginStepsMobile
                .loginAs(ADMIN) // Авторизация
                .waitLoadMainPage(); // Ожидание открытия главной страницы
        internalPageStepsMobile.goToInternalMenu(); // Открываем главное меню
        assertThat("Check that the displayed menu item 9 (User Info; Tasks And Documents; Create Tasks; Today; Search; Settings; Help; Exit; Go To Full Version)",
                internalPageStepsMobile.hasMenuUserComplete());


        //----------------------------------------------------------------ФОРМА - создания Задачи
        newTaskStepsMobile.goToCreateOfNewTask().creatingTask(task);
        taskStepsMobile.fieldsWhenGroupsClosed(); // Проверка отбражения полей при закрытых группах полей
        taskStepsMobile.fieldsWhenGroupsOpen(task);// Проверка отбражения полей при открытых группах полей
        taskStepsMobile.verifyValueWhenGroupsClosed(task); //проверка введенных значений в полях при закрытых группах полей
        taskStepsMobile.verifyValueWhenGroupsOpen(task); //проверка введенных значений в полях при открытых группах полей
        newTaskStepsMobile.saveTask()
                .goToNewTaskViaToast(); //Сохраняем задачу и переходим в созданную задачу через вспылвающие уведомление - Toast

        //----------------------------------------------------------------ФОРМА - Задача
        // Проверяем отображение значений в форме созданой задачи
        taskStepsMobile.verifyCreatedTask(task);

        //Добавление действия и проверяем его сохранение
        taskActionsStepsMobile.postAction(editTask.getActions());

        // редактируем атрибуты задачи
        editOfTaskMobile.editOfTask(task, editTask);

        // Переходим на корневую страницу папок
        internalPageStepsMobile.goToHome();
        refresh();
        //----------------------------------------------------------------ГРИД - Папка
        sleep(500); //ожидание папок;
        // Проверяем отображение созданной задачи в гриде папки
        gridOfFoldersSteps.checkDisplayItemInGrid(editTask.getTaskName(), folder[0]);
        //Переход в задачу из грида
        gridOfFoldersSteps.openItemInGrid(editTask.getTaskName(), folder[0]);

        //Проверка всех отредактированных полей после перезагрузки страницы
        taskStepsMobile.verifyCreatedTask(editTask);
        //Проверка добавленных действий после перезагрузки страницы
        taskActionsStepsMobile.checkAddedActions(editTask.getActions());

        //Завершаем задачу
        closeTaskStepsMobile.closeTask();
        //Проверяем исчезновение задачи в гриде папки
        gridOfFoldersSteps.checkDisappearItemInGrid(editTask.getTaskName(), folder[0]);
        //todo переход в завершенную задачу по id, проверка добавленного комментрия при завершении задачи
        // Выход из системы
        internalPageStepsMobile.logout();
    }

    @Test(priority = 2, dataProvider = "objectDataEditTaskNewType", dataProviderClass = TasksMobile.class, dependsOnMethods = "preconditionInWeb")
    public void verifyCreateAndEditingTaskWithCustomFieldsMobile(Task task, Task editTask, Folder[] folder) throws Exception {
        loginStepsMobile
                .loginAs(ADMIN) // Авторизация
                .waitLoadMainPage(); // Ожидание открытия главной страницы
        internalPageStepsMobile.goToInternalMenu(); // Открываем главное меню
        assertThat("Check that the displayed menu item 9 (User Info; Tasks And Documents; Create Tasks; Today; Search; Settings; Help; Exit; Go To Full Version)",
                internalPageStepsMobile.hasMenuUserComplete());


        //----------------------------------------------------------------ФОРМА - создания Задачи
        newTaskStepsMobile.goToCreateOfNewTask().creatingTask(task);
        taskStepsMobile.fieldsWhenGroupsClosed(); //проверка наличия полей при закрытых группах полей
        taskStepsMobile.fieldsWhenGroupsOpen(task);//проверка наличия полей при открытых группах полей
        taskStepsMobile.verifyValueWhenGroupsClosed(task); //проверка введенных значений в полях при закрытых группах полей
        taskStepsMobile.verifyValueWhenGroupsOpen(task); //проверка введенных значений в полях при открытых группах полей

        newTaskStepsMobile.saveTask()
                .goToNewTaskViaToast(); //Сохраняем задачу и переходим в созданную задачу через всплывающие уведомление - Toast

        //----------------------------------------------------------------ФОРМА - Задача
        // Проверяем отображение значений в форме созданой задачи
        taskStepsMobile.verifyCreatedTask(task);

        //Добавление действия и проверяем его сохранение
        taskActionsStepsMobile.postAction(editTask.getActions());
        // редактируем атрибуты задачи
        editOfTaskMobile.editOfTask(task, editTask);

        // Переходим на корневую страницу папок
        internalPageStepsMobile.goToHome();
        refresh();
        //----------------------------------------------------------------ГРИД - Папка
        sleep(500); //ожидание папок;
        // Проверяем отображение созданной задачи в гриде папки
        gridOfFoldersSteps.checkDisplayItemInGrid(editTask.getTaskName(), folder[0]);
        //Переход в задачу из грида
        gridOfFoldersSteps.openItemInGrid(editTask.getTaskName(), folder[0]);

        //Проверка всех отредактированных полей после перезагрузки страницы
        taskStepsMobile.verifyCreatedTask(editTask);
        //Проверка добавленных действий после перезагрузки страницы
        taskActionsStepsMobile.checkAddedActions(editTask.getActions());

        //Завершаем задачу
        closeTaskStepsMobile.closeTask();
        //Проверяем исчезновение задачи в гриде папки
        gridOfFoldersSteps.checkDisappearItemInGrid(editTask.getTaskName(), folder[0]);
        //todo переход в завершенную задачу по id, проверка добавленного комментрия при завершении задачи
        // Выход из системы
        internalPageStepsMobile.logout();
    }


    //@Test(priority = 11, dataProvider = "objectDataEditTaskNewType", dataProviderClass = TasksMobile.class)
    public void verifyCreateAndEditingTaskWithUserFieldsMobile1(Task task, Task editTask, Folder[] folder) throws Exception {
        loginStepsMobile
                .loginAs(ADMIN) // Авторизация
                .waitLoadMainPage(); // Ожидание открытия главной страницы
        //----------------------------------------------------------------ГРИД - Папка
        sleep(500); //ожидание папок;
        // Проверяем отображение созданной задачи в гриде папки
        gridOfFoldersSteps.checkDisplayItemInGrid(editTask.getTaskName(), folder[0]);

//        newTaskStepsMobile.goToCreateOfNewTask();
//        sleep(1000);
//        open("/m/#task/210");
//
//        sleep(500);
//
//        taskStepsMobile.openTab(DESCRIPTION_TAB);
//        newTaskStepsMobile.selectGroupTab(TYPE_TASK);
//
//        DirectoriesField[] fields = new DirectoriesField[]{
//                // Осуществляем заполнение (наполнение) полей справочника через массив
//                (DirectoriesField) new DirectoriesField()
//                        // Осуществляем заполнение (наполнение) полей справочника через массив
//                        .setDirectoryItems("рУёпOъLъsдb"),
//                (DirectoriesField) new DirectoriesField()
//                        // Осуществляем заполнение (наполнение) полей справочника через массив
//                        .setDirectoryItems("рУёпOъLъsдb1"),
//        };
//
//        taskStepsMobile.verifyValueInInput("Множественная ссылка на справочник ЩtД2я", fields);

    }


}
