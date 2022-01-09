package pl.grzegorz.attendees.service;

import org.springframework.stereotype.Component;
import pl.grzegorz.attendees.dto.ParticipantDto;
import pl.grzegorz.attendees.exception.ParticipantError;
import pl.grzegorz.attendees.exception.ParticipantException;
import pl.grzegorz.attendees.model.ParticipantEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParticipantValidator {

    protected void validateEmptyList(List<ParticipantEntity> list) {
        if (list.isEmpty()) {
            throw new ParticipantException(ParticipantError.PARTICIPANT_EMPTY_LIST);
        }
    }

    protected void validateEditCompany(ParticipantDto participantDto) {
        if (participantDto.getCompany() == null) {
            throw new ParticipantException(ParticipantError.PARTICIPANT_NULL_POINTER);
        }
    }

    protected void validateEditLastName(ParticipantDto participantDto) {
        if (participantDto.getLastName() == null) {
            throw new ParticipantException(ParticipantError.PARTICIPANT_NULL_POINTER);
        }
    }

    protected void validateParticipantDto(ParticipantDto participantDto) {
        if (participantDto == null) {
            throw new ParticipantException(ParticipantError.PARTICIPANT_NULL_POINTER);
        }
    }

    protected void validateParticipantEmail(String emailValue, List<ParticipantEntity> participants) {
        List<String> participantsEmails = participantsEmail(participants);
        participantsEmails.stream()
                .filter(participantsEmail -> participantsEmail.equals(emailValue))
                .forEach(participantsEmail -> {
            throw new ParticipantException(ParticipantError.PARTICIPANT_ALREADY_EXISTS);
        });
    }

    private List<String> participantsEmail(List<ParticipantEntity> participants) {
        return participants
                .stream()
                .map(ParticipantEntity::getEmail)
                .collect(Collectors.toList());
    }
}
