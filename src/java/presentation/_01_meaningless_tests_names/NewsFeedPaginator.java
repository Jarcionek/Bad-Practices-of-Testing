package presentation._01_meaningless_tests_names;

import java.util.List;

public class NewsFeedPaginator {

    private final NewsFeedDbAdapter newsFeedDbAdapter;

    public NewsFeedPaginator(NewsFeedDbAdapter newsFeedDbAdapter) {
        this.newsFeedDbAdapter = newsFeedDbAdapter;
    }

    public NewsFeed fetch(UserId userId, PageSize pageSize, PageNumber pageNumber) {
        List<Message> messages = newsFeedDbAdapter.getNewsFeed(userId).getMessages();
        int toIndex = messages.size() - pageSize.asInt() * pageNumber.asInt();
        if (toIndex < 0) {
            toIndex = 0;
        }
        int fromIndex = toIndex - pageSize.asInt();
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        return new NewsFeed(messages.subList(fromIndex, toIndex));
    }

}