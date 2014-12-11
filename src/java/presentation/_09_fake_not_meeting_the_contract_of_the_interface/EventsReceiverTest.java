package presentation._09_fake_not_meeting_the_contract_of_the_interface;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class EventsReceiverTest {

    private Reporter reporter = mock(Reporter.class);

    private EventsReceiver eventsReceiver = new EventsReceiver(reporter, new FakeUuidProvider());

    @Test
    public void reportsEventsWithDifferentUuids() {
        Event eventOne = new Event("eventNameOne");
        Event eventTwo = new Event("eventNameTwo");

        eventsReceiver.process(eventOne, eventTwo);

        verify(reporter, times(1)).report("eventNameOne", "12345");
        verify(reporter, times(1)).report("eventNameTwo", "12345");
    }

    // other tests

}