package presentation._09_fake_not_meeting_the_contract_of_the_interface;

public class EventsReceiver {

    private final Reporter reporter;
    private final UuidProvider uuidProvider;

    public EventsReceiver(Reporter reporter, UuidProvider uuidProvider) {
        this.reporter = reporter;
        this.uuidProvider = uuidProvider;
    }

    public void process(Event... events) {
        String uuid = uuidProvider.next();
        for (Event event : events) {
            reporter.report(event.getEventName(), uuid);
        }

        // other code
    }

}
