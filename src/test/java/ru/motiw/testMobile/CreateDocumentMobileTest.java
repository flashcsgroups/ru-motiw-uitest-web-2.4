package ru.motiw.testMobile;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.motiw.data.dataproviders.DocflowAdministrationMobile;
import ru.motiw.mobile.elements.Login.LoginPageElementsMobile;
import ru.motiw.mobile.steps.Folders.GridOfFoldersSteps;
import ru.motiw.mobile.steps.InternalStepsMobile;
import ru.motiw.mobile.steps.LoginStepsMobile;
import ru.motiw.mobile.steps.Tasks.EditOfTaskMobile;
import ru.motiw.mobile.steps.Tasks.NewTaskStepsMobile;
import ru.motiw.mobile.steps.Tasks.TaskActionsStepsMobile;
import ru.motiw.mobile.steps.Tasks.TaskStepsMobile;
import ru.motiw.web.model.Administration.Users.Department;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards.DocRegisterCards;
import ru.motiw.web.model.Document.Document;
import ru.motiw.web.model.Tasks.Folder;
import ru.motiw.web.steps.Administration.Users.UsersSteps;
import ru.motiw.web.steps.DocflowAdministration.DocumentRegistrationCards.FormDocRegisterCardsEditGeneralSteps;
import ru.motiw.web.steps.DocflowAdministration.DocumentRegistrationCards.GridDocRegisterCardsSteps;
import ru.motiw.web.steps.Home.InternalSteps;
import ru.motiw.web.steps.Login.LoginStepsSteps;
import ru.motiw.web.steps.Tasks.UnionTasksSteps;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;
import static ru.motiw.web.steps.Administration.Users.DepartmentSteps.goToURLDepartments;
import static ru.motiw.web.steps.DocflowAdministration.DocumentRegistrationCards.FormDocRegisterCardsEditConnectedRoutesSteps.routesTabRCD;
import static ru.motiw.web.steps.DocflowAdministration.DocumentRegistrationCards.FormDocRegisterCardsEditGeneralSteps.generalTabRCD;
import static ru.motiw.web.steps.Documents.CreateDocument.NewDocumentSteps.goToURLNewDocument;
import static ru.motiw.web.steps.Tasks.UnionTasksSteps.goToUnionTasks;

public class CreateDocumentMobileTest extends DocflowAdministrationMobile {


    private LoginStepsSteps loginPageSteps;
    private InternalSteps internalPageSteps;
    private InternalStepsMobile internalPageStepsMobile;
    private UnionTasksSteps unionTasksSteps;
    private TaskStepsMobile taskStepsMobile;
    private LoginStepsMobile loginStepsMobile;
    private NewTaskStepsMobile newTaskStepsMobile;
    private GridOfFoldersSteps gridOfFoldersSteps;
    private TaskActionsStepsMobile taskActionsStepsMobile;
    private EditOfTaskMobile editOfTaskMobile;
    private LoginPageElementsMobile loginPageElementsMobile;
    private UsersSteps userPageSteps;


    @BeforeClass
    public void beforeTest() {
        loginPageSteps = page(LoginStepsSteps.class);
        internalPageSteps = page(InternalSteps.class);
        internalPageStepsMobile = page(InternalStepsMobile.class);
        unionTasksSteps = page(UnionTasksSteps.class);
        taskStepsMobile =page(TaskStepsMobile.class);
        loginStepsMobile = page(LoginStepsMobile.class);
        newTaskStepsMobile = page(NewTaskStepsMobile.class);
        gridOfFoldersSteps = page(GridOfFoldersSteps.class);
        taskActionsStepsMobile = page(TaskActionsStepsMobile.class);
        editOfTaskMobile = page(EditOfTaskMobile.class);
        loginPageElementsMobile = page(LoginPageElementsMobile.class);
        userPageSteps = page(UsersSteps.class);

    }
//
//    предусловия
//
//    создать папку (с признаком добавить всем? чтобы было  попадание задачи по докумету в папку  Пользователя, которого добавляем в блок МС)
//    setAddSharedFolderForAll
//
//    потом все как в  verificationDocumentCreation
//    Создать документ в вебе  - сейчас МС Согласование входящей корреспонденции - Постановка задачи
//
//            (если нужно заменить)
//
//
//- попадание задачи по докумету в папку  Пользоввателя, которого добавляем в блок МС
//
//    АРМ:
//    переход в папку
//    переход в документ
//    проверка карточки: кнопки операций , файл

    @Test(priority = 1, dataProvider = "objectDataDocument", dataProviderClass = DocflowAdministrationMobile.class)
    public void aPreconditionForFurtherVerification(Department[] departments, Employee[] employee,
                                                    DocRegisterCards registerCards, Document document, Folder[] folders) {

        loginPageSteps.loginAs(ADMIN);
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPageSteps.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице


        //---------------------------------------------------------------------------------Пользователи и Подразделения
        // Инициализируем страницу и переходим на нее - Администрирование/Пользователи (Подразделения)
        goToURLDepartments().createDepartment(departments[0]);

        userPageSteps.createUser(employee[0].setDepartment(departments[0]));

        //---------------------------------------------------------------------------------РКД
        // Переход в раздел Администрирование ДО/Регистрационные карточки документов
        FormDocRegisterCardsEditGeneralSteps editRCD = GridDocRegisterCardsSteps
                .goToURLGridDocRegisterCards().addDocRegisterCards(); // Добавить РКД

        /**
         * Устанавливаем настройки для РКД (регистрационная карточка документа) на вкладке - МАРШРУТЫ
         */
        routesTabRCD().checkBoxUseAllPossibleRoutes(registerCards);

        generalTabRCD().addNameDocumentRegisterCards(registerCards);

        // Сохранение настроек РКД
        GridDocRegisterCardsSteps gridDocRegisterCards = editRCD.saveAllChangesInDoc(); // Сохранение всех изменений в РКД
        gridDocRegisterCards.verifyDocRegisterCards(registerCards.getDocRegisterCardsName());


        //-------------------------------------------------------------------------------Создать документ
        goToURLNewDocument().createDocument(document);



        //---------------------------------------------------------------- Задачи/Задачи
        goToUnionTasks();
        unionTasksSteps.beforeAddFolder(20);
        // Добавляем Папки(/у)
        unionTasksSteps.addFolders(new Folder[]{folders[0].setNameFolder("wD_Smart_Box " + randomString(4))
                .setUseFilter(true)
                .setFilterField("Начало")
                .setChooseRelativeValue(true)
                .setSharedFolder(false)
                .setAddSharedFolderForAll(true) // признак "Добавить всем", чтобы было попадание задачи по докумету в папку  Пользователя, которого добавляем в блок МС
                .setAddSharedFolderForNewUsers(false)});

        // Выход
        internalPageSteps.logout();
        // Проверка - пользователь разлогинен
        assertTrue(loginPageSteps.isNotLoggedIn());
        clearBrowserCache(); //чистим кеш, т.к после логаута в вебе пользователь все равно остается залогинен (баг после работы в user/tab/user/uniontasks/)
    }

    @Test(priority = 2, dataProvider = "objectDataDocument", dataProviderClass = DocflowAdministrationMobile.class)
    public void verifyDocument(Department[] departments, Employee[] employee, DocRegisterCards registerCards, Document document, Folder[] folders) {

        //Переход в мобильную версию по ссылке в форме авторизации
        $(By.xpath("//a[@class=\"m_link\"]")).waitUntil(visible, 10000);
        $(By.xpath("//a[@class=\"m_link\"]")).click();

        loginPageElementsMobile.getLogin().waitUntil(Condition.visible, 20000);
        // Авторизация под пользователем, который рассматривает документ
        loginStepsMobile.loginAs(employee[0]);
        // Ожидание появления маски загрузки
        loginPageElementsMobile.getMaskOfLoading().waitUntil(Condition.appears, 2000);
        // Ожидание скрытия маски загрузки
        loginPageElementsMobile.getMaskOfLoading().waitUntil(Condition.disappear, 10000);
        // Ожидание кнопки Главного Меню
        $(By.xpath("//div[@class=\"x-component x-button no-blue-alt x-has-icon x-icon-align-left x-arrow-align-right x-button-alt x-component-alt x-layout-box-item x-layout-hbox-item\"][1]")).waitUntil(Condition.visible, 10000);

        internalPageStepsMobile.goToInternalMenu(); // Открываем главное меню
        assertThat("Check that the displayed menu item 9 (User Info; Tasks And Documents; Create Tasks; Today; Search; Settings; Help; Exit; Go To Full Version)",
                internalPageStepsMobile.hasMenuUserComplete());

        //----------------------------------------------------------------ГРИД - Папка
        sleep(500); //ожидание папок;
        // Проверяем отображение созданной задачи в гриде папки
        //gridOfFoldersSteps.checkDisplayTaskGrid(task, folders[0]);
        //Переход в задачу из грида



        //----------------------------------------------------------------ФОРМА -  Документ


    }




}
