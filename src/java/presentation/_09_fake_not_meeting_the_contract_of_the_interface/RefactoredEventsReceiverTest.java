package presentation._09_fake_not_meeting_the_contract_of_the_interface;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class RefactoredEventsReceiverTest {

    private EventProcessor eventProcessor = mock(EventProcessor.class);
    private UuidProvider uuidProvider = new RefactoredFakeUuidProvider();

    private EventsReceiver eventsReceiver = new EventsReceiver(eventProcessor, uuidProvider);

    @Test
    public void reportsEventsWithDifferentUuids() {
        Event eventOne = new Event("eventNameOne");
        Event eventTwo = new Event("eventNameTwo");

        eventsReceiver.process(eventOne, eventTwo);

        verify(eventProcessor, times(1)).process(eventOne, "0");
        verify(eventProcessor, times(1)).process(eventTwo, "1");
        verifyNoMoreInteractions(eventProcessor);
    }

    // other tests

}