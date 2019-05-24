package ru.motiw.mobile.model.Document;

/**
 * Типы операций для созданного документа
 */
public enum TypeOperationsOfDocument {

    CREATE_RESOLUTION,
    MOVE_TO_EXECUTION,
    MOVE_TO_ARCHIVE,
    RETURN_TO_EXECUTION,
    CLOSE_EXECUTION;

    TypeOperationsOfDocument() {

    }

}
