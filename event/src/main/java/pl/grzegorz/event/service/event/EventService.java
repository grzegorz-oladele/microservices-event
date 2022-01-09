package pl.grzegorz.event.service.event;

import pl.grzegorz.event.model.Event;

import java.util.List;

public interface EventService {

    List<Event> getAllEvents(Event.Status status);

    Event getEventByCode(String code);

    Event addEvent(Event event);

    Event editParticipantsLimit(String code, Event event);

    Event editDescription(String code, Event event);

    void removeEventByCode(String code);

    void eventEnrollment(String eventCode, long participantId);
}
