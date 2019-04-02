package ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards;

public class FieldTypeFileDocument extends FieldDocument {

    private String[] nameFile;

    /**
     * Название файла - в системном поле типа "Файл"
     *
     */
    public String[] getNameFile() {
        return nameFile;
    }

    public FieldTypeFileDocument setNameFile(String[] nameFile) {
        this.nameFile = nameFile;
        return this;
    }
}
