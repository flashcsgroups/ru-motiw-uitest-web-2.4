package ru.motiw.testsWeb.DocflowAdministration;

import com.codeborne.selenide.testng.TextReport;
import com.codeborne.selenide.testng.annotations.Report;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.motiw.data.dataproviders.DocflowAdministration;
import ru.motiw.data.listeners.ScreenShotOnFailListener;
import ru.motiw.web.model.DocflowAdministration.DictionaryEditor.DictionaryEditor;
import ru.motiw.web.steps.Home.InternalSteps;
import ru.motiw.web.steps.Login.LoginStepsSteps;

import static com.codeborne.selenide.Selenide.page;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;
import static ru.motiw.web.steps.DocflowAdministration.DictionaryEditorSteps.goToURLDictionaryEditor;

@Listeners({ScreenShotOnFailListener.class, TextReport.class})
@Report
public class DictionaryEditorTest extends DocflowAdministration {

    private LoginStepsSteps loginPageSteps;
    private InternalSteps internalPage;

    @BeforeClass
    public void beforeTest() {
        loginPageSteps = page(LoginStepsSteps.class);
        internalPage = page(InternalSteps.class);
    }

    // Инициализируем объект - Редактор словарей
    DictionaryEditor dictionaryEditor = getRandomDictionaryEditor();

    @Test(priority = 1)
    public void createDictionaryEditor() throws Exception {
        loginPageSteps.loginAs(ADMIN);
        assertThat("Check that the displayed menu item 8 (Logo; Tasks; Documents; Messages; Calendar; Library; Tools; Details)",
                internalPage.hasMenuUserComplete()); // Проверяем отображение п.м. на внутренней странице
        assertTrue(loginPageSteps.isLoggedIn());

        // Переход в раздел Администрирование ДО/Редактор словарей
        goToURLDictionaryEditor()
        // Добавляем элементы словаря
        .addDictionaryEditor(dictionaryEditor);

        // Выход
        internalPage.logout();
        // Проверка - пользователь разлогинен
        assertTrue(loginPageSteps.isNotLoggedIn());
    }


}