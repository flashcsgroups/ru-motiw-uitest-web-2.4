package ru.motiw.web.steps.Administration.TypesOfTables;

import org.openqa.selenium.By;
import ru.motiw.web.elements.elementsweb.Administration.TaskTypeListElements;
import ru.motiw.web.steps.Administration.TaskTypeListSteps;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static ru.motiw.web.model.URLMenu.TYPE_TABLE;


/**
 * Типы таблиц - Грид объекта
 */
public class TypesOfTablesSteps extends TaskTypeListSteps {

    private TaskTypeListElements taskTypeListElements = page(TaskTypeListElements.class);

    /**
     * Добавление объекты - Типы таблиц
     *
     * @param nameTypeOfTable передаваемое название для объекта)
     * @return TaskTypeListSteps объект - Типы таблиц
     */
    @Override
    public void addObjectTaskTypeList(String nameTypeOfTable) {
        super.addObjectTaskTypeList(nameTypeOfTable);
    }

    /**
     * Редактирование - Типы таблиц
     *
     * @param nameTypeOfTable
     * @return TaskTypeListSteps
     */
    @Override
    public void editObjectTaskTypeList(String nameTypeOfTable) {
        super.editObjectTaskTypeList(nameTypeOfTable);
    }

    /**
     * Удаление - Типы таблиц
     *
     * @param typesOfTables
     * @return TaskTypeListSteps
     */
    @Override
    public void removeObjectTaskTypeList(String typesOfTables) {
        super.removeObjectTaskTypeList(typesOfTables);
        checkingMessagesConfirmationOfTheObject($(By.xpath("//div[contains(@id,'messagebox')]/ancestor::div[contains(@id,'container')]//div[text()]")),
                "Вы действительно хотите удалить таблицу \"" + typesOfTables + "" + "\"?",
                taskTypeListElements.getConfirmationButtonObjectDeletion());
        $(By.xpath("//*[contains(text(),'" + typesOfTables + "')][ancestor::table]/..")).shouldNotBe(visible);
    }

    /**
     * Переход в меню - Администрирование/Типы таблиц
     */
    public static TypesOfTablesSteps goToURLTypesOfTables() {
        openSectionOnURL(TYPE_TABLE.getMenuURL());
        return page(TypesOfTablesSteps.class);
    }


}
