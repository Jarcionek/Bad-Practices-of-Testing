package presentation._06_dependencies_between_tests;

public class EventProcessor {

    private final Logger logger;

    public EventProcessor(Logger logger) {
        this.logger = logger;
    }

    public void process(Event event) {
        if (System.getProperty("debug") != null && System.getProperty("debug").equalsIgnoreCase("enabled")) {
            logger.log(event.getType());
        }

        // actual processing here
    }

}
