package ru.motiw.mobile.steps.Document;

import ru.motiw.mobile.elements.Internal.InternalElementsMobile;
import ru.motiw.mobile.elements.Tasks.TaskElementsMobile;
import ru.motiw.mobile.model.Document.RoleOfUser;
import ru.motiw.mobile.steps.Folders.GridOfFoldersSteps;
import ru.motiw.mobile.steps.InternalStepsMobile;
import ru.motiw.mobile.steps.LoginStepsMobile;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Document.Document;
import ru.motiw.web.model.Tasks.Folder;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import static ru.motiw.mobile.model.Document.RoleOfUser.AUTHOR;
import static ru.motiw.mobile.model.Document.RoleOfUser.CONSIDER_OF_DOCUMENT;

/**
 * Верификация карточки
 */
public class VerifyDocumentStepsMobile extends DocumentStepsMobile {
    private LoginStepsMobile loginStepsMobile = page(LoginStepsMobile.class);
    private InternalStepsMobile internalPageStepsMobile = page(InternalStepsMobile.class);
    private GridOfFoldersSteps gridOfFoldersSteps = page(GridOfFoldersSteps.class);
    private InternalElementsMobile internalElementsMobile = page(InternalElementsMobile.class);
    private TaskElementsMobile taskElementsMobile = page(TaskElementsMobile.class);




    /**
     * Проверка созданного документа
     *
     * @param document атрибуты
     * @return VerifyDocumentStepsMobile
     */
    private VerifyDocumentStepsMobile verifyPageOfDocument(Document document, RoleOfUser roleOfUser) throws Exception {
        if (document == null) {
            return null;
        } else
            internalElementsMobile.getMainTitle().shouldHave((text(document.getDocumentType().getDocRegisterCardsName()))); // Название документа в хедере

        /**
         * Ожидание и проверка кнопок тулбара
         */
        taskElementsMobile.getToolbarOfMenu().waitUntil(visible, 10000);
        verifyAccessToOperations(document.isOnExecution(), roleOfUser);

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
        //----------------------------------------------------------------ГРИД - Папка
        sleep(500); //ожидание папок;
        // Проверяем отображение созданного документа в гриде папки
        gridOfFoldersSteps.checkDisplayItemInGrid(document.getDocumentType().getDocRegisterCardsName(), folders[0]);

        // Проверяем отображение или отсутствие признака нового документа в гриде папки
        gridOfFoldersSteps.verifyMarkOfNewDocument(document, roleOfUser);

        // Проверяем доступные операции с документом из грида
        verifyOperationForDocumentInTheGrid(document, roleOfUser);

        //Переход в документ из грида
        gridOfFoldersSteps.openItemInGrid(document.getDocumentType().getDocRegisterCardsName(), folders[0]); // todo нужно чтобы шаблон отображения в арме имел более уникальное значение чем Тип документа.  Открывать записи по такому уникальному значению.
        //----------------------------------------------------------------ФОРМА - Документ
        verifyPageOfDocument(document, roleOfUser);

        //----------------------------------------------------------------ГРИД - Папка
        // Проверяем отсутствие признака нового документа после возвращения в грид папки
        gridOfFoldersSteps.checkDisappearMarkOfNewDocument(document, folders[0]);

        // Выход из системы
        internalPageStepsMobile.logout();
    }

    /**
     * Проверяем карточку под разными пользователями
     *
     * @param document документ
     * @param folders  папки с которыми будем работать
     * @return
     * @throws Exception
     */
    public VerifyDocumentStepsMobile verifyDocumentOnDifferentUsers(Document document, Folder[] folders) throws Exception {
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
