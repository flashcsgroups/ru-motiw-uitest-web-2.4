package ru.motiw.web.model.Tasks;

/**
 * Модель объекта системы - Действие
 */
public class Action {

    private String actionText;
    private String timeOfAddAction;

    /**
     * Текст действия
     */
    public String getActionText() {
        return actionText;
    }

    public Action setActionText(String actionText) {
        this.actionText = actionText;
        return this;
    }

    /**
     * Время добавления действия
     */
    public String getTimeOfAddAction() { return timeOfAddAction; }

    public Action setTimeOfAddAction (String timeOfAddAction) {

        this.timeOfAddAction = timeOfAddAction;
        return this;
    }
}
