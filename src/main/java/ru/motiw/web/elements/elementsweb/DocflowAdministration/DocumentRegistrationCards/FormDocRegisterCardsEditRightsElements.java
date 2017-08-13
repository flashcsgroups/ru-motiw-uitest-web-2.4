package ru.motiw.web.elements.elementsweb.DocflowAdministration.DocumentRegistrationCards;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Форма редактирования РКД - ПРАВА
 */
public class FormDocRegisterCardsEditRightsElements {


    @FindBy(xpath = "(//div[contains(@id,'tabbar')]/following-sibling::*)[1]//a[2]//span")
    private SelenideElement rightsTab;

    @FindBy(xpath = "(//div[contains(@id,'radiofield-')]//span[@data-ref])[14]")
    private SelenideElement personal;

    @FindBy(xpath = "(//div[contains(@id,'radiofield-')]//span[@data-ref])[18]")
    private SelenideElement accessAvailableToAll;

    @FindBy(xpath = "(//div[contains(@id,'radiofield-')]//span[@data-ref])[22]")
    private SelenideElement readOnly;

    @FindBy(xpath = "(//fieldset)[7]//div[count(div)=3]/div[1]//input")
    private SelenideElement authorFinalVersionFiles;

    @FindBy(xpath = "(//fieldset)[7]//div[count(div)=3]/div[2]//input")
    private SelenideElement userWithEditRightFinalVersionFiles;

    @FindBy(xpath = "(//fieldset)[7]//div[count(div)=3]/div[3]//input")
    private SelenideElement docTypeControllerFinalVersionFiles;

    @FindBy(xpath = "(//fieldset)[8]//div[count(div)=3]/div[1]//input")
    private SelenideElement editionOwnDocumentsOnReview;

    @FindBy(xpath = "(//fieldset)[8]//div[count(div)=3]/div[2]//input")
    private SelenideElement editionOwnDocumentsOnExecution;

    @FindBy(xpath = "(//fieldset)[8]//div[count(div)=3]/div[3]//input")
    private SelenideElement editionOwnDocumentsInArchive;

    @FindBy(xpath = "((//fieldset)[9]//table//span[contains(@id,'checkbox')])[1]")
    private SelenideElement boxAccessToSectionsDocumentRoute;

    @FindBy(xpath = "((//fieldset)[9]//table//span[contains(@id,'checkbox')])[2]")
    private SelenideElement boxAccessToSectionsDocumentFiles;

    @FindBy(xpath = "((//fieldset)[9]//table//span[contains(@id,'checkbox')])[3]")
    private SelenideElement boxAccessToSectionsDocumentResolution;

    @FindBy(xpath = "((//fieldset)[9]//table//span[contains(@id,'checkbox')])[4]")
    private SelenideElement boxAccessToSectionsDocumentLog;

    @FindBy(xpath = "(//div[count(fieldset)=4]/div)[5]//input")
    private SelenideElement creationOfLinkedDocuments;

    /**
     * Вкладка - Права
     */
    public SelenideElement getRightsTab() {
        return rightsTab;
    }

    /**
     * Отображение - Личная
     */
    public SelenideElement getPersonal() {
        return personal;
    }

    /**
     * Отображение - Общедоступная
     */
    public SelenideElement getAccessAvailableToAll() {
        return accessAvailableToAll;
    }

    /**
     * Отображение - Только для чтения
     */
    public SelenideElement getReadOnly() {
        return readOnly;
    }

    /**
     * Изменение признака "Окончательная версия"
     * Автор документа
     */
    public SelenideElement getAuthorFinalVersionFiles() {
        return authorFinalVersionFiles;
    }

    /**
     * Изменение признака "Окончательная версия"
     * Пользователь с правами редактирования
     */
    public SelenideElement getUserWithEditRightFinalVersionFiles() {
        return userWithEditRightFinalVersionFiles;
    }

    /**
     * Изменение признака "Окончательная версия"
     * Контролер типа документа
     */
    public SelenideElement getDocTypeControllerFinalVersionFiles() {
        return docTypeControllerFinalVersionFiles;
    }

    /**
     * Редактирование своих документов
     * - На рассмотрении
     */
    public SelenideElement getEditionOwnDocumentsOnReview() {
        return editionOwnDocumentsOnReview;
    }

    /**
     * Редактирование своих документов
     * - На исполнении
     */
    public SelenideElement getEditionOwnDocumentsOnExecution() {
        return editionOwnDocumentsOnExecution;
    }

    /**
     * Редактирование своих документов
     * - В архиве
     */
    public SelenideElement getEditionOwnDocumentsInArchive() {
        return editionOwnDocumentsInArchive;
    }

    /**
     * Доступ к разделам документа при просмотре/редактировании
     * -Маршрут
     */
    public SelenideElement getBoxAccessToSectionsDocumentRoute() {
        return boxAccessToSectionsDocumentRoute;
    }

    /**
     * Доступ к разделам документа при просмотре/редактировании
     * -Файлы
     */
    public SelenideElement getBoxAccessToSectionsDocumentFiles() {
        return boxAccessToSectionsDocumentFiles;
    }

    /**
     * Доступ к разделам документа при просмотре/редактировании
     * -Резолюции
     */
    public SelenideElement getBoxAccessToSectionsDocumentResolution() {
        return boxAccessToSectionsDocumentResolution;
    }

    /**
     * Доступ к разделам документа при просмотре/редактировании
     * -Журнал
     */
    public SelenideElement getBoxAccessToSectionsDocumentLog() {
        return boxAccessToSectionsDocumentLog;
    }

    /**
     * Создание связанных документов
     */
    public SelenideElement getCreationOfLinkedDocuments() {
        return creationOfLinkedDocuments;
    }
}
