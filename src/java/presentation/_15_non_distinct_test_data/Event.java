package presentation._15_non_distinct_test_data;

public class Event {

    private final int appId;
    private final int userId;

    public Event(int appId, int userId) {
        this.appId = appId;
        this.userId = userId;
    }

    public int getAppId() {
        return appId;
    }

    public int getUserId() {
        return userId;
    }

}
