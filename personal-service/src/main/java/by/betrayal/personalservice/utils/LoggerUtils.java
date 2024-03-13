package by.betrayal.personalservice.utils;


public class LoggerUtils {
    public static String createLogIdStart(String controllerName, String methodName, Number id) {
        return createLogStart(controllerName, methodName, String.format("with id %s", id));
    }

    public static String createLogStart(String controllerName, String methodName, String message) {
        return String.format("%s %s %s %s", start(), controller(controllerName), method(methodName), message(message));
    }

    public static String createLogEnd(String controllerName, String methodName, String message) {
        return String.format("%s %s %s %s", end(), controller(controllerName), method(methodName), message(message));
    }

    private static String start() {
        return "\uD83C\uDFCE\uFE0F Start";
    }

    private static String end() {
        return "\uD83C\uDFC1 End";
    }

    private static String controller(String controllerName) {
        return String.format("request in controller: `%s``", controllerName);
    }

    private static String method(String methodName) {
        return String.format("\uD83D\uDE80 method: `%s``", methodName);
    }

    private static String message(String message) {
        return String.format("\uD83D\uDCAC message: `%s`", message);
    }

}
