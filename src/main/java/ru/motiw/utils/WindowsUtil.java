package ru.motiw.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.Set;

import static com.codeborne.selenide.Selenide.executeJavaScript;

/**
 * Windows tab
 */
public abstract class WindowsUtil {

    /**
     * Открыть url в новом окне
     *
     * @param url - url страницы
     *            Пример - WebElement link = driver.findElement(By.tagName("a"));
     *            openInNewWindow(link.getAttribute("href"));
     */
    public static void openInNewWindow(String url) {
        executeJavaScript("window.open(arguments[0])", url);
    }

    /**
     * The code below will open the link in new Tab
     * <p/>
     * пример - driver.findElement(By.linkText("urlLink")).sendKeys(selectLinkOpenInNewTab);
     */
    public static String selectLinkOpenInNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);

    /**
     * Метод появление новго окна - переключение, т.е. взаимодействие с данным окном
     * <p/>
     * пример использования:
     * driver.switchTo().window(new WebDriverWait(driver, 10).until(newWindowForm(By.cssSelector("#searchField"))));
     *
     * @param locator element that should contain the new page
     */
    public static ExpectedCondition<String> newWindowForm(final By locator) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver d) {
                String initialWindowHandle = d.getWindowHandle(); // Запоминаем в начале, в каком окне мы находились
                String found = null;
                Set<String> windowHandles = d.getWindowHandles(); // возвращает множ-во идентификаторов окон И далее проходим в цикле в каждое окно и проверяем
                // имеется ли необходимый элемент в новом окне, нет - тогда переключаемся в следующее, если совпадает, то true
                for (String handle : windowHandles) {
                    try {
                        d.switchTo().window(handle);
                        if (d.findElement((locator)).isDisplayed()) {
                            found = handle;
                            break;
                        }
                    } catch (WebDriverException e) { // игнорируем все исключения
                    }
                }
                d.switchTo().window(initialWindowHandle);
                return found;
            }
        };
    }

}
