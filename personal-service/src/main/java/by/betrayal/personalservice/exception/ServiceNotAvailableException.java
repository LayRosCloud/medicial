package by.betrayal.personalservice.exception;

public class ServiceNotAvailableException extends RuntimeException {

    public ServiceNotAvailableException() {
        super("Service is not available! Try later execute request");
    }
}
