package backend.interview.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.MethodArgumentNotValidException;


import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(WidgetNotFoundException.class)
    public ResponseEntity<String> handleDuplicateEmailException(WidgetNotFoundException widgetNotFoundException) {
        return new ResponseEntity<>(widgetNotFoundException.getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(DuplicateWidgetNameException.class)
    public ResponseEntity<String> handleDuplicateWidgetNameException(DuplicateWidgetNameException duplicateWidgetNameException) {
        return new ResponseEntity<>(duplicateWidgetNameException.getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
