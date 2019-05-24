package ru.motiw.mobile.steps.Document;

import com.codeborne.selenide.SelenideElement;
import ru.motiw.mobile.elements.Documents.DocumentElementsMobile;
import ru.motiw.mobile.elements.Internal.GridOfFolderElementsMobile;
import ru.motiw.mobile.elements.Internal.InternalElementsMobile;
import ru.motiw.mobile.elements.Tasks.TaskElementsMobile;
import ru.motiw.mobile.model.Document.OperationsOfDocument;
import ru.motiw.mobile.model.Document.TypeOperationsOfDocument;
import ru.motiw.mobile.steps.Folders.GridOfFoldersSteps;
import ru.motiw.mobile.steps.Tasks.TaskStepsMobile;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Document.Document;
import ru.motiw.web.model.Document.OperationOfDocument;
import ru.motiw.web.model.Document.Resolution;
import ru.motiw.web.model.Tasks.Task;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import static org.testng.AssertJUnit.fail;

/**
 * Работа с документами
 */
public class DocumentStepsMobile {
    private InternalElementsMobile internalElementsMobile = page(InternalElementsMobile.class);
    private TaskStepsMobile taskStepsMobile = page(TaskStepsMobile.class);
    private TaskElementsMobile taskElementsMobile = page(TaskElementsMobile.class);
    private DocumentElementsMobile documentElementsMobile = page(DocumentElementsMobile.class);
    GridOfFolderElementsMobile gridOfFolderElementsMobile = page(GridOfFolderElementsMobile.class);
    private GridOfFoldersSteps gridOfFoldersSteps = page(GridOfFoldersSteps.class);


    /**
     * Проверка доступных операций в гриде или в карточке документа
     * Набор кнопок зависит от роли пользователя и от того, на каком этапе находится документ ( на рассмотрении - false, на исполнении - true)
     * <p>
     * isOnExecution на рассмотрении - false, на исполнении - true)
     */
    public void verifyAccessToOperations(Document document, Employee currentUser) {

        // Проверка кнопки операции "Список резолюций"
        verifyAccessToOperationsOnlyInDocumentForm(document.isOnExecution(), OperationsOfDocument.LIST_OF_RESOLUTION.getNameOperation());

        // Документ не на исполнении - Автор документа
        if (!document.isOnExecution() && document.getAuthorOfDocument() == currentUser) {
            verifySetOfOperationForDocument(
                    new OperationOfDocument()
                            .setMoveToExecution(true)
                            .setMoveToArchive(true)
                            .setCreateResolution(true));
        }

        // Документ не на исполнении - Рассматривающий документа
        if (!document.isOnExecution() && compareCurrentUserAndUserInDocument(document.getRouteSchemeForDocument().getUserRoute(), currentUser)) {
            verifySetOfOperationForDocument(
                    new OperationOfDocument()
                            .setMoveToExecution(true)
                            .setMoveToArchive(true));
        }

        // ----------- Документ на исполнении (создана резолюция)
        if (document.isOnExecution()) {

            // список всех резолюций по документу
            List<Resolution> resolutions = new ArrayList<>();

            for (Task resolution : document.getResolutionOfDocument()) {
                resolutions.add((Resolution) resolution);
            }
            // Наличие отправленного отчета по исполнению резолюции
            boolean resolutionWithReportOfExecution = resolutionWithReportOfExecution(resolutions);

            // ----------- Отчет по исполнению резолюции не отправлен
            if (!resolutionWithReportOfExecution) {
                // Документ на исполнении и Отчет по исполнению резолюции не отправлен - Автор документа
                if (document.getAuthorOfDocument() == currentUser) {
                    verifySetOfOperationForDocument(
                            new OperationOfDocument()
                                    .setCloseExecution(true)
                                    .setCreateResolution(true));
                }

                // Документ на исполнении и Отчет по исполнению резолюции не отправлен -  Рассматривающий документа
                if (compareCurrentUserAndUserInDocument(document.getRouteSchemeForDocument().getUserRoute(), currentUser)) {
                    verifySetOfOperationForDocument(
                            new OperationOfDocument());
                }

                // Документ на исполнении и Отчет по исполнению резолюции не отправлен -  Отв.Исполнитель резолюции
                for (Resolution resolution : resolutions) {
                    if (compareCurrentUserAndUserInDocument(resolution.getExecutiveManagers(), currentUser)) {
                        verifySetOfOperationForDocument(
                                new OperationOfDocument()
                                        .setCloseExecution(true));
                    }
                }
            }

            // -----------  Отчет по исполнению резолюции отправлен
            if (resolutionWithReportOfExecution) {
                // Отчет по исполнению резолюции отправлен - Автор документа
                if (document.getAuthorOfDocument() == currentUser) {
                    verifySetOfOperationForDocument(
                            new OperationOfDocument()
                                    .setReturnToExecution(true)
                                    .setCloseExecution(true)
                                    .setCreateResolution(true));
                }


                // Отчет по исполнению резолюции отправлен - Рассматривающий документа
                if (compareCurrentUserAndUserInDocument(document.getRouteSchemeForDocument().getUserRoute(), currentUser)) {
                    verifySetOfOperationForDocument(
                            new OperationOfDocument());
                }


                // Отчет по исполнению Документа отправлен - Отв.Исполнитель резолюции

                for (Resolution resolution : resolutions)
                    if (resolution.getExecutiveManagers() != null) {
                        if (compareCurrentUserAndUserInDocument(resolution.getExecutiveManagers(), currentUser)) {
                            // отчет по резолюции, где текущий пользователь отв.рук отправлен
                            if (resolution.isReportOfExecution()) {
                                verifySetOfOperationForDocument(
                                        new OperationOfDocument());
                            }

                            // отчет по резолюции, где текущий пользователь отв.рук не отправлен
                            if (!resolution.isReportOfExecution()) {
                                verifySetOfOperationForDocument(
                                        new OperationOfDocument()
                                                .setCloseExecution(true));
                            }
                        }
                    }
            }
        }
    }

    /**
     * Метод для нахождения текущего пользователя в массиве пользователей из метаданных документа
     *
     * @param usersInDocument
     * @param currentUser
     * @return
     */
    public boolean compareCurrentUserAndUserInDocument(Employee[] usersInDocument, Employee currentUser) {
        if (usersInDocument != null)
            for (Employee userInDocument : usersInDocument) {
                if (userInDocument == currentUser) {
                    return true;
                }
            }

        return false;
    }

    /**
     * Метод для нахождения текущего пользователя среди отв.рук массива резолюций
     *
     * @param resolutions
     * @param currentUser
     * @return
     */
    public boolean compareCurrentUserAndExecutiveManagersInResolutions(Task[] resolutions, Employee currentUser) {
        if (resolutions != null)
            for (Task resolution : resolutions) {
                for (Employee executiveManager : resolution.getExecutiveManagers()) {
                    if (executiveManager == currentUser) {
                        return true;
                    }
                }
            }
        return false;
    }


    /**
     * Проверка всех резолюций на наличие по ним отправленного отчета по исполнению резолюции
     * отправлен - true
     * не отправлен - false
     *
     * @param resolutions - список резолюций
     * @return
     */
    private boolean resolutionWithReportOfExecution(List<Resolution> resolutions) {
        for (Resolution resolution : resolutions) {
            if (resolution.isReportOfExecution()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Проверка кнопок доступных операций в гриде или в карточке документа
     *
     * @param operationOfDocument - Операции Документа
     */
    public void verifySetOfOperationForDocument(OperationOfDocument operationOfDocument) {

        // todo тут как-то getElementOfOperation вынести в переменную метода, чтобы при каждой проверке не обращаться к получению элемента

            if (operationOfDocument.isCreateResolution()) {
                getElementOfOperation(getNameOfOperation(TypeOperationsOfDocument.CREATE_RESOLUTION)).shouldBe(visible);
            } else
                getElementOfOperation(getNameOfOperation(TypeOperationsOfDocument.CREATE_RESOLUTION)).shouldNotBe(visible);

            if (operationOfDocument.isMoveToExecution()) {
                getElementOfOperation(OperationsOfDocument.MOVE_TO_EXECUTION.getNameOperation()).shouldBe(visible);
            } else
                getElementOfOperation(OperationsOfDocument.MOVE_TO_EXECUTION.getNameOperation()).shouldNotBe(visible);

            if (operationOfDocument.isMoveToArchive()) {
                getElementOfOperation(OperationsOfDocument.MOVE_TO_ARCHIVE.getNameOperation()).shouldBe(visible);
            } else
                getElementOfOperation(OperationsOfDocument.MOVE_TO_ARCHIVE.getNameOperation()).shouldNotBe(visible);

            if (operationOfDocument.isReturnToExecution()) {
                getElementOfOperation(OperationsOfDocument.RETURN_TO_EXECUTION.getNameOperation()).shouldBe(visible);
            } else
                getElementOfOperation(OperationsOfDocument.RETURN_TO_EXECUTION.getNameOperation()).shouldNotBe(visible);

            if (operationOfDocument.isCloseExecution()) {
                getElementOfOperation(OperationsOfDocument.CLOSE_EXECUTION.getNameOperation()).shouldBe(visible);
            } else
                getElementOfOperation(OperationsOfDocument.CLOSE_EXECUTION.getNameOperation()).shouldNotBe(visible);
    }

    /**
     * Проверяем наличие доступных операций с документом из грида
     *
     * @param document
     */
    public void verifyOperationForDocumentInTheGrid(Document document, Employee currentUser) {
        // Открываем меню операций
        gridOfFoldersSteps.clickContextMenuForItemInGrid(document.getDocumentType().getDocRegisterCardsName());
        // Проверяем наличие доступных операций с документом из грида
        verifyAccessToOperations(document, currentUser);
        // Закрываем меню операций
        gridOfFoldersSteps.clickContextMenuForItemInGrid(document.getDocumentType().getDocRegisterCardsName());
    }


    /**
     * Проверка кнопок операций, которые доступны только в карточке документа
     * Набор кнопок зависит от роли пользователя и от того, на каком этапе находится документ ( на рассмотрении - false, на исполнении - true)
     *
     * @param onExecution на рассмотрении - false, на исполнении - true)
     */
    public void verifyAccessToOperationsOnlyInDocumentForm(Boolean onExecution, String nameOperation) {

        if (taskElementsMobile.getToolbarOfMenu().is(visible)) // Если мы в карточке
        {
            if (!onExecution) {
                documentElementsMobile.getButtonOfTab(nameOperation).shouldNotBe(visible);
            } else {
                documentElementsMobile.getButtonOfTab(nameOperation).shouldBe(visible);
            }
        }
    }


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
    SelenideElement getElementOfOperation(String nameOfOperation) {

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
    String getNameOfOperation(TypeOperationsOfDocument operation) {

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
            fail("Для операции " + operation.name() + " отсутствует Название ИЛИ не найдено место выполнения операции");
        }

        return nameOfOperationCreateResolution;
    }

    /**
     * Выполнение стандартной операции в форме докумена с вводом текста перед подтверждением операции
     *
     * @param nameOfOperation - название операции
     * @param textForInput    - текст вводимый в инпут перед подтверждением операции
     */
    void executionOperationWithAdditionText(String nameOfOperation, String textForInput) {
        // Находим локатор элемента кнопки операции
        getElementOfOperation(nameOfOperation).click();
        // Вводим текст в форму
        sleep(500);
        taskElementsMobile.getInputForAddComment().setValue(textForInput);
        // Подтверждаем выполнение действия
        internalElementsMobile.getButtonInFormOfExecutionOperations(nameOfOperation).click();
    }

}
