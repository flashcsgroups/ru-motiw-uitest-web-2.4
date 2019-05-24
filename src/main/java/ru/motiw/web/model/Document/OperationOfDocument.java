package ru.motiw.web.model.Document;

/**
 * Модель объекта - Операции Документа
 */
public class OperationOfDocument extends Document {

    private boolean CreateResolution;
    private boolean MoveToExecution;
    private boolean MoveToArchive;
    private boolean ReturnToExecution;
    private boolean CloseExecution;


    public boolean isReturnToExecution() {
        return ReturnToExecution;
    }

    public OperationOfDocument setReturnToExecution(boolean returnToExecution) {
        ReturnToExecution = returnToExecution;
        return this;
    }

    public boolean isCreateResolution() {
        return CreateResolution;
    }

    public OperationOfDocument setCreateResolution(boolean createResolution) {
        CreateResolution = createResolution;
        return this;
    }

    public boolean isMoveToExecution() {
        return MoveToExecution;
    }

    public OperationOfDocument setMoveToExecution(boolean moveToExecution) {
        MoveToExecution = moveToExecution;
        return this;
    }

    public boolean isMoveToArchive() {
        return MoveToArchive;
    }

    public OperationOfDocument setMoveToArchive(boolean moveToArchive) {
        MoveToArchive = moveToArchive;
        return this;
    }

    public boolean isCloseExecution() {
        return CloseExecution;
    }

    public OperationOfDocument setCloseExecution(boolean closeExecution) {
        CloseExecution = closeExecution;
        return this;
    }
}
