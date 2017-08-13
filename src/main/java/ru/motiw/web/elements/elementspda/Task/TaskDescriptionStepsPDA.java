package ru.motiw.web.elements.elementspda.Task;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Tasks.Task;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/*
 * Форма задачи (Атрибуты задачи (Описание))
 */
public class TaskDescriptionStepsPDA extends NewTaskStepsPDA {

    /*
     * кнопка Создать задачу
     */
    @FindBy(css = "input[name='next3']")
    private SelenideElement createTask;

    /*
     * Ссылка - Перейти к задаче
     */
    @FindBy(xpath = "//a[contains(@href, '/task/')]")
    private SelenideElement goToTask;

    /*
     * Сохранить
     */
    @FindBy(css = "div.save_button")
    private SelenideElement save;

    /*
     * Ссылка на задачу в форме редактирования задачи
     */
    @FindBy(xpath = "(//div[@class='menu-line']//a/li)[2]")
    private SelenideElement linkTaskReturnMainForm;


    /**
     * Проверка введенных данный в предпросмотре формы создания задачи
     *
     * @param task return values of attributes of the task
     * @return TaskDescriptionStepsPDA page
     */
    public TaskDescriptionStepsPDA inputValidationFormTask(Task task) {
        $(By.xpath("//form[@id='data_value']//li[2]//span[@style][text()='" + task.getTaskName() + "']"))
                .shouldBe(visible); // Название задачи
        $(By.xpath("//form[@id='data_value']//li[3]//span[@style][text()='" + task.getDescription() + "']"))
                .shouldBe(visible); // Описание задачи
        $(By.xpath("//form[@id='data_value']//li[9]//span[@style][contains(text(),'" + task.getDateEnd() + "')]"))
                .shouldBe(visible); // Окончание задачи
        saveNewTask();
        return this;
    }

    /**
     * Создать (нажатие кнопки - Создать)
     *
     * @return TaskDescriptionStepsPDA
     */
    public TaskDescriptionStepsPDA saveNewTask() {
        createTask.click();
        $(By.xpath("//a[contains(@href, '/task/')]")).waitUntil(Condition.appear, 4);
        return this;
    }

    /**
     * Перейти к форме задачи (Лента действий)
     *
     * @return TaskActionsStepsPDA
     */
    public TaskActionsStepsPDA goToTask() {
        goToTask.click();
        return page(TaskActionsStepsPDA.class);
    }

    /**
     * Сохранить изменения по задаче
     *
     * @return TaskDescriptionStepsPDA
     */
    public TaskDescriptionStepsPDA saveChangesToTask() {
        save.click();
        return this;
    }

    /**
     * Редактирование атрибутов задачи
     *
     * @return TaskDescriptionStepsPDA
     */
    public TaskDescriptionStepsPDA editAttributesOfTasks(Task editTask) {
        setTaskName(editTask.getTaskName()) // Название задачи
                .setTasksDescription(editTask.getDescription()) // Описание задачи
                .setDateEnd(editTask.getDateEnd()); // Дата окончания задачи
        rangeOfValuesF​romTheCheckbox(editTask.getIsImportant(), importantTask); // признак - Важная задача
        // TODO необходимы предусловия, признака установки Секретная задача - т.е. может быть скрыт по умолчанию
        rangeOfValuesF​romTheCheckbox(editTask.getIsSecret(), privateTask); // признак - Секретная задача
        saveChangesToTask();
        sleep(1500);
        checkTheAttributesAreSaved(editTask); // проверяем отображение изменений (системное действие) в ленте действий
        verifyAttributesOfTask(editTask); // проверяем отображение новых значений в полях задачи
        return this;
    }

    /**
     * Проверяем отображение новых значений в полях задачи
     *
     * @return TaskDescriptionStepsPDA
     */
    public TaskDescriptionStepsPDA verifyAttributesOfTask(Task editTask) {
        goToTask.click();
        $(By.xpath("//input[@id='input_prj_t' and @name='task_name']"))
                .waitUntil(value(" " + editTask.getTaskName() + " "), 10000); // Название задачи
        $(By.xpath("//textarea[@id='task_description']"))
                .shouldHave(value(" " + editTask.getDescription() + " ")); // Описание задачи
        return this;
    }

    /**
     * Редактирование РГ (рабочая группа) задачи
     *
     * @param employee return values user details
     * @return TaskDescriptionStepsPDA
     */
    public TaskDescriptionStepsPDA editWorkingGroupInTask(Employee employee) {
        goToTask.click();
        // Удаляем - Контролеры задачи
        $(By.xpath("//input[@id='input_prj_t' and contains(@name,'cg_') and contains(@value,'" + employee.getLastName() + "')]/../..//a[not(contains(@onclick,'window.open'))]//span[2]")).click();
        $(By.xpath("//input[@id='input_prj_t' and contains(@name,'cg_') and contains(@value,'" + employee.getLastName() + "')]/../..//a[not(contains(@onclick,'window.open'))]//span[2]"))
                .shouldNotBe(visible);
        // Удаляем - Ответственные руководители
        $(By.xpath("//input[@id='input_prj_t' and contains(@name,'rg_') and contains(@value,'" + employee.getLastName() + "')]/../..//a[not(contains(@onclick,'window.open'))]//span[2]")).click();
        $(By.xpath("//input[@id='input_prj_t' and contains(@name,'rg_') and contains(@value,'" + employee.getLastName() + "')]/../..//a[not(contains(@onclick,'window.open'))]//span[2]"))
                .shouldNotBe(visible);
        // Удаляем - Исполнители
        $(By.xpath("//input[@id='input_prj_t' and contains(@name,'wg_') and contains(@value,'" + employee.getLastName() + "')]/../..//a[not(contains(@onclick,'window.open'))]//span[2]")).click();
        $(By.xpath("//input[@id='input_prj_t' and contains(@name,'wg_') and contains(@value,'" + employee.getLastName() + "')]/../..//a[not(contains(@onclick,'window.open'))]//span[2]"))
                .shouldNotBe(visible);
        checkWorkingGroupInTaskAreSaved(employee); // проверяем формирование системных действий об удалении пользователей в ленте действий задачи
        return this;
    }

    /**
     * Проверяем сохраненные изменения в ленте действий задачи
     *
     * @param editTask post in action
     * @return TaskActionsStepsPDA
     */
    public TaskActionsStepsPDA checkTheAttributesAreSaved(Task editTask) {
        linkTaskReturnMainForm.click();
        $(By.xpath("//div[@id='mainblock']//ul[@class='ui-listview']//div//font[text()='" + editTask.getTaskName() + "']"))
                .shouldHave(Condition.exactText(" " + editTask.getTaskName() + " "));
        return page(TaskActionsStepsPDA.class);
    }

    /**
     * Проверяем сохраненные изменения в ленте действий задачи
     *
     * @param employee
     * @return TaskActionsStepsPDA
     */
    public TaskActionsStepsPDA checkWorkingGroupInTaskAreSaved(Employee employee) {
        linkTaskReturnMainForm.click();
        $$(By.xpath("//span[contains(text(),'Из задачи удален') and contains(text(),'" + employee.getLastName() + "')]"))
                .shouldHave(CollectionCondition.size(3));
        return page(TaskActionsStepsPDA.class);
    }


}
