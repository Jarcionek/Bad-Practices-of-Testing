package presentation._14_asserting_on_default_value;

public class StatisticsReporter {

    private final FlightsManager flightsManager;
    private final DbStatisticsProxy dbStatisticsProxy;

    public StatisticsReporter(FlightsManager flightsManager, DbStatisticsProxy dbStatisticsProxy) {
        this.flightsManager = flightsManager;
        this.dbStatisticsProxy = dbStatisticsProxy;
    }

    public void generateReport() {
        dbStatisticsProxy.saveNumberOfPeopleWhoMissedTheirFlight(0);
    }

}
