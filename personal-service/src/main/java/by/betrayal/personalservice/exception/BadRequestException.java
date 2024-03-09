package by.betrayal.personalservice.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super("Request has bad field");
    }

    public BadRequestException(String message) {
        super(message);
    }
}
