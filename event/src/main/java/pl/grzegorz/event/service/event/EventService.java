package pl.grzegorz.event.service.event;

import pl.grzegorz.event.model.Event;
import pl.grzegorz.event.model.dto.Participant;

import java.util.List;

public interface EventService {

    List<Event> getAllEvents(Event.Status status);

    Event getEventByCode(String code);

    Event addEvent(Event event);

    Event editParticipantsLimit(String code, Event event);

    Event editDescription(String code, Event event);

    void removeEventByCode(String code);

    void eventEnrollment(String eventCode, long participantId);

    List<Participant> getEventMembers(String eventCode);

    void eventFinishEnroll(String eventCode);
}
