package ru.motiw.mobile.steps.Tasks;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.motiw.mobile.elements.Tasks.TaskElementsMobile;
import ru.motiw.web.model.Tasks.Task;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static junit.framework.TestCase.assertTrue;
import static ru.motiw.utils.WindowsUtil.newWindowForm;

public class TaskStepsMobile extends NewTaskStepsMobile {

    private TaskElementsMobile taskElementsMobile = page(TaskElementsMobile.class);

    /**
     * Проверка введенных данный в предпросмотре формы создания задачи
     *
     * @param task return values of attributes of the task
     * @return TaskDescriptionStepsPDA page
     */
    public  TaskStepsMobile inputValidationFormTask(Task task) {
        $(By.xpath("//div[text()='" + task.getTaskName() + "']"))
                .shouldBe(visible); // Название задачи
        /*
        $(By.xpath("//form[@id='data_value']//li[3]//span[@style][text()='" + task.getDescription() + "']"))
                .shouldBe(visible); // Описание задачи
        $(By.xpath("//form[@id='data_value']//li[9]//span[@style][contains(text(),'" + task.getDateEnd() + "')]"))
                .shouldBe(visible); // Окончание задачи*/
        return this;
    }



    /**
     * Проверка значений в поле "Приоритет"
     * Сейчас задачу создаем всегда с установкой приоритета Важная задача - true
     * Но можно проверять и с рандомным значением.
     * т.к установка признака и его проверка завязаны на одно значение true/false в valueTask.getIsImportant()
     */


    private boolean verifyIsImportant (boolean isImportant){
        if (isImportant) {
            $(By.xpath("//div[contains(@id,\"object\")]//input[@name=\"priority\"]")).shouldHave(value("Важная задача"));
            return true;
        } else {
            $(By.xpath("//div[contains(@id,\"object\")]//input[@name=\"priority\"]")).shouldHave(value("Обычная задача"));
            return true;
        }
    }


    /**
     * Проверка значений в чебоксах
     * Сейчас задачу создаем всегда с установкой приоритета Важная задача - true
     * Но можно проверять и с рандомным значением.
     * т.к установка признака и его проверка завязаны на одно значение true/false в valueTask.getIsImportant()
     */



    /**
     * Проверка значений в чебоксах true/false для последующего сравнения с передаваемым зеачением
     */

    private boolean verifyCheckboxIsSelected (boolean isTrue, SelenideElement inputCheckbox) {
        if (isTrue) {
            inputCheckbox.shouldBe(selected);
            return true;
        } else {
            inputCheckbox.shouldNotBe(selected);
            return false;
        }

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
        $(By.xpath("//div[@class=\"x-component x-title x-title-align-left x-layout-box-item x-layout-hbox-item x-flexed\"]/div[text()='" + valueTask.getTaskName() + "']"))
                .shouldBe(visible); // Название задачи в хедере



        //Переходим на вкладку "Описание"

        $(By.xpath("//div[text()=\"Описание\"]//ancestor::div[contains(@class,\"x-component x-button x-icon-align-top x-widthed x-has-icon\")]")).click();
        verifyElementsOnDescriptionPage();// Ожидание и проверка элементов на вкладке "Описание"

        //Проверка значений в полях
        //TODO наверное ещё нужно проверять после раскрытия группы полей "Название"

        $(By.xpath("//div[contains(@id,\"object\")]//input[@name=\"taskname\"]")).should(value(valueTask.getTaskName())); // input не содержит текста в DOM, но через value получилось.



        $(By.xpath("//div[contains(@id,\"object\")]//div[@name=\"description\"]"))
                .shouldHave(value(valueTask.getDescription())); // Описание задачи


        /*
         * Проверка даты окончания задачи
         */

        $(By.xpath("//div[contains(@id,\"object\")]//input[@name=\"enddate\"]"))
                .shouldHave(value(valueTask.getDateEnd()));


        /*
         * Открываем вкладку "Срок"
         * Проверка даты начала и окончания задачи и приоритета
         */

        $(By.xpath("//div[contains(text(),'Срок')]//ancestor::div[contains(@class,\"x-unselectable x-paneltitle x-component\")]")).click();  // Открываем вкладку "Срок"
        //TODO Проверка на то, что вкладка открылась и все поля отображаются. т.к значения считаывются через DOM сразу даже без отрытия вкладки.

        /*
        $(By.xpath("//div[contains(@id,\"object\")]//input[@name=\"startdate\"]"))
                .shouldHave(value(valueTask.getDateBegin())); //проверка даты, если заполнять её при создании задачи - см. в Tasks objectDataTaskPDA .setDateBegin(yesterdayDate())
                */
        // TODO подумать над необходимостью добавления проверки того, что дата по умолчанию подставилась верная. Раньше в web не было

        $(By.xpath("//div[contains(@id,\"object\")]//input[@name=\"enddate\"]"))
                .shouldHave(value(valueTask.getDateEnd()));

        assertTrue(verifyIsImportant(valueTask.getIsImportant()));

        $(By.xpath("//div[contains(text(),'Срок')]//ancestor::div[contains(@class,\"x-unselectable x-paneltitle x-component\")]")).click();  // Закрываем вкладку "Срок"
        //TODO Проверка на то, что вкладка закрылась и все поля не отображаются. т.к значения считаывются через DOM сразу даже без отрытия вкладки.




        /*
         * Открываем вкладку "Ещё"
         * Проверка признаков в чекбоксах
         */


        $(By.xpath("//div[contains(text(),'Еще')]//ancestor::div[contains(@class,\"x-unselectable x-paneltitle x-component\")]")).click();  // Открываем вкладку "Еще"
        //TODO Проверка на то, что вкладка открылась и все поля отображаются. т.к значения считаывются через DOM сразу даже без отрытия вкладки.

        newTaskFormElementsMobile.getReportRequired().shouldBe(selected); // Признак - С Докладом всегда по умолчанию должен быть выбран.
        assertTrue(verifyCheckboxIsSelected(valueTask.getIsWithReport(), newTaskFormElementsMobile.getReportRequired()));
        newTaskFormElementsMobile.getIsForExamination().shouldBe(disabled); // Признак -  "Для ознакомления" в созданной задаче должен быть задизейблен.





        $(By.xpath("//div[contains(text(),'Еще')]//ancestor::div[contains(@class,\"x-unselectable x-paneltitle x-component\")]")).click();  // Открываем вкладку "Еще"
        //TODO Проверка на то, что вкладка закрылась и все поля не отображаются. т.к значения считаывются через DOM сразу даже без отрытия вкладки.









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
