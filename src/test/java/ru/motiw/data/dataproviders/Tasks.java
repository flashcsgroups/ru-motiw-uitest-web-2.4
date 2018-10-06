package ru.motiw.data.dataproviders;

import org.testng.annotations.DataProvider;
import ru.motiw.data.BaseTest;
import ru.motiw.web.model.Administration.TasksTypes.TasksTypes;
import ru.motiw.web.model.Administration.Users.Department;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Period;
import ru.motiw.web.model.Tasks.*;

import static ru.motiw.data.dataproviders.Administration.getRandomDepartment;
import static ru.motiw.data.dataproviders.Administration.getRandomEmployer;

/**
 * Данные раздела - Задача
 */
public abstract class Tasks extends BaseTest {

    /**
     * Метод создания полностью случайного объекта - "Задача" for PDA
     */
    public Task getRandomObjectTask() {
        return new Task()
                //.setActions(new Action[] {getRandomAction()})
                .setTaskName(randomString(15) + " " + randomString(30))
                .setDescription(randomString(100))  // для Описания АРМа пока в одну строку
                .setDateEnd(tomorrowDateWithoutTime())
                .setIsImportant(true) // Важная задача
                .setAuthors(new Employee[]{EMPLOYEE_ADMIN})
                .setControllers(null) //null т.к при редактировании удаляем пользователей добавленных при создании, а потом проверяем поле на null
                .setExecutiveManagers(null) //null т.к при редактировании удаляем пользователей добавленных при создании, а потом проверяем поле на null
                .setWorkers(null) //null т.к при редактировании удаляем пользователей добавленных при создании, а потом проверяем поле на null
                .setTaskType(new TasksTypes("Обычный"))
                .setIsSecret(true) // Секретная задача
                .setIsWithReport(false) // C докладом
                .setIsForReview(true); // Только для озакомления
    }



    /**
     * Метод создания полностью случайного массива объектов - "Действие"
     */
    public static Action[] getRandomArrayAction() {
        return new Action[]{
                new Action(),
                getRandomAction(),
                getRandomAction(),
        };
    }


    /**
     * Метод создания полностью случайного объекта - "Действие"
     */
    public static Action getRandomAction() {
        return new Action()
                        .setActionText(randomString(10) + "" + nowDate());
    }




    /**
     * Параметризация - Инициализируем модель - Задача for PDA
     */
    @DataProvider
    public Object[][] objectDataTaskPDA() {
        return new Object[][]{

                {new Task().setTaskName(randomString(15) + " " + randomString(30))
                        .setDescription(randomString(100)) // для Описания АРМа пока в одну строку
                        /*.setDescription(randomString(100) + "\n " + randomString(100) + "\n " + randomString(100))*/
                        //.setDateBegin(yesterdayDate()) //можно убрать, т.к проверка на автозаполнение даты начало м.б важнее
                        .setDateEnd(tomorrowDateWithoutTime())
                        .setAuthors(new Employee[]{EMPLOYEE_ADMIN})
                        .setControllers(new Employee[]{EMPLOYEE_ADMIN})
                        .setExecutiveManagers(new Employee[]{EMPLOYEE_ADMIN})
                        .setWorkers(new Employee[]{EMPLOYEE_ADMIN})
                        .setTaskType(new TasksTypes("Обычный"))
                        .setIsSecret(true) // Секретная задача
                        .setIsWithReport(true) // C докладом
                        .setIsForReview(true) // Только для озакомления
                        .setIsImportant(true)}, // Важная задача
        };
    }

    /**
     * Метод создания полностью случайного объекта - "Проект"
     */
    public static Project getRandomProject() {
        return new Project()
                .setDescription(randomString(80))
                .setNameProject(randomString(80))
                .setСlient(randomString(80))
                .setEndDate(randomDateTime());
    }

    /**
     * Метод создания полностью случайного объекта - "Задача" for Web
     */
    public Task getRandomTask() {
        return new Task()
                .setTaskName(randomString(80))
                .setTaskType(new TasksTypes("Обычный"))
                .setDescription(randomString(500))
                .setDateEnd(tomorrowDate())
                // Только для озакомления
                .setIsForReview(randomBoolean())
                // Важная задача
                .setIsImportant(randomBoolean())
                // Секретная задача
                .setIsSecret(randomBoolean())
                //
                .setIsWithReport(randomBoolean())
                .setProject(getRandomProject());
    }

    /**
     * Метод создания полностью случайного объекта - "КТ"
     */
    public Checkpoint getRandomCheckpoint() {
        return new Checkpoint()
                .setDate(randomDateTime())
                .setDescription(randomString(100))
                .setIsReady(randomBoolean())
                .setLinkedTo(randomEnum(LinkedTo.class))
                .setName(randomString(80))
                .setOffset(randomInt(10))
                .setPeriod(randomEnum(Period.class));
    }

    /**
     * Метод создания полностью случайного объекта - "ИРГ"
     */
    public IWG getRandomIWG() {
        return new IWG()
                .setIsSystemActionsInParentTask(randomBoolean())
                .setNameIWG(randomString(80))
                .setTasksTypes(new TasksTypes("Обычный"));
    }

    /**
     * Параметризация - Инициализируем модель - Задача for Web
     */
    @DataProvider
    public Object[][] objectDataTask() {

        // Инициализация объекта - Подразделение
        Department department = getRandomDepartment();

        // Инициализация объекта - Пользователи
        Employee[] author = new Employee[]{getRandomEmployer().setLastName("Автор задачи " + randomString(5))};

        Employee[] responsibleLeaders = new Employee[]{getRandomEmployer().setLastName("ОР задачи " + randomString(5))};

        Employee[] controller = new Employee[]{getRandomEmployer().setLastName("Контролер задачи " + randomString(5))};

        Employee[] worker = new Employee[]{getRandomEmployer().setLastName("Исполнитель задачи " + randomString(5))};

        // Исполнители для задачи типа ИРГ
        Employee[] IWGWorker = new Employee[]{getRandomEmployer().setLastName("Исп. ИРГ " + randomString(5))};
        // Ответственный руководители для задачи типа ИРГ
        Employee[] IWGResponsibleLeaders = new Employee[]{getRandomEmployer().setLastName("ОР ИРГ " + randomString(5))};
        // Контролеры для задачи типа ИРГ
        Employee[] IWGController = new Employee[]{getRandomEmployer().setLastName("Контролер ИРГ " + randomString(5))};

        //-------------Инициализация объекта - Контрольная точка
        Checkpoint[] checkPoint = new Checkpoint[]{getRandomCheckpoint().setIsReady(true).setLinkedTo(LinkedTo.NULL),
                getRandomCheckpoint().setIsReady(false).setLinkedTo(LinkedTo.NULL)};
        //-------------Инициализация объекта - задача типа ИРГ
        IWG[] iwg = new IWG[]{getRandomIWG().setNameIWG("ИРГ (трансляция сис. действий из ИРГ) " + randomString(5))
                .setIsSystemActionsInParentTask(true), // транслировать системные действия из ИРГ в родительскую задачу
                getRandomIWG()
                        .setNameIWG("ИРГ1 " + randomString(5))
                        .setIsSystemActionsInParentTask(false),
                getRandomIWG()
                        .setNameIWG("ИРГ3 " + randomString(5))
                        .setIsSystemActionsInParentTask(false)};
        //-----------Инициализация объекта - Задача (с атрибутами)
        Task task = getRandomTask()
                .setAuthors(new Employee[]{author[0]})
                .setControllers(new Employee[]{controller[0]})
                .setWorkers(new Employee[]{worker[0]})
                .setExecutiveManagers(new Employee[]{responsibleLeaders[0]})
                .setCheckpoints(new Checkpoint[]{checkPoint[0], checkPoint[1]})
                .setIWG(new IWG[]{
                        iwg[0]
                                .setRespPersons(new Employee[]{IWGResponsibleLeaders[0]})
                                .setWorkers(new Employee[]{IWGWorker[0]})
                                .setControllers(new Employee[]{IWGController[0]}),
                        iwg[1]
                                .setRespPersons(new Employee[]{IWGResponsibleLeaders[0]})
                                .setWorkers(new Employee[]{IWGWorker[0]})
                                .setControllers(new Employee[]{IWGController[0]}),
                        iwg[2]
                                .setRespPersons(new Employee[]{IWGResponsibleLeaders[0]})
                                .setWorkers(new Employee[]{IWGWorker[0]})});
        //-------------Проект
        return new Object[][]{

                {
                        department,
                        author,
                        responsibleLeaders,
                        controller,
                        worker,
                        IWGWorker,
                        IWGResponsibleLeaders,
                        IWGController,
                        task

                }
        };
    }


    /**
     * Метод создания полностью случайного объект - "Папка"
     *
     * @return folder c атрибутами полей объекта - Папка
     */
    public static Folder getRandomFolder() {
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
    public static Folder[] getRandomArrayFolders() {
        return new Folder[]{
                new Folder()
                        .setNameFolder("wD_Box " + randomString(10)) // Зн-ие НЕ изменять - используется в проверке - checkDisplayCreateAFolderInTheGrid()
                        .setUseFilter(randomBoolean())
                        .setChooseRelativeValue(randomBoolean())
                        .setSharedFolder(randomBoolean())
                        .setAddSharedFolderForAll(randomBoolean())
                        .setAddSharedFolderForNewUsers(randomBoolean()),

                getRandomFolder(),
                getRandomFolder(),
                getRandomFolder(),
                getRandomFolder(),


        };
    }

}






