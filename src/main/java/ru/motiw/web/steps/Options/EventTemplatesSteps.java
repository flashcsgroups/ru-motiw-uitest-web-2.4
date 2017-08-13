package ru.motiw.web.steps.Options;


import org.openqa.selenium.By;
import ru.motiw.web.steps.BaseSteps;
import ru.motiw.web.elements.elementsweb.Administration.TaskTypeListElements;
import ru.motiw.web.elements.elementsweb.Options.EventTemplatesElements;
import ru.motiw.web.elements.elementsweb.Tasks.TaskForm.EventElements;
import ru.motiw.web.model.Tasks.Event;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.switchTo;
import static ru.motiw.web.model.URLMenu.EVENT_TEMPLATES;

/**
 * Шаблоны событий
 */
public class EventTemplatesSteps extends BaseSteps {

    private TaskTypeListElements gridTypeListElements = page(TaskTypeListElements.class);
    private EventElements eventElements = page(EventElements.class);
    private EventTemplatesElements eventTemplatesElements = page(EventTemplatesElements.class);

    /**
     * Переход в меню - Настройки / Шаблоны событий
     *
     * @return EventTemplatesSteps
     */
    public static EventTemplatesSteps openSectionOnURLEventTemplates() {
        openSectionOnURL(EVENT_TEMPLATES.getMenuURL());
        return page(EventTemplatesSteps.class);
    }

    /**
     * Добавление - Шаблон события
     *
     * @param event передаваемые атрибуты для взаимодействия с формой объекта
     */
    public EventTemplatesSteps createEventTemplates(Event event) {
        gridTypeListElements.getAddTypesObject().click();
        getFrameObject($(By.xpath("//iframe[contains(@id,'component') and contains(@src,'/user/eventtemplates')]")));
        eventTemplatesElements.getInputNameEventTemplate()
                .setValue(event.getEventTemplateName().getNameEventTemplate()); // Название Шаблон события
        eventElements.getInputEventName()
                .setValue(event.getEventName()); // Название события
        eventElements.getInputEventDescription()
                .setValue(event.getEventDescription()); // Описание события
        eventElements.getInputEventPlace().setValue(event.getScheduledPlace()); // Место проведения

        switchTo().defaultContent();
        switchTo().frame($(By.cssSelector("#flow")));
        eventTemplatesElements.getSaveEventTemplate().click();
        checkOutTheObjectCreation(event.getEventTemplateName().getNameEventTemplate());
        return this;
    }


}
