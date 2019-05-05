package ru.motiw.web.model.Document;

import ru.motiw.web.model.Tasks.Task;

/**
 * Модель объекта - Резолюция
 */
public class Resolution extends Task {

    private String textOfResolution;


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

}
