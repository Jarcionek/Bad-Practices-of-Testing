package presentation._09_fake_not_meeting_the_contract_of_the_interface;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class RefactoredEventsReceiverTest {

    private Reporter reporter = mock(Reporter.class);

    private EventsReceiver eventsReceiver = new EventsReceiver(reporter, new RefactoredFakeUuidProvider());

    @Test
    public void reportsEventsWithDifferentUuids() {
        Event eventOne = new Event("eventNameOne");
        Event eventTwo = new Event("eventNameTwo");

        eventsReceiver.process(eventOne, eventTwo);

        verify(reporter, times(1)).report("eventNameOne", "0");
        verify(reporter, times(1)).report("eventNameTwo", "1");
        verifyNoMoreInteractions(reporter);
    }

    // other tests

}