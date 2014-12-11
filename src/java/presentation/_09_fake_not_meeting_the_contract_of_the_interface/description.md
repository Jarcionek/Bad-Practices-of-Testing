## Fake not meeting the contract of the interface it is implementing

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

    private Reporter reporter = mock(Reporter.class);

    private EventsReceiver eventsReceiver = new EventsReceiver(reporter, new FakeUuidProvider());

    @Test
    public void reportsEventsWithDifferentUuids() {
        Event eventOne = new Event("eventNameOne");
        Event eventTwo = new Event("eventNameTwo");

        eventsReceiver.process(eventOne, eventTwo);

        verify(reporter, times(1)).report("eventNameOne", "12345");
        verify(reporter, times(1)).report("eventNameTwo", "12345");
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
