package ru.motiw.mobile.model.Document;

/**
 * Типы операций для созданного документа
 */
public enum TypeOperationsOfDocument {

    CREATE_RESOLUTION,
    MOVE_TO_EXECUTION,
    MOVE_TO_ARCHIVE,
    MOVE_FROM_REFINEMENT_TO_EXECUTION, // Возврат с доработки
    CLOSE_EXECUTION;

    TypeOperationsOfDocument() {

    }

}
