package by.betrayal.personalservice.utils;

import org.springframework.stereotype.Component;


public class LoggerHelper {
    public static String createLogIdStart(String controllerName, String methodName, Number id) {
        return createLogStart(controllerName, methodName, String.format("with id %s", id));
    }

    public static String createLogStart(String controllerName, String methodName, String message) {
        return String.format("\uD83C\uDFCE\uFE0F Start request in controller: `%s` \uD83D\uDE80 method: `%s` \uD83D\uDCAC message: `%s`", controllerName, methodName, message);
    }

    public static String createLogEnd(String controllerName, String methodName, String message) {
        return String.format("\uD83C\uDFC1 End request in controller: `%s` \uD83D\uDE80 method: `%s` \uD83D\uDCAC message: `%s`", controllerName, methodName, message);
    }

}
