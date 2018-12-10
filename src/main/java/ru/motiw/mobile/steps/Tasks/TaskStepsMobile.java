package ru.motiw.mobile.steps.Tasks;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.AssertJUnit;
import ru.motiw.mobile.elements.Internal.InternalElementsMobile;
import ru.motiw.mobile.elements.Tasks.TaskElementsMobile;
import ru.motiw.mobile.model.Task.TabsOfTask;
import ru.motiw.web.model.Administration.TasksTypes.TasksTypes;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertTrue;
import static ru.motiw.mobile.model.Task.InnerGroupTabs.*;
import static ru.motiw.mobile.model.Task.TabsOfTask.DESCRIPTION_TAB;
import static ru.motiw.mobile.model.Task.TabsOfTask.FILES_TAB;


public class TaskStepsMobile extends NewTaskStepsMobile {

    private TaskElementsMobile taskElementsMobile = page(TaskElementsMobile.class);
    private InternalElementsMobile internalElementsMobile = page(InternalElementsMobile.class);




    /**
     *  Переход между вкладками.
     *  Проверка наличия кнопок на тулбаре меню после перехода на вкладку.
     *  TODO  Проверка отображения подписей под кнопками и хинтов
     * @param nameOfTabs Название вкладки на тулбаре
     */
    public TaskStepsMobile openTab(TabsOfTask nameOfTabs) {
        // Ожидание и проверка элементов меню тулбара задачи
        verifyMenuOfTask();
        switch (nameOfTabs.getNameTab()) {
            case "Файлы":
                //Переходим на вкладку "Файлы"
                taskElementsMobile.getButtonOfFilesTab().waitUntil(visible, 2000).click();

                //проверка на отображение
                taskElementsMobile.getButtonOfFinalizeExecution().shouldBe(visible);
                taskElementsMobile.getElementAmongButtonsOfMenu().shouldBe(visible);

                taskElementsMobile.getPressedButtonOfFiles().shouldBe(visible);
                taskElementsMobile.getButtonOfActionsTab().shouldBe(visible);
                taskElementsMobile.getButtonOfDescriptionTab().shouldBe(visible);

                //проверка на Неотображение
                taskElementsMobile.getButtonOfAddAction().shouldNotBe(visible);
                taskElementsMobile.getButtonOfSave().shouldNotBe(visible);


                break;
            case "Действия":
                //Переходим на вкладку "Действия"

                taskElementsMobile.getButtonOfActionsTab().waitUntil(visible, 2000).click();

                //проверка на отображение
                taskElementsMobile.getButtonOfAddAction().shouldBe(visible);
                taskElementsMobile.getButtonOfFinalizeExecution().shouldBe(visible);
                taskElementsMobile.getElementAmongButtonsOfMenu().shouldBe(visible);

                taskElementsMobile.getPressedButtonOfActions().shouldBe(visible);
                taskElementsMobile.getButtonOfFilesTab().shouldBe(visible);
                taskElementsMobile.getButtonOfDescriptionTab().shouldBe(visible);

                //проверка на Неотображение
                taskElementsMobile.getButtonOfSave().shouldNotBe(visible);

                break;
            case "Описание":

                //Переходим на вкладку "Описание"
                taskElementsMobile.getButtonOfDescriptionTab().waitUntil(visible, 2000).click();

                //проверка на отображение
                taskElementsMobile.getButtonOfSave().shouldBe(visible);
                taskElementsMobile.getButtonOfFinalizeExecution().shouldBe(visible);
                taskElementsMobile.getElementAmongButtonsOfMenu().shouldBe(visible);

                taskElementsMobile.getPressedButtonOfDescription().waitUntil(visible, 2000);
                taskElementsMobile.getButtonOfFilesTab().shouldBe(visible);
                taskElementsMobile.getButtonOfActionsTab().shouldBe(visible);


                //проверка на Неотображение
                taskElementsMobile.getButtonOfAddAction().shouldNotBe(visible);
                break;

        }
        return this;
    }



    /**
     * Проверка значений в инпутах формы задачи при закрытой группе полей
     * @param valueInInput передаваемое значенние поля
     * @param nameOfElement имя элемента для xpath
     */
    TaskStepsMobile verifyValueBeforeOpenGroupFields(String valueInInput, String nameOfElement) {
        if (valueInInput == null) {
            return this;
        }
        $(By.xpath("//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//input[@name='" + nameOfElement + "']")).shouldHave(exactValue(valueInInput));
        return this;

    }

    /**
     * Проверка установленного по умолчанию Типа задачи - при закрытой группе полей "Тип задачи".
     * @param taskType передаваемое значенние поля Типа задачи
     */

    TaskStepsMobile verifyTaskTypeBeforeOpenGroupFields(TasksTypes taskType) {
        String nameOfTaskType = taskType.getObjectTypeName();
        verifyValueBeforeOpenGroupFields(nameOfTaskType, "id_tasktype");
        return this;
    }

    /**
     * Проверка значений в поле "Приоритет"
     * Сейчас задачу создаем всегда с установкой приоритета Важная задача - true
     * Но можно проверять и с рандомным значением.
     * т.к установка признака и его проверка завязаны на одно значение true/false в valueTask.getIsImportant()
     */


    boolean verifyIsImportant(boolean isImportant){
        if (isImportant) {
            $(By.xpath("//div[contains(@id,\"object\")]//input[@name=\"priority\"]")).shouldHave(value("Важная задача"));
            return true;
        } else {
            $(By.xpath("//div[contains(@id,\"object\")]//input[@name=\"priority\"]")).shouldHave(value("Обычная задача"));
            return true;
        }
    }


    /**
     * Проверка значений в чебоксах для последующего сравнения с передаваемым значением методом assertTrue
     */

    public boolean verifyCheckboxIsSelected (boolean isTrue, SelenideElement inputCheckbox) {
        if (isTrue) {
            inputCheckbox.shouldBe(selected);
            return true;
        } else {
            inputCheckbox.shouldNotBe(selected);
            return true;
        }

    }

    /**
     * Проверка значений в инпутах формы задачи
     * @param valueInInput передаваемое значенние поля
     * @param nameOfElement имя элемента для xpath
     */
    TaskStepsMobile verifyValueInInput(String nameOfElement, String valueInInput) {
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
     * Проверка добавленых пользователей в полях ролей задачи
     * @param employees       передаваемые пользователи
     * @param fieldCustomRole выбираемая роль в задаче (Исполнители, Контролеры, Авторы и ОР)
     */

    private void verifyUserInFieldOfRole(Employee[] employees, SelenideElement fieldCustomRole) {

        if (employees != null) {
            for (Employee employee : employees) {
                fieldCustomRole.shouldHave(exactText(employee.getLastName()));
            }
            }

    }

    /**
     * Проверка пользователей в формах добавления
     * @param employees       передаваемые пользователи
     */

    private void verifyUserInFormOfRole(Employee[] employees) {
        if (employees != null) {
            for (Employee employee : employees) {
                //проверка того, что элемент ПЕРВОГО пользователя в списке - выделен т.е выбран в роль
                //contains(@class,"x-selected" - вроде бы всех выделенных будет находить. todo проверить это
                newTaskFormElementsMobile.getSelectedUserInTheList(employee.getLastName()).shouldBe(visible);
                newTaskFormElementsMobile.getInputForSearchUsers().sendKeys(Keys.chord(Keys.ESCAPE)); //Закрыть форму

                //проверка того, что элемент ВТОРОГО пользователя в списке - выделен т.е выбран в роль.
                //Для выделенных элементов ВТОРОГО и след. пользователей в списке class в div-е отличается от ПЕРВОГО пользователя в списке
                return;
            }
        }

        //В случае, если employees == null - Проверяем, что элемент в списке не выделен. Нужно для проверки после удаления пользователя из поля раб.группы.
        newTaskFormElementsMobile.getSelectedUserInTheList().shouldNotBe(visible);
        newTaskFormElementsMobile.getInputForSearchUsers().sendKeys(Keys.chord(Keys.ESCAPE)); //Закрыть форму

    }


    private void verifyNameOfAttachedFiles(String[] nameOfFiles) {

        if (nameOfFiles != null)
            for (String nameOfFile : nameOfFiles) {
                assertTrue(verifyNameFileInTheListFiles(nameOfFile), "Не пройдена проверка названий Файлов в списке прикрепленных файлов");

            }
    }


    /*
     * Проверка отбражения полей при закрытых группах полей
     */
    public TaskStepsMobile fieldsWhenGroupsClosed() {

        newTaskFormElementsMobile.getTaskName().shouldBe(visible); // "Название"
        newTaskFormElementsMobile.getDescriptionTask().shouldBe(visible); // Описание задачи - отображение и проверка значения поля после закрытия группы полей  "Название"
        // Кому
        $(By.xpath("(//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]" +
                "//div[contains(text(),'Кому')]" +
                "//ancestor::div[contains(@class,\"x-panel x-container x-component small-collapser-panel x-noborder-trbl x-header-position-top x-panel-grey-background x-container-grey-background \")]" +
                "//div[@class=\"x-input-el\"])[1]")).shouldBe(visible);
        // TODO  xpath другой м.б написать - этот ищет все 4ре поля - хотя для других полей ведь тоже xpath одинаковые для раскрытого и закрытого поля. //span[contains(text(),'Ответственные руководители')]/../..//div[@class="x-input-el\ - не находит

        newTaskFormElementsMobile.getEndField().shouldBe(visible); // Окончание
        //$(By.xpath("//input[@name=\"id_tasktype\"]")).shouldBe(visible); // Тип задачи TODO не проходит на centos  т.к нет поля типа задачи пока просто закомментил

        if ($(newTaskFormElementsMobile.getButtonCreateTask()).is(Condition.visible)) {
            $(By.xpath("//input[@name=\"id_task_template\"] ")).shouldBe(visible); // Шаблон задачи в созданной задаче отсутствует
        }

        /*
         * Проверка полей которые НЕ должны отображаться после закрытия всех групп полей
         */

        newTaskFormElementsMobile.getTaskNumber().shouldNotBe(visible);  //поле №
        newTaskFormElementsMobile.getProjectTask().shouldNotBe(visible);// - проект не должен отображаться ДО раскрытия группы полей "Название"


        newTaskFormElementsMobile.getFieldOfWorkGroup("Авторы").shouldNotBe(visible);
        newTaskFormElementsMobile.getFieldOfWorkGroup("Контролеры").shouldNotBe(visible);
        newTaskFormElementsMobile.getFieldOfWorkGroup("Ответственные руководители").shouldNotBe(visible);
        newTaskFormElementsMobile.getFieldOfWorkGroup("Исполнители").shouldNotBe(visible);


        newTaskFormElementsMobile.getBeginField().shouldNotBe(visible); // Начало
        newTaskFormElementsMobile.getPriority().shouldNotBe(visible); // Приоритет
        newTaskFormElementsMobile.getFieldFiles().shouldNotBe(visible); //  поле - Файлы


        newTaskFormElementsMobile.getCheckboxReportRequired().shouldNotBe(visible); // Признак - С Докладом
        newTaskFormElementsMobile.getCheckboxIsSecret().shouldNotBe(visible); // признак - Секретная
        newTaskFormElementsMobile.getCheckboxIsForReview().shouldNotBe(visible); // Признак - "Для ознакомления"

        return this;
    }


    /*
     * Проверка отбражения полей при открытых группах полей
     */
    public TaskStepsMobile fieldsWhenGroupsOpen() {

        //Методы для Разворачивания всех групп полей
        selectGroupTab(NAME);
        selectGroupTab(TO_WHOM);
        selectGroupTab(DATE);
        //selectGroupTab("Тип задачи");
        selectGroupTab(FILES);
        selectGroupTab(MORE);

        //Проверка
        newTaskFormElementsMobile.getTaskName().shouldBe(visible); // "Название"
        newTaskFormElementsMobile.getDescriptionTask().shouldBe(visible); // Описание задачи
        newTaskFormElementsMobile.getTaskNumber().shouldBe(visible);  // поле №
        newTaskFormElementsMobile.getProjectTask().shouldBe(visible);// проект должен отображаться после раскрытия группы полей "Название"

        newTaskFormElementsMobile.getFieldOfWorkGroup("Авторы").shouldBe(visible);
        newTaskFormElementsMobile.getFieldOfWorkGroup("Контролеры").shouldBe(visible);
        newTaskFormElementsMobile.getFieldOfWorkGroup("Ответственные руководители").shouldBe(visible);
        newTaskFormElementsMobile.getFieldOfWorkGroup("Исполнители").shouldBe(visible);

        newTaskFormElementsMobile.getBeginField().shouldBe(visible); // Начало
        newTaskFormElementsMobile.getEndField().shouldBe(visible); // Окончание
        newTaskFormElementsMobile.getPriority().shouldBe(visible); // Приоритет
        //$(By.xpath("//input[@name=\"id_tasktype\"]")).shouldBe(visible);
        newTaskFormElementsMobile.getFieldFiles().shouldBe(visible); //  поле - Файлы


        newTaskFormElementsMobile.getCheckboxReportRequired().shouldBe(visible); // Признак - С Докладом
        newTaskFormElementsMobile.getCheckboxIsSecret().shouldBe(visible); // признак - Секретная
        newTaskFormElementsMobile.getCheckboxIsForReview().shouldBe(visible); // Признак - "Для ознакомления"

        if ($(newTaskFormElementsMobile.getButtonCreateTask()).is(Condition.visible)) {
            $(By.xpath("//input[@name=\"id_task_template\"] ")).shouldBe(visible); // Шаблон задачи в созданной задаче отсутствует.
            // TODO поля ввыбора шаблона может не быть, если у пользователя не создано шаблонов. Нужно в таком случае перед проверкой создавать в системе шаблон.
        }

        //Закрытитие все групп полей
        selectGroupTab(NAME);
        selectGroupTab(TO_WHOM);
        selectGroupTab(DATE);
        //selectGroupTab(TYPE_TASK);
        selectGroupTab(FILES);
        selectGroupTab(MORE);
        return this;
    }



    /*
     * Проверка значений в полях при закрытых группах полей
     */
    public TaskStepsMobile verifyValueWhenGroupsClosed(Task task) {

        verifyValueBeforeOpenGroupFields(task.getTaskName(), "taskname"); //Проверка поля названия при закрытой группы полей "Название"

        //div[contains(@id,'object') and not(contains(@class,"x-hidden-display"))]//div[@name="description"

        newTaskFormElementsMobile.getDescriptionTask().shouldHave(exactValue(task.getDescription())); // Проверка поля - Описание задачи - до раскрытия группы полей "Название". Через verifyValueInInput не проверишь т.к описание в div.

        //$(By.xpath("//div[contains(@id,\"object\")]//div[@name=\"description\"]"))
         //       .shouldHave(exactValue(task.getDescription())); // Проверка поля - Описание задачи - до раскрытия группы полей "Название". Через verifyValueInInput не проверишь т.к описание в div.
        // Можно в отдельный схожий метод для проверки если есть ещё поля где значения в div
        verifyValueBeforeOpenGroupFields(task.getDateEnd(), "enddate"); // Проверка поля -  Дата окончания - при закрытой группе полей  "Срок".
        //verifyTaskTypeBeforeOpenGroupFields(task.getTaskType()); // Проверка поля -  Тип задачи - при закрытой группе полей "Тип задачи".

        return this;
    }


    /*
     * Проверка значений в полях при открытых группах полей
     */
    public TaskStepsMobile verifyValueWhenGroupsOpen(Task task) {

        //Методы для Разворачивания всех групп полей
        selectGroupTab(NAME);
        selectGroupTab(TO_WHOM);
        selectGroupTab(DATE);
        selectGroupTab(TYPE_TASK);
        selectGroupTab(FILES);
        selectGroupTab(MORE);

        //Все проверки из verifyValueInInput
        verifyValueInInput("Название", task.getTaskName());

        newTaskFormElementsMobile.getDescriptionTask().shouldHave(exactValue(task.getDescription())); // Описание задачи. Через verifyValueInInput не проверишь т.к описание в div.

//        $(By.xpath("//div[contains(@id,\"object\")]//div[@name=\"description\"]"))
//                .shouldHave(exactValue(task.getDescription())); // Описание задачи. Через verifyValueInInput не проверишь т.к описание в div.
        // Можно в отдельный схожий метод для проверки еслиесть ещё поля где значения в div

        verifyValueInInput("Проект", "Главное подразделение: Задачи вне проектов");

        //Проверка пользователей в полях раб.группы
        verifyUserInFieldOfRole(task.getAuthors(), newTaskFormElementsMobile.getAuthorsField()); // - Авторы задачи
        verifyUserInFieldOfRole(task.getControllers(), newTaskFormElementsMobile.getСontrollersField()); //- Контролеры задачи
        verifyUserInFieldOfRole(task.getExecutiveManagers(), newTaskFormElementsMobile.getResponsiblesField()); // - Ответственные руковдители
        verifyUserInFieldOfRole(task.getWorkers(), newTaskFormElementsMobile.getWorkersField()); // - Исполнители задачи


        //Проверка выбранных пользователей в форме выбора "Авторы задачи"
        openFormSelectUser(newTaskFormElementsMobile.getAuthorsField());
        verifyUserInFormOfRole(task.getAuthors());

        //Проверка выбранных пользователей в форме выбора "Контролеры задачи"
        openFormSelectUser(newTaskFormElementsMobile.getСontrollersField());
        verifyUserInFormOfRole(task.getControllers());

        //Проверка выбранных пользователей в форме выбора "Ответственные руководители"
        openFormSelectUser(newTaskFormElementsMobile.getResponsiblesField());
        verifyUserInFormOfRole(task.getExecutiveManagers());

        //Проверка выбранных пользователей в форме выбора "Исполнители задачи"
        openFormSelectUser(newTaskFormElementsMobile.getWorkersField());
        verifyUserInFormOfRole(task.getWorkers());


        verifyValueInInput("Окончание", task.getDateEnd());
        verifyValueInInput("Начало", task.getDateBegin());
        Assert.assertTrue(verifyIsImportant(task.getIsImportant())); // Приоритет

        /*
         * Проверка в списке файлов
         * в форме создания задачи
         * на вкладке "Описание" в форме созданной задачи
         * проверка текста в просмотрике файлов в форме созданной задачи
         */
        verifyFilesInTheList(task);


        Assert.assertTrue(verifyCheckboxIsSelected(task.getIsWithReport(), newTaskFormElementsMobile.getReportRequired())); // Признак - С Докладом - по умолчанию выбран при создании задачи.
//        Assert.assertTrue(verifyCheckboxIsSelected(task.getIsSecret(), newTaskFormElementsMobile.getIsSecret())); // Признак Секретная todo motiwtest4.motiw.ru не проходит

        verifyTaskType(task.getTaskType()); // Проверка установленного Типа задачи

        //Закрытитие все групп полей
        selectGroupTab(NAME);
        selectGroupTab(TO_WHOM);
        selectGroupTab(DATE);
        selectGroupTab(TYPE_TASK);
        selectGroupTab(FILES);
        selectGroupTab(MORE);
        return this;
    }



    /**
     * Проверяем создание задачи
     *
     * @param valueTask атрибуты задачи
     * @return UnionMessageSteps
     */
    public TaskStepsMobile verifyCreateTask(Task valueTask) throws Exception {
        //refresh(); // чтобы сбросить из кеша все элементы что остаются после работы в форме создания задачи
        if (valueTask == null) {
            return null;
        } else

         internalElementsMobile.getMainTitle().shouldHave((text(valueTask.getTaskName()))); // Название задачи в хедере
        //Переходим на вкладку "Описание"
        openTab(DESCRIPTION_TAB);
        verifyElementsOnDescriptionPage();// Ожидание и проверка элементов на вкладке "Описание"
        fieldsWhenGroupsClosed(); //проверка наличия полей при закрытых группах полей
        fieldsWhenGroupsOpen();//проверка наличия полей при открытых группах полей
        verifyValueWhenGroupsClosed(valueTask); //проверка введенных значений в полях при закрытых группах полей
        verifyValueWhenGroupsOpen(valueTask); //проверка введенных значений в полях при открытых группах полей


        /*
         * Открываем группы полей "Срок"
         * Проверка даты начала и окончания задачи и приоритета
         */
        selectGroupTab(DATE); // Открываем вкладку "Срок"

        /*
        $(By.xpath("//div[contains(@id,\"object\")]//input[@name=\"startdate\"]"))
                .shouldHave(value(valueTask.getDateBegin())); //проверка даты, если заполнять её при создании задачи - см. в Tasks objectDataTaskPDA .setDateBegin(yesterdayDate())
                */
        // TODO подумать над необходимостью добавления проверки того, что дата по умолчанию подставилась верная. Раньше в web не было

        selectGroupTab(DATE); // Закрываем вкладку "Срок"


        /*
         * Открываем вкладку "Ещё"
         * Проверка признаков в чекбоксах
         */
        selectGroupTab(MORE); // Открываем вкладку "Еще"
        newTaskFormElementsMobile.getIsForReview().shouldBe(disabled); // Признак -  "Для ознакомления" в созданной задаче должен быть задизейблен. Состояние чекбокса не отображается.
        selectGroupTab(MORE); // Закрываем вкладку "Еще"


        /*
         * Открываем вкладку "Файлы"
         * Проверка того, чтобы кол-во файлов в элементе-переключателе файлов соответствовало числу файлов содержашихся в задаче
         * проверка скачивания файлов и текста в просмотрике файлов (каруселе)
         */
         verifyFiles(valueTask);


        /*
         Window PopUp. Store your parent window
         */
        /*
        String parentWindowHandler = getWebDriver().getWindowHandle();
        getWebDriver().switchTo().window(new WebDriverWait(getWebDriver(), 10).until(newWindowForm(By.xpath("//body[@id='unionmessage']//li//span[text()='Действия']"))));
        ensurePageLoaded();
        if (valueTask == null) {
            return null;
        } else
            verifyEnd(valueTask.getDateEnd())
                    .verifyProject(valueTask.getProject())
                    .verifyTaskDescription(valueTask.getDescription())
                    .verifyBegin(valueTask.getDateBegin())
                    .verifyImportance(valueTask.getIsImportant())
                    .verifyAuthors(valueTask.getAuthors())
                    .verifyControllers(valueTask.getControllers())
                    .verifyWorkers(valueTask.getWorkers())
                    .verifyResppersons(valueTask.getExecutiveManagers())
                    .verifyTaskType(valueTask.getTaskType())
                    .verifyIWG(valueTask.getIWG())
                    // TODO вынести проверки -  .verifyCheckpoints(valueTask.getCheckpoints()) и .verifyIWG(valueTask.getIWG()) в отдельные - для соот.  отдельных методов
                    .verifyCheckpoints(valueTask.getCheckpoints())
                    .verifyReport(valueTask.getIsWithReport())
                    .verifySecret(valueTask.getIsSecret())
                    .verifyReview(valueTask.getIsForReview())
                    .postAction(valueTask.getActions())
                    .verifyAction();

        closeWindow();
        getWebDriver().switchTo().window(parentWindowHandler);  // Switch back to parent window
        */



        return this;
    }

    public TaskStepsMobile verifyFiles(Task task) throws Exception {
        if (task.getFileName() == null) {
            return this;
        } else {
            // сравниваем кол-во прикрепленных файлов с числом отображаемым в элементе-переключтеле файлов.
            taskElementsMobile.getNumbersOnElementCounterFiles().shouldHave(text("1 / " + task.getNumberOfFiles()));
            //Проверка файлов в каруселе
            openTab(FILES_TAB);
            verifyFilesInPreview(task.getFileName(), task.getNumberOfFiles());
            }

        return this;
    }

    /**
     * Скачивание файла в просмотрщике файлов формы задачи
     * Проверяем имя скаченного файла и наличие текста в просмотрщике файла.
     * @param nameFiles передаваемое Имя файла для скачивания
     * @return TaskActionsStepsPDA форма задачи
     * @throws FileNotFoundException  в том случае, если файл не будет загружен метод .download() выкинет FileNotFoundException
     */
    public TaskStepsMobile verifyFilesInPreview(String[] nameFiles, int numbersOfFiles) throws FileNotFoundException {

        for (int numberOfCurrentFile = 1; numberOfCurrentFile < numbersOfFiles+1; numberOfCurrentFile++) {
            File downloadedFile =
                    $(By.xpath("//div[contains(@class,\"x-container x-component x-titlebar-right x-size-monitored\")]//a[@href]")).download();

            // Проверяем имя скаченного файла и текст в просмотрщике файла.
            assertTrue(verifyNameAndTextOfFile(downloadedFile.getName(), nameFiles), "Название скаченного файла не совпадает с набором названий файлов прикрепляемых к задаче!");
            AssertJUnit.assertTrue(downloadedFile.getAbsolutePath().startsWith(folder.getAbsolutePath()));
            switchToNextFile(numberOfCurrentFile, numbersOfFiles); //Переход к следующему файлу через кнопку "Вперед" в просмотрщике файлов
        }
        return this;
    }

    /**
     * Переход к следующему файлу через кнопку "Вперед" в просмотрщике файлов
     * @param numberOfCurrentFile  Номер текущего файла открытого в предпросмотре
     * @param numbersOfFiles Число всех прикрепленных файлов
     */

    public void switchToNextFile (int numberOfCurrentFile, int numbersOfFiles) {
        taskElementsMobile.getNumbersOnElementCounterFiles().waitUntil(Condition.text((numberOfCurrentFile) + " / " + numbersOfFiles), 2000); //проверка числа файлов в счетчике

        if (numbersOfFiles > 1) {
            $(By.xpath("//div[@class=\"x-icon-el x-font-icon x-mi mi-chevron-right\"]/ancestor::div[contains(@id,\"ext-filesnavigationbtn\")]")).click(); //переходим к следующему файлу в карусели
            sleep(500);
        }

        //Проверка изменения числа в счетчике после перехода к следующему файлу в просмотрщике
        if (numberOfCurrentFile != numbersOfFiles) {
            taskElementsMobile.getNumbersOnElementCounterFiles().waitUntil(Condition.text((numberOfCurrentFile + 1) + " / " + numbersOfFiles), 2000); //изменение числа в счетчике после переключения между файлами в просмотрщике
        }

        //В случае переключения "Вперед" с последнего файла, происходит переход к первому файлу, соответственно число в счетчике изменяется на 1
        if (numberOfCurrentFile == numbersOfFiles) {
            taskElementsMobile.getNumbersOnElementCounterFiles().waitUntil(Condition.text((1) + " / " + numbersOfFiles), 2000); //изменение числа в счетчике после переключения между файлами в просмотрщике
        }

    }

    /**
     * Сравнение имени скаченного файла с набором названий файлов прикрепляемых к задаче
     * Проверяем наличие текста в просмотрщике файла.
     * @param nameOfDownloadedFile имя скаченного файла
     * @param nameFiles набор названий файлов прикрепляемых к документу
     */

    private boolean verifyNameAndTextOfFile(String nameOfDownloadedFile, String[] nameFiles) {

        for(String nameFile : nameFiles) {
            if(nameOfDownloadedFile.equals(nameFile)){
                verifyTextInFilesInPreview(nameFile); // Проверяем наличие текста в просмотрщике файла.
                return true;
            }
        }
        return false;
    }


    /**
     * Проверка наличия текста в просмотрщике файлов формы задачи
     * @param textInFile передаваемое Имя файла для скачивания - текст с Именем файла находится в прикрепляемых для теста файлах
     */
    public TaskStepsMobile verifyTextInFilesInPreview(String textInFile)  {
        switchTo().frame($(By.xpath("//iframe")));  //Переходим во фрейм просмотра файлов
        $(By.xpath("//div[@id=\"viewerContainer\"]//div[@class=\"textLayer\"]/div[contains(text(),'" + textInFile + "')]")).shouldBe(visible);
        switchTo().defaultContent();  //Уходим из фрейма просмотра файлов
        return this;
    }


    /**
     * Проверка названий в списке прикрепленных файлов  в форме создания задачи и в форме созданной задачи
     * Проверка открытия файла в предпросмотре (по клику) на вкладке "Описание" в форме созданной задачи
     * проверка текста в просмотрике файлов в форме созданной задачи
     * todo и скачивание
     * @param task объект Задача
     * @return
     */
    public TaskStepsMobile verifyFilesInTheList(Task task) {

        verifyNameOfAttachedFiles(task.getFileName()); // Названия файлов в списке поля "Файлы"

        //Выполняем проверку файлов в предпросмотре только в форме созданной задачи
        if (taskElementsMobile.getButtonOfDescriptionTab().is(visible)) {
            if (task.getFileName() == null) {
                return this;
            } else {
                for (String fileName : task.getFileName()) {
                    $(By.xpath("//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]" +
                            "//i[contains(@class,'file')]/ancestor::div[contains(@class,\"x-body-wrap-el x-panel-body-wrap-el x-container-body-wrap-el x-component-body-wrap-el \")]" +
                            "//div[contains(text(),'" + fileName + "')]"))
                            .click(); //открытие файла в предпросмотре
                    sleep(500);
                    verifyTextInFilesInPreviewOnDescriptionPage(fileName); //Проверка наличия текста в просмотрщике файлов
                    $(By.xpath("//div[@class=\"x-component x-button x-round-button-floated x-has-icon x-icon-align-left x-arrow-align-right x-button-alt x-component-alt x-button-round x-component-round x-floating\"]"))
                            .click();  //закрытие предпросмотра
                    sleep(500);
                }
            }
        }

        return this;
    }


    /**
     * Проверка наличия текста в просмотрщике файлов на вкладке "Описнаие" задачи
     * @param textInFile передаваемое Имя файла для скачивания - текст с Именем файла находится в прикрепляемых для теста файлах
     */
    public TaskStepsMobile verifyTextInFilesInPreviewOnDescriptionPage(String textInFile)  {
        switchTo().frame($(By.xpath("(//iframe)[2]")));  //Переходим во фрейм просмотра файлов
        $(By.xpath("//div[@id=\"viewerContainer\"]//div[@class=\"textLayer\"]/div[contains(text(),'" + textInFile + "')]")).shouldBe(visible);
        switchTo().defaultContent();  //Уходим из фрейма просмотра файлов
        return this;
    }


    /**
         * Удаление файлов через ДнД (свайп влево по плашке с файлом )
         * @param nameOfFiles названия прикрепляемых файлов в объекте task
         * todo удалять файлы через открытие панели кнопок для взаимодействия с файлами (открытие панели кнопок через drag and drop)
         */
        public void deleteFile (String[] nameOfFiles) {

            for (String nameOfFile : nameOfFiles) {
                SelenideElement sourceElement = $(By
                        .xpath("//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//div[contains(text(), '" + nameOfFile + "')]/ancestor::div[contains(@class,\"x-listitem x-component\")]"));
                SelenideElement targetElement = $(taskElementsMobile.getElementAmongButtonsOfMenu());

                sleep(200);
                Actions builder = new Actions(getWebDriver());
                Action drag = builder.clickAndHold(sourceElement).build();
                Action drop = builder.moveToElement(targetElement)
                        .release(targetElement).build();
                drag.perform();
                drop.perform();
                sleep(500);
            }


        }


    // Ожидание и проверка элементов меню тулбара задачи
    private void verifyMenuOfTask() {
        $(taskElementsMobile.getToolbarOfMenu()).waitUntil(visible, 50000);
        taskElementsMobile.getMenuOfTask().shouldHaveSize(9); // 9 элементов - это вместе со скрытыми элементами.
    }


    // Ожидание и проверка элементов в форме задачи на вкладке Описание
    public void verifyElementsOnDescriptionPage() {
       newTaskFormElementsMobile.getCollectionElementsFormOfTask().shouldHave(CollectionCondition.size(7), 10000);
    }

}
