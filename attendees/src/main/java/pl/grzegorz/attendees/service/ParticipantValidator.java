package pl.grzegorz.attendees.service;

import org.springframework.stereotype.Component;
import pl.grzegorz.attendees.exception.ParticipantError;
import pl.grzegorz.attendees.exception.ParticipantException;
import pl.grzegorz.attendees.model.ParticipantEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParticipantValidator {

    protected void validateEmptyEntityList(List<ParticipantEntity> list) {
        if (list.isEmpty()) {
            throw new ParticipantException(ParticipantError.PARTICIPANT_EMPTY_LIST);
        }
    }

    protected void validateEmptyEmailList(List<String> list) {
        if (list.isEmpty()) {
            throw new ParticipantException(ParticipantError.PARTICIPANT_EMPTY_LIST);
        }
    }

    protected void validateParticipantEmail(String emailValue, List<ParticipantEntity> participants) {
        List<String> participantsEmails = getParticipantsEmail(participants);
        participantsEmails.stream()
                .filter(participantsEmail -> participantsEmail.equals(emailValue))
                .forEach(participantsEmail -> {
            throw new ParticipantException(ParticipantError.PARTICIPANT_ALREADY_EXISTS);
        });
    }

    private List<String> getParticipantsEmail(List<ParticipantEntity> participants) {
        return participants
                .stream()
                .map(ParticipantEntity::getEmail)
                .collect(Collectors.toList());
    }
}
