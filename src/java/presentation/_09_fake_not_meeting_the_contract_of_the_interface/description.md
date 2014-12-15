## Fake not meeting the contract of the interface it is implementing


### Domain:

The system polls events from the queue and calls ```EventsReceiver``` with a bulk of events. ```EventsReceiver``` attaches some metadata to the events, logs the events etc. before passing them one by one to ```EventsProcessor```. Bulks of events are guaranteed to be smaller than 1000 and it is required that each event in the same bulk has a unique identifier that allows to distinguish it from others in the bulk. Bulks can be identified by other metadata attached to the event, which is not relevant and hence not shown in the code.


### Production code:

```java
public interface UuidProvider {

    /**
     * Returns new universally unique identifier. It is guaranteed that returned value
     * is different than last 1000 previously returned values.
     */
    String next();

}
```


### Test code:

```java
public class FakeUuidProvider implements UuidProvider {

    @Override
    public String next() {
        return "12345";
    }

}

public class EventsReceiverTest {

    private EventsProcessor eventsProcessor = mock(EventsProcessor.class);

    private EventsReceiver eventsReceiver = new EventsReceiver(eventsProcessor, new FakeUuidProvider());

    @Test
    public void reportsEventsWithDifferentUuids() {
        Event eventOne = new Event("eventNameOne");
        Event eventTwo = new Event("eventNameTwo");

        eventsReceiver.process(eventOne, eventTwo);

        verify(eventsProcessor, times(1)).process(eventOne, "12345");
        verify(eventsProcessor, times(1)).process(eventTwo, "12345");
    }

    // other tests

}
```


### Problem:

Although the name of the test clearly says what it is supposed to test, it does not test it at all. FakeUuidProvider does not meet the contract of the interface it is implementing, it behaves differently than the real UuidProvider behaves. The minimal implementation to make this test pass is to get only one UUID and report all events against this single value.

Another minor problem is lack of verification that there were no other interactions with ```reporter```. If implementation was also reporting these events with other uuids (resulting in more than 2 reports), this test would be still green.


### Solution:

If you create stub or fake, make sure that it behaves like a real implementation:

```java
public class FakeUuidProvider implements UuidProvider {

    private int counter = 0;

    @Override
    public String next() {
        return "" + counter++;
    }

}
```


#### [Next page](https://github.com/Jarcionek/Bad-Practices-of-Testing/blob/master/src/java/presentation/_10_test_verifying_implementation_rather_than_behaviour/description.md)
