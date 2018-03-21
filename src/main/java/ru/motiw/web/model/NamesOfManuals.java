package ru.motiw.web.model;

/**
 * Навигация меню (URL) по системе
 */
public enum NamesOfManuals {

    CREATE_TASK("/user/tab/user/unionmessage/new/"),
    TASKS("/user/tab/user/uniontasks/"),
    INTERNAL_MENU("/user"),
    SSS ("Советы для начинающих3 "),

    DEPARTMENTS_AND_USERS("/user/tab/user/createuser/"),
    SYSTEM_INFO("/user/tab/user/sisteminfo/"),
    SYSTEM_OPTIONS("/user/tab/user/systemoptions/"),
    SEARCH_ADMIN("/user/tab/java/searchAdminPage/"),
    TYPE_TABLE("/user/tab/user/tasktypelist/table/"),
    DICTIONARY("/user/tab/user/tasktypelist/dictionary/"),
    TYPE_TASK("/user/tab/user/tasktypelist/"),
    DICTIONARY_EDITOR("/user/tab/user/diction/"),
    DOCUMENT_REGISTER_CARDS("/user/tab/user/DocRegisterCards/"),
    MY_PROPERTIES("/user/tab/user/pwd/"),
    EVENT_TEMPLATES("/user/tab/user/eventtemplates/"),
    MANUALS("/user/tab/user/manuals/"),


    CREATE_DOCUMENT("/user/tab/documents/newdocument/");


    private String menuURL;

    NamesOfManuals(String menuURL) {
        this.menuURL = menuURL;
    }

    public String getNameOfOfManual() {
        return menuURL;
    }

}
