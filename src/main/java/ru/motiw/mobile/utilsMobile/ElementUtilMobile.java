package ru.motiw.mobile.utilsMobile;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$;

/**
 * Взаимодействие с элементами на стр.
 */
public abstract class ElementUtilMobile {


    public int componentId;

    /**
     * Открытие формы выбора пользователей  - поля раб.группы
     */
    public int getCurrentComponentId() {

        qq();

        return componentId;
    }

    private int qq() {

        //Ищем на странице все элементы с componentId;
        List<SelenideElement> elements = new ArrayList<>($$(By.xpath("//div[contains(@id,\"ext-selectdialog\") and contains(@id,\"floatWrap\")]")));

        for(SelenideElement e: elements) {

           String q = e.toString();
           System.out.print(q);
        }

        // выбираем максимальный componentId и возвращаем его


        return 1;
    }


}
