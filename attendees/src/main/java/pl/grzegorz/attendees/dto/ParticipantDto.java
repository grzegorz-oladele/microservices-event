package pl.grzegorz.attendees.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipantDto {

    private String firstName;
    private String secondName;
    private String email;
    private int age;
    private String company;
}
