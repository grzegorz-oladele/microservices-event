package pl.grzegorz.event.service;

import pl.grzegorz.event.model.Event;

import java.util.List;

public interface EventService {

    List<Event> getAllEvents();

    Event getEventByCode(String code);

    Event addEvent(Event event);

    Event editParticipantsLimit(int limit);

    Event editDate(Event event);

    Event editDescription(Event event);

    void removeEventByCode(String code);
}
