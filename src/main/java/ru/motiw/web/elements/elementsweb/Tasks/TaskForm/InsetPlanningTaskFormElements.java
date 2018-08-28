package ru.motiw.web.elements.elementsweb.Tasks.TaskForm;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Элементы формы - ЗАДАЧА - вкладка Планирование
 */
public class InsetPlanningTaskFormElements {

    @FindBy(xpath = "//li[contains (@id, 'planning')]//span[contains (@class, 'strip')]/span")
    private SelenideElement planningTab;

    @FindBy(xpath = "//li[contains(@id, 'tab_events')]//span[contains (@class, 'strip')]/../../../a[2]")
    private SelenideElement eventsTab;

    @FindBy(xpath = "//table[contains (@id, 'AddCP')]//button")
    private SelenideElement buttonAddCheckpoint;

    @FindBy(xpath = "//*[contains (@id, 'tab_planning')]//td//div[contains (@class, 'check')]")
    private SelenideElement checkboxReadyFirst;

    @FindBy(xpath = "//*[contains (@id, 'tab_planning')]//*[contains (@class, 'scroller')]//*[contains (@class, 'row')][1]//td[3]")
    private SelenideElement checkpointDateField;

    @FindBy(xpath = "//*[contains (@id, 'tab_planning')]//*[contains (@class, 'scroller')]//*[contains (@class, 'row')][1]//td[3]")
    private SelenideElement checkpointLinkedField;

    @FindBy(xpath = "//*[contains (@id, 'tab_planning')]//*[contains (@class, 'scroller')]//*[contains (@class, 'row')][1]//td[4]")
    private SelenideElement checkpointOffsetField;

    @FindBy(xpath = "//*[contains (@id, 'tab_planning')]//*[contains (@class, 'scroller')]//*[contains (@class, 'row')][1]//td[5]")
    private SelenideElement checkpointPeriodField;

    @FindBy(xpath = "//*[contains (@id, 'tab_planning')]//*[contains (@class, 'scroller')]//*[contains (@class, 'row')][1]//td[4]")
    private SelenideElement checkpointNameField;

    @FindBy(xpath = "(//img[contains (@onclick, 'Description')])[1]")
    private SelenideElement checkpointDescriptionField;

    @FindBy(xpath = "//div[contains (@class, 'combo')][contains (@style, 'visibility: visible')]/div/div[1]")
    private SelenideElement firstVisibleCombo;

    @FindBy(xpath = "//div[contains (@class, 'combo')][contains (@style, 'visibility: visible')]/div/div[2]")
    private SelenideElement secondVisibleCombo;

    @FindBy(xpath = "//div[contains (@class, 'combo')][contains (@style, 'visibility: visible')]/div/div[3]")
    private SelenideElement thirdVisibleCombo;

    @FindBy(xpath = "//div[contains (@class, 'combo')][contains (@style, 'visibility: visible')]/div/div[4]")
    private SelenideElement fourthVisibleCombo;

    @FindBy(xpath = "//div[contains (@class, 'combo')][contains (@style, 'visibility: visible')]/div/div[5]")
    private SelenideElement fifthVisibleCombo;

    /**
     * Вкладка - Планирование
     */
    public SelenideElement getPlanningTab() {
        return planningTab;
    }

    /**
     * Вкладка - События
     */
    public SelenideElement getEventsTab() {
        return eventsTab;
    }

    /**
     * Добавить КТ
     */
    public SelenideElement getButtonAddCheckpoint() {
        return buttonAddCheckpoint;
    }

    /**
     * Чекбокс "Готово" первой КТ грида
     */
    public SelenideElement getCheckboxReadyFirst() {
        return checkboxReadyFirst;
    }

    /**
     * Поле Дата первой КТ грида
     */
    public SelenideElement getCheckpointDateField() {
        return checkpointDateField;
    }

    /**
     * Поле Привязка первой КТ грида
     */
    public SelenideElement getCheckpointLinkedField() {
        return checkpointLinkedField;
    }

    /**
     * Поле смещение первой КТ грида
     */
    public SelenideElement getCheckpointOffsetField() {
        return checkpointOffsetField;
    }

    /**
     * Поле период первой КТ грида
     */
    public SelenideElement getCheckpointPeriodField() {
        return checkpointPeriodField;
    }

    /**
     * Поле название первой КТ грида
     */
    public SelenideElement getCheckpointNameField() {
        return checkpointNameField;
    }

    /**
     * Кнопка Описание первой КТ грида
     */
    public SelenideElement getCheckpointDescriptionField() {
        return checkpointDescriptionField;
    }

    /**
     * Первый видимый элемент комбобокса
     */
    public SelenideElement getFirstVisibleCombo() {
        return firstVisibleCombo;
    }

    /**
     * Второй видимый элеиент комбобокса
     */
    public SelenideElement getSecondVisibleCombo() {
        return secondVisibleCombo;
    }

    /**
     * Третий видимый элеиент комбобокса
     */
    public SelenideElement getThirdVisibleCombo() {
        return thirdVisibleCombo;
    }


    /**
     * Четвертый видимый элеиент комбобокса
     */
    public SelenideElement getFourthVisibleCombo() {
        return fourthVisibleCombo;
    }

    /**
     * Пятый видимый элеиент комбобокса
     */
    public SelenideElement getFifthVisibleCombo() {
        return fifthVisibleCombo;
    }
}
