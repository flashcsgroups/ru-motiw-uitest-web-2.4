package ru.motiw.data.dataproviders;

import org.testng.annotations.DataProvider;
import ru.motiw.data.BaseTest;
import ru.motiw.mobile.model.FilesForAttachment;
import ru.motiw.web.model.Administration.Directories.Directories;
import ru.motiw.web.model.Administration.Directories.DirectoriesField;
import ru.motiw.web.model.Administration.FieldsObject.*;
import ru.motiw.web.model.Administration.TaskTypeListEditObject;
import ru.motiw.web.model.Administration.TasksTypes.ComputeModeNumerator;
import ru.motiw.web.model.Administration.TasksTypes.ObligatoryField;
import ru.motiw.web.model.Administration.TasksTypes.SettingsForTaskTypeListFields;
import ru.motiw.web.model.Administration.TasksTypes.TasksTypes;
import ru.motiw.web.model.Administration.Users.Department;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.CorrectionMethod;
import ru.motiw.web.model.OpenFilesForEdit;
import ru.motiw.web.model.ShiftDirection;
import ru.motiw.web.model.Tasks.*;

import static ru.motiw.data.dataproviders.Administration.getRandomDepartment;
import static ru.motiw.data.dataproviders.Administration.getRandomEmployer;
import static ru.motiw.web.model.Administration.TasksTypes.SettingsForTaskTypeListFields.NO;

/**
 * Данные раздела - Задача в мобильном интерфейсе
 */
public abstract class TasksMobile extends BaseTest {

    // Инициализация объекта -  Папка
    private Folder[] folder = getRandomArrayFolders();

    // Инициализация объекта -  Шаблон задачи
    private TemplateOfTask[] templateOfTasks = getRandomTemplateOfTask();

    // Инициализация объекта - Подразделение
    private Department department = getRandomDepartment();

    // Инициализация объекта - Пользователи
    private Employee[] authors = new Employee[]{
            getRandomEmployer().setLastName("АвторЗадачи-" + randomString(5)),
            getRandomEmployer().setLastName("АвторЗадачи-" + randomString(5))
    };

    private Employee[] executiveManagers = new Employee[]{
            getRandomEmployer().setLastName("ОРЗадачи-" + randomString(5)),
            getRandomEmployer().setLastName("ОРЗадачи-" + randomString(5))
    };

    private Employee[] controller = new Employee[]{
            getRandomEmployer().setLastName("КонтролерЗадачи-" + randomString(5)),
            getRandomEmployer().setLastName("КонтролерЗадачи-" + randomString(5))
    };

    private Employee[] worker = new Employee[]{
            getRandomEmployer().setLastName("ИсполнительЗадачи-" + randomString(5)),
            getRandomEmployer().setLastName("ИсполнительЗадачи-" + randomString(5))
    };



    //------------------------------------------------------------------------Инициализация полей и настроек объекта - Регистрационная карточка Справочника
    // 1. СТРОКА (Выбор из списка == Да; Уникальное; Обязательное)
    FieldObject fieldStringIsNotListChoiceDirectories = new TypeListFieldsString()
            .setFieldName("Строка (Выбор из списка == Да; Обяз.) " + randomString(10))
            .setFieldID("STRING" + randomIdentifier(5))
            .setObligatory(ObligatoryField.YES) // Обязательное поле
            .setIsUniqueField(SettingsForTaskTypeListFields.YES) // Уникальное
            .setFieldType(new TypeListFieldsString()
                    .setIsListChoice(SettingsForTaskTypeListFields.NO) // Выбор из списка
                    .setValuesList(randomString(10) + "\n" + randomString(10) + "\n" + randomString(10))); // Список значений

    // Инициализируем объект - Справочник
    TaskTypeListEditObject registerCardDirectories = new Directories("wD_Directories_for_TasksTypes " + randomString(10))

            // Вкладка - Настройки
            .setShareRecords(true) // Общедоступность записей
            .setAccessToRecords(true) // Настройка доступа к записям
            .setMappingDevice(true) // Способ отображения - Линейный ли? true - да; false - иерархический
            .setSearchSettings(true) // true - поиск записей через SOLR; false - поиск записей через БД
            .setTaskTypeListObjectFields(new FieldObject[]{fieldStringIsNotListChoiceDirectories});

    //--------------------------------------------------------------------------------------------------Инициализируем объекты - Записи Справочника
    FieldObject recordOneDirectory = new DirectoriesField()

            // Осуществляем заполнение (наполнение) полей справочника через массив
            .setDirectoryItems(randomString(11))
            .setFieldName(fieldStringIsNotListChoiceDirectories.getFieldName())
            .setFieldType(new TypeListFieldsString()
                    .setIsListChoice(NO)); // Выбор из списка;

    FieldObject recordTwoDirectory = new DirectoriesField()

            // Осуществляем заполнение (наполнение) полей справочника через массив
            .setDirectoryItems(randomString(11))
            .setFieldName(fieldStringIsNotListChoiceDirectories.getFieldName())
            .setFieldType(new TypeListFieldsString()
                    .setIsListChoice(NO)); // Выбор из списка;

    //--------------------------------------------------------------------------------------------------Инициализируем объект - Справочник
    Directories directory = new Directories(registerCardDirectories.getObjectTypeName())

            // Осуществляем заполнение (наполнение) полей справочника через массив
            .setDirectoriesFields(new DirectoriesField[] {
                    (DirectoriesField) recordOneDirectory,
                    (DirectoriesField) recordTwoDirectory
            });

    //----------------------------------------------------------------------------------------------------Инициализация поля объекта и объект - Типы задач

    // 1. СТРОКА (Выбор из списка == Нет; )
    private FieldObject fieldStringIsNotListChoice = new TypeListFieldsString()
            .setFieldName("Строка (Выбор из списка == Нет; Обяз. при завершении) " + randomString(5))
            .setFieldID("STRNOTLIST" + randomIdentifier(5))
            .setFieldType(new TypeListFieldsString()
                    .setIsListChoice(NO) // Выбор из списка
                    .setValuesList(randomString(10) + "\n" + randomString(10) + "\n" + randomString(10))); // Список значений
            //.setObligatory(ObligatoryField.OBLIGATORY_ON_CLOSE); // Обязательное при завершении

    // 2. ТЕКСТ
    private FieldObject fieldTextTasksTypes = new TypeListFieldsText()
            .setFieldName("Текст " + "|транс. 2| " + randomString(10))
            .setFieldID("TEXT" + randomIdentifier(5))
            .setFieldType(new TypeListFieldsText()); // Скрывать при поиске

    // 3. ЦЕЛОЕ
    private FieldObject fieldIntTasksTypes = new TypeListFieldsInteger()
            .setFieldName("Целое " + "|транс. 3| " + randomString(10))
            .setFieldID("INTEGER" + randomIdentifier(5))
            .setFieldType(new TypeListFieldsInteger());

    // 4. ВЕЩЕСТВЕННОЕ
    private FieldObject fieldFloatTasksTypes = new TypeListFieldsDouble()
            .setFieldName("Вещественное " + randomString(10))
            .setFieldID("FLOAT" + randomIdentifier(5))
            .setFieldType(new TypeListFieldsDouble());

    // 5. ДАТА
    private FieldObject fieldDateTasksTypes = new TypeListFieldsDate()
            .setFieldName("Дата " + "|транс. 4| " + randomString(10))
            .setFieldID("DATE" + randomIdentifier(5))
            .setFieldType(new TypeListFieldsDate());

    // 6. ССЫЛКА НА СПР-К
    FieldObject fieldDirectory = new TypeListFieldsDirectory()
            .setFieldName("Ссылка на справочник " + randomString(10))
            .setFieldID("DIRECTORY" + randomIdentifier(5))
            .setFieldType(new TypeListFieldsDirectory()
                    .setDirectoryName(registerCardDirectories.getObjectTypeName()) // Выбираем проинициализированный объект - Справочник
                    .setFieldDirectory(fieldStringIsNotListChoiceDirectories));  // Выбираем проинициализированный объект - поле Справочника

    // 7. МНОЖЕСТВЕННАЯ ССЫЛКА НА СПР-К
    FieldObject fieldMultiDirectory = new TypeListFieldsMultiDirectory()
            .setFieldName("Множественная ссылка на справочник " + randomString(5))
            .setFieldID("DIRMULTI" + randomIdentifier(5))
            .setFieldType(new TypeListFieldsMultiDirectory()
                    .setDirectoryName(registerCardDirectories.getObjectTypeName()) // Выбираем проинициализированный объект - Справочник
                    .setFieldDirectory(fieldStringIsNotListChoiceDirectories));  // Выбираем проинициализированный объект - поле Справочника


    // 8. ЛОГИЧЕСКИЙ
    FieldObject fieldBoolean = new TypeListFieldsBoolean()
            .setFieldName("Логический " + randomString(5))
            .setFieldID("BOOLEAN" + randomIdentifier(5))
            .setFieldType(new TypeListFieldsBoolean());

    // 9. НУМЕРАТОР
    FieldObject fieldNumerator = new TypeListFieldsNumerator()
            .setFieldName("Нумератор " + randomString(5))
            .setFieldID("NUMERATOR" + randomIdentifier(5))
            .setFieldType(new TypeListFieldsNumerator()
                    .setNumeratorTemplate("{counter}-{" + fieldStringIsNotListChoice.getFieldID() + "}-{DD}.{YYYY} "
                            + randomString(15)) // Шаблон нумератора
                    .setComputeMode(ComputeModeNumerator.WHEN_CREATING_TASK)); // Режим вычисления - При создании задачи

    // 10. ПОДРАЗДЕЛЕНИЕ
    FieldObject fieldDepartment = new TypeListFieldsDepartment()
            .setFieldName("Подразделение " + randomString(5))
            .setFieldID("DEPARTMENT" + randomIdentifier(5))
            .setFieldType(new TypeListFieldsDepartment());

    // 11. СОТРУДНИК
    FieldObject fieldEmployee = new TypeListFieldsEmployee()
            .setFieldName("Сотрудник " + randomString(5))
            .setFieldID("EMPLOYEE" + randomIdentifier(5))
            .setFieldType(new TypeListFieldsEmployee());


    // Инициализация объекта - Типы задач с настройками
    private TaskTypeListEditObject newTaskType = new TasksTypes("wD_Тип задачи_for_DRC (verify трансл. значений полей) "
            + randomString(5))

            // Направление смещения даты при попадании на нерабочее время
            .setTaskTypeShiftDirection(ShiftDirection.DATE_MOVES_BACK) // Дата сдвигается назад

            // Корректировка даты
            .setTaskTypeCorrectionMethod(CorrectionMethod.SET_TIME)

            .setIsTaskTypeChangeDisabled(true) // Запретить изменение типа для созданной задачи
            .setOnlyTheSameTypeIWG(true) // Создавать подзадачи ИРГ только родительского типа
            .setIsCloseTaskWithNotReadyCheckpointsDisabled(true) // Запретить закрытие задач с неготовыми контрольными точками
            .setOpenFilesForEdit(OpenFilesForEdit.YES) // Открывать файлы для редактирования
            // Прикреплять файлы:
            .setIsAttachFilesToActionLine(false) // Лента действий (true - есть сигнал, значит производим клик - снимаем настройку; false - оставляем без изменения)
            .setIsAttachFilesToDescription(false) // Описание

            // Пользовательские поля любого типа
            .setTaskTypeListObjectFields(new FieldObject[]
                    {fieldStringIsNotListChoice, fieldTextTasksTypes, fieldIntTasksTypes,
                            fieldFloatTasksTypes, fieldDateTasksTypes, fieldDirectory, fieldMultiDirectory, fieldBoolean, fieldNumerator, fieldDepartment, fieldEmployee});



            // Инициализация объекта - Названия Файлов задачи
            private String[] filename = new String[] {
                    FilesForAttachment.FILE_1.getNameFile(),
                    FilesForAttachment.FILE_2.getNameFile(),
                    FilesForAttachment.FILE_3.getNameFile(),
            };

            // Инициализация объекта - Действия
            private Action[] actions = new Action[]{
                    getRandomAction(),
                    getRandomAction()
            };



    /**
     * Метод создания полностью случайного объект - "Папка"
     *
     * @return folder c атрибутами полей объекта - Папка
     */
    private static Folder getRandomFolder() {
        return new Folder()
                .setNameFolder("wD_Box " + randomString(10)) // Зн-ие НЕ изменять - используется в проверке - checkDisplayCreateAFolderInTheGrid()
                .setUseFilter(randomBoolean())
                .setChooseRelativeValue(randomBoolean())
                .setSharedFolder(randomBoolean()) // Общая папка
                .setAddSharedFolderForAll(randomBoolean()) // Добавить всем
                .setAddSharedFolderForNewUsers(randomBoolean());
    }

    /**
     * Метод создания полностью случайного массива объектов - "Папка"
     *
     * @return folders с атрибутами полей объекта - Папка
     */
    private static Folder[] getRandomArrayFolders() {
        return new Folder[]{
                new Folder()
                        .setNameFolder("wD_Box " + randomString(10)) // Зн-ие НЕ изменять - используется в проверке - checkDisplayCreateAFolderInTheGrid()
                        .setUseFilter(randomBoolean())
                        .setChooseRelativeValue(randomBoolean())
                        .setSharedFolder(randomBoolean())
                        .setAddSharedFolderForAll(randomBoolean())
                        .setAddSharedFolderForNewUsers(randomBoolean()),

        };
    }


    /**
     * Метод создания полностью случайного объекта - "ШАБЛОН ЗАДАЧ"
     */
    private TemplateOfTask[] getRandomTemplateOfTask() {
        return new TemplateOfTask[]{
                new TemplateOfTask()
                        .setName("TemplateOfTask" + " " + randomString(11)),
        };
    }


    /**
     * Метод создания полностью случайного объекта - "Действие"
     */
    private static Action getRandomAction() {
        return new Action()
                .setActionText(randomString(10) )
                .setAuthorAction(EMPLOYEE_ADMIN)
                .setTimeOfAddAction(nowHourTime());
    }

    /**
     * Метод создания полностью случайного объекта - "Проект"
     */
    private static Project getRandomProject() {
        return new Project()
                .setDescription(randomString(80))
                .setNameProject(randomString(80))
                .setСlient(randomString(80))
                .setEndDate(randomDateTime());
    }

    /**
     * Метод создания полностью случайного объекта - "Задача" for Mobile
     */
    private Task getRandomTaskMobile() {
        return new Task()
                .setTaskName(randomString(15) + " " + randomString(30))
                .setDescription(randomString(100))  // для Описания АРМа пока в одну строку
                .setDateEnd(tomorrowDateWithoutTime())
                .setIsImportant(true) // Важная задача
                .setIsSecret(true) // Секретная задача
                .setIsForReview(true)

                //Добавляем текстовые действия задачи
                .setActions(new Action[] {
                        actions[0]
                                .setActionText("Действие №1" + " " + randomString(10) ),
                        actions[1]
                                .setActionText("Действие №2" + " " + randomString(10) )
                });

    }


    /**
     * Параметризация - Инициализируем модель - Новый тип задачи с набором пользовательских полей, Новый Департамент и Пользователи, Справочник, Шаблоны задач
     */
    @DataProvider
    public Object[][] objectDataTaskNewType() {

        return new Object[][]{

                {
                        department,
                        authors,
                        executiveManagers,
                        controller,
                        worker,
                        registerCardDirectories,
                        directory,
                        newTaskType,
                        folder,
                        templateOfTasks
                }
        };
    }

    /**
     * Параметризация - Инициализируем модель - "Задача" тип Обычный (Используется для проверки создания обычной задачи и её редактирования новыми атрибутами)
     */
    @DataProvider
    public Object[][] objectDataEditTask() {

       Task task =  getRandomTaskMobile()
                .setTemplateOfTask(templateOfTasks[0])
                .setAuthorDefault(EMPLOYEE_ADMIN)
                .setAuthors(new Employee[]{authors[0]})
                .setControllers(new Employee[]{controller[0]})
                .setExecutiveManagers(new Employee[]{executiveManagers[0]})
                .setWorkers(new Employee[]{worker[0]})
                .setTaskType(new TasksTypes("Обычный"))
                .setIsWithReport(true) // C докладом
                .setFileName(new String[ ]{filename[2]}); // Файлы - взаимодействуем только с названиями файлов

        Task editTask =  getRandomTaskMobile()
                .setAuthorDefault(EMPLOYEE_ADMIN)
                .setAuthors(new Employee[]{authors[1]})
                .setControllers(new Employee[]{controller[1]})
                .setExecutiveManagers(new Employee[]{executiveManagers[1]})
                .setWorkers(new Employee[]{worker[1]})
                .setTaskType(new TasksTypes("Обычный"))
                .setIsWithReport(false) // C докладом
                .setFileName(new String[ ]{filename[0], filename[1]}); // Файлы - взаимодействуем только с названиями файлов

        return new Object[][]{
                {
                    task,
                    editTask,
                    folder
                }

        };
    }


    /**
     * Параметризация - Инициализируем модель - "Задача" с набором пользовательских полей (новый тип задачи)  (Используется для проверки создания задачи нового типа и её редактирования новыми атрибутами)
     */
    @DataProvider
    public Object[][] objectDataEditTaskNewType() {

        Task task =  getRandomTaskMobile()
                .setAuthorDefault(EMPLOYEE_ADMIN)
                .setAuthors(new Employee[]{authors[0]})
                .setControllers(new Employee[]{controller[0]})
                .setExecutiveManagers(new Employee[]{executiveManagers[0]})
                .setWorkers(new Employee[]{worker[0]})
                .setTaskType(new TasksTypes(newTaskType.getObjectTypeName()))
                .setIsWithReport(true) // C докладом
                .setFileName(new String[ ]{filename[2]}) // Файлы - взаимодействуем только с названиями файлов
                //Пользовательские поля
                .setTaskFields(new FieldObject[]{
                         fieldStringIsNotListChoice.setValueField(randomString(10)),
                         fieldTextTasksTypes.setValueField(randomString(10)),
                         fieldIntTasksTypes.setValueField(randomInt(5)),
                         fieldFloatTasksTypes.setValueField(randomInt (5)),
                         fieldDateTasksTypes.setValueField(randomDate()),
                         fieldBoolean.setValueBooleanField(true),
                         fieldNumerator.setValueField(randomInt(5)),
                         fieldDepartment.setValueField(department.getDepartmentName()),
                         fieldEmployee.setValueEmployeeField(new Employee[]{controller[0]}),
                         fieldDirectory.setValueDirectoriesField(new DirectoriesField[] {
                                 (DirectoriesField)  recordTwoDirectory}),
                        fieldMultiDirectory.setValueDirectoriesField(new DirectoriesField[] {
                                (DirectoriesField)  recordOneDirectory,
                                (DirectoriesField)  recordTwoDirectory})
                })
                //Набор названий полей, которые содержит тип задачи
                .setTaskFieldsName(newTaskType.getObjectTypeFields());


        Task editTask =  getRandomTaskMobile()
                .setAuthorDefault(EMPLOYEE_ADMIN)
                .setAuthors(new Employee[]{authors[1]})
                .setControllers(new Employee[]{controller[1]})
                .setExecutiveManagers(new Employee[]{executiveManagers[1]})
                .setWorkers(new Employee[]{worker[1]})
                .setTaskType(new TasksTypes(newTaskType.getObjectTypeName()))
                .setIsWithReport(false) // C докладом
                .setFileName(new String[ ]{filename[0], filename[1]}) // Файлы - взаимодействуем только с названиями файлов

                //Пользовательские поля
                .setTaskFields(new FieldObject[]{
                        fieldStringIsNotListChoice.setValueField(randomString(10)),
                        fieldTextTasksTypes.setValueField(randomString(10)),
                        fieldIntTasksTypes.setValueField(randomInt(5)),
                        fieldFloatTasksTypes.setValueField(randomInt (5)),
                        fieldDateTasksTypes.setValueField(randomDate()),
                        fieldBoolean.setValueBooleanField(false),
                        fieldNumerator.setValueField(randomInt(5)),
                        fieldDepartment.setValueField(department.getDepartmentName()),
                        fieldEmployee.setValueEmployeeField(new Employee[]{controller[1]}),
                        fieldDirectory.setValueDirectoriesField(new DirectoriesField[] {
                                (DirectoriesField)  recordTwoDirectory}),
                        fieldMultiDirectory.setValueDirectoriesField(new DirectoriesField[] {
                                (DirectoriesField)  recordOneDirectory,
                                (DirectoriesField)  recordTwoDirectory})
                });

        return new Object[][]{
                {
                        task,
                        editTask,
                        folder
                }

        };
    }


}