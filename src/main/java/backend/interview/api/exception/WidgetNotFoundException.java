package backend.interview.api.exception;

public class WidgetNotFoundException extends RuntimeException {
    public WidgetNotFoundException(String name) {
        super("Widget not found: " + name);
    }
}
