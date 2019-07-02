package ru.motiw.mobile.steps.Tasks.ValidationSteps;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.motiw.mobile.steps.Tasks.CardTaskStepsMobile;
import ru.motiw.web.model.Administration.FieldsObject.FieldObject;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.fail;


/**
 * Методы для проверки полей на странице - Форма задачи (Описание)
 */
public class ValidateFieldsStepsMobile extends CardTaskStepsMobile {

    ValidateFilesStepsMobile validateFilesStepsMobile = page(ValidateFilesStepsMobile.class);

    /**
     * Проверка отображения системного поля "Шаблон задачи"
     */
    ValidateFieldsStepsMobile verifyFieldTemplateOfTask() {
        if ($(newTaskFormElementsMobile.getButtonCreateTask()).is(Condition.visible)) // Шаблон задачи в созданной задаче отсутствует.
        {
            $(By.xpath("//input[@name=\"id_task_template\"] ")).shouldBe(visible);
        }
        return this;
    }

    /**
     * Проверка присутсвия названий полей, которые должны отображаться для данного типа задачи и отсутствия лишних названий полей
     * Сравнение значений набора названий полей отображаемых в данный момент в форме задачи со значениями названий полей, которые выводяться для этого типа задачи
     *
     * @param fields                набор полей для данного типа задачи
     * @param elementsOfNamesFields элементы со значениями названий полей, которые отображаются в данный момент в форме задачи
     */
    public ValidateFieldsStepsMobile verifyNamesOfFields(FieldObject[] fields, ElementsCollection elementsOfNamesFields) {
        if (fields == null) {
            return this;
        }
        // Названия всех полей, которые присутствуют в типе задачи
        ArrayList<String> valuesThatShouldBe = new ArrayList<String>();
        for (FieldObject field : fields) {
            valuesThatShouldBe.add(field.getFieldName());
        }

        ArrayList<String> currentValuesInInputs = new ArrayList<>();
        // каждый отображаемый в данный момент элемент помещаем в список в виде строки
        for (SelenideElement element : elementsOfNamesFields) {
            if (!(element.is(Condition.exactText("Тип задачи")))) //Кроме названия "Тип задачи" - это системное поля
            {
                currentValuesInInputs.add(element.getText());
            }
        }

        // Проверяем присутствие нужных/отсутствие лишних названий полей
        try {
            assertEquals(currentValuesInInputs, valuesThatShouldBe);
        } catch (Error e) {
            fail("\n" + " Проверка названий списка полей: " + "\n"
                    + getReportOfDifferenceBetweenTwoArrayLists(valuesThatShouldBe, currentValuesInInputs));
        }
        return this;
    }

}
