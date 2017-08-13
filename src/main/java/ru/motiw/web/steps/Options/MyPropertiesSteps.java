package ru.motiw.web.steps.Options;

import ru.motiw.web.steps.Administration.Users.UsersSteps;

import static com.codeborne.selenide.Selenide.page;
import static ru.motiw.web.model.URLMenu.MY_PROPERTIES;

/**
 * Стараница - Мои реквизиты
 */
public class MyPropertiesSteps extends UsersSteps {

    /**
     * Переход в меню - Настройки/Мои реквизиты
     *
     * @return MyPropertiesSteps страница - Мои реквизиты пользователя
     */
    public static MyPropertiesSteps goToURLPwd() {
        openSectionOnURL(MY_PROPERTIES.getMenuURL());
        return page(MyPropertiesSteps.class);
    }

}
