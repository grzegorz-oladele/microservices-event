package pl.grzegorz.attendees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.grzegorz.attendees.model.ParticipantEntity;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {

    @Query
    List<ParticipantEntity> findAllByEmailIn(List<String> emails);
}
