package ru.motiw.web.elements.elementsweb;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/*
*Элементы на странице Руководств
 */

public class DocumantionTestElements {


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
    private SelenideElement checkPdfPage;




    public SelenideElement getManualGlossary() {
        return manualGlossary;
    }

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

    public  SelenideElement getPdfPage() {
        return  checkPdfPage;
    }
}
