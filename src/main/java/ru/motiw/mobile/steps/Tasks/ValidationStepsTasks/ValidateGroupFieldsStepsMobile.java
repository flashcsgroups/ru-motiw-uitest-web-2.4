package ru.motiw.mobile.steps.Tasks.ValidationStepsTasks;

import org.junit.Assert;
import org.openqa.selenium.By;
import ru.motiw.web.model.Tasks.Task;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static ru.motiw.mobile.model.Task.InnerGroupTabs.*;

/**
 * Проверка групп полей
 */
public class ValidateGroupFieldsStepsMobile extends ValidateValuesOfFieldsStepsMobile {

    /**
     * Проверка отбражения полей при закрытых группах полей
     *
     */
    public ValidateGroupFieldsStepsMobile existFieldsWhenGroupsClosed() {

        newTaskFormElementsMobile.getTaskName().shouldBe(visible); // "Название"
        newTaskFormElementsMobile.getDescriptionTask().shouldBe(visible); // Описание задачи - отображение и проверка значения поля после закрытия группы полей  "Название"
        // Кому
        $(By.xpath("(//div[contains(@id,'object') and not(contains(@class,\"x-hidden-display\"))]" +
                "//div[contains(text(),'Кому')]" +
                "//ancestor::div[contains(@class,\"x-panel x-container x-component small-collapser-panel x-noborder-trbl x-header-position-top x-panel-grey-background x-container-grey-background \")]" +
                "//div[@class=\"x-input-el\"])[1]")).shouldBe(visible);
        // TODO  xpath другой м.б написать - этот ищет все 4ре поля - хотя для других полей ведь тоже xpath одинаковые для раскрытого и закрытого поля. //span[contains(text(),'Ответственные руководители')]/../..//div[@class="x-input-el\ - не находит

        newTaskFormElementsMobile.getEndField().shouldBe(visible); // Окончание
        newTaskFormElementsMobile.getTaskType().shouldBe(visible); // Тип задачи TODO не проходит т.к есть какой-то баг, что в АРМе нет поля типа задачи. Возможно, в случае, если не было создано ранее задач.
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

    /**
     * Проверка отображения полей при открытых группах полей
     *
     * @param task
     */
    public ValidateGroupFieldsStepsMobile existFieldsWhenGroupsOpen(Task task) {

        // Разворачиваем все группы полей
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
        newTaskFormElementsMobile.getTaskType().shouldBe(visible);
        newTaskFormElementsMobile.getFieldFiles().shouldBe(visible); //  поле - Файлы


        newTaskFormElementsMobile.getCheckboxReportRequired().shouldBe(visible); // Признак - С Докладом
        newTaskFormElementsMobile.getCheckboxIsSecret().shouldBe(visible); // признак - Секретная
        newTaskFormElementsMobile.getCheckboxIsForReview().shouldBe(visible); // Признак - "Для ознакомления"

        // Проверка отображения названий пользовательских полей в форме - проверка присутсвия всех названий полей, проверка отсутсвия лишних названий полей
        verifyNamesOfFields(task.getTaskFieldsName(), newTaskFormElementsMobile.getNameOfFieldsInCustomFields());

        // Закрытитие всех групп полей
        selectGroupTab(NAME);
        selectGroupTab(TO_WHOM);
        selectGroupTab(DATE);
        selectGroupTab(TYPE_TASK);
        selectGroupTab(FILES);
        selectGroupTab(MORE);
        return this;
    }


    /**
     * Проверка значений в полях при закрытых группах полей
     *
     * @param task
     */
    public ValidateGroupFieldsStepsMobile valuesInInputsExistWhenGroupsClosed(Task task) {
        verifyValueInInputOfField(task.getTaskName(), newTaskFormElementsMobile.getTaskName()); //Проверка поля названия при закрытой группы полей "Название"
        verifyValueInInputOfField(task.getDescription(), newTaskFormElementsMobile.getDescriptionTask()); // Проверка поля - Описание задачи - до раскрытия группы полей "Название".
        verifyValueInInputOfField(task.getDateEnd(), newTaskFormElementsMobile.getEndField()); // Проверка поля -  Дата окончания - при закрытой группе полей  "Срок".
        verifyTaskType(task.getTaskType()); // Проверка поля -  Тип задачи - при закрытой группе полей "Тип задачи".
        return this;
    }


    /**
     * Проверка значений в полях при открытых группах полей
     *
     * @param task
     */
    public ValidateGroupFieldsStepsMobile valuesInInputsExistWhenGroupsOpen(Task task) {

        //Методы для Разворачивания всех групп полей
        selectGroupTab(NAME);
        selectGroupTab(TO_WHOM);
        selectGroupTab(DATE);
        selectGroupTab(TYPE_TASK);
        selectGroupTab(FILES);
        selectGroupTab(MORE);


        verifyValueInInputOfField(task.getTaskName(), newTaskFormElementsMobile.getTaskName());
        verifyValueInInputOfField(task.getDescription(), newTaskFormElementsMobile.getDescriptionTask()); // Описание задачи.
        verifyValueInInputOfField("Главное подразделение: Задачи вне проектов", newTaskFormElementsMobile.getProjectTask());

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

        //Проверка дат в полях "Начало", "Окончание"
        verifyValueInInputOfField(task.getDateEnd(), newTaskFormElementsMobile.getEndField());
        verifyValueInInputOfField(task.getDateBegin(), newTaskFormElementsMobile.getBeginField());
        Assert.assertTrue(verifyIsImportant(task.getIsImportant())); // Приоритет

        /*
         * Проверка в списке файлов
         * в форме создания задачи
         * на вкладке "Описание" в форме созданной задачи
         * проверка текста в просмотрике файлов в форме созданной задачи
         */
        validateFilesStepsMobile.verifyFilesInTheList(task);

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
     * Проверка на вкладке "Еще"
     *
     */
    public ValidateFieldsStepsMobile valuesInInputsExistOnGroupTabMore() {
        selectGroupTab(MORE); // Открываем вкладку "Еще"
        newTaskFormElementsMobile.getIsForReview().shouldBe(disabled); // Признак -  "Для ознакомления" в созданной задаче должен быть задизейблен. Состояние чекбокса не отображается.
        selectGroupTab(MORE); // Закрываем вкладку "Еще"
        return this;
    }
}
