package presentation._08_fake_not_meeting_the_contract_of_the_interface;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class JsonLoggingExceptionMapperTest {

    private final UuidProvider uuidProvider = new FakeUuidProvider();
    private final ExceptionLogger exceptionLogger = mock(ExceptionLogger.class);

    private final JsonLoggingExceptionMapper jsonLoggingExceptionMapper = new JsonLoggingExceptionMapper(uuidProvider, exceptionLogger);

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
