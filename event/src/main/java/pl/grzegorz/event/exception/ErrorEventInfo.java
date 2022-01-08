package pl.grzegorz.event.exception;

public class ErrorEventInfo {

    private final String message;

    public ErrorEventInfo(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
