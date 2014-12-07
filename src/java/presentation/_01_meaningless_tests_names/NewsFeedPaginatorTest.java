package presentation._01_meaningless_tests_names;

import org.junit.Test;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NewsFeedPaginatorTest {

    private UserId userId = new UserId(7654);
    private NewsFeedDbAdapter dbAdapter = mock(NewsFeedDbAdapter.class);
    private NewsFeedPaginator newsFeedPaginator = new NewsFeedPaginator(dbAdapter);

    @Test
    public void test_1() {
        when(dbAdapter.getNewsFeed(userId)).thenReturn(new NewsFeed(msg(1), msg(2), msg(3)));

        NewsFeed actualNewsFeed = newsFeedPaginator.fetch(userId, 3, 0);

        assertThat(actualNewsFeed, is(sameBeanAs(new NewsFeed(msg(1), msg(2), msg(3)))));
    }

    @Test
    public void test_2() {
        when(dbAdapter.getNewsFeed(userId)).thenReturn(new NewsFeed(msg(1), msg(2), msg(3), msg(4)));

        NewsFeed actualNewsFeed = newsFeedPaginator.fetch(userId, 2, 0);

        assertThat(actualNewsFeed, is(sameBeanAs(new NewsFeed(msg(3), msg(4)))));
    }

    @Test
    public void test_3() {
        when(dbAdapter.getNewsFeed(userId)).thenReturn(new NewsFeed(msg(1), msg(2), msg(3), msg(4), msg(5), msg(6), msg(7)));

        NewsFeed actualNewsFeed = newsFeedPaginator.fetch(userId, 2, 2);

        assertThat(actualNewsFeed, is(sameBeanAs(new NewsFeed(msg(2), msg(3)))));
    }

    @Test
    public void test_4() {
        when(dbAdapter.getNewsFeed(userId)).thenReturn(new NewsFeed(msg(1), msg(2), msg(3), msg(4), msg(5)));

        NewsFeed actualNewsFeed = newsFeedPaginator.fetch(userId, 3, 1);

        assertThat(actualNewsFeed, is(sameBeanAs(new NewsFeed(msg(1), msg(2)))));
    }

    @Test
    public void test_5() {
        when(dbAdapter.getNewsFeed(userId)).thenReturn(new NewsFeed(msg(1), msg(2), msg(3)));

        NewsFeed actualNewsFeed = newsFeedPaginator.fetch(userId, 3, 3);

        assertThat(actualNewsFeed, is(sameBeanAs(new NewsFeed())));
    }


    private static Message msg(int timestamp) {
        return new Message(timestamp, "msg-" + timestamp);
    }

}