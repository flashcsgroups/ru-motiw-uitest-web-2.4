package ru.motiw.mobile.steps.Tasks;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
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
import ru.motiw.web.model.Administration.Directories.DirectoriesField;
import ru.motiw.web.model.Administration.FieldsObject.*;
import ru.motiw.web.model.Administration.TasksTypes.TasksTypes;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.fail;
import static ru.motiw.mobile.model.Task.InnerGroupTabs.*;
import static ru.motiw.mobile.model.Task.TabsOfTask.DESCRIPTION_TAB;
import static ru.motiw.mobile.model.Task.TabsOfTask.FILES_TAB;


public class TaskStepsMobile extends NewTaskStepsMobile {

    private TaskElementsMobile taskElementsMobile = page(TaskElementsMobile.class);
    private InternalElementsMobile internalElementsMobile = page(InternalElementsMobile.class);


    /**
     * Проверка созданой задачи
     *
     * @param valueTask атрибуты задачи
     * @return UnionMessageSteps
     */
    public TaskStepsMobile verifyCreatedTask(Task valueTask) throws Exception {
        //refresh(); // чтобы сбросить из кеша все элементы что остаются после работы в форме создания задачи
        if (valueTask == null) {
            return null;
        } else
            internalElementsMobile.getMainTitle().shouldHave((text(valueTask.getTaskName()))); // Название задачи в хедере
        //Переходим на вкладку "Описание"
        openTab(DESCRIPTION_TAB);
        verifyElementsOnDescriptionPage();// Ожидание и проверка элементов на вкладке "Описание"
        fieldsWhenGroupsClosed(); //проверка наличия полей при закрытых группах полей
        fieldsWhenGroupsOpen(valueTask);//проверка наличия полей при открытых группах полей
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

        return this;
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

        //----------------------------------------------------------------  Шаблон задачи
        verifyFieldTemplateOfTask();

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
     * Проверка отображения полей при открытых группах полей
     */
    public TaskStepsMobile fieldsWhenGroupsOpen(Task task) {

        //Разворачиваем все группы полей
        selectGroupTab(NAME);
        selectGroupTab(TO_WHOM);
        selectGroupTab(DATE);
        selectGroupTab(TYPE_TASK);
        selectGroupTab(FILES);
        selectGroupTab(MORE);

        //------------- Проверка

        // Шаблон задачи
        verifyFieldTemplateOfTask();

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


        //Проверка отображения названий пользовательских полей в форме - проверка присутсвия всех полей
        verifyNamesOfFields(task.getTaskFieldsName(), newTaskFormElementsMobile.getNameOfFieldsInCustomFields());

        //Сравнение набора пользовательских полей добавленных при создании задачи с теми, что отображаются в форме - проверка отсутсвия лишних полей
        checkNamesOfFields(task.getTaskFieldsName(), newTaskFormElementsMobile.getNameOfFieldsInCustomFields());

        //Закрытитие всех групп полей
        selectGroupTab(NAME);
        selectGroupTab(TO_WHOM);
        selectGroupTab(DATE);
        selectGroupTab(TYPE_TASK);
        selectGroupTab(FILES);
        selectGroupTab(MORE);
        return this;
    }



    /*
     * Проверка значений в полях при закрытых группах полей
     */
    public TaskStepsMobile verifyValueWhenGroupsClosed(Task task) {
        verifyValueInInputBeforeOpenGroupFields(task.getTaskName(), "taskname"); //Проверка поля названия при закрытой группы полей "Название"
        newTaskFormElementsMobile.getDescriptionTask().shouldHave(exactValue(task.getDescription())); // Проверка поля - Описание задачи - до раскрытия группы полей "Название". Через verifyValueInInput не проверишь т.к описание в div.
        verifyValueInInputBeforeOpenGroupFields(task.getDateEnd(), "enddate"); // Проверка поля -  Дата окончания - при закрытой группе полей  "Срок".
        verifyTaskTypeBeforeOpenGroupFields(task.getTaskType()); // Проверка поля -  Тип задачи - при закрытой группе полей "Тип задачи".
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

        verifyValueInInput("Проект", "Главное подразделение: Задачи вне проектов");

        //Проверка пользователей в полях раб.группы
        verifyUserInFieldOfRole(task.getAuthorDefault(), task.getAuthors(), newTaskFormElementsMobile.getAuthorsField()); // - Авторы задачи
        verifyUserInFieldOfRole(task.getControllers(), newTaskFormElementsMobile.getСontrollersField()); // - Контролеры задачи
        verifyUserInFieldOfRole(task.getExecutiveManagers(), newTaskFormElementsMobile.getResponsiblesField()); // - Ответственные руковдители
        verifyUserInFieldOfRole(task.getWorkers(), newTaskFormElementsMobile.getWorkersField()); // - Исполнители задачи


        //Проверка выбранных пользователей в форме выбора "Авторы задачи"
        openFormSelectUser(newTaskFormElementsMobile.getAuthorsField());
        verifyUserInFormOfRole(task.getAuthorDefault(), task.getAuthors());

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

        // Проверка значений в пользовательских полях
        verifyValueInCustomFields(task.getTaskFields());


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
            default:
                throw new IllegalArgumentException("Неверное название вкладки:" + nameOfTabs.getNameTab());
        }
        return this;
    }

    /**
     * Проверка значений в пользовательских полях
     */
    private TaskStepsMobile verifyValueInCustomFields(FieldObject[] taskTypeListEditObjects) {
        if (taskTypeListEditObjects == null) {
            return this;
        }

        for (FieldObject fieldObject : taskTypeListEditObjects) {

            // СТРОКА
            if (fieldObject.getFieldType() instanceof TypeListFieldsString) {
                verifyValueInInput(fieldObject.getFieldName(), fieldObject.getValueField());
            }

            // ТЕКСТ
            if (fieldObject.getFieldType() instanceof TypeListFieldsText) {
                compareInputAndValue(new ArrayList<String>(Collections.singleton(fieldObject.getValueField())), newTaskFormElementsMobile.getCollectionOfTextAreaInCustomField(fieldObject.getFieldName()));
                //Проверяем значения в поле
                newTaskFormElementsMobile.getTextAreaInCustomField(fieldObject.getFieldName()).shouldHave(exactValue(fieldObject.getValueField()));
            }

            // Целое
            if (fieldObject.getFieldType() instanceof TypeListFieldsInteger) {
                verifyValueInInput(fieldObject.getFieldName(), fieldObject.getValueField());
            }

            // ВЕЩЕСТВЕННОЕ
            if (fieldObject.getFieldType() instanceof TypeListFieldsDouble) {
                verifyValueInInput(fieldObject.getFieldName(), fieldObject.getValueField());
            }

            // ДАТА
            if (fieldObject.getFieldType() instanceof TypeListFieldsDate) {
                verifyValueInInput(fieldObject.getFieldName(), fieldObject.getValueField() + " 00:00:00");
            }

            // ЛОГИЧЕСКИЙ
            if (fieldObject.getFieldType() instanceof TypeListFieldsBoolean) {
                Assert.assertTrue( "Значение в пользовательском поле типа ЛОГИЧЕСКИЙ неверное", verifyCheckboxIsSelected(fieldObject.getValueBooleanField(), newTaskFormElementsMobile.getStateOfCheckboxInUserField(fieldObject.getFieldName())));
            }

            // НУМЕРАТОР
            if (fieldObject.getFieldType() instanceof TypeListFieldsNumerator) {
                verifyValueInInput(fieldObject.getFieldName(), fieldObject.getValueField());
            }

            // ПОДРАЗДЕЛЕНИЕ
            if (fieldObject.getFieldType() instanceof TypeListFieldsDepartment) {
                verifyValueInInput(fieldObject.getFieldName(), fieldObject.getValueField());
            }

            // СОТРУДНИК
            if (fieldObject.getFieldType() instanceof TypeListFieldsEmployee) {
                //Проверка добавленых пользователей в поле задачи
                //verifyUserInFieldOfRole(fieldObject.getValueEmployeeField(), newTaskFormElementsMobile.getInputInUserFieldTypeEmployee(fieldObject.getFieldName())); //todo существующий метод не подходит для случая, когда в поле несколько пользователей
                //Проверка выбранных пользователей в форме выбора
                openFormSelectUser(newTaskFormElementsMobile.getInputInUserFieldTypeEmployee(fieldObject.getFieldName()));
                verifyUserInFormOfRole(fieldObject.getValueEmployeeField());
            }

            // СПРАВОЧНИК
            if (fieldObject.getFieldType() instanceof TypeListFieldsDirectory) {
                verifyValueInInput(fieldObject.getFieldName(), fieldObject.getValueDirectoriesField());
            }

            // МН.СПРАВОЧНИК
            if (fieldObject.getFieldType() instanceof TypeListFieldsMultiDirectory) {
                verifyValueInInput(fieldObject.getFieldName(), fieldObject.getValueDirectoriesField());
            }
        }

        return this;
    }


    /**
     * Проверка значений в инпутах формы задачи при закрытой группе полей
     * @param valueInInput передаваемое значенние поля
     * @param nameOfElement имя элемента для xpath
     */
    TaskStepsMobile verifyValueInInputBeforeOpenGroupFields(String valueInInput, String nameOfElement) {
        if (valueInInput == null) {
            return this;
        }
        $(By.xpath("//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//input[@name='" + nameOfElement + "']")).shouldHave(exactValue(valueInInput));
        return this;

    }


    /**
     * Проверка отображения системного поля "Шаблон задачи"
     */
    TaskStepsMobile verifyFieldTemplateOfTask(){
        if ($(newTaskFormElementsMobile.getButtonCreateTask()).is(Condition.visible)) // Шаблон задачи в созданной задаче отсутствует.
        {
            $(By.xpath("//input[@name=\"id_task_template\"] ")).shouldBe(visible);
        }

        return this;
    }

    /**
     * Проверка установленного по умолчанию Типа задачи - при закрытой группе полей "Тип задачи".
     * @param taskType передаваемое значенние поля Типа задачи
     */
    TaskStepsMobile verifyTaskTypeBeforeOpenGroupFields(TasksTypes taskType) {
        verifyValueInInputBeforeOpenGroupFields(taskType.getObjectTypeName(), "id_tasktype");
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
        //Проверяем значения в полях
        $(By.xpath("//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]//span[text()='" + nameOfElement + "']/../..//input")).shouldHave(exactValue(valueInInput));
        //Проверка отсутствия лишних значений в поле
        compareInputAndValue(new ArrayList<String>(Collections.singleton(valueInInput)),newTaskFormElementsMobile.getSetInputs(nameOfElement));
        return this;
    }


    /**
     * Проверка значений в инпутах формы задачи для полей типа "Справочник",  "Мн.Справочник"
     * @param directoriesField передаваемое значенние поля
     * @param nameOfElement имя элемента для xpath
     */
    public TaskStepsMobile verifyValueInInput(String nameOfElement, DirectoriesField[] directoriesField) {
        if (directoriesField == null) {
            return this;
        }
        List<String> values = new ArrayList<String>(); // все значения, которыми были заполнены поля
        for (DirectoriesField d: directoriesField) {
            values.add(d.getDirectoriesItem());
        }

        //Проверяем значения в полях
        List<SelenideElement> allInputs = new ArrayList<>(newTaskFormElementsMobile.getSetInputs(nameOfElement));

        for (String value: values) {
            if(!(verifyValueInInputs(value, allInputs))) {
                fail("Отсутствует значение в поле справочника, которое должно отображаться: " +
                        "" + "'" + getListAbsentValues(values,allInputs) + "'");
            }
        }

        //Проверка отсутствия лишних значений в поле
        compareInputAndValue(values,newTaskFormElementsMobile.getSetInputs(nameOfElement));

        return this;
    }

    /**
     * Проверка наличия введенного значения с набором полей ввода отображаемых в данный момент форме задачи
     * @param value значение поля
     * @param inputs поле ввода
     */
    public boolean verifyValueInInputs(String value, List<SelenideElement> inputs) {
        for (SelenideElement element : inputs) {
            if (element.is(Condition.exactValue(value)) || element.is(Condition.exactText(value))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Проверка отсутствия лишних значения в нескольких инпутах полей формы задачи
     * @param values значения, которыми были заполнены поля
     * @param inputs набор инпутов для проверки в них значений полей
     */
    public void compareInputAndValue(List<String> values, ElementsCollection inputs) {
        if(inputs.isEmpty()) {
            fail("Поля ввода отсутствуют");
        }

        //Все поля ввода, кроме последнего - это поле всегда пустое
        List<SelenideElement> listInputs = new ArrayList<SelenideElement>();

        List<SelenideElement> allInputs = inputs;
        if(allInputs.size() <= 1) {
            listInputs = allInputs;
        }  else {
            SelenideElement lastInput = allInputs.get(allInputs.size()-1); //  последний инпут
            lastInput.shouldHave(empty); //  последний инпут -  это поле должно быть пустое

        } {
            //берем все инпуты, кроме последнего
            int allElements = allInputs.size();
            for (int i = 0; i < allElements; i++) {
                if (i != allElements-1) {
                    listInputs.add(allInputs.get(i));
                }
            }
        }

        //Сравнение значений в наборе полей ввода отображаемых в данный момент форме задачи со значениями полей справочника
        for (SelenideElement input : listInputs) {
            if (!(compareValueAndInput(values,input))) {
                fail("Присутствует значение несовпадающие с набором валидных значений: " +
                        "" + "'" + getListNotValidValue(values,listInputs) + "'" +
                        " exp: " + "'" + values + "'");
            }
        }
    }


    /**
     * Проверка отсутствия лишних значений в нескольких инпутах полей формы задачи
     * Сравнение значений в наборе полей ввода отображаемых в данный момент форме задачи со значениями полей справочника
     * @param values значение поля справочника
     * @param input поле ввода в "Мн.Справочнике"
     */
    public boolean compareValueAndInput(List<String> values, SelenideElement input) {
                for (String value : values) {
                    if (input.is(Condition.exactValue(value))) {
                        return true;
                    }
                }
            return false;
    }

    /**
     * Сравнение значений в наборе полей ввода отображаемых в данный момент форме задачи со значениями полей справочника
     * @param values  значение поля справочника
     * @param inputs  набор полей ввода в "Мн.Справочнике"
     * @return значения в полях несоответствующие значениям полей справочника
     */
    public List<String> getListNotValidValue(List<String> values, List<SelenideElement> inputs) {

        List<String> listNotValidValue = new ArrayList<String>();

        for (SelenideElement input : inputs) {
            if(!(compareValueAndInput(values,input))) {
            listNotValidValue.add(input.getValue());
            }
        }
        return listNotValidValue;
    }

    /**
     * Проверка присутсвия названий полей, которые должны отображаться для данного типа задачи
     * Сравнение значений набора названий полей отображаемых в данный момент в форме задачи со значениями названий полей, которые выводяться для этого типа задачи
     * @param fields набор полей для данного типа задачи
     * @param elementsOfFields элементы со значениями названий полей, которые отображаются в данный момент в форме задачи
     */
    void verifyNamesOfFields(FieldObject[] fields, ElementsCollection elementsOfFields) {
        if(fields == null) {
            return;
        }
        // Названия всех полей, которые присутствуют в типе задачи
        List<String> values = new ArrayList<String>();
        for (FieldObject field: fields) {
            values.add(field.getFieldName());
        }

        //Собираем все названия пользовательских полей
        List<SelenideElement> setInputs = new ArrayList<SelenideElement>();
        for(SelenideElement element: elementsOfFields) {
            if(!(element.is(Condition.exactText("Тип задачи")))) {
                setInputs.add(element);
            }
        }

        //Проверка
        for (String value: values) {
            if (!(verifyValueInInputs(value, elementsOfFields)))
         {
                fail("Отсутствует название поля, которое должно отображаться для данного типа задачи: " +
                        "" + "'" + getListAbsentValues(values,setInputs) + "'");
            }
        }
    }



    /**
     * Проверка отсутствия лишних названий полей среди набора полей формы задачи
     * Сравнение значений набора названий полей отображаемых в данный момент в форме задачи со значениями названий полей, которые выводяться для этого типа задачи
     * @param fields набор полей для данного типа задачи
     * @param elementsOfNamesFields элементы со значениями названий полей, которые отображаются в данный момент в форме задачи
     */
    public void checkNamesOfFields(FieldObject[] fields, ElementsCollection elementsOfNamesFields) {
        if(fields == null) {
            return;
        }
        // Названия всех полей, которые присутствуют в типе задачи
        List<String> values = new ArrayList<String>();
        for (FieldObject field: fields) {
            values.add(field.getFieldName());
        }

        //Собираем все названия пользовательских полей
        List<SelenideElement> setElementsOfNamesField = new ArrayList<SelenideElement>();
        for(SelenideElement element: elementsOfNamesFields) {
            if(!(element.is(Condition.exactText("Тип задачи")))) //Кроме названия "Тип задачи" - это системное поля
            {
                setElementsOfNamesField.add(element);
            }
        }

        //Проверка
        for (SelenideElement input : setElementsOfNamesField) {
            if (!(compareNamesOfFields(values,input))) {
                fail("Присутствует название поля несовпадающие с названиями набора полей для данного типа задачи: " +
                        "" + "'" + getListNotValidValuesInNamesOfField(values,setElementsOfNamesField) + "'" +
                        " exp: " + "'" + values + "'");
            }
        }
    }


    /**
     * Сравнение значения названия поля отображаемого в данный момент в форме задачи со значениями названий полей, которые выводяться для этого типа задачи
     * @param values набор названий полей для данного типа задачи
     * @param input элементы со значениями названий полей, которые отображаются в данный момент в форме задачи
     */
    public boolean compareNamesOfFields(List<String> values, SelenideElement input) {
        for (String value : values) {
            if (input.is(Condition.exactText(value))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Сравнение значений набора названий полей отображаемых в данный момент в форме задачи со значениями названий полей, которые выводяться для этого типа задачи
     * @param values набор названий полей для данного типа задачи
     * @param inputs элементы со значениями названий полей, которые отображаются в данный момент в форме задачи
     * @return названия полей несоответствующие названям полей, которые должны выводится для этого типа задачи
     */
    public List<String> getListNotValidValuesInNamesOfField(List<String> values, List<SelenideElement> inputs) {
        List<String> listNotValidValues = new ArrayList<String>();
        for (SelenideElement input : inputs) {
            if(!(compareNamesOfFields(values,input))) {
                listNotValidValues.add(input.getText());
            }
        }
        return listNotValidValues;
    }


    /**
     * Сравнение значений набора значений отображаемых в данный момент в форме задачи со значениями, которые должны отображаться
     * @param values набор значений, которые должны отображаться
     * @param elements элементы со значениями, которые отображаются в данный момент в форме задачи
     * @return набор значений, которые должны отображаться, но не отображаются
     */
    public List<String> getListAbsentValues(List<String> values, List<SelenideElement> elements) {
        List<String> listAbsentValues = new ArrayList<String>();
        for (String value: values) {
                if (!(verifyValueInInputs(value, elements))) {
                    listAbsentValues.add(value);
                }
        }
        return listAbsentValues;
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
                fieldCustomRole.shouldHave(exactText(employee.getLastName() + " " + employee.getName().substring(0,1) + "." + employee.getPatronymic().substring(0,1) + "."));

            }
            }
    }

    /**
     * Проверка добавленых пользователей в полях ролей задачи
     * @param employees       передаваемые пользователи
     * @param employeeDefault пользователь находящийся в поле по-умолчанию
     * @param fieldCustomRole выбираемая роль в задаче (Исполнители, Контролеры, Авторы и ОР)
     */
    private void verifyUserInFieldOfRole (Employee employeeDefault, Employee[] employees, SelenideElement fieldCustomRole) {
        if (employees != null) {
            for (Employee employee : employees) {
                fieldCustomRole.shouldHave(exactText(employeeDefault.getLastName() + " , " + employee.getLastName() + " " + employee.getName().substring(0,1) + "." + employee.getPatronymic().substring(0,1) + "."));
            }
        } else {
            fieldCustomRole.shouldHave(exactText(employeeDefault.getLastName()));
        }
    }

    /**
     * Проверка пользователей в формах добавления
     * @param employees передаваемые пользователи
     */
    private void verifyUserInFormOfRole(Employee[] employees) {
        if (employees != null) {
            for (Employee employee : employees) {
                newTaskFormElementsMobile.getSelectedUserInTheList(employee.getLastName()).shouldBe(visible);
                newTaskFormElementsMobile.getInputForSearchUsers().sendKeys(Keys.chord(Keys.ESCAPE)); //Закрыть форму
                return;
            }
        }
        //В случае, если employees == null - Проверяем, что элемент в списке не выделен. Нужно для проверки после удаления пользователя из поля раб.группы.
        newTaskFormElementsMobile.getListOfUsers().shouldBe(CollectionCondition.sizeGreaterThan(0), 5000); //ожидание когда загрузится список пользователей. todo на http://motiwtest5.test.lan у админ не может удалить сам себя
        newTaskFormElementsMobile.getSelectedUserInTheList().shouldNotBe(visible);
        newTaskFormElementsMobile.getInputForSearchUsers().sendKeys(Keys.chord(Keys.ESCAPE)); //Закрыть форму

    }


    /**
     * Проверка пользователей в формах добавления
     * @param employees   передаваемые пользователи
     * @param employeeDefault пользователь по умолчанию
     */
    private void verifyUserInFormOfRole(Employee employeeDefault, Employee[] employees) {
        if (employees != null) {
            for (Employee employee : employees) {
                newTaskFormElementsMobile.getSelectedUserInTheList(employee.getLastName()).shouldBe(visible);
                newTaskFormElementsMobile.getInputForSearchUsers().sendKeys(Keys.chord(Keys.ESCAPE)); //Закрыть форму
                return;
            }
        }
        //В случае, если employees == null - Проверяем, что пользователь по умолчанию выбран в списке. Нужно для проверки поля Автор, после удаления пользователя добавленного при создании задачи остается только Автор по умолчанию.
        newTaskFormElementsMobile.getListOfUsers().shouldBe(CollectionCondition.sizeGreaterThan(0), 5000); //ожидание когда загрузится список пользователей. todo на http://motiwtest5.test.lan у админ не может удалить сам себя
        newTaskFormElementsMobile.getSelectedUserInTheList(employeeDefault.getLastName()).shouldBe(visible);
        newTaskFormElementsMobile.getInputForSearchUsers().sendKeys(Keys.chord(Keys.ESCAPE)); //Закрыть форму

    }

    private void verifyNameOfAttachedFiles(String[] nameOfFiles) {
        if (nameOfFiles != null)
            for (String nameOfFile : nameOfFiles) {
                assertTrue(verifyNameFileInTheListFiles(nameOfFile), "Не пройдена проверка названий Файлов в списке прикрепленных файлов");

            }
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
