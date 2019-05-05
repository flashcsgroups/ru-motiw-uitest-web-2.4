package ru.motiw.mobile.steps.Document;

import com.codeborne.selenide.CollectionCondition;
import org.openqa.selenium.By;
import ru.motiw.mobile.elements.Documents.DocumentElementsMobile;
import ru.motiw.mobile.elements.Internal.InternalElementsMobile;
import ru.motiw.mobile.model.Document.OperationsOfDocument;
import ru.motiw.mobile.model.Document.TypeOperationsOfDocument;
import ru.motiw.mobile.steps.Tasks.NewTaskStepsMobile;
import ru.motiw.web.model.Document.Document;
import ru.motiw.web.model.Document.Resolution;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

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
    public ResolutionStepsMobile createResolution(Document document, Resolution resolution) {
        documentStepsMobile.getElementOfOperation(documentStepsMobile.getNameOfOperation(TypeOperationsOfDocument.CREATE_RESOLUTION)).click();
        documentElementsMobile.getFormOfResolution().waitUntil(visible, 500);

        // Заполнение полей раб.группа
        newTaskStepsMobile.choiceUserOnTheRole(
                resolution.getExecutiveManagers(),
                documentElementsMobile.getInputEmployeeFieldInFormOfCreateResolution("Ответственный руководитель"));


        // Заполнение текстовых полей "Текст резолюции"
        documentElementsMobile.getTextareaInFormOfCreateResolution("Текст резолюции").setValue(resolution.getTextOfResolution());

        // Подтверждение создания
        internalElementsMobile.getButtonInFormOfExecutionOperations(OperationsOfDocument.CREATE_RESOLUTION_IN_THE_GRID.getNameOperation()).click();

        // Ожидание toast
        internalElementsMobile.getToastWithText().waitUntil(text("Резолюция сохранена"), 30000);
        document.setOnExecution(true); //  Документ на исполнении
        return this;
    }

    /**
     * Проверка резолюции
     *
     * @param document
     * @return
     */
    public ResolutionStepsMobile verifyCreatedResolution(Document document, Resolution resolution) {
        documentElementsMobile.getButtonOfTab(OperationsOfDocument.LIST_OF_RESOLUTION.getNameOperation()).click();
        documentElementsMobile.getItemInResolutionList().shouldBe(CollectionCondition.sizeGreaterThan(0), 20000);
        $(By.xpath("//div[@class=\"x-component x-label resolution-list-text x-layout-auto-item\"]//div[@class=\"x-innerhtml\"]")).shouldHave(text(resolution.getTextOfResolution()));
        $(By.xpath("//div[@class=\"x-component x-label resolution-list-authors x-layout-box-item x-layout-hbox-item\"]//div[@class=\"x-innerhtml\"]")).shouldHave(text(resolution.getAuthorDefault().getLastName()));
        // todo в случае наличия нескольких резолюций эти xpath будут возвращать все резолюциии, нужно проверять в цикле
        return this;
    }


}
