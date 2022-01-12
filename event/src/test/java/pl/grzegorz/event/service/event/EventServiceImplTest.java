package pl.grzegorz.event.service.event;

import org.junit.jupiter.api.Test;
import pl.grzegorz.event.model.EventDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EventServiceImplTest {

    @Test
    void shouldReturnListOfEventsWithAllStatus() {
//        given
        List<EventDto> eventDtoList = getEventList();
    }

    @Test
    void shouldReturnEventsWithACTIVEStatus() {

    }

    @Test
    void shouldReturnEventWithINACTIVEStatus() {

    }

    @Test
    void shouldReturnEventsWithFULLStatus() {

    }

    @Test
    void shouldReturnNewValueOfParticipantsLimit() {

    }

    @Test
    void shouldReturnNewValueOfDescription() {

    }

    @Test
    void shouldIncreaseParticipantsNumberByOne() {

    }

    @Test
    void shouldIncreaseParticipantsNumberByOneAndSetFullStatus() {

    }

    @Test
    void shouldSetStatusInactive() {

    }

    private List<EventDto> getEventList() {
        PrepareEventData prepareEventData = new PrepareEventData();
        return prepareEventData.getListOfEvents();
    }

}