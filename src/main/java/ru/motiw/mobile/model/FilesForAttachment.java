package ru.motiw.mobile.model;


/**
 * Файлы для прикрепления
 */

public enum FilesForAttachment {

    FILE_1("Тестовое название.pdf"),
    FILE_2("Договор аренды.doc"),
    FILE_3("lease_contract.doc"),
    FILE_4("image.png"),
    FILE_5("hello_world.txt");


    private String nameFile;

    FilesForAttachment(String nameFile) {this.nameFile = nameFile;}

    public String getNameFile() { return nameFile; }

}
