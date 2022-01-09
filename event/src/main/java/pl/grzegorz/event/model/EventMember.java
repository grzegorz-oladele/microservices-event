package pl.grzegorz.event.model;

import java.time.LocalDateTime;

public class EventMember {

    private LocalDateTime enrollmentData;
    private String firstName;
    private String lastName;
    private String email;

    public EventMember(String firstName, String lastName, String email) {
        this.enrollmentData = LocalDateTime.now();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public LocalDateTime getEnrollmentData() {
        return enrollmentData;
    }

    public void setEnrollmentData(LocalDateTime enrollmentData) {
        this.enrollmentData = enrollmentData;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
