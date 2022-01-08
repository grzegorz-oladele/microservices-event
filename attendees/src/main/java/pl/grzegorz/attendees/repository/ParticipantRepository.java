package pl.grzegorz.attendees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.grzegorz.attendees.model.ParticipantEntity;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {
}
