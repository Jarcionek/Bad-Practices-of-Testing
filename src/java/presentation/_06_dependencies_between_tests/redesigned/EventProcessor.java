package presentation._06_dependencies_between_tests.redesigned;

import presentation._06_dependencies_between_tests.Event;
import presentation._06_dependencies_between_tests.Logger;

public class EventProcessor {

    private final Logger logger;
    private final Properties properties;

    public EventProcessor(Logger logger, Properties properties) {
        this.logger = logger;
        this.properties = properties;
    }

    public void process(Event event) {
        if (properties.getProperty("debug") != null && properties.getProperty("debug").equalsIgnoreCase("enabled")) {
            logger.log(event.getText());
        }

        // actual processing here
    }

}
