package pl.grzegorz.attendees.service;

import org.springframework.stereotype.Service;
import pl.grzegorz.attendees.dto.ParticipantDto;
import pl.grzegorz.attendees.dto.ParticipantDtoInfo;
import pl.grzegorz.attendees.exception.ParticipantError;
import pl.grzegorz.attendees.exception.ParticipantException;
import pl.grzegorz.attendees.mapper.ParticipantMapper;
import pl.grzegorz.attendees.model.ParticipantEntity;
import pl.grzegorz.attendees.repository.ParticipantRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;
    private final ParticipantValidator participantValidator;
    private final ParticipantMapper participantMapper;

    public ParticipantServiceImpl(ParticipantRepository participantRepository, ParticipantValidator participantValidator,
                                  ParticipantMapper participantMapper) {
        this.participantRepository = participantRepository;
        this.participantValidator = participantValidator;
        this.participantMapper = participantMapper;
    }

    @Override
    public ParticipantDtoInfo getParticipantById(long id) {
        ParticipantEntity participantEntity = getParticipantEntity(id);
        return participantMapper.fromEntityToDtoInfo(participantEntity);
    }

    @Override
    public List<ParticipantDtoInfo> getParticipantsByEmails(List<String> emails) {
        List<ParticipantEntity> entityEmailList = participantRepository.findAllByEmailIn(emails);
        return toParticipantListByEmail(entityEmailList);
    }

    @Override
    public List<ParticipantDtoInfo> getAllActiveParticipants() {
        List<ParticipantEntity> participantEntityList = getParticipantsList();
        participantValidator.validateEmptyList(participantEntityList);
        return toActiveParticipantDtoInfoList(participantEntityList);
    }

    @Override
    public ParticipantDtoInfo addParticipant(ParticipantDto participantDto) {
        participantValidator.validateParticipantEmail(participantDto.getEmail(), getParticipantsList());
        ParticipantEntity participantEntity = participantMapper.fromDtoToEntity(participantDto);
        participantEntity.setActive(true);
        participantRepository.save(participantEntity);
        return participantMapper.fromEntityToDtoInfo(participantEntity);
    }

    @Override
    public ParticipantDtoInfo editCompany(long id, ParticipantDto participantDto) {
        ParticipantEntity participantEntity = getParticipantEntity(id);
        participantEntity.setId(id);
        participantEntity.setCompany(participantDto.getCompany());
        return participantMapper.fromEntityToDtoInfo(participantEntity);
    }

    @Override
    public ParticipantDtoInfo editLastName(long id, ParticipantDto participantDto) {
        ParticipantEntity participantEntity = getParticipantEntity(id);
        participantEntity.setId(id);
        participantEntity.setLastName(participantDto.getLastName());
        return participantMapper.fromEntityToDtoInfo(participantEntity);
    }

    @Override
    public void removeParticipantById(long id) {
        ParticipantEntity participantEntity = getParticipantEntity(id);
        participantRepository.delete(participantEntity);
    }

    private List<ParticipantEntity> getParticipantsList() {
        return participantRepository.findAll();
    }

    private ParticipantEntity getParticipantEntity(long id) {
        return participantRepository.findById(id)
                .orElseThrow(() -> new ParticipantException(ParticipantError.PARTICIPANT_NOT_FOUND));
    }

    private List<ParticipantDtoInfo> toActiveParticipantDtoInfoList(List<ParticipantEntity> list) {
        return list
                .stream()
                .filter(ParticipantEntity::isActive)
                .map(participantMapper::fromEntityToDtoInfo)
                .collect(Collectors.toList());
    }

    private List<ParticipantDtoInfo> toParticipantListByEmail(List<ParticipantEntity> list) {
        return list
                .stream()
                .map(participantMapper::fromEntityToDtoInfo)
                .collect(Collectors.toList());
    }
}
