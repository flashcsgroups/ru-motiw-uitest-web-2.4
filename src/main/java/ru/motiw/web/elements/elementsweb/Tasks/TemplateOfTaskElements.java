package ru.motiw.web.elements.elementsweb.Tasks;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Форма создания шаблона задачи
 */
public class TemplateOfTaskElements {

    @FindBy(xpath = "//input[@id=\"template_name\"]")
    private SelenideElement inputTemplateName;

    @FindBy(xpath = "//input[@type=\"button\" and @value=\"Сохранить\"]")
    private SelenideElement buttonSave;

    @FindBy(xpath = "//input[@type=\"button\" and @value=\"Вернуться без сохранения\"]")
    private SelenideElement buttonExitWithoutSave;

    //input Название шаблона
    public SelenideElement getInputTemplateName() {
        return inputTemplateName;
    }

    //Сохранить
    public SelenideElement getButtonSave() {
        return buttonSave;
    }

    //Вернуться без сохранения
    public SelenideElement getButtonExitWithoutSave() {
        return buttonExitWithoutSave;
    }
}
