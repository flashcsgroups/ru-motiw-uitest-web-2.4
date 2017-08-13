package ru.motiw.testsWeb;

import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.motiw.data.dataproviders.Options;
import ru.motiw.data.listeners.ScreenShotOnFailListener;
import ru.motiw.web.model.Administration.TasksTypes.TasksTypes;
import ru.motiw.web.model.Administration.Users.Department;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Tasks.Event;
import ru.motiw.web.model.Tasks.Task;
import ru.motiw.web.steps.Administration.Users.UsersSteps;
import ru.motiw.web.steps.Home.InternalSteps;
import ru.motiw.web.steps.Login.LoginStepsSteps;
import ru.motiw.web.steps.Tasks.TaskForm.InsetEventsTaskFormSteps;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.page;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.motiw.web.steps.Administration.Users.DepartmentSteps.goToURLDepartments;
import static ru.motiw.web.steps.Options.EventTemplatesSteps.openSectionOnURLEventTemplates;
import static ru.motiw.web.steps.Tasks.TaskForm.UnionMessageNewSteps.goToURLUnionMessageNew;


@Listeners({ScreenShotOnFailListener.class, TextReport.class})
@Report
public class EventTemplatesTest extends Options {

    private LoginStepsSteps loginPageSteps;
    private InternalSteps internalPageSteps;
    private UsersSteps userPageSteps;
    private InsetEventsTaskFormSteps eventsTabInTheFormOfTask;

    @BeforeClass
    public void beforeTest() {
        loginPageSteps = page(LoginStepsSteps.class);
        internalPageSteps = page(InternalSteps.class);
        userPageSteps = page(UsersSteps.class);
        eventsTabInTheFormOfTask = page(InsetEventsTaskFormSteps.class);
    }

    // Инициализация объекта Задача
    Task task = new Task().setTaskName(randomString(10) + " " + randomString(10)
            + "_" + randomInt(5))
            .setTaskType(new TasksTypes("Обычный"));

    @Test(priority = 1, dataProvider = "objectDataEventTemplates", dataProviderClass = Options.class)
    public void createEventTemplates(ArrayList<Department> departments, ArrayList<Employee> users, Event event) throws Exception {
        // Авторизация
        loginPageSteps.loginAs(ADMIN);
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPageSteps.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице

        // Создаем подразделения для пользователей
        goToURLDepartments().createDepartment(departments.get(0));

        // Пользователи
        userPageSteps.createUser(users.get(0).setDepartment(departments.get(0)));
        // Переход в раздел Настройки / Шаблоны событий
        openSectionOnURLEventTemplates().createEventTemplates(event);

        // Проверяем созданный объект - Шаблон задачи в форма Задачи
        goToURLUnionMessageNew().creatingTask(task);

        eventsTabInTheFormOfTask.beforeAddEvents().addEventToTheTemplateForTheTask(event);


    }


}
