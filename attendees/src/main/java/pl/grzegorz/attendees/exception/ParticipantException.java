package pl.grzegorz.attendees.exception;

public class ParticipantException extends RuntimeException{

    private final ParticipantError participantError;

    public ParticipantException(ParticipantError participantError) {
        this.participantError = participantError;
    }

    public ParticipantError getParticipantError() {
        return participantError;
    }
}
