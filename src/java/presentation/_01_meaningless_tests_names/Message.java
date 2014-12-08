package presentation._01_meaningless_tests_names;

public class Message {

    private final long timestamp;
    private final String message;

    public Message(long timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

}
