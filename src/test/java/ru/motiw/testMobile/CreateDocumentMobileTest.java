package ru.motiw.testMobile;

import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.motiw.data.dataproviders.DocflowAdministrationMobile;
import ru.motiw.data.listeners.ScreenShotOnFailListener;
import ru.motiw.mobile.elements.Login.LoginPageElementsMobile;
import ru.motiw.mobile.model.Document.TypeOfExecutionPlace;
import ru.motiw.mobile.model.FilesForAttachment;
import ru.motiw.mobile.steps.Document.DocumentStepsMobile;
import ru.motiw.mobile.steps.Document.ExecutionDocumentStepsMobile;
import ru.motiw.mobile.steps.Document.VerifyDocumentStepsMobile;
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
import ru.motiw.web.model.DocflowAdministration.RouteSchemeEditor.RouteSchemeEditor;
import ru.motiw.web.model.Document.Document;
import ru.motiw.web.model.Document.ExecutionOfDocument;
import ru.motiw.web.model.Document.Resolution;
import ru.motiw.web.model.Tasks.Folder;
import ru.motiw.web.model.Tasks.Task;
import ru.motiw.web.steps.Administration.Users.UsersSteps;
import ru.motiw.web.steps.DocflowAdministration.DocumentRegistrationCards.FormDocRegisterCardsEditConnectedRoutesSteps;
import ru.motiw.web.steps.DocflowAdministration.DocumentRegistrationCards.FormDocRegisterCardsEditGeneralSteps;
import ru.motiw.web.steps.DocflowAdministration.DocumentRegistrationCards.GridDocRegisterCardsSteps;
import ru.motiw.web.steps.Home.InternalSteps;
import ru.motiw.web.steps.Login.LoginStepsSteps;
import ru.motiw.web.steps.Tasks.UnionTasksSteps;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;
import static ru.motiw.data.dataproviders.Tasks.getRandomProject;
import static ru.motiw.mobile.model.Document.TypeOperationsOfDocument.CLOSE_EXECUTION;
import static ru.motiw.web.steps.Administration.Users.DepartmentSteps.goToURLDepartments;
import static ru.motiw.web.steps.Documents.CreateDocument.NewDocumentSteps.goToURLNewDocument;
import static ru.motiw.web.steps.Tasks.UnionTasksSteps.goToUnionTasks;

@Listeners({ScreenShotOnFailListener.class, TextReport.class})
@Report
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
    private FormDocRegisterCardsEditConnectedRoutesSteps formDocRegisterCardsEditConnectedRoutesSteps;
    private FormDocRegisterCardsEditGeneralSteps formDocRegisterCardsEditGeneralSteps;
    private VerifyDocumentStepsMobile verifyDocumentStepsMobile;
    private ExecutionDocumentStepsMobile executionDocumentStepsMobile;
    private DocumentStepsMobile documentStepsMobile;

    @BeforeClass
    public void beforeTest() {
        loginPageSteps = page(LoginStepsSteps.class);
        internalPageSteps = page(InternalSteps.class);
        internalPageStepsMobile = page(InternalStepsMobile.class);
        unionTasksSteps = page(UnionTasksSteps.class);
        taskStepsMobile = page(TaskStepsMobile.class);
        loginStepsMobile = page(LoginStepsMobile.class);
        newTaskStepsMobile = page(NewTaskStepsMobile.class);
        gridOfFoldersSteps = page(GridOfFoldersSteps.class);
        taskActionsStepsMobile = page(TaskActionsStepsMobile.class);
        editOfTaskMobile = page(EditOfTaskMobile.class);
        loginPageElementsMobile = page(LoginPageElementsMobile.class);
        userPageSteps = page(UsersSteps.class);
        formDocRegisterCardsEditConnectedRoutesSteps = page(FormDocRegisterCardsEditConnectedRoutesSteps.class);
        formDocRegisterCardsEditGeneralSteps = page(FormDocRegisterCardsEditGeneralSteps.class);
        verifyDocumentStepsMobile = page(VerifyDocumentStepsMobile.class);
        executionDocumentStepsMobile = page(ExecutionDocumentStepsMobile.class);
        documentStepsMobile = page(DocumentStepsMobile.class);
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
    public void preconditionForFurtherVerification(Department[] departments, Employee[] employee,
                                                   DocRegisterCards registerCards, Document document, Folder[] folders) {
        loginPageSteps.loginAs(ADMIN);
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPageSteps.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице


        //---------------------------------------------------------------------------------Пользователи и Подразделения
        // Инициализируем страницу и переходим на нее - Администрирование/Пользователи (Подразделения)
        goToURLDepartments().createDepartment(departments[0]);

        userPageSteps.createUser(employee[0].setDepartment(departments[0]));
        userPageSteps.createUser(employee[1].setDepartment(departments[0]));
        userPageSteps.createUser(employee[2].setDepartment(departments[0]));
        userPageSteps.createUser(employee[3].setDepartment(departments[0]));

        //---------------------------------------------------------------- Задачи/Задачи
        goToUnionTasks();
        unionTasksSteps.beforeAddFolder(20);
        // Добавляем Папки(/у)
        unionTasksSteps.addFolders(new Folder[]{folders[0].setNameFolder("wD_Smart_Box " + randomString(4))
                .setUseFilter(true)
                .setFilterField("Начало")
                .setChooseRelativeValue(true)
                .setSharedFolder(true)
                .setAddSharedFolderForAll(true) // признак "Добавить всем", чтобы было попадание задачи по докумету в папку  Пользователя, которого добавляем в блок МС
                .setAddSharedFolderForNewUsers(false)});
        //---------------------------------------------------------------------------------РКД
        // Переход в раздел Администрирование ДО/Регистрационные карточки документов
        FormDocRegisterCardsEditGeneralSteps editRCD = GridDocRegisterCardsSteps
                .goToURLGridDocRegisterCards().addDocRegisterCards(); // Добавить РКД

        /**
         * Устанавливаем настройки для РКД (регистрационная карточка документа) на вкладке - МАРШРУТЫ
         */
        formDocRegisterCardsEditConnectedRoutesSteps.checkBoxUseAllPossibleRoutes(registerCards);

        formDocRegisterCardsEditGeneralSteps.addNameDocumentRegisterCards(registerCards);

        // Сохранение настроек РКД
        GridDocRegisterCardsSteps gridDocRegisterCards = editRCD.saveAllChangesInDoc(); // Сохранение всех изменений в РКД
        gridDocRegisterCards.verifyDocRegisterCards(registerCards.getDocRegisterCardsName());


        //-------------------------------------------------------------------------------Создать документ
        goToURLNewDocument().createDocument(document);



        // Выход
        internalPageSteps.logout();
        // Проверка - пользователь разлогинен
        assertTrue(loginPageSteps.isNotLoggedIn());
        clearBrowserCache(); //чистим кеш, т.к после логаута в вебе пользователь все равно остается залогинен (баг после работы в user/tab/user/uniontasks/)
    }

    @Test(priority = 2, dataProvider = "objectDataDocument", dataProviderClass = DocflowAdministrationMobile.class)
    public void verifyDocument(Department[] departments, Employee[] employee, DocRegisterCards registerCards, Document document, Folder[] folders) throws Exception {

        // Проверка карточки под разными пользователями
       verifyDocumentStepsMobile.verifyDocumentOnDifferentUsers(document, folders);

        //Выполнение действий с документом
        executionDocumentStepsMobile.executionOnDifferentUsers(document, folders[0], TypeOfExecutionPlace.DOCUMENT_CARD);

        //Комментарии на файле

    }


    @Test(priority = 3, dataProvider = "objectDataDocument", dataProviderClass = DocflowAdministrationMobile.class)
    public void verifyDocument1(Department[] departments, Employee[] employee, DocRegisterCards registerCards, Document document, Folder[] folders) throws Exception {

        Folder[] folder1 = new Folder[]{
                new Folder()
                        .setNameFolder("wD_Smart_Box бБЮо") // Зн-ие НЕ изменять - используется в проверке - checkDisplayCreateAFolderInTheGrid()
        };



        Employee[] qqq = new Employee[]{
                new Employee()
                        .setName("qqq")
                        .setLoginName("qqq")
                        .setLastName("qqq")
                        .setPassword("qqq")};


        Employee[] qqq1 = new Employee[]{
                new Employee()
                        .setName("qqq")
                        .setLoginName("SчeыbЦnhоz")
                        .setLastName("qqq")
                        .setPassword("123")};


        // Инициализация объекта - Названия Файлов задачи
        String[] file = new String[]{
                FilesForAttachment.FILE_1.getNameFile(),
                FilesForAttachment.FILE_2.getNameFile(),
                FilesForAttachment.FILE_3.getNameFile(),
        };


        //----------------------------------------------------------------------------------------------------------- Инициализация Документа
        Document document1 = new Document()

                .setDocumentType(new DocRegisterCards("wD_Тестовая карточка ъ2mыЦоe5yeяУХ2e3xе9Ы")) // Тип документа
                .setAuthorOfDocument(ADMIN)
                .setDateRegistration(randomDateTime()) // Дата регистрации
                .setProject(getRandomProject()) // Инициализируем проект документа
                //.setValueFiles(new String[]{file[0], file[1]})
                .setRouteSchemeForDocument(new RouteSchemeEditor()
                        .setRouteScheme("Согласование входящей корреспонденции - Постановка задачи")
                        .setReviewDuration(randomInt(999))
                        .setUserRoute(new Employee[]{
                                new Employee()
                                        .setName("qqq")
                                        .setLastName("qqq")
                                        .setLoginName("qqq")
                                        .setPassword("qqq")})
                        // Добавляем в маршрут созданного пользователя
                )
                .setExecutionOfDocument(new ExecutionOfDocument[]
                        {

//                                new ExecutionOfDocument()
//                                        .setExecutionOperation(1, ADMIN, CREATE_RESOLUTION),
                                new ExecutionOfDocument()
                                        .setExecutionOperation(2, qqq[0], CLOSE_EXECUTION),

                        })
                .setResolutionOfDocument(new Task[]{
                        new Resolution()
                                .setTextOfResolution(randomString(21))
                                .setAuthorDefault(EMPLOYEE_ADMIN)
                                .setExecutiveManagers(qqq)
                        ,
                        new Resolution()
                                .setTextOfResolution(randomString(21))
                                .setAuthorDefault(EMPLOYEE_ADMIN)
                                .setExecutiveManagers(qqq1)
                })
                .setOnExecution(true)
                ;


        // Проверка карточки под разными пользователями
        //verifyDocumentStepsMobile.verifyDocumentOnDifferentUsers(document1, folder1);
        // Выполнение действий с документом под разными пользователями
        //1.Выполнение операций
        //2. Комментарии на файле
       // executionDocumentStepsMobile.executionOnDifferentUsers(document1, folder1[0], TypeOfExecutionPlace.CONTEXT_MENU_IN_THE_GRID_FOLDER);


//        loginStepsMobile
//                .loginAs(ADMIN) // Авторизация под участником рассмотрения документа
//                .waitLoadMainPage(); // Ожидание открытия главной страницы
   //     gridOfFoldersSteps.openFolder(folder1[0]);
       // gridOfFoldersSteps.openItemInGrid("Входящая корреспонденция", folder1[0]);

        // Ожидание и проверка кнопок тулбара
     //   $(By.xpath("//div[contains(@class,\"x-toolbar x-container x-component x-noborder-trbl x-toolbar-side-toolbar\")]")).waitUntil(Condition.visible, 15000);



        //gridOfFoldersSteps.clickContextMenuForItemInGrid("Входящая корреспонденция");




        //----------------------------------------------------------------ГРИД - Папка

        // Проверка карточки под разными пользователями
        verifyDocumentStepsMobile.verifyDocumentOnDifferentUsers(document1, folder1);

        //Выполнение действий с документом
     //   executionDocumentStepsMobile.executionOnDifferentUsers(document1, folder1[0], TypeOfExecutionPlace.DOCUMENT_CARD);


    }


}
