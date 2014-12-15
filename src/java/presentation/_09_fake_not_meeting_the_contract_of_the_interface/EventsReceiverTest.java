package presentation._09_fake_not_meeting_the_contract_of_the_interface;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class EventsReceiverTest {

    private EventProcessor eventProcessor = mock(EventProcessor.class);
    private UuidProvider uuidProvider = new FakeUuidProvider();

    private EventsReceiver eventsReceiver = new EventsReceiver(eventProcessor, uuidProvider);

    @Test
    public void reportsEventsWithDifferentUuids() {
        Event eventOne = new Event("eventNameOne");
        Event eventTwo = new Event("eventNameTwo");

        eventsReceiver.process(eventOne, eventTwo);

        verify(eventProcessor, times(1)).process(eventOne, "12345");
        verify(eventProcessor, times(1)).process(eventTwo, "12345");
    }

    // other tests

}
