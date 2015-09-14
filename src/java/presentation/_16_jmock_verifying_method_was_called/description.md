## JMock verifying method was called


### Domain:

In the web application a lot of user's choices are saved to the database. For example, if user resizes the main panel, the next time they open an application the panel will have exactly the same sizes. ```UserSettingsDbSaver``` is a class that saves data to the SQL database, while ```UserSettingsService``` is a class sitting between HTTP endpoint and ```UserSettingsDbSaver```. 


### Test code:

```java
public class UserSettingsServiceTest {

    private static final int USER_ID = 123;

    private final Mockery mockery = new Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    private final UserSettingsDbSaver userSettingsDbSaver = mockery.mock(UserSettingsDbSaver.class);
    private final UserSettingsService userSettingsService = new UserSettingsService(userSettingsDbSaver);

    @Test
    public void callsDbSaverForEverySettings() {
        mockery.checking(new Expectations() {{
            oneOf(userSettingsDbSaver).save(USER_ID, "main_panel_width", "1024");
            oneOf(userSettingsDbSaver).save(USER_ID, "main_panel_height", "600");
        }});

        userSettingsService.saveUserSettings(USER_ID, ImmutableMap.of("main_panel_width", "1024", "main_panel_height", "600"));
    }

}
```


### Problem:

This test never fails. This is because JMock expectations are set up before the call are not checked after the call was made.

Another minor problem (but it is really a matter of preference) is extra setup required by JMock. By default it cannot mock classes, ```setImposteriser(ClassImposteriser.INSTANCE)``` is required which in turn requires maven dependency on ```jmock-legacy```.


### Solution:

When using JMock, the setup has to be changed to:

```java
@Rule
public final JUnitRuleMockery mockery = new JUnitRuleMockery() {{
    setImposteriser(ClassImposteriser.INSTANCE);
}};
```

Class ```JUnitRuleMockery``` is defined in ```jmock-junit4``` dependency so also a change to the pom file is required.

With Mockito, no extra setup/dependencies are necessary and the test can be written in gwen style (given-when-then):

```java
public class RefactoredMockitoUserSettingsServiceTest {

    private static final int USER_ID = 123;

    private final UserSettingsDbSaver userSettingsDbSaver = mock(UserSettingsDbSaver.class);
    private final UserSettingsService userSettingsService = new UserSettingsService(userSettingsDbSaver);

    @Test
    public void callsDbSaverForEverySettings() {
        userSettingsService.saveUserSettings(USER_ID, ImmutableMap.of("main_panel_width", "1024", "main_panel_height", "600"));

        verify(userSettingsDbSaver, times(1)).save(USER_ID, "main_panel_width", "1024");
        verify(userSettingsDbSaver, times(1)).save(USER_ID, "main_panel_height", "600");
    }

}
```


#### [Back to main page](https://github.com/Jarcionek/Bad-Practices-of-Testing)

