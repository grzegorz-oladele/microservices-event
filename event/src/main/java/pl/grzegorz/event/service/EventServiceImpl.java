package pl.grzegorz.event.service;

import org.springframework.stereotype.Service;
import pl.grzegorz.event.exception.EventError;
import pl.grzegorz.event.exception.EventException;
import pl.grzegorz.event.model.Event;
import pl.grzegorz.event.repository.EventRepository;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventValidator eventValidator;

    public EventServiceImpl(EventRepository eventRepository, EventValidator eventValidator) {
        this.eventRepository = eventRepository;
        this.eventValidator = eventValidator;
    }

    @Override
    public List<Event> getAllEvents() {
        List<Event> eventList = eventRepository.findAll();
        eventValidator.validateEmptyList(eventList);
        return eventList;
    }

    @Override
    public Event getEventByCode(String code) {
        return getEvent(code);
    }

    @Override
    public Event addEvent(Event event) {
        List<Event> eventList = eventRepository.findAll();
        eventValidator.validateAlreadyExist(event.getCode(), eventList);
        return eventRepository.save(event);
    }

    @Override
    public Event editParticipantsLimit(String code, long limit) {
        Event event = getEvent(code);
        eventValidator.validateParticipantsLimit(limit, event);
        event.setParticipantsLimit(limit);
        return event;
    }

    @Override
    public Event editDescription(String code, Event event) {
        Event event1 = getEvent(code);
        event1.setDescription(event.getDescription());
        eventRepository.save(event1);
        return event1;
    }

    @Override
    public void removeEventByCode(String code) {
        Event event = getEvent(code);
        eventRepository.delete(event);
    }

    private Event getEvent(String code) {
        return eventRepository.findById(code)
                .orElseThrow(() -> new EventException(EventError.EVENT_NOT_FOUND));
    }
}
