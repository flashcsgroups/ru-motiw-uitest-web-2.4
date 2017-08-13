package ru.motiw.web.model.Administration.FieldsObject;


import ru.motiw.web.model.Administration.Directories.Directories;

/**
 * Модель объекта системы - Тип поля "Вложенный справочник"
 */
public class TypeListFieldsEnclosedDirectory extends FieldObject {

    private Directories directories;
    private String nameDirectory;

    /**
     * Название спр-ка
     *
     * @return
     */
    public String getDirectoryName() {
        return nameDirectory;
    }

    public TypeListFieldsEnclosedDirectory setDirectoryName(String nameDirectory) {
        this.nameDirectory = nameDirectory;
        return this;
    }

    /**
     * Справочник
     *
     * @return
     */
    public Directories getDirectories() {
        return directories;
    }

    public TypeListFieldsEnclosedDirectory setDirectories(Directories directories) {
        this.directories = directories;
        return this;
    }


}
