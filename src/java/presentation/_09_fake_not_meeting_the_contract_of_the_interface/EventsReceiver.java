package presentation._09_fake_not_meeting_the_contract_of_the_interface;

public class EventsReceiver {

    private final EventProcessor eventProcessor;
    private final UuidProvider uuidProvider;

    public EventsReceiver(EventProcessor eventProcessor, UuidProvider uuidProvider) {
        this.eventProcessor = eventProcessor;
        this.uuidProvider = uuidProvider;
    }

    public void process(Event... events) {
        String uuid = uuidProvider.nextUuid();
        for (Event event : events) {
            eventProcessor.process(event, uuid);
        }

        // other code
    }

}
