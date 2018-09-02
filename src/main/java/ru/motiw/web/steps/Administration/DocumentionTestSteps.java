package ru.motiw.web.steps.Administration;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.motiw.web.elements.elementsweb.DocumantionTestElements;
import ru.motiw.web.steps.BaseSteps;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static ru.motiw.utils.WindowsUtil.newWindowForm;
import static ru.motiw.web.model.URLMenu.MANUALS;

/**
 * Руководства
 */
public class DocumentionTestSteps extends BaseSteps {

    private DocumantionTestElements documantionTestElements = page(DocumantionTestElements.class);

    /*
    *  Проверка Загрузки pdf-страницы
    * */
    private DocumentionTestSteps ensurePageLoaded()  {
        documantionTestElements.getPdfPage().shouldBe(Condition.visible);
        return this;
    }

    /*
    *  Открываем руководство, Проверяем Загрузку pdf-страницы, Переход в раздел "Руководства"
    *  Пока не все Руководства
    * */

    private DocumentionTestSteps checkPdfManuals() {
        String parentWindowHandler = getWebDriver().getWindowHandle();
        documantionTestElements.getManualGlossary().click();
        getWebDriver().switchTo().window(new WebDriverWait(getWebDriver(), 10).until(newWindowForm(By.xpath("//embed[@type='application/pdf']")))); //здесь сразу и проверяется
        closeWindow();
        getWebDriver().switchTo().window(parentWindowHandler);


        openSectionOnURL(MANUALS.getMenuURL());
        documantionTestElements.getManualTipsForBeginners().click();
        getWebDriver().switchTo().window(new WebDriverWait(getWebDriver(), 10).until(newWindowForm(By.xpath("//embed[@type='application/pdf']"))));
        closeWindow();
        getWebDriver().switchTo().window(parentWindowHandler);


        openSectionOnURL(MANUALS.getMenuURL());
        documantionTestElements.getAdminDoGuide().click();
        getWebDriver().switchTo().window(new WebDriverWait(getWebDriver(), 10).until(newWindowForm(By.xpath("//embed[@type='application/pdf']"))));
        closeWindow();
        getWebDriver().switchTo().window(parentWindowHandler);

        openSectionOnURL(MANUALS.getMenuURL());
        documantionTestElements.getAdminSecureAndSystemGuide().click();
        getWebDriver().switchTo().window(new WebDriverWait(getWebDriver(), 10).until(newWindowForm(By.xpath("//embed[@type='application/pdf']"))));
        closeWindow();
        getWebDriver().switchTo().window(parentWindowHandler);

        openSectionOnURL(MANUALS.getMenuURL());
        documantionTestElements.getInstallGuide().click();
        getWebDriver().switchTo().window(new WebDriverWait(getWebDriver(), 10).until(newWindowForm(By.xpath("//embed[@type='application/pdf']"))));
        closeWindow();
        getWebDriver().switchTo().window(parentWindowHandler);


        openSectionOnURL(MANUALS.getMenuURL());
        documantionTestElements.getApiManual().click();
        getWebDriver().switchTo().window(new WebDriverWait(getWebDriver(), 10).until(newWindowForm(By.xpath("//embed[@type='application/pdf']"))));
        closeWindow();
        getWebDriver().switchTo().window(parentWindowHandler);


        openSectionOnURL(MANUALS.getMenuURL());
        documantionTestElements.getUserGuide().click();
        getWebDriver().switchTo().window(new WebDriverWait(getWebDriver(), 10).until(newWindowForm(By.xpath("//embed[@type='application/pdf']"))));
        closeWindow();
        getWebDriver().switchTo().window(parentWindowHandler);

        openSectionOnURL(MANUALS.getMenuURL());
        documantionTestElements.getArmManual().click();
        getWebDriver().switchTo().window(new WebDriverWait(getWebDriver(), 10).until(newWindowForm(By.xpath("//embed[@type='application/pdf']"))));
        closeWindow();
        getWebDriver().switchTo().window(parentWindowHandler);

        return this;
    }

    /**
     * раздел: Справка - Руководства
     */
    public static DocumentionTestSteps goToURLManuals() {
        openSectionOnURL(MANUALS.getMenuURL());
        return page(DocumentionTestSteps.class);
    }



    public DocumentionTestSteps checkManuals() {
        checkPdfManuals();
        return this;
    }


}