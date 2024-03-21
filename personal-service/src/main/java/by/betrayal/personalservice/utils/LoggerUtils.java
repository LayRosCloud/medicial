package by.betrayal.personalservice.utils;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerUtils {
    public static void createLogIdStart(String controllerName, String methodName, Number id) {
        log.info(
                String.format("%s %s %s %s", start(),
                        controller(controllerName),
                        method(methodName, "id"),
                        message("with id " + id)
                )
        );
    }

    public static void createLogFindAllStart(String controllerName, String param, String message) {
        createLogStart(controllerName, "findAll", param, message);
    }

    public static void createLogFindByIdStart(String controllerName, Number id) {
        createLogIdStart(controllerName, "findById", id);
    }

    public static void createLogCreateStart(String controllerName, String message) {
        createLogStart(controllerName, "create", message);
    }

    public static void createLogUpdateStart(String controllerName, String message) {
        createLogStart(controllerName, "update", message);
    }

    public static void createLogDeleteStart(String controllerName, Number id) {
        createLogIdStart(controllerName, "delete", id);
    }

    public static void createLogFindAllEnd(String controllerName, String param, Number length) {
        createLogEnd(controllerName, "findAll", param, "with result size " + length);
    }

    public static void createLogFindByIdEnd(String controllerName, String message) {
        createLogEnd(controllerName, "findById", message);
    }

    public static void createLogCreateEnd(String controllerName, String message) {
        createLogEnd(controllerName, "create", message);
    }

    public static void createLogUpdateEnd(String controllerName, String message) {
        createLogEnd(controllerName, "update", message);
    }

    public static void createLogDeleteEnd(String controllerName, String message) {
        createLogEnd(controllerName, "delete", message);
    }


    public static void createLogStart(String controllerName, String methodName, String params, String message) {
        log.info(
                String.format("%s %s %s %s",
                        start(),
                        controller(controllerName),
                        method(methodName, params),
                        message(message)
                )
        );
    }

    public static void createLogStart(String controllerName, String methodName, String message) {
        log.info(
                String.format("%s %s %s %s",
                        start(),
                        controller(controllerName),
                        method(methodName),
                        message(message)
                )
        );
    }

    public static void createLogEnd(String controllerName, String methodName, String message) {
        log.info(
                String.format("%s %s %s %s",
                        end(),
                        controller(controllerName),
                        method(methodName),
                        message(message)
                )
        );
    }

    public static void createLogEnd(String controllerName, String methodName, String params, String message) {
        log.info(
                String.format("%s %s %s %s",
                        end(),
                        controller(controllerName),
                        method(methodName, params),
                        message(message)
                )
        );
    }

    private static String start() {
        return "\uD83C\uDFCE\uFE0F Start";
    }

    private static String end() {
        return "\uD83C\uDFC1 End";
    }

    private static String controller(String controllerName) {
        return String.format("request in controller: `%sController``", controllerName);
    }
    private static String method(String methodName) {
        return method(methodName, "");
    }
    private static String method(String methodName, String params) {
        return String.format("\uD83D\uDE80 method: `%s(%s)``", methodName, params);
    }

    private static String message(String message) {
        return String.format("\uD83D\uDCAC message: `%s`", message);
    }

}
