package ru.motiw.web.elements.elementspda.Task;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import ru.motiw.web.model.Tasks.Folder;
import ru.motiw.web.model.Tasks.Task;
import ru.motiw.web.model.Administration.Users.Employee;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static org.apache.commons.io.FileUtils.readFileToString;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

/**
 * Страница - Форма задачи (Лента действий)
 */
public class TaskActionsStepsPDA extends TasksReportsStepsPDA {


    public final String SUBMIT_BUTTON_ADD_FILE = "input[name='myfile1']";

    File folder = new File(Configuration.reportsFolder);

    /*
    Кнопки в форме "Обычной задач"
     */
    @FindBy(xpath = "//*[@id='buttonDopMenu2']/div//div")
    private ElementsCollection buttonMenu;

    /*
     Сохранить
     */
    @FindBy(css = "div.save_button")
    private SelenideElement save;

    /*
     Завершить выполнение
     */
    @FindBy(css = "div.close_button")
    private SelenideElement completeTask;

    /*
     Лента действий
     */
    @FindBy(css = "#text")
    private SelenideElement action;

    /*
     Добавить файл
     */
    @FindBy(xpath = "//a[@onclick='addFile();']//span[2]")
    private SelenideElement addFile;

    /*
     Скачать файл
     */
    @FindBy(xpath = "//div[@class='message-files']//a[text()]/../a[@target='_blank']/../a//img")
    private ElementsCollection downloadFile;


    /**
     * Проверяем отображение формы созданной задачи
     *
     * @param task return values of attributes of the task
     */
    public TaskActionsStepsPDA openShapeCreatedTask(Task task) {
        $(By.cssSelector("div.save_button")).shouldBe(visible);
        $(By.xpath("//a[contains(text(),'" + task.getTaskName() + "')][ancestor::ul[@class='ui-listview']]")).shouldHave(Condition.visible);
        return this;
    }

    /**
     * Проверяем отображениия кнопок в форме задачи
     */
    public boolean resultsDisplayButtons() {
        buttonMenu.shouldHaveSize(5); // проверяем, что отображается 5 кнопок в форме задачи (Сохранить; Завершить выполнение; Play; Pause; Stop)
        return !buttonMenu.isEmpty();
    }

    /**
     * Открываем форму редактирования задачи (Атрибуты задачи)
     *
     * @param task return values of attributes of the task
     */
    public TaskDescriptionStepsPDA openFormEditTask(Task task, Employee user) {
        $(By.xpath("//a[contains(text(),'" + task.getTaskName() + "')][ancestor::ul[@class='ui-listview']]")).click();
        save.shouldBe(Condition.visible);
        $(By.xpath("//span[@name='autor']//a[contains(text(),'" + user.getLastName() + "')]")).shouldBe(Condition.visible);
        return page(TaskDescriptionStepsPDA.class);
    }


    /**
     * Добавляем текст в ленту действий
     *
     * @param textAction input text for feed action tasks
     */
    public TaskActionsStepsPDA saveActionsInTheTape(String textAction) {
        $(By.xpath("(//div[@class='menu-line']//a/li)[2]")).click();
        int n = 5;
        while (n > 0) {
            action.setValue(textAction);
            save.click();
            $(By.xpath("//ul[@class='ui-listview']//div/span[text()='" + textAction + "']")).shouldBe(Condition.visible);
            n--;
        }
        return this;
    }

    /**
     * Закрываем задачу
     *
     * @param task       return values of attributes of the task
     * @param textAction input text for feed action tasks
     * @param folderTask
     */
    public TaskActionsStepsPDA closeTask(Task task, String textAction, Folder folderTask) {
        checkDisplayTaskGrid(task, folderTask); // Проверяем отображение созданной задачи в гриде Задач
        openTaskInGrid(task); // открываем форму задачи в гриде Задач
        action.setValue(textAction); // пишем действие
        completeTask.click(); // Завершить выполнение
        $(By.xpath("//ul[@class='ui-listview']//div/span[text()='" + textAction + "']")).shouldBe(Condition.visible);
        return this;
    }

    /**
     * Аттачминг файлов в форме задачи
     *
     * @param textAction input text for feed action tasks
     * @param files      name files
     * @param sum        количество передаваемых файлов
     */
    public TaskActionsStepsPDA addAttachFiles(String textAction, String files, int sum) {
        for (int i = 0; i < sum; i++) {
            addFile.click();
            String mainFilePath = "src" + File.separator + "main" + File.separator +
                    "resources" + File.separator + "attachfiles" + File.separator;
            File file = $(By.cssSelector(SUBMIT_BUTTON_ADD_FILE))
                    .uploadFile(new File(mainFilePath, files));
            action.setValue(textAction);
            save.click();
            $(By.xpath("//div[@class='message-file-container'][text()='Файлы:']")).shouldHave(Condition.visible);
            $(By.xpath("//ul[@class='ui-listview']//div/span[text()='" + textAction + "']")).shouldBe(Condition.visible);
            assertTrue(file.exists());
            assertTrue(file.getPath().replace(File.separatorChar, '/').endsWith("src/main/resources/attachfiles/" + files + ""));
        }
        return this;
    }

    /**
     * Скачивание файла в форме ленты действий задачи
     *
     * @param nameFiles передаваемое Имя файла для скачки
     * @return TaskActionsStepsPDA форма задачи
     * @throws IOException
     */
    public TaskActionsStepsPDA downloadsFiles(String nameFiles) throws IOException {
        List<SelenideElement> elementsIndexing = new ArrayList<>();
        for (SelenideElement selenideElement : downloadFile) {
            elementsIndexing.add(selenideElement);
        }
        for (int i = 0; i < elementsIndexing.size(); i++) {
            elementsIndexing.get(i).isImage();
        }
        File downloadedFile = $(By.xpath("//div[@class='message-files']//a[text()='"
                + nameFiles + "']/../a[1]")).download();
        // TODO downloadedFile.getName() возвращает  - UTF-8''. assertEquals не проходит сравнение. Почему?
        //  assertEquals(nameFiles, downloadedFile.getName());
        // assertEquals(nameFiles, readFileToString(downloadedFile, "UTF-8"));
        assertTrue(downloadedFile.getAbsolutePath().startsWith(folder.getAbsolutePath()));
        return this;
    }

}
