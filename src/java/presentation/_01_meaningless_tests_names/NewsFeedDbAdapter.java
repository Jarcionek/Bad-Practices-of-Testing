package presentation._01_meaningless_tests_names;

public interface NewsFeedDbAdapter {

    NewsFeed getNewsFeed(UserId userId);

    void post(UserId userId, Message message);

    void removeAll(UserId userId);

}
