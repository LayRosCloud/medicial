package by.betrayal.visitingservice.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        this("Object has bad fields");
    }

    public BadRequestException(String message) {
        super(message);
    }
}
