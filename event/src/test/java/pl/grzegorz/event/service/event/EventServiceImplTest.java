package pl.grzegorz.event.service.event;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import pl.grzegorz.event.model.EventDto;
import pl.grzegorz.event.model.dto.Participant;
import pl.grzegorz.event.repository.EventRepository;
import pl.grzegorz.event.service.participant.ParticipantServiceClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class EventServiceImplTest {

    @Test
    void shouldReturnListOfEventsWithAllStatus() {
//        given
        List<EventDto> eventDtoList = getEventList();
        EventRepository eventRepository = mock(EventRepository.class);
        EventValidator eventValidator = new EventValidator();
        ParticipantServiceClient participantServiceClient = mock(ParticipantServiceClient.class);
        RabbitTemplate rabbitTemplate = mock(RabbitTemplate.class);
        EventService eventService = new EventServiceImpl(eventRepository, eventValidator, participantServiceClient, rabbitTemplate);
//        when
        given(eventRepository.findAll()).willReturn(eventDtoList);
//        then
        assertThat(5, is(eventService.getAllEvents(null).size()));
        assertThat(EventDto.Status.ACTIVE, is(eventService.getAllEvents(null).get(0).getStatus()));
        assertThat("25th Anniversary Party", is(eventService.getAllEvents(null).get(1).getDescription()));
        assertThat("Sal-2022", is(eventService.getAllEvents(null).get(2).getCode()));
        assertThat(LocalDateTime.parse("2022-07-13T10:00:00.00"), is(eventService.getAllEvents(null).get(3).getStartDate()));
    }

    @Test
    void shouldReturnNewValueOfParticipantsLimit() {
//        given
        EventDto eventDto = new EventDto();
        eventDto.setParticipantsLimit(40L);
        String code = "Team-building";
        EventDto event = getEventList().get(0);
        EventRepository eventRepository = mock(EventRepository.class);
        EventValidator eventValidator = new EventValidator();
        ParticipantServiceClient participantServiceClient = mock(ParticipantServiceClient.class);
        RabbitTemplate rabbitTemplate = mock(RabbitTemplate.class);
        EventService eventService = new EventServiceImpl(eventRepository, eventValidator, participantServiceClient, rabbitTemplate);
//        when
        given(eventRepository.findById(code)).willReturn(Optional.of(event));
        eventService.editParticipantsLimit(code, eventDto);
//        then
        assertThat(event.getParticipantsLimit(), is(40L));
    }

    @Test
    void shouldReturnNewValueOfDescription() {
        //        given
        EventDto eventDto = new EventDto();
        eventDto.setDescription("The best team building in this year");
        String code = "Team-building";
        EventDto event = getEventList().get(0);
        EventRepository eventRepository = mock(EventRepository.class);
        EventValidator eventValidator = new EventValidator();
        ParticipantServiceClient participantServiceClient = mock(ParticipantServiceClient.class);
        RabbitTemplate rabbitTemplate = mock(RabbitTemplate.class);
        EventService eventService = new EventServiceImpl(eventRepository, eventValidator, participantServiceClient, rabbitTemplate);
//        when
        given(eventRepository.findById(code)).willReturn(Optional.of(event));
        eventService.editDescription(code, eventDto);
//        then
        assertThat(event.getDescription(), is("The best team building in this year"));
    }

    @Test
    void shouldIncreaseParticipantsNumberByOneAndSetFullStatus() {
        //        given
        Participant participant = getParticipant();
        EventDto event = getEventList().get(4);
        EventRepository eventRepository = mock(EventRepository.class);
        EventValidator eventValidator = new EventValidator();
        ParticipantServiceClient participantServiceClient = mock(ParticipantServiceClient.class);
        RabbitTemplate rabbitTemplate = mock(RabbitTemplate.class);
        EventService eventService = new EventServiceImpl(eventRepository, eventValidator, participantServiceClient, rabbitTemplate);
//        when
        given(eventRepository.findById(event.getCode())).willReturn(Optional.of(event));
        given(participantServiceClient.getParticipantById(1)).willReturn(participant);
        eventService.eventEnrollment(event.getCode(), 1);
//        then
        assertThat(event.getEventMembers().size(), is(1));
        assertThat(EventDto.Status.FULL, is(event.getStatus()));
    }

    @Test
    void shouldSetStatusInactive() {
        //        given
        Participant participant = getParticipant();
        EventDto event = getEventList().get(0);
        EventRepository eventRepository = mock(EventRepository.class);
        EventValidator eventValidator = new EventValidator();
        ParticipantServiceClient participantServiceClient = mock(ParticipantServiceClient.class);
        RabbitTemplate rabbitTemplate = mock(RabbitTemplate.class);
        EventService eventService = new EventServiceImpl(eventRepository, eventValidator, participantServiceClient, rabbitTemplate);
//        when
        given(eventRepository.findById(event.getCode())).willReturn(Optional.of(event));
        eventService.eventFinishEnroll(event.getCode());
//        then
        assertThat(EventDto.Status.INACTIVE, is(event.getStatus()));
    }

    private List<EventDto> getEventList() {
        PrepareEventData prepareEventData = new PrepareEventData();
        return prepareEventData.getListOfEvents();
    }

    private Participant getParticipant() {
        PrepareEventData prepareEventData = new PrepareEventData();
        return prepareEventData.getParticipant();
    }

}