## Non distinct test data

### Test code:

```java
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
```


### Problem:

Even when test driving the implementation and writing only the minimal implementation necessary to make the test green you may produce a correct implementation. One of the common mutations applied by mutation testing (see explanation below) is swapping the usage of variables/parameters/fields which have the same type. And because both app id and user id are equal, the tests would not fail if you accidentally introduced such bug.

Mutation testing - the production code is mutated (changed) and the tests are run. If all tests are green it means that nothing detected the change. This in turn means that introduced changed is irrelevant (fine) or the tests are of bad quality (not fine). If some tests have failed it means that they detected the change and you are protected against introducing such bug.


### Solution:

Extra tools (e.g. mutation testing framework) can help detect problems, but in this case it would be really used to resolve a technical debt of the test. It is enough to just make app id and user id different. Always try to make test data distinct so you will protect yourself from incorrectly using another variable of the same type.
