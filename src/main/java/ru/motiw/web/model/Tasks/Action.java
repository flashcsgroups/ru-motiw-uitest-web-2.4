package ru.motiw.web.model.Tasks;

import ru.motiw.web.model.Administration.Users.Employee;

/**
 * Модель объекта системы - Действие
 */
public class Action {

    private String actionText;
    private String timeOfAddAction;
    private Employee authorAction;

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

    /**
     * Пользователь добавивший действие
     */
    public Employee  getAuthorAction() {
        return authorAction;
    }

    public Action setAuthorAction(Employee authorAction) {
        this.authorAction = authorAction;
        return this;
    }


}
