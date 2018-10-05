package ru.motiw.mobile.steps.Tasks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.motiw.mobile.elements.Internal.InternalElementsMobile;
import ru.motiw.mobile.elements.Tasks.NewTaskFormElementsMobile;
import ru.motiw.mobile.steps.LoginStepsMobile;
import ru.motiw.web.elements.elementspda.Task.TaskDescriptionStepsPDA;
import ru.motiw.web.model.Administration.TasksTypes.TasksTypes;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Tasks.Task;
import ru.motiw.web.steps.BaseSteps;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static ru.motiw.mobile.model.URLMenuMobile.CREATE_TASK;
import static ru.motiw.mobile.steps.BaseStepsMobile.openSectionOnURLMobile;

/*
 Страница - Создать задачу
 */



public class NewTaskStepsMobile extends BaseSteps {

    NewTaskFormElementsMobile newTaskFormElementsMobile = page(NewTaskFormElementsMobile.class);
    private InternalElementsMobile internalElementsMobile = page(InternalElementsMobile.class);
    private LoginStepsMobile loginStepsMobile = page(LoginStepsMobile.class);
    //private TaskStepsMobile taskStepsMobile = page(TaskStepsMobile.class); //это потомок


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
    private NewTaskStepsMobile  setTaskName(String nameTasks) {
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
     * Проверка значений в инпутах формы задачи при закрытой группе полей
     * @param valueInInput передаваемое значенние поля
     * @param nameOfElement имя элемента для xpath
     */
    NewTaskStepsMobile verifyValueBeforeOpenGroupFields(String valueInInput, String nameOfElement) {
        if (valueInInput == null) {
            return this;
        }
        $(By.xpath("//div[contains(@id,\"object\")]//input[@name='" + nameOfElement + "']")).shouldHave(exactValue(valueInInput));
        return this;

    }

    /**
     * Проверка установленного по умолчанию Типа задачи - при закрытой группе полей "Тип задачи".
     * @param taskType передаваемое значенние поля Типа задачи
     */

    NewTaskStepsMobile verifyTaskTypeBeforeOpenGroupFields(TasksTypes taskType) {
        String nameOfTaskType = taskType.getObjectTypeName();
        verifyValueBeforeOpenGroupFields(nameOfTaskType, "id_tasktype");
        return this;
    }


    /**
     * Проверка значений в инпутах формы задачи
     * @param valueInInput передаваемое значенние поля
     * @param nameOfElement имя элемента для xpath
     */
    NewTaskStepsMobile verifyValueInInput(String nameOfElement, String valueInInput) {
        if (valueInInput == null) {
            return this;
        }
        //Использую xpath с уникальным названием поля
        // можно ещё использовать //div[contains(@id,\"object\")]//input[@name='" + nameOfElement + "'], но не каждый элемент имеет name инпута
        //$(By.xpath("//span[text()=\"Название\"]/../..//input")).shouldHave(value(valueInInput));
        $(By.xpath("//span[text()='" + nameOfElement + "']/../..//input")).shouldHave(exactValue(valueInInput));
        return this;

    }

    /**
     * Проверка установленного Типа задачи
     * @param taskType передаваемое значенние поля Типа задачи
     */
    void verifyTaskType(TasksTypes taskType) {
        String nameOfTaskType = taskType.getObjectTypeName();
        verifyValueInInput("Тип задачи", nameOfTaskType);
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
     * Установка типа задачи
     * @param taskType передаваемое имя типа задачи
     */
    private NewTaskStepsMobile setTaskType(TasksTypes taskType) {
        if (taskType == null) {
            return this;
        } else {
            $(newTaskFormElementsMobile.getFieldTaskType()).shouldBe(Condition.visible);
        }
        newTaskFormElementsMobile.getFieldTaskType().click();
        $(By.xpath("//div[contains(@class,\"x-list x-dataview x-container x-component x-floated\")]//span[text()='" + taskType.getObjectTypeName() + "']"))
                .click();
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



    NewTaskStepsMobile selectGroupTab(String nameOfGroup){
        $(By.xpath("//div[contains(text(),'" + nameOfGroup + "')]//ancestor::div[contains(@class,\"x-unselectable x-paneltitle x-component\")]")).click();
        return this;
    }


    // Единный метод в котром описаны все поля , которые отображаются/НЕ отображаются после закрытия всех групп полей
    NewTaskStepsMobile fieldsWhenGroupsCloset() {

        //поля, которые отображаются после закрытия всех групп полей

        newTaskFormElementsMobile.getTaskName().shouldBe(visible); // "Название"
        newTaskFormElementsMobile.getDescriptionTask().shouldBe(visible); // Описание задачи - отображение и проверка значения поля после закрытия группы полей  "Название"
        // Кому
        $(By.xpath("(//div[contains(text(),'Кому')]//ancestor::div[contains(@class,\"x-panel x-container x-component small-collapser-panel x-noborder-trbl x-header-position-top x-panel-grey-background x-container-grey-background \")]//div[@class=\"x-input-el\"])[1]")).shouldBe(visible);
        // TODO  xpath другой м.б написать - этот ищет все 4ре поля - хотя для других полей ведь тоже xpath одинаковые для раскрытого и закрытого поля. //span[contains(text(),'Ответственные руководители')]/../..//div[@class="x-input-el\ - не находит

        newTaskFormElementsMobile.getEndField().shouldBe(visible); // Окончание
        $(By.xpath("//input[@name=\"id_tasktype\"]")).shouldBe(visible); // Тип задачи

        if ($(newTaskFormElementsMobile.getButtonCreateTask()).is(Condition.visible)) {
             $(By.xpath("//input[@name=\"id_task_template\"] ")).shouldBe(visible); // Шаблон задачи в созданной задаче отсутствует
         }


        //поля, которые НЕ отображаются после закрытия всех групп полей

        newTaskFormElementsMobile.getTaskNumber().shouldNotBe(visible);  //поле №
        $(By.xpath("//span[text()='Проект']/../..//input")).shouldNotBe(visible);// - проект не должен отображаться ДО раскрытия группы полей


        $(By.xpath("//span[contains(text(),'Авторы')]/../..//div[@class=\"x-input-el\"]")).shouldNotBe(visible);
        $(By.xpath("//span[contains(text(),'Контролеры')]/../..//div[@class=\"x-input-el\"]")).shouldNotBe(visible);
        $(By.xpath("//span[contains(text(),'Ответственные руководители')]/../..//div[@class=\"x-input-el\"]")).shouldNotBe(visible);
        $(By.xpath("//span[contains(text(),'Исполнители')]/../..//div[@class=\"x-input-el\"]")).shouldNotBe(visible);


        newTaskFormElementsMobile.getBeginField().shouldNotBe(visible); // Начало
        newTaskFormElementsMobile.getPriority().shouldNotBe(visible); // Приоритет
        newTaskFormElementsMobile.getFieldFiles().shouldNotBe(visible); //  поле - Файлы


        newTaskFormElementsMobile.getCheckboxReportRequired().shouldNotBe(visible); // Признак - С Докладом
        newTaskFormElementsMobile.getCheckboxIsSecret().shouldNotBe(visible); // признак - Секретная
        newTaskFormElementsMobile.getCheckboxIsForReview().shouldNotBe(visible); // Признак - "Для ознакомления"


        return this;
    }

    // Единный метод в котром описаны все поля , которые отображаются/НЕ отображаются после раскрытия всех групп полей
    // Проверка на то, что вкладки открылись и все поля отображаются

    NewTaskStepsMobile fieldsWhenGroupsOpen(Task task) {

        //Методы для Разворачивания всех групп полей
        selectGroupTab("Название");
        selectGroupTab("Кому");
        selectGroupTab("Срок");
        selectGroupTab("Тип задачи");
        selectGroupTab("Файлы");
        selectGroupTab("Еще");



        //Проверка
        //$(By.xpath("//input[@name=\"id_task_template\"] ")).shouldBe(visible); // Шаблон задачи
        newTaskFormElementsMobile.getTaskName().shouldBe(visible); // "Название"
        newTaskFormElementsMobile.getDescriptionTask().shouldBe(visible); // Описание задачи
        newTaskFormElementsMobile.getTaskNumber().shouldBe(visible);  //поле №
        $(By.xpath("//span[text()='Проект']/../..//input")).shouldBe(visible);// - проект не должен отображаться ДО раскрытия группы полей

        // TODO В ЕД.МЕТОД с подстановкой. xpath подойдет для других полей где div ?
        $(By.xpath("//span[contains(text(),'Авторы')]/../..//div[@class=\"x-input-el\"]")).shouldBe(visible);
        $(By.xpath("//span[contains(text(),'Контролеры')]/../..//div[@class=\"x-input-el\"]")).shouldBe(visible);
        $(By.xpath("//span[contains(text(),'Ответственные руководители')]/../..//div[@class=\"x-input-el\"]")).shouldBe(visible);
        $(By.xpath("//span[contains(text(),'Исполнители')]/../..//div[@class=\"x-input-el\"]")).shouldBe(visible);


        newTaskFormElementsMobile.getBeginField().shouldBe(visible); // Начало
        newTaskFormElementsMobile.getEndField().shouldBe(visible); // Окончание
        newTaskFormElementsMobile.getPriority().shouldBe(visible); // Приоритет
        $(By.xpath("//input[@name=\"id_tasktype\"]")).shouldBe(visible); // Тип задачи
        newTaskFormElementsMobile.getFieldFiles().shouldBe(visible); //  поле - Файлы


        newTaskFormElementsMobile.getCheckboxReportRequired().shouldBe(visible); // Признак - С Докладом
        newTaskFormElementsMobile.getCheckboxIsSecret().shouldBe(visible); // признак - Секретная
        newTaskFormElementsMobile.getCheckboxIsForReview().shouldBe(visible); // Признак - "Для ознакомления"

        if ($(newTaskFormElementsMobile.getButtonCreateTask()).is(Condition.visible)) {
            $(By.xpath("//input[@name=\"id_task_template\"] ")).shouldBe(visible); // Шаблон задачи в созданной задаче отсутствует
        }


        //Все проверки из verifyValueInInput



        verifyTaskType(task.getTaskType()); // Проверка установленного Типа задачи





        //Закрываем все групп полей
        selectGroupTab("Название");
        selectGroupTab("Кому");
        selectGroupTab("Срок");
        selectGroupTab("Тип задачи");
        selectGroupTab("Файлы");
        selectGroupTab("Еще");
        return this;
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
     * @param task передаваемые атрибуты задачи
     */
    public NewTaskStepsMobile creatingTask(Task task) {
       ensurePageLoaded();



       // Разворачиваем  группу полей  "Название"
        selectGroupTab("Название");



       //Заполняем Название задачи
        setTaskName(task.getTaskName())
                .setTasksDescription(task.getDescription());

        verifyValueInInput("Название", task.getTaskName());
        verifyValueInInput("Проект", "Главное подразделение: Задачи вне проектов");

        // Закрываем  группу полей  "Название"
        selectGroupTab("Название");


        verifyValueBeforeOpenGroupFields(task.getTaskName(), "taskname"); //Проверка поля названия при закрытой группы полей "Название"

        // Открываем  группу полей  "Срок"
        selectGroupTab("Срок");


        //Заполняем Даты
        setDateBegin(task.getDateBegin())
                .setDateEnd(task.getDateEnd());

        verifyValueInInput("Окончание", task.getDateEnd())
                .verifyValueInInput("Начало", task.getDateBegin());

        setImportance(task.getIsImportant()); // "Приоритет" - выбираем - Важная задача

        /*
        taskStepsMobile.verifyIsImportant(task.getIsImportant()); //Проверка выбранного значения в поле "Приоритет"
        //verifyIsImportant метод в потомке - поэтому не вызовешь
        */

        // Закрываем  группу полей  "Срок"
        selectGroupTab("Срок");
        verifyValueBeforeOpenGroupFields(task.getDateEnd(), "enddate"); // Проверка поля -  Дата окончания - при закрытой группе полей  "Срок".


        $(By.xpath("//div[contains(@id,\"object\")]//input[@name='taskname']")).shouldNotBe(empty);//Проверка поля названия при закрытой группы полей "Название" - проверяет что, поле не пустое, т.к должно быть значение по умолчанию.
        // Открываем  группу полей  "Тип задачи"
        selectGroupTab("Тип задачи");

        setTaskType(task.getTaskType()); //Тип задачи

        /*
         * Проверка  Тип задачи
         */
        verifyTaskType(task.getTaskType());


        // Закрываем  группу полей  "Тип задачи"
        selectGroupTab("Тип задачи");


        // Открываем группу полей "Ещё"
        selectGroupTab("Еще");
        newTaskFormElementsMobile.getReportRequired().shouldBe(selected); // Признак - С Докладом всегда по умолчанию должен быть выбран.
        rangeOfValuesFromTheCheckbox(task.getIsSecret(), newTaskFormElementsMobile.getCheckboxIsSecret()); // признак - Секретная
        rangeOfValuesFromTheCheckbox(task.getIsForReview(), newTaskFormElementsMobile.getCheckboxIsForReview()); // Признак - "Для ознакомления"


        // Закрываем  группу полей "Ещё"
        selectGroupTab("Еще");


        // тест отображения полей при закрытых групп полей TODO Единный метод в котром описаны все поля , которые отображаются/НЕ отображаются после закрытия всех групп полей
        fieldsWhenGroupsCloset();
        fieldsWhenGroupsOpen(task);
        //TODO  можно  verifyValueInInput в эти метода. Сами метода можно вынест в CreateTaskMobileTes после создания перед сохранением


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
