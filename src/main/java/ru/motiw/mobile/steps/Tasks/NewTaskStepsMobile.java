package ru.motiw.mobile.steps.Tasks;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.testng.AssertJUnit;
import ru.motiw.mobile.elements.Internal.InternalElementsMobile;
import ru.motiw.mobile.elements.Login.LoginPageElementsMobile;
import ru.motiw.mobile.elements.Tasks.NewTaskFormElementsMobile;
import ru.motiw.mobile.elements.Tasks.TaskElementsMobile;
import ru.motiw.mobile.steps.InternalStepsMobile;
import ru.motiw.mobile.steps.LoginStepsMobile;
import ru.motiw.web.model.Administration.TasksTypes.TasksTypes;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Tasks.Task;
import ru.motiw.web.steps.BaseSteps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertTrue;
import static ru.motiw.mobile.model.URLMenuMobile.CREATE_TASK;
import static ru.motiw.mobile.steps.BaseStepsMobile.openSectionOnURLMobile;

/*
 Страница - Создать задачу
 */



public class NewTaskStepsMobile extends BaseSteps {

    NewTaskFormElementsMobile newTaskFormElementsMobile = page(NewTaskFormElementsMobile.class);
    private InternalElementsMobile internalElementsMobile = page(InternalElementsMobile.class);
    private LoginStepsMobile loginStepsMobile = page(LoginStepsMobile.class);
    private InternalStepsMobile internalStepsMobile = page(InternalStepsMobile.class);
    private LoginPageElementsMobile loginPageElementsMobile = page(LoginPageElementsMobile.class);
    private TaskElementsMobile taskElementsMobile = page(TaskElementsMobile.class);

    File folder = new File(Configuration.reportsFolder);


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
    @FindBy(xpath = "(//div[contains(text(),'Кому')]//ancestor::div[contains(@class,\"x-panel x-container x-component small-collapser-panel x-noborder-trbl x-header-position-top x-panel-grey-background x-container-grey-background\")]//div[@class=\"x-input-el\"])[2]")
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
     * Переход в Задачи/Создать задачу напрямую по ссылке
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
            internalStepsMobile.goToInternalMenu();
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
        newTaskFormElementsMobile.getButtonCreateTask().shouldBe(visible);
        return this;
    }



    /**
     * Название задачи
     *
     * @param nameTasks name task for input
     * @return page NewTaskPag
     */
    public NewTaskStepsMobile  setTaskName(String nameTasks) {
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
    public NewTaskStepsMobile setTasksDescription(String descriptionTasks) {
            if (descriptionTasks == null) {
                return this;
            }
            else {
                newTaskFormElementsMobile.getDescriptionTask().click();
                newTaskFormElementsMobile.getDescriptionTask().clear();
                newTaskFormElementsMobile.getDescriptionTask().setValue(descriptionTasks);
            }
        return this;
    }


    /**
     * Ввод даты начала
     */
    public NewTaskStepsMobile setDateBegin(String begin) {
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
    public NewTaskStepsMobile setDateEnd(String end) {
        if (end == null) {
            return this;
        } else {
            newTaskFormElementsMobile.getEndField().click();
            newTaskFormElementsMobile.getEndField().setValue(end);

        }
        return this;
    }


    /*
     * Открытие комонента выбора пользователей
     */
    public void openFormSelectUser(SelenideElement fieldCustomRole, String componentId) {
        fieldCustomRole.click(); //клик в само поле.
        $(By.xpath("//div[@data-componentid='" + componentId + "']//input")).waitUntil(visible, 5000); // поле ввода
    }


    /**
     * Добавление/Удаление пользователей в роль задачи
     * @param employees       передаваемые пользователи
     * @param fieldCustomRole выбираемая роль в задаче (Исполнители, Авторы и ОР)
     * @param componentId т.к после каждого открытия формы выбора пользователей она остается в DOM, то приходится передавать componentId
     * componentId = ext-selectdialog-{порядковый номер открытой формы}
     */

    void choiceUserOnTheRole(Employee[] employees, SelenideElement fieldCustomRole, String componentId) {
        openFormSelectUser(fieldCustomRole, componentId);
        if (employees != null) {
            for (Employee employee : employees) {
                newTaskFormElementsMobile.getInputForSearchUsers(componentId).setValue(employee.getLastName()); // вводим в поле ввода Фамилию пользователя
                newTaskFormElementsMobile.getListOfUsers(componentId).shouldBe(CollectionCondition.size(1), 10000); //ожидание когда будет найден один пользователь. Это с учетом того, что у нас доступно для выбора больше одного пользователя.

                //выбор пользователя в списке
                newTaskFormElementsMobile.getUserFromList(componentId, employee).shouldBe(visible).click();
                //newTaskFormElementsMobile.getListOfUsers(componentId).shouldBe(CollectionCondition.sizeGreaterThan(1), 5000); //ожидание когда загрузится список пользователей. Это с учетом того, что у нас доступно для выбора больше одного пользователя.
                newTaskFormElementsMobile.getButtonAppointUsers(componentId).click(); //кнопка "Назначить"
            }
        }
    }

    /**
     * Проверка того, что текущий пользователь добавлен по умолчанию в роль задачи
     * @param employees       передаваемые пользователи
     * @param fieldCustomRole выбираемая роль в задаче (Исполнители, Авторы и ОР)
     * @param componentId т.к после каждого открытия формы выбора пользователей она остается в DOM, то приходится передавать componentId
     * componentId = ext-selectdialog-{порядковый номер открытой формы}
     */

    void currentUserSelectedInTheRole(Employee[] employees, SelenideElement fieldCustomRole, String componentId) {
        openFormSelectUser(fieldCustomRole, componentId);
        if (employees != null) {
            for (Employee employee : employees) {
                //проверка того, что элемент ПЕРВОГО пользователя в списке - выделен т.е выбран в роль
                $(By.xpath("//div[@data-componentid='" + componentId + "']//div[contains(@class,\"x-selected\")]//div[contains(text(),'" + employee.getLastName() + "')]")).shouldBe(visible);
                newTaskFormElementsMobile.getInputForSearchUsers(componentId).sendKeys(Keys.chord(Keys.ESCAPE)); //Закрыть форму
            }
        }
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
                //fieldCustomRole.clear();
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

    public void rangeOfValuesFromTheCheckbox(boolean stateOfCheckbox, SelenideElement inputCheckbox) {
        if (stateOfCheckbox){
            inputCheckbox.click();
        } else if ($(inputCheckbox).is(selected)) {
            inputCheckbox.click();
        }
    }



    /**
     * Выбор булевой настройки в форме задачи в зависимости от состояния чекбокса.
     * Например, признак "Важная задача", "Секретная задача", "С докладом"
     *
     * @param stateOfCheckbox состояние установленной настройки
     * @param inputCheckbox    совершить действие (снять/установить настройку)
     * @param stateInputOfCheckbox  элемент для проверки состояния чекбокса (снят/установлен)
     */

    public void rangeOfValuesFromTheCheckbox(boolean stateOfCheckbox, SelenideElement inputCheckbox, SelenideElement stateInputOfCheckbox) {
        if (stateOfCheckbox){
            inputCheckbox.click();
        } else if ($(stateInputOfCheckbox).is(selected)) //если stateOfCheckbox не true, а чекбокс уже установлен, то снимаем.
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


    /*
    * Открытие группы полей на вкладке "Описание"
    * */
    public void selectGroupTab(String nameOfGroup){
        $(By.xpath("//div[contains(text(),'" + nameOfGroup + "')]//ancestor::div[contains(@class,\"x-unselectable x-paneltitle x-component\")]")).click();
    }


    /**
     * Создание обычной задачи КОПИЯ МЕТОДА ДЛЯ WEB
     * @param task передаваемые атрибуты задачи
     */
    public NewTaskStepsMobile creatingTask(Task task) {
        ensurePageLoaded();
        refresh(); //чтобы сбросить из кеша все элементы что остаются после работы в других формах

       // Разворачиваем  группу полей  "Название"
        selectGroupTab("Название");
       //Заполняем Название задачи
        setTaskName(task.getTaskName())
                .setTasksDescription(task.getDescription());
        // Закрываем  группу полей  "Название"
        selectGroupTab("Название");


        // Открываем  группу полей  "Кому"
        selectGroupTab("Кому");

        // выбор пользователя по ФИО - через searchlive
        currentUserSelectedInTheRole(task.getAuthors(), newTaskFormElementsMobile.getAuthorsField(), "ext-selectdialog-1"); // - по умолчанию Автор задачи текущий пользователь (admin)
        choiceUserOnTheRole(task.getControllers(), newTaskFormElementsMobile.getСontrollersField(), "ext-selectdialog-2"); // вводим - Контролеры задачи
        choiceUserOnTheRole(task.getExecutiveManagers(), newTaskFormElementsMobile.getResponsiblesField(), "ext-selectdialog-3"); // вводим - Ответственные руководители
        choiceUserOnTheRole(task.getWorkers(), newTaskFormElementsMobile.getWorkersField(),"ext-selectdialog-4"); // вводим - Исполнители задачи


        // Закрываем  группу полей  "Кому"
        selectGroupTab("Кому");

        // Открываем  группу полей  "Срок"
        selectGroupTab("Срок");
        //Заполняем Даты
        setDateBegin(task.getDateBegin())
                .setDateEnd(task.getDateEnd());
        setImportance(task.getIsImportant()); // "Приоритет" - выбираем - Важная задача
        // Закрываем  группу полей  "Срок"
        selectGroupTab("Срок");
        //$(By.xpath("//div[contains(@id,\"object\")]//input[@name='id_tasktype']")).shouldNotBe(empty);//Проверка поля названия при закрытой группы полей "Название" - проверяет что, поле не пустое, т.к должно быть значение по умолчанию.
        // Открываем  группу полей  "Тип задачи"
        //selectGroupTab("Тип задачи");
        //$(By.xpath("//div[contains(@id,\"object\")]//input[@name='id_tasktype']")).shouldNotBe(empty);//Проверка поля названия при открытой группы полей "Название" - проверяет что, поле не пустое, т.к должно быть значение по умолчанию.
        //setTaskType(task.getTaskType()); //Тип задачи
        // если у пользователя была создана задача только с типом "Обычный"(по умолчанию), то баг в АРМе - у него нет поля Тип задачи.
        // Поэтому все что связано с этим полем заккоментиронно.
        // Закрываем  группу полей  "Тип задачи"
        //selectGroupTab("Тип задачи");



        // Открываем группу полей "Файлы"
        selectGroupTab("Файлы");
        addAttachFiles(task.getFileName());
        // Закрываем  группу полей "Файлы"
        selectGroupTab("Файлы");


        // Открываем группу полей "Ещё"
        selectGroupTab("Еще");
        newTaskFormElementsMobile.getReportRequired().shouldBe(selected); // Признак - С Докладом всегда по умолчанию должен быть выбран.
        rangeOfValuesFromTheCheckbox(task.getIsSecret(), newTaskFormElementsMobile.getCheckboxIsSecret()); // признак - Секретная
        rangeOfValuesFromTheCheckbox(task.getIsForReview(), newTaskFormElementsMobile.getCheckboxIsForReview()); // Признак - "Для ознакомления"
        // Закрываем  группу полей "Ещё"
        selectGroupTab("Еще");



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
     * Ждем появление маски загрузки
     * Ждем пока исчезнит маска загрузки
     * @return UnionMessageSteps страница UnionMessageSteps (Задачи / Задачи) - убрал т.к пишет, что ничего не возвращает. Зачем это было в ориг. методе для web?
     */
    public NewTaskStepsMobile saveTask() {

        sleep(1000);
        newTaskFormElementsMobile.getButtonCreateTask().click();
        //Ждем появление маски загрузки
        loginPageElementsMobile.getMaskOfLoading().waitUntil(Condition.visible, 1000);
        //Ждем пока исчезнит маска загрузки
        loginPageElementsMobile.getMaskOfLoading().waitUntil(Condition.disappear, 5000);
        return page(NewTaskStepsMobile.class);

    }


    /**
     * Переходим по ссылке в появившемся toast в созданную задачу
     */

    public NewTaskStepsMobile goToNewTaskViaToast() {
        //Проверяем появление toast "Создана задача"
        loginPageElementsMobile.getToastOfNewTask().waitUntil(Condition.visible, 10000);
        loginPageElementsMobile.getTextOnToastOfNewTask().shouldHave(Condition.text("Создана задача №"));
        //Переходим по ссылке в появившемся toast в созданную задачу
        loginPageElementsMobile.getTextOnToastOfNewTask().click();
        return page (NewTaskStepsMobile.class);
    }



    /**
     * Аттачминг файлов в форме задачи
     * @param nameOfFiles  названия файлов
     */
    public NewTaskStepsMobile addAttachFiles (String[] nameOfFiles) {
        if (nameOfFiles != null)
        for (String nameOfFile : nameOfFiles) {
            String mainFilePath = "src" + File.separator + "main" + File.separator +
                    "resources" + File.separator + "attachfiles" + File.separator;
            File file = newTaskFormElementsMobile.getInputFiles()
                    .uploadFile(new File(mainFilePath, nameOfFile));
            assertTrue(file.exists()); // наверное, проверяет, что создан объект File.
            assertTrue(file.getPath().replace(File.separatorChar, '/').endsWith("src/main/resources/attachfiles/" + nameOfFile + ""));
            assertTrue(verifyNameFileInTheListFiles(nameOfFile), "Не пройдена проверка названий Файлов в списке прикрепленных файлов");
        }
        return this;
    }


    /**
     * Проверка названий Файлов в списке прикрепленных файлов
     * @param nameOfFile названия файлов
     *  false  если среди файлов в списке не находим название файла
     *  true   если среди файлов в списке находим название файла
     */

    public boolean verifyNameFileInTheListFiles(String nameOfFile) {

            List<SelenideElement> nameFileInTheList = new ArrayList<>(newTaskFormElementsMobile.getListOfNameFiles());

        for (SelenideElement nameFile : nameFileInTheList) {
            if ($(nameFile).is((text(nameOfFile)))) {
                return true;
            }
        }
            return false;
    }



    /**
     * Скачивание файла в просмотрщике файлов формы задачи
     * @param nameFiles передаваемое Имя файла для скачивания
     * @return TaskActionsStepsPDA форма задачи
     * @throws FileNotFoundException  в том случае, если файл не будет загружен метод .download() выкинет FileNotFoundException
     */
    public NewTaskStepsMobile downloadsFilesInPreview(String[] nameFiles, int numbersOfFiles) throws FileNotFoundException {

        for (int numberOfCurrentFile = 1; numberOfCurrentFile < numbersOfFiles+1; numberOfCurrentFile++) {
            taskElementsMobile.getNumbersOnElementCounterFiles().waitUntil(Condition.text((numberOfCurrentFile) + " / " + numbersOfFiles), 2000); //изменение числа в счетчике после переключения между файлами в просмотрщике
            //todo что если в методе перехода к следующему файлу в карусели - проверять сразу?
            File downloadedFile =
                    $(By.xpath("//div[contains(@class,\"x-container x-component x-titlebar-right x-size-monitored\")]//a[@href]")).download();

            assertTrue(verifyNameOfDownloadedFile(downloadedFile.getName(), nameFiles), "Название скаченного файла не совпадает с набором названий файлов прикрепляемых к задаче!");
            // TODO downloadedFile.getName() возвращает  - UTF-8''. assertEquals не проходит сравнение. Почему?
            //assertEquals(nameFiles, downloadedFile.getName());
            // assertEquals(nameFiles, readFileToString(downloadedFile, "UTF-8"));
            AssertJUnit.assertTrue(downloadedFile.getAbsolutePath().startsWith(folder.getAbsolutePath()));
            if (numbersOfFiles > 1) {
                $(By.xpath("//div[@class=\"x-icon-el x-font-icon x-mi mi-chevron-right\"]/ancestor::div[contains(@id,\"ext-filesnavigationbtn\")]")).click(); //переходим к следующему файлу в карусели
                sleep(500);
            }

        }
        return this;
    }

    /**
     * Сравнение имени скаченного файла с набором названий файлов прикрепляемых к задаче
     * @param nameOfDownloadedFile имя скаченного файла
     * @param nameFiles набор названий файлов прикрепляемых к документу
     */

    private boolean verifyNameOfDownloadedFile(String nameOfDownloadedFile, String[] nameFiles) {

        for(String nameFile : nameFiles) {
            if(nameOfDownloadedFile.equals(nameFile)){
                return true;
            }
        }
        return false;

    }

    }


