package pl.grzegorz.event.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EventExceptionHandler {

    @ExceptionHandler(value = EventException.class)
    ResponseEntity<ErrorEventInfo> handlerEventException(EventException e) {
        HttpStatus httpStatus = HttpStatus.MULTI_STATUS;
        if (EventError.EVENT_EMPTY_LIST.equals(e.getEventError())
                || EventError.EVENT_NOT_FOUND.equals(e.getEventError())) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        if (EventError.EVENT_BAD_LIMIT.equals(e.getEventError())
                || EventError.EVENT_ALREADY_EXIST.equals(e.getEventError())
                || EventError.EVENT_BAD_DESCRIPTION.equals(e.getEventError())
                || EventError.EVENT_MAX_PARTICIPANT_NUMBER.equals(e.getEventError())
                || EventError.EVENT_BAD_DATA.equals(e.getEventError())
                || EventError.EVENT_NOT_ACTIVE.equals(e.getEventError())
                || EventError.PARTICIPANT_NOT_ACTIVE.equals(e.getEventError())) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        if (EventError.PARTICIPANT_ALREADY_ENROLLED.equals(e.getEventError())
                || EventError.EVENT_IS_INACTIVE.equals(e.getEventError())) {
            httpStatus = HttpStatus.CONFLICT;
        }
        return ResponseEntity.status(httpStatus).body(new ErrorEventInfo(e.getEventError().getMessage()));
    }
}
