package ru.motiw.web.elements.elementsweb.Tasks;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;

/**
 * Грид раздела - Шаблоны задачи
 */
public class GridTemplateOfTaskElements {


    @FindBy(xpath = "//a[@data-qtip=\"Добавить\"]//span[@class=\"x-btn-icon-el x-btn-icon-el-default-toolbar-small tb-button-icon \"]")
    private SelenideElement addTemplate;

    @FindBy(xpath = "//a[@data-qtip=\"Добавить\"]//span[@class=\"x-btn-icon-el x-btn-icon-el-default-toolbar-small tb-button-icon \"]")
    private SelenideElement recordOfTemplateInTheGrid;

    //Добавить
    public SelenideElement getAddTemplateButton() {
        return addTemplate;
    }

    //Запись в гриде
    public SelenideElement getRecordOfTemplateInTheGrid(String nameOfTemplate) {
        return $(By.xpath("//table//span[text()='"+ nameOfTemplate + "']"));
    }
}
