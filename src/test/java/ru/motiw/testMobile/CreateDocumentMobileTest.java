package ru.motiw.testMobile;

import com.codeborne.selenide.testng.ScreenShooter;
import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.motiw.data.dataproviders.DocflowAdministrationMobile;
import ru.motiw.mobile.model.Document.TypeOfLocation;
import ru.motiw.mobile.model.FilesForAttachment;
import ru.motiw.mobile.steps.AnnotationOnFilesSteps;
import ru.motiw.mobile.steps.Document.DocumentStepsMobile;
import ru.motiw.mobile.steps.Document.ExecutionDocumentStepsMobile;
import ru.motiw.mobile.steps.Document.VerifyDocumentStepsMobile;
import ru.motiw.mobile.steps.Folders.GridOfFoldersSteps;
import ru.motiw.mobile.steps.InternalStepsMobile;
import ru.motiw.mobile.steps.LoginStepsMobile;
import ru.motiw.web.model.Administration.Users.Department;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards.DocRegisterCards;
import ru.motiw.web.model.DocflowAdministration.RouteSchemeEditor.RouteSchemeEditor;
import ru.motiw.web.model.Document.Document;
import ru.motiw.web.model.Document.ExecutionOfDocument;
import ru.motiw.web.model.Document.Resolution;
import ru.motiw.web.model.Tasks.Folder;
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

@Listeners({ScreenShooter.class, TextReport.class})
@Report
public class CreateDocumentMobileTest extends DocflowAdministrationMobile {

    private LoginStepsSteps loginPageSteps;
    private LoginStepsMobile loginStepsMobile;
    private InternalStepsMobile internalStepsMobile;
    private GridOfFoldersSteps gridOfFoldersSteps;
    private InternalSteps internalPageSteps;
    private UnionTasksSteps unionTasksSteps;
    private UsersSteps userPageSteps;
    private FormDocRegisterCardsEditConnectedRoutesSteps formDocRegisterCardsEditConnectedRoutesSteps;
    private FormDocRegisterCardsEditGeneralSteps formDocRegisterCardsEditGeneralSteps;
    private VerifyDocumentStepsMobile verifyDocumentStepsMobile;
    private ExecutionDocumentStepsMobile executionDocumentStepsMobile;
    private DocumentStepsMobile documentStepsMobile;
    private AnnotationOnFilesSteps annotationOnFilesSteps;

    @BeforeClass
    public void beforeTest() {
        loginPageSteps = page(LoginStepsSteps.class);
        loginStepsMobile = page(LoginStepsMobile.class);
        internalStepsMobile = page(InternalStepsMobile.class);
        gridOfFoldersSteps = page(GridOfFoldersSteps.class);
        internalPageSteps = page(InternalSteps.class);
        unionTasksSteps = page(UnionTasksSteps.class);
        userPageSteps = page(UsersSteps.class);
        formDocRegisterCardsEditConnectedRoutesSteps = page(FormDocRegisterCardsEditConnectedRoutesSteps.class);
        formDocRegisterCardsEditGeneralSteps = page(FormDocRegisterCardsEditGeneralSteps.class);
        verifyDocumentStepsMobile = page(VerifyDocumentStepsMobile.class);
        executionDocumentStepsMobile = page(ExecutionDocumentStepsMobile.class);
        documentStepsMobile = page(DocumentStepsMobile.class);
        annotationOnFilesSteps = page(AnnotationOnFilesSteps.class);
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

    @Test(dataProvider = "objectDataForVerifyingCreateDocument", dataProviderClass = DocflowAdministrationMobile.class)
    public void preconditionInWeb(Department[] departments, Employee[] employee,
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

    @Test(dataProvider = "objectDataForVerifyingCreateDocument", dataProviderClass = DocflowAdministrationMobile.class, dependsOnMethods = "preconditionInWeb")
    public void verifyDocument(Department[] departments, Employee[] employee, DocRegisterCards registerCards, Document document, Folder[] folders) throws Exception {
        // Проверка карточки под разными пользователями
        verifyDocumentStepsMobile.verifyDocumentOnDifferentUsers(document, folders);
        //Выполнение действий с документом
        executionDocumentStepsMobile.executionOnDifferentUsers(document, folders[0], TypeOfLocation.PAGE_CARD);
    }

//    @Test(priority = 4, dataProvider = "objectDataForVerifyingCreateDocument", dataProviderClass = DocflowAdministrationMobile.class)
//    public void verifyCommentOnFileInDocument(Department[] departments, Employee[] employee, DocRegisterCards registerCards, Document document, Folder[] folders) throws Exception {
//
//
//        // todo вынести отдельно с созданием предуловий - нового документа т.к во  тут документ будет в архиве в предыдуших тестах. Каждую проверку в отдельный @test
//
//        Folder[] folder1 = new Folder[]{
//                new Folder()
//                        .setNameFolder("дог") // Зн-ие НЕ изменять - используется в проверке - checkDisplayCreateAFolderInTheGrid()
//        };
//
//
//
//        loginStepsMobile
//                .loginAs(ADMIN) // Авторизация под участником рассмотрения документа
//                .waitLoadMainPage(); // Ожидание открытия главной страницы
//        gridOfFoldersSteps.openFolder(folder1[0]);
//        gridOfFoldersSteps.openItemInGrid("Проект документа № 4, 123123", folder1[0]);
//
//        //  Ожидание и проверка кнопок тулбара
//        $(By.xpath("//div[contains(@class,\"x-toolbar x-container x-component x-noborder-trbl x-toolbar-side-toolbar\")]")).waitUntil(Condition.visible, 15000);
//
//
////
////        loginStepsMobile
////                .loginAs(ADMIN) // Авторизация под участником рассмотрения документа TODO ПОД КЕМ? - - Пользователь-А
////                .waitLoadMainPage(); // Ожидание открытия главной страницы
////        gridOfFoldersSteps.openFolder(folders[0]);
////        //----------------------------------------------------------------ГРИД - Папка
////        gridOfFoldersSteps.validateThatInGrid().itemDisplayed(document.getDocumentType().getDocRegisterCardsName(), folders[0]); // todo какая папка?
////
////        gridOfFoldersSteps.openItemInGrid(document.getDocumentType().getDocRegisterCardsName(), folders[0]);
//
//        //----------------------------------------------------------------ФОРМА - Документ
//        //Комментарии на файле
//        annotationOnFilesSteps
//                .addCommentOfPenOnFile()
//                .addCommentOfMarkerOnFile()
//                .validateThat().annotationPenAndMarkerOnPdfExist();
//
//        // Проверяем отображение граф.комментария после перезагрузки страницы
//        refresh();
//        //  Ожидание и проверка кнопок тулбара
//        $(By.xpath("//div[contains(@class,\"x-toolbar x-container x-component x-noborder-trbl x-toolbar-side-toolbar\")]")).waitUntil(Condition.visible, 15000);
//        annotationOnFilesSteps
//                .validateThat().annotationPenAndMarkerOnPdfExist();
//        // Выход из системы
//        internalStepsMobile.logout();
//
//        // ------------------ Проверка под участником документа, не Автор-1 граф.комментария - Пользователь-Б
//        loginStepsMobile
//                .loginAs(ADMIN) // Авторизация под участником рассмотрения документа
//                .waitLoadMainPage(); // Ожидание открытия главной страницы
//        gridOfFoldersSteps.openFolder(folder1[0]);
//        gridOfFoldersSteps.openItemInGrid("Проект документа № 4, 123123", folder1[0]);
//
//        //  Ожидание и проверка кнопок тулбара
//        $(By.xpath("//div[contains(@class,\"x-toolbar x-container x-component x-noborder-trbl x-toolbar-side-toolbar\")]")).waitUntil(Condition.visible, 15000);
//
//        //Проверяем отображение граф.комментария
//        annotationOnFilesSteps.validateThat()
//                .annotationControlsToolbarAppears() // для ожидания
//                .annotationPenAndMarkerOnPdfExist();
//
//        // Включение/выключения комменатрия Автора-1 граф.комментария
//        //todo
//
//        // Пробуем удалить граф.комментарий Автора-1
//        // todo в отдельный тест такую негативную проверку? - думаю, что в отдельный тест каждую провекру, чтобы были короткие тесты, только авторизацию сделать только в пером тесте, а остальные сделать зависимыми от него.
//
//
//        // Добавляем комментарий под Автор-2 граф.комментария
//        annotationOnFilesSteps.addCommentOfPenOnFile()
//                .validateThat().annotationPenExist(); // todo дополнительно добавить проверку скриншота со комментарием второго пользователя
//        // Проверяем отображение граф.комментария после перезагрузки страницы
//        refresh();
//        //  Ожидание и проверка кнопок тулбара
//        $(By.xpath("//div[contains(@class,\"x-toolbar x-container x-component x-noborder-trbl x-toolbar-side-toolbar\")]")).waitUntil(Condition.visible, 15000);
//        annotationOnFilesSteps
//                .validateThat().annotationPenExist();
//        // Выход из системы
//        internalStepsMobile.logout();
//
//
//
//        // ------------------ Проверка Удаления граф.комментария
//        loginStepsMobile
//                .loginAs(ADMIN) // Авторизация под участником рассмотрения документа
//                .waitLoadMainPage(); // Ожидание открытия главной страницы
//        gridOfFoldersSteps.openFolder(folder1[0]);
//        gridOfFoldersSteps.openItemInGrid("Проект документа № 4, 123123", folder1[0]);
//
//        //  Ожидание и проверка кнопок тулбара
//        $(By.xpath("//div[contains(@class,\"x-toolbar x-container x-component x-noborder-trbl x-toolbar-side-toolbar\")]")).waitUntil(Condition.visible, 15000);
//        annotationOnFilesSteps.eraseAnnotationOnFile()
//                .validateThat().annotationOnPdfNotExist();
//
//       // Проверяем отображение граф.комментария после перезагрузки страницы
//        refresh();
//        //  Ожидание и проверка кнопок тулбара
//        $(By.xpath("//div[contains(@class,\"x-toolbar x-container x-component x-noborder-trbl x-toolbar-side-toolbar\")]")).waitUntil(Condition.visible, 15000);
//        annotationOnFilesSteps
//                .validateThat().annotationPenAndMarkerOnPdfExist();
//        // Выход из системы
//        internalStepsMobile.logout();
//    }


    @Test(priority = 3, dataProvider = "objectDataForVerifyingCreateDocument", dataProviderClass = DocflowAdministrationMobile.class)
    public void verifyDocument1(Department[] departments, Employee[] employee, DocRegisterCards registerCards, Document document, Folder[] folders) throws Exception {

        Folder[] folder1 = new Folder[]{
                new Folder()
                        .setNameFolder("wD_Smart_Box IHЭG") // Зн-ие НЕ изменять - используется в проверке - checkDisplayCreateAFolderInTheGrid()
        };


        Employee e1 = new Employee()
                .setName("qqq")
                .setLoginName("qqq")
                .setLastName("qqq")
                .setPassword("qqq");

        Employee[] qqq = new Employee[]{e1, new Employee().setName("11")};
        Employee[] qqq11 = new Employee[]{e1};


        Employee[] qqq1 = new Employee[]{
                new Employee()
                        .setName("qqq")
                        .setLoginName("qqq")
                        .setLastName("qqq")
                        .setPassword("qqq")};


        // Инициализация объекта - Названия Файлов задачи
        String[] file = new String[]{
                FilesForAttachment.FILE_1.getNameFile(),
                FilesForAttachment.FILE_2.getNameFile(),
                FilesForAttachment.FILE_3.getNameFile(),
        };


        //----------------------------------------------------------------------------------------------------------- Инициализация Документа
        Document document1 = new Document()

                .setDocumentType(new DocRegisterCards("[31]/, as")) // Тип документа
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
//                                        .setExecutionOperation(1, EMPLOYEE_ADMIN, CREATE_RESOLUTION),
                                new ExecutionOfDocument(2, qqq[0], CLOSE_EXECUTION),

                        })
                .setResolutionOfDocument(new Resolution[]{
                        (Resolution) new Resolution()
                                .setTextOfResolution(randomString(21))
                                .setAuthorDefault(EMPLOYEE_ADMIN)
                                .setExecutiveManagers(qqq1),
                        (Resolution) new Resolution()
                                .setTextOfResolution(randomString(21))
                                .setAuthorDefault(EMPLOYEE_ADMIN)
                                .setExecutiveManagers(qqq1)
                })
                .setOnExecution(false);


        // Проверка карточки под разными пользователями
        //verifyDocumentStepsMobile.verifyDocumentOnDifferentUsers(document1, folder1);
        // Выполнение действий с документом под разными пользователями
        //1.Выполнение операций
        //2. Комментарии на файле
        // executionDocumentStepsMobile.executionOnDifferentUsers(document1, folder1[0], TypeOfLocation.GRID_FOLDER);


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

//        assertThat(Arrays.asList(document1.getResolutionOfDocument()), contains(
//                hasProperty("reportOfExecution", is(false))
//        ));

        // assertTrue(documentStepsMobile.currentUserIsExecutiveManagersInResolution(document1.getResolutionOfDocument(), qqq[0]));

        //verifyDocumentStepsMobile.verifyDocumentOnDifferentUsers(document1, folder1);

        //Выполнение действий с документом
        // executionDocumentStepsMobile.executionOnDifferentUsers(document1, folder1[0], TypeOfLocation.PAGE_CARD);

    }

}
