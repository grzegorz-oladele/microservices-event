package pl.grzegorz.event.service.event;

import org.junit.jupiter.api.Test;
import pl.grzegorz.event.exception.EventError;
import pl.grzegorz.event.exception.EventException;
import pl.grzegorz.event.model.EventDto;
import pl.grzegorz.event.model.EventMember;
import pl.grzegorz.event.model.dto.Participant;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        assertThrows(EventException.class, () -> eventValidator.validateEmptyDtoList(listOfEvents));
        assertThat("Events according to your parameters not found", is(EventError.EVENT_EMPTY_LIST.getMessage()));
    }

    @Test
    void shouldReturnMessageWhenListOfEventDtoIWillBeNotEmpty() {
//        given
        List<EventDto> listOfEvents = getListOfEvents();
        EventValidator eventValidator = new EventValidator();
//        when
        eventValidator.validateEmptyDtoList(listOfEvents);
//        then
        System.out.println("The validateEmptyList() method did not throw an exception. The test passed");
    }

    @Test
    void shouldThrowEventExceptionWhenListOfEmailEventMembersIsEmpty() {
//        given
        List<String> listOfEmailMembers = Collections.emptyList();
        EventValidator eventValidator = new EventValidator();
//        when + then
        assertThrows(EventException.class, () -> eventValidator.validateEmptyEmailsEventMembersList(listOfEmailMembers));
        assertThat("Events according to your parameters not found", is(EventError.EVENT_EMPTY_LIST.getMessage()));
    }

    @Test
    void shouldReturnMessageWhenListOfEmailEventMembersIWillBeNotEmpty() {
//        given
        List<String> listOfEmails = getListOfEmails();
        EventValidator eventValidator = new EventValidator();
//        when
        eventValidator.validateEmptyEmailsEventMembersList(listOfEmails);
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
        PrepareEventData eventPrepareData = new PrepareEventData();
        return eventPrepareData.getListOfEvents();
    }

    private List<String> getListOfEmails() {
        PrepareEventData prepareEventData = new PrepareEventData();
        return prepareEventData.listOfEventMembers()
                .stream()
                .map(EventMember::getEmail)
                .collect(Collectors.toList());
    }
}