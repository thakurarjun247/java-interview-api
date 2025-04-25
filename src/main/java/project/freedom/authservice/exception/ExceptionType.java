package project.freedom.authservice.exception;

public enum ExceptionType {
    DUPLICATE_EMAIL("DUPLICATE_EMAIL", "Can't sign up, email already exists"),
    USER_NOT_FOUND("USER_NOT_FOUND", "User doesn't exist");
    private final String exceptionType;
    private final String exceptionDescription;

    ExceptionType(String exceptionType, String exceptionDescription) {
        this.exceptionType = exceptionType;
        this.exceptionDescription = exceptionDescription;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public String getExceptionDescription() {
        return exceptionDescription;
    }
}
