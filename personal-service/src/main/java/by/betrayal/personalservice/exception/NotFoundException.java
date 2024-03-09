package by.betrayal.personalservice.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException() {
        super("Object is not found");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
