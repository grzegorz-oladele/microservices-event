package pl.grzegorz.event.controller;

import org.springframework.web.bind.annotation.*;
import pl.grzegorz.event.model.Event;
import pl.grzegorz.event.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{code}")
    public Event getEventByCode(@PathVariable String code) {
        return eventService.getEventByCode(code);
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

}
