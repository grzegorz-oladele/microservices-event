package pl.grzegorz.attendees.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.grzegorz.attendees.dto.ParticipantDto;
import pl.grzegorz.attendees.dto.ParticipantDtoInfo;
import pl.grzegorz.attendees.repository.ParticipantRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;

    @Override
    public ParticipantDtoInfo getParticipantById(long id) {
        return null;
    }

    @Override
    public List<ParticipantDtoInfo> getAllActiveParticipants() {
        return null;
    }

    @Override
    public ParticipantDtoInfo addParticipant(ParticipantDto participantDto) {
        return null;
    }

    @Override
    public ParticipantDtoInfo editCompany(long id, ParticipantDto participantDto) {
        return null;
    }

    @Override
    public ParticipantDtoInfo editLastName(long id, ParticipantDto participantDto) {
        return null;
    }

    @Override
    public void removeParticipantById(long id) {

    }
}
