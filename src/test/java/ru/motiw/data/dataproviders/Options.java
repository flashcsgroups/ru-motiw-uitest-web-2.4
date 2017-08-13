package ru.motiw.data.dataproviders;


import org.testng.annotations.DataProvider;
import ru.motiw.data.BaseTest;
import ru.motiw.web.model.Options.EventTemplates;
import ru.motiw.web.model.Options.ScheduledTimeEvent;
import ru.motiw.web.model.Tasks.Event;
import ru.motiw.web.model.Tasks.EventType;

import static ru.motiw.data.dataproviders.Administration.getDepartment;
import static ru.motiw.data.dataproviders.Administration.getEmployees;

/**
 * Данные раздела - Настройки
 */
public abstract class Options extends BaseTest {

    // Тип события
    private EventType[] eventTypes = new EventType[]{getEventType(), getEventType()};

    /**
     * Шаблоны событий
     */
    public EventTemplates getEventTemplates() {
        return new EventTemplates().setNameEventTemplate(randomString(15) + " " + randomInt(3));
    }


    /**
     * Тип события
     */
    public EventType getEventType() {
        return new EventType()
                .setNameEventType(randomString(15))
                .setDescriptionEventType(randomString(50) + "\n " + randomString(25));
    }

    /**
     * Событие
     */
    public Event event = new Event()
            .setEventName(randomString(15))
            .setEventDescription(randomString(50) + "\n " + randomString(25) + "\n " + randomString(25))
            .setScheduledTimeEvent(randomEnum(ScheduledTimeEvent.class))
            .setScheduledPlace(randomString(25) + "\n " + randomString(25))
            .setEventType(eventTypes);

    /**
     * Параметризация - Инициализируем модель - Шаблоны события
     */
    @DataProvider
    public Object[][] objectDataEventTemplates() {

        Event event = new Event()
                .setEventTemplateName(getEventTemplates())
                .setEventName(randomString(15))
                .setEventDescription(randomString(50) + "\n "
                        + randomString(25) + "\n " + randomString(25))
                .setScheduledTimeEvent(randomEnum(ScheduledTimeEvent.class))
                .setScheduledPlace(randomString(25) + "\n " + randomString(25))
                .setEventType(eventTypes)

                .setUsers(getEmployees());

        return new Object[][]{
                {
                        // Подразделения
                        getDepartment(),
                        // Пользователи
                        getEmployees(),
                        // Шаблон событий
                        event,
                },
        };

    }


}
