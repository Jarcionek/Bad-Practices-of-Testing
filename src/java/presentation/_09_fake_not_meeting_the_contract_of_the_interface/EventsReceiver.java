package presentation._09_fake_not_meeting_the_contract_of_the_interface;

public class EventsReceiver {

    private final EventsProcessor eventsProcessor;
    private final UuidProvider uuidProvider;

    public EventsReceiver(EventsProcessor eventsProcessor, UuidProvider uuidProvider) {
        this.eventsProcessor = eventsProcessor;
        this.uuidProvider = uuidProvider;
    }

    public void process(Event... events) {
        String uuid = uuidProvider.next();
        for (Event event : events) {
            eventsProcessor.process(event, uuid);
        }

        // other code
    }

}
