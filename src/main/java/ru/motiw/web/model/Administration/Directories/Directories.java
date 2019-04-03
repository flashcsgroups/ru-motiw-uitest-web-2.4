package ru.motiw.web.model.Administration.Directories;


import ru.motiw.web.model.Administration.TaskTypeListEditObject;

/**
 * Модель объекта системы - Справочники (Администрирование/Справочники)
 */
public class Directories extends TaskTypeListEditObject {

    private boolean shareRecords;
    private boolean accessToRecords;
    private boolean isMappingDevice;
    private boolean searchSettings;
    private DirectoriesField[] directoriesFields;

    public Directories(String nameTableType) {
        super(nameTableType);
    }

    /**
     * Общедоступность записей
     */
    public boolean getShareRecords() {
        return shareRecords;
    }

    public Directories setShareRecords(boolean shareRecords) {
        this.shareRecords = shareRecords;
        return this;
    }

    /**
     * Настройка доступа к записям
     */
    public boolean getAccessToRecords() {
        return accessToRecords;
    }

    public Directories setAccessToRecords(boolean accessToRecords) {
        this.accessToRecords = accessToRecords;
        return this;
    }

    /**
     * Линейный ли? true - да false - иерархический
     */
    public boolean getMappingDevice() {
        return isMappingDevice;
    }

    public Directories setMappingDevice(boolean isMappingDevice) {
        this.isMappingDevice = isMappingDevice;
        return this;
    }

    /**
     * true - поиск записей через SOLR; false - поиск записей через БД
     */
    public boolean getSearchSettings() {
        return searchSettings;
    }

    public Directories setSearchSettings(boolean searchSettings) {
        this.searchSettings = searchSettings;
        return this;
    }

    /**
     * Поля задачи - массив объектов - пользовательские поля любого типа
     */
    public DirectoriesField[] getDirectoriesFields() {
        return directoriesFields;
    }

    public Directories setDirectoriesFields(DirectoriesField[] directoriesFields) {
        this.directoriesFields = directoriesFields;
        return this;
    }

}