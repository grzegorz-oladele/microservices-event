package pl.grzegorz.event.service.event;

import pl.grzegorz.event.model.EventDto;
import pl.grzegorz.event.model.dto.Participant;

import java.util.List;

public interface EventService {

    List<EventDto> getAllEvents(EventDto.Status status);

    EventDto getEventByCode(String code);

    EventDto addEvent(EventDto event);

    EventDto editParticipantsLimit(String code, EventDto event);

    EventDto editDescription(String code, EventDto event);

    void removeEventByCode(String code);

    void eventEnrollment(String eventCode, long participantId);

    List<Participant> getEventMembers(String eventCode);

    void eventFinishEnroll(String eventCode);
}
