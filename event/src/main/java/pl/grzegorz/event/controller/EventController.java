package pl.grzegorz.event.controller;

import org.springframework.web.bind.annotation.*;
import pl.grzegorz.event.model.Event;
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
    public List<Event> getAllEvents(@RequestParam (required = false) Event.Status status) {
        return eventService.getAllEvents(status);
    }

    @GetMapping("/{code}")
    public Event getEventByCode(@PathVariable String code) {
        return eventService.getEventByCode(code);
    }

    @PostMapping("/{code}/members")
    public List<Participant> getEventMembers(@PathVariable String code) {
        return eventService.getEventMembers(code);
    }

    @PostMapping
    public Event addEvent(@RequestBody Event event) {
        return eventService.addEvent(event);
    }

    @PatchMapping("/edit-limit/{code}")
    public Event editCode(@PathVariable String code, @RequestBody Event event) {
        return eventService.editParticipantsLimit(code, event);
    }

    @PatchMapping("/edit-description/{code}")
    public Event editDescription(@PathVariable String code, @RequestBody Event event) {
        return eventService.editDescription(code, event);
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
