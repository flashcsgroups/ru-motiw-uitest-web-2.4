package ru.motiw.web.model.Administration.Directories;

import ru.motiw.web.model.Administration.FieldsObject.FieldObject;

public class DirectoriesField extends  FieldObject{

   private String directoryItems;

    /**
     * Название Элемента - Запись справочника
     */
    public String getDirectoriesItem() {
        return directoryItems;
    }

    public DirectoriesField setDirectoryItems(String directoryItems) {
        this.directoryItems = directoryItems;
        return this;
    }



}
