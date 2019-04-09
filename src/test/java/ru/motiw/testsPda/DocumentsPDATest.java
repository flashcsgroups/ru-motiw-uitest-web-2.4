package ru.motiw.testsPda;

import com.codeborne.selenide.testng.TextReport;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.motiw.data.dataproviders.DocflowAdministration;
import ru.motiw.data.listeners.ScreenShotOnFailListener;
import ru.motiw.web.elements.elementspda.DocumentsStepsPDA;
import ru.motiw.web.elements.elementspda.InternalStepsPDA;
import ru.motiw.web.elements.elementspda.LoginStepsPDA;
import ru.motiw.web.model.Administration.Directories.Directories;
import ru.motiw.web.model.Administration.TasksTypes.TasksTypes;
import ru.motiw.web.model.Administration.Users.Department;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.DocflowAdministration.DictionaryEditor.DictionaryEditor;
import ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards.DocRegisterCards;
import ru.motiw.web.model.Document.Document;
import ru.motiw.web.steps.Administration.Directories.DirectoriesEditSteps;
import ru.motiw.web.steps.Administration.Directories.DirectoriesSteps;
import ru.motiw.web.steps.Administration.Users.UsersSteps;
import ru.motiw.web.steps.BaseSteps;
import ru.motiw.web.steps.DocflowAdministration.DocumentRegistrationCards.FormDocRegisterCardsEditGeneralSteps;
import ru.motiw.web.steps.DocflowAdministration.DocumentRegistrationCards.GridDocRegisterCardsSteps;
import ru.motiw.web.steps.Home.InternalSteps;
import ru.motiw.web.steps.Login.LoginStepsSteps;
import ru.motiw.web.steps.Login.RestorePasswordSteps;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;
import static ru.motiw.web.steps.Administration.Users.DepartmentSteps.goToURLDepartments;
import static ru.motiw.web.steps.DocflowAdministration.DictionaryEditorSteps.goToURLDictionaryEditor;
import static ru.motiw.web.steps.Documents.CreateDocument.NewDocumentSteps.goToURLNewDocument;


@Listeners({ScreenShotOnFailListener.class, TextReport.class})

public class DocumentsPDATest extends DocflowAdministration {

    private LoginStepsSteps loginPageSteps;
    private InternalSteps internalPageSteps;
    private UsersSteps userPageSteps;
    private RestorePasswordSteps restorePasswordSteps;

    @BeforeClass
    public void beforeTest() {
        loginPageSteps = page(LoginStepsSteps.class);
        internalPageSteps = page(InternalSteps.class);
        userPageSteps = page(UsersSteps.class);
        restorePasswordSteps = page(RestorePasswordSteps.class);
        open("/");
    }

    @Test(priority = 1, dataProvider = "objectDataRCD", dataProviderClass = DocflowAdministration.class)
    public void checkWorkDocumentsPDA(Department[] departments, Employee[] employees, Directories directories, TasksTypes tasksTypes, DictionaryEditor dictionaryEditor,
                                      DocRegisterCards registerCards, Document document) throws Exception {

        loginPageSteps.loginAs(ADMIN);

        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPageSteps.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице

        //---------------------------------------------------------------------------------Пользователи и Подразделения
        goToURLDepartments().createDepartment(departments[0]);


        userPageSteps.createUser(employees[0].setDepartment(departments[0]))
                .createUser(employees[1].setDepartment(departments[0]))
                .createUser(employees[2].setDepartment(departments[0]));

        //-----------------------------------------------------------------------------------Редактор словарей
        // Переход в раздел - Администрирование ДО/Редактор словарей
        goToURLDictionaryEditor().addDictionaryEditor(dictionaryEditor);

        //----------------------------------------------------------------------Справочники
        // Переход в раздел Администрирование/Справочники
        DirectoriesSteps.goToURLDirectories().addObjectTaskTypeList(directories.getObjectTypeName());  // Добавляем объект - Справочник

        DirectoriesEditSteps directoriesEditSteps = new DirectoriesEditSteps().directoriesEditPage();

        AssertJUnit.assertTrue("check display the form directories editing", directoriesEditSteps.loadedDirectoriesEditPage());

        // Добавляем поля и сохранеяем объект
        directoriesEditSteps.addAllFieldsTaskTypeList(directories).saveChangesOnObject(directories);

        //---------------------------------------------------------------------------------РКД
        // Переход в раздел Администрирование ДО/Регистрационные карточки документов
        FormDocRegisterCardsEditGeneralSteps editRCD = GridDocRegisterCardsSteps
                .goToURLGridDocRegisterCards().addDocRegisterCards(); // Добавить РКД

        /**
         * Добавляем поля для РКД (регистрационная карточка документа) на вкладке - ПОЛЯ
         */
        //fieldsTabRCD().addFieldsDocRegisterCards(registerCards);

        /**
         * Устанавливаем настройки для РКД (регистрационная карточка документа) на вкладке - МАРШРУТЫ
         */
        //routesTabRCD().checkBoxUseAllPossibleRoutes(registerCards);

        //generalTabRCD().addNameDocumentRegisterCards(registerCards);
        // Сохранение настроек РКД
        GridDocRegisterCardsSteps gridDocRegisterCards = editRCD.saveAllChangesInDoc(); // Сохранение всех изменений в РКД
        gridDocRegisterCards.verifyDocRegisterCards(registerCards.getDocRegisterCardsName());

        //-------------------------------------------------------------------------------Создать документ
        goToURLNewDocument().createDocument(document);

        internalPageSteps.logout(); // Выход из системы
        assertTrue(loginPageSteps.isNotLoggedIn());

        /*
         * Проверяем отображение документов в гриде документов (отчет Контролирования)
         */
        LoginStepsPDA loginPagePDA = open(BaseSteps.PDA_PAGE_URL, LoginStepsPDA.class);
        // Авторизация
        loginPagePDA.loginAsAdmin(ADMIN);
        InternalStepsPDA internalPagePDA = loginPagePDA.goToInternalMenu(); // Инициализируем внутренюю стр. системы и переходим на нее
        assertThat("Check that the displayed menu item 4 (Tasks; Create Tasks; Today; Document)",
                internalPagePDA.hasMenuUserComplete());

        DocumentsStepsPDA documentsPagePDA = internalPagePDA.goToDocuments();
        // Проверяем отображение отчетов "контролирования"
        documentsPagePDA.checkMapGridsDocuments();
        // Проверяем отображение созданного документа в гриде
        documentsPagePDA.checkTheDisplayOfTheDocumentGrid(registerCards, document);

        internalPagePDA.logout(); // Выход из системы
        assertTrue(loginPagePDA.isNotLoggedInPDA());


    }


}
