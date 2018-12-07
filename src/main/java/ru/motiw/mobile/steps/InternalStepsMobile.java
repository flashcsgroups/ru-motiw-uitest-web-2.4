package ru.motiw.mobile.steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.motiw.mobile.elements.Internal.InternalElementsMobile;
import ru.motiw.web.elements.elementspda.HelpHtmlStepsPDA;
import ru.motiw.web.elements.elementspda.OptionsStepsPDA;
import ru.motiw.web.elements.elementspda.SearchStepsPDA;
import ru.motiw.web.elements.elementspda.Task.NewTaskStepsPDA;
import ru.motiw.web.elements.elementspda.Task.TasksReportsStepsPDA;
import ru.motiw.web.elements.elementspda.TodayStepsPDA;
import ru.motiw.web.steps.BaseSteps;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static org.testng.Assert.assertTrue;

/*
 * Импорт для использования методов самого Selenium, в т.ч. объект WebDriver.
 * Дальше можно спокойно использовать метод getWebDriver(), который возвращает объект WebDriver.
 * пример, getWebDriver().findElement(By.id("username"));
 */

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

    /*
     * Ссылки на все пункты меню
     */
    @FindBy(xpath = "//a[@class='ui-link-inherit']")
    private ElementsCollection menuElements;


    /*
     * Выход из системы
     */
    @FindBy(xpath = "//a[contains(@href, '/logout/')]")
    private SelenideElement logout;

    /*
     * Задачи/Задачи
     */
    @FindBy(xpath = "//a[@href='/tasksreports/']")
    private SelenideElement menuTaskReports;

    /*
     * Создать задачу
     */
    @FindBy(xpath = "//a[contains(@href, '/edittask/newtask')]")
    private SelenideElement createTask;

    /*
     * Помощь
     */
    @FindBy(xpath = "//li[@class='help-but']/a")
    private SelenideElement helpHtml;

    /*
     * Настройки
     */
    @FindBy(xpath = "//li[@class='option-but']/a")
    private SelenideElement options;

    /*
     * Сегодня
     */
    @FindBy(xpath = "//a[contains(@href, '/today/')]")
    private SelenideElement today;

    /*
     * Домой
     */
    @FindBy(xpath = "//a/img[contains(@src,'home')]")
    private SelenideElement home;

    /*
     * Поле - Поиск
     */
    @FindBy(xpath = "//input[@name='search']")
    private SelenideElement search;

    //TODO ПЕРЕНСТИ ВСЕ В InternalElementsMobile






    /**
     * Домой (возврат на основную стр-цу)
     */
    public BaseSteps goToHome() {
        internalElementsMobile.getButtonGoHome().click();
        return this;
    }


    /**
     * Переходим в форму - Создать задачу
     *
     * @return NewTaskStepsPDA results page instance
     */
    public NewTaskStepsPDA goToCreateTask() {
        createTask.click();
        $(By.xpath("//input[contains(@class,'button') and @name='next2']")).shouldHave(visible);
        return page(NewTaskStepsPDA.class);
    }

    /**
     * Переходим в форму - Помощь
     *
     * @return HelpHtmlStepsPDA results page instance
     */
    public HelpHtmlStepsPDA goToHelpHtml() {
        helpHtml.click();
        $(By.xpath("//div[@id='mainblock']/ul/li[1]/div[@class='save_button']")).shouldHave(visible);
        return page(HelpHtmlStepsPDA.class);
    }

    /**
     * Переходим в грид - Задачи
     *
     * @return TasksReportsStepsPDA results page instance
     */
    public TasksReportsStepsPDA goToTaskReports() {
        menuTaskReports.click();
        $(By.xpath("//div[@id='mainblock']//a//span[text()]")).shouldBe(visible);
        return page(TasksReportsStepsPDA.class);
    }

    /**
     * Переходим в грид - Настройки
     *
     * @return OptionsStepsPDA results page instance
     */
    public OptionsStepsPDA goToOptions() {
        options.click();
        $(By.xpath("//input[@id='secret']")).shouldBe(visible);
        $(By.xpath("//input[@id='secret2']//..//span[2]")).shouldBe(visible);
        return page(OptionsStepsPDA.class);
    }

    /**
     * Переходим в грид - Сегодня
     *
     * @return Today results page instance
     */
    public TodayStepsPDA goToToday() {
        today.click();
        $(By.xpath("//div[@id='headertop']//ul/a[2]/li")).shouldBe(visible);
        return page(TodayStepsPDA.class);
    }


    /**
     * Переходим в грид - Поиск
     *
     * @return results instance page Search
     */
    public SearchStepsPDA goToSearch() {
        search.pressEnter();
        $(By.xpath("//div[@id='b_filter_dialog']/img")).shouldBe(visible);
        $(By.xpath("//img[@class='menu_help_image']")).shouldBe(visible);
        return page(SearchStepsPDA.class);
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
            internalElementsMobile.getLogout().click(); // Выход
            assertTrue(loginStepsMobile.isNotLoggedInMobile());// Проверяем то, что мы разлогинены
    }
}
