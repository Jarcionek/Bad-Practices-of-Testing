package presentation._15_non_distinct_test_data;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ActivityReporterTest {

    private static final int APP_ID = 123456;
    private static final int USER_ID = 123456;

    private AppActivityReporter appActivityReporter = mock(AppActivityReporter.class);
    private UserActivityReporter userActivityReporter = mock(UserActivityReporter.class);

    private ActivityReporter activityReporter = new ActivityReporter(appActivityReporter, userActivityReporter);

    @Test
    public void reportsAppUsage() {
        activityReporter.reportUsage(APP_ID, USER_ID);

        verify(appActivityReporter, times(1)).report(APP_ID);
    }

    @Test
    public void reportsUserActivity() {
        activityReporter.reportUsage(APP_ID, USER_ID);

        verify(userActivityReporter, times(1)).report(USER_ID);
    }

}