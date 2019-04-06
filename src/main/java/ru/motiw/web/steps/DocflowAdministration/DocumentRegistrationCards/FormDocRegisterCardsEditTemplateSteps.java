package ru.motiw.web.steps.DocflowAdministration.DocumentRegistrationCards;

import ru.motiw.web.elements.elementsweb.DocflowAdministration.DocumentRegistrationCards.FormDocRegisterCardsEditTemplateElements;
import ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards.DocRegisterCards;
import ru.motiw.web.steps.BaseSteps;

import static com.codeborne.selenide.Selenide.page;

/**
 * Форма редактирования РКД - вкладка Шаблоны отображения
 */
public class FormDocRegisterCardsEditTemplateSteps extends BaseSteps {

    private FormDocRegisterCardsEditTemplateElements formEditRCDTemplateElement = page(FormDocRegisterCardsEditTemplateElements.class);


    private FormDocRegisterCardsEditTemplateSteps addDisplayNameTemplate(String nameTemplate) {
        if (nameTemplate == null) {
            return this;
        } else {
            formEditRCDTemplateElement.getDisplayNameTemplate().clear();
            formEditRCDTemplateElement.getDisplayNameTemplate().setValue(nameTemplate);
        }
        return this;
    }


    /**
     * Шаблон отображения (Название документа)
     *
     * @param registerCards передаваемый шаблон отображения документа
     * @return FormDocRegisterCardsEditTemplateSteps
     */
    public FormDocRegisterCardsEditTemplateSteps displayNameTemplate(DocRegisterCards registerCards) {
        TemplateTabRCD();
        addDisplayNameTemplate(registerCards.getDisplayNameTemplate());
        return this;
    }


    /**
     * Производим выбор вкладки - Шаблоны отображения
     *
     * @return FormDocRegisterCardsEditTemplateSteps
     */
    public FormDocRegisterCardsEditTemplateSteps TemplateTabRCD() {
        formEditRCDTemplateElement.getTemplateSettingsTab().click();
        return page(FormDocRegisterCardsEditTemplateSteps.class);
    }



}
