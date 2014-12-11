package presentation._09_fake_not_meeting_the_contract_of_the_interface;

public class Event {

    private final String eventName;

    public Event(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

}
