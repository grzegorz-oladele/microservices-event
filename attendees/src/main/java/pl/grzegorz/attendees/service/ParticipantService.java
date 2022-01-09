package pl.grzegorz.attendees.service;

import pl.grzegorz.attendees.dto.ParticipantDto;
import pl.grzegorz.attendees.dto.ParticipantDtoInfo;

import java.util.List;

public interface ParticipantService {

    ParticipantDtoInfo getParticipantById(long id);

    List<ParticipantDtoInfo> getParticipantsByEmails(List<String> emails);

    List<ParticipantDtoInfo> getAllActiveParticipants();

    ParticipantDtoInfo addParticipant(ParticipantDto participantDto);

    ParticipantDtoInfo editCompany(long id, ParticipantDto participantDto);

    ParticipantDtoInfo editLastName(long id, ParticipantDto participantDto);

    void removeParticipantById(long id);
}
