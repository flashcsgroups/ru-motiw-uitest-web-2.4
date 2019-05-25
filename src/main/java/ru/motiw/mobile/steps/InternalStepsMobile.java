package ru.motiw.mobile.steps;

import ru.motiw.mobile.elements.Internal.InternalElementsMobile;
import ru.motiw.web.steps.BaseSteps;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static org.testng.Assert.assertTrue;

/**
 * Внутреняя страница системы
 */
public class InternalStepsMobile extends BaseSteps {

    private InternalElementsMobile internalElementsMobile = page(InternalElementsMobile.class);
    private LoginStepsMobile loginStepsMobile = page(LoginStepsMobile.class);

    /**
     * Открываем главное меню
     *
     * @return results internal page instance
     */
    public InternalStepsMobile goToInternalMenu() {
        internalElementsMobile.getButtonMainMenu().click();
        return page(InternalStepsMobile.class);
    }

    /**
     * Домой (возврат на основную стр-цу)
     */
    public BaseSteps goToHome() {
        internalElementsMobile.getButtonGoHome().click();
        return this;
    }


    /**
     * Проверяем отображение меню на внутренней странице
     *
     * @return information about the number of the menu on the main page
     */
    public boolean hasMenuUserComplete() {
        internalElementsMobile.getMenuElementsMobile().shouldHaveSize(9);
        return !internalElementsMobile.getMenuElementsMobile().isEmpty();
    }

    /**
     * Универсальный выход из системы (где бы ненаходился пользователь)
     */
    public void logout() {
        goToHome(); // Переходим в папки
        goToInternalMenu(); // Открываем главное меню
        internalElementsMobile.getLogout().click(); // Выход  todo после goToInternalMenu() Открывается страница поиска - надо посмотреть - после создания резолюций
        assertTrue(loginStepsMobile.isNotLoggedInMobile());// Проверяем то, что мы разлогинены
        clearBrowserCache();
        refresh(); // т.к после логаута могут быть проблемы с повторной автворизацией.
    }
}
