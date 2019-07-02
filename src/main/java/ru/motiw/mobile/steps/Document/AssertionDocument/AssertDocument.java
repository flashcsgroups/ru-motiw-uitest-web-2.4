package ru.motiw.mobile.steps.Document.AssertionDocument;

import ru.motiw.mobile.model.Document.TypeOfLocation;
import ru.motiw.mobile.steps.CardStepsMobile;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Document.Resolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Assert'ы для документа и его внутринних объектов
 *
 */
public class AssertDocument extends CardStepsMobile {

    /**
     * Текущие расположение - карточка документа
     *
     * @return
     */
    public boolean currentLocationIsDocumentCard() {
        return getCurrentLocation() == TypeOfLocation.PAGE_CARD;
    }

    /**
     * Метод для нахождения текущего пользователя среди отв.рук массива резолюций
     *
     * @param resolutions
     * @param currentUser
     * @return
     */
    public boolean currentUserIsExecutiveManagersInResolution(Resolution[] resolutions, Employee currentUser) {
        if (resolutions != null) {
            List<Employee> employees = new ArrayList<Employee>();
            Arrays.asList(resolutions).forEach(resolution -> employees.addAll(Arrays.asList(resolution.getExecutiveManagers())));
            if (employees.contains(currentUser)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Проверка всех резолюций на наличие по ним отправленного отчета по исполнению резолюции
     * отправлен - true
     * не отправлен - false
     *
     * @param resolutions - список резолюций
     * @return
     */
    public boolean resolutionHasReportOfExecution(Resolution[] resolutions) {
        for (Resolution resolution : resolutions) {
            if (resolution.isReportOfExecution()) {
                return true;
            }
        }
        return false;
    }
}
