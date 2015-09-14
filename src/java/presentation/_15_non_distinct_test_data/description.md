## Non distinct test data


### Domain:

A large system has multiple applications available to access it - web interface, mobile app, stand alone installation etc. Each installation has an unique application id, which means that single user can be interacting with the system having one of a few different app ids. To make targeted promotions the marketing department wants to know how often users are using the system and what apps are most commonly used. `EventHandler` receives incoming user request (event), reports the activity and does some event processing (which is intentionally omitted in this code snippet).


### Test code:

```java
public class EventHandlerTest {

    private static final int APP_ID = 123456;
    private static final int USER_ID = 123456;

    private AppActivityReporter appActivityReporter = mock(AppActivityReporter.class);
    private UserActivityReporter userActivityReporter = mock(UserActivityReporter.class);

    private EventHandler eventHandler = new EventHandler(appActivityReporter, userActivityReporter);

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
```


### Problem:

Even when test driving the implementation and writing only the minimal implementation necessary to make the test green you may produce a correct implementation. One of the common mutations applied by mutation testing (see notes below) is swapping the usage of variables/parameters/fields which have the same type. And because both app id and user id are equal, the tests would not fail if you accidentally introduced such bug.

Also in this case you may consider to verify that there were no other interactions with `appActivityReporter` and `userActivityReporter`.


### Solution:

Extra tools (e.g. mutation testing framework) can help detect problems, but in this case it would be really used to resolve a technical debt of the test. It is enough to just make app id and user id different. Always try to make test data distinct so you will protect yourself from incorrectly using another variable of the same type. You can also consider encapsulating primitive types into domain objects.


### Notes:

Mutation testing - the production code is mutated (changed) and the tests are run. If all tests are green it means that nothing detected the change. This in turn means that introduced changed is irrelevant (fine) or the tests are of bad quality (not fine). If some tests have failed it means that they detected the change and you are protected against introducing such bug.


#### [Next page](https://github.com/Jarcionek/Bad-Practices-of-Testing/blob/master/src/java/presentation/_16_jmock_verifying_method_was_called/description.md)

