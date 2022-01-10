package pl.grzegorz.attendees.exception;

public enum ParticipantError {

    PARTICIPANT_EMPTY_LIST("Participants according to your parameters not found"),
    PARTICIPANT_NOT_FOUND("Participant not found"),
    PARTICIPANT_ALREADY_EXISTS("The user with the specified email address is already in the application. " +
                                       "Select a different email address.");

    private final String message;

    ParticipantError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
