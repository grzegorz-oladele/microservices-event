package pl.grzegorz.event.controller;

import org.springframework.web.bind.annotation.*;
import pl.grzegorz.event.model.EventDto;
import pl.grzegorz.event.model.dto.Participant;
import pl.grzegorz.event.service.event.EventService;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventDto> getAllEvents(@RequestParam (required = false) EventDto.Status status) {
        return eventService.getAllEvents(status);
    }

    @GetMapping("/{code}")
    public EventDto getEventByCode(@PathVariable String code) {
        return eventService.getEventByCode(code);
    }

    @GetMapping("/{code}/members")
    public List<Participant> getEventMembers(@PathVariable String code) {
        return eventService.getEventMembers(code);
    }

    @PostMapping
    public EventDto addEvent(@RequestBody EventDto eventDto) {
        return eventService.addEvent(eventDto);
    }

    @PatchMapping("/edit-limit/{code}")
    public EventDto editLimit(@PathVariable String code, @RequestBody EventDto eventDto) {
        return eventService.editParticipantsLimit(code, eventDto);
    }

    @PatchMapping("/edit-description/{code}")
    public EventDto editDescription(@PathVariable String code, @RequestBody EventDto eventDto) {
        return eventService.editDescription(code, eventDto);
    }

    @DeleteMapping("/{code}")
    public void deleteEvent(@PathVariable String code) {
        eventService.removeEventByCode(code);
    }

    @PostMapping("/{eventCode}/participant/{participantId}")
    public void participantEnrollmentOnEvent(@PathVariable String eventCode, @PathVariable long participantId) {
        eventService.eventEnrollment(eventCode, participantId);
    }

    @PostMapping("/{code}/finish-enroll")
    public void eventFinishEnroll(@PathVariable String code) {
        eventService.eventFinishEnroll(code);
    }
}
