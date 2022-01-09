package pl.grzegorz.event.exception;

public enum EventError {

    EVENT_EMPTY_LIST("Events according to your parameters not found"),
    EVENT_NOT_FOUND("Event not found"),
    EVENT_ALREADY_EXIST("Event already exist"),
    EVENT_BAD_LIMIT("Limit of participants must be greater than 0"),
    EVENT_BAD_DESCRIPTION("Bad description"),
    EVENT_MAX_PARTICIPANT_NUMBER("The number of participants is full. Enrollment closed"),
    EVENT_BAD_DATA("Start date must be earlier than the end date"),
    EVENT_NOT_ACTIVE("Registration for the event is not impossible at this time. Event is not active"),
    PARTICIPANT_ALREADY_ENROLLED("Participant already enrolled on this event"),
    PARTICIPANT_NOT_ACTIVE("Participant is not active"),
    EVENT_IS_INACTIVE("Event is inactive");

    private final String message;

    EventError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
