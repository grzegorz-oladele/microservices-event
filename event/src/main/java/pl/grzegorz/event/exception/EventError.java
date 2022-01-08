package pl.grzegorz.event.exception;

public enum EventError {

    EVENT_EMPTY_LIST("Events according to your parameters not found");

    private final String message;

    EventError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
