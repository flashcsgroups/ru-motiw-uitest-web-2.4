package ru.motiw.mobile.steps.ValidationStepsMobile;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.motiw.mobile.elements.Internal.FilesPreviewElementsMobile;
import ru.motiw.utils.GenericDate;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.fail;
import static ru.motiw.mobile.steps.ValidationStepsMobile.ImgForVerificationFiles.*;


/**
 * Проверки граф.коментариев на файлах
 */
public class ValidationAnnotationOnFilesStepsMobile {

    private FilesPreviewElementsMobile filesPreviewElementsMobile = page(FilesPreviewElementsMobile.class);
    private GenericDate genericDate = page(GenericDate.class);
    private ImageDiffer imageDiffer = new ImageDiffer();

    /**
     * Инициалы автора комментария
     *
     * @param author
     * @return Первые буквы Имени и Отчества автора комментария
     */
    public static String getInitialAuthorOfAnnotation(Employee author) {
        return author.getName().substring(0, 1).toUpperCase() + author.getPatronymic().substring(0, 1).toUpperCase();
    }


    /**
     * Проверяем, что панель инструментов для комментирования на pdf отображется
     */
    public ValidationAnnotationOnFilesStepsMobile annotationControlsToolbarForPdfAppears() {
        switchTo().frame($(By.xpath("//iframe")));  //Переходим во фрейм просмотра файлов
        annotationControlsToolbarAppears();
        switchTo().defaultContent();  //Уходим из фрейма просмотра файлов
        return this;
    }

    /**
     * Проверяем, что панель инструментов отображется
     */
    public ValidationAnnotationOnFilesStepsMobile annotationControlsToolbarAppears() {
        sleep(2000); // todo на linux без этого ожидания бывает ошибка. Поробовать на новой версии ChromeDriver или разбираться в чем причина.
        filesPreviewElementsMobile.getAnnotationControlsToolbar().waitUntil(Condition.exist, 10000);
        return this;
    }

    /**
     * Проверяем, что кнопка вкючения/выключения списка комментариев на pdf отображется
     */
    public ValidationAnnotationOnFilesStepsMobile annotationTriggerForPdfAppears() {
        switchTo().frame($(By.xpath("//iframe")));  //Переходим во фрейм просмотра файлов
        annotationTriggerAppears();
        switchTo().defaultContent();  //Уходим из фрейма просмотра файлов
        return this;
    }


    /**
     * Проверяем, что кнопка вкючения/выключения списка комментариев отображется
     */
    public ValidationAnnotationOnFilesStepsMobile annotationTriggerAppears() {
        sleep(2000); // todo на linux без этого ожидания бывает ошибка. Поробовать на новой версии ChromeDriver или разбираться в чем причина.
        filesPreviewElementsMobile.getAnnotationTrigger().waitUntil(Condition.visible, 10000);
        return this;
    }


    /**
     * Проверяем, что кнопка вкючения/выключения списка комментариев на pdf не отображется
     */
    public ValidationAnnotationOnFilesStepsMobile annotationTriggerForPdfNotExit() {
        switchTo().frame($(By.xpath("//iframe")));  //Переходим во фрейм просмотра файлов
        annotationTriggerNotExit();
        switchTo().defaultContent();  //Уходим из фрейма просмотра файлов
        return this;
    }


    /**
     * Проверяем, что кнопка вкючения/выключения списка комментариев не отображется
     */
    public ValidationAnnotationOnFilesStepsMobile annotationTriggerNotExit() {
        filesPreviewElementsMobile.getAnnotationControlsToolbar().waitUntil(Condition.visible, 10000);
        filesPreviewElementsMobile.getAnnotationTrigger().shouldNotBe(Condition.visible);
        return this;
    }


    /**
     * Проверяем, что кнопка выключения отображения комментария на pdf отображается
     *
     * @param author
     * @return
     */
    public ValidationAnnotationOnFilesStepsMobile triggerOfViewAnnotationByAuthorOnPdfActive(Employee author) {
        switchTo().frame($(By.xpath("//iframe")));  //Переходим во фрейм просмотра файлов
        triggerOfViewAnnotationByAuthorActive(author);
        switchTo().defaultContent();  //Уходим из фрейма просмотра файлов
        return this;
    }

    /**
     * Проверяем, что кнопка выключения отображения комментария отображается
     *
     * @param author
     * @return
     */
    public ValidationAnnotationOnFilesStepsMobile triggerOfViewAnnotationByAuthorActive(Employee author) {
        filesPreviewElementsMobile.getActiveAnnotationTriggerOfUserList(getInitialAuthorOfAnnotation(author)).waitUntil(Condition.visible, 5000);
        return this;
    }

    /**
     * Проверяем, что кнопка включения отображения комментария на pdf отображается
     *
     * @param author
     * @return
     */
    public ValidationAnnotationOnFilesStepsMobile triggerOfViewAnnotationByAuthorOnPdfNotActive(Employee author) {
        switchTo().frame($(By.xpath("//iframe")));  //Переходим во фрейм просмотра файлов
       triggerOfViewAnnotationByAuthorNotActive(author);
        switchTo().defaultContent();  //Уходим из фрейма просмотра файлов
        return this;
    }

    /**
     * Проверяем, что кнопка включения отображения комментария отображается
     *
     * @param author
     * @return
     */
    public ValidationAnnotationOnFilesStepsMobile triggerOfViewAnnotationByAuthorNotActive(Employee author) {
        filesPreviewElementsMobile.getNotActiveAnnotationTriggerOfUserList(getInitialAuthorOfAnnotation(author)).waitUntil(Condition.visible, 5000);
        return this;
    }

    /**
     * Проверяем, отсутствие кнопки включения/выключения отображения комментария на pdf конкретного пользователя
     *
     * @param author
     * @return
     */
    public ValidationAnnotationOnFilesStepsMobile triggerOfViewAnnotationByAuthorOnPdfNotExist(Employee author) {
        switchTo().frame($(By.xpath("//iframe")));  //Переходим во фрейм просмотра файлов
        triggerOfViewAnnotationByAuthorNotExist(author);
        switchTo().defaultContent();  //Уходим из фрейма просмотра файлов
        return this;
    }


    /**
     * Проверяем, отсутствие кнопки включения/выключения отображения комментария конкретного пользователя
     *
     * @param author
     * @return
     */
    public ValidationAnnotationOnFilesStepsMobile triggerOfViewAnnotationByAuthorNotExist(Employee author) {
        for (SelenideElement element : filesPreviewElementsMobile.getAnnotationList()) {
            element.shouldNotHave(Condition.text(getInitialAuthorOfAnnotation(author)));
        }
        return this;
    }

    /**
     * Проверяем отображение граф.комментария (ручка) на pdf-файле  через сравнение скриншота с эталонным изображением
     *
     * @return
     * @throws Exception
     */
    public ValidationAnnotationOnFilesStepsMobile annotationPenOnPdfExist() throws Exception {
        compareScreenshotOfPdfWithActualImage(IMAGE_PEN_ON_PDF); // сравнение
        return this;
    }

    /**
     * Проверяем отображение граф.комментария (маркер) на pdf-файле через сравнение скриншота с эталонным изображением
     *
     * @return
     * @throws Exception
     */
    public ValidationAnnotationOnFilesStepsMobile annotationMarkerOnPdfExist() throws Exception {
        compareScreenshotOfPdfWithActualImage(IMAGE_MARKER_ON_PDF); // сравнение
        return this;
    }

    /**
     * Проверяем что граф.комментарии не отображаются на pdf-файле через сравнение скриншота с эталонным изображением
     *
     * @return
     * @throws Exception
     */
    public ValidationAnnotationOnFilesStepsMobile annotationOnPdfNotExist() throws Exception {
        compareScreenshotOfPdfWithActualImage(IMAGE_ERASER_ON_PDF); // сравнение
        return this;
    }

    /**
     * Проверяем отображение граф.комментария (маркер и ручка одновременно) на pdf-файле через сравнение скриншота с эталонным изображением
     *
     * @return
     * @throws Exception
     */
    public ValidationAnnotationOnFilesStepsMobile annotationPenAndMarkerOnPdfExist() throws Exception {
        compareScreenshotOfPdfWithActualImage(IMAGE_PEN_AND_MARKER_ON_PDF); // сравнение
        return this;
    }

    /**
     * Проверяем отображение граф.комментария на pdf-файле первого и второго автора
     *
     * @return
     * @throws Exception
     */
    public ValidationAnnotationOnFilesStepsMobile annotationsFirstAndSecondAuthorOnPdfExist() throws Exception {
        compareScreenshotOfPdfWithActualImage(IMAGE_FIRST_AND_SECOND_ANNOTATION_ON_PDF); // сравнение
        return this;
    }

    /**
     * Проверяем отображение граф.комментария (ручка) на файле  через сравнение скриншота с эталонным изображением
     *
     * @return
     * @throws Exception
     */
    public ValidationAnnotationOnFilesStepsMobile annotationPenExist() throws Exception {
        compareScreenshotWithActualImage(IMAGE_PEN_ON_PDF); // сравнение
        return this;
    }

    /**
     * Проверяем отображение граф.комментария (маркер) на файле через сравнение скриншота с эталонным изображением
     *
     * @return
     * @throws Exception
     */
    public ValidationAnnotationOnFilesStepsMobile annotationMarkerExist() throws Exception {
        compareScreenshotWithActualImage(IMAGE_MARKER_ON_PDF); // сравнение
        return this;
    }

    /**
     * Проверяем что граф.комментарии не отображаются на файле через сравнение скриншота с эталонным изображением
     *
     * @return
     * @throws Exception
     */
    public ValidationAnnotationOnFilesStepsMobile annotationNotExist() throws Exception {
        compareScreenshotWithActualImage(IMAGE_ERASER_ON_IMG); // сравнение
        return this;
    }

    /**
     * Проверяем отображение граф.комментария (маркер и ручка одновременно) на файле через сравнение скриншота с эталонным изображением
     *
     * @return
     * @throws Exception
     */
    public ValidationAnnotationOnFilesStepsMobile annotationPenAndMarkerExist() throws Exception {
        compareScreenshotWithActualImage(IMAGE_PEN_AND_MARKER_ON_IMG); // сравнение
        return this;
    }

    /**
     * Проверяем отображение граф.комментария на файле первого и второго автора
     *
     * @return
     * @throws Exception
     */
    public ValidationAnnotationOnFilesStepsMobile annotationsFirstAndSecondAuthorExist() throws Exception {
        compareScreenshotWithActualImage(IMAGE_FIRST_AND_SECOND_ANNOTATION_ON_IMG); // сравнение
        return this;
    }

    /**
     * Сравнение скриншота на pdf-файле с эталонным изображением
     *
     * @param actualImage Скриншот эталонного изображения. Он должен быть снят именно на том мониторе, на котором будут запускаться тесты. Только в этом случае сравнение проходит успешно.
     * @return
     * @throws Exception
     */
    private ValidationAnnotationOnFilesStepsMobile compareScreenshotOfPdfWithActualImage(BufferedImage actualImage) throws Exception {
        switchTo().frame($(By.xpath("//iframe")));  //Переходим во фрейм просмотра файлов
        if (actualImage != null) {
        /*
        / Для выбора области скриншота используется набор элементов, которые содержатся внутри pdf-страницы.
        / Только так получается сделать скриншот небольшой области, чтобы в него не попадали другие области карточки документа, которые содержат меняющиеся данные.
        */
            $(By.xpath("//div[@id=\"viewerContainer\"]//div[@class=\"textLayer\"]/*")).waitUntil(Condition.exist, 10000); // Ожидание pdf-страницы
            List<WebElement> elements = getWebDriver().findElements(byXpath("//div[@id=\"viewerContainer\"]//div[@class=\"textLayer\"]/*"));

            Screenshot actual = new AShot()
                    .coordsProvider(new WebDriverCoordsProvider()) // т.к на странице нет jquery, то нужен coordsProvider
                    .takeScreenshot(getWebDriver(), elements);

            ImageDiff diff = imageDiffer.makeDiff(actualImage, actual.getImage());

            if (diff.hasDiff()) {
                failOnDifferentScreenshots(actual, diff);
            }
        }
        switchTo().defaultContent();  //Уходим из фрейма просмотра файлов
        return this;
    }

    /**
     * Сравнение скриншота с эталонным изображением
     *
     * @param expectedBufferedImage Скриншот эталонного изображения. Он должен быть снят именно на том мониторе, на котором будут запускаться тесты. Только в этом случае сравнение проходит успешно.
     *                              В гите файл изображения сжимается и из-за этого сравнение падает. Нужно держать у себя локально несжатые версиии изображений, которые будут использоваться для проверки.
     * @return
     * @throws Exception
     */
    private void compareScreenshotWithActualImage(BufferedImage expectedBufferedImage) throws Exception {
        if (expectedBufferedImage != null) {
            annotationControlsToolbarAppears();
            WebElement element = getWebDriver().findElement(byXpath("//div[@class=\"x-component x-img image-full-contain x-heighted x-widthed x-layout-fit-item x-size-monitored x-paint-monitored\"]/img"));

            Screenshot actual = new AShot()
                    .addIgnoredElement(By.xpath("//div[contains(@class,\"annotation-layers\")]"))
                    .addIgnoredElement(By.xpath("//div[contains(@class,\"annotation-controls\")]"))
                    .addIgnoredElement(By.xpath("//div[contains(@class,\"file-navigation-button\")]"))
                    .addIgnoredElement(By.xpath(" //div[contains(@id,\"ext-object-toolbar\")]"))
                    .coordsProvider(new WebDriverCoordsProvider()) // т.к на странице нет jquery, то нужен coordsProvider
                    .takeScreenshot(getWebDriver(), element);
            Screenshot expectedImage = new Screenshot(expectedBufferedImage);
            expectedImage.setIgnoredAreas(actual.getIgnoredAreas());
            ImageDiff diff = imageDiffer.makeDiff(expectedImage, actual);
            if (diff.hasDiff()) {
                failOnDifferentScreenshots(actual, diff);
            }
        }
    }

    /**
     * Завершение прохождение теста, сохранение файла скриншота и эталонного изображения
     *
     * @param actual
     * @param diff
     * @throws Exception
     */
    private void failOnDifferentScreenshots(Screenshot actual, ImageDiff diff) throws Exception {
        // Сохраняем полученные скриншоты на которых есть разница с эталонным изображением
        String mainFilePath = "build" + File.separator + "reports" + File.separator +
                "tests" + File.separator + "ru" + File.separator + "motiw" + File.separator + "testMobile" + File.separator;
        String nameScreenshotFile = "screenshot" + "_displaySize" + getWebDriver().manage().window().getSize() + "_" + genericDate.nowDateWithUnderScore() +"_.png";
        String nameDiffImageFile =  "diffImage" + "_displaySize" + getWebDriver().manage().window().getSize() + "_" + genericDate.nowDateWithUnderScore() +"_.png";
        ImageIO.write(actual.getImage(), "png", new File(mainFilePath + nameScreenshotFile)); // сделанный скриншот
        ImageIO.write(diff.getMarkedImage(), "png", new File(mainFilePath + nameDiffImageFile)); // разница скриншота с эталонным изображением

        fail("Скриншот с граф.комментарием не совпадает с эталонным изображением! см. " + mainFilePath + " " + nameScreenshotFile + ", " + nameDiffImageFile);
    }

}
