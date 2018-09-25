package ru.motiw.mobile.model;

/**
 * Навигация меню (URL) по системе
 */
public enum URLMenuMobile {

    CREATE_TASK("/m/#createtask"),
    TASKS("/user/tab/user/uniontasks/"),
    INTERNAL_MENU("/user"),

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

    URLMenuMobile(String menuURL) {
        this.menuURL = menuURL;
    }

    public String getMenuURLMobile() {
        return menuURL;
    }

}
