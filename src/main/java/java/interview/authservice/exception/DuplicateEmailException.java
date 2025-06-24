package java.interview.authservice.exception;

public class DuplicateEmailException extends RuntimeException {
    ExceptionType exceptionType;

    public DuplicateEmailException() {

    }

    public DuplicateEmailException(ExceptionType exceptionType) {
        super(exceptionType.getExceptionDescription());
        this.exceptionType = exceptionType;
    }
}
