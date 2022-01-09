package pl.grzegorz.event.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document
public class Event {

    public enum Status {

        ACTIVE,
        INACTIVE
        ,FULL
    }

    @Id
    private String code;
    private String name;
    private String description;
    private Status status = Status.ACTIVE;
    private List<EventMember> eventMembers = new ArrayList<>();
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long participantsLimit;
    private Long participantsNumber;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Long getParticipantsLimit() {
        return participantsLimit;
    }

    public void setParticipantsLimit(Long participantsLimit) {
        this.participantsLimit = participantsLimit;
    }

    public Long getParticipantsNumber() {
        return participantsNumber;
    }

    public void setParticipantsNumber(Long participantsNumber) {
        this.participantsNumber = participantsNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<EventMember> getEventMembers() {
        return eventMembers;
    }

    public void setEventMembers(List<EventMember> eventMembers) {
        this.eventMembers = eventMembers;
    }
}
