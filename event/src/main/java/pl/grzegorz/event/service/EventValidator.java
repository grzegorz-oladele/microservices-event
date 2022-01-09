package pl.grzegorz.event.service;

import org.springframework.stereotype.Component;
import pl.grzegorz.event.exception.EventError;
import pl.grzegorz.event.exception.EventException;
import pl.grzegorz.event.model.Event;

import java.util.List;

@Component
public class EventValidator {

    protected void validateEmptyList(List<Event> list) {
        if (list.isEmpty()) {
            throw new EventException(EventError.EVENT_EMPTY_LIST);
        }
    }

    protected void validateAlreadyExist(String code, List<Event> eventList) {
        eventList
                .stream()
                .filter(event -> event.getCode().equals(code))
                .forEach(event -> {
                    throw new EventException(EventError.EVENT_ALREADY_EXIST);
                });
    }

    protected void validateParticipantsLimit(long newLimit, Event event) {
        if (newLimit <= 0 || newLimit < event.getParticipantsNumber() ||
                event.getParticipantsLimit() < event.getParticipantsNumber()) {
            throw new EventException(EventError.EVENT_BAD_LIMIT);
        }
    }

    protected void validateMaxParticipantNumber(Event event) {
        if (event.getParticipantsNumber().equals(event.getParticipantsLimit())) {
            event.setStatus(Event.Status.FULL);
            throw new EventException(EventError.EVENT_MAX_PARTICIPANT_NUMBER);
        }
    }

    protected void validateDescription(String description) {
        if (description == null || description.equals("") || !description.matches("(.|\\s)*\\S(.|\\s)*")) {
            throw new EventException(EventError.EVENT_BAD_DESCRIPTION);
        }
    }

    protected void validateDate(Event event) {
        if (event.getStartDate().isAfter(event.getEndDate())) {
            throw new EventException(EventError.EVENT_BAD_DATA);
        }
    }
}
