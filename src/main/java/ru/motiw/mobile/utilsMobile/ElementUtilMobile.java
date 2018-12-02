package ru.motiw.mobile.utilsMobile;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.testng.Assert.fail;

/**
 * Взаимодействие с элементами на стр.
 */
public  class ElementUtilMobile  {


    private static int componentId; //componentId = ext-selectdialog-{порядковый номер открытой формы}

    /**
     * Получаем componentId формы добавления пользователей  - (поля раб.группы)
     * т.к после каждого открытия формы выбора пользователей она остается в DOM, то приходится узнавать componentId формы, которая открыта в данный момент
     * componentId = ext-selectdialog-{порядковый номер открытой формы}
     */
    public int getCurrentComponentId() {
        if ((getIdVisibleComponent()) < 1) {
            fail("Failed Test! Нет Открытой Формы добавления пользователя");
        }
        return componentId;
    }

    private int getIdVisibleComponent() {

        //Ищем на странице все элементы формы выбора пользователя и помещаем их в список
        List<SelenideElement> allFormsForAddUser = new ArrayList<>($$(By.xpath("//div[contains(@id,\"ext-selectdialog\") and contains(@id,\"floatWrap\")]")));

        //id всех компонентов форм добавления пользователей находящихся на данный момент в DOM-е
        List<Integer> allComponentId = new ArrayList<>();

        //Получаем id в виде числа всех компонентов форм добавления пользователей (все ранее открытые формы остаются в DOM вплоть до принудительной перезагрузки страницы)
        for(SelenideElement e: allFormsForAddUser) {
            String element = e.toString();
            String stringAfterClean = element.replaceAll("( id=\"ext-selectdialog)[^&]*",""); //удаляем все лишние символы из строки - после id="ext-selectdialog находятся цифра, а значит при парсинге попадут лишние цифры.
            String getNumberOfComponentId = stringAfterClean.replaceAll("\\D+",""); //удаляем все символы, которые не являются числами
            allComponentId.add(Integer.parseInt(getNumberOfComponentId));
        }

        //Находим id компонента формы добавления пользователей, которая открыта в данный момент.
        for (Integer i : allComponentId) {

            if ($(By.xpath("//div[@data-componentid=\"ext-selectdialog-" + i + "\"]/div")).is(Condition.visible)) {
                componentId = i;
                break;
            }
        }
        return componentId;
    }



}

