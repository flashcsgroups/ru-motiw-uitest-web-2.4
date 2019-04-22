package ru.motiw.mobile.steps.Document;

import com.codeborne.selenide.SelenideElement;
import ru.motiw.mobile.elements.Documents.DocumentElementsMobile;
import ru.motiw.mobile.elements.Internal.GridOfFolderElementsMobile;
import ru.motiw.mobile.elements.Internal.InternalElementsMobile;
import ru.motiw.mobile.elements.Tasks.TaskElementsMobile;
import ru.motiw.mobile.model.Document.OperationsOfDocument;
import ru.motiw.mobile.model.Document.RoleOfUser;
import ru.motiw.mobile.model.Document.TypeOperationsOfDocument;
import ru.motiw.mobile.steps.Folders.GridOfFoldersSteps;
import ru.motiw.mobile.steps.InternalStepsMobile;
import ru.motiw.mobile.steps.LoginStepsMobile;
import ru.motiw.mobile.steps.Tasks.TaskStepsMobile;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Document.Document;
import ru.motiw.web.model.Tasks.Folder;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.fail;
import static ru.motiw.mobile.model.Document.RoleOfUser.AUTHOR;
import static ru.motiw.mobile.model.Document.RoleOfUser.CONSIDER_OF_DOCUMENT;

public class DocumentStepsMobile {
    private LoginStepsMobile loginStepsMobile = page(LoginStepsMobile.class);
    private InternalStepsMobile internalPageStepsMobile = page(InternalStepsMobile.class);
    private GridOfFoldersSteps gridOfFoldersSteps = page(GridOfFoldersSteps.class);
    private InternalElementsMobile internalElementsMobile = page(InternalElementsMobile.class);
    private TaskStepsMobile taskStepsMobile = page(TaskStepsMobile.class);
    private TaskElementsMobile taskElementsMobile = page(TaskElementsMobile.class);
    private DocumentElementsMobile documentElementsMobile = page(DocumentElementsMobile.class);
    private GridOfFolderElementsMobile gridOfFolderElementsMobile = page(GridOfFolderElementsMobile.class);


    /**
     * Проверка кол-во прикрепленных файлов
     * Проверка файлов в каруселе
     *
     * @param document
     * @return TaskStepsMobile
     * @throws Exception
     */
    public DocumentStepsMobile verifyFilesInDocument(Document document) throws Exception {
        if (document.getValueFiles() == null) {
            return this;
        } else {
            // сравниваем кол-во прикрепленных файлов с числом отображаемым в элементе-переключтеле файлов.
            taskElementsMobile.getNumbersOnElementCounterFiles().shouldHave(text("1 / " + document.getNumberOfFiles()));
            //Проверка файлов в каруселе
            taskStepsMobile.verifyFilesInPreview(document.getValueFiles(), document.getNumberOfFiles());
        }
        return this;
    }

    /**
     * Получаем элемент операции в зависимости от того, где в данный момент операция отображается (конт.меню в гриде папки или форма документа)
     *
     * @param nameOfOperation
     * @return
     */
    private SelenideElement getElementOfOperation(String nameOfOperation) {

        SelenideElement elementOfOperation = null;

        if (!gridOfFolderElementsMobile.getAllItemsInTheGridOfFolder().isEmpty()) // Если мы в гриде
        {
            elementOfOperation = gridOfFolderElementsMobile.getOperationInContextMenu(nameOfOperation);
        } else if (taskElementsMobile.getToolbarOfMenu().is(visible)) // Если в карточке
        {
            elementOfOperation = documentElementsMobile.getButtonOfTab(nameOfOperation);
        }

        if (elementOfOperation == null) {
            fail("Элемент для взаимодействия с операциями докмуента отсутствует");
        }

        return elementOfOperation;
    }

    /**
     * Получение названий операций
     * нужно для некоторых операций название которых отличается, в зависимости от того, где в данный момент операция отображается (конт.меню в гриде папки или форма документа)
     *
     * @param operation тип операции
     * @return
     */
    private String getNameOfOperation(TypeOperationsOfDocument operation) {

        String nameOfOperationCreateResolution = null;

        switch (operation) {
            case CREATE_RESOLUTION:
                if (!gridOfFolderElementsMobile.getAllItemsInTheGridOfFolder().isEmpty()) // Если мы в гриде
                {
                    nameOfOperationCreateResolution = OperationsOfDocument.CREATE_RESOLUTION_IN_THE_GRID.getNameOperation();
                } else if (taskElementsMobile.getToolbarOfMenu().is(visible)) // Если в карточке
                {
                    nameOfOperationCreateResolution = OperationsOfDocument.CREATE_RESOLUTION_IN_FORM_OF_DOCUMENT.getNameOperation();
                }
        }

        if (nameOfOperationCreateResolution == null) {
            fail("Название операции отсутствует");
        }

        return nameOfOperationCreateResolution;
    }


    /**
     * Проверка кнопок доступных операций
     * Набор кнопок зависит от роли пользователя
     */
    public void verifyAccessToOperations(RoleOfUser role) {

        getElementOfOperation(OperationsOfDocument.MOVE_TO_EXECUTION.getNameOperation()).shouldBe(visible);
        getElementOfOperation(OperationsOfDocument.MOVE_TO_ARCHIVE.getNameOperation()).shouldBe(visible);

        switch (role) {
            case AUTHOR:
                getElementOfOperation(getNameOfOperation(TypeOperationsOfDocument.CREATE_RESOLUTION)).shouldBe(visible);
                break;

            case CONSIDER_OF_DOCUMENT:
                getElementOfOperation(getNameOfOperation(TypeOperationsOfDocument.CREATE_RESOLUTION)).shouldNotBe(visible);
                break;

            default:
                throw new IllegalArgumentException("Неверное название роли:" + role);
        }
    }


    /**
     * Проверяем наличие доступных операций с документом из грида
     *
     * @param document
     * @param roleOfUser
     */
    public void verifyOperationForDocument(Document document, RoleOfUser roleOfUser) {
        // Открываем меню операций
        gridOfFoldersSteps.clickContextMenuForItemInGrid(document.getDocumentType().getDocRegisterCardsName());
        // Проверяем наличие доступных операций с документом из грида
        verifyAccessToOperations(roleOfUser);
        // Закрываем меню операций
        gridOfFoldersSteps.clickContextMenuForItemInGrid(document.getDocumentType().getDocRegisterCardsName());
    }


    /**
     * Проверка созданного документа
     *
     * @param document атрибуты
     * @return DocumentStepsMobile
     */
    private DocumentStepsMobile verifyPageOfDocument(Document document, RoleOfUser roleOfUser) throws Exception {
        if (document == null) {
            return null;
        } else
            internalElementsMobile.getMainTitle().shouldHave((text(document.getDocumentType().getDocRegisterCardsName()))); // Название документа в хедере

        /**
         * Ожидание и проверка кнопок тулбара
         */
        taskElementsMobile.getToolbarOfMenu().waitUntil(visible, 5000);
        verifyAccessToOperations(roleOfUser);

        /*
         * Проверка Файлов
         */
        verifyFilesInDocument(document);

        return this;
    }

    /**
     * Шаги при проверке карточки документа
     *
     * @param document   документ
     * @param employee   пользователь
     * @param folders    папки с которыми будем работать
     * @param roleOfUser Роль пользователя в документе
     * @throws Exception
     */
    private void stepsOfVerifyDocument(Document document, Employee employee, Folder[] folders, RoleOfUser roleOfUser) throws Exception {
        loginStepsMobile
                .loginAs(employee) // Авторизация под участником рассмотрения документа
                .waitLoadMainPage(); // Ожидание открытия главной страницы
        internalPageStepsMobile.goToInternalMenu(); // Открываем главное меню
        assertThat("Check that the displayed menu item 9 (User Info; Tasks And Documents; Create Tasks; Today; Search; Settings; Help; Exit; Go To Full Version)",
                internalPageStepsMobile.hasMenuUserComplete());

        //----------------------------------------------------------------ГРИД - Папка
        sleep(500); //ожидание папок;
        // Проверяем отображение созданного документа в гриде папки
        gridOfFoldersSteps.checkDisplayDocumentInGrid(document, folders[0]);

        // Проверяем отображение или отсутствие признака нового документа в гриде папки
        gridOfFoldersSteps.verifyMarkOfNewDocument(document, roleOfUser);

        // Проверяем доступные операции с документом из грида
        verifyOperationForDocument(document, roleOfUser);

        //Переход в документ из грида
        gridOfFoldersSteps.openDocumentInGrid(document);
        //----------------------------------------------------------------ФОРМА - Документ
        verifyPageOfDocument(document, roleOfUser);
        // Выход из системы
        internalPageStepsMobile.logout();
    }


    /**
     * Проверяем под разными пользователями
     *
     * @param document документ
     * @param folders  папки с которыми будем работать
     * @return
     * @throws Exception
     */
    public DocumentStepsMobile verifyDocumentOnDifferentUsers(Document document, Folder[] folders) throws Exception {
        if (document == null || folders == null) {
            return null;
        }

        //Проверки для Автора
        if (document.getAuthorOfDocument() == null) {
            return null;
        } else
            stepsOfVerifyDocument(document, document.getAuthorOfDocument(), folders, AUTHOR);

        //Проверки для каждого рассматривающиего
        if (document.getRouteSchemeForDocument().getUserRoute() == null) {
            return null;
        } else
            for (Employee employee : document.getRouteSchemeForDocument().getUserRoute()) {
                stepsOfVerifyDocument(document, employee, folders, CONSIDER_OF_DOCUMENT);
            }

        return this;
    }

}
