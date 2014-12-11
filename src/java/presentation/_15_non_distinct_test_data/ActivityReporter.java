package presentation._15_non_distinct_test_data;

public class ActivityReporter {

    private final AppActivityReporter appActivityReporter;
    private final UserActivityReporter userActivityReporter;

    public ActivityReporter(AppActivityReporter appActivityReporter, UserActivityReporter userActivityReporter) {
        this.appActivityReporter = appActivityReporter;
        this.userActivityReporter = userActivityReporter;
    }

    public void reportUsage(int appId, int userId) {
        appActivityReporter.report(appId);
        userActivityReporter.report(appId);
    }

}
