package ru.motiw.web.elements.elementsweb.Administration.TasksTypes;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.motiw.web.elements.elementsweb.Administration.TaskTypeListEditElements;

/**
 * Форма редактирования - Типы задач
 */
public class TaskTypesEditElements extends TaskTypeListEditElements {


    @FindBy(xpath = "//a/ancestor::div[contains(@id,'tabbar')]/a[2]")
    private SelenideElement fieldsTab;

    @FindBy(xpath = "//a/ancestor::div[contains(@id,'tabbar')]/a[1]")
    private SelenideElement settingsTab;

    @FindBy(xpath = "//a/ancestor::div[contains(@id,'tabbar')]/a[3]")
    private SelenideElement handlersTab;

    @FindBy(xpath = "//a/ancestor::div[contains(@id,'tabbar')]/a[4]")
    private SelenideElement handlersAndMailNotifySettingsTab;

    @FindBy(xpath = "//a/ancestor::div[contains(@id,'tabbar')]/a[5]")
    private SelenideElement estimationsAndReportsTab;

    @FindBy(xpath = "//a/ancestor::div[contains(@id,'tabbar')]/a[6]")
    private SelenideElement accessTab;

    @FindBy(css = "#files_edit-inputEl")
    private SelenideElement filesEdit;

    @FindBy(css = "#actiontape_attachments-inputEl")
    private SelenideElement attachFilesActionLine;

    @FindBy(css = "#description_attachments-inputEl")
    private SelenideElement attachFilesDescription;

    @FindBy(xpath = "(//input[contains(@id,'checkbox')] [ancestor::div[contains(@id,'tabpanel')]])[2]")
    private SelenideElement typeChangeDisabled;

    @FindBy(xpath = "(//input[contains(@id,'checkbox')] [ancestor::div[contains(@id,'tabpanel')]])[3]")
    private SelenideElement sameTypeIWG;

    @FindBy(xpath = "(//input[contains(@id,'checkbox')] [ancestor::div[contains(@id,'tabpanel')]])[4]")
    private SelenideElement closingTasksWithNotReadyCheckpoints;

    @FindBy(css = "#type_correction-inputEl")
    private SelenideElement typeCorrection;

    @FindBy(xpath = "//ul[@class='x-list-plain']/li[1]")
    private SelenideElement doNotAdjust;

    @FindBy(xpath = "//ul[@class='x-list-plain']/li[2]")
    private SelenideElement setTime;

    @FindBy(xpath = "//ul[@class='x-list-plain']/li[3]")
    private SelenideElement offsetInTheInterval;

    @FindBy(xpath = "//ul[@class='x-list-plain']/li[4]")
    private SelenideElement offsetInThePeriod;

    /**
     * Корректировка даты
     * Способ корректировки:
     * выбор поля - Способ корректировки (активация списка значений)
     */
    public SelenideElement getTypeCorrection() {
        return typeCorrection;
    }

    /**
     * Способ корректировки:
     * Не корректировать
     */
    public SelenideElement getDoNotAdjust() {
        return doNotAdjust;
    }

    /**
     * Способ корректировки:
     * Установить время
     */
    public SelenideElement getSetTime() {
        return setTime;
    }

    /**
     * Способ корректировки:
     * Сместить в рабочем интервале
     */
    public SelenideElement getOffsetInTheInterval() {
        return offsetInTheInterval;
    }

    /**
     * Способ корректировки:
     * Сместить в периоде
     */
    public SelenideElement getOffsetInThePeriod() {
        return offsetInThePeriod;
    }

    /**
     * Открывать файлы для редактирования
     */
    public SelenideElement getFilesEdit() {
        return filesEdit;
    }

    /**
     * Прикреплять файлы:
     * Лента действий
     */
    public SelenideElement getAttachFilesActionLine() {
        return attachFilesActionLine;
    }

    /**
     * Прикреплять файлы:
     * Описание
     */
    public SelenideElement getAttachFilesDescription() {
        return attachFilesDescription;
    }

    /**
     * Вкладка - Поля
     */
    public SelenideElement getFieldsTab() {
        return fieldsTab;
    }

    /**
     * Вкладка - Настройки
     */
    public SelenideElement getSettingsTab() {
        return settingsTab;
    }

    /**
     * Вкладка - Обработчики
     */
    public SelenideElement getHandlersTab() {
        return handlersTab;
    }

    /**
     * Вкладка - Настройки почтовых уведомлений И Настройки закладок (только для объектов - Справочники И Типы таблиц)
     */
    public SelenideElement getHandlersAndMailNotifySettingsTab() {
        return handlersAndMailNotifySettingsTab;
    }

    /**
     * Вкладка - Оценки и доклады
     */
    public SelenideElement getEstimationsAndReportsTab() {
        return estimationsAndReportsTab;
    }

    /**
     * Вкладка - Доступ
     */
    public SelenideElement getAccessTab() {
        return accessTab;
    }

    /**
     * Запретить изменение типа для созданной задачи
     */
    public SelenideElement getTypeChangeDisabled() {
        return typeChangeDisabled;
    }

    /**
     * Создавать подзадачи ИРГ только родительского типа
     */
    public SelenideElement getSameTypeIWG() {
        return sameTypeIWG;
    }

    /**
     * Запретить закрытие задач с неготовыми контрольными точками
     */
    public SelenideElement getClosingTasksWithNotReadyCheckpoints() {
        return closingTasksWithNotReadyCheckpoints;
    }
}
