package pl.grzegorz.event.exception;

public enum EventError {

    EVENT_EMPTY_LIST("Events according to your parameters not found"),
    EVENT_NOT_FOUND("Event not found"),
    EVENT_ALREADY_EXIST("Event already exist"),
    EVENT_BAD_LIMIT("Limit of participants must be greater than 0"),
    EVENT_BAD_DESCRIPTION("Bad description"),
    EVENT_MAX_PARTICIPANT_NUMBER("The number of participants is full. Enrollment closed");

    private final String message;

    EventError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
