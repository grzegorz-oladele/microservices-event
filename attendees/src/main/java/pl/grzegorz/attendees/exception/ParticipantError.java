package pl.grzegorz.attendees.exception;

public enum ParticipantError {

    PARTICIPANT_EMPTY_LIST("Participants according to your parameters not found"),
    PARTICIPANT_NOT_FOUND("Participant not found"),
    PARTICIPANT_NULL_POINTER("Cannot perform the operation");

    private final String message;

    ParticipantError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
