package ru.motiw.web.model.Administration.TasksTypes;

import ru.motiw.web.model.Administration.FieldsObject.FieldObject;
import ru.motiw.web.model.Administration.TaskTypeListEditObject;
import ru.motiw.web.model.CorrectionMethod;
import ru.motiw.web.model.OpenFilesForEdit;
import ru.motiw.web.model.ShiftDirection;

/**
 * Модель объекта системы - Тип задачи (Администрирование/Типы задач)
 */
public class TasksTypes extends TaskTypeListEditObject {

    private ShiftDirection shiftDirection;
    private CorrectionMethod correctionMethod;
    private boolean disableTaskTypeChange;
    private boolean onlyTheSameTypeIWG;
    private boolean disableCloseTaskWithNotReadyCheckpoints;
    private OpenFilesForEdit openFilesForEdit;
    private boolean attachFilesToActionLine;
    private boolean attachFilesToDescription;
    private FieldObject addTableRecordTo;
    private boolean useECP;

    public TasksTypes(String nameTaskType) {
        super(nameTaskType);
    }

    /**
     * Направление смещения при попадании на нерабочее время
     */
    public ShiftDirection getTaskTypeShiftDirection() {
        return shiftDirection;
    }

    public TasksTypes setTaskTypeShiftDirection(ShiftDirection shiftDirection) {
        this.shiftDirection = shiftDirection;
        return this;
    }

    /**
     * Использовать ЭЦП
     */
    public boolean getUseECP() {
        return useECP;
    }

    public TasksTypes setUseECP(boolean useECP) {
        this.useECP = useECP;
        return this;
    }

    /**
     * Корректировка даты
     */
    public CorrectionMethod getTaskTypeCorrectionMethod() {
        return correctionMethod;
    }

    public TasksTypes setTaskTypeCorrectionMethod(CorrectionMethod correctionMethod) {
        this.correctionMethod = correctionMethod;
        return this;
    }

    /**
     * Запретить изменение типа для созданной задачи
     */
    public boolean getIsTaskTypeChangeDisabled() {
        return disableTaskTypeChange;
    }

    public TasksTypes setIsTaskTypeChangeDisabled(boolean disableTaskTypeChange) {
        this.disableTaskTypeChange = disableTaskTypeChange;
        return this;
    }

    /**
     * Создавать подзадачи ИРГ только родительского типа
     */
    public boolean getOnlyTheSameTypeIWG() {
        return onlyTheSameTypeIWG;
    }

    public TasksTypes setOnlyTheSameTypeIWG(boolean onlyTheSameTypeIWG) {
        this.onlyTheSameTypeIWG = onlyTheSameTypeIWG;
        return this;
    }

    /**
     * Запретить закрытие задач с неготовыми контрольными точками
     */
    public boolean getIsCloseTaskWithNotReadyCheckpointsDisabled() {
        return disableCloseTaskWithNotReadyCheckpoints;
    }

    public TasksTypes setIsCloseTaskWithNotReadyCheckpointsDisabled(boolean disableCloseTaskWithNotReadyCheckpoints) {
        this.disableCloseTaskWithNotReadyCheckpoints = disableCloseTaskWithNotReadyCheckpoints;
        return this;
    }

    /**
     * Открывать файлы для редактирования
     */
    public OpenFilesForEdit getOpenFilesForEdit() {
        return openFilesForEdit;
    }

    public TasksTypes setOpenFilesForEdit(OpenFilesForEdit openFilesForEdit) {
        this.openFilesForEdit = openFilesForEdit;
        return this;
    }

    /**
     * Добавлять файлы в ленту действий
     */
    public boolean getIsAttachFilesToActionLine() {
        return attachFilesToActionLine;
    }

    public TasksTypes setIsAttachFilesToActionLine(boolean attachFilesToActionLine) {
        this.attachFilesToActionLine = attachFilesToActionLine;
        return this;
    }

    /**
     * Добавлять файлы в описание
     */
    public boolean getIsAttachFilesToDescription() {
        return attachFilesToDescription;
    }

    public TasksTypes setIsAttachFilesToDescription(boolean attachFilesToDecription) {
        this.attachFilesToDescription = attachFilesToDecription;
        return this;
    }

    /**
     * Вместо добавления файла добавлять запись в таблицу
     */
    public FieldObject getAddTableRecordTo() {
        return addTableRecordTo;
    }

    public TasksTypes setAddTableRecordTo(FieldObject addTableRecordTo) {
        this.addTableRecordTo = addTableRecordTo;
        return this;
    }

}
