package exceptions;

public class SystemInitializationError extends RuntimeException{

    public SystemInitializationError() {
        super();
    }

    public SystemInitializationError(String message) {
        super(message);
    }

    public SystemInitializationError(String message, Throwable cause) {
        super(message, cause);
    }
}
