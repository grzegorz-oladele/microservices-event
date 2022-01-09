package pl.grzegorz.event.service.participant;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.grzegorz.event.model.dto.Participant;

import java.util.List;

@FeignClient(value = "PARTICIPANT-SERVICE")
@RequestMapping("/participants")
public interface ParticipantServiceClient {

    @GetMapping("/{participantId}")
    Participant getParticipantById(@PathVariable long participantId);

    @GetMapping("/emails")
    List<Participant> getParticipantsByEmailList(@RequestBody List<String> emails);
}
