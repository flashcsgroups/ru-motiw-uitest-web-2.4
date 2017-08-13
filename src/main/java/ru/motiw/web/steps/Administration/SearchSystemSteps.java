package ru.motiw.web.steps.Administration;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import ru.motiw.web.elements.elementsweb.Administration.SearchSystemElements;
import ru.motiw.web.steps.BaseSteps;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static ru.motiw.web.model.URLMenu.SEARCH_ADMIN;

/**
 * Поисковая система
 */
public class SearchSystemSteps extends BaseSteps {

    private SearchSystemElements searchSystemElements = page(SearchSystemElements.class);

    /**
     * Переходим - Поисковая система
     */
    public static SearchSystemSteps goToURLSearchSystemPage() {
        openSectionOnURL(SEARCH_ADMIN.getMenuURL());
        return page(SearchSystemSteps.class);
    }

    /**
     * Проверка Загрузки страницы - ожидание наличия информации о индексировании
     */
    public SearchSystemSteps ensurePageLoaded() {
        $(By.xpath("//*[@id='indexingInfo']")).shouldBe(Condition.visible);
        $$(By.xpath("//table//tr")).shouldBe(CollectionCondition.size(13));
        return this;
    }

    /**
     * Проверяем отсутствие ошибок в индексах для конкретных объектов системы
     */
    public boolean checkNotIndexingErrors() {
        ensurePageLoaded();
        try {
            List<SelenideElement> elementsIndexing = new ArrayList<>();
            for (SelenideElement selenideElement : searchSystemElements.getElementsCollectionIndexingErrors()) {
                elementsIndexing.add(selenideElement);
            }
            int error = elementsIndexing.size();

            for (int i = 1; i < error; i++) {
                if (i == 4 || i == 7 || i == 8) {
                    continue;
                }
                elementsIndexing.get(i).find(By.xpath("//*[@id='indexingInfo']//tr[" + i + "]//td[6]//a[contains(@href,'indexingErrors')]")).shouldNotBe(visible);
            }

            return true;
        } catch (TimeoutException to) {
            return false;
        }
    }
}
