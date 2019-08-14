package ru.motiw.mobile.steps.Tasks.ValidationStepsTasks;


import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import ru.motiw.web.model.Administration.Directories.DirectoriesField;
import ru.motiw.web.model.Administration.FieldsObject.*;
import ru.motiw.web.model.Administration.TasksTypes.TasksTypes;
import ru.motiw.web.model.Administration.Users.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.fail;

/**
 * Методы для проверки значений в полях на странице - Форма задачи (Описание)
 */
public class ValidateValuesOfFieldsStepsMobile extends ValidateFieldsStepsMobile {

    /**
     * Проверка значений в пользовательских полях
     */
    ValidateValuesOfFieldsStepsMobile verifyValueInCustomFields(FieldObject[] taskTypeListEditObjects) {
        if (taskTypeListEditObjects == null) {
            return this;
        }

        for (FieldObject fieldObject : taskTypeListEditObjects) {

            // СТРОКА
            if (fieldObject.getFieldType() instanceof TypeListFieldsString) {
                verifyValueInInputWithCustomName(fieldObject);
            }

            // ТЕКСТ
            if (fieldObject.getFieldType() instanceof TypeListFieldsText) {
                verifyValueInInputOfField(fieldObject.getValueField(), newTaskFormElementsMobile.getTextAreaInCustomField(fieldObject.getFieldName()));
            }

            // Целое
            if (fieldObject.getFieldType() instanceof TypeListFieldsInteger) {
                verifyValueInInputWithCustomName(fieldObject);
            }

            // ВЕЩЕСТВЕННОЕ
            if (fieldObject.getFieldType() instanceof TypeListFieldsDouble) {
                verifyValueInInputWithCustomName(fieldObject);
            }

            // ДАТА
            if (fieldObject.getFieldType() instanceof TypeListFieldsDate) {
                verifyValueInInputOfField(fieldObject.getValueField() + " 00:00:00", newTaskFormElementsMobile.getInputWithCustomName(fieldObject.getFieldName()));
            }

            // ЛОГИЧЕСКИЙ
            if (fieldObject.getFieldType() instanceof TypeListFieldsBoolean) {
                Assert.assertTrue("Значение в пользовательском поле типа ЛОГИЧЕСКИЙ неверное", verifyCheckboxIsSelected(fieldObject.getValueBooleanField(), newTaskFormElementsMobile.getStateOfCheckboxInUserField(fieldObject.getFieldName())));
            }

            // НУМЕРАТОР
            if (fieldObject.getFieldType() instanceof TypeListFieldsNumerator) {
                verifyValueInInputWithCustomName(fieldObject);
            }

            // ПОДРАЗДЕЛЕНИЕ
            if (fieldObject.getFieldType() instanceof TypeListFieldsDepartment) {
                verifyValueInInputWithCustomName(fieldObject);
            }

            // СОТРУДНИК
            if (fieldObject.getFieldType() instanceof TypeListFieldsEmployee) {
                //Проверка добавленых пользователей в поле задачи
                //verifyUserInFieldOfRole(fieldObject.getValueEmployeeField(), newTaskFormElementsMobile.getInputInUserFieldTypeEmployee(fieldObject.getFieldName())); //todo существующий метод не подходит для случая, когда в поле несколько пользователей
                //Проверка выбранных пользователей в форме выбора
                openFormSelectUser(newTaskFormElementsMobile.getInputInUserFieldTypeEmployee(fieldObject.getFieldName()));
                verifyUserInFormOfRole(fieldObject.getValueEmployeeField());
            }

            // СПРАВОЧНИК
            if (fieldObject.getFieldType() instanceof TypeListFieldsDirectory) {
                verifyValueInInputOfDirectoriesField(fieldObject.getFieldName(), fieldObject.getValueDirectoriesField());
            }

            // МН.СПРАВОЧНИК
            if (fieldObject.getFieldType() instanceof TypeListFieldsMultiDirectory) {
                verifyValueInInputOfMultiDirectoriesField(fieldObject.getFieldName(), fieldObject.getValueDirectoriesField());
            }
        }

        return this;
    }


    /**
     * Стандартная проверка значений в инпутах полей с индивидуальным названием
     *
     * @param fieldObject передаваемое значенние поля
     */
    ValidateValuesOfFieldsStepsMobile verifyValueInInputWithCustomName(FieldObject fieldObject) {
        if (fieldObject == null) {
            return null;
        }
        verifyValueInInputOfField(fieldObject.getValueField(), newTaskFormElementsMobile.getInputWithCustomName(fieldObject.getFieldName()));
        return this;
    }

    /**
     * Проверка значений в инпутах
     *
     * @param valueInInput передаваемое значенние поля
     * @param element      элемент
     */
    public void verifyValueInInputOfField(String valueInInput, SelenideElement element) {
        if (valueInInput == null) {
            return;
        }
        element.shouldHave(exactValue(valueInInput));
    }

    /**
     * Проверка значений в инпутах формы задачи для полей типа "Справочник"
     *
     * @param directoriesField передаваемое значенние поля
     * @param nameOfElement    имя элемента для xpath
     */
    ValidateValuesOfFieldsStepsMobile verifyValueInInputOfDirectoriesField(String nameOfElement, DirectoriesField[] directoriesField) {
        if (directoriesField == null) {
            return this;
        }
        //Проверяем значения в поле
        for (DirectoriesField d : directoriesField) {
            verifyValueInInputOfField(d.getDirectoriesItem(), newTaskFormElementsMobile.getInputWithCustomName(nameOfElement));
        }
        return this;
    }

    /**
     * Проверка значений в инпутах формы задачи для полей типа "Справочник",  "Мн.Справочник"
     *
     * @param directoriesFields передаваемое значенние поля
     * @param nameOfElement     имя элемента для xpath
     */
    ValidateValuesOfFieldsStepsMobile verifyValueInInputOfMultiDirectoriesField(String nameOfElement, DirectoriesField[] directoriesFields) {
        if (directoriesFields == null) {
            return this;
        }
        List<String> values = new ArrayList<String>(); // все значения, которыми были заполнены поля
        for (DirectoriesField directoriesField : directoriesFields) {
            values.add(directoriesField.getDirectoriesItem());
        }

        //Проверка отсутствия лишних значений в поле
        compareInputAndValue(values, newTaskFormElementsMobile.getSetInputs(nameOfElement));

        return this;
    }


    /**
     * Проверка установленного Типа задачи
     *
     * @param taskType передаваемое значенние поля Типа задачи
     */
    public ValidateValuesOfFieldsStepsMobile verifyTaskType(TasksTypes taskType) {
        verifyValueInInputOfField(taskType.getObjectTypeName(), newTaskFormElementsMobile.getTaskType());
        return this;
    }

    /**
     * Проверка пользователей в формах добавления
     *
     * @param employees передаваемые пользователи
     */
    void verifyUserInFormOfRole(Employee[] employees) {
        if (employees != null) {
            for (Employee employee : employees) {
                newTaskFormElementsMobile.getSelectedUserInTheList(employee.getLastName()).shouldBe(visible);
                // todo проверка отсутсвтия лишних выбранных пользователй
                newTaskFormElementsMobile.getInputForSearchUsers().sendKeys(Keys.chord(Keys.ESCAPE)); //Закрыть форму
                return;
            }
        }
        //В случае, если employees == null - Проверяем, что элемент в списке не выделен. Нужно для проверки после удаления пользователя из поля раб.группы.
        newTaskFormElementsMobile.getListOfUsers().shouldBe(CollectionCondition.sizeGreaterThan(0), 5000); //ожидание когда загрузится список пользователей. todo на http://motiwtest5.test.lan у админ не может удалить сам себя
        newTaskFormElementsMobile.getSelectedUserInTheList().shouldNotBe(visible);
        newTaskFormElementsMobile.getInputForSearchUsers().sendKeys(Keys.chord(Keys.ESCAPE)); //Закрыть форму

    }

    /**
     * Проверка добавленых пользователей в полях ролей задачи
     *
     * @param employees       передаваемые пользователи
     * @param employeeDefault пользователь находящийся в поле по-умолчанию
     * @param fieldCustomRole выбираемая роль в задаче (Исполнители, Контролеры, Авторы и ОР)
     */
    void verifyUserInFieldOfRole(Employee employeeDefault, Employee[] employees, SelenideElement fieldCustomRole) {
        if (employees != null) {
            for (Employee employee : employees) {
                fieldCustomRole.shouldHave(exactText(employeeDefault.getLastName() + " , " + employee.getLastName() + " " + employee.getName().substring(0, 1) + "." + employee.getPatronymic().substring(0, 1) + "."));
            }
        } else {
            fieldCustomRole.shouldHave(exactText(employeeDefault.getLastName()));
        }
    }

    /**
     * Проверка добавленых пользователей в полях ролей задачи
     *
     * @param employees       передаваемые пользователи
     * @param fieldCustomRole выбираемая роль в задаче (Исполнители, Контролеры, Авторы и ОР)
     */
    void verifyUserInFieldOfRole(Employee[] employees, SelenideElement fieldCustomRole) {
        if (employees != null) {
            for (Employee employee : employees) {
                fieldCustomRole.shouldHave(exactText(employee.getLastName() + " " + employee.getName().substring(0, 1) + "." + employee.getPatronymic().substring(0, 1) + "."));
            }
        }
    }

    /**
     * Проверка пользователей в формах добавления
     *
     * @param employees       передаваемые пользователи
     * @param employeeDefault пользователь по умолчанию
     */
    void verifyUserInFormOfRole(Employee employeeDefault, Employee[] employees) {
        if (employees != null) {
            for (Employee employee : employees) {
                newTaskFormElementsMobile.getSelectedUserInTheList(employee.getLastName()).shouldBe(visible);
                newTaskFormElementsMobile.getInputForSearchUsers().sendKeys(Keys.chord(Keys.ESCAPE)); //Закрыть форму
                return;
            }
        }
        //В случае, если employees == null - Проверяем, что пользователь по умолчанию выбран в списке. Нужно для проверки поля Автор, после удаления пользователя добавленного при создании задачи остается только Автор по умолчанию.
        newTaskFormElementsMobile.getListOfUsers().shouldBe(CollectionCondition.sizeGreaterThan(0), 5000); //ожидание когда загрузится список пользователей. todo на http://motiwtest5.test.lan админ не может удалить сам себя
        newTaskFormElementsMobile.getSelectedUserInTheList(employeeDefault.getLastName()).shouldBe(visible);
        newTaskFormElementsMobile.getInputForSearchUsers().sendKeys(Keys.chord(Keys.ESCAPE)); //Закрыть форму

    }


    /**
     * Проверка значений в поле "Приоритет"
     * Сейчас задачу создаем всегда с установкой приоритета Важная задача - true
     * Но можно проверять и с рандомным значением.
     * т.к установка признака и его проверка завязаны на одно значение true/false в valueTask.getIsImportant()
     */
    boolean verifyIsImportant(boolean isImportant) {
        if (isImportant) {
            $(By.xpath("//div[contains(@id,\"object\")]//input[@name=\"priority\"]")).shouldHave(value("Важная задача"));
            return true;
        } else {
            $(By.xpath("//div[contains(@id,\"object\")]//input[@name=\"priority\"]")).shouldHave(value("Обычная задача"));
            return true;
        }
    }

    /**
     * Проверка отсутствия лишних значения в нескольких инпутах полей формы задачи
     *
     * @param valuesThatShouldBe значения, которыми были заполнены поля
     * @param inputs             набор инпутов для проверки в них значений полей
     */
    public void compareInputAndValue(List<String> valuesThatShouldBe, ElementsCollection inputs) {
        if (inputs.isEmpty()) {
            fail("Элементы полей отсутствуют");
        }

        //Все поля ввода, кроме последнего - это поле всегда пустое
        ArrayList<SelenideElement> listInputs = new ArrayList<SelenideElement>();

        ArrayList<SelenideElement> allInputs = new ArrayList<>(inputs);
        if (allInputs.size() <= 1) {
            listInputs = allInputs;
        } else {
            // Проверка последнего инпута - это поле должно быть пустое
            allInputs.get(allInputs.size() - 1).shouldHave(empty);

            // берем все инпуты, кроме последнего
            int allElements = allInputs.size();
            for (int i = 0; i < allElements; i++) {
                if (i != allElements - 1) {
                    listInputs.add(allInputs.get(i));
                }
            }
        }

        ArrayList<String> currentValuesInInputs = new ArrayList<>();
        // каждый отображаемый в данный момент элемент помещаем в список в виде строки
        for (SelenideElement element : listInputs) {
            currentValuesInInputs.add(element.getValue());
        }

        // Сортируем значения в списках перед их сравнением
        Collections.sort(valuesThatShouldBe);
        Collections.sort(currentValuesInInputs);

        // Проверяем присутствие нужных/отсутствие лишних операций
        try {
            assertEquals(currentValuesInInputs, valuesThatShouldBe);
        } catch (Error e) {
            fail("\n" + " Проверка текста в полях: " + "\n"
                    + getReportOfDifferenceBetweenTwoArrayLists((ArrayList<String>) valuesThatShouldBe, currentValuesInInputs));
        }
    }

    /**
     * Проверка того, что текущий пользователь добавлен по умолчанию в роль задачи
     *
     * @param currentUser     передаваемый пользователи
     * @param fieldCustomRole выбираемая роль в задаче (Исполнители, Авторы и ОР)
     */

    public void currentUserSelectedInTheRole(Employee currentUser, SelenideElement fieldCustomRole) {
        if (currentUser != null) {
            openFormSelectUser(fieldCustomRole);
//            for (Employee employee : employees) {
            //проверка того, что элемент ПЕРВОГО пользователя в списке - выделен т.е выбран в роль
            newTaskFormElementsMobile.getSelectedUserInTheList(currentUser.getLastName()).shouldBe(visible);
            newTaskFormElementsMobile.getInputForSearchUsers().sendKeys(Keys.chord(Keys.ESCAPE)); //Закрыть форму
//            }
        }
    }
}
