package pl.grzegorz.event.service;

import org.springframework.stereotype.Service;
import pl.grzegorz.event.model.Event;
import pl.grzegorz.event.repository.EventRepository;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    @Override
    public List<Event> getAllEvents() {
        return null;
    }

    @Override
    public Event getEventByCode(String code) {
        return null;
    }

    @Override
    public Event addEvent(Event event) {
        return null;
    }

    @Override
    public Event editParticipantsLimit(int limit) {
        return null;
    }

    @Override
    public Event editDate(Event event) {
        return null;
    }

    @Override
    public Event editDescription(Event event) {
        return null;
    }

    @Override
    public void removeEventByCode(String code) {

    }
}
