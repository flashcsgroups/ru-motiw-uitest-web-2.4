package ru.motiw.mobile.steps.Document;

import ru.motiw.mobile.elements.Internal.InternalElementsMobile;
import ru.motiw.mobile.elements.Tasks.TaskElementsMobile;
import ru.motiw.mobile.model.Document.OperationsOfDocument;
import ru.motiw.mobile.model.Document.RoleOfUser;
import ru.motiw.mobile.model.Document.TypeOfExecutionPlace;
import ru.motiw.mobile.steps.Folders.GridOfFoldersSteps;
import ru.motiw.mobile.steps.InternalStepsMobile;
import ru.motiw.mobile.steps.LoginStepsMobile;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Document.Document;
import ru.motiw.web.model.Document.ExecutionOfDocument;
import ru.motiw.web.model.Document.Resolution;
import ru.motiw.web.model.Tasks.Folder;
import ru.motiw.web.model.Tasks.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static ru.motiw.mobile.model.Document.RoleOfUser.AUTHOR;
import static ru.motiw.mobile.model.Document.RoleOfUser.CONSIDER_OF_DOCUMENT;

/**
 * Проверка выполнения действий
 */
public class ExecutionDocumentStepsMobile extends DocumentStepsMobile {
    private LoginStepsMobile loginStepsMobile = page(LoginStepsMobile.class);
    private InternalStepsMobile internalPageStepsMobile = page(InternalStepsMobile.class);
    private GridOfFoldersSteps gridOfFoldersSteps = page(GridOfFoldersSteps.class);
    private InternalElementsMobile internalElementsMobile = page(InternalElementsMobile.class);
    private TaskElementsMobile taskElementsMobile = page(TaskElementsMobile.class);
    private ResolutionStepsMobile resolutionStepsMobile = page(ResolutionStepsMobile.class);


    /**
     * Выполнение различных действий из грида папки в конт.меню операций
     *
     * @param document
     * @param executionOfDocument
     */
    private void verifyExecutionInGrid(Document document, ExecutionOfDocument executionOfDocument) {
        // Открываем меню операций
        gridOfFoldersSteps.clickContextMenuForItemInGrid(document.getDocumentType().getDocRegisterCardsName());
        // Выполняем операции
        executionOperations(document, executionOfDocument);
        // Проверяем, что Кнопка открытия конт.меню появилась
        gridOfFolderElementsMobile.getButtonOfOpenContextMenu(document.getDocumentType().getDocRegisterCardsName()).shouldBe(visible);
        // Проверяем, что конт.меню закрылось
        gridOfFolderElementsMobile.getContextMenu().shouldNotBe(visible);
    }


    /**
     * Выполнение различных действий в карточке
     *
     * @param
     * @param document
     * @param executionOfDocument
     */
    private void executionInFormOfDocument(Document document, ExecutionOfDocument executionOfDocument) {
        // Ожидание и проверка кнопок тулбара
        taskElementsMobile.getToolbarOfMenu().waitUntil(visible, 15000);
        // Выполнение операций
        executionOperations(document, executionOfDocument); // также как verifyAccessToOperations - локаторы кнопкок операций зависят от того, где мы находимся

        // Работа с файлами

    }

    /**
     * Выполнение операций
     *
     * @param executionOfDocument
     */
    private void executionOperations(Document document, ExecutionOfDocument executionOfDocument) {

        switch (executionOfDocument.getTypeExecutionOperation()) {
            case CREATE_RESOLUTION:
                for (Task resolution: document.getResolutionOfDocument())
                    resolutionStepsMobile.createResolution(document, (Resolution) resolution);

                break;

            case MOVE_TO_EXECUTION:
                getElementOfOperation(OperationsOfDocument.MOVE_TO_EXECUTION.getNameOperation()).click();
                internalElementsMobile.getToastWithText().shouldHave(text("В данный момент создание задачи по исполнению в разработке"));
                break;

            case MOVE_TO_ARCHIVE:
                executionOperationWithAdditionText(OperationsOfDocument.MOVE_TO_ARCHIVE.getNameOperation(), OperationsOfDocument.MOVE_TO_ARCHIVE.getNameOperation());
                internalElementsMobile.getToastWithText().waitUntil(text("в архив"), 10000); // Выводимый текст может отличаться, поэтому проверяем только общую часть текста сообщений "в архив".
                break;

            case CLOSE_EXECUTION:
                executionOperationWithAdditionText(OperationsOfDocument.CLOSE_EXECUTION.getNameOperation(), OperationsOfDocument.CLOSE_EXECUTION.getNameOperation());
                internalElementsMobile.getToastWithText().waitUntil(text("Выполнение завершено"), 10000);

            default:
                throw new IllegalArgumentException("Неверное название типа операции:" + executionOfDocument.getTypeExecutionOperation());
        }
    }


    /**
     * Шаги при проверке выполнения действий в карточке документа
     *
     * @param document
     * @param employee
     * @param folder
     * @param executionOfDocument
     * @throws Exception
     */
    private void stepsOfExecutionDocument(Document document, Employee employee, Folder folder, ExecutionOfDocument executionOfDocument, TypeOfExecutionPlace executionPlace) {
        loginStepsMobile
                .loginAs(employee) // Авторизация под участником рассмотрения документа
                .waitLoadMainPage(); // Ожидание открытия главной страницы
        gridOfFoldersSteps.openFolder(folder);
        //----------------------------------------------------------------ГРИД - Папка
        gridOfFoldersSteps.checkDisplayDocumentInGrid(document, folder);

        // ---------------------------------------------------------------- Выполнение операций из грида папки в конт.меню операций
        if (executionPlace == TypeOfExecutionPlace.CONTEXT_MENU_IN_THE_GRID_FOLDER) {
            verifyExecutionInGrid(document, executionOfDocument);
        }

        // ---------------------------------------------------------------- Выполнение операций в карточке документа
        if (executionPlace == TypeOfExecutionPlace.DOCUMENT_CARD) {
            // Переход в документ из грида
            gridOfFoldersSteps.openDocumentInGrid(document, folder);
            //----------------------------------------------------------------ФОРМА - Документ
            // Выполнение операций
            executionInFormOfDocument(document, executionOfDocument);
        }
        // Выход из системы
        internalPageStepsMobile.logout();

        // Проверка выполненых операций
        verifyExecutionOnDifferentUsers(document, folder, executionOfDocument);
    }


    /**
     * Проверяем выполнение действий под разными пользователями
     *
     * @param document             документ
     * @param folder               папка с которой будем работать
     * @param typeOfExecutionPlace место откуда будет выполнена операция с документом
     * @return
     */
    public ExecutionDocumentStepsMobile executionOnDifferentUsers(Document document, Folder folder, TypeOfExecutionPlace typeOfExecutionPlace) {
        if (document == null || folder == null) {
            return null;
        }

        if (document.getExecutionOfDocument() != null) {
            List<Integer> allNumbersOfExecution = new ArrayList<>(); // Все порядковые номера выполняемых операций

            //  Находим все порядковые номера выполняемых операций
            for (ExecutionOfDocument execution : document.getExecutionOfDocument()) {
                allNumbersOfExecution.add(execution.getNumberOfExecution());
            }

            // Cортируем по возрастанию все порядковые номера выполняемых операций
            Collections.sort(allNumbersOfExecution);

            // Обработка выполняемых операций согласно их порядковым номерам
            for (Integer numberOfExecutionInAscendingOrder : allNumbersOfExecution) {
                for (ExecutionOfDocument executionOfDocument : document.getExecutionOfDocument()) {
                    if (numberOfExecutionInAscendingOrder.equals(executionOfDocument.getNumberOfExecution())) {
                        stepsOfExecutionDocument(document, executionOfDocument.getExecutiveUser(), folder, executionOfDocument, typeOfExecutionPlace);
                    }
                }
            }
        }
        return this;
    }

    /**
     * Проверка раннее выполненых операций под разными пользователями
     *
     * @param document
     * @param folder              папка с которой будем работать
     * @param executionOfDocument раннее выполненая операция
     * @return
     */
    public ExecutionDocumentStepsMobile verifyExecutionOnDifferentUsers(Document document, Folder folder, ExecutionOfDocument executionOfDocument) {

        if (document == null || folder == null) {
            return null;
        }
        //Проверки для Автора
        if (document.getAuthorOfDocument() == null) {
            return null;
        } else
            stepsOfVerifyExecutionDocument(document, document.getAuthorOfDocument(), folder, executionOfDocument, AUTHOR);

        //Проверки для каждого рассматривающиего
        if (document.getRouteSchemeForDocument().getUserRoute() == null) {
            return null;
        } else
            for (Employee employee : document.getRouteSchemeForDocument().getUserRoute()) {
                stepsOfVerifyExecutionDocument(document, employee, folder, executionOfDocument, CONSIDER_OF_DOCUMENT);
            }

        return this;
    }

    /**
     * Шаги при проверке раннее выполненых операций
     *
     * @param document
     * @param employee            пользователь под которым проверяем
     * @param folder              папка с которой будем работать
     * @param executionOfDocument раннее выполненая операция
     * @param roleOfUser          роль пользователя под которым проверяем
     */
    private void stepsOfVerifyExecutionDocument(Document document, Employee employee, Folder folder, ExecutionOfDocument executionOfDocument, RoleOfUser roleOfUser) {
        loginStepsMobile
                .loginAs(employee) // Авторизация под участником рассмотрения документа
                .waitLoadMainPage(); // Ожидание открытия главной страницы
        gridOfFoldersSteps.openFolder(folder);
        //----------------------------------------------------------------ГРИД - Папка

        // Проверка раннее выполненых операций
        verifyExecutionOfDocument(document, folder, executionOfDocument, roleOfUser);

        // Выход из системы
        internalPageStepsMobile.logout();

    }

    /**
     * Проверка раннее выполненых операций в карточке или гриде
     *
     * @param document
     * @param folder
     * @param executionOfDocument
     * @param roleOfUser
     */
    private void verifyExecutionOfDocument(Document document, Folder folder, ExecutionOfDocument executionOfDocument, RoleOfUser roleOfUser) {

        switch (executionOfDocument.getTypeExecutionOperation()) {
            case CREATE_RESOLUTION:
                // Проверяем доступные операции с документом из грида
                verifyOperationForDocumentInTheGrid(document, roleOfUser);
                //Переход в документ из грида
                gridOfFoldersSteps.openDocumentInGrid(document, folder);
                //----------------------------------------------------------------ФОРМА - Документ
                // Ожидание и проверка кнопок тулбара
                taskElementsMobile.getToolbarOfMenu().waitUntil(visible, 15000);
                verifyAccessToOperations(document.isOnExecution(), roleOfUser);
                // Проверка резолюции
                for (Task resolution: document.getResolutionOfDocument())
                    resolutionStepsMobile.verifyCreatedResolution(document, (Resolution) resolution);
                break;

            case MOVE_TO_EXECUTION:
                break;

            case MOVE_TO_ARCHIVE:
                break;

            case CLOSE_EXECUTION:
                break;

            default:
                throw new IllegalArgumentException("Неверное название типа операции:" + executionOfDocument.getTypeExecutionOperation());
        }
    }

}
