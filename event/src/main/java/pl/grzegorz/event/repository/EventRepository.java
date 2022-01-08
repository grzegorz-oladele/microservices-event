package pl.grzegorz.event.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.grzegorz.event.model.Event;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {
}
