package ru.motiw.mobile.steps.Folders;

import com.codeborne.selenide.ex.ElementNotFound;
import ru.motiw.mobile.elements.Documents.DocumentElementsMobile;
import ru.motiw.mobile.elements.Internal.GridOfFolderElementsMobile;
import ru.motiw.mobile.elements.Internal.InternalElementsMobile;
import ru.motiw.mobile.steps.InternalStepsMobile;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Document.Document;
import ru.motiw.web.model.Tasks.Folder;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.fail;
import static ru.motiw.utils.ElementUtil.scrollToAndClick;

/*
 * Страница грида - Папка
 */

public class GridOfFoldersSteps extends InternalStepsMobile {

    private InternalElementsMobile internalElementsMobile = page(InternalElementsMobile.class);
    private DocumentElementsMobile documentElementsMobile = page(DocumentElementsMobile.class);
    private GridOfFolderElementsMobile gridOfFolderElementsMobile = page(GridOfFolderElementsMobile.class);


    /**
     * Открытие папки
     */
    public void openFolder(Folder folder) {
        try {
            sleep(500);
            gridOfFolderElementsMobile.getFolder(folder.getNameFolder()).click();
        } catch (ElementNotFound notFoundFolder) {
            notFoundFolder.printStackTrace();
            fail("Не найдена папка");
        }

        try {
            // Ждем элемент с названием группировки Грида Папки
            gridOfFolderElementsMobile.getItemHeaderOfGridFolder().waitUntil(appear, 2000);
        } catch (Throwable notFoundItemHeaderOfGridFolder) {
            try {
                // Ждем элемент с Текст "Документов нет" в Гриде Папки
                gridOfFolderElementsMobile.getTextNoHaveDocumentInGridFolder().waitUntil(visible, 2000);
            } catch (ElementNotFound notFoundTextNoHaveDocumentInGridFolder) {
                notFoundItemHeaderOfGridFolder.printStackTrace();
                notFoundTextNoHaveDocumentInGridFolder.printStackTrace();
                fail("Не найден элемент в гриде папки");
            }
        }

    }


    /**
     * Проверяем отображение созданного объекта в гриде раздела - Папки
     *
     * @param nameOfItem уникальный текст по которому ищем объект в гриде (наименование задачи, наименование типа документа)
     * @param folder     наименование папки в к-й будет содержаться созданный объект
     * @return GridOfFoldersSteps
     */
    public GridOfFoldersSteps checkDisplayItemInGrid(String nameOfItem, Folder folder) {
        if (!internalElementsMobile.getMainTitle().is(text(folder.getNameFolder()))) {
            openFolder(folder);  // входим в созданную папку
        }
        gridOfFolderElementsMobile.getItemInTheGrid(nameOfItem).waitUntil(visible, 2000); // проверяем отображение созданного объекта в гриде (отображается наименование задачи)
        return this;
    }

    /**
     * Проверяем исчезновение объекта в гриде раздела - Папки
     *
     * @param nameOfItem уникальный текст по которому ищем объект в гриде (наименование задачи, наименование типа документа)
     * @return
     */
    public GridOfFoldersSteps checkDisappearItemInGrid(String nameOfItem, Folder folder) {

        // Если после завершения задачи мы не перешли в папку, то переходим в созданную папку
        if (!internalElementsMobile.getMainTitle().is(text(folder.getNameFolder()))) {
            goToHome();
            openFolder(folder);  // входим в созданную папку
        }

        // Если пакет amq ещё не пришел, то грид может не обновиться. Поэтому делаем так:
        // 1. проверяем на отображение завершенной задачи в гриде.
        // 2. и если завершенная задача продолжает отображаться, то перезагружаем страницу и проверяем снова.
        if ((gridOfFolderElementsMobile.getItemInTheGrid(nameOfItem).isDisplayed())) {
            refresh();
            gridOfFolderElementsMobile.getItemHeaderOfGridFolder().waitUntil(appear, 5000);
            (gridOfFolderElementsMobile.getItemInTheGrid(nameOfItem)).shouldNotBe(visible);
        }

        return this;
    }


    /**
     * Открываем объект в гриде папки
     *
     * @param nameOfItem уникальный текст по которому ищем объект в гриде (наименование задачи, наименование типа документа)
     * @return
     */
    public GridOfFoldersSteps openItemInGrid(String nameOfItem, Folder folder) {

        if (!internalElementsMobile.getMainTitle().is(text(folder.getNameFolder()))) // Если мы не в папке, то переходим в неё
        {
            openFolder(folder);  // входим в созданную папку
        }

        gridOfFolderElementsMobile.getItemInTheGrid(nameOfItem).click(); // Открываем по записи, которая содержит Тип документа.

        sleep(500);
        return page(GridOfFoldersSteps.class);
    }


    /**
     * Проверяем отображение или отсутствие признака нового документа в гриде папки
     *
     * @param document
     * @param currentUser текущий пользователь
     */
    public void verifyMarkOfNewDocument(Document document, Employee currentUser) {

        // Автор документа
        if (document.getAuthorOfDocument() != null && document.getAuthorOfDocument() == currentUser) {
            gridOfFolderElementsMobile.getMarkOfNewItem(document.getDocumentType().getDocRegisterCardsName()).shouldNotBe(visible);
        }

        // Рассматривающий документа
        if (document.getRouteSchemeForDocument().getUserRoute() != null) {
            for (Employee userRoute : document.getRouteSchemeForDocument().getUserRoute()) {
                if (userRoute == currentUser) {
                    gridOfFolderElementsMobile.getMarkOfNewItem(document.getDocumentType().getDocRegisterCardsName()).shouldBe(visible);
                }
            }
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
            openFolder(folder);  // входим в созданную папку
        }

        // Если пакет amq ещё не пришел, то грид может не обновиться. Поэтому делаем так:
        // 1. проверяем на отображение Признака в гриде.
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
        gridOfFolderElementsMobile.getItemInTheGrid(nameOfItem).waitUntil(visible, 5000);
        if ((gridOfFolderElementsMobile.getButtonOfOpenContextMenu(nameOfItem).is(visible))) {
            // Скроллинг до объекта (задачи\документа) в гриде и клик
            scrollToAndClick(gridOfFolderElementsMobile.getButtonOfOpenContextMenu(nameOfItem));
            // Ждем скрытие маски на кнопке Открытия/закрытия конт.меню
            gridOfFolderElementsMobile.getMaskOnButtonOfCloseContextMenu(nameOfItem).waitUntil(not(visible), 5000);
            // Проверяем, что Кнопка закрытия конт.меню появилась
            gridOfFolderElementsMobile.getButtonOfCloseContextMenu(nameOfItem).shouldBe(visible);

            // Проверяем отображение текста во всплывающем Toast-е "Нет доступных операций" или Конт.меню операций
            if (!internalElementsMobile.getToastWithText().is(text("Нет доступных операций"))) {
                gridOfFolderElementsMobile.getContextMenu().shouldBe(visible);
            }

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

