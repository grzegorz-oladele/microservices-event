package pl.grzegorz.attendees.builder;

import pl.grzegorz.attendees.dto.ParticipantDtoInfo;

public class ParticipantDtoInfoBuilder {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String company;
    ParticipantDtoInfoBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public ParticipantDtoInfoBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ParticipantDtoInfoBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ParticipantDtoInfoBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public ParticipantDtoInfoBuilder withAge(int age) {
        this.age = age;
        return this;
    }

    public ParticipantDtoInfoBuilder withCompany(String company) {
        this.company = company;
        return this;
    }

    public ParticipantDtoInfo build() {
        ParticipantDtoInfo participantDtoInfo = new ParticipantDtoInfo();
        participantDtoInfo.setId(id);
        participantDtoInfo.setFirstName(firstName);
        participantDtoInfo.setLastName(lastName);
        participantDtoInfo.setEmail(email);
        participantDtoInfo.setAge(age);
        participantDtoInfo.setCompany(company);
        return participantDtoInfo;
    }
}
