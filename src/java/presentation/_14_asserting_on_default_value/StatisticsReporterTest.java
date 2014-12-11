package presentation._14_asserting_on_default_value;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StatisticsReporterTest {

    private static final int NUMBER_OF_BOOKED_AND_NOT_USED_SEATS = 0;

    private FlightsManager flightsManager = mock(FlightsManager.class);
    private DbStatisticsProxy dbStatisticsProxy = mock(DbStatisticsProxy.class);

    private StatisticsReporter statisticsReporter = new StatisticsReporter(flightsManager, dbStatisticsProxy);

    @Test
    public void savesTheNumberOfCustomersWhoMissedTheirFlight() {
        when(flightsManager.getBookedUnusedSeats()).thenReturn(NUMBER_OF_BOOKED_AND_NOT_USED_SEATS);

        statisticsReporter.generateReport();

        verify(dbStatisticsProxy, times(1)).saveNumberOfPeopleWhoMissedTheirFlight(NUMBER_OF_BOOKED_AND_NOT_USED_SEATS);
    }

}
