package presentation._06_dependencies_between_tests.redesigned;

import org.junit.Test;
import presentation._06_dependencies_between_tests.Event;
import presentation._06_dependencies_between_tests.Logger;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EventProcessorTest {

    private final Logger logger = mock(Logger.class);
    private final Properties properties = mock(Properties.class);

    private final EventProcessor eventProcessor = new EventProcessor(logger, properties);

    @Test
    public void doesNotLogEventIfDebugIsOff() {
        when(properties.getProperty("debug")).thenReturn("disabled");

        eventProcessor.process(new Event("eventText"));

        verify(logger, never()).log("eventText");
    }

    @Test
    public void logsEventIfDebugIsOn() {
        when(properties.getProperty("debug")).thenReturn("enabled");

        eventProcessor.process(new Event("eventText"));

        verify(logger, times(1)).log("eventText");
    }

    // some other tests

}