package ru.motiw.mobile.steps.Tasks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import ru.motiw.mobile.elements.Internal.InternalElementsMobile;
import ru.motiw.mobile.elements.Tasks.NewTaskFormElementsMobile;
import ru.motiw.mobile.steps.InternalStepsMobile;
import ru.motiw.mobile.steps.LoginStepsMobile;
import ru.motiw.web.elements.elementspda.Task.TaskDescriptionStepsPDA;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Tasks.Task;
import ru.motiw.web.steps.BaseSteps;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static ru.motiw.mobile.model.URLMenuMobile.CREATE_TASK;
import static ru.motiw.mobile.steps.BaseStepsMobile.openSectionOnURLMobile;

/*
 Страница - Создать задачу
 */



public class NewTaskStepsMobile extends BaseSteps {

    NewTaskFormElementsMobile newTaskFormElementsMobile = page(NewTaskFormElementsMobile.class);
    private InternalElementsMobile internalElementsMobile = page(InternalElementsMobile.class);
    private LoginStepsMobile loginStepsMobile = page(LoginStepsMobile.class);


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





    //TODO Все что выше под удаление
    //////////////////////


    /**
     * Переход в Задачи/Создать задачу напрямую по ссылке TODO cделать переход по ссылке в меню?
     *
     * @return UnionMessageNewSteps вощвращаем стр. для дальнейшего взаимодействия
     * с елементами на странице
     */
    public static NewTaskStepsMobile goToURLNewTask() {
        openSectionOnURLMobile(CREATE_TASK.getMenuURLMobile());
        return page(NewTaskStepsMobile.class);
    }


    public  NewTaskStepsMobile goToCreateOfNewTask() {
        //Если Меню не открыто - открываем его перед тем, как перейти в форму создания задачи
        if ($(internalElementsMobile.getCreateTaskMobile()).is(Condition.disappear)) {
            loginStepsMobile.goToInternalMenu();
            internalElementsMobile.getCreateTaskMobile().click();
        } else {
            internalElementsMobile.getCreateTaskMobile().click();
        }
        return page(NewTaskStepsMobile.class);
    }


    /**
     * Проверка Загрузки страницы - форма Создания задачи - проверка отображения и кол-ва элементов (все блоки компонетов - поля задачи) в форме задачи
     */
    private NewTaskStepsMobile ensurePageLoaded() {
        newTaskFormElementsMobile.getCollectionNewTaskformElements().shouldHaveSize(9);
        return this;
    }



    /**
     * Название задачи
     *
     * @param nameTasks name task for input
     * @return page NewTaskPag
     */
    private NewTaskStepsMobile setTaskName(String nameTasks) {
        newTaskFormElementsMobile.getTaskName().click();
        newTaskFormElementsMobile.getTaskName().setValue(nameTasks);
        return this;
    }

    /**
     * Описание задачи
     *
     * @param descriptionTasks description task for input
     * @return page NewTaskPag
     */
    private NewTaskStepsMobile setTasksDescription(String descriptionTasks) {
        //сейчас пишет, что return не используется нигде
            if (descriptionTasks == null) {
                return this;
            } else {
                newTaskFormElementsMobile.getDescriptionTask().click();
                newTaskFormElementsMobile.getDescriptionTask().setValue(descriptionTasks);
            }
        return this;
    }

    /**
     * Ввод даты начала
     */
    private NewTaskStepsMobile setDateBegin(String begin) {
        if (begin == null) {
            return this;
        } else {
            newTaskFormElementsMobile.getBeginField().click();
            newTaskFormElementsMobile.getBeginField().setValue(begin);
        }
        return this;
    }

    /**
     * Ввод даты окончания задачи
     */
    private NewTaskStepsMobile setDateEnd(String end) {
        if (end == null) {
            return this;
        } else {
            newTaskFormElementsMobile.getEndField().click();
            newTaskFormElementsMobile.getEndField().setValue(end);

        }
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


    private boolean verifyThatCheckboxIsNotSelected (boolean q, SelenideElement element) {
       if (q) {
            element.shouldBe(selected);
            return true; //если  надо высталять чекбокс, а он уже выставлен, то возвращаем true - т.к чекбокс уже выствален.
       } else {
            element.shouldNotBe(selected);
            return false;
       }

    }







    /**
     * Выбор булевой настройки в форме задачи
     * <p>
     * Например, признак "Важная задача", "Секретная задача", "С докладом"
     *
     * @param stateOfCheckbox состояние установленной настройки
     * @param inputCheckbox    совершить действие (снять/установить настройку)
     */

    private void rangeOfValuesFromTheCheckbox(boolean stateOfCheckbox, SelenideElement inputCheckbox) {
        if (stateOfCheckbox){
            inputCheckbox.click();
        }
    }

    //TODO не получается сделать методы выбор чекбоксов в зависимости того, выбран чекбокс по умолчанию или нет.
    private void rangeOfValuesFromTheCheckbox2(boolean stateOfCheckbox, SelenideElement inputCheckbox) {
        if (stateOfCheckbox && !verifyThatCheckboxIsNotSelected(stateOfCheckbox, inputCheckbox))
            // выставляем чекбокс только в случае если  в передаваемом параметре true, а чекбокс в интерфейсе ещё не selected (напрмер, в случае если он не устанавливается по умолчанию)
        {
            inputCheckbox.click();
        }
    }





    /**
     * Установка признака важности
     */
    private NewTaskStepsMobile setImportance(boolean isImportant) {
        newTaskFormElementsMobile.getPriority().click();

        if (isImportant) {
            newTaskFormElementsMobile.getImportantTask().click();
        } else {
            newTaskFormElementsMobile.getSimpleTask().click();
        }
        return this;
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
     * Создание новой задачи - КОПИЯ МЕТОДА ДЛЯ PDA
     */
    public void creatingTask2(Task task) {
        setTaskName(task.getTaskName()) // Название задачи
                .setTasksDescription(task.getDescription()); // Описание задачи
        choiceUsersThroughTheSearchLiveSurname(task.getControllers(), inputFieldTaskSupervisors); // вводим - Контролеры задачи
        choiceUsersThroughTheSearchLiveSurname(task.getExecutiveManagers(), inputFieldExecutiveManagers); // вводим - Ответственные руковдители
        choiceUsersThroughTheSearchLiveSurname(task.getWorkers(), inputFieldPerformers); // вводим - Исполнители
        rangeOfValuesFromTheCheckbox(task.getIsImportant(), importantTask); // признак - Важная задача
        rangeOfValuesFromTheCheckbox(task.getIsWithReport(), reportRequired); // признак - С доклаом
        rangeOfValuesFromTheCheckbox(task.getIsSecret(), privateTask); // признак - Секретная задача
    }


    /**
     * Создание обычной задачи КОПИЯ МЕТОДА ДЛЯ WEB
     *
     * @param task передаваемые атрибуты задачи
     */
    public NewTaskStepsMobile creatingTask(Task task) {
       ensurePageLoaded();

       // Разворачиваем  группу полей  "Название"

        $(By.xpath("//div[contains(text(),'Название')]//ancestor::div[contains(@class,\"x-unselectable x-paneltitle x-component\")]")).click();
        //TODO Проверка на то, что вкладка открылась и все поля отображаются

       //Заполняем Название задачи
        setTaskName(task.getTaskName())
                .setTasksDescription(task.getDescription());

        // Закрываем  группу полей  "Название"

        $(By.xpath("//div[contains(text(),'Название')]//ancestor::div[contains(@class,\"x-unselectable x-paneltitle x-component\")]")).click();
        //TODO Проверка на то, что вкладка закрылась и все поля не отображаются


        // Открываем  группу полей  "Срок"
        $(By.xpath("//div[contains(text(),'Срок')]//ancestor::div[contains(@class,\"x-unselectable x-paneltitle x-component\")]")).click();
        //TODO Проверка на то, что вкладка открылась и все поля отображаются


        //Заполняем Даты

        setDateBegin(task.getDateBegin())
                .setDateEnd(task.getDateEnd());

        //rangeOfValuesF​romTheCheckbox(task.getIsImportant(), importantTask); // признак - Важная задача

        setImportance(task.getIsImportant());

        // Закрываем  группу полей  "Срок"
        $(By.xpath("//div[contains(text(),'Срок')]//ancestor::div[contains(@class,\"x-unselectable x-paneltitle x-component\")]")).click();
        //TODO Проверка на то, что вкладка закрылась и все поля не отображаются

        // Открываем группу полей "Ещё"

        $(By.xpath("//div[contains(text(),'Еще')]//ancestor::div[contains(@class,\"x-unselectable x-paneltitle x-component\")]")).click();
        //TODO Проверка на то, что вкладка открылась и все поля отображаются

        newTaskFormElementsMobile.getReportRequired().shouldBe(selected); // Признак - С Докладом всегда по умолчанию должен быть выбран.
        //rangeOfValuesFromTheCheckbox(task.getIsSecret(), newTaskFormElementsMobile.getIsSecret()); // признак - Секретная  -- не находит  Element should be visible {By.xpath: //input[@name="issecret"]}
        //newTaskFormElementsMobile.getIsForExamination().shouldBe(disabled); // Признак - "Для ознакомления" -  в метод  rangeOfValuesFromTheCheckbox нужно видмо , м.б все чекбоксы надо стараться выствлять?







       /*
       setEnd(task.getDateEnd());
        createProject(task.getProject());
        setTaskName(task.getTaskName())
                .setTaskDescription(task.getDescription())
                .setDataBegin(task.getDateBegin())
                .setImportance(task.getIsImportant());
        // выбор пользователя по ФИО - Авторы - через searchlive
        choiceUsersThroughTheSearchLiveSurname(task.getAuthors(), insetDescriptionTaskFormElements.getAuthorsField(),
                insetDescriptionTaskFormElements.getEditorField());
        // выбор пользователя - Контролер - через searchlive
        choiceUsersThroughTheSearchLiveForSpace(task.getControllers(), insetDescriptionTaskFormElements.getСontrollersField(),
                insetDescriptionTaskFormElements.getEditorField());
        // выбор пользователя - Исполнителей - через searchlive
        choiceUsersThroughTheSearchLiveForSpace(task.getWorkers(), insetDescriptionTaskFormElements.getWorkersField(),
                insetDescriptionTaskFormElements.getEditorField());
        // выбор пользователя - Ответственные руководители - через searchlive
        choiceUsersThroughTheSearchLiveForSpace(task.getExecutiveManagers(), insetDescriptionTaskFormElements.getExecutiveManagersField(),
                insetDescriptionTaskFormElements.getEditorField());
        setTaskType(task.getTaskType()) // выбор - Тип задачи
                .setReport(task.getIsWithReport())
                .setSecret(task.getIsSecret())
                .setReview(task.getIsForReview());*/

        return this;

    }


    /**
     * Сохранить задачу
     *
     * @return UnionMessageSteps страница UnionMessageSteps (Задачи / Задачи) - убрал т.к пишет, что ничего не возвращает. Зачем это было в ориг. методе для web?
     */
    public void saveTask() {
        newTaskFormElementsMobile.getButtonCreateTask().click();

        /*clickSaveTask()
                .assertWindowTaskCreated();*/
        ;
    }

}
