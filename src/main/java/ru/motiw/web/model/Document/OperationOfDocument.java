package ru.motiw.web.model.Document;

/**
 * Модель объекта - Операции Документа
 */
public class OperationOfDocument extends Document {

    private boolean listOfResolution;
    private boolean createResolution;
    private boolean moveToExecution;
    private boolean moveToArchive;
    private boolean returnToExecution;
    private boolean closeExecution;

    public boolean isListOfResolution() {
        return listOfResolution;
    }

    public OperationOfDocument setListOfResolution(boolean listOfResolution) {
        this.listOfResolution = listOfResolution;
        return this;
    }


    public boolean isCreateResolution() {
        return createResolution;
    }

    public OperationOfDocument setCreateResolution(boolean createResolution) {
        this.createResolution = createResolution;
        return this;
    }

    public boolean isMoveToExecution() {
        return moveToExecution;
    }

    public OperationOfDocument setMoveToExecution(boolean moveToExecution) {
        this.moveToExecution = moveToExecution;
        return this;
    }

    public boolean isMoveToArchive() {
        return moveToArchive;
    }

    public OperationOfDocument setMoveToArchive(boolean moveToArchive) {
        this.moveToArchive = moveToArchive;
        return this;
    }

    public boolean isReturnToExecution() {
        return returnToExecution;
    }

    public OperationOfDocument setReturnToExecution(boolean returnToExecution) {
        this.returnToExecution = returnToExecution;
        return this;
    }

    public boolean isCloseExecution() {
        return closeExecution;
    }

    public OperationOfDocument setCloseExecution(boolean closeExecution) {
        this.closeExecution = closeExecution;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OperationOfDocument operationOfDocument = (OperationOfDocument) o;

        return
                listOfResolution == operationOfDocument.listOfResolution &&
                        createResolution == operationOfDocument.createResolution &&
                        moveToExecution == operationOfDocument.moveToExecution &&
                        moveToArchive == operationOfDocument.moveToArchive &&
                        returnToExecution == operationOfDocument.returnToExecution &&
                        closeExecution == operationOfDocument.closeExecution;

    }


}
