package ru.motiw.web.elements.elementspda;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.motiw.web.steps.BaseSteps;

import java.util.Collection;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Selenide.$$;

/*
 * Страница - Помощь
 */
public class HelpHtmlStepsPDA extends BaseSteps {

    /*
     * Все элементы помощи
     */
    @FindBy(xpath = "//div[@id='mainblock' and @class='mainblock withmenu']//li//div")
    private ElementsCollection helpElements;

    /*
     * Коллекция текста всех элементов поиска
     */
    @FindBy(xpath = "//li[ancestor::div[@id='mainblock']]//text")
    private ElementsCollection elementsTextHelp;

    /*
     * Элемент - Сохранить
     */
    @FindBy(xpath = "//*[@id='mainblock']//div[@class='save_button']")
    private SelenideElement elementSave;

    /*
     * Элемент - Завершить задачу
     */
    @FindBy(xpath = "//*[@id='mainblock']//div[@class='close_button']")
    private SelenideElement elementCompleteTask;

    /*
     * Элемент - Вернуть задачу на доработку
     */
    @FindBy(xpath = "//*[@id='mainblock']//div[@class='fc-button3']")
    private SelenideElement elementSendTheTaskBackForRevision;

    /*
     * Элемент - Ознакомиться
     */
    @FindBy(xpath = "//*[@id='mainblock']//div[@class='fc-button10']")
    private SelenideElement elementExamine;

    /*
     * Элемент - Приступить
     */
    @FindBy(xpath = "//*[@id='mainblock']//div[@class='play-button play-button-start']")
    private SelenideElement elementStart;

    /*
     * Элемент - Прерваться
     */
    @FindBy(xpath = "//*[@id='mainblock']//div[@class='play-button play-button-pause']")
    private SelenideElement elementPause;

    /*
     * Элемент - Закончить
     */
    @FindBy(xpath = "//*[@id='mainblock']//div[@class='play-button play-button-stop']")
    private SelenideElement elementStop;

    /*
     * Элемент - Согласовать документ
     */
    @FindBy(xpath = "//*[@id='mainblock']//div[@class='doc-button2 doc-button-consider']")
    private SelenideElement elementReviewDocument;

    /*
     * Элемент - Согласовать с замечаниями
     */
    @FindBy(xpath = "//*[@id='mainblock']//div[@class='doc-button2 doc-button-consider-with-notice']")
    private SelenideElement elementReconcileWithNotice;

    /*
     * Элемент - Отказаться согласовать
     */
    @FindBy(xpath = "//*[@id='mainblock']//div[@class='doc-button2 doc-button-refuze-consider']")
    private SelenideElement elementRefuseToConsider;

    /*
     * Элемент - Создать резолюцию
     */
    @FindBy(xpath = "//*[@id='mainblock']//div[@class='doc-button2 doc-button-resolution']")
    private SelenideElement elementCreateResolution;

    /*
     * Элемент - Вернуть документ на доработку
     */
    @FindBy(xpath = "//*[@id='mainblock']//div[@class='doc-button2 doc-button-reject']")
    private SelenideElement elementReturnTheDocumentForRevision;

    /*
     * Элемент - Просмотр листа согласования
     */
    @FindBy(xpath = "//*[@id='mainblock']//div[@class='doc-button2 doc-button-signlist-for_help']")
    private SelenideElement elementViewAgreeList;

    /*
     * Элемент - Отправить документ в архив
     */
    @FindBy(xpath = "//*[@id='mainblock']//div[@class='fc-button5']")
    private SelenideElement elementSendTheDocumentToTheArchive;

    /*
     * Элемент - Переслать на исполнение
     */
    @FindBy(xpath = "//*[@id='mainblock']//div[@class='fc-button7']")
    private SelenideElement elementSendToExecution;

    /*
     * Элемент - Создать задачу по исполнению документа
     */
    @FindBy(xpath = "//*[@id='mainblock']//div[@class='fc-button8']")
    private SelenideElement elementCreateDocumentExecutionTask;

    /*
     * Элемент - Настройка фильтра поиска
     */
    @FindBy(xpath = "//*[@id='mainblock']//div[@class='doc-button button-search-filter']")
    private SelenideElement elementSearchFilterSettings;

    /*
     * Элемент - Настройки
     */
    @FindBy(xpath = "//*[@id='mainblock']//div[@class='option-but']")
    private SelenideElement elementOptions;

    /*
     * Элемент - Выход
     */
    @FindBy(xpath = "//*[@id='mainblock']//div[@class='exit-but']")
    private SelenideElement elementExit;


    /**
     * Проверяем общее количество элементов помощи
     *
     * @return true or false display items per page
     */
    public boolean checkPresenceElementsOfAid() {
        helpElements.shouldHaveSize(19); // проверяем отображение 19 элементов помощи (Сохранить; Завершить задачу и пр.)
        return !helpElements.isEmpty();
    }

    public Collection<SelenideElement> results() {
        return $$(By.xpath("//div[@id='mainblock' and @class='mainblock withmenu']//li//div"));
    }


    /**
     * Проверяем отображение текста в элементах помощи
     * exactTexts - проверяет - кол-во, порядок и отображение текста
     *
     * @return page HelpHtml
     */
    public HelpHtmlStepsPDA visibleElementsTextHelp() {
        elementsTextHelp.shouldHave(exactTexts("- Сохранить", "- Завершить задачу", "- Вернуть задачу на доработку", "- Ознакомиться",
                "- Приступить", "- Прерваться", "- Закончить", "- Согласовать документ", "- Согласовать с замечаниями", "- Отказаться согласовать",
                "- Создать резолюцию", "- Вернуть документ на доработку",
                "- Просмотр листа согласования", "- Отправить документ в архив",
                "- Переслать на исполнение", "- Создать задачу по исполнению документа",
                "- Настройка фильтра поиска", "- Настройки", "- Выход"));
        return this;
    }

}
