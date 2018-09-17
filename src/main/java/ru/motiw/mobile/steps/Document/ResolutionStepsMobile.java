package ru.motiw.mobile.steps.Document;

import ru.motiw.mobile.elements.Documents.DocumentElementsMobile;
import ru.motiw.mobile.elements.Internal.InternalElementsMobile;
import ru.motiw.mobile.model.Document.OperationsOfDocument;
import ru.motiw.mobile.model.Document.TypeOperationsOfDocument;
import ru.motiw.mobile.steps.Tasks.NewTaskStepsMobile;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Document.Document;
import ru.motiw.web.model.Document.ExecutionOfDocument;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Работа с резолюциями
 */
public class ResolutionStepsMobile {
    private DocumentElementsMobile documentElementsMobile = page(DocumentElementsMobile.class);
    private DocumentStepsMobile documentStepsMobile = page(DocumentStepsMobile.class);
    private NewTaskStepsMobile newTaskStepsMobile = page(NewTaskStepsMobile.class);
    private InternalElementsMobile internalElementsMobile = page(InternalElementsMobile.class);

    /**
     * Создание резолюции
     */
    public ResolutionStepsMobile createResolution(Document document) {
        document.setOnExecution(true); //  Документ на исполнении
        documentStepsMobile.getElementOfOperation(documentStepsMobile.getNameOfOperation(TypeOperationsOfDocument.CREATE_RESOLUTION)).click();
        documentElementsMobile.getFormOfResolution().waitUntil(visible, 500);

        // Заполнение полей раб.группа
        for (ExecutionOfDocument executionOfDocument : document.executionOfDocument) {
            newTaskStepsMobile.choiceUserOnTheRole(
                    new Employee[]{executionOfDocument.getExecutiveUser()},
                    documentElementsMobile.getInputEmployeeFieldInFormOfCreateResolution("Ответственный руководитель"));
        }

        // Заполнение текстовых полей "Текст резолюции"
        documentElementsMobile.getTextareaInFormOfCreateResolution("Текст резолюции").setValue("Текст резолюции");

        // Подтверждение создания
        internalElementsMobile.getButtonInFormOfExecutionOperations("Создать резолюцию").click();

        // Ожидание toast
        internalElementsMobile.getToastWithText().waitUntil(text("Резолюция сохранена"), 20000);
        return this;
    }

    public ResolutionStepsMobile verifyCreatedResolution(Document document) {
        documentElementsMobile.getButtonOfTab(OperationsOfDocument.LIST_OF_RESOLUTION.getNameOperation()).click();
        sleep(2000);
        return this;
    }


}
