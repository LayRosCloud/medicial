package by.betrayal.visitingservice.exception;

public class NotFoundException extends RuntimeException {
     public NotFoundException() {
         this("Object not found");
     }

    public NotFoundException(String message) {
        super(message);
    }
}
