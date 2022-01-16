package pl.grzegorz.attendees.service;

import lombok.var;
import org.junit.jupiter.api.Test;
import pl.grzegorz.attendees.dto.ParticipantDto;
import pl.grzegorz.attendees.dto.ParticipantDtoInfo;
import pl.grzegorz.attendees.mapper.ParticipantMapper;
import pl.grzegorz.attendees.model.ParticipantEntity;
import pl.grzegorz.attendees.repository.ParticipantRepository;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ParticipantServiceImplTest {

    @Test
    void shouldSetActiveAsTrueInParticipantEntity() {
//        given
        ParticipantRepository participantRepository = mock(ParticipantRepository.class);
        ParticipantValidator participantValidator = new ParticipantValidator();
        ParticipantMapper participantMapper = new ParticipantMapper();
        ParticipantService participantService = new ParticipantServiceImpl(participantRepository, participantValidator, participantMapper);
        ParticipantDto participantDto = new ParticipantDto();
//        when + then
        assertThat(participantService.addParticipant(participantDto).isActive(), is(true));
        assertThat(participantService.addParticipant(participantDto), is(instanceOf(ParticipantDtoInfo.class)));
    }

    @Test
    void shouldSetCompanyNameInParticipantEntityAndReturnParticipantDtoInfo() {
        //        given
        ParticipantRepository participantRepository = mock(ParticipantRepository.class);
        ParticipantValidator participantValidator = new ParticipantValidator();
        ParticipantMapper participantMapper = new ParticipantMapper();
        ParticipantService participantService = new ParticipantServiceImpl(participantRepository, participantValidator, participantMapper);
        ParticipantDto participantDto = new ParticipantDto();
        ParticipantEntity participantEntity = new ParticipantEntity();
        participantDto.setCompany("Google");
//        given
        given(participantRepository.findById(1L)).willReturn(Optional.of(participantEntity));
//        then
        assertThat("Google", is(participantService.editCompany(1, participantDto).getCompany()));
        assertThat(1L, is(participantService.editCompany(1, participantDto).getId()));
        assertThat(participantService.editCompany(1, participantDto), instanceOf(ParticipantDtoInfo.class));
    }

    @Test
    void shouldSetLastNameNameInParticipantEntityAndReturnParticipantDtoInfo() {
        //        given
        ParticipantRepository participantRepository = mock(ParticipantRepository.class);
        ParticipantValidator participantValidator = new ParticipantValidator();
        ParticipantMapper participantMapper = new ParticipantMapper();
        ParticipantService participantService = new ParticipantServiceImpl(participantRepository, participantValidator, participantMapper);
        ParticipantDto participantDto = new ParticipantDto();
        ParticipantEntity participantEntity = new ParticipantEntity();
        participantDto.setLastName("Kowalski");
//        given
        given(participantRepository.findById(1L)).willReturn(Optional.of(participantEntity));
//        then
        assertThat("Kowalski", is(participantService.editLastName(1, participantDto).getLastName()));
        assertThat(1L, is(participantService.editLastName(1, participantDto).getId()));
        assertThat(participantService.editCompany(1, participantDto), instanceOf(ParticipantDtoInfo.class));
    }
}