package presentation._16_jmock_verifying_method_was_called;

import com.google.common.collect.ImmutableMap;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;

public class UserSettingsServiceTest {

    private static final int USER_ID = 123;

    private final Mockery mockery = new Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
    }};

    private final UserSettingsSqlDbSaver userSettingsDbSaver = mockery.mock(UserSettingsSqlDbSaver.class);
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
