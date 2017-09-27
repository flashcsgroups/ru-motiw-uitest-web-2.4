package ru.motiw.web.steps.Tasks;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.motiw.web.steps.BaseSteps;
import ru.motiw.web.elements.elementsweb.Tasks.UnionTasks.EditFormFoldersElements;
import ru.motiw.web.elements.elementsweb.Tasks.UnionTasks.UnionTasksElements;
import ru.motiw.web.model.Tasks.Folder;
import ru.motiw.web.model.Tasks.Task;
import ru.motiw.web.steps.Home.InternalSteps;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static ru.motiw.utils.WindowsUtil.openInNewWindow;
import static ru.motiw.web.model.URLMenu.TASKS;

/**
 * Шаги - отчет - грид задач - ЗАДАЧИ / ЗАДАЧИ
 */
public class UnionTasksSteps extends BaseSteps {

    UnionTasksElements unionTasksElements = page(UnionTasksElements.class);
    EditFormFoldersElements editFormFoldersElements = page(EditFormFoldersElements.class);

    /**
     * Переход в Задачи/Задачи
     */
    public static UnionTasksSteps goToUnionTasks() {
        openSectionOnURL(TASKS.getMenuURL());
        return page(UnionTasksSteps.class);
    }

    /**
     * Проверка загрузки страницы
     */
    private UnionTasksSteps ensurePageLoaded() {
        $(By.xpath("//div[@id='ext-comp-1001-bodyWrap']")).shouldBe(visible); // Панель ПУГЗ
        $(By.xpath("//div[contains(@data-ref,'bodyWrap')]/child::div[contains(@id,'taskpagingtoolbar')]"))
                .shouldBe(visible); // Панель Навигации по гриду
        return this;
    }

    /**
     * Поиск задачи
     */
    private UnionTasksSteps findTask(String taskName) {
        initializationInternalPage().searchFacilityOnTheGrid(taskName);
        return this;
    }

    /**
     * Ожидание маски грида - Задачи/Задачи
     */
    private UnionTasksSteps waitMaskForGridTask() {
        $(By.xpath("//div[@class='x-mask x-border-box']")).shouldNotBe(visible);
        sleep(500);
        return this;
    }


    /**
     * Открытие формы задачи в гриде по гриппировке Папка через поиск по гриду
     * Задачи/Задачи
     *
     * @param task   Название задачи
     * @param folder папка для поиска задачи в гриде
     */
    public void openExistingTaskInTheFolderThroughTheSearch(Task task, Folder folder) {
        waitMaskForGridTask();
        ensurePageLoaded();
        $(By.xpath("//span[contains(@class,'x-tree-node-text ')]/b[contains(text(),'" + parseNameFolder(folder.getNameFolder())[0] + "')]")).click();
        switchTo().defaultContent();
        findTask(task.getTaskName());
        switchTo().frame($(By.cssSelector("#flow")));
        waitMaskForGridTask();
        $$(By.xpath("//a[contains(@href,'/user/unionmessage') and contains(text(),'" + task.getTaskName() + "')]"))
                .first().shouldBe(visible);
        SelenideElement link = $(By.xpath("//a[text()='" + task.getTaskName() + "']"));
        openInNewWindow(link.getAttribute("href"));
    }

    /**
     * Открытие формы задачи в гриде по гриппировке Папка
     *
     * @param task   Название задачи
     * @param folder папка для поиска задачи в гриде
     */
    public void openAnExistingTaskInFolder(Task task, Folder folder) {
        waitMaskForGridTask();
        ensurePageLoaded();
        $(By.xpath("//span[contains(@class,'x-tree-node-text ')]/b[contains(text(),'" + parseNameFolder(folder.getNameFolder())[0] + "')]")).click();
        waitMaskForGridTask();
        $$(By.xpath("//a[contains(@href,'/user/unionmessage') and text()='" + task.getTaskName() + "']"))
                .first().shouldBe(visible);
        SelenideElement link = $(By.xpath("//a[text()='" + task.getTaskName() + "']"));
        openInNewWindow(link.getAttribute("href"));
    }

    /**
     * Инициализируем внутренюю стр. (InternalElements)
     */
    private InternalSteps initializationInternalPage() {
        return page(InternalSteps.class);
    }

    /**
     * Вводим имя папки
     *
     * @param nameFolder зн-ие для формирования имени папки
     */
    private UnionTasksSteps enterTheNameOfTheFolder(String nameFolder) {
        editFormFoldersElements.getFolderName().clear();
        editFormFoldersElements.getFolderName().setValue(nameFolder);
        return this;
    }

    /**
     * Метод помогающий подготовить интерфейс к взаимодействию с объектом - Папка
     * (Проверка загрузки страницы, раскрытие всех элементов дерева подразделений, выбор группировки
     * Папка)
     *
     * @param countPanelGrouping передаваемое зн-ия - кол-во содержащихся элементов на ПУГЗ
     *                           (Панель управления группировкой задач)
     */

    /* todo
    * try  со старым с 2.1 .xpath("//div[@id='tree_folders']//div[contains(@id,'extdd')]//a//span")));
    * для чего нужно непонятно, но без этого не работает
    */
    public UnionTasksSteps beforeAddFolder(int countPanelGrouping) {
        ensurePageLoaded();
        try {
            (new WebDriverWait(getWebDriver(), 0, 50))
                    .until(ExpectedConditions.presenceOfElementLocated(By
                            .xpath("//div[@id='tree_folders']//div[contains(@id,'extdd')]//a//span")));
        } catch (WebDriverException e) {

            selectTheGroupInTheGridForUserComplete(unionTasksElements.getPanelGroupingTasks(), countPanelGrouping);

            unionTasksElements.getGroupingFolder().click(); // выбрать группировка - Папка
        }
        waitMaskForGridTask();
        $(By.xpath("//div[contains(@class,' x-tree-icon x-tree')]")).shouldBe(visible); // отображение пиктограмм Папка
        unwrapAllNodes(unionTasksElements.getPlusSubsites(),
                By.xpath("//img[contains(@class,'x-tree-ec-icon') and contains(@class,'plus')]"));
        unionTasksElements.getFolderInTheGroup().first().shouldBe(visible);
        return this;
    }


    /**
     * Выбираем группировку на ПУГЗ (панель управления группировкой задач)
     *
     * @param panelGrouping      элемент панели ПУГЗ
     * @param countPanelGrouping кол-во элементов на пенели управления группировкой задачи
     */
 /*  public void selectTheGroupInTheGridForUserComplete(SelenideElement panelGrouping, int countPanelGrouping) {
        waitMaskForGridTask();
        panelGrouping.click();
        $$(By.xpath("//div[contains(@id,'ext-gen') and contains(@style,'visibility: visible')]//div[contains(@class,'x-combo-list-item')]"))
                .shouldHaveSize(countPanelGrouping); // проверяем, кол-во зн-ий в панели группировок
    }
*/


    public void selectTheGroupInTheGridForUserComplete(SelenideElement panelGrouping, int countPanelGrouping) {
        waitMaskForGridTask();
        panelGrouping.click();
        $$(By.xpath("//ul[@id='clickcombo-1003-picker-listEl']//li[contains(@class, 'x-boundlist-item')]"))
                .shouldHaveSize(countPanelGrouping); // проверяем, кол-во зн-ий в панели группировок

    }

    /**
     * Выбираем папку в иерархии папок по Имени
     *
     * @param folder передаваемые атрибуты папки
     */
    private void selectTheParentFolder(Folder folder) {
        $(By.xpath("//span[contains(@class,'x-tree-node-text ')]/b[contains(text(),'"
                + parseNameFolder(folder.getNameFolder())[0] + "')]")).shouldBe(visible).click();
        waitMaskForGridTask();
        sleep(1000);
        $(By.xpath("//span[contains(@class,'x-tree-node-text ')]/b[contains(text(),'"
                + parseNameFolder(folder.getNameFolder())[0] + "')]")).shouldBe(visible).contextClick();
    }

    /**
     * Добавление объекта - Папка
     *
     * @param folders кол-во передаваемых папок с атрибутами (настройки СП (смарт-папки); ОП (общие папки)..)
     */
    public void addFolders(Folder[] folders) {
        if (folders != null) {
            for (Folder folder : folders) {
                if (folder.getParentFolder() != null) {
                    selectTheParentFolder(folder.getParentFolder()); // Выбираем родительскую папку папку и выводим КМ для взаимодействия с папкой
                } else {
                    waitMaskForGridTask();
                    sleep(1000);
                    unionTasksElements.getFolderInTheGroup().first().contextClick();
                }
                unionTasksElements.getAddFolder().click(); // Добавить папку
                getFrameObject($(By.xpath("//iframe[contains(@src,'/user/smart_folder')]"))); // уходим во фрейм окна - Редактирование папки
                enterTheNameOfTheFolder(folder.getNameFolder());
                if (folder.isUseFilter() & folder.isChooseRelativeValue()) {
                    editFormFoldersElements.getCheckUseFilter().click();
                    setTheConditionOfFiltration(folder.getFilterField(), folder.isChooseRelativeValue());
                }
                if (folder.isSharedFolder()) editFormFoldersElements.getCheckFolderSharedFilter().click();
                if (folder.isAddSharedFolderForAll()) editFormFoldersElements.getCheckFolderAddForAll().click();
                if (folder.isAddSharedFolderForNewUsers())
                    editFormFoldersElements.getCheckAddSharedFolderForNewUsers().click();

                editFormFoldersElements.getSaveСhangesInTheCustomFolder().click();
                refresh();
                switchTo().defaultContent();
                switchTo().frame($(By.cssSelector("#flow")));
                unwrapAllNodes(unionTasksElements.getPlusSubsites(),
                        By.xpath("//img[contains(@class,'x-tree-ec-icon') and contains(@class,'plus')]"));
                checkDisplayCreateAFolderInTheGrid(folder.getNameFolder(), folder.isUseFilter()); // проверяем созданную Папку
            }
        }
    }

    /**
     * Формируем условие фильтра - Начало (относительное значение == Сегодня)
     *
     * @param field                передаваемое навание поля для формирования условия фильтрации
     * @param relativeImportanceOf относительное зн-ие для условия папки
     */
    private UnionTasksSteps setTheConditionOfFiltration(String field, boolean relativeImportanceOf) {
        $(By.xpath("//table[contains(@id,'treeview')][2]//td[1]//span")).click();
        // Выбираем поле для фильтрации
        $(By.xpath("//div[@id='sffieldcombochooser']//input")).clear();
        $(By.xpath("//div[@id='sffieldcombochooser']//input")).setValue(field);
        $(By.xpath("//table[contains(@id,'treeview')][2]//td[3]//div")).click();   // Выбор поля - Значение
        if (relativeImportanceOf) {
            $(By.xpath("//input[contains(@id,'checkbox') and (@type='checkbox')]//ancestor::span")).click();
        }
        return this;
    }

    /**
     * Проверяем отображение созданных папок на Панели управления группировкой задач (ПУГЗ)
     *
     * @param folder     передаем название папки
     * @param useAFilter передаем параметр установленного зн-ия - Использовать фильтр
     */
    private void checkDisplayCreateAFolderInTheGrid(String folder, boolean useAFilter) {
        if (useAFilter) {
            $(By.xpath("//span[contains(@class,'x-tree-node-text ')]/b[contains(text(),'"
                    + parseNameFolder(folder)[0] + "')]")).waitUntil(visible, 10000);
            $(By.xpath("//b[contains(text(), '" +
                    parseNameFolder(folder)[0] + "')]/ancestor::div[contains(@class,'x-grid-cell')]/child::div[@class=' x-tree-icon x-tree-icon-custom x-tree-icon-leaf rfolder-smart' or @class=' x-tree-icon x-tree-icon-custom x-tree-icon-leaf bfolder-smart' or @class=' x-tree-icon x-tree-icon-custom x-tree-icon-leaf yfolder-smart']")).waitUntil(visible, 10000).shouldBe(visible);
        } else
            $(By.xpath("//span[contains(@class,'x-tree-node-text ')]/b[contains(text(),'" + parseNameFolder(folder)[0] + "')]"))
                    .waitUntil(visible, 10000);

    }

    /**
     * Проверяем отображение созданной ОП (Общая папка) у нового пользователя
     *
     * @param folder             атрибуты объекта Папка
     * @param countPanelGrouping счетчик - кол-ва значений группировки на ПУГЗ (Панель управления группировкой задач)
     */
    public void checkTheMapASharedFolderFromTheNewlyCreatedUser(Folder folder, int countPanelGrouping) {
        beforeAddFolder(countPanelGrouping);
        checkDisplayCreateAFolderInTheGrid(folder.getNameFolder(), folder.isUseFilter()); // Проверяем созданные Папки
    }

    /**
     * Метод удаляет (0/0/0) в названии папки
     *
     * @param folder передаваемое значение названия папки (имя целиком со счетчиками)
     */
    private String[] parseNameFolder(String folder) {
        if (folder == null)
            return null;
        String[] splitNameFolderGrid;
        String nameFolderParse = $(By.xpath("//span[contains(@class,'x-tree-node-text ')]/b[contains(text(),'"
                + folder + "')]"))
                .getText();
        splitNameFolderGrid = nameFolderParse.split("[(/0)]");
        return splitNameFolderGrid;
    }

}