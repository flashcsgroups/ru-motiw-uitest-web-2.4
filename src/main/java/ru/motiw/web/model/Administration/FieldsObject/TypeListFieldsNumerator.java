package ru.motiw.web.model.Administration.FieldsObject;


import ru.motiw.web.model.Administration.TasksTypes.ComputeModeNumerator;

/**
 * Модель объекта системы - Тип поля "Нумератор"
 */
public class TypeListFieldsNumerator extends FieldObject {

    private String numeratorTemplate;
    private ComputeModeNumerator computeMode;


    /**
     * Шаблон нумератора
     *
     * @return
     */
    public String getNumeratorTemplate() {
        return numeratorTemplate;
    }

    public TypeListFieldsNumerator setNumeratorTemplate(
            String numeratorTemplate) {
        this.numeratorTemplate = numeratorTemplate;
        return this;
    }

    /**
     * Режим вычисления
     *
     * @return
     */
    public ComputeModeNumerator getComputeMode() {
        return computeMode;
    }

    public TypeListFieldsNumerator setComputeMode(
            ComputeModeNumerator computeMode) {
        this.computeMode = computeMode;
        return this;
    }


}
