package pl.grzegorz.event.service.event;

import org.springframework.stereotype.Component;
import pl.grzegorz.event.exception.EventError;
import pl.grzegorz.event.exception.EventException;
import pl.grzegorz.event.model.EventDto;
import pl.grzegorz.event.model.dto.Participant;

import java.util.List;

@Component
public class EventValidator {

    protected void validateEmptyList(List<EventDto> list) {
        if (list.isEmpty()) {
            throw new EventException(EventError.EVENT_EMPTY_LIST);
        }
    }

    protected void validateAlreadyExist(String code, List<EventDto> eventList) {
        eventList
                .stream()
                .filter(event -> event.getCode().equals(code))
                .forEach(event -> {
                    throw new EventException(EventError.EVENT_ALREADY_EXIST);
                });
    }

    protected void validateParticipantsLimit(long newLimit, EventDto event) {
        if (newLimit <= 0 || newLimit < event.getParticipantsNumber() ||
                event.getParticipantsLimit() < event.getParticipantsNumber()) {
            throw new EventException(EventError.EVENT_BAD_LIMIT);
        }
    }

    protected void validateMaxParticipantNumber(EventDto event) {
        if (event.getParticipantsNumber() >= event.getParticipantsLimit()) {
            throw new EventException(EventError.EVENT_MAX_PARTICIPANT_NUMBER);
        }
    }

    protected void validateDate(EventDto event) {
        if (event.getStartDate().isAfter(event.getEndDate())) {
            throw new EventException(EventError.EVENT_BAD_DATA);
        }
    }

    protected void validateEventActive(EventDto event) {
        if (!EventDto.Status.ACTIVE.equals(event.getStatus())) {
            throw new EventException(EventError.EVENT_NOT_ACTIVE);
        }
    }

    protected void validateParticipantEnrolled(EventDto event, Participant participant) {
        if(event.getEventMembers()
                .stream()
                .anyMatch(member -> participant.getEmail().equals(member.getEmail()))) {
            throw new EventException(EventError.PARTICIPANT_ALREADY_ENROLLED);
        }
    }

    protected void setEventFullStatus(EventDto event) {
        if (event.getParticipantsNumber().equals(event.getParticipantsLimit())) {
            event.setStatus(EventDto.Status.FULL);
        }
    }

    protected void validateActiveParticipant(Participant participant) {
        if(!participant.isActive()) {
            throw new EventException(EventError.PARTICIPANT_NOT_ACTIVE);
        }
    }

    protected void validateInactiveCourse(EventDto event) {
        if (EventDto.Status.INACTIVE.equals(event.getStatus())) {
            throw new EventException(EventError.EVENT_IS_INACTIVE);
        }
    }
}
