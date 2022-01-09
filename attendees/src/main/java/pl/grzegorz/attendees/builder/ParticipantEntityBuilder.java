package pl.grzegorz.attendees.builder;

import pl.grzegorz.attendees.model.ParticipantEntity;

public class ParticipantEntityBuilder {

    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String company;

    public ParticipantEntityBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ParticipantEntityBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ParticipantEntityBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public ParticipantEntityBuilder withAge(int age) {
        this.age = age;
        return this;
    }

    public ParticipantEntityBuilder withCompany(String company) {
        this.company = company;
        return this;
    }

    public ParticipantEntity build() {
        ParticipantEntity participantEntity = new ParticipantEntity();
        participantEntity.setFirstName(firstName);
        participantEntity.setLastName(lastName);
        participantEntity.setEmail(email);
        participantEntity.setAge(age);
        participantEntity.setCompany(company);
        return participantEntity;
    }
}
