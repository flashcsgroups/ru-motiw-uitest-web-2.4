package ru.motiw.mobile.model.Document;

/**
 * Названия операций для созданного документа
 */
public enum OperationsOfDocument {

    CREATE_RESOLUTION_IN_FORM_OF_DOCUMENT("Создать"),
    CREATE_RESOLUTION_IN_THE_GRID("Создать резолюцию"),
    MOVE_TO_EXECUTION("Переслать на исполнение"),
    MOVE_TO_ARCHIVE("Отправить в архив");

    private String nameTab;

    OperationsOfDocument(String nameOperation) {this.nameTab = nameOperation;}


    public String getNameOperation() {return  nameTab;}


}
