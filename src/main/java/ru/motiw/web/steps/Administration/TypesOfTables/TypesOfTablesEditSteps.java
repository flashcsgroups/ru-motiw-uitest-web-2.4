package ru.motiw.web.steps.Administration.TypesOfTables;

import org.openqa.selenium.By;
import ru.motiw.web.elements.elementsweb.Administration.TypesOfTables.TypesOfTablesEditElements;
import ru.motiw.web.model.Administration.TaskTypeListEditObject;
import ru.motiw.web.steps.Administration.TaskTypeListEditSteps;

import static com.codeborne.selenide.Selenide.page;

/**
 * Форма редактирования - Типы таблиц
 */
public class TypesOfTablesEditSteps extends TaskTypeListEditSteps {

    private TypesOfTablesEditElements typesOfTablesEditElements = page(TypesOfTablesEditElements.class);


    /**
     * Добавление полей Типа таблицы
     *
     * @param typesOfTables атрибуты объекта - Типы таблиц
     */
    @Override
    public TaskTypeListEditSteps addAllFieldsTaskTypeList(TaskTypeListEditObject typesOfTables) {
        // проверка отображения вкладок в форме редактирования Типы таблиц
        checkDisplayedTabsInTheShapeOfAnObject(By.xpath("//div[count(a)=4]/a//text()//.."), 4,
                By.xpath("//div[count(a)=4]/a//text()//.."), new String[]{"Настройки", "Поля", "Обработчики", "Настройки закладок"});
        typesOfTablesEditElements.getFieldsTab().click(); // Выбираем вкладку Поля
        super.addAllFieldsTaskTypeList(typesOfTables); // Добавление всех типов полей объекта
        return this;
    }

}