package presentation._07_asserting_on_the_elements_of_the_list;

public class Message {

    private final long timestamp;
    private final String sender;
    private final String receiver;

    public Message(long timestamp, String sender, String receiver) {
        this.timestamp = timestamp;
        this.sender = sender;
        this.receiver = receiver;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

}
