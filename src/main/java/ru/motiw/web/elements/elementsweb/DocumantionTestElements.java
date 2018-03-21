package ru.motiw.web.elements.elementsweb;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/*
*Элементы на странице Руководств
 */

public  class DocumantionTestElements {


    @FindBy(xpath = "//p/a[@href=\"/FAQ/glossary.pdf\"]")
    private SelenideElement manualGlossary;

    @FindBy(xpath = "//p/a[@href=\"/FAQ/tips_for_beginners.pdf\"]")
    private SelenideElement tipsForBeginners;

    @FindBy(xpath = "//p/a[@href=\"/FAQ/admin_do_guide.pdf\"]")
    private SelenideElement adminDoGuide;

    @FindBy(xpath = "//p/a[@href=\"/FAQ/admin_secure_and_system_guide.pdf\"]")
    private SelenideElement adminSecureAndSystemGuide;

    @FindBy(xpath = "//p/a[@href=\"/FAQ/install_guide.pdf\"]")
    private SelenideElement  installGuide;

    @FindBy(xpath = "//p/a[@href=\"/FAQ/api_manual.pdf\"]")
    private SelenideElement  apiManual;

    @FindBy(xpath = "//p/a[@href=\"/FAQ/User_Guide.pdf\"]")
    private SelenideElement  userGuide;

    @FindBy(xpath = "//p/a[@href=\"/FAQ/arm_manual.pdf\"]")
    private SelenideElement  armManual;

    @FindBy(xpath = "//embed[@type='application/pdf']")
    private SelenideElement checkPdfManualPage;

    @FindBy(xpath = "//p/a[contains(@href,  'pdf')]")
    private ElementsCollection allReferenceToPdf;

    @FindBy(xpath = "//*[text()=\"Далее\" or text()=\"1 Глоссарий\"]") //ссылка с текстом "Далее" во html-фрейме на большенстве страницах или ссылка с текстом "1 Глоссарий" на странице "Термины и определения".
    private SelenideElement checkHtmlManualPage;

    /*
    * Коллекция всех элементов c текстом в мануале
    */
    @FindBy (xpath = "//td[@align=\"left\" and  not(contains(@class,\"toc\")) and @style]//span")
    private ElementsCollection allTextOnHtmlManual;

    @FindBy(xpath = "//p/a[contains(@href,  'html')]")
    private ElementsCollection allReferenceToHtml;

    @FindBy(xpath = "//frame[@name='hmcontent']")
    private SelenideElement frameHtml;


    @FindBy(xpath = "//p[contains(text(),'Советы для начинающих ')]")
    private ElementsCollection namesOfManuals;
//div[@class="in"]

    @FindBy(xpath = "(//p/a[contains(@href,  'html')])[2]")
    private SelenideElement test;




    public SelenideElement getManualGlossary() { return manualGlossary; }

    public  SelenideElement getManualTipsForBeginners() {
        return tipsForBeginners;
    }

    public SelenideElement getAdminDoGuide() {
        return adminDoGuide;
    }

    public SelenideElement getAdminSecureAndSystemGuide() {
        return adminSecureAndSystemGuide;
    }

    public SelenideElement getInstallGuide() {
        return installGuide;
    }

    public SelenideElement getApiManual() {
        return apiManual;
    }

    public SelenideElement getUserGuide() {
        return userGuide;
    }

    public SelenideElement getArmManual() {
        return armManual;
    }

    //наличе элемента отвечающего за отображение pdf
    public  SelenideElement getPdfPage() {
        return  checkPdfManualPage;
    }

    //Все ссылки на pdf-мануалы.
    public ElementsCollection getAllReferenceToPdfManual() {
        return allReferenceToPdf;
    }

    //наличе элемента  и текста, который отображается на странице html-мануала
    public SelenideElement getHtmlPage() {
        return  checkHtmlManualPage;
    }
    //Находим все ссылки на Html-мануалы.
    public ElementsCollection getAllReferenceToHtmlManual() {
        return allReferenceToHtml;
    }

    //Весь текст в мануале
    public  ElementsCollection getAllTextOnHtmlManual () {return allTextOnHtmlManual; }

    public SelenideElement getFrameHtml(){
        return frameHtml;
    }

    public ElementsCollection getNamesOfManuals(){
        return namesOfManuals;
    }
    public SelenideElement test() {
        return test;
    }
}

