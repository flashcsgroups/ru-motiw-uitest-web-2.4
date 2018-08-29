package ru.motiw.web.elements.elementsweb.Internal;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;


/**
 * Внутренняя страница системы (ОМ - Основное меню)
 */
public class InternalElements {

    @FindBy(xpath = "//div[@id='left-panel'][ancestor::div[@id='menu']]//div[contains(@class,'menu-point') and not(@class='menu-point-hidden')]//div[@class='caption m-h-caption-wrapper']")
    private ElementsCollection menuElements;

    @FindBy(xpath = "//img[@class='motiw-logo-icon' or @class='logo-icon' ]")
    private ElementsCollection menuLogo;

    @FindBy(id = "task")
    private SelenideElement menuTask;

    @FindBy(id = "L_MENU_UNIONTASKS-menupoint")
    private SelenideElement tasks;

    @FindBy(css = "#L_INFORMER_CREATETASK-menupoint")
    private SelenideElement createTask;

    @FindBy(css = "#doc")
    private SelenideElement menuDocument;

    @FindBy(css = "#L_DOCUMENT_NEW-menupoint")
    private SelenideElement createDoc;

    @FindBy(id = "col")
    private SelenideElement сalendarMenu;

    @FindBy(id = "lib")
    private SelenideElement libMenu;

    @FindBy(id = "instr")
    private SelenideElement instrMenu;

    @FindBy(id = "L_MENU_ADMINISTRATION-menupoint")
    private SelenideElement menuAdministration;

    @FindBy(id = "L_MENU_USERADMIN-menupoint")
    private SelenideElement menuUsers;

    @FindBy(id = "L_MENU_TABLES-menupoint")
    private SelenideElement menuTables;

    @FindBy(id = "L_MENU_ADMIN_DICTIONARY-menupoint")
    private SelenideElement menuDirectories;

    @FindBy(id = "L_MENU_ADMIN_TYPE_TASK-menupoint")
    private SelenideElement menuTypeTask;

    @FindBy(css = "#x-menu-el-L_MENU_SYSTEMOPTIONS-menupoint")
    private SelenideElement menuSystemOptions;

    @FindBy(id = "L_DOCPROCESSING-menupoint")
    private SelenideElement docAdministrationMenu;

    @FindBy(id = "L_AU_DOC_CARDS-menupoint")
    private SelenideElement registerCardsMenu;

    @FindBy(id = "L_MENU_ROUTESCHEME_EDITOR-menupoint")
    private SelenideElement routeSchemeEditorMenu;

    @FindBy(id = "L_AU_DOCUMENT_COUNTERS-menupoint")
    private SelenideElement docCountersMenu;

    @FindBy(id = "L_GLOBAL_DICTIONARY_EDITOR-menupoint")
    private SelenideElement dictionaryEditorMenu;

    @FindBy(xpath = "//img[contains(@id,'logoutButtonImg')]")
    private SelenideElement logout;

    @FindBy(css = "#searchQueryEdit")
    private SelenideElement search;

    @FindBy(id = "L_AU_MENU_SETTINGS-menupoint")
    private SelenideElement menuSettings;

    @FindBy(id = "L_MENU_MYOPTIONS-menupoint")
    private SelenideElement menuMyOptions;

    @FindBy(id = "L_EVENT_TEMPLATE-menupoint")
    private SelenideElement menuEventTemplate;

    @FindBy(id = "L_MENU_SISTEM_INFORMATION-menupoint")
    private SelenideElement tester;

    @FindBy(id = "L_SEARCH_ADMIN-menupoint")
    private SelenideElement searchSystem;


    /**
     * Ссылки на все пункты меню
     *
     * @return колекция пунктов меню
     */
    public ElementsCollection getMenuElements() {
        return menuElements;
    }

    /**
     * Основное меню Задачи
     */
    public SelenideElement getMenuTask() {
        return menuTask;
    }


    /**
     * Задачи/Задачи
     */
    public SelenideElement getTasks() {
        return tasks;
    }

    /**
     * Задачи/Создать задачу
     */
    public SelenideElement getCreateTask() {
        return createTask;
    }

    /**
     * Меню  - Документы
     */
    public SelenideElement getMenuDocument() {
        return menuDocument;
    }

    /**
     * Документы/Создать документ
     */
    public SelenideElement getCreateDoc() {
        return createDoc;
    }

    /**
     * Календарь
     */
    public SelenideElement getСalendarMenu() {
        return сalendarMenu;
    }

    /**
     * Библиотека
     */
    public SelenideElement getLibMenu() {
        return libMenu;
    }

    /**
     * Инструменты
     */
    public SelenideElement getInstrMenu() {
        return instrMenu;
    }

    /**
     * Администрирование
     */
    public SelenideElement getMenuAdministration() {
        return menuAdministration;
    }

    /**
     * Инструменты/Администрирование/Пользователи
     */
    public SelenideElement getMenuUsers() {
        return menuUsers;
    }

    /**
     * Типы таблиц
     */
    public SelenideElement getMenuTables() {
        return menuTables;
    }

    /**
     * Справочники
     */
    public SelenideElement getMenuDirectories() {
        return menuDirectories;
    }

    /**
     * Типы задач
     */
    public SelenideElement getMenuTypeTask() {
        return menuTypeTask;
    }

    /**
     * Настройки системы
     */
    public SelenideElement getMenuSystemOptions() {
        return menuSystemOptions;
    }

    /**
     * Администрирование ДО
     */
    public SelenideElement getDocAdministrationMenu() {
        return docAdministrationMenu;
    }

    /**
     * Регистрационные карточки документов
     */
    public SelenideElement getRegisterCardsMenu() {
        return registerCardsMenu;
    }

    /**
     * Редактор маршрутных схем
     */
    public SelenideElement getRouteSchemeEditorMenu() {
        return routeSchemeEditorMenu;
    }

    /**
     * Счетчики документов
     */
    public SelenideElement getDocCountersMenu() {
        return docCountersMenu;
    }

    /**
     * Редактор словарей
     */
    public SelenideElement getDictionaryEditorMenu() {
        return dictionaryEditorMenu;
    }

    /**
     * Выход
     */
    public SelenideElement getLogout() {
        return logout;
    }

    /**
     * Главный поиск в гриде Задач
     */
    public SelenideElement getSearch() {
        return search;
    }

    /**
     * Меню Настройки
     */
    public SelenideElement getMenuSettings() {
        return menuSettings;
    }

    /**
     * Мои реквизиты
     */
    public SelenideElement getMenuMyOptions() {
        return menuMyOptions;
    }

    /**
     * Шаблон событий
     */
    public SelenideElement getMenuEventTemplate() {
        return menuEventTemplate;
    }

    /**
     * Информация о системе
     */
    public SelenideElement getTester() {
        return tester;
    }

    /**
     * Поисковая система
     */
    public SelenideElement getSearchSystem() {
        return searchSystem;
    }


}
