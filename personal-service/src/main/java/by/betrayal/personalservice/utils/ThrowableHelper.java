package by.betrayal.personalservice.utils;

import by.betrayal.personalservice.exception.BadRequestException;
import by.betrayal.personalservice.exception.NotFoundException;

public class ThrowableHelper {

    public static NotFoundException throwNotFoundException() {
        return new NotFoundException();
    }

    public static NotFoundException throwNotFoundException(String message, Object... args) {
        return new NotFoundException(String.format(message, args));
    }

    public static NotFoundException throwNotFoundException(Number id) {
        return throwNotFoundException("Object with id = %d is not found", id);
    }

    public static BadRequestException throwBadRequestException() {
        return new BadRequestException();
    }

    public static BadRequestException throwBadRequestException(String message, Object... args) {
        return new BadRequestException(String.format(message, args));
    }
}
