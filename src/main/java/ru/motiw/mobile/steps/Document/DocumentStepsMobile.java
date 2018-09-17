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
import ru.motiw.mobile.steps.Tasks.TaskStepsMobile;
import ru.motiw.web.model.Document.Document;

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
     * Проверка кнопок доступных операций в гриде или в карточке документа
     * Набор кнопок зависит от роли пользователя и от того, на каком этапе находится документ ( на рассмотрении - false, на исполнении - true)
     *
     * @param onExecution на рассмотрении - false, на исполнении - true)
     */
    public void verifyAccessToOperations(Boolean onExecution, RoleOfUser role) {

        verifyAccessToOperationsOnlyInDocumentForm(onExecution, OperationsOfDocument.LIST_OF_RESOLUTION.getNameOperation());

        if (!onExecution) {
            getElementOfOperation(OperationsOfDocument.MOVE_TO_EXECUTION.getNameOperation()).shouldBe(visible);
            getElementOfOperation(OperationsOfDocument.MOVE_TO_ARCHIVE.getNameOperation()).shouldBe(visible);
            getElementOfOperation(OperationsOfDocument.CLOSE_EXECUTION.getNameOperation()).shouldNotBe(visible);
        }

        if (onExecution) {
            getElementOfOperation(OperationsOfDocument.CLOSE_EXECUTION.getNameOperation()).shouldBe(visible);
            getElementOfOperation(OperationsOfDocument.MOVE_TO_EXECUTION.getNameOperation()).shouldNotBe(visible);
            getElementOfOperation(OperationsOfDocument.MOVE_TO_ARCHIVE.getNameOperation()).shouldNotBe(visible);
        }

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
    public void verifyOperationForDocumentInTheGrid(Document document, RoleOfUser roleOfUser) {
        // Открываем меню операций
        gridOfFoldersSteps.clickContextMenuForItemInGrid(document.getDocumentType().getDocRegisterCardsName());
        // Проверяем наличие доступных операций с документом из грида
        verifyAccessToOperations(document.isOnExecution(), roleOfUser);
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
            }

            if (onExecution) {
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
            fail("Для операции " + operation.name() + " отсутствует Название");
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
