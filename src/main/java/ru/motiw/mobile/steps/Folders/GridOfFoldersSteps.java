package ru.motiw.mobile.steps.Folders;

import ru.motiw.mobile.elements.Documents.DocumentElementsMobile;
import ru.motiw.mobile.elements.Internal.GridOfFolderElementsMobile;
import ru.motiw.mobile.elements.Internal.InternalElementsMobile;
import ru.motiw.mobile.model.Document.RoleOfUser;
import ru.motiw.mobile.steps.InternalStepsMobile;
import ru.motiw.web.model.Document.Document;
import ru.motiw.web.model.Tasks.Folder;
import ru.motiw.web.model.Tasks.Task;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/*
 * Страница грида - Папка
 */

public class GridOfFoldersSteps extends InternalStepsMobile {

    private InternalElementsMobile internalElementsMobile = page(InternalElementsMobile.class);
    private DocumentElementsMobile documentElementsMobile = page(DocumentElementsMobile.class);
    private GridOfFolderElementsMobile gridOfFolderElementsMobile = page(GridOfFolderElementsMobile.class);


    /**
     * Проверяем отображение созданной задачи в гриде раздела - Папки
     *
     * @param task       return values of attributes of the task
     * @param folderTask наименование папки в к-й будет содержаться созданный объект - Задача
     * @return GridOfFoldersSteps
     */
    public GridOfFoldersSteps checkDisplayTaskInGrid(Task task, Folder folderTask) {
        // входим в созданную папку
        gridOfFolderElementsMobile.getFolder(folderTask.getNameFolder()).click();
        sleep(2000);//ожидание грида
        gridOfFolderElementsMobile.getItemInTheGrid(task.getTaskName())
                .shouldHave(exactText(task.getTaskName())); // проверяем отображение созданной задачи в гриде (отображается наименование задачи)
        return this;
    }

    /**
     * Проверяем исчезновение задачи в гриде раздела - Задачи
     *
     * @param task return values of attributes of the task
     * @return
     */
    public GridOfFoldersSteps checkDisappearTaskInGrid(Task task, Folder folderTask) {

        //sleep(2000);
        // Если после завершения задачи мы не перешли в папку, то переходим в созданную папку
        if (!internalElementsMobile.getMainTitle().is(text(folderTask.getNameFolder()))) {
            goToHome();
            // входим в созданную папку
            gridOfFolderElementsMobile.getFolder(folderTask.getNameFolder()).click();
            sleep(500);//ожидание грида
        }

        // Если пакет amq ещё не пришел, то грид может не обновиться. Поэтому делаем так:
        // 1. проверяем на отображение завершенной задачи в гриде.
        // 2. и если завершенная задача продолжает отображаться, то перезагружаем страницу и проверяем снова.
        if ((gridOfFolderElementsMobile.getItemInTheGrid(task.getTaskName()).isDisplayed())) {
            refresh();
            sleep(5000);// Todo может недожидаться на самом деле грида, и проходить. надо ожидание грида нормально реализовать
            (gridOfFolderElementsMobile.getItemInTheGrid(task.getTaskName())).shouldNotBe(visible);
        }


        return this;
    }

    /**
     * Проверяем отображение созданного документа в гриде раздела - Папки
     *
     * @param document return values of attributes of the task
     * @param folder   наименование папки в к-й будет содержаться созданный объект - Документ
     * @return GridOfFoldersSteps
     */
    public GridOfFoldersSteps checkDisplayDocumentInGrid(Document document, Folder folder) {
        // входим в созданную папку
        gridOfFolderElementsMobile.getFolder(folder.getNameFolder()).click();
        sleep(500);//ожидание грида
        gridOfFolderElementsMobile.getItemInTheGrid(document.getDocumentType().getDocRegisterCardsName())
                .shouldHave(exactText(document.getDocumentType().getDocRegisterCardsName())); // проверяем отображение созданного документа в гриде (отображается наименование типа документа )
        return this;
    }


    /**
     * Открываем задачу в гриде папки
     *
     * @param task return values of attributes of the task
     * @return
     */
    public GridOfFoldersSteps openTaskInGrid(Task task) {
        gridOfFolderElementsMobile.getItemInTheGrid(task.getTaskName()).click();
        sleep(500);
        return page(GridOfFoldersSteps.class);
    }


    /**
     * Открываем документ в гриде папки
     *
     * @param document return values of attributes of the document
     * @return
     */
    public GridOfFoldersSteps openDocumentInGrid(Document document, Folder folder) {

        if (!internalElementsMobile.getMainTitle().is(text(folder.getNameFolder()))) // Если мы не в папке, то переходим в неё
        {
            // входим в созданную папку
            gridOfFolderElementsMobile.getFolder(folder.getNameFolder()).click();
            sleep(500);//ожидание грида
        }

        gridOfFolderElementsMobile.getItemInTheGrid(document.getDocumentType().getDocRegisterCardsName()).click(); // Открываем по записи, которая содержит Тип документа.
        // todo нужно чтобы шаблон отображения в арме имел более уникальное значение чем Тип документа.  Открывать записи по такому уникальному значению.
        sleep(500);
        return page(GridOfFoldersSteps.class);
    }


    /**
     * Проверяем отображение или отсутствие признака нового документа в гриде папки
     *
     * @param document
     * @param roleOfUser
     */
    public void verifyMarkOfNewDocument(Document document, RoleOfUser roleOfUser) {
        switch (roleOfUser) {
            case AUTHOR:
                gridOfFolderElementsMobile.getMarkOfNewItem(document.getDocumentType().getDocRegisterCardsName()).shouldNotBe(visible);
                break;

            case CONSIDER_OF_DOCUMENT:
                gridOfFolderElementsMobile.getMarkOfNewItem(document.getDocumentType().getDocRegisterCardsName()).shouldBe(visible);
                break;

            default:
                throw new IllegalArgumentException("Неверное название роли:" + roleOfUser);
        }
    }

    /**
     * Проверяем отсутствие признака нового документа после возвращения в гриде папки
     *
     * @param document
     */
    public void checkDisappearMarkOfNewDocument(Document document, Folder folder) {
        // Если после выполнения операций задачи мы не перешли в папку, то переходим в созданную папку
        if (!internalElementsMobile.getMainTitle().is(text(folder.getNameFolder()))) {
            goToHome();
            // входим в созданную папку
            gridOfFolderElementsMobile.getFolder(folder.getNameFolder()).click();
            sleep(500);//ожидание грида
        }


        // Если пакет amq ещё не пришел, то грид может не обновиться. Поэтому делаем так:
        // 1. проверяем на отображение завершенной Признака в гриде.
        // 2. если Признак продолжает отображаться, то перезагружаем страницу и проверяем снова.
        if ((gridOfFolderElementsMobile.getMarkOfNewItem(document.getDocumentType().getDocRegisterCardsName()).isDisplayed())) {
            refresh();
            gridOfFolderElementsMobile.getItemInTheGrid(document.getDocumentType().getDocRegisterCardsName()).waitUntil(visible, 5000);
            gridOfFolderElementsMobile.getMarkOfNewItem(document.getDocumentType().getDocRegisterCardsName()).shouldNotBe(visible);
        }
    }

    /**
     * Нажатие на кнопку конт.меню операций для объекта (задачи\документа) в гриде папки
     *
     * @param nameOfItem - текст по которому находим объект в гриде
     */
    public void clickContextMenuForItemInGrid(String nameOfItem) {
        if ((gridOfFolderElementsMobile.getButtonOfOpenContextMenu(nameOfItem).is(visible))) {
            // click ContextMenu
            gridOfFolderElementsMobile.getButtonOfOpenContextMenu(nameOfItem).click();
            // Проверяем, что Кнопка закрытия конт.меню появилась
            gridOfFolderElementsMobile.getButtonOfCloseContextMenu(nameOfItem).shouldBe(visible);
            // Проверяем, что конт.меню открылось
            gridOfFolderElementsMobile.getContextMenu().shouldBe(visible);
        } else {
            // click ContextMenu
            gridOfFolderElementsMobile.getButtonOfCloseContextMenu(nameOfItem).click();
            // Проверяем, что Кнопка открытия конт.меню появилась
            gridOfFolderElementsMobile.getButtonOfOpenContextMenu(nameOfItem).shouldBe(visible);
            // Проверяем, что конт.меню закрылось
            gridOfFolderElementsMobile.getContextMenu().shouldNotBe(visible);
        }
    }
}

