package ru.motiw.data.dataproviders;

import org.testng.annotations.DataProvider;
import ru.motiw.data.BaseTest;
import ru.motiw.mobile.model.AuthorOfAnnotation;
import ru.motiw.mobile.model.FilesForAttachment;
import ru.motiw.web.model.Administration.TasksTypes.TasksTypes;
import ru.motiw.web.model.Administration.Users.Department;
import ru.motiw.web.model.Administration.Users.Employee;
import ru.motiw.web.model.Administration.Users.Sex;
import ru.motiw.web.model.DocflowAdministration.DocumentRegistrationCards.DocRegisterCards;
import ru.motiw.web.model.DocflowAdministration.RouteSchemeEditor.RouteSchemeEditor;
import ru.motiw.web.model.Document.Document;
import ru.motiw.web.model.Document.Resolution;
import ru.motiw.web.model.Tasks.Folder;
import ru.motiw.web.model.Tasks.Task;

import static ru.motiw.data.dataproviders.Administration.getRandomDepartment;
import static ru.motiw.data.dataproviders.Tasks.getRandomProject;
import static ru.motiw.mobile.model.AuthorOfAnnotation.NumbersOfAnnotation.FirstAnnotation;
import static ru.motiw.mobile.model.AuthorOfAnnotation.NumbersOfAnnotation.SecondAnnotation;


/**
 * Данные для тестов граф.комментраиев в задачах и документах в АРМ
 */
public abstract class AnnotationOnFilesInDocumentAndTaskMobile extends BaseTest {

    // Инициализация объекта - Названия Файлов задачи
    private String[] file = new String[]{
            FilesForAttachment.FILE_1.getNameFile(),
            FilesForAttachment.FILE_2.getNameFile(),
            FilesForAttachment.FILE_3.getNameFile(),
            FilesForAttachment.FILE_4.getNameFile(),
    };

    //---------------------------------------------------------------------------------------------------------- Инициализируем объект - Подразделение и Пользователь
    private Department[] department = new Department[]{getRandomDepartment()};
    private Employee[] employee = new Employee[]{
            getRandomEmployer()
                    .setLastName(randomString(5)).setName("FirstUser-" + randomString(5)).setPatronymic(randomString(10)),
            getRandomEmployer()
                    .setLastName(randomString(5)).setName("SecondUser-" + randomString(5)).setPatronymic(randomString(10))
    };


    /**
     * Метод создания объекта - "Пользователь"
     */
    private static Employee getRandomEmployer() {
        String pass = randomString(10);
        String newPass = randomString(10);
        return new Employee()
                .setLastName(randomString(5)).setName(randomString(5)).setPatronymic(randomString(10)) // ФИО
                .setSex(randomEnum(Sex.class))
                .setLoginName(randomString(10))
                .setPassword(pass).setСonfirmationPassword(pass)
                .setNewPassword(newPass).setNewСonfirmationPassword(newPass)
                .setNeedsPasswordChange(false);
    }


    /**
     * Метод создания полностью случайного объекта - "Задача" for Mobile
     */
    private Task getRandomTaskMobile() {
        return new Task()
                .setTaskName(randomString(15) + " " + randomString(30));
    }


    //----------------------------------------------------------------------------------------------------------- Инициализация Документов
    private Document documentWithPdf = getRandomDocument(new Employee[]{employee[0]})
                    .setValueFiles(new String[]{file[0]})
                    .setResolutionOfDocument(new Resolution[]{
                    (Resolution) new Resolution()
                            .setTextOfResolution(randomString(20))
                            .setAuthorDefault(EMPLOYEE_ADMIN)
                            .setExecutiveManagers(new Employee[]{employee[0], employee[1]})
                    });

    private Document documentWithImg = getRandomDocument(new Employee[]{employee[0]})
            .setValueFiles(new String[]{file[3]})
            .setResolutionOfDocument(new Resolution[]{
                    (Resolution) new Resolution()
                            .setTextOfResolution(randomString(20))
                            .setAuthorDefault(EMPLOYEE_ADMIN)
                            .setExecutiveManagers(new Employee[]{employee[0], employee[1]})
            });

    private Document documentWithPdfAndImg = getRandomDocument(new Employee[]{employee[0]})
            .setValueFiles(new String[]{file[0], file[3]})
            .setResolutionOfDocument(new Resolution[]{
                    (Resolution) new Resolution()
                            .setTextOfResolution(randomString(20))
                            .setAuthorDefault(EMPLOYEE_ADMIN)
                            .setExecutiveManagers(new Employee[]{employee[0], employee[1]})
            });

    // -----------------------------------------------------------------------------------------------------------  Инициализация массива РКД из ранее созданного массива документов
    private Document[] docArray = new Document[]{documentWithPdf, documentWithImg, documentWithPdfAndImg};
    private DocRegisterCards[] rcdArray = getRegisterCardsArray(docArray);

    private DocRegisterCards[] getRegisterCardsArray(Document[] documents) {
        DocRegisterCards[] arrayTypesOfDocument = new DocRegisterCards[documents.length];
        for (int i = 0; i < arrayTypesOfDocument.length; i++) {
            arrayTypesOfDocument[i] = documents[i].getDocumentType();
        }
        return arrayTypesOfDocument;
    }


    /**
     * Метод создания массива объектов - "Тип документа"
     *
     * @param numbersOfObjects - нужное число типов документов
     * @return
     */
    private DocRegisterCards[] getRandomArrayTypesOfDocument(Integer numbersOfObjects) {
        DocRegisterCards[] arrayTypesOfDocument = new DocRegisterCards[numbersOfObjects];
        for (int i = 0; i < arrayTypesOfDocument.length; i++) {
            arrayTypesOfDocument[i] = new DocRegisterCards("wD_Тестовая карточка " + randomString(20))
                    .setCheckBoxUseAllPossibleRoutes(true); // Использовать все возможные маршруты;
        }
        return arrayTypesOfDocument;
    }

    /**
     * Метод создания массива объектов - "Документ"
     *
     * @param numbersOfDoc - нужное число документов
     * @return
     */
    private Document[] getArrayDocuments(Integer numbersOfDoc) {
        Document[] arrayOfDocuments = new Document[numbersOfDoc];
        DocRegisterCards[] arrayTypesOfDocument = getRandomArrayTypesOfDocument(numbersOfDoc);
        for (int i = 0; i < arrayOfDocuments.length; i++) {
            arrayOfDocuments[i] = new Document()
                    .setDocumentType(arrayTypesOfDocument[i]) // Тип документа
                    .setAuthorOfDocument(EMPLOYEE_ADMIN)
                    .setDateRegistration(randomDateTime()) // Дата регистрации
                    .setProject(getRandomProject()) // Инициализируем проект документа
                    .setValueFiles(new String[]{file[0]})
                    .setRouteSchemeForDocument(new RouteSchemeEditor()
                            .setRouteScheme("Согласование входящей корреспонденции - Постановка задачи")
                            .setReviewDuration(randomInt(999))
                            .setUserRoute(new Employee[]{employee[0]}) // Добавляем в маршрут созданного пользователя
                    )
                    .setResolutionOfDocument(new Resolution[]{
                            (Resolution) new Resolution()
                                    .setTextOfResolution(randomString(20))
                                    .setAuthorDefault(EMPLOYEE_ADMIN)
                                    .setExecutiveManagers(new Employee[]{employee[0], employee[1]})
                    });
        }
        return arrayOfDocuments;
    }

    /**
     * Метод создания объекта - "Документ"
     *
     * @param userRoute - пользователь добавляемый в МС
     * @return
     */
    private Document getRandomDocument(Employee[] userRoute) {
        return new Document()
                .setDocumentType(getRandomArrayTypesOfDocument(1)[0]) // Тип документа
                .setAuthorOfDocument(EMPLOYEE_ADMIN)
                .setDateRegistration(randomDateTime()) // Дата регистрации
                .setProject(getRandomProject()) // Инициализируем проект документа
                .setRouteSchemeForDocument(new RouteSchemeEditor()
                        .setRouteScheme("Согласование входящей корреспонденции - Постановка задачи")
                        .setReviewDuration(randomInt(999))
                        .setUserRoute(userRoute) // Добавляем в маршрут созданного пользователя
                );
    }

    // ----------------------------------------------------------------------------------------------------------- Инициализация Задача
    Task task = getRandomTaskMobile()
            .setExecutiveManagers(employee)
            .setTaskType(new TasksTypes("Обычный"))
            .setFileName(new String[]{file[0]}); // Файлы - взаимодействуем только с названиями файлов

    // ----------------------------------------------------------------------------------------------------------- Инициализация Папки
    private Folder[] folders = getRandomArrayFolders();

    /**
     * Метод создания массива объектов - "Папка"
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
     * Авторы граф.коментария
     */
    private AuthorOfAnnotation[] authorOfAnnotations = new AuthorOfAnnotation[]{
            new AuthorOfAnnotation(employee[0]).setNumberOfAnnotation(FirstAnnotation),
            new AuthorOfAnnotation(employee[1]).setNumberOfAnnotation(SecondAnnotation)
    };

    /**
     * Параметризация - Инициализируем модель - Объекты необходимые для создания документа
     *
     * @return массив параметров объектов системы
     */
    @DataProvider
    public Object[][] objectDataForPreconditionTestAnnotationInDocument() {
        return new Object[][]{
                {
                        //переменная объекта - ПОДРАЗДЕЛЕНИЕ
                        department,
                        //переменная объекта - ПОЛЬЗОВАТЕЛЬ
                        employee,
                        //переменная объекта - МАССИВ РКД
                        rcdArray,
                        //переменная объекта - ДОКУМЕНТ C PDF-ФАЙЛОМ
                        documentWithPdf,
                        //переменная объекта - ДОКУМЕНТ C PNG-ФАЙЛОМ
                        documentWithImg,
                        //переменная объекта - ДОКУМЕНТ C PDF-ФАЙЛОМ и PNG-ФАЙЛОМ
                        documentWithPdfAndImg,
                        //переменная объекта - Папка
                        folders
                }
        };
    }

    /**
     * Параметризация - Инициализируем модель - Объекты необходимые для создания задачи
     *
     * @return массив параметров объектов системы
     */
    @DataProvider
    public Object[][] objectDataForPreconditionTestAnnotationInTask() {
        return new Object[][]{
                {
                        //переменная объекта - ПОДРАЗДЕЛЕНИЕ
                        department,
                        //переменная объекта - ПОЛЬЗОВАТЕЛЬ
                        employee,
                        //переменная объекта - ЗАДАЧА
                        task,
                        //переменная объекта - Папка
                        folders
                }
        };
    }

    /**
     * Параметризация - Инициализируем модель - Объекты необходимые для  проверки граф.коментариев на png-файле в документе
     *
     * @return массив параметров объектов системы
     */
    @DataProvider
    public Object[][] objectDataForVerifyingAnnotationOnPngFileInDocument() {
        return new Object[][]{
                {
                        //переменная объекта - ДОКУМЕНТ C PDF-ФАЙЛОМ
                        documentWithImg,
                        //переменная объекта - Папка
                        folders,
                        //переменная объекта - Автор граф.коментария
                        authorOfAnnotations
                }
        };
    }

    /**
     * Параметризация - Инициализируем модель - Объекты необходимые для  проверки граф.коментариев на pdf-файле в документе
     *
     * @return массив параметров объектов системы
     */
    @DataProvider
    public Object[][] objectDataForVerifyingAnnotationOnPdfFileInDocument() {
        return new Object[][]{
                {
                        //переменная объекта - ДОКУМЕНТ C PDF-ФАЙЛОМ
                        documentWithPdf,
                        //переменная объекта - Папка
                        folders,
                        //переменная объекта - Автор граф.коментария
                        authorOfAnnotations
                }
        };
    }

    /**
     * Параметризация - Инициализируем модель - Объекты необходимые для  проверки граф.коментариев на pdf-файле и png-файле в документе
     *
     * @return массив параметров объектов системы
     */
    @DataProvider
    public Object[][] objectDataForVerifyingAnnotationOnPdfAndPngFilesInDocument() {
        return new Object[][]{
                {
                        //переменная объекта - ДОКУМЕНТ C PDF-ФАЙЛОМ
                        documentWithPdfAndImg,
                        //переменная объекта - Папка
                        folders,
                        //переменная объекта - Автор граф.коментария
                        authorOfAnnotations
                }
        };
    }

    /**
     * Параметризация - Инициализируем модель - Объекты необходимые для  проверки граф.коментариев на png-файле в задаче
     *
     * @return массив параметров объектов системы
     */
    @DataProvider
    public Object[][] objectDataForVerifyingAnnotationOnFileInTask() {
        return new Object[][]{
                {
                        //переменная объекта - ПОЛЬЗОВАТЕЛЬ
                        employee,
                        //переменная объекта - ЗАДАЧА
                        task,
                        //переменная объекта - Папка
                        folders,
                        //переменная объекта - Автор граф.коментария
                        authorOfAnnotations
                }
        };
    }

}
