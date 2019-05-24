package ru.motiw.web.model.Document;

import ru.motiw.web.model.Tasks.Task;

/**
 * Модель объекта - Резолюция
 */
public class Resolution extends Task {

    private String textOfResolution;
    private boolean reportOfExecution;


    /**
     *  Текст резолюции
      * @return
     */
    public String getTextOfResolution() {
        return textOfResolution;
    }

    public Resolution setTextOfResolution(String textOfResolution) {
        this.textOfResolution = textOfResolution;
        return this;
    }

    /**
     * Отчет по исполнению Документа
     * отчет не отправлен - false
     * отчет отправлен - true
     *
     * @return
     */
    public boolean isReportOfExecution() {
        return reportOfExecution;
    }

    public Resolution setReportOfExecution(boolean reportOfExecution) {
        this.reportOfExecution = reportOfExecution;
        return this;
    }
}
