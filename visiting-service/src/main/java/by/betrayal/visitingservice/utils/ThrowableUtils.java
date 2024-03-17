package by.betrayal.visitingservice.utils;

import by.betrayal.visitingservice.exception.BadRequestException;
import by.betrayal.visitingservice.exception.NotFoundException;

public class ThrowableUtils {
    public static NotFoundException throwNotFoundException(Number id) {
        return new NotFoundException(String.format("Object with id %s is not found", id));
    }

    public static BadRequestException throwBadRequestException(String message) {
        return new BadRequestException(message);
    }
}
