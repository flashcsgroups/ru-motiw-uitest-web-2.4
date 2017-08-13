package ru.motiw.web.model.Administration.FieldsObject;

/**
 * Модель объекта системы - Тип поля "Вещественное"
 */
public class TypeListFieldsDouble extends FieldObject {

    private boolean formatNumber;


    /**
     * Формат; true == Другой формат; false == Значение по умолчанию
     *
     * @return
     */
    public boolean getFormatNumber() {
        return formatNumber;
    }

    public TypeListFieldsDouble setFormatNumber(boolean formatNumber) {
        this.formatNumber = formatNumber;
        return this;
    }


}
