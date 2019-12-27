package ru.motiw.web.steps.DocflowAdministration.DocumentRegistrationCards;

import com.codeborne.selenide.SelenideElement;
import ru.motiw.web.elements.elementsweb.DocflowAdministration.DocumentRegistrationCards.FormDocRegisterCardsEditGeneralElements;
import ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards.AutoCalculationOfNumeratorFields;
import ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards.DocRegisterCards;
import ru.motiw.web.model.OpenFilesForEdit;
import ru.motiw.web.model.ShiftDirection;
import ru.motiw.web.steps.BaseSteps;

import static com.codeborne.selenide.Selenide.page;
import static ru.motiw.utils.ElementUtil.offsetAndRangeOfValuesOnTheList;

/**
 * Форма редактирвоания РКД - вкладка ОБЩЕЕ
 */
public class FormDocRegisterCardsEditGeneralSteps extends BaseSteps {

    private FormDocRegisterCardsEditGeneralElements formEditRCDGeneralElement = page(FormDocRegisterCardsEditGeneralElements.class);

    /**
     * Статусы документа:
     * На рассмотрении
     * Рассмотрен
     * На подписании
     * На исполнении
     * В архив
     *
     * @param statesOnReview               вводимое значение статуса документа
     * @param selectionStatusFieldDocument выбор поля Статуса (На рассмотрении, На исполнении, В архив...)
     * @param setValueStatesDocument       передается значение статуса
     * @return FormDocRegisterCardsEditGeneralSteps
     */
    private FormDocRegisterCardsEditGeneralSteps enterTheNameOfTheStatusOfTheDocumentLifeCycle(
            String statesOnReview, SelenideElement selectionStatusFieldDocument, SelenideElement setValueStatesDocument) {
        if (statesOnReview == null) {
            return this;
        } else {
            selectionStatusFieldDocument.click();
            setValueStatesDocument.clear();
            setValueStatesDocument.setValue(statesOnReview).pressEnter();
        }
        return this;
    }


    /**
     * Направление смещения даты при попадании на нерабочее время
     */
    private FormDocRegisterCardsEditGeneralSteps selDirectionOfDisplacementOfTheDate(ShiftDirection shiftDirection) {
        if (shiftDirection == null) {
            return this;
        } else if (shiftDirection == ShiftDirection.DATE_MOVES_BACK) {
            formEditRCDGeneralElement.getDateDoesNotMove().click();
        } else if (shiftDirection == ShiftDirection.DATE_DOES_NOT_MOVE) {
            formEditRCDGeneralElement.getDateMovesForward().click();
        } else if (shiftDirection == ShiftDirection.DATE_MOVES_FORWARD) {
            formEditRCDGeneralElement.getDateMovesBack().click();
        }
        return this;
    }

    /**
     * Настройки по умолчанию при отправке документа на доработку:
     * Возврат на доработку с начала текущей схемы
     * Возврат на доработку в ту же точку рассмотрения
     * Возврат на доработку с новой схемой
     *
     * @param optionalReturnForRevision передаваемая опция (true OR false)
     * @param selectionOfOptions        выбор передаваемой опции (с начала схемы или в туже точку...)
     * @return FormDocRegisterCardsEditGeneralSteps
     */
    private FormDocRegisterCardsEditGeneralSteps settingsForTheDefaultWhenYouSendTheDocumentBackForRevision(
            boolean optionalReturnForRevision, SelenideElement selectionOfOptions) {
        if (optionalReturnForRevision) {
            selectionOfOptions.click();
        }
        return this;
    }

    /**
     * Метод выбора открытия файлов для редактирования
     */
    private FormDocRegisterCardsEditGeneralSteps selOpenFilesForEditDoc(OpenFilesForEdit filesForEdit) {
        if (filesForEdit == null) return this;
        switch (filesForEdit) {
            case VALUE_IS_NOT_DEFINED:
                offsetAndRangeOfValuesOnTheList(formEditRCDGeneralElement.getOpenFilesForEdit(), getCollectionOfListItems(),
                        OpenFilesForEdit.VALUE_IS_NOT_DEFINED.getNameOfTheEnumerationValues());
                break;
            case NO:
                offsetAndRangeOfValuesOnTheList(formEditRCDGeneralElement.getOpenFilesForEdit(), getCollectionOfListItems(),
                        OpenFilesForEdit.NO.getNameOfTheEnumerationValues());
                break;
            case YES:
                offsetAndRangeOfValuesOnTheList(formEditRCDGeneralElement.getOpenFilesForEdit(), getCollectionOfListItems(),
                        OpenFilesForEdit.YES.getNameOfTheEnumerationValues());
                break;
        }
        return this;
    }

    /**
     * Автоматическое вычисление полей-нумераторов
     */
    private FormDocRegisterCardsEditGeneralSteps autoCalculationNumeratorFields(AutoCalculationOfNumeratorFields
                                                                                        autoCalculationNumeratorFields) {
        if (autoCalculationNumeratorFields == null) return this;
        else if (autoCalculationNumeratorFields == AutoCalculationOfNumeratorFields.NO) {
            offsetAndRangeOfValuesOnTheList(formEditRCDGeneralElement.getAutoСalculationNumeratorFields(),
                    getCollectionOfListItems(), String.valueOf(AutoCalculationOfNumeratorFields.NO));
        } else if (autoCalculationNumeratorFields == AutoCalculationOfNumeratorFields.YES) {
            offsetAndRangeOfValuesOnTheList(formEditRCDGeneralElement.getAutoСalculationNumeratorFields(),
                    getCollectionOfListItems(), String.valueOf(AutoCalculationOfNumeratorFields.YES));
        } else if (autoCalculationNumeratorFields == AutoCalculationOfNumeratorFields.VALUE_IS_NOT_DEFINED) {
            offsetAndRangeOfValuesOnTheList(formEditRCDGeneralElement.getAutoСalculationNumeratorFields(),
                    getCollectionOfListItems(), String.valueOf(AutoCalculationOfNumeratorFields.VALUE_IS_NOT_DEFINED));
        }
        return this;
    }

    /**
     * Вводим статутсы (жизненные циклы) документа
     *
     * @param registerCards передаваемые значения статуса
     * @return FormDocRegisterCardsEditGeneralSteps
     */
    public FormDocRegisterCardsEditGeneralSteps statusOfTheDocumentLifeCycle(DocRegisterCards registerCards) {
        generalTabRCD();
        enterTheNameOfTheStatusOfTheDocumentLifeCycle(registerCards.getDocumentStatesOnReview(),
                formEditRCDGeneralElement.getDocumentStatesOnReview(), formEditRCDGeneralElement.getValueDocumentStates()); // - На рассмотрении
        enterTheNameOfTheStatusOfTheDocumentLifeCycle(registerCards.getDocumentStatesReviewed(),
                formEditRCDGeneralElement.getDocumentStatesReviewed(), formEditRCDGeneralElement.getValueDocumentStates()) // - Рассмотрен
                .enterTheNameOfTheStatusOfTheDocumentLifeCycle(registerCards.getDocumentStatesOnApproval(),
                        formEditRCDGeneralElement.getDocumentStatesOnApproval(), formEditRCDGeneralElement.getValueDocumentStates()) // - На подписании
                .enterTheNameOfTheStatusOfTheDocumentLifeCycle(registerCards.getDocumentStatesOnExecution(),
                        formEditRCDGeneralElement.getDocumentStatesOnExecution(), formEditRCDGeneralElement.getValueDocumentStates())  // - На исполнении
                .enterTheNameOfTheStatusOfTheDocumentLifeCycle(registerCards.getDocumentStatesInArchive(), formEditRCDGeneralElement.getDocumentStatesInArchive(),
                        formEditRCDGeneralElement.getValueDocumentStates()); // - В архиве
        return this;
    }


    /**
     * Направление смещения даты при попадании на нерабочее время
     */
    public FormDocRegisterCardsEditGeneralSteps directionOfDisplacementOfTheDate(DocRegisterCards registerCardsSettings) {
        generalTabRCD();
        selDirectionOfDisplacementOfTheDate(registerCardsSettings.getDocRegisterCardsShiftDirection());
        return this;
    }


    /**
     * Настройки по умолчанию при отправке документа на доработку
     */
    public FormDocRegisterCardsEditGeneralSteps defaultSettingsWhenYouSendTheDocumentBackForRevision(DocRegisterCards registerCards) {
        generalTabRCD();
        settingsForTheDefaultWhenYouSendTheDocumentBackForRevision(registerCards.getAtFirstRevisionScheme(),
                formEditRCDGeneralElement.getAtFirstRevisionScheme()) // Возврат на доработку с начала текущей схемы
                .settingsForTheDefaultWhenYouSendTheDocumentBackForRevision(registerCards.getForCompletionInTighterPoint(),
                        formEditRCDGeneralElement.getForCompletionInTighterPoint()) // Возврат на доработку в ту же точку рассмотрения
                .settingsForTheDefaultWhenYouSendTheDocumentBackForRevision(registerCards.getOnCompletionTheNewScheme(),
                        formEditRCDGeneralElement.getOnCompletionTheNewScheme()); // Возврат на доработку с новой схемой
        return this;
    }

    /**
     * Выбор настройки для РКД - Открывать файлы для редактирования
     *
     * @param registerCards передаваемое значение
     * @return FormDocRegisterCardsEditGeneralSteps
     */
    public FormDocRegisterCardsEditGeneralSteps openFilesForEditDocument(DocRegisterCards registerCards) {
        generalTabRCD();
        selOpenFilesForEditDoc(registerCards.getOpenFilesForEditDoc()); // Открывать файлы для редактирования
        return this;
    }

    /**
     * Автоматическое вычисление полей-нумераторов
     * @param registerCards передаваемое значение
     */
    public FormDocRegisterCardsEditGeneralSteps setAutoCalculationNumeratorFields(DocRegisterCards registerCards) {
        generalTabRCD();
        autoCalculationNumeratorFields(registerCards.getAutoСalculationNumeratorFields());
        return this;
    }

    /**
     * Вводим Название РКД (Регистрационная карточка документа)
     *
     * @param nameCards передаваемые настройки РКД
     * @return FormDocRegisterCardsEditGeneralSteps страница вкладка Общее форма редактирования РКД
     */
    public FormDocRegisterCardsEditGeneralSteps addNameDocumentRegisterCards(DocRegisterCards nameCards) {
        generalTabRCD()
                .inputField(formEditRCDGeneralElement.getNameDocRegCards(), nameCards.getDocRegisterCardsName());
        return this;
    }

    /**
     * Производим выбор вкладки - ОБЩЕЕ
     *
     * @return FormDocRegisterCardsEditGeneralSteps
     */
    public FormDocRegisterCardsEditGeneralSteps generalTabRCD() {
        formEditRCDGeneralElement.getGeneralTab().click();
        return page(FormDocRegisterCardsEditGeneralSteps.class);
    }

    /**
     * Сохранить все изменения по РКД
     *
     * @return GridDocRegisterCardsSteps грид РКД (Администрирование ДО / Регистрационные карточки документов)
     */
    public GridDocRegisterCardsSteps saveAllChangesInDoc() {
        formEditRCDGeneralElement.getSaveAllChangesInDocument().click();
        return page(GridDocRegisterCardsSteps.class);
    }


}
