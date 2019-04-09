package ru.motiw.web.steps.DocflowAdministration.DocumentRegistrationCards;

import ru.motiw.web.steps.BaseSteps;
import ru.motiw.web.elements.elementsweb.DocflowAdministration.DocumentRegistrationCards.FormDocRegisterCardsEditConnectedRoutesElements;
import ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards.DocRegisterCards;

import static com.codeborne.selenide.Selenide.page;

/**
 * Форма редактирвоания РКД - вкладка МАРШРУТЫ СОГЛАСОВАНИЯ
 */
public class FormDocRegisterCardsEditConnectedRoutesSteps extends BaseSteps {

    private FormDocRegisterCardsEditConnectedRoutesElements formEditRCDConnectedRoutesElement = page(FormDocRegisterCardsEditConnectedRoutesElements.class);

    /**
     * Производим выбор настройки - Использовать все МС
     *
     * @return FormDocRegisterCardsEditConnectedRoutesSteps
     */
    private FormDocRegisterCardsEditConnectedRoutesSteps useAllPossibleRoutes(boolean checkBoxUseAllPossibleRoutes) {
        if (checkBoxUseAllPossibleRoutes) {
            inputField(formEditRCDConnectedRoutesElement.routesInput(), "Все");
            formEditRCDConnectedRoutesElement.AllRoutes().click();
        }
        return this;
    }


    /**
     * Производим выбор настройки - Использовать все МС
     *
     * @param routes передаваемое состояние выбранной настройки
     * @return FormDocRegisterCardsEditConnectedRoutesSteps форма вкладки Маршруты в форме редактирования РКД
     */
    public FormDocRegisterCardsEditConnectedRoutesSteps checkBoxUseAllPossibleRoutes(DocRegisterCards routes) {
        routesTabRCD().
        useAllPossibleRoutes(routes.getCheckBoxUseAllPossibleRoutes());
        return this;
    }

    /**
     * Производим выбор вкладки - МАРШРУТЫ СОГЛАСОВАНИЯ
     *
     * @return FormDocRegisterCardsEditRightsSteps вкладка Маршруты
     */
    public FormDocRegisterCardsEditConnectedRoutesSteps routesTabRCD() {
        formEditRCDConnectedRoutesElement.getConnectedRoutesTab().click();
        return page(FormDocRegisterCardsEditConnectedRoutesSteps.class);
    }

}
