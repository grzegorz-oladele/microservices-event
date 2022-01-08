package pl.grzegorz.attendees.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.grzegorz.attendees.builder.ParticipantDtoInfoBuilder;
import pl.grzegorz.attendees.builder.ParticipantEntityBuilder;
import pl.grzegorz.attendees.dto.ParticipantDto;
import pl.grzegorz.attendees.dto.ParticipantDtoInfo;
import pl.grzegorz.attendees.model.ParticipantEntity;

@Component
public class ParticipantMapper {

    public ParticipantEntity fromDtoToEntity(ParticipantDto participantDto) {
        if (participantDto == null) {
            return null;
        }
        var participantEntityBuilder = new ParticipantEntityBuilder();
        if (participantDto.getFirstName() != null) {
            participantEntityBuilder.withFirstName(participantDto.getFirstName());
        }
        if (participantDto.getLastName() != null) {
            participantEntityBuilder.withLastName(participantDto.getLastName());
        }
        if (participantDto.getEmail() != null) {
            participantEntityBuilder.withEmail(participantDto.getEmail());
        }
        if (participantDto.getAge() > 0) {
            participantEntityBuilder.withAge(participantDto.getAge());
        }
        if (participantDto.getCompany() != null) {
            participantEntityBuilder.withCompany(participantDto.getCompany());
        }
        return participantEntityBuilder.build();
    }

    public ParticipantDtoInfo fromEntityToDtoInfo(ParticipantEntity participantEntity) {
        if (participantEntity == null) {
            return null;
        }
        var participantDtoInfoBuilder = new ParticipantDtoInfoBuilder();
        if (participantEntity.getId() > 0) {
            participantDtoInfoBuilder.withAge(participantEntity.getAge());
        }
        if (participantEntity.getFirstName() != null) {
            participantDtoInfoBuilder.withFirstName(participantEntity.getFirstName());
        }
        if (participantEntity.getLastName() != null) {
            participantDtoInfoBuilder.withLastName(participantEntity.getLastName());
        }
        if (participantEntity.getEmail() != null) {
            participantDtoInfoBuilder.withEmail(participantEntity.getEmail());
        }
        if (participantEntity.getAge() > 0) {
            participantDtoInfoBuilder.withAge(participantEntity.getAge());
        }
        if (participantEntity.getCompany() != null) {
            participantDtoInfoBuilder.withCompany(participantEntity.getCompany());
        }
        return participantDtoInfoBuilder.build();
    }


}
