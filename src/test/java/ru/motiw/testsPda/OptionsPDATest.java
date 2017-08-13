package ru.motiw.testsPda;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.testng.TextReport;
import ru.motiw.data.BaseTest;
import ru.motiw.data.dataproviders.Tasks;
import ru.motiw.data.listeners.ScreenShotOnFailListener;
import ru.motiw.web.elements.elementspda.LoginStepsPDA;
import ru.motiw.web.model.Tasks.Task;
import ru.motiw.web.steps.BaseSteps;
import ru.motiw.web.elements.elementspda.InternalStepsPDA;
import ru.motiw.web.elements.elementspda.OptionsStepsPDA;
import ru.motiw.web.elements.elementspda.Task.TaskDescriptionStepsPDA;
import ru.motiw.web.elements.elementspda.Task.NewTaskStepsPDA;
import ru.motiw.web.elements.elementspda.Task.TaskActionsStepsPDA;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;


@Listeners({ScreenShotOnFailListener.class, TextReport.class})
public class OptionsPDATest extends Tasks {

    private final String fileAttach = "hello_world.txt";
    private final String fileAttachTwo = "Договор аренды.doc";
    private final String fileAttachThree = "lease_contract.doc";

    @Test(priority = 1, dataProvider = "objectDataTaskPDA", dataProviderClass = Tasks.class)
    public void verifyAttachmentAndDownloadFileInTheTask(Task task) throws Exception {
        LoginStepsPDA loginPagePDA = Selenide.open(BaseSteps.PDA_PAGE_URL, LoginStepsPDA.class);
        loginPagePDA.loginAsAdmin(BaseTest.ADMIN);
        InternalStepsPDA internalPagePDA = loginPagePDA.goToInternalMenu(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 4 (Tasks; Create Tasks; Today; Document)",
                internalPagePDA.hasMenuUserComplete());


        // Инициализируем стр. формы Настройки и переходим на нее
        OptionsStepsPDA optionsPagePDA = internalPagePDA.goToOptions();
        optionsPagePDA.selAttachFiles(true); // устанавливаем признак - возможность прикрепления файлов
        internalPagePDA.goToHome(); // уходим домой

        // Инициализируем стр. формы создание задачи и переходим на нее
        NewTaskStepsPDA newTaskPagePDA = internalPagePDA.goToCreateTask();

        //----------------------------------------------------------------ФОРМА - создания Задачи
        newTaskPagePDA.creatingTask(task);
        TaskDescriptionStepsPDA taskDescriptionPagePDA = newTaskPagePDA.goToPreview(); // Инициализируем стр. формы предпросмотра задачи и переходим на нее

        //----------------------------------------------------------------ФОРМА - Предпросмотр создания задачи

        taskDescriptionPagePDA.inputValidationFormTask(task); // Проверяем отображение значений в форме предпросмотра создания задачи

        //----------------------------------------------------------------ФОРМА - Задачи

        TaskActionsStepsPDA taskForm = taskDescriptionPagePDA.goToTask(); // Инициализируем стр. формы - Созданной задачи и переходим на нее
        taskForm.openShapeCreatedTask(task); // Открываем форму созданной задачи
        assertTrue(taskForm.resultsDisplayButtons()); // Проверяем отображения кнопок в форме задачи
        taskForm.addAttachFiles(randomString(15), fileAttach, 1); // Аттачим файлы
        taskForm.addAttachFiles(randomString(15), fileAttachTwo, 2); // Аттачим файлы 2
        taskForm.addAttachFiles(randomString(15), fileAttachThree, 1); // Аттачим файлы 2
        taskForm.downloadsFiles(fileAttachThree);
        internalPagePDA.logout(); // Выход из системы
        assertTrue(loginPagePDA.isNotLoggedInPDA());

    }


}

