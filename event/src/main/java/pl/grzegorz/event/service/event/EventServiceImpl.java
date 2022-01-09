package pl.grzegorz.event.service.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import pl.grzegorz.event.exception.EventError;
import pl.grzegorz.event.exception.EventException;
import pl.grzegorz.event.model.Event;
import pl.grzegorz.event.model.EventMember;
import pl.grzegorz.event.model.dto.NotificationInfoDto;
import pl.grzegorz.event.model.dto.Participant;
import pl.grzegorz.event.repository.EventRepository;
import pl.grzegorz.event.service.participant.ParticipantServiceClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private static final String QUEUE_ENROLL_FINISH = "enroll_finish_event";

    private final EventRepository eventRepository;
    private final EventValidator eventValidator;
    private final ParticipantServiceClient participantServiceClient;
    private final RabbitTemplate rabbitTemplate;

    public EventServiceImpl(EventRepository eventRepository, EventValidator eventValidator, ParticipantServiceClient participantServiceClient, RabbitTemplate rabbitTemplate) {
        this.eventRepository = eventRepository;
        this.eventValidator = eventValidator;
        this.participantServiceClient = participantServiceClient;
        this.rabbitTemplate = rabbitTemplate;
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
        eventRepository.delete(event);
    }

    @Override
    public void eventEnrollment(String eventCode, long participantId) {
        Event event = getEvent(eventCode);
        eventValidator.validateEventActive(event);
        Participant participant = participantServiceClient.getParticipantById(participantId);
        eventValidator.validateParticipantEnrolled(event, participant);
        eventValidator.validateActiveParticipant(participant);
        eventValidator.validateMaxParticipantNumber(event);
        event.getEventMembers().add(new EventMember(participant.getEmail()));
        event.setParticipantsNumber(event.getParticipantsNumber() + 1);
        eventValidator.setEventFullStatus(event);
        eventRepository.save(event);
    }

    @Override
    public List<Participant> getEventMembers(String eventCode) {
        Event event = getEvent(eventCode);
        List<String> emailsCourseMembers = getEmailsEventMembers(event);
        return participantServiceClient.getParticipantsByEmailList(emailsCourseMembers);
    }

    @Override
    public void eventFinishEnroll(String eventCode) {
        Event event = getEvent(eventCode);
        eventValidator.validateInactiveCourse(event);
        event.setStatus(Event.Status.INACTIVE);
        eventRepository.save(event);
        List<String> emailsCourseMembers = getEmailsEventMembers(event);
        NotificationInfoDto notificationInfoDto = new NotificationInfoDto();
        notificationInfoDto.setEventCode(event.getCode());
        notificationInfoDto.setEventName(event.getName());
        notificationInfoDto.setEventDescription(event.getDescription());
        notificationInfoDto.setStartDate(event.getStartDate());
        notificationInfoDto.setEndDate(event.getEndDate());
        notificationInfoDto.setEmails(emailsCourseMembers);
        rabbitTemplate.convertAndSend(QUEUE_ENROLL_FINISH, notificationInfoDto);
    }

    private Event getEvent(String code) {
        return eventRepository.findById(code)
                .orElseThrow(() -> new EventException(EventError.EVENT_NOT_FOUND));
    }

    private List<String> getEmailsEventMembers(Event event) {
        return event.getEventMembers()
                .stream()
                .map(EventMember::getEmail)
                .collect(Collectors.toList());
    }
}
