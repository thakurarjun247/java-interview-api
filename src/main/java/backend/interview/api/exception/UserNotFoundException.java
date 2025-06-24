package backend.interview.api.exception;

public class UserNotFoundException extends RuntimeException {
    ExceptionType exceptionType;

    public UserNotFoundException() {

    }

    public UserNotFoundException(ExceptionType exceptionType) {
        super(exceptionType.getExceptionDescription());
        this.exceptionType = exceptionType;
    }
}
