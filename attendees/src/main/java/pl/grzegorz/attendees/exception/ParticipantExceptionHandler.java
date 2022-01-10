package pl.grzegorz.attendees.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ParticipantExceptionHandler {

    @ExceptionHandler(value = ParticipantException.class)
    public ResponseEntity<ErrorParticipantInfo> handlerParticipantException(ParticipantException e) {
        HttpStatus httpStatus = HttpStatus.MULTI_STATUS;
        if (ParticipantError.PARTICIPANT_NOT_FOUND.equals(e.getParticipantError())
                || ParticipantError.PARTICIPANT_EMPTY_LIST.equals(e.getParticipantError())) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        if (ParticipantError.PARTICIPANT_ALREADY_EXISTS.equals(e.getParticipantError())) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(httpStatus).body(new ErrorParticipantInfo(e.getParticipantError().getMessage()));
    }
}
