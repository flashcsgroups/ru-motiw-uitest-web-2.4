package ru.motiw.mobile.steps.Tasks;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import ru.motiw.mobile.elements.Tasks.TaskElementsMobile;
import ru.motiw.web.model.Administration.TasksTypes.TasksTypes;
import ru.motiw.web.model.Tasks.Task;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static junit.framework.TestCase.assertTrue;

public class TaskStepsMobile extends NewTaskStepsMobile {

    private TaskElementsMobile taskElementsMobile = page(TaskElementsMobile.class);



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



    /*
     * Проверка отбражения полей при закрытых группах полей
     */
    public NewTaskStepsMobile fieldsWhenGroupsClosed() {

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
    public NewTaskStepsMobile fieldsWhenGroupsOpen() {

        //Методы для Разворачивания всех групп полей
        selectGroupTab("Название");
        selectGroupTab("Кому");
        selectGroupTab("Срок");
        selectGroupTab("Тип задачи");
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
        $(By.xpath("//input[@name=\"id_tasktype\"]")).shouldBe(visible);
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
        selectGroupTab("Тип задачи");
        selectGroupTab("Файлы");
        selectGroupTab("Еще");
        return this;
    }



    /*
     * Проверка значений в полях при закрытых группах полей
     */
    public NewTaskStepsMobile verifyValueWhenGroupsClosed(Task task) {

        verifyValueBeforeOpenGroupFields(task.getTaskName(), "taskname"); //Проверка поля названия при закрытой группы полей "Название"

        $(By.xpath("//div[contains(@id,\"object\")]//div[@name=\"description\"]"))
                .shouldHave(exactValue(task.getDescription())); // Проверка поля - Описание задачи - до раскрытия группы полей "Название". Через verifyValueInInput не проверишь т.к описание в div.
        // Можно в отдельный схожий метод для проверки если есть ещё поля где значения в div
        verifyValueBeforeOpenGroupFields(task.getDateEnd(), "enddate"); // Проверка поля -  Дата окончания - при закрытой группе полей  "Срок".
        verifyTaskTypeBeforeOpenGroupFields(task.getTaskType()); // Проверка поля -  Тип задачи - при закрытой группе полей "Тип задачи".

        return this;
    }


    /*
     * Проверка значений в полях при открытых группах полей
     */
    public NewTaskStepsMobile verifyValueWhenGroupsOpen(Task task) {

        //Методы для Разворачивания всех групп полей
        selectGroupTab("Название");
        selectGroupTab("Кому");
        selectGroupTab("Срок");
        selectGroupTab("Тип задачи");
        selectGroupTab("Файлы");
        selectGroupTab("Еще");


        //Все проверки из verifyValueInInput
        verifyValueInInput("Название", task.getTaskName());

        $(By.xpath("//div[contains(@id,\"object\")]//div[@name=\"description\"]"))
                .shouldHave(exactValue(task.getDescription())); // Описание задачи. Через verifyValueInInput не проверишь т.к описание в div.
        // Можно в отдельный схожий метод для проверки еслиесть ещё поля где значения в div

        verifyValueInInput("Проект", "Главное подразделение: Задачи вне проектов");

        /*
        todo Кому
        1. Проверка в полях
        2. Проверка в форме выбора пользователей?  Нужно проверять выделенные объекты в гриде пользователй. Возможно ли?
        примерно так: используем openFormSelectUser, а затем по подобию метода авбора пользователй из грида choiceUserOnTheRole делаем проверку
        */





        verifyValueInInput("Окончание", task.getDateEnd());
        verifyValueInInput("Начало", task.getDateBegin());
        Assert.assertTrue(verifyIsImportant(task.getIsImportant())); // Приоритет
        Assert.assertTrue(verifyCheckboxIsSelected(task.getIsWithReport(), newTaskFormElementsMobile.getReportRequired())); // Признак - С Докладом - по умолчанию выбран при создании задачи.
        Assert.assertTrue(verifyCheckboxIsSelected(task.getIsSecret(), newTaskFormElementsMobile.getIsSecret())); // Признак Секретная

        verifyTaskType(task.getTaskType()); // Проверка установленного Типа задачи

        //Закрытитие все групп полей
        selectGroupTab("Название");
        selectGroupTab("Кому");
        selectGroupTab("Срок");
        selectGroupTab("Тип задачи");
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
        refresh(); //делать, чтобы сбросить из кеша все элементы что остаются после работы в форме создания задачи
        verifyMenuOfTask(); // Ожидание и проверка элементов меню
        if (valueTask == null) {
            return null;
        } else
        $(By.xpath("//div[@class=\"x-component x-title x-title-align-left x-layout-box-item x-layout-hbox-item x-flexed\"]/div[text()='" + valueTask.getTaskName() + "']"))
                .shouldBe(visible); // Название задачи в хедере

        //Переходим на вкладку "Описание"
        $(By.xpath("//div[text()=\"Описание\"]//ancestor::div[contains(@class,\"x-component x-button x-icon-align-top x-widthed x-has-icon\")]")).click();
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


    // Ожидание и проверка элементов меню
    public void verifyMenuOfTask() {
        $(taskElementsMobile.getToolbarOfMenu()).waitUntil(visible, 30000);
        taskElementsMobile.getMenuOfTask().shouldHaveSize(9); // TODO 9 элементов - это вместе со скрытыми элементами. Надо проверять какие действительно отображаются при переходе между вкладками.

    }


    // Ожидание и проверка элементов в форме задачи на вкладке Описание
    public void verifyElementsOnDescriptionPage() {
       taskElementsMobile.getElementsOnDescriptionPage().shouldHave(CollectionCondition.size(7), 10000);
       //TODO
        // 16 элементов такое кол-во получается после прямого прехода из формы создания задачи
        //Делаю refresh после перехода и проверяю действительное кол-во отображаемых элементов на вкладке Описание - элементов 7 (столько действительно отображается).
        // Но тогда теряется реальный пользовательский кейс, хотя какая польза от проверки кол-ва элементов котрые все равно уже не отображаются?
        //это может быть полезно только для проверки случая потери этих элементов после создания задачи. Например, перходим в созданную задачу, а там не подгрузятся эти элементы.
        //Но т.к у нас основные проверки тут - это наличе значений в полях, то делаем всегда refresh, а для кокретного случая (кешрования элемнтов после создания задачи, а также в других формах) можно сделать отдельную проверку.
        //с методом типа if первый xpath не visible, то кликай во второй элемент с тем же xpath

        //TODO ещё Наверное по-хорошему надо проверять действительное отображение элементов, которые visible  на вкладке Описание
        // - а это можно только через SelenideElement, а не ElementsCollection. Но там кажеться тоже возможны сложности и нужно будет находить и проверять строго отображаемые  элементы.


    }

}
