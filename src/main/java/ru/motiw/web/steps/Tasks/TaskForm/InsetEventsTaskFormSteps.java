package ru.motiw.web.steps.Tasks.TaskForm;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.motiw.web.elements.elementsweb.Tasks.TaskForm.EventElements;
import ru.motiw.web.elements.elementsweb.Tasks.TaskForm.InsetEventsTaskFormElements;

import ru.motiw.web.elements.elementsweb.Tasks.TaskForm.InsetPlanningTaskFormElements;
import ru.motiw.web.model.Tasks.Event;

import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertEquals;
import static ru.motiw.utils.WindowsUtil.newWindowForm;

/**
 * Форма задачи - вкладка СОБЫТИЯ
 */
public class InsetEventsTaskFormSteps extends UnionMessageNewSteps {

    private InsetEventsTaskFormElements insetEventsTaskFormElements = page(InsetEventsTaskFormElements.class);
    private EventElements eventElements = page(EventElements.class);
    private InsetPlanningTaskFormElements insetPlanningTaskFormElements = page(InsetPlanningTaskFormElements.class);

    /**
     * Проверяем отрображение грида Событие
     */
    public InsetEventsTaskFormSteps beforeAddEvents() {
        insetPlanningTaskFormElements.getEventsTab().click();
        insetEventsTaskFormElements.getAddEvent().shouldBe(visible);
        return this;
    }

    /**
     * Добавление объекта - Событие в форме задачи
     *
     * @param event атрибуты для объекта События
     */
    public void addEventToTheTemplateForTheTask(Event event) {

        insetEventsTaskFormElements.getAddEvent().click();
        /*
         Window PopUp. Store your parent window
         */
        String parentWindowHandler = getWebDriver().getWindowHandle();
        getWebDriver().switchTo().window(new WebDriverWait(getWebDriver(), 10)
                .until(newWindowForm(By.cssSelector("#eventTemplateList"))));

        eventElements.getEventTemplate().click(); // Выбираем Шаблон Событий

        SelenideElement select = $(By.id("eventTemplateList"));
        select.selectOption(event.getEventTemplateName().getNameEventTemplate());

        select.getSelectedOption().shouldBe(selected);

        // Валидация заполненных полей из Шаблона События
        assertEquals(event.getEventTemplateName().getNameEventTemplate(), select.getSelectedText()); // Выбранное название Шаблона события
        $(By.cssSelector("#eventTemplateList")).click();
        sleep(100);
        assertThat(eventElements.getEventTemplate().getText(), is(equalTo(event.getEventTemplateName().getNameEventTemplate())));
        assertThat(eventElements.getInputEventName().getValue(), is(equalTo(event.getEventName()))); // Название события из шаблона
        assertThat(eventElements.getInputEventDescription().getValue(), is(equalTo(event.getEventDescription()))); // Описание события из шаблона
        assertThat(eventElements.getInputEventPlace().getValue(), is(equalTo(event.getScheduledPlace()))); // Место проведения

        // TODO проверяем отображение созданного объекта в гриде Cобытий ДО создания задачи И после в созданной задаче
        // TODO расширить проверку заполненных полей после заполнения
        eventElements.getSaveEvent().click();


        getWebDriver().switchTo().window(parentWindowHandler);  // Switch back to parent window
        switchTo().defaultContent();
        switchTo().frame($(By.cssSelector("#flow")));
        saveTask();
    }


}
