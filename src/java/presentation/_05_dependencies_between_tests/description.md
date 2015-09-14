## Dependencies between tests


### Domain:

A class in the larger system is responsible for processing incoming events. One of its responsibilities is to log the types of the events, but only if debug mode is enabled.


### Test code:

```java
public class EventProcessorTest {

    private Logger logger = mock(Logger.class);

    private EventProcessor eventProcessor = new EventProcessor(logger);

    @Test
    public void doesNotLogEventIfDebugIsOff() {
        eventProcessor.process(new Event("eventText"));

        verify(logger, never()).log("eventText");
    }

    @Test
    public void logsEventIfDebugIsOn() {
        System.setProperty("debug", "enabled");

        eventProcessor.process(new Event("eventText"));

        verify(logger, times(1)).log("eventText");
    }

}
```


### Problem:

If you run the tests separately, both of them will pass. But if you run them together, the first one may fail. May, because it depends on the order in which these two tests run and this depends on the environment (e.g. it may be always passing in IntelliJ, but always failing on command line; or it may be failing on someone’s machine but not on someone else’s).

These system properties also pollute your entire runtime environment affecting all other tests that run afterwards.


### Solution:

In this case it will be enough to add `@After` method that will be clearing this property or add a setup in the first test that will be setting debug property to disabled. However, using system properties for class behaviour can indicate the flaws in your system design - consider using insatiable Properties class instead.


#### [Next page](https://github.com/Jarcionek/Bad-Practices-of-Testing/blob/master/src/java/presentation/_06_non_thread_safe_tests/description.md)
