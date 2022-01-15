package pl.grzegorz.event.service.event;

import pl.grzegorz.event.model.EventDto;
import pl.grzegorz.event.model.EventMember;
import pl.grzegorz.event.model.dto.Participant;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PrepareEventData {

    protected List<EventDto> getListOfEvents() {
        List<EventDto> list = new ArrayList<>();
        EventDto eventDto1 = new EventDto();
        eventDto1.setCode("Team-building");
        eventDto1.setDescription("The best team building in history of our company");
        eventDto1.setParticipantsLimit(20L);
        eventDto1.setParticipantsNumber(0L);
        eventDto1.setStartDate(LocalDateTime.parse("2022-01-24T10:00:00.00"));
        eventDto1.setEndDate(LocalDateTime.parse("2022-01-25T16:00:00.00"));
        eventDto1.setStatus(EventDto.Status.ACTIVE);
        eventDto1.setEventMembers(listOfEventMembers());

        EventDto eventDto2 = new EventDto();
        eventDto2.setCode("25th-anniversary");
        eventDto2.setDescription("25th Anniversary Party");
        eventDto2.setParticipantsLimit(200L);
        eventDto2.setParticipantsNumber(0L);
        eventDto2.setStartDate(LocalDateTime.parse("2022-03-13T19:00:00.00"));
        eventDto2.setEndDate(LocalDateTime.parse("2022-03-14T06:00:00.00"));
        eventDto2.setStatus(EventDto.Status.INACTIVE);

        EventDto eventDto3 = new EventDto();
        eventDto3.setCode("Sal-2022");
        eventDto3.setDescription("Training in sales closing techniques");
        eventDto3.setParticipantsLimit(10L);
        eventDto3.setParticipantsNumber(0L);
        eventDto3.setStartDate(LocalDateTime.parse("2022-02-10T10:00:00.00"));
        eventDto3.setEndDate(LocalDateTime.parse("2022-02-10T18:00:00.00"));
        eventDto3.setStatus(EventDto.Status.FULL);

        EventDto eventDto4 = new EventDto();
        eventDto4.setCode("Tre-2022");
        eventDto4.setDescription("Training event");
        eventDto4.setParticipantsLimit(1L);
        eventDto4.setParticipantsNumber(1L);
        eventDto4.setStartDate(LocalDateTime.parse("2022-07-13T10:00:00.00"));
        eventDto4.setEndDate(LocalDateTime.parse("2022-07-14T18:00:00.00"));
        eventDto4.setStatus(EventDto.Status.ACTIVE);

        EventDto eventDto5 = new EventDto();
        List<EventMember> listOfMembers = new ArrayList<>();
        eventDto5.setCode("CSR-0809");
        eventDto5.setDescription("CSR event");
        eventDto5.setParticipantsLimit(1L);
        eventDto5.setParticipantsNumber(0L);
        eventDto5.setStartDate(LocalDateTime.parse("2022-05-23T13:00:00.00"));
        eventDto5.setEndDate(LocalDateTime.parse("2022-05-23T15:00:00.00"));
        eventDto5.setStatus(EventDto.Status.ACTIVE);
        eventDto5.setEventMembers(listOfMembers);

        list.add(eventDto1);
        list.add(eventDto2);
        list.add(eventDto3);
        list.add(eventDto4);
        list.add(eventDto5);

        return list;
    }

    private List<EventMember> listOfEventMembers() {
        List<EventMember> list = new ArrayList<>();
        EventMember eventMember = new EventMember("tomasz.tomaszewski@123.com");
        EventMember eventMember2 = new EventMember("grzegorz.grzegorzewski@123.com");

        list.add(eventMember);
        list.add(eventMember2);
        return list;
    }

    protected Participant getParticipant() {
        Participant participant = new Participant();
        participant.setId(1);
        participant.setFirstName("Andrzej");
        participant.setLastName("Andrzejewski");
        participant.setEmail("andrzej.andrzejewski@123.com");
        participant.setActive(true);
        participant.setCompany("Facebook");
        return participant;
    }
}
