package pl.grzegorz.attendees.exception;

public class ErrorParticipantInfo {

    private final String message;

    public ErrorParticipantInfo(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
