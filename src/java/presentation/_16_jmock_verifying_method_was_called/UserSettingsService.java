package presentation._16_jmock_verifying_method_was_called;

import java.util.Map;
import java.util.Map.Entry;

public class UserSettingsService {

    private final UserSettingsSqlDbSaver userSettingsDbSaver;

    public UserSettingsService(UserSettingsSqlDbSaver userSettingsDbSaver) {
        this.userSettingsDbSaver = userSettingsDbSaver;
    }

    public void saveUserSettings(int userId, Map<String, String> settings) {
        for (Entry<String, String> entry : settings.entrySet()) {
            userSettingsDbSaver.save(userId, entry.getKey(), entry.getValue());
        }
    }

}
