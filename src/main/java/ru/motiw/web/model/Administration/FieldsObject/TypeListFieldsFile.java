package ru.motiw.web.model.Administration.FieldsObject;


import ru.motiw.web.model.OpenFilesForEdit;

/**
 * Тип поля документа - Файл
 */
public class TypeListFieldsFile extends FieldObject {

    private OpenFilesForEdit openFilesForEdit;

    /**
     * Открывать файлы для редактирования
     */
    public OpenFilesForEdit getOpenFilesForEdit() {
        return openFilesForEdit;
    }

    public TypeListFieldsFile setOpenFilesForEdit(OpenFilesForEdit openFilesForEdit) {
        this.openFilesForEdit = openFilesForEdit;
        return this;
    }


}
