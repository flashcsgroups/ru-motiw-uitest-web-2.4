package ru.motiw.web.steps.Administration;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebElement;
import ru.motiw.web.elements.elementsweb.DocumantionTestElements;
import ru.motiw.web.steps.BaseSteps;

import java.util.List;

import static com.codeborne.selenide.Selenide.page;
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

        documantionTestElements.getManualGlossary().click();
        ensurePageLoaded();
        openSectionOnURL(MANUALS.getMenuURL());
        documantionTestElements.getManualTipsForBeginners().click();
        ensurePageLoaded();
        openSectionOnURL(MANUALS.getMenuURL());
        documantionTestElements.getAdminDoGuide().click();
        ensurePageLoaded();
        openSectionOnURL(MANUALS.getMenuURL());
        documantionTestElements.getAdminSecureAndSystemGuide().click();
        ensurePageLoaded();
        openSectionOnURL(MANUALS.getMenuURL());
        documantionTestElements.getInstallGuide().click();
        ensurePageLoaded();
        openSectionOnURL(MANUALS.getMenuURL());
        documantionTestElements.getApiManual().click();
        ensurePageLoaded();
        openSectionOnURL(MANUALS.getMenuURL());
        documantionTestElements.getUserGuide().click();
        ensurePageLoaded();
        openSectionOnURL(MANUALS.getMenuURL());
        documantionTestElements.getArmManual().click();
        ensurePageLoaded();
        return this;
    }

    public  class list {

            List<WebElement> vse = (List<WebElement>) documantionTestElements.getArmManual();

    }



    private DocumentionTestSteps test() {


        //*for (int i = 0; i<2; i++);
        //*for (List list : g())

        {

          //*  WebElement url = (WebElement) vse;
          //*  url.click();
            ensurePageLoaded();
            openSectionOnURL(MANUALS.getMenuURL());
            return this;
        }
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
       /* test();*/
        //*checkPdfManuals();
        return this;
    }


}