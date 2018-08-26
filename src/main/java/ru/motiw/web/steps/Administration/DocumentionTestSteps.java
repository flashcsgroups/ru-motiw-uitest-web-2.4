package ru.motiw.web.steps.Administration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import ru.motiw.web.elements.elementsweb.DocumantionTestElements;
import ru.motiw.web.steps.BaseSteps;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;
import static ru.motiw.web.model.NamesOfManuals.CREATE_TASK;
import static ru.motiw.web.model.NamesOfManuals.DICTIONARY;
import static ru.motiw.web.model.URLMenu.MANUALS;

/**
 * Руководства
 */
public class DocumentionTestSteps extends BaseSteps {

    private DocumantionTestElements documantionTestElements = page(DocumantionTestElements.class);
   // private NamesOfManuals namesOfManuals = page(NamesOfManuals.class);

    /*
    *  Проверка Загрузки pdf-страницы
    * */
    private DocumentionTestSteps ensurePdfPageLoaded()  {
        documantionTestElements.getPdfPage().shouldBe(Condition.visible);
        return this;
    }

     /*
    *  Проверка Загрузки html-страницы
    * */
    private DocumentionTestSteps ensureHtmlPageLoaded() {
       documantionTestElements.getHtmlPage().shouldBe(Condition.visible);
        return this;
    }







    /*
    *  Находим все ссылки на pdf-мануалы. Проверяем кол-во найденных ссылок. Переходим и проверяем загрузку pdf-страницы.
    * */

    private DocumentionTestSteps checkPdfManuals() {
        //проверяем кол-во ссылок на pdf-мануалы
        documantionTestElements.getAllReferenceToPdfManual().shouldHaveSize(14);
        //Находим все ссылки на pdf-мануалы и помещаем их в массив.
        List<SelenideElement> elements = new ArrayList<>();
        for (SelenideElement selenideElement : documantionTestElements.getAllReferenceToPdfManual()) {
            elements.add(selenideElement);
        }
        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).click();//Переходим по найденным ссылкам на pdf-мануалы.
            ensurePdfPageLoaded(); //Проверяем Загрузку pdf-страницы
            goToURLManuals();
            sleep(5000);
        }

        return this;
    }
    /*
    *  Находим все ссылки на html-мануалы. Проверяем кол-во найденных ссылок. Переходим и проверяем загрузку html-страницы.
    * */

    private DocumentionTestSteps checkHtmlManuals() {
        //проверяем кол-во ссылок на html-руководства
        documantionTestElements.getAllReferenceToHtmlManual().shouldHaveSize(14);
        //Находим все ссылки на html-мануалы и помещаем их в массив.
        List<SelenideElement> elements = new ArrayList<>();

        for (SelenideElement selenideElement : documantionTestElements.getAllReferenceToHtmlManual()) {
            elements.add(selenideElement);

        }
        for (int i = 0; i < elements.size(); i++) {
            sleep(500);
            elements.get(i).click(); //Переходим по найденным ссылкам на html-мануалы.
            //проверяем наличие фрейма - он есть на всех страницах, за исключением страницы "Термины и определения"
            if (isElementPresent(By.xpath("//frame[@name='hmcontent']"))){
            getFrameObject($(documantionTestElements.getFrameHtml())); //  уходим во фрейм hmcontent
            ensureHtmlPageLoaded();//Проверяем Загрузку html-страницы
            //checkTextOnHtmlPage();   // Проверяем наличе текста, который отображается на страницах html-мануала
            } else
                ensureHtmlPageLoaded(); // на странице "Термины и определения" сразу проверяем загрузку, без перехода во фрейм (его там нет)
           isElementPresent1();//TEST
            goToURLManuals();
        }

        return this;
    }


//TEST
    private  boolean isElementPresent1() {
        try {
            isElementPresent(By.xpath("//frame[@name='hmcontent']"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    //тестововый!!
    /*
    private DocumentionTestSteps checkingNameOfManuals() {

        for (int i = 0; i <mm.size(); i++) {
            mm.get(i).toString(); //Переходим по найденным ссылкам на html-мануалы.
            //проверяем наличие фрейма - он есть на всех страницах, за исключением страницы "Термины и определения"
            if (isElementPresent(By.xpath("//frame[@name='hmcontent']"))){
                getFrameObject($(documantionTestElements.getFrameHtml())); //  уходим во фрейм hmcontent
                ensureHtmlPageLoaded();//Проверяем Загрузку html-страницы
                checkTextOnHtmlPage();   // Проверяем наличе текста, который отображается на страницах html-мануала
            }


        //где-то должна быть коллекция названий
        List<String> elements = new ArrayList<>();
        for (SelenideElement selenideElement : documantionTestElements.getAllReferenceToHtmlManual()) {
            elements.add((String) selenideElement);
        }

      /*
      //Забрать весь текст со страницы.
      //берем все элементы в div-е
        for (SelenideElement strings: $$(By.xpath("//div[@class=\"in\"]"))) {
            String text = strings.getText(); //переводим в строку
            System.out.println(text);
        }



        //в div  ищем все имена из коллекции
        //$$(By.xpath("//div[@class=\"in\"]")).shouldHave(CollectionCondition.texts("Термины и определения "));

        return this;
    }*/

//универсальный метод сравнение текста из коллекции
    private static void Qqq(ElementsCollection collection, java.lang.String text) {


        for (SelenideElement e : collection) {
            e.getText().equals(text);
            e.shouldBe(Condition.visible);
            }

        }


    private static void www(ElementsCollection collection, java.lang.String textValue) {

        for (SelenideElement e : collection) {
            if (e.getText().equals(textValue)) {
                e.shouldBe(Condition.visible);
                assertEquals("asdasd", DICTIONARY.getNameOfOfManual());
            }

        }
    }

    private DocumentionTestSteps eee() {
        for (SelenideElement strings : $$(By.xpath("//div[@class=\"in\"]"))) {
            java.lang.String text = strings.getText(); //переводим в строку
            assertEquals(
                    "Термины и определения pdf html\n" +
                    "Советы для начинающих pdf html\n" +
                    "Руководство администратора документооборота pdf html\n" +
                    "Руководство администратора системы pdf html\n" +
                    "Руководство по установке и обслуживанию системы МОТИВ pdf html\n" +
                    "Руководство программиста pdf html\n" +
                    "Руководство пользователя системы МОТИВ pdf html\n" +
                    "Руководство пользователя программы «Информер» pdf html\n" +
                    "Руководство пользователя программы XConductor pdf html\n" +
                    "Руководство пользователя. Мобильный интерфейс pdf html\n" +
                    "Руководство пользователя. Новый мобильный интерфейс pdf html\n" +
                    "Руководство по внедрению системы МОТИВ pdf html\n" +
                    "Установка и настройка КриптоПро ЭЦП pdf html\n" +
                    "Руководство пользователя программы ScanMaster (потоковое сканирование) pdf html", text);

        }
        return this;
    }

        //сравнение текста
    private DocumentionTestSteps diff() {
        www(documantionTestElements.getNamesOfManuals(), CREATE_TASK.getNameOfOfManual());

        return this;
    }






    /**
     * раздел: Справка - Руководства
     */
    public static DocumentionTestSteps goToURLManuals() {
        openSectionOnURL(MANUALS.getMenuURL());
        return page(DocumentionTestSteps.class);
    }

    /*
    *  Открываем руководство, Проверяем Загрузку pdf,html-страницы
    * */

    public DocumentionTestSteps checkManuals() {
       // documantionTestElements.test().click();
// посмотри for в методах - что-то е так
        //ensureHtmlPageLoaded();

       checkPdfManuals();
       checkHtmlManuals();
        //eee();
        //diff();
        //checkingNameOfManuals();
        //test();
        //downloadsFilesx();
        //*checkPdfManuals();
        return this;
    }


}