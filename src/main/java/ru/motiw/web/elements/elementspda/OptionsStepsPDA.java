package ru.motiw.web.elements.elementspda;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.motiw.web.steps.BaseSteps;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/*
 * Страница - Настройки
 */
public class OptionsStepsPDA extends BaseSteps {

    /*
    * Сохранить
     */
    @FindBy(xpath = "(//input[@type='submit'])[1]")
    private SelenideElement save;

    /*
    * Отображение скрытых задач
     */
    @FindBy(xpath = "//input[@id='secret']")
    private SelenideElement showHiddenTasks;

    /*
    * Возможность присоединения файлов
     */
    @FindBy(xpath = "//input[@id='secret2']//..//span[2]")
    private SelenideElement attachFiles;


    /**
     * Устанавливаем значение - Возможность присоединения файлов
     * если true - идет проверка установлен ли признак, если нет - устанавливаем значение; Если признак стоит оставляем все без изменения
     *
     */
    public OptionsStepsPDA selAttachFiles(boolean attach) {
        if (attach) {
            try {
                (new WebDriverWait(getWebDriver(), 0, 50))
                        .until(ExpectedConditions.presenceOfElementLocated(By
                                .xpath("//input[@id='secret2']/..//span[contains(@class,'ui-icon-checkbox-on')]")));
            } catch (WebDriverException e) {
                attachFiles.click();
                save.click();
                sleep(800);
                getWebDriver().navigate().refresh();
                $(By.xpath("//input[@id='secret2']/..//span[contains(@class,'ui-icon-checkbox-on')]")).shouldBe(Condition.visible);
            }
        }
        return this;
    }



}





