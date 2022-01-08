package pl.grzegorz.attendees.service;

import org.springframework.stereotype.Service;
import pl.grzegorz.attendees.dto.ParticipantDto;
import pl.grzegorz.attendees.dto.ParticipantDtoInfo;
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
        ParticipantEntity participantEntity = participantValidator.validateNotFound(id);
        return participantMapper.fromEntityToDtoInfo(participantEntity);
    }

    @Override
    public List<ParticipantDtoInfo> getAllActiveParticipants() {
        List<ParticipantEntity> participantEntityList = participantRepository.findAll();
        participantValidator.validateEmptyList(participantEntityList);
        return toActiveParticipantDtoInfoList(participantEntityList);
    }

    @Override
    public ParticipantDtoInfo addParticipant(ParticipantDto participantDto) {
        participantValidator.validateParticipantDto(participantDto);
        ParticipantEntity participantEntity = participantMapper.fromDtoToEntity(participantDto);
        participantRepository.save(participantEntity);
        return participantMapper.fromEntityToDtoInfo(participantEntity);
    }

    @Override
    public ParticipantDtoInfo editCompany(long id, ParticipantDto participantDto) {
        ParticipantEntity participantEntity = participantValidator.validateNotFound(id);
        participantValidator.validateEditCompany(participantDto);
        participantEntity.setId(id);
        participantEntity.setCompany(participantDto.getCompany());
        return participantMapper.fromEntityToDtoInfo(participantEntity);
    }

    @Override
    public ParticipantDtoInfo editLastName(long id, ParticipantDto participantDto) {
        ParticipantEntity participantEntity = participantValidator.validateNotFound(id);
        participantValidator.validateEditLastName(participantDto);
        participantEntity.setId(id);
        participantEntity.setLastName(participantDto.getLastName());
        return participantMapper.fromEntityToDtoInfo(participantEntity);
    }

    @Override
    public void removeParticipantById(long id) {
        ParticipantEntity participantEntity = participantValidator.validateNotFound(id);
        participantEntity.setActive(false);
    }

    private List<ParticipantDtoInfo> toActiveParticipantDtoInfoList(List<ParticipantEntity> list) {
        return list
                .stream()
                .filter(ParticipantEntity::isActive)
                .map(participantMapper::fromEntityToDtoInfo)
                .collect(Collectors.toList());
    }
}
