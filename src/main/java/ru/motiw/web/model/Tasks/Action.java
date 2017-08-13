package ru.motiw.web.model.Tasks;

/**
 * Модель объекта системы - Действие
 */
public class Action {

    private String actionText;

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
}
