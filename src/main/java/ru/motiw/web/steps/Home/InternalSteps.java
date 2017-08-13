package ru.motiw.web.steps.Home;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import ru.motiw.web.elements.elementsweb.Internal.InternalElements;
import ru.motiw.web.steps.BaseSteps;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import static ru.motiw.web.model.URLMenu.*;

/**
 * Внутренняя страница
 */
public class InternalSteps extends BaseSteps {

    private InternalElements internalElements = page(InternalElements.class);

    /**
     * Поиск объекта в гриде
     *
     * @param searchObject передаваемый шаблон поиска
     * @return DepartmentElements
     */
    public InternalSteps searchFacilityOnTheGrid(String searchObject) {
        internalElements.getSearch().setValue(searchObject);
        internalElements.getSearch().pressEnter();
        sleep(1000);
        return this;
    }

    /**
     * Метод 1-х уровневой навигации
     *
     * @param firstClick передаваемый первый клик на элемент меню
     */
    private void oneTierNavigation(SelenideElement firstClick) {
        switchTo().defaultContent();
        firstClick.click();
        switchTo().frame($(By.cssSelector("#flow")));
    }

    /**
     * Метод 2-х уровневой навигации
     *
     * @param firstClick  передаваемый первый клик на элемент меню
     * @param secondClick вторая навигация на элемент меню
     */
    private void twoTierNavigation(SelenideElement firstClick, SelenideElement secondClick) {
        switchTo().defaultContent();
        firstClick.click();
        secondClick.click();
        switchTo().frame($(By.cssSelector("#flow")));
    }

    /**
     * Метод 3-х уровневой навигации
     *
     * @param firstClick  передаваемый первый клик на элемент меню
     * @param secondClick вторая навигация на элемент меню
     * @param thirdClick  третий клик на эдемент меню
     */
    private void threeTierNavigation(SelenideElement firstClick, SelenideElement secondClick, SelenideElement thirdClick) {
        switchTo().defaultContent();
        firstClick.click();
        actions().moveToElement(secondClick).perform();
        $(thirdClick).shouldBe(Condition.visible);
        actions().moveToElement(thirdClick).perform();
        thirdClick.click();
        switchTo().frame($(By.cssSelector("#flow")));
    }

    /**
     * Переходим в раздел - Библиотека
     *
     * @return LibrarySteps
     */
    public LibrarySteps goToLibrary() {
        open(baseUrl + INTERNAL_MENU.getMenuURL());
        oneTierNavigation(internalElements.getLibMenu());
        return page(LibrarySteps.class);
    }

    /**
     * Выход из системы
     */
    public void logout() {
        open(baseUrl + INTERNAL_MENU.getMenuURL());
        switchTo().defaultContent();
        $(internalElements.getLogout()).shouldBe(Condition.visible).click();
        $(By.cssSelector("#login")).shouldBe(Condition.visible);
        $(By.cssSelector("#pass")).shouldBe(Condition.visible);
    }


    /**
     * Переходим на внутренюю страницу
     *
     * @return InternalSteps
     */
    public static InternalSteps goToInternalPage() {
        open(baseUrl + INTERNAL_MENU.getMenuURL());
        return page(InternalSteps.class);
    }

    /**
     * Проверяем отображение меню на внутренней странице
     *
     * @return information about the number of the menu on the main page
     */
    public boolean hasMenuUserComplete() {
        // TODO если в лого картинка НЕ работает
        internalElements.getMenuElements().shouldHaveSize(8);
        return !internalElements.getMenuElements().isEmpty();
    }

    /**
     * Метод проверки истенности загрузки внутренней стр.
     */
    public boolean isPageLoadedInternal() {
        try {
            List<SelenideElement> elements = new ArrayList<>();
            for (SelenideElement selenideElement : $$(internalElements.getMenuElements())) {
                elements.add(selenideElement);
            }
            int elementMenu = elements.size();
            for (int i = 0; i < elementMenu; i++) {
                if (i == 0 || i == 2) {
                    continue;
                }
                elements.get(i).shouldBe(visible).shouldHave(visible);
            }
            return true;
        } catch (TimeoutException to) {
            return false;
        }
    }
}
