package ru.motiw.web.model.DocflowAdministration.RouteSchemeEditor;


import ru.motiw.web.model.Administration.Users.Employee;

/**
 * Модель объекта - Маршрутная схема
 */
public class RouteSchemeEditor {

    private String routeSchemeEditor = "";
    private String ReviewDuration = "";
    private Employee[] userRoute;

    /**
     * Участники рассмотрения документа
     */
    public Employee[] getUserRoute() {
        return userRoute;
    }

    public RouteSchemeEditor setUserRoute(Employee[] userRoute) {
        this.userRoute = userRoute;
        return this;
    }

    /**
     * Длительность рассмотрения
     */
    public String getReviewDuration() {
        return ReviewDuration;
    }

    public RouteSchemeEditor setReviewDuration(String reviewDuration) {
        ReviewDuration = reviewDuration;
        return this;
    }

    /**
     * Название маршрутной схемы
     */
    public String getNameRouteScheme() {
        return routeSchemeEditor;
    }

    public RouteSchemeEditor setRouteScheme(
            String routeSchemeEditor) {
        this.routeSchemeEditor = routeSchemeEditor;
        return this;
    }


}
