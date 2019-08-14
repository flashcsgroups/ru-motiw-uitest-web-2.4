package ru.motiw.mobile.model;

import ru.motiw.web.model.Administration.Users.Employee;

/**
 * Автор граф.коментария
 */
public class AuthorOfAnnotation {

    private Employee employee;

    private Enum NumberOfAnnotation; // Порядковый номер комментария

    public enum NumbersOfAnnotation {FirstAnnotation, SecondAnnotation}

    public AuthorOfAnnotation(Employee authorOfAnnotation) {
        this.employee = authorOfAnnotation;
    }

    public Employee getEmployee() {
        return employee;
    }

    public AuthorOfAnnotation setEmployee(Employee employee) {
        this.employee = employee;
        return this;
    }

    /**
     * Получить порядковый номер комментария
     *
     * @return
     */
    public Enum getNumberOfAnnotation() {
        return NumberOfAnnotation;
    }

    /**
     * Установить порядковый номер комментария
     *
     * @return
     */
    public AuthorOfAnnotation setNumberOfAnnotation(Enum numberOfAnnotation) {
        NumberOfAnnotation = numberOfAnnotation;
        return this;
    }
}
