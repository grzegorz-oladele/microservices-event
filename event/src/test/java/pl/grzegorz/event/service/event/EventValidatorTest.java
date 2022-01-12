package pl.grzegorz.event.service.event;

import org.junit.jupiter.api.Test;
import pl.grzegorz.event.exception.EventError;
import pl.grzegorz.event.exception.EventException;
import pl.grzegorz.event.model.EventDto;
import pl.grzegorz.event.model.EventMember;
import pl.grzegorz.event.model.dto.Participant;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class EventValidatorTest {

    @Test
    void shouldThrowEventExceptionWhenListOfEventsIsEmpty() {
//        given
        List<EventDto> listOfEvents = Collections.emptyList();
        EventValidator eventValidator = new EventValidator();
//        when + then
        assertThrows(EventException.class, () -> eventValidator.validateEmptyList(listOfEvents));
        assertThat("Events according to your parameters not found", is(EventError.EVENT_EMPTY_LIST.getMessage()));
    }

    @Test
    void shouldReturnMessageWhenListOfEventsIsNotEmpty() {
//        given
        List<EventDto> listOfEvents = getListOfEvents();
        EventValidator eventValidator = new EventValidator();
//        when
        eventValidator.validateEmptyList(listOfEvents);
//        then
        System.out.println("The validateEmptyList() method did not throw an exception. The test passed");
    }

    @Test
    void shouldThrowEventExceptionWhenCodeOneOfEventsInListWillBeEqualWithCodeInArgumentValidatorMethod() {
//        given
        List<EventDto> listOfEvents = getListOfEvents();
        String code = "25th-anniversary";
        EventValidator eventValidator = new EventValidator();
//        when + then
        assertThrows(EventException.class, () -> eventValidator.validateAlreadyExist(code, listOfEvents));
        assertThat("Event already exist", is(EventError.EVENT_ALREADY_EXIST.getMessage()));
    }

    @Test
    void shouldReturnMessageWhenCodesOfEventsWillBeIsNotEqual() {
//        given
        List<EventDto> listOfEvents = getListOfEvents();
        String code = "20th-anniversary";
        EventValidator eventValidator = new EventValidator();
//        when
        eventValidator.validateAlreadyExist(code, listOfEvents);
//        then
        System.out.println("The validateAlreadyExist() method did not throw an exception. The test passed");
    }

    @Test
    void shouldThrowException_WhenNewLimitOfParticipant_WillBeGreaterThanPresentParticipantsLimitInEvent() {
//        given
        EventDto eventDto = getListOfEvents().get(1);
        eventDto.setParticipantsNumber(123L);
        long newLimit = 92;
        EventValidator eventValidator = new EventValidator();
//        when = then
        assertThrows(EventException.class, () -> eventValidator.validateParticipantsLimit(newLimit, eventDto));
        assertThat("Limit of participants must be greater than 0 or less if number of participants is greater than 0",
                is(EventError.EVENT_BAD_LIMIT.getMessage()));
    }

    @Test
    void shouldReturnMessageWhenParticipantsLimitWillBeGreaterThan0AndPresentParticipantsNumber() {
//        given
        EventDto eventDto = getListOfEvents().get(1);
        eventDto.setParticipantsNumber(123L);
        long newLimit = 150;
        EventValidator eventValidator = new EventValidator();
//        when
        eventValidator.validateParticipantsLimit(newLimit, eventDto);
//        then
        System.out.println("The validateParticipantsLimit() method did not throw an exception. The test passed");
    }

    @Test
    void shouldThrowException_WhenParticipantsNumberWillBeEqualParticipantsLimit() {
//        given
        EventDto eventDto = getListOfEvents().get(1);
        eventDto.setParticipantsNumber(200L);
        EventValidator eventValidator = new EventValidator();
//        when + then
        assertThrows(EventException.class, () -> eventValidator.validateMaxParticipantNumber(eventDto));
        assertThat("The number of participants is full. Enrollment closed", is(EventError.EVENT_MAX_PARTICIPANT_NUMBER.getMessage()));
    }

    @Test
    void shouldThrowException_WhenParticipantsNumberWillBeGreaterThanParticipantsLimit() {
//        given
        EventDto eventDto = getListOfEvents().get(1);
        eventDto.setParticipantsNumber(219L);
        EventValidator eventValidator = new EventValidator();
//        when + then
        assertThrows(EventException.class, () -> eventValidator.validateMaxParticipantNumber(eventDto));
        assertThat("The number of participants is full. Enrollment closed", is(EventError.EVENT_MAX_PARTICIPANT_NUMBER.getMessage()));
    }

    @Test
    void shouldReturnMessageWhenParticipantsNumberWillBeLessThanParticipantsLimit() {
//        given
        EventDto eventDto = getListOfEvents().get(1);
        eventDto.setParticipantsNumber(199L);
        EventValidator eventValidator = new EventValidator();
//        when
        eventValidator.validateMaxParticipantNumber(eventDto);
//        then
        System.out.println("The validateMaxParticipantNumber() method did not throw an exception. The test passed");
    }

    @Test
    void shouldThrowExceptionWhenStartDateWillBeAfterEndDate() {
//        given
        EventDto eventDto = getListOfEvents().get(1);
        eventDto.setStartDate(LocalDateTime.parse("2022-03-21T06:00:00.00"));
        EventValidator eventValidator = new EventValidator();
//        when + then
        assertThrows(EventException.class, () -> eventValidator.validateDate(eventDto));
        assertThat("Start date must be earlier than the end date", is(EventError.EVENT_BAD_DATA.getMessage()));
    }

    @Test
    void shouldReturnMessageWhenStartDateWillBeBeforeEndDate() {
//        given
        EventDto eventDto = getListOfEvents().get(1);
        EventValidator eventValidator = new EventValidator();
//        when
        eventValidator.validateDate(eventDto);
//        then
        System.out.println("The validateDate() method did not throw an exception. The test passed");
    }

    @Test
    void shouldThrowExceptionWhenEventStatusWillBeEqualFULL() {
//        given
        EventDto eventDto = getListOfEvents().get(2);
        EventValidator eventValidator = new EventValidator();
//        when + then
        assertThrows(EventException.class, () -> eventValidator.validateEventActive(eventDto));
        assertThat("Registration for the event is not impossible at this time. Event is not active",
                is(EventError.EVENT_NOT_ACTIVE.getMessage()));
    }

    @Test
    void shouldThrowExceptionWhenEventStatusWillBeEqualINACTIVE() {
//        given
        EventDto eventDto = getListOfEvents().get(1);
        EventValidator eventValidator = new EventValidator();
//        when + then
        assertThrows(EventException.class, () -> eventValidator.validateEventActive(eventDto));
        assertThat("Registration for the event is not impossible at this time. Event is not active",
                is(EventError.EVENT_NOT_ACTIVE.getMessage()));
    }

    @Test
    void shouldReturnMessageWhenEventStatusWillBeEqualACTIVE() {
//        given
        EventDto eventDto = getListOfEvents().get(0);
        EventValidator eventValidator = new EventValidator();
//        when
        eventValidator.validateEventActive(eventDto);
//        then
        System.out.println("The validateDate() method did not throw an exception. The test passed");
    }

    @Test
    void shouldThrowExceptionWhenParticipantWillBeEnrolledOnEvent() {
//        given
        EventDto eventDto = getListOfEvents().get(0);
        EventValidator eventValidator = new EventValidator();
        Participant participant = new Participant();
        participant.setEmail("tomasz.tomaszewski@123.com");
//        when + then
        assertThrows(EventException.class, () -> eventValidator.validateParticipantEnrolled(eventDto, participant));
        assertThat("Participant already enrolled on this event",
                is(EventError.PARTICIPANT_ALREADY_ENROLLED.getMessage()));
    }

    @Test
    void shouldReturnMessageWhenParticipantWillBeNotEnrolledOnEvent() {
//        given
        EventDto eventDto = getListOfEvents().get(0);
        EventValidator eventValidator = new EventValidator();
        Participant participant = new Participant();
        participant.setEmail("maciej.maciejewski@123.com");
//        when
        eventValidator.validateParticipantEnrolled(eventDto, participant);
//        then
        System.out.println("The validateParticipantEnrolled() method did not throw an exception. The test passed");
    }

    @Test
    void shouldSetFullStatusParticipantsNumberIsEqualParticipantsLimit() {
//        given
        EventDto eventDto = getListOfEvents().get(3);
        EventValidator eventValidator = new EventValidator();
//        when
        eventValidator.setEventFullStatus(eventDto);
//        then
        assertThat(EventDto.Status.FULL, is(eventDto.getStatus()));
    }

    @Test
    void shouldThrowEventExceptionWhenParticipantWillBeNotActive() {
//        given
        Participant participant = new Participant();
        participant.setActive(false);
        EventValidator eventValidator = new EventValidator();
//        when + then
        assertThrows(EventException.class, () -> eventValidator.validateActiveParticipant(participant));
        assertThat("Participant is not active", is(EventError.PARTICIPANT_NOT_ACTIVE.getMessage()));
    }

    @Test
    void shouldReturnMessageWhenParticipantWillBeActive() {
//        given
        Participant participant = new Participant();
        participant.setActive(true);
        EventValidator eventValidator = new EventValidator();
//        when
        eventValidator.validateActiveParticipant(participant);
//        then
        System.out.println("The validateActiveParticipant() method did not throw an exception. The test passed");
    }

    @Test
    void shouldThrowEventExceptionWhenStatusOfEventWillBeEqualINACTIVE() {
//        given
        EventDto eventDto = getListOfEvents().get(1);
        EventValidator eventValidator = new EventValidator();
//        when + then
        assertThrows(EventException.class, () -> eventValidator.validateInactiveEvent(eventDto));
        assertThat(EventDto.Status.INACTIVE, is(eventDto.getStatus()));
        assertThat("Event is inactive", is(EventError.EVENT_IS_INACTIVE.getMessage()));
    }

    @Test
    void shouldThrowEventExceptionWhenStatusOfEventWillBeEqualACTIVE() {
//        given
        EventDto eventDto = getListOfEvents().get(0);
        EventValidator eventValidator = new EventValidator();
//        when
        eventValidator.validateInactiveEvent(eventDto);
//        then
        assertThat(EventDto.Status.ACTIVE, is(eventDto.getStatus()));
        System.out.println("The validateInactiveEvent() method did not throw an exception. The test passed");
    }

    @Test
    void shouldThrowEventExceptionWhenStatusOfEventWillBeEqualFULL() {
//        given
        EventDto eventDto = getListOfEvents().get(2);
        EventValidator eventValidator = new EventValidator();
//        when
        eventValidator.validateInactiveEvent(eventDto);
//        then
        assertThat(EventDto.Status.FULL, is(eventDto.getStatus()));
        System.out.println("The validateInactiveEvent() method did not throw an exception. The test passed");
    }


    private List<EventDto> getListOfEvents() {
        List<EventDto> list = new ArrayList<>();
        EventDto eventDto1 = new EventDto();
        eventDto1.setCode("Team-building");
        eventDto1.setDescription("The best team building in history of our company");
        eventDto1.setParticipantsLimit(20L);
        eventDto1.setParticipantsNumber(0L);
        eventDto1.setStartDate(LocalDateTime.parse("2022-01-24T10:00:00.00"));
        eventDto1.setEndDate(LocalDateTime.parse("2022-01-25T16:00:00.00"));
        eventDto1.setStatus(EventDto.Status.ACTIVE);
        eventDto1.setEventMembers(listOfEventMembers());

        EventDto eventDto2 = new EventDto();
        eventDto2.setCode("25th-anniversary");
        eventDto2.setDescription("25th Anniversary Party");
        eventDto2.setParticipantsLimit(200L);
        eventDto2.setParticipantsNumber(0L);
        eventDto2.setStartDate(LocalDateTime.parse("2022-03-13T19:00:00.00"));
        eventDto2.setEndDate(LocalDateTime.parse("2022-03-14T06:00:00.00"));
        eventDto2.setStatus(EventDto.Status.INACTIVE);

        EventDto eventDto3 = new EventDto();
        eventDto3.setCode("Sal-2022");
        eventDto3.setDescription("Training in sales closing techniques");
        eventDto3.setParticipantsLimit(10L);
        eventDto3.setParticipantsNumber(0L);
        eventDto3.setStartDate(LocalDateTime.parse("2022-02-10T10:00:00.00"));
        eventDto3.setEndDate(LocalDateTime.parse("2022-02-10T18:00:00.00"));
        eventDto3.setStatus(EventDto.Status.FULL);

        EventDto eventDto4 = new EventDto();
        eventDto4.setCode("Tre-2022");
        eventDto4.setDescription("Training event");
        eventDto4.setParticipantsLimit(1L);
        eventDto4.setParticipantsNumber(1L);
        eventDto4.setStartDate(LocalDateTime.parse("2022-07-13T10:00:00.00"));
        eventDto4.setEndDate(LocalDateTime.parse("2022-07-14T18:00:00.00"));
        eventDto4.setStatus(EventDto.Status.ACTIVE);

        list.add(eventDto1);
        list.add(eventDto2);
        list.add(eventDto3);
        list.add(eventDto4);

        return list;
    }

    private List<EventMember> listOfEventMembers() {
        List<EventMember> list = new ArrayList<>();
        EventMember eventMember = new EventMember("tomasz.tomaszewski@123.com");
        EventMember eventMember2 = new EventMember("grzegorz.grzegorzewski@123.com");

        list.add(eventMember);
        list.add(eventMember2);
        return list;
    }
}