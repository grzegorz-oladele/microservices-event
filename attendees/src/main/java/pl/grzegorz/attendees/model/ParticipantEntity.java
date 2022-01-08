package pl.grzegorz.attendees.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "participants")
@Getter
@Setter
public class ParticipantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isActive;
    private int age;
    private String company;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    @PrePersist
    public void create() {
        this.createdOn = LocalDateTime.now();
    }

    @PreUpdate
    public void update() {
        this.updatedOn = LocalDateTime.now();
    }
}
