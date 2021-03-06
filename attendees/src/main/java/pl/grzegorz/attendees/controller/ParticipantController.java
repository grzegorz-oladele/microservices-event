package pl.grzegorz.attendees.controller;

import org.springframework.web.bind.annotation.*;
import pl.grzegorz.attendees.dto.ParticipantDto;
import pl.grzegorz.attendees.dto.ParticipantDtoInfo;
import pl.grzegorz.attendees.service.ParticipantService;

import java.util.List;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping("/{id}")
    public ParticipantDtoInfo getParticipantById(@PathVariable long id) {
        return participantService.getParticipantById(id);
    }

    @GetMapping
    public List<ParticipantDtoInfo> getAllActiveParticipants() {
        return participantService.getAllActiveParticipants();
    }

    @PostMapping
    public ParticipantDtoInfo addParticipant(@RequestBody ParticipantDto participantDto) {
        return participantService.addParticipant(participantDto);
    }

    @PatchMapping("/last-name/{id}")
    public ParticipantDtoInfo editLastName(@PathVariable long id, @RequestBody ParticipantDto participantDto) {
        return participantService.editLastName(id, participantDto);
    }

    @PatchMapping("/company/{id}")
    public ParticipantDtoInfo editCompany(@PathVariable long id, @RequestBody ParticipantDto participantDto) {
        return participantService.editCompany(id, participantDto);
    }

    @DeleteMapping("/{id}")
    public void deleteParticipant(@PathVariable long id) {
        participantService.removeParticipantById(id);
    }

    @PostMapping("/emails")
    public List<ParticipantDtoInfo> getParticipantsByEmail(@RequestBody List<String> emails) {
        return participantService.getParticipantsByEmails(emails);
    }
}
