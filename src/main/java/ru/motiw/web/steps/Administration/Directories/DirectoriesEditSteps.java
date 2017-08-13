package ru.motiw.web.steps.Administration.Directories;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import ru.motiw.web.elements.elementsweb.Administration.Directories.DirectoriesEditElements;
import ru.motiw.web.model.Administration.Directories.Directories;
import ru.motiw.web.model.Administration.TaskTypeListEditObject;
import ru.motiw.web.steps.Administration.TaskTypeListEditSteps;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;


/**
 * Форма редактирования объекта - Справочники
 */
public class DirectoriesEditSteps extends TaskTypeListEditSteps {

    private DirectoriesEditElements directoriesEditElements = page(DirectoriesEditElements.class);

    /**
     * Выбор настройки доступности записей справочника в гриде
     * Общедоступность записей
     * Настройка доступа к записям
     *
     * @param recordsDirectory
     */
    private DirectoriesEditSteps recordsAvailableDirectories(boolean recordsDirectory, SelenideElement availabilityOfDirectory) {
        if (recordsDirectory) {
            availabilityOfDirectory.click();
        }
        return this;
    }

    /**
     * Способ отображения
     */
    private DirectoriesEditSteps mappingDevice(boolean flat) {
        if (flat) {
            directoriesEditElements.getRbFlat().click();
        } else if (!flat) {
            directoriesEditElements.getRbHierarchical().click();
        }
        return this;
    }

    /**
     * Настройка поиска
     */
    private DirectoriesEditSteps searchSettings(boolean searchSystem) {
        if (searchSystem) {
            directoriesEditElements.getUseSearchSystem().click();
        } else if (!searchSystem) {
            directoriesEditElements.getUseSearchBD().click();
        }
        return this;
    }

    /**
     * Настройка поиска: Использовать поисковую систему / Использовать базу данных
     *
     * @param valueSettings атрибуты настроек полей
     */
    public DirectoriesEditSteps setSearchSettings(Directories valueSettings) {
        $(directoriesEditElements.getSettingsTab()).shouldBe(visible).click(); // выбор вкладки Настройки
        searchSettings(valueSettings.getSearchSettings()); // Настройка поиска
        return this;
    }

    /**
     * Общедоступность записей
     *
     * @param valueSettings передаваемое значение настройки общедоступности записи
     * @return DirectoriesEditSteps
     */
    public DirectoriesEditSteps setRecordsAvailableDirectories(Directories valueSettings) {
        $(directoriesEditElements.getSettingsTab()).shouldBe(visible).click(); // выбор вкладки Настройки
        recordsAvailableDirectories(valueSettings.getShareRecords(), directoriesEditElements.getRecordsAccessibility());
        recordsAvailableDirectories(valueSettings.getAccessToRecords(), directoriesEditElements.getRecordAccessSetting());
        return this;
    }

    /**
     * Способ отображения: Иерархический / Линейный
     *
     * @param valueSettings передаваемое значение настройки общедоступности записи
     * @return DirectoriesEditSteps
     */
    public DirectoriesEditSteps setMappingDevice(Directories valueSettings) {
        $(directoriesEditElements.getSettingsTab()).shouldBe(visible).click(); // выбор вкладки Настройки
        mappingDevice(valueSettings.getMappingDevice());
        return this;
    }

    /**
     * Добавление полей Справочника
     *
     * @param fields передаваемые поля и атрибуты полей спр-ка
     */
    @Override
    public TaskTypeListEditSteps addAllFieldsTaskTypeList(TaskTypeListEditObject fields) {
        $(directoriesEditElements.getFieldsTab()).shouldBe(visible).click(); // Выбираем вкладку Поля
        super.addAllFieldsTaskTypeList(fields);
        return this;
    }

    /**
     * Проверяем истенность загрузки формы редактирования Справочника
     */
    public boolean loadedDirectoriesEditPage() {
        return page(DirectoriesEditSteps.class).isPageLoaded();
    }

    /**
     * Инициализация формы редактирования - Справочники
     */
    public DirectoriesEditSteps directoriesEditPage() {
        return page(DirectoriesEditSteps.class);
    }

    private boolean isPageLoaded() {
        try {
            checkDisplayedTabsInTheShapeOfAnObject(By.xpath("//div[count(a)=4]/a//text()//.."), 4,
                    By.xpath("//div[count(a)=4]/a//text()//.."), new String[]{"Настройки", "Поля", "Обработчики", "Настройки закладок"});
            return true;
        } catch (TimeoutException to) {
            return false;
        }
    }


}
