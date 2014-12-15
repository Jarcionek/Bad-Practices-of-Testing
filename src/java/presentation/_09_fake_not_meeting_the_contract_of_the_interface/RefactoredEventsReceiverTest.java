package presentation._09_fake_not_meeting_the_contract_of_the_interface;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class RefactoredEventsReceiverTest {

    private EventsProcessor eventsProcessor = mock(EventsProcessor.class);

    private EventsReceiver eventsReceiver = new EventsReceiver(eventsProcessor, new RefactoredFakeUuidProvider());

    @Test
    public void reportsEventsWithDifferentUuids() {
        Event eventOne = new Event("eventNameOne");
        Event eventTwo = new Event("eventNameTwo");

        eventsReceiver.process(eventOne, eventTwo);

        verify(eventsProcessor, times(1)).process(eventOne, "0");
        verify(eventsProcessor, times(1)).process(eventTwo, "1");
        verifyNoMoreInteractions(eventsProcessor);
    }

    // other tests

}