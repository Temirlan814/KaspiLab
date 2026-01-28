package project.HomeWork11Delivery.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {

  @ExceptionHandler(DuplicateDeliveryException.class)
  public ResponseEntity<Map<String, Object>> handleDup(DuplicateDeliveryException ex) {
    return ResponseEntity.status(409).body(Map.of(
        "error", "DUPLICATE",
        "message", ex.getMessage()
    ));
  }
}
