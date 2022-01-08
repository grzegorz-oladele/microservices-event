package pl.grzegorz.event.exception;

public class EventException extends RuntimeException {

    private final EventError eventError;

    public EventException(EventError eventError) {
        this.eventError = eventError;
    }

    public EventError getEventError() {
        return eventError;
    }
}
