package pl.grzegorz.attendees.mapper;

import org.junit.jupiter.api.Test;
import pl.grzegorz.attendees.dto.ParticipantDto;
import pl.grzegorz.attendees.dto.ParticipantDtoInfo;
import pl.grzegorz.attendees.model.ParticipantEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;

class ParticipantMapperTest {

    @Test
    void shouldReturnNullWhenParticipantDtoIsNull() {
//        given
        ParticipantDto participantDto = null;
        ParticipantMapper participantMapper = new ParticipantMapper();
//        when
        ParticipantEntity participantEntity = participantMapper.fromDtoToEntity(participantDto);
//        then
        assertThat(null, is(participantEntity));
    }

    @Test
    void shouldReturnParticipantEntityWhenParticipantDtoIsNotNull() {
//        given
        ParticipantDto participantDto = getParticipantDto();
        ParticipantMapper participantMapper = new ParticipantMapper();
//        when
        ParticipantEntity participantEntity = participantMapper.fromDtoToEntity(participantDto);
//        then
        assertThat(anyLong(), is(participantEntity.getId()));
        assertThat("Bartek", is(participantEntity.getFirstName()));
        assertThat("Bartoszewski", is(participantEntity.getLastName()));
        assertThat("bartek.bartoszewski@123.com", is(participantEntity.getEmail()));
        assertThat("Google", is(participantEntity.getCompany()));
        assertThat(false, is(participantEntity.isActive()));
        assertThat(30, is(participantEntity.getAge()));
        assertThat(participantEntity, is(instanceOf(ParticipantEntity.class)));
    }

    @Test
    void shouldReturnNullWhenParticipantEntityIsNull() {
//        given
        ParticipantEntity participantEntity = null;
        ParticipantMapper participantMapper = new ParticipantMapper();
//        when
        ParticipantDtoInfo participantDtoInfo = participantMapper.fromEntityToDtoInfo(participantEntity);
//        then
        assertThat(null, is(participantDtoInfo));
    }

    @Test
    void shouldReturnParticipantDtoInfoWhenParticipantEntityIsNotNull() {
//        given
        ParticipantEntity participantEntity = getParticipantEntity();
        ParticipantMapper participantMapper = new ParticipantMapper();
//        when
        ParticipantDtoInfo participantDtoInfo = participantMapper.fromEntityToDtoInfo(participantEntity);
//        then
        assertThat(anyLong(), is(participantDtoInfo.getId()));
        assertThat("Tomasz", is(participantDtoInfo.getFirstName()));
        assertThat("Tomaszewski", is(participantDtoInfo.getLastName()));
        assertThat("tomasz.tomaszewski@123.com", is(participantDtoInfo.getEmail()));
        assertThat("Netflix", is(participantDtoInfo.getCompany()));
        assertThat(false, is(participantDtoInfo.isActive()));
        assertThat(33, is(participantDtoInfo.getAge()));
        assertThat(participantDtoInfo, is(instanceOf(ParticipantDtoInfo.class)));
    }

    private ParticipantEntity getParticipantEntity() {
        ParticipantEntity participantEntity = new ParticipantEntity();
        participantEntity.setFirstName("Tomasz");
        participantEntity.setLastName("Tomaszewski");
        participantEntity.setEmail("tomasz.tomaszewski@123.com");
        participantEntity.setCompany("Netflix");
        participantEntity.setAge(33);
        return participantEntity;
    }

    private ParticipantDto getParticipantDto() {
        ParticipantDto participantDto = new ParticipantDto();
        participantDto.setFirstName("Bartek");
        participantDto.setLastName("Bartoszewski");
        participantDto.setEmail("bartek.bartoszewski@123.com");
        participantDto.setCompany("Google");
        participantDto.setAge(30);
        return participantDto;
    }
}