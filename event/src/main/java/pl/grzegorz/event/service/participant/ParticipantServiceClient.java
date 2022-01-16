package pl.grzegorz.event.service.participant;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import pl.grzegorz.event.model.dto.Participant;

import java.util.List;

@FeignClient(value = "PARTICIPANT-SERVICE")
@RequestMapping("/participants")
public interface ParticipantServiceClient {

    @GetMapping("/{participantId}")
    Participant getParticipantById(@PathVariable long participantId);

    @PostMapping("/emails")
    List<Participant> getParticipantsByEmailList(@RequestBody List<String> emails);


}
