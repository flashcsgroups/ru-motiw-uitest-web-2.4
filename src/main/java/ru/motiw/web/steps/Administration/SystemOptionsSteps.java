package ru.motiw.web.steps.Administration;

import org.openqa.selenium.By;
import ru.motiw.web.steps.BaseSteps;
import ru.motiw.web.elements.elementsweb.Administration.SystemOptionsElements;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static ru.motiw.utils.ElementUtil.offsetAndRangeOfValuesOnTheList;
import static ru.motiw.web.model.Administration.TasksTypes.SettingsForTaskTypeListFields.YES;
import static ru.motiw.web.model.URLMenu.SYSTEM_OPTIONS;

/**
 * Настройки системы
 */
public class SystemOptionsSteps extends BaseSteps {

    private SystemOptionsElements systemOptionsElements = page(SystemOptionsElements.class);

    /**
     * Переходим - Настройки системы
     */
    public static SystemOptionsSteps goToURLSystemOptionsPage() {
        openSectionOnURL(SYSTEM_OPTIONS.getMenuURL());
        return page(SystemOptionsSteps.class);
    }

    /**
     * Проверка Загрузки страницы - ожидание вкладок - Лицензии, Интерфейс, Задачи, Документы и пр..
     */
    public SystemOptionsSteps ensurePageLoaded() {
        checkDisplayedTabsInTheShapeOfAnObject(By.xpath("//a//span[text()][ancestor::a[contains(@id,'tab')]]"), 6,
                By.xpath("//a//span[text()][ancestor::a[contains(@id,'tab')]][text()]"), new String[]{"Лицензии", "Интерфейс", "Настройки email", "Задачи",
                        "Документы", "Дополнительно"});
        return this;
    }

    /**
     * Выбор опции - Удаление себя из задач == Да
     */
    public SystemOptionsSteps selectionRemoveYourselfFromTheTasks() {
        final String initialValue = "Нет";
        ensurePageLoaded();
        systemOptionsElements.getTabTasks().click();
        String value = systemOptionsElements.getAllowToDeleteFromTasks().val();
        if (value != null && initialValue.equals(value)) {
            offsetAndRangeOfValuesOnTheList(systemOptionsElements.getAllowToDeleteFromTasks(), getCollectionOfListItems(),
                    YES.getNameOfTheEnumerationValues());
            systemOptionsElements.getSave().click();
            checkingMessagesConfirmationOfTheObject($(By.xpath("//div[count(div)=3]/div[2]//div[contains(@id,'messagebox') and (@data-errorqtip)]")),
                    "Изменения сохранены", systemOptionsElements.getOk());
        }

        return this;
    }

    /**
     * Выбор опции - Создание секретных задач == Да
     */
    public SystemOptionsSteps selectCreatingASecretTask() {
        final String initialValue = "Нет";
        ensurePageLoaded();
        systemOptionsElements.getTabTasks().click();
        String value = systemOptionsElements.getCreatingASecretTask().val();
        if (value != null && initialValue.equals(value)) {
            offsetAndRangeOfValuesOnTheList(systemOptionsElements.getCreatingASecretTask(), getCollectionOfListItems(),
                    YES.getNameOfTheEnumerationValues());
            systemOptionsElements.getSave().click();
            checkingMessagesConfirmationOfTheObject($(By.xpath("//div[count(div)=3]/div[2]//div[contains(@id,'messagebox') and (@data-errorqtip)]")),
                    "Изменения сохранены", systemOptionsElements.getOk());
        }

        return this;
    }


}
