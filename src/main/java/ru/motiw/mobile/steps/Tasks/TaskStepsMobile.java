package ru.motiw.mobile.steps.Tasks;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import ru.motiw.mobile.elements.Tasks.TaskElementsMobile;
import ru.motiw.web.model.Administration.TasksTypes.TasksTypes;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertTrue;

public class TaskStepsMobile extends NewTaskStepsMobile {

    private TaskElementsMobile taskElementsMobile = page(TaskElementsMobile.class);



    /**
     *  Переход между вкладками.
     *  Проверка наличия кнопок на тулбаре меню после перехода на вкладку.
     *  TODO  Проверка отображения подписей под кнопками и хинтов
     * @param nameOfTabs Название вкладки на тулбаре
     */
    public TaskStepsMobile openTab(String nameOfTabs) {
        // Ожидание и проверка элементов меню тулбара задачи
        verifyMenuOfTask();

        switch (nameOfTabs) {
            case "Файлы":
                //Переходим на вкладку "Файлы"
                taskElementsMobile.getButtonOfFilesTab().waitUntil(visible, 10000).click();

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

                taskElementsMobile.getButtonOfActionsTab().waitUntil(visible, 10000).click();

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
                taskElementsMobile.getButtonOfDescriptionTab().waitUntil(visible, 10000).click();

                //проверка на отображение
                taskElementsMobile.getButtonOfSave().shouldBe(visible);
                taskElementsMobile.getButtonOfFinalizeExecution().shouldBe(visible);
                taskElementsMobile.getElementAmongButtonsOfMenu().shouldBe(visible);

                taskElementsMobile.getPressedButtonOfDescription().waitUntil(visible, 5000);
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
        $(By.xpath("//div[contains(@id,\"object\")]//input[@name='" + nameOfElement + "']")).shouldHave(exactValue(valueInInput));
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
     * @param componentId т.к после каждого открытия формы выбора пользователей она остается в DOM, то приходится передавать componentId
     * componentId = ext-selectdialog-{порядковый номер открытой формы}
     */

    private void verifyUserInFormOfRole(Employee[] employees, String componentId) {
        if (employees != null) {
            for (Employee employee : employees) {
                //проверка того, что элемент ПЕРВОГО пользователя в списке - выделен т.е выбран в роль
                //contains(@class,"x-selected" - вроде бы всех выделенных будет находить. todo проверить это
                $(By.xpath("//div[@data-componentid='" + componentId + "']//div[contains(@class,\"x-selected\")]//div[contains(text(),'" + employee.getLastName() + "')]")).shouldBe(visible);
                newTaskFormElementsMobile.getInputForSearchUsers(componentId).sendKeys(Keys.chord(Keys.ESCAPE)); //Закрыть форму

                //проверка того, что элемент ВТОРОГО пользователя в списке - выделен т.е выбран в роль.
                //Для выделенных элементов ВТОРОГО и след. пользователей в списке class в div-е отличается от ПЕРВОГО пользователя в списке
                return;
            }
        }

        //В случае, если employees == null - Проверяем, что элемент в списке не выделен. Нужно для проверки после удаления пользователя из поля раб.группы.
        $(By.xpath("//div[@data-componentid='" + componentId + "']//div[contains(@class,\"x-selected\")]")).shouldNotBe(visible);
        newTaskFormElementsMobile.getInputForSearchUsers(componentId).sendKeys(Keys.chord(Keys.ESCAPE)); //Закрыть форму

    }


    private void verifyNameOfAttachedFiles(String[] nameOfFiles) {

        if (nameOfFiles != null)
            for (String nameOfFile : nameOfFiles) {
                assertTrue(verifyNameFileInTheListFiles(nameOfFile));

            }

    }


    /*
     * Проверка отбражения полей при закрытых группах полей
     */
    public TaskStepsMobile fieldsWhenGroupsClosed() {

        newTaskFormElementsMobile.getTaskName().shouldBe(visible); // "Название"
        newTaskFormElementsMobile.getDescriptionTask().shouldBe(visible); // Описание задачи - отображение и проверка значения поля после закрытия группы полей  "Название"
        // Кому
        $(By.xpath("(//div[contains(text(),'Кому')]//ancestor::div[contains(@class,\"x-panel x-container x-component small-collapser-panel x-noborder-trbl x-header-position-top x-panel-grey-background x-container-grey-background \")]//div[@class=\"x-input-el\"])[1]")).shouldBe(visible);
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


    /*
     * Проверка отбражения полей при открытых группах полей
     */
    public TaskStepsMobile fieldsWhenGroupsOpen() {

        //Методы для Разворачивания всех групп полей
        selectGroupTab("Название");
        selectGroupTab("Кому");
        selectGroupTab("Срок");
        //selectGroupTab("Тип задачи");
        selectGroupTab("Файлы");
        selectGroupTab("Еще");



        //Проверка
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
        selectGroupTab("Название");
        selectGroupTab("Кому");
        selectGroupTab("Срок");
        //selectGroupTab("Тип задачи");
        selectGroupTab("Файлы");
        selectGroupTab("Еще");
        return this;
    }



    /*
     * Проверка значений в полях при закрытых группах полей
     */
    public TaskStepsMobile verifyValueWhenGroupsClosed(Task task) {

        verifyValueBeforeOpenGroupFields(task.getTaskName(), "taskname"); //Проверка поля названия при закрытой группы полей "Название"

        $(By.xpath("//div[contains(@id,\"object\")]//div[@name=\"description\"]"))
                .shouldHave(exactValue(task.getDescription())); // Проверка поля - Описание задачи - до раскрытия группы полей "Название". Через verifyValueInInput не проверишь т.к описание в div.
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
        selectGroupTab("Название");
        selectGroupTab("Кому");
        selectGroupTab("Срок");
        //selectGroupTab("Тип задачи");
        selectGroupTab("Файлы");
        selectGroupTab("Еще");

        //Все проверки из verifyValueInInput
        verifyValueInInput("Название", task.getTaskName());

        $(By.xpath("//div[contains(@id,\"object\")]//div[@name=\"description\"]"))
                .shouldHave(exactValue(task.getDescription())); // Описание задачи. Через verifyValueInInput не проверишь т.к описание в div.
        // Можно в отдельный схожий метод для проверки еслиесть ещё поля где значения в div

        verifyValueInInput("Проект", "Главное подразделение: Задачи вне проектов");

        //Проверка пользователей в полях раб.группы
        verifyUserInFieldOfRole(task.getAuthors(), newTaskFormElementsMobile.getAuthorsField()); // - Авторы задачи
        verifyUserInFieldOfRole(task.getControllers(), newTaskFormElementsMobile.getСontrollersField()); //- Контролеры задачи
        verifyUserInFieldOfRole(task.getExecutiveManagers(), newTaskFormElementsMobile.getResponsiblesField()); // - Ответственные руковдители
        verifyUserInFieldOfRole(task.getWorkers(), newTaskFormElementsMobile.getWorkersField()); // - Исполнители задачи


        //Проверка выбранных пользователей в форме выбора "Авторы задачи"
        openFormSelectUser(newTaskFormElementsMobile.getAuthorsField(), "ext-selectdialog-1");
        verifyUserInFormOfRole(task.getAuthors(),"ext-selectdialog-1");

        //Проверка выбранных пользователей в форме выбора "Контролеры задачи"
        openFormSelectUser(newTaskFormElementsMobile.getСontrollersField(), "ext-selectdialog-2");
        verifyUserInFormOfRole(task.getControllers(),"ext-selectdialog-2");

        //Проверка выбранных пользователей в форме выбора "Ответственные руководители"
        openFormSelectUser(newTaskFormElementsMobile.getResponsiblesField(), "ext-selectdialog-3");
        verifyUserInFormOfRole(task.getExecutiveManagers(), "ext-selectdialog-3");

        //Проверка выбранных пользователей в форме выбора "Исполнители задачи"
        openFormSelectUser(newTaskFormElementsMobile.getWorkersField(),"ext-selectdialog-4");
        verifyUserInFormOfRole(task.getWorkers(), "ext-selectdialog-4");


        verifyValueInInput("Окончание", task.getDateEnd());
        verifyValueInInput("Начало", task.getDateBegin());
        Assert.assertTrue(verifyIsImportant(task.getIsImportant())); // Приоритет


        verifyNameOfAttachedFiles(task.getFileName()); // Названия файлов в списке поля "Файлы"

        Assert.assertTrue(verifyCheckboxIsSelected(task.getIsWithReport(), newTaskFormElementsMobile.getReportRequired())); // Признак - С Докладом - по умолчанию выбран при создании задачи.
        Assert.assertTrue(verifyCheckboxIsSelected(task.getIsSecret(), newTaskFormElementsMobile.getIsSecret())); // Признак Секретная

        //verifyTaskType(task.getTaskType()); // Проверка установленного Типа задачи

        //Закрытитие все групп полей
        selectGroupTab("Название");
        selectGroupTab("Кому");
        selectGroupTab("Срок");
        //selectGroupTab("Тип задачи");
        selectGroupTab("Файлы");
        selectGroupTab("Еще");
        return this;
    }



    /**
     * Проверяем создание задачи
     *
     * @param valueTask атрибуты задачи
     * @return UnionMessageSteps
     */
    public TaskStepsMobile verifyCreateTask(Task valueTask) {
        refresh(); // чтобы сбросить из кеша все элементы что остаются после работы в форме создания задачи
        if (valueTask == null) {
            return null;
        } else
        $(By.xpath("//div[@class=\"x-component x-title x-title-align-left x-layout-box-item x-layout-hbox-item x-flexed\"]/div[text()='" + valueTask.getTaskName() + "']"))
                .waitUntil(visible, 20000); // Название задачи в хедере todo почемуто валится на площадке http://motiwtest4.motiw.ru в версии 2.3

        //Переходим на вкладку "Описание"
        openTab("Описание");
        verifyElementsOnDescriptionPage();// Ожидание и проверка элементов на вкладке "Описание"
        fieldsWhenGroupsClosed(); //проверка наличия полей при закрытых группах полей
        fieldsWhenGroupsOpen();//проверка наличия полей при открытых группах полей
        verifyValueWhenGroupsClosed(valueTask); //проверка введенных значений в полях при закрытых группах полей
        verifyValueWhenGroupsOpen(valueTask); //проверка введенных значений в полях при открытых группах полей


        /*
         * Открываем группы полей "Срок"
         * Проверка даты начала и окончания задачи и приоритета
         */
        selectGroupTab("Срок"); // Открываем вкладку "Срок"

        /*
        $(By.xpath("//div[contains(@id,\"object\")]//input[@name=\"startdate\"]"))
                .shouldHave(value(valueTask.getDateBegin())); //проверка даты, если заполнять её при создании задачи - см. в Tasks objectDataTaskPDA .setDateBegin(yesterdayDate())
                */
        // TODO подумать над необходимостью добавления проверки того, что дата по умолчанию подставилась верная. Раньше в web не было

        selectGroupTab("Срок"); // Закрываем вкладку "Срок"


        /*
         * Открываем вкладку "Ещё"
         * Проверка признаков в чекбоксах
         */
        selectGroupTab("Еще"); // Открываем вкладку "Еще"
        newTaskFormElementsMobile.getIsForReview().shouldBe(disabled); // Признак -  "Для ознакомления" в созданной задаче должен быть задизейблен. Состояние чекбокса не отображается.
        selectGroupTab("Еще"); // Закрываем вкладку "Еще"


        /*
         * Открываем вкладку "Файлы"
         * Проверка того, чтобы кол-во файлов в элементе с-переключателе файлов соответствовало числу файлов содержашихся в задаче
         * проверка числа файлов в каруселе
         */
         verifyNumbersOfFiles(valueTask);




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

    public TaskStepsMobile verifyNumbersOfFiles(Task task) {

        //TODO надо подумать как сделать этот метод более универсальным. Чтобы его можно было использовать в любом тесте

        if (task.getFileName() == null) {
            return this;
        } else {
            openTab("Описание");
            selectGroupTab("Файлы"); // Открываем вкладку "Файлы"

            //считаем кол-во файлов - заносим в массив
            List<SelenideElement> nameFileInTheList = new ArrayList<>(newTaskFormElementsMobile.getListOfNameFiles());
            int numberOfFiles = nameFileInTheList.size();


            // сравниваем кол-во файлов с числом отображаемым в элементе-переключтеле файлов.

            //это можно наверное, в отдельный метод. На вход передавать только numberOfFiles.
            // Можно использовать для какого-нибудь теста, где прсто будут добавляться и удаляться файлы,
            // и парралельно сверяться этим методо кол-во в счетчике.
            taskElementsMobile.getNumbersOnElementCounterFiles().shouldHave(text("1 / " + numberOfFiles));

            selectGroupTab("Файлы"); // Закрываем вкладку "Файлы"

            //проверка числа файлов в каруселе
            openTab("Файлы");
            //Здесь можно добавлять файлы, напрмер, rtf -и сверять переключение и одновреенно отображение.

            // Скачивание отдельным методом через кнопку в парвом углу.

            //надо передавать в цикле имена файлов из констанст,
            // но как это сделать, если  эта проверка будет после редактирвоания задачи, а значит будет добавлен новый файл? итого = 3, а может 2 с учетом удаления.
            // Как узнать конкретное кол-во файлов и их емен?
            // самое простое gettext из каждого элемента массива  nameFileInTheList не получилось - возвращает пустое. м.б как-то иначе это сделать. все-таки тода полностю универльный метод получился бы проверки файлов.
            //нужно где-то с передаваемым объектом task и edittask передавать итогое кол-во после редактирования.


                try {
                    downloadsFiles("");

                } catch (IOException e) {
                    return this;
                }



                // И счетчик чтобы изменялся в каруселе

            }




        return this;
    }

    public void saveFile () {


    }


    // Ожидание и проверка элементов меню тулбара задачи
    private void verifyMenuOfTask() {
        $(taskElementsMobile.getToolbarOfMenu()).waitUntil(visible, 30000);
        taskElementsMobile.getMenuOfTask().shouldHaveSize(9); // 9 элементов - это вместе со скрытыми элементами.
    }


    // Ожидание и проверка элементов в форме задачи на вкладке Описание
    public void verifyElementsOnDescriptionPage() {
       taskElementsMobile.getElementsOnDescriptionPage().shouldHave(CollectionCondition.size(7), 10000);
    }

}
