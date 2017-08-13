package ru.motiw.web.steps.Home;

import ru.motiw.web.elements.elementsweb.Internal.CalendarElements;
import ru.motiw.web.steps.BaseSteps;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.switchTo;

/**
 * Страница - Календарь
 */
public class CalendarSteps extends BaseSteps {

	private CalendarElements calendarElements = page(CalendarElements.class);

	/**
	 *  Уходим во фрейм системных календарей
	 */
	public CalendarSteps goToFremSystemCal() {
		switchTo().frame(calendarElements.getFremSystemCal());
		return this;
	}
	
	/**
	 * Клик чек-бокс системных календарей
	 */
	public CalendarSteps сheckBoxCalendar() {
		calendarElements.getClickCheckBox().click();
		return this;
	}
	
	


}
