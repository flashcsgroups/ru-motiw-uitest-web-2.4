package ru.motiw.web.model.Tasks;

/**
 * Тип События
 */
public class EventType {

    private String nameEventType;
    private String DescriptionEventType;

    /**
     * Имя - Тип события
     */
    public String getNameEventType() {
        return nameEventType;
    }

    public EventType setNameEventType(String nameEventType) {
        this.nameEventType = nameEventType;
        return this;
    }

    /**
     * Описание - Тип события
     */
    public String getDescriptionEventType() {
        return DescriptionEventType;
    }

    public EventType setDescriptionEventType(String descriptionEventType) {
        DescriptionEventType = descriptionEventType;
        return this;
    }


}
