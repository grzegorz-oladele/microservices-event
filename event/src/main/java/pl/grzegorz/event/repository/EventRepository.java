package pl.grzegorz.event.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.grzegorz.event.model.EventDto;

import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<EventDto, String> {

    List<EventDto> findAllByStatus(EventDto.Status status);
}
