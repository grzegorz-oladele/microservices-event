package pl.grzegorz.event.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EventExceptionHandler {

    ResponseEntity<ErrorEventInfo> handlerEventException(EventException e) {
        HttpStatus httpStatus = HttpStatus.MULTI_STATUS;
        if (EventError.EVENT_EMPTY_LIST.equals(e.getEventError())) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity.status(httpStatus).body(new ErrorEventInfo(e.getEventError().getMessage()));
    }
}
