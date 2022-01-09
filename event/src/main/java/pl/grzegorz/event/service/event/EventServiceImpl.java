package pl.grzegorz.event.service.event;

import org.springframework.stereotype.Service;
import pl.grzegorz.event.exception.EventError;
import pl.grzegorz.event.exception.EventException;
import pl.grzegorz.event.model.Event;
import pl.grzegorz.event.model.EventMember;
import pl.grzegorz.event.model.dto.Participant;
import pl.grzegorz.event.repository.EventRepository;
import pl.grzegorz.event.service.participant.ParticipantServiceClient;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventValidator eventValidator;
    private final ParticipantServiceClient participantServiceClient;

    public EventServiceImpl(EventRepository eventRepository, EventValidator eventValidator, ParticipantServiceClient participantServiceClient) {
        this.eventRepository = eventRepository;
        this.eventValidator = eventValidator;
        this.participantServiceClient = participantServiceClient;
    }

    @Override
    public List<Event> getAllEvents(Event.Status status) {
        if (status == null) {
            List<Event> eventList = eventRepository.findAll();
            eventValidator.validateEmptyList(eventList);
            return eventList;
        } else {
            List<Event> eventListByStatus = eventRepository.findAllByStatus(status);
            eventValidator.validateEmptyList(eventListByStatus);
            return eventListByStatus;
        }
    }

    @Override
    public Event getEventByCode(String code) {
        return getEvent(code);
    }

    @Override
    public Event addEvent(Event event) {
        List<Event> eventList = eventRepository.findAll();
        eventValidator.validateAlreadyExist(event.getCode(), eventList);
        eventValidator.validateDate(event);
        return eventRepository.save(event);
    }

    @Override
    public Event editParticipantsLimit(String code, Event event) {
        Event event1 = getEvent(code);
        eventValidator.validateParticipantsLimit(event.getParticipantsLimit(), event1);
        event1.setParticipantsLimit(event.getParticipantsLimit());
        return event1;
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
//        event.setStatus(Event.Status.INACTIVE);
        eventRepository.delete(event);
    }

    @Override
    public void eventEnrollment(String eventCode, long participantId) {
        Event event = getEvent(eventCode);
        eventValidator.validateEventActive(event);
        Participant participant = participantServiceClient.getParticipantById(participantId);
        eventValidator.validateParticipantEnrolled(event, participant);
        eventRepository.save(event);
        eventValidator.validateActiveParticipant(participant);
        eventValidator.validateMaxParticipantNumber(event);
        event.getEventMembers().add(new EventMember(participant.getEmail()));
        event.setParticipantsNumber(event.getParticipantsNumber() + 1);
        eventRepository.save(event);
        eventValidator.setEventFullStatus(event);
    }

    private Event getEvent(String code) {
        return eventRepository.findById(code)
                .orElseThrow(() -> new EventException(EventError.EVENT_NOT_FOUND));
    }
}
