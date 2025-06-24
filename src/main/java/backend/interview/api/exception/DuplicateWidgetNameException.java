package backend.interview.api.exception;

public class DuplicateWidgetNameException extends RuntimeException {
    public DuplicateWidgetNameException(String name) {
        super("Widget name must be unique: " + name);
    }
}
