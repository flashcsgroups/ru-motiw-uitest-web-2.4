package ru.motiw.web.model.Administration.TasksTypes;

/**
 * Режим вычисления для поля типа "Нумератор"; Тип объекта - Типы задач
 * Значения - "При создании задачи", "Не вычислять"
 */
public enum ComputeModeNumerator {

    WHEN_CREATING_TASK("При создании задачи"), NOT_CALCULATED("Не вычислять");


    private String computeModeNumerator;

    private ComputeModeNumerator(String computeModeNumerator) {
        this.computeModeNumerator = computeModeNumerator;
    }

    public String getComputeModeNumerator() {
        return this.computeModeNumerator;
    }

}

