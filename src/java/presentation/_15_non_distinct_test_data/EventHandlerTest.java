package presentation._15_non_distinct_test_data;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class EventHandlerTest {

    private static final int APP_ID = 123456;
    private static final int USER_ID = 123456;

    private final AppActivityReporter appActivityReporter = mock(AppActivityReporter.class);
    private final UserActivityReporter userActivityReporter = mock(UserActivityReporter.class);

    private final EventHandler eventHandler = new EventHandler(appActivityReporter, userActivityReporter);

    @Test
    public void reportsAppUsage() {
        eventHandler.handleEvent(new Event(APP_ID, USER_ID));

        verify(appActivityReporter, times(1)).report(APP_ID);
    }

    @Test
    public void reportsUserActivity() {
        eventHandler.handleEvent(new Event(APP_ID, USER_ID));

        verify(userActivityReporter, times(1)).report(USER_ID);
    }

}
