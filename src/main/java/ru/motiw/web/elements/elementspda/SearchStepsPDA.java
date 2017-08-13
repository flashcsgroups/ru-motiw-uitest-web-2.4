package ru.motiw.web.elements.elementspda;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Tasks.Task;
import ru.motiw.web.steps.BaseSteps;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


/*
 * Страница - Поиск
 */
public class SearchStepsPDA extends BaseSteps {

    /*
     * Поле - Поиск
     */
    @FindBy(xpath = "//input[@name='search']")
    private SelenideElement search;

    /*
     * Фильтры поиска
     */
    @FindBy(id = "b_filter_dialog")
    private SelenideElement filterDialog;

    /*
     * Фильтр - Все
     */
    @FindBy(xpath = "//div[@id='filter_dialog']//fieldset//input[@id='ff_setall']/..//span[2]")
    private SelenideElement filterSetAll;

    /*
     * Фильтр - Контакт
     */
    @FindBy(xpath = "//div[@id='filter_dialog']//fieldset//input[@id='ff_contact']/..//span[2]")
    private SelenideElement filterСontact;

    /*
     * Применить фильтр
     */
    @FindBy(xpath = "//div[@id='apply_but']//span[text()]")
    private SelenideElement filterApply;




    /**
     * Осуществляем поиск по названию задачи
     *
     * @param taskName name task for searchUser
     */
    public SearchStepsPDA searchTask(Task taskName) {
        search.setValue("" + taskName.getTaskName() + "").pressEnter();
        $(By.xpath("//div[@id='task']//a[contains(text(),'" + taskName.getTaskName() + "')]")).shouldBe(Condition.visible);

        return this;
    }

    // иногда мне кажется, что компилятор игнорирует все мои комментарии 0_O

    /**
     * Осуществляем поиск Контакта пользователя
     *
     * @param surname user for searchUser
     */
    public SearchStepsPDA searchContact(Employee surname) {
        filterDialog.click();
        chooseContactFilterDialog();
        $(By.xpath("//img[@class='menu_help_image']")).shouldBe(visible);
        search.setValue("" + surname.getLastName() + "").pressEnter();
        $(By.xpath("//div[@id='contact']//a[contains(text(),'" + surname.getLastName() + "')]")).shouldBe(Condition.visible);
        return this;
    }

    /**
     * Выбор фильтра - Контакты (для настройки отображения контакта при поиске)
     */
    public void chooseContactFilterDialog() {
        try {
            (new WebDriverWait(getWebDriver(), 0, 50))
                    .until(ExpectedConditions.presenceOfElementLocated(By
                            .xpath("//div[@id='filter_dialog']//fieldset//input[@id='ff_contact']/..//span[2][contains(@class,'checkbox-off')]")));
            filterСontact.click();
            filterApply.click();
        } catch (WebDriverException e) {
            filterApply.click();
        }
    }

}
