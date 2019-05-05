package ru.motiw.data.dataproviders;

import org.testng.annotations.DataProvider;
import ru.motiw.data.BaseTest;
import ru.motiw.mobile.model.FilesForAttachment;
import ru.motiw.web.model.Administration.Users.Department;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards.DocRegisterCards;
import ru.motiw.web.model.DocflowAdministration.RouteSchemeEditor.RouteSchemeEditor;
import ru.motiw.web.model.Document.Document;
import ru.motiw.web.model.Document.ExecutionOfDocument;
import ru.motiw.web.model.Document.Resolution;
import ru.motiw.web.model.Tasks.Folder;
import ru.motiw.web.model.Tasks.Task;

import static ru.motiw.data.dataproviders.Administration.getRandomDepartment;
import static ru.motiw.data.dataproviders.Administration.getRandomEmployer;
import static ru.motiw.data.dataproviders.Tasks.getRandomProject;
import static ru.motiw.mobile.model.Document.TypeOperationsOfDocument.CREATE_RESOLUTION;
import static ru.motiw.mobile.model.Document.TypeOperationsOfDocument.MOVE_TO_EXECUTION;


/**
 * Данные раздела - Администрирование ДО - для тестов документов в АРМ
 */
public abstract class DocflowAdministrationMobile extends BaseTest {

    // Инициализация объекта - Названия Файлов задачи
    String[] file = new String[]{
            FilesForAttachment.FILE_1.getNameFile(),
            FilesForAttachment.FILE_2.getNameFile(),
            FilesForAttachment.FILE_3.getNameFile(),
    };

    //---------------------------------------------------------------------------------------------------------- Инициализируем объект - Подразделение и Пользователь
    Department[] department = new Department[]{getRandomDepartment()};

    Employee[] employee = new Employee[]{getRandomEmployer()};

    // Инициализация РКД и её настроек
    DocRegisterCards registerCards = new DocRegisterCards("wD_Тестовая карточка " + randomString(20))

            // Статус документа
            .setDocumentStatesOnReview("На рассмотрении " + randomString(20)) // - На рассмотрении
            .setDocumentStatesReviewed("Рассмотрен " + randomString(20)) // - Рассмотрен
            .setDocumentStatesOnApproval("На подписании " + randomString(20)) // - На подписании
            .setDocumentStatesOnExecution("На исполнении " + randomString(20)) // - На исполнении
            .setDocumentStatesInArchive("В архиве " + randomString(20)) // - В архиве

            .setCheckBoxUseAllPossibleRoutes(true); // Использовать все возможные маршруты


    //----------------------------------------------------------------------------------------------------------- Инициализация Документа
    Document document = new Document()

            .setDocumentType(registerCards) // Тип документа
            .setAuthorOfDocument(ADMIN)
            .setDateRegistration(randomDateTime()) // Дата регистрации
            .setProject(getRandomProject()) // Инициализируем проект документа
            .setValueFiles(new String[]{file[0], file[1]})
            .setRouteSchemeForDocument(new RouteSchemeEditor()
                    .setRouteScheme("Согласование входящей корреспонденции - Постановка задачи")
                    .setReviewDuration(randomInt(999))
                    .setUserRoute(new Employee[]{employee[0]}) // Добавляем в маршрут созданного пользователя
            )
            .setExecutionOfDocument(new ExecutionOfDocument[]
                    {
                            new ExecutionOfDocument()
                                    .setExecutionOperation(1, ADMIN, CREATE_RESOLUTION),

                            new ExecutionOfDocument()
                                    .setExecutionOperation(2, employee[0], MOVE_TO_EXECUTION)

                    }
            )
            .setResolutionOfDocument(new Task[]{
                    new Resolution()
                            .setTextOfResolution(randomString(21))
                            .setAuthorDefault(ADMIN)
                            .setExecutiveManagers(employee)

            }); // Резолюция


    // ----------------------------------------------------------------------------------------------------------- Инициализация Папки
    Folder[] folder = getRandomArrayFolders();

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
        };
    }


    /**
     * Параметризация - Инициализируем модель - Регистрационная карточка документа (со всеми надстройками)
     *
     * @return массив параметров объектов системы
     */
    @DataProvider
    public Object[][] objectDataDocument() {


        return new Object[][]{

                {
                        //переменная объекта - ПОДРАЗДЕЛЕНИЕ
                        department,
                        //переменная объекта - ПОЛЬЗОВАТЕЛЬ
                        employee,
                        //переменная объекта - РКД
                        registerCards,
                        //переменная объекта - ДОКУМЕНТ
                        document,
                        //переменная объекта - Папка
                        folder

                }
        };
    }

}
