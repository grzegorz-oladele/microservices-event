package pl.grzegorz.event.service;

import pl.grzegorz.event.model.Event;

import java.util.List;

public interface EventService {

    List<Event> getAllEvents();

    Event getEventByCode(String code);

    Event addEvent(Event event);

    Event editParticipantsLimit(String code, Event event);

    Event editDescription(String code, Event event);

    void removeEventByCode(String code);
}
