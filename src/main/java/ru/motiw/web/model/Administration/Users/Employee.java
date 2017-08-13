package ru.motiw.web.model.Administration.Users;

/**
 * Модель объекта системы - Пользователь
 */
public class Employee {

    private Department department;
    private String fullName;
    private String lastName;
    private String name;
    private String patronymic;
    private Sex sex;
    private String birthDate;
    private boolean needsPasswordChange;
    private String jobTitle;
    private String loginName;
    private String password;
    private String confirmationPassword;
    private String newConfirmationPassword;
    private String newPassword;
    private Status status;
    private Module module;
    private String additionalNumber;
    private String userForcedSorting;
    private String email;

    /**
     * Подразделение пользователя
     */
    public Department getDepartment() {
        return department;
    }

    public Employee setDepartment(Department department) {
        this.department = department;
        return this;
    }

    /**
     * Полное имя пользователя (ФИО)
     */
    public String getFullName() {
        return fullName;
    }

    public Employee setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    /**
     * Фамилия
     */
    public String getLastName() {
        return lastName;
    }

    public Employee setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Имя
     */
    public String getName() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Отчество
     */
    public String getPatronymic() {
        return patronymic;
    }

    public Employee setPatronymic(String patronymic) {
        this.patronymic = patronymic;
        return this;
    }

    /**
     * Мужик ли?
     */
    public Sex getSex() {
        return sex;
    }

    public Employee setSex(Sex sex) {
        this.sex = sex;
        return this;
    }

    /**
     * Дата рождения
     */
    public String getBirthDate() {
        return birthDate;
    }

    public Employee setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    /**
     * Должность
     */
    public String getJobTitle() {
        return jobTitle;
    }

    public Employee setJobTitle(String jobtitle) {
        this.jobTitle = jobtitle;
        return this;
    }

    /**
     * Имя пользователя (login)
     */
    public String getLoginName() {
        return loginName;
    }

    public Employee setLoginName(String loginName) {
        this.loginName = loginName;
        return this;
    }

    /**
     * Пароль
     */
    public String getPassword() {
        return password;
    }

    public Employee setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * Подтверждение пароля
     */
    public String getСonfirmationPassword() {
        return confirmationPassword;
    }

    public Employee setСonfirmationPassword(String confirmationpassword) {
        this.confirmationPassword = confirmationpassword;
        return this;
    }

    /**
     * Новый Пароль
     */
    public String getNewPassword() {
        return newPassword;
    }

    public Employee setNewPassword(String newpassword) {
        this.newPassword = newpassword;
        return this;
    }

    /**
     * Новое подтверждение пароля
     */
    public String getNewСonfirmationPassword() {
        return newConfirmationPassword;
    }

    public Employee setNewСonfirmationPassword(String newconfirmationpassword) {
        this.newConfirmationPassword = newconfirmationpassword;
        return this;
    }

    /**
     * Статус
     */
    public Status getStatus() {
        return status;
    }

    public Employee setStatus(Status status) {
        this.status = status;
        return this;
    }

    /**
     * Модуль
     */
    public Module getModule() {
        return module;
    }

    public Employee setModule(Module module) {
        this.module = module;
        return this;
    }

    /**
     * Дополнительный номер
     */
    public String getAdditionalNumber() {
        return additionalNumber;
    }

    public Employee setAdditionalNumber(String additionalnumber) {
        this.additionalNumber = additionalnumber;
        return this;
    }

    /**
     * Порядок пользователя при принудительной сортировке
     */
    public String getUserForcedSorting() {
        return userForcedSorting;
    }

    public Employee setUserForcedSorting(String userforcedsorting) {
        this.userForcedSorting = userforcedsorting;
        return this;
    }

    /**
     * Нужна ли смена пароля?
     */
    public boolean getNeedsPasswordChange() {
        return needsPasswordChange;
    }

    public Employee setNeedsPasswordChange(boolean needsPasswordChange) {
        this.needsPasswordChange = needsPasswordChange;
        return this;
    }


    /**
     * Email
     */
    public String getEmail() {
        return email;
    }

    public Employee setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "lastName='" + lastName + '\'' +
                ", name=" + name +
                ", patronymic=" + patronymic +
                ", department=" + department +
                '}';
    }

}
