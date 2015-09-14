package presentation._16_jmock_verifying_method_was_called;

import com.google.common.collect.ImmutableMap;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Rule;
import org.junit.Test;

public class RefactoredJMockUserSettingsServiceTest {

    private static final int USER_ID = 123;

    @Rule
    public final JUnitRuleMockery mockery = new JUnitRuleMockery() {{
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
