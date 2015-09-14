package presentation._16_jmock_verifying_method_was_called;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RefactoredMockitoUserSettingsServiceTest {

    private static final int USER_ID = 123;

    private final UserSettingsSqlDbSaver userSettingsDbSaver = mock(UserSettingsSqlDbSaver.class);
    private final UserSettingsService userSettingsService = new UserSettingsService(userSettingsDbSaver);

    @Test
    public void callsDbSaverForEverySettings() {
        userSettingsService.saveUserSettings(USER_ID, ImmutableMap.of("main_panel_width", "1024", "main_panel_height", "600"));

        verify(userSettingsDbSaver, times(1)).save(USER_ID, "main_panel_width", "1024");
        verify(userSettingsDbSaver, times(1)).save(USER_ID, "main_panel_height", "600");
    }

}
