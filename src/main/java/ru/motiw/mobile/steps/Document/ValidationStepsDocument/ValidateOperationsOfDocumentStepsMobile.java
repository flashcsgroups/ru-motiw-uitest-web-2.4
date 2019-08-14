package ru.motiw.mobile.steps.Document.ValidationStepsDocument;

import com.codeborne.selenide.SelenideElement;
import ru.motiw.mobile.model.Document.OperationsOfDocument;
import ru.motiw.mobile.model.Document.TypeOfLocation;
import ru.motiw.mobile.model.Document.TypeOperationsOfDocument;
import ru.motiw.mobile.steps.CardStepsMobile;
import ru.motiw.mobile.steps.Document.AssertionDocument.AssertDocument;
import ru.motiw.mobile.steps.Document.DocumentStepsMobile;
import ru.motiw.mobile.steps.Folders.GridOfFoldersSteps;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Document.Document;
import ru.motiw.web.model.Document.OperationOfDocument;
import ru.motiw.web.model.Document.Resolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static com.codeborne.selenide.Selenide.page;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.fail;

/**
 *  Проверки операций документа
 */
public class ValidateOperationsOfDocumentStepsMobile extends CardStepsMobile {

    private DocumentStepsMobile documentStepsMobile = page(DocumentStepsMobile.class);
    private AssertDocument assertDocument = page(AssertDocument.class);
    private GridOfFoldersSteps gridOfFoldersSteps = page(GridOfFoldersSteps.class);

    /**
     * Проверка доступных операций в гриде или в карточке документа
     * Набор кнопок зависит от роли пользователя и от того, на каком этапе находится документ ( на рассмотрении - false, на исполнении - true)
     * <p>
     * isOnExecution на рассмотрении - false, на исполнении - true)
     */
    public void visibleWithRightAccessToOperations(Document document, Employee currentUser) {

        // Документ не на исполнении - Автор документа
        if (!document.isOnExecution() && document.getAuthorOfDocument() == currentUser) {
            verifyAccessToSetOfOperation(
                    document.setOperationOfDocument(
                            new OperationOfDocument()
                                    .setMoveToExecution(true)
                                    .setMoveToArchive(true)
                                    .setCreateResolution(true)));
        }

        // Документ не на исполнении - Рассматривающий документа

        if (!document.isOnExecution() && Arrays.asList(document.getRouteSchemeForDocument().getUserRoute()).contains(currentUser)) {
            verifyAccessToSetOfOperation(
                    document.setOperationOfDocument(
                            new OperationOfDocument()
                                    .setMoveToExecution(true)
                                    .setMoveToArchive(true)));
        }

        // ----------- Документ на исполнении (создана резолюция)
        if (document.isOnExecution()) {
            // Наличие отправленного отчета по исполнению резолюции
            boolean documentHaveResolutionWithReportOfExecution = assertDocument.resolutionHasReportOfExecution(document.getResolutionOfDocument());

            // ----------- Отчет по исполнению резолюции не отправлен
            if (!documentHaveResolutionWithReportOfExecution) {
                // Документ на исполнении и Отчет по исполнению резолюции не отправлен - Автор документа
                if (document.getAuthorOfDocument() == currentUser) {
                    verifyAccessToSetOfOperation(
                            document.setOperationOfDocument(
                                    new OperationOfDocument()
                                            .setListOfResolution(assertDocument.currentLocationIsDocumentCard())
                                            .setCloseExecution(true)
                                            .setCreateResolution(true)));
                }

                // Документ на исполнении и Отчет по исполнению резолюции не отправлен -  Отв.Исполнитель резолюции
                for (Resolution resolution : document.getResolutionOfDocument()) {
                    if (Arrays.asList(resolution.getExecutiveManagers()).contains(currentUser)) {
                        verifyAccessToSetOfOperation(
                                document.setOperationOfDocument(
                                        new OperationOfDocument()
                                                .setListOfResolution(assertDocument.currentLocationIsDocumentCard())
                                                .setCloseExecution(true)));
                    }
                }
            }

            // -----------  Отчет по исполнению резолюции отправлен
            if (documentHaveResolutionWithReportOfExecution) {
                // Отчет по исполнению резолюции отправлен - Автор документа
                if (document.getAuthorOfDocument() == currentUser) {
                    verifyAccessToSetOfOperation(
                            document.setOperationOfDocument(
                                    new OperationOfDocument()
                                            .setListOfResolution(assertDocument.currentLocationIsDocumentCard())
                                            .setReturnToExecution(true)
                                            .setCloseExecution(true)
                                            .setCreateResolution(true)));
                }

                // Отчет по исполнению Документа отправлен - Отв.Исполнитель резолюции
                for (Resolution resolution : document.getResolutionOfDocument())
                    if (resolution.getExecutiveManagers() != null) {
                        if (Arrays.asList(resolution.getExecutiveManagers()).contains(currentUser)) {
                            // отчет по резолюции, где текущий пользователь отв.рук, отправлен
                            if (resolution.isReportOfExecution()) {
                                verifyAccessToSetOfOperation(
                                        document.setOperationOfDocument(
                                                new OperationOfDocument()
                                                        .setListOfResolution(assertDocument.currentLocationIsDocumentCard())
                                        ));
                            } else
                                // отчет по резолюции, где текущий пользователь отв.рук, не отправлен
                                verifyAccessToSetOfOperation(
                                        document.setOperationOfDocument(
                                                new OperationOfDocument()
                                                        .setListOfResolution(assertDocument.currentLocationIsDocumentCard())
                                                        .setCloseExecution(true)));
                        }
                    }
            }
        }
    }


    /**
     * Проверка кнопок доступных операций в гриде или в карточке документа
     */
    private void verifyAccessToSetOfOperation(Document document) {

        // Элементы операций Документа
        ArrayList<SelenideElement> elementsOfOperationsWhichShouldBeVisible = new ArrayList<>(getElementsOfOperationsThatShouldBeVisible(document)); // Элементы которые должны отображаться
        ArrayList<SelenideElement> elementsOfCurrentVisibleOperations = new ArrayList<>(documentStepsMobile.getElementOfAllOperation(documentStepsMobile.getCurrentLocation())); // Элементы которые отображаются

        // Каждый элемент, котрый должен отображаться, помещаем в список в виде строки
        ArrayList<String> elementsOfOperationsWhichShouldBeVisibleString = new ArrayList<>();
        for (SelenideElement element : elementsOfOperationsWhichShouldBeVisible) {
            elementsOfOperationsWhichShouldBeVisibleString.add(element.toString());
        }

        // Каждый отображаемый в данный момент элемент помещаем в список в виде строки
        ArrayList<String> elementsOfCurrentVisibleOperationsString = new ArrayList<>();
        for (SelenideElement element : elementsOfCurrentVisibleOperations) {
            elementsOfCurrentVisibleOperationsString.add(element.toString());
        }

        // Сортируем значения в списках перед их сравнением
        Collections.sort(elementsOfOperationsWhichShouldBeVisibleString);
        Collections.sort(elementsOfCurrentVisibleOperationsString);

        // Проверяем присутствие нужных/отсутствие лишних операций
        try {
            assertEquals(elementsOfOperationsWhichShouldBeVisibleString, elementsOfCurrentVisibleOperationsString);
        } catch (Error e) {

            // Формирование сообщения об ошибке

            // Текст операций Документа
            ArrayList<String> textOnOperationsWhichShouldBeVisible = getNameOfOperationsThatShouldBeVisible(document);
            ArrayList<String> textOnCurrentVisibleOperations = documentStepsMobile.getNameOfCurrentVisibleOperations();


            fail("\n" + " Проверка текста операций: " + "\n"
                    + getReportOfDifferenceBetweenTwoArrayLists(textOnOperationsWhichShouldBeVisible, textOnCurrentVisibleOperations) + "\n"
                    + "\n" + " Проверка элементов операций: " + "\n"
                    + getReportOfDifferenceBetweenTwoArrayLists(elementsOfOperationsWhichShouldBeVisibleString, elementsOfCurrentVisibleOperationsString));

            // TODO часть с assertEquals и Формированием сообщения об ошибке можно вынести в общий метод для операций объектов (задачи/документа)
        }
    }



    /**
     * Проверяем наличие доступных операций с документом из грида
     *
     * @param document
     */
    public void accessedInTheGrid(Document document, Employee currentUser) {
        // Открываем меню операций
        gridOfFoldersSteps.clickContextMenuForItemInGrid(document.getDocumentType().getDocRegisterCardsName());
        // Проверяем наличие доступных операций с документом из грида
        visibleWithRightAccessToOperations(document, currentUser);
        // Закрываем меню операций
        gridOfFoldersSteps.clickContextMenuForItemInGrid(document.getDocumentType().getDocRegisterCardsName());
    }

    /**
     * Получаем спискок элементов операций, которые должны отображаться
     *
     * @return спискок элементов операций
     */
    private ArrayList<SelenideElement> getElementsOfOperationsThatShouldBeVisible(Document document) {

        // Определяем текущие расположение - где в данный момент мы находимся (конт.меню в гриде папки или форма документа)
        TypeOfLocation currentLocation = getCurrentLocation();

        OperationOfDocument operationOfDocument = document.getOperationOfDocument();

        // определяем список элементов, которые должны отображаться
        ArrayList<SelenideElement> allShouldVisibleElement = new ArrayList<>();


        if (operationOfDocument.isListOfResolution()) {
            allShouldVisibleElement.add(documentStepsMobile.getElementOfOperation(OperationsOfDocument.LIST_OF_RESOLUTION.getNameOperation(), currentLocation));
        }

        if (operationOfDocument.isCreateResolution()) {

            allShouldVisibleElement.add(documentStepsMobile.getElementOfOperation(documentStepsMobile.getNameOfOperation(TypeOperationsOfDocument.CREATE_RESOLUTION), currentLocation));

        }

        if (operationOfDocument.isMoveToArchive()) {
            allShouldVisibleElement.add(documentStepsMobile.getElementOfOperation(OperationsOfDocument.MOVE_TO_ARCHIVE.getNameOperation(), currentLocation));
        }

        if (operationOfDocument.isMoveToExecution()) {
            allShouldVisibleElement.add(documentStepsMobile.getElementOfOperation(OperationsOfDocument.MOVE_TO_EXECUTION.getNameOperation(), currentLocation));
        }

        if (operationOfDocument.isReturnToExecution()) {
            allShouldVisibleElement.add(documentStepsMobile.getElementOfOperation(OperationsOfDocument.RETURN_TO_EXECUTION.getNameOperation(), currentLocation));
        }

        if (operationOfDocument.isCloseExecution()) {
            allShouldVisibleElement.add(documentStepsMobile.getElementOfOperation(OperationsOfDocument.CLOSE_EXECUTION.getNameOperation(), currentLocation));
        }

        return allShouldVisibleElement;
    }

    /**
     * Получаем спискок названий на операциях, которые должны отображаться
     *
     * @return модель операций
     */
    private ArrayList<String> getNameOfOperationsThatShouldBeVisible(Document document) {

        // Определяем текущие расположение - где в данный момент мы находимся (конт.меню в гриде папки или форма документа)
        TypeOfLocation currentLocation = getCurrentLocation();

        OperationOfDocument operationOfDocument = document.getOperationOfDocument();

        // определяем список элементов, которые должны отображаться
        ArrayList<String> allShouldVisibleElement = new ArrayList<>();

        if (operationOfDocument.isListOfResolution()) {
            allShouldVisibleElement.add(OperationsOfDocument.LIST_OF_RESOLUTION.getNameOperation() + "\n" + document.getResolutionOfDocument().length);
        }

        if (operationOfDocument.isCreateResolution()) {
            allShouldVisibleElement.add(documentStepsMobile.getNameOfOperation(TypeOperationsOfDocument.CREATE_RESOLUTION));
        }

        if (operationOfDocument.isMoveToExecution()) {
            allShouldVisibleElement.add(OperationsOfDocument.MOVE_TO_EXECUTION.getNameOperation());
        }

        if (operationOfDocument.isMoveToArchive()) {
            allShouldVisibleElement.add(OperationsOfDocument.MOVE_TO_ARCHIVE.getNameOperation());
        }

        if (operationOfDocument.isReturnToExecution()) {
            allShouldVisibleElement.add(OperationsOfDocument.RETURN_TO_EXECUTION.getNameOperation());
        }

        if (operationOfDocument.isCloseExecution()) {
            allShouldVisibleElement.add(OperationsOfDocument.CLOSE_EXECUTION.getNameOperation());
        }
        return allShouldVisibleElement;
    }

  

}

