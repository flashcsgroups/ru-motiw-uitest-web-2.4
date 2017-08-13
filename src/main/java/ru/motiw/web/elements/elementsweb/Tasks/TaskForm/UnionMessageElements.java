package ru.motiw.web.elements.elementsweb.Tasks.TaskForm;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 * Форма созданной задачи
 */
public class UnionMessageElements {

    @FindBy(xpath = "//span[text()][ancestor::em[contains(@class,'x-tab')]][ancestor::li[not(@style='display: none;')]][text()='Изолированные рабочие группы']")
    private SelenideElement tabIWG;

    /**
     * Вкладка - Изолированные рабочие группы
     * @return элемент вкладка ИРГ в форме задачи
     */
    public SelenideElement getTabIWG() {
        return tabIWG;
    }

}
