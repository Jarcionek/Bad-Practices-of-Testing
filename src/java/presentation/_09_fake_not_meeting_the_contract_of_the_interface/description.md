## Fake not meeting the contract of the interface it is implementing


### Domain:

`JsonLoggingExceptionMapper` is a class that converts exception thrown anywhere in the code into HTTP response to be returned by the server. In our case it also logs the exceptions - each with different id.


### Production code:

```java
public interface UuidProvider {

    /**
     * Returns new universally unique identifier. It is guaranteed that returned value
     * is different than last 1000 previously returned values.
     */
    String nextUuid();

}
```


### Test code:

```java
public class JsonLoggingExceptionMapperTest {

    private final UuidProvider uuidProvider = new FakeUuidProvider();
    private final ExceptionLogger exceptionLogger = mock(ExceptionLogger.class);

    private final JsonLoggingExceptionMapper jsonLoggingExceptionMapper
                                         = new JsonLoggingExceptionMapper(uuidProvider, exceptionLogger);

    @Test
    public void logsSuppressedExceptionsWithDifferentIds() {
        RuntimeException exception = new RuntimeException();
        Throwable suppressedExceptionOne = new IllegalArgumentException();
        Throwable suppressedExceptionTwo = new IllegalStateException();
        exception.addSuppressed(suppressedExceptionOne);
        exception.addSuppressed(suppressedExceptionTwo);

        jsonLoggingExceptionMapper.toResponse(exception);

        verify(exceptionLogger, times(1)).log("12345", suppressedExceptionOne);
        verify(exceptionLogger, times(1)).log("12345", suppressedExceptionTwo);
    }
    
    // other tests

}
```


### Problem:

Although the name of the test clearly says what it is supposed to test, it does not test it at all. `FakeUuidProvider` does not meet the contract of the interface it is implementing, it behaves differently than the real `UuidProvider` behaves. The minimal implementation to make this test pass is to get only one UUID and log all exceptions with this single value.

Another minor problem is lack of verification that there were no other interactions with `exceptionLogger`. If implementation was also logging the actual exception, this test would be still green.


### Solution:

If you create stub or fake, make sure that it behaves like a real implementation:

```java
public class FakeUuidProvider implements UuidProvider {

    private int counter = 0;

    @Override
    public String nextUuid() {
        return "" + counter++;
    }

}
```

Some classes (like `UuidProvider`) cannot be stubbed.


#### [Next page](https://github.com/Jarcionek/Bad-Practices-of-Testing/blob/master/src/java/presentation/_10_test_verifying_implementation_rather_than_behaviour/description.md)
