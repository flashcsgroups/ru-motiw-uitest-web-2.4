package ru.motiw.web.model.Tasks;


import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Options.EventTemplates;
import ru.motiw.web.model.Options.ScheduledTimeEvent;

import java.util.ArrayList;

/**
 * Событие
 */
public class Event {

    private String eventName;
    private String eventDescription;
    private ScheduledTimeEvent scheduledTimeEvent;
    private String scheduledPlace;
    private ArrayList<Employee> users;
    private EventType[] eventType;
    private EventTemplates eventTemplateName;


    /**
     * Название События
     */
    public String getEventName() {
        return eventName;
    }

    public Event setEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    /**
     * Описание События
     */
    public String getEventDescription() {
        return eventDescription;
    }

    public Event setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
        return this;
    }

    /**
     * Время проведения
     */
    public ScheduledTimeEvent getScheduledTimeEvent() {
        return scheduledTimeEvent;
    }

    public Event setScheduledTimeEvent(ScheduledTimeEvent scheduledTimeEvent) {
        this.scheduledTimeEvent = scheduledTimeEvent;
        return this;
    }

    /**
     * Место проведения
     */
    public String getScheduledPlace() {
        return scheduledPlace;
    }

    public Event setScheduledPlace(String scheduledPlace) {
        this.scheduledPlace = scheduledPlace;
        return this;
    }

    /**
     * Пользователи
     */
    public ArrayList<Employee> getUsers() {
        return users;
    }

    public Event setUsers(ArrayList<Employee> users) {
        this.users = users;
        return this;
    }

    /**
     * Тип события
     */
    public EventType[] getEventType() {
        return eventType;
    }

    public Event setEventType(EventType[] eventType) {
        this.eventType = eventType;
        return this;
    }

    /**
     * Название Шаблона События
     */
    public EventTemplates getEventTemplateName() {
        return eventTemplateName;
    }

    public Event setEventTemplateName(EventTemplates eventTemplateName) {
        this.eventTemplateName = eventTemplateName;
        return this;
    }

}
