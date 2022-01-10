package pl.grzegorz.attendees.service;

import org.junit.jupiter.api.Test;
import pl.grzegorz.attendees.exception.ParticipantError;
import pl.grzegorz.attendees.exception.ParticipantException;
import pl.grzegorz.attendees.model.ParticipantEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class ParticipantValidatorTest {

    @Test
    void shouldThrowParticipantExceptionWhenEntityListIsEmpty() {
        //        given
        List<ParticipantEntity> participantList = Collections.emptyList();
        ParticipantValidator participantValidator = new ParticipantValidator();
        //        when + then
        assertThrows(ParticipantException.class, () -> participantValidator.validateEmptyEntityList(participantList));
        assertThat("Participants according to your parameters not found",
                is(ParticipantError.PARTICIPANT_EMPTY_LIST.getMessage()));
    }

    @Test
    void shouldReturnAMessageWhenTheEntityListIsNotEmpty() {
        //        given
        List<ParticipantEntity> participantList = getParticipantList();
        ParticipantValidator participantValidator = new ParticipantValidator();
        //        when
        participantValidator.validateEmptyEntityList(participantList);
        //        then
        System.out.println("The validateEmptyList() method did not throw an exception. The test passed");
    }

    @Test
    void shouldThrowParticipantExceptionWhenTheSpecifiedEmailIsInTheList() {
        //        given
        List<ParticipantEntity> participantEntityList = getParticipantList();
        String email = "jacek.jackowski@123.com";
        ParticipantValidator participantValidator = new ParticipantValidator();
        //        when + then
        assertThrows(ParticipantException.class,
                () -> participantValidator.validateParticipantEmail(email, participantEntityList));
        assertThat("The user with the specified email address is already in the application. Select a different " +
                "email address.", is(ParticipantError.PARTICIPANT_ALREADY_EXISTS.getMessage()));
    }

    @Test
    void shouldReturnAMessageWhenTheEmailIsNotInTheList() {
        //        given
        List<ParticipantEntity> participantEntityList = getParticipantList();
        String email = "bartek.bartkowski@123.com";
        ParticipantValidator participantValidator = new ParticipantValidator();
        //        when
        participantValidator.validateParticipantEmail(email, participantEntityList);
        //        then
        System.out.println("The validateParticipantEmail() method did not throw an exception. The test passed");
    }

    @Test
    void shouldThrowParticipantExceptionWhenListOfEmailsIsEmpty() {
        //        given
        List<String> emails = Collections.emptyList();
        ParticipantValidator participantValidator = new ParticipantValidator();
        //        when + then
        assertThrows(ParticipantException.class, () -> participantValidator.validateEmptyEmailList(emails));
        assertThat("Participants according to your parameters not found",
                is(ParticipantError.PARTICIPANT_EMPTY_LIST.getMessage()));
    }

    @Test
    void shouldReturnAMessageWhenTheEmailListIsNotEmpty() {
        //        given
        List<String> emails = getListOfEmails();
        ParticipantValidator participantValidator = new ParticipantValidator();
        //        when
        participantValidator.validateEmptyEmailList(emails);
        //        then
        System.out.println("The validateEmptyEmailList() method did not throw an exception. The test passed");
    }

    private List<ParticipantEntity> getParticipantList() {
        List<ParticipantEntity> participantEntityList = new ArrayList<>();
        ParticipantEntity participant1 = new ParticipantEntity();
        participant1.setFirstName("Tomasz");
        participant1.setLastName("Tomaszewski");
        participant1.setAge(30);
        participant1.setEmail("tomasz.tomaszewski@123.com");
        participant1.setCompany("Firma 1");

        ParticipantEntity participant2 = new ParticipantEntity();
        participant2.setFirstName("Jacek");
        participant2.setLastName("Jackowski");
        participant2.setAge(40);
        participant2.setEmail("jacek.jackowski@123.com");
        participant2.setCompany("Firma 2");

        ParticipantEntity participant3 = new ParticipantEntity();
        participant3.setFirstName("Grzegorz");
        participant3.setLastName("Grzegorzewski");
        participant3.setAge(45);
        participant3.setEmail("grzegorz.grzegrzewski@123.com");
        participant3.setCompany("Firma 3");

        participantEntityList.add(participant1);
        participantEntityList.add(participant2);
        participantEntityList.add(participant3);
        return participantEntityList;
    }

    private List<String> getListOfEmails() {
        return getParticipantList()
                .stream()
                .map(ParticipantEntity::getEmail)
                .collect(Collectors.toList());
    }
}