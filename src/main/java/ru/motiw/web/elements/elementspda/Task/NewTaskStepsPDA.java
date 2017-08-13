package ru.motiw.web.elements.elementspda.Task;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.motiw.web.model.Tasks.Task;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.steps.BaseSteps;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/*
 Страница - Создать задачу
 */
public class NewTaskStepsPDA extends BaseSteps {

    /*
     Название задачи
     */
    @FindBy(xpath = "//input[@id='input_prj_t' and @name='task_name']")
    private SelenideElement tasksName;

    /*
     Описание задачи
     */
    @FindBy(xpath = "//textarea[@id='task_description']")
    private SelenideElement tasksDescription;

    /*
     Окончание задачи
     */
    @FindBy(xpath = "//input[@id='mes_date_end']")
    private SelenideElement dateEnd;

    /*
     Авторы
     */
    @FindBy(xpath = "//input[@id='ag']")
    private SelenideElement inputFieldAuthors;

    /*
     Контролеры задачи
     */
    @FindBy(xpath = "//input[@id='cg']")
    private SelenideElement inputFieldTaskSupervisors;

    /*
     Ответственные руководители
     */
    @FindBy(xpath = "//input[@id='rg']")
    private SelenideElement inputFieldExecutiveManagers;

    /*
     Исполнители
     */
    @FindBy(xpath = "//input[@id='wg']")
    private SelenideElement inputFieldPerformers;

    /*
     Секретная задача
     */
    @FindBy(xpath = "//div[@class='ui-checkbox']//input[@id='secret']/..//span[2]")
    protected SelenideElement privateTask;

    /*
     C докладом
     */
    @FindBy(xpath = "//div[@class='ui-checkbox']//input[@id='withreport2']/..//span[2]")
    protected SelenideElement reportRequired;

    /*
     Важная задача
     */
    @FindBy(xpath = "//div[@class='ui-checkbox']//input[@id='taskhigh']/..//span[2]")
    protected SelenideElement importantTask;

    /*
     Просмотр
     */
    @FindBy(css = "input[name='next2']")
    private SelenideElement view;

    /*
     Постановщик задачи
     */
    @FindBy(xpath = "//a [ancestor::span[@name='autor']]")
    private SelenideElement directorOfTheTask;

    /**
     * Название задачи
     *
     * @param nameTasks name task for input
     * @return page NewTaskPag
     */
    public NewTaskStepsPDA setTaskName(String nameTasks) {
        tasksName.clear();
        tasksName.setValue(nameTasks);
        return this;
    }

    /**
     * Описание задачи
     *
     * @param descriptionTasks description task for input
     * @return page NewTaskPag
     */
    public NewTaskStepsPDA setTasksDescription(String descriptionTasks) {
        tasksDescription.clear();
        tasksDescription.setValue(descriptionTasks);
        return this;
    }

    /**
     * Окончание задачи
     *
     * @param end date end for task
     * @return
     */
    public NewTaskStepsPDA setDateEnd(String end) {
        dateEnd.clear();
        dateEnd.setValue(end);
        return this;
    }


    /**
     * Добавление пользователей в роль задачи, через livesearch - Поиск по фамилии
     *
     * @param employees       передаваемые пользователи
     * @param fieldCustomRole выбираемая роль в задаче (Исполнители, Авторы и ОР)
     */
    protected void choiceUsersThroughTheSearchLiveSurname(Employee[] employees, SelenideElement fieldCustomRole) {
        if (employees != null) {
            for (Employee employee : employees) {
                $(fieldCustomRole).shouldNotBe(Condition.disabled);
                fieldCustomRole.clear();
                fieldCustomRole.setValue(employee.getLastName());
                // TODO баг - если пользователь НЕ admin, в данном случае нужно добавить пред условие - считывание Постановщик или прекондишен - установить соот. ФИО в реквизитах пользователя
                $(By.xpath("//ul[contains(@style,' display: block')]//a[contains(text(),'" + employee.getLastName() + "')]"))
                        .shouldBe(Condition.visible);
                $(By.xpath("//ul[contains(@style,' display: block')]//a[contains(text(),'" + employee.getLastName() + "')]")).click();
            }
        }
    }

    /**
     * Выбор булевой настройки в форме задачи
     * <p>
     * Например, признак "Важная задача", "Секретная задача", "С докладом"
     *
     * @param stateOfCheckbox состояние установленной настройки
     * @param inputChekbox    совершить действие (снять/установить настройку)
     */
    protected void rangeOfValuesF​romTheCheckbox(boolean stateOfCheckbox, SelenideElement inputChekbox) {
        if (stateOfCheckbox) {
            inputChekbox.click();
        }
    }

    /**
     * Просмотр (предсоздание задачи)
     *
     * @return
     */
    public TaskDescriptionStepsPDA goToPreview() {
        view.click();
        $(By.cssSelector("input[name='next3']")).waitUntil(Condition.appear, 4);
        return page(TaskDescriptionStepsPDA.class);
    }

    /**
     * Создание новой задачи
     */
    public void creatingTask(Task task) {
        setTaskName(task.getTaskName()) // Название задачи
                .setTasksDescription(task.getDescription()); // Описание задачи
        choiceUsersThroughTheSearchLiveSurname(task.getControllers(), inputFieldTaskSupervisors); // вводим - Контролеры задачи
        choiceUsersThroughTheSearchLiveSurname(task.getExecutiveManagers(), inputFieldExecutiveManagers); // вводим - Ответственные руковдители
        choiceUsersThroughTheSearchLiveSurname(task.getWorkers(), inputFieldPerformers); // вводим - Исполнители
        setDateEnd(task.getDateEnd()); // Дата окончания задачи
        rangeOfValuesF​romTheCheckbox(task.getIsImportant(), importantTask); // признак - Важная задача
        rangeOfValuesF​romTheCheckbox(task.getIsWithReport(), reportRequired); // признак - С доклаом
        rangeOfValuesF​romTheCheckbox(task.getIsSecret(), privateTask); // признак - Секретная задача
    }

}
