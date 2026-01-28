package project.HomeWork11Delivery.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateDeliveryException extends RuntimeException {
  public DuplicateDeliveryException(String msg) { super(msg); }
}
