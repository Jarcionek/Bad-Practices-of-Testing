package presentation._14_asserting_on_default_value;

public class StatisticsGenerator {

    private final FlightManager flightManager;
    private final DbStatisticsProxy dbStatisticsProxy;

    public StatisticsGenerator(FlightManager flightManager, DbStatisticsProxy dbStatisticsProxy) {
        this.flightManager = flightManager;
        this.dbStatisticsProxy = dbStatisticsProxy;
    }

    public void generateReport() {
        dbStatisticsProxy.saveNumberOfPeopleWhoMissedTheirFlight(0);
    }

}
