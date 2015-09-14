## Meaningless tests names


### Domain:

User news feed contains of messages which are made of timestamps and text. The mobile application that displays it rarely needs more than last 50 messages so it queries for needed pages - e.g. most recent 50 messages is page of size 50 and page number 0, next 50 messages is page size 50 and page number 1. Paginator is a class that retrieves entire user news feed with messages sorted by timestamp and returns requested page only. Note that news feed is sorted by timestamp in ascending order while page number go in the opposite order - so if entire news feed is `[A, B, C, D, E]`, page number 0 of size 3 is `[C, D, E]` and page number 1 of size 3 is `[A, B]`.


### Test code:

```java
public class NewsFeedPaginatorTest {

    private UserId userId = new UserId(7654);

    private NewsFeedDbAdapter dbAdapter = mock(NewsFeedDbAdapter.class);

    private NewsFeedPaginator newsFeedPaginator = new NewsFeedPaginator(dbAdapter);

    @Test
    public void test_1() {
        when(dbAdapter.getNewsFeed(userId)).thenReturn(new NewsFeed(msg(1), msg(2), msg(3)));

        NewsFeed actualNewsFeed = newsFeedPaginator.fetch(userId, pageSize(3), pageNumber(0));

        assertThat(actualNewsFeed, is(sameBeanAs(new NewsFeed(msg(1), msg(2), msg(3)))));
    }

    @Test
    public void test_2() {
        when(dbAdapter.getNewsFeed(userId)).thenReturn(new NewsFeed(msg(1), msg(2), msg(3), msg(4)));

        NewsFeed actualNewsFeed = newsFeedPaginator.fetch(userId, pageSize(2), pageNumber(0));

        assertThat(actualNewsFeed, is(sameBeanAs(new NewsFeed(msg(3), msg(4)))));
    }

    @Test
    public void test_3() {
        when(dbAdapter.getNewsFeed(userId))
                .thenReturn(new NewsFeed(msg(1), msg(2), msg(3), msg(4), msg(5), msg(6), msg(7)));

        NewsFeed actualNewsFeed = newsFeedPaginator.fetch(userId, pageSize(2), pageNumber(2));

        assertThat(actualNewsFeed, is(sameBeanAs(new NewsFeed(msg(2), msg(3)))));
    }

    @Test
    public void test_4() {
        when(dbAdapter.getNewsFeed(userId))
                .thenReturn(new NewsFeed(msg(1), msg(2), msg(3), msg(4), msg(5)));

        NewsFeed actualNewsFeed = newsFeedPaginator.fetch(userId, pageSize(3), pageNumber(1));

        assertThat(actualNewsFeed, is(sameBeanAs(new NewsFeed(msg(1), msg(2)))));
    }

    @Test
    public void test_5() {
        when(dbAdapter.getNewsFeed(userId)).thenReturn(new NewsFeed(msg(1), msg(2), msg(3)));

        NewsFeed actualNewsFeed = newsFeedPaginator.fetch(userId, pageSize(3), pageNumber(3));

        assertThat(actualNewsFeed, is(sameBeanAs(new NewsFeed())));
    }


    private static Message msg(int timestamp) {
        return new Message(timestamp, "msg-" + timestamp);
    }

}
```


### Problem:

Paginator has multiple edge cases and takes multiple parameters. The tests should be testing that both page size and page number are used correctly. There are also multiple edge cases - what if there are no messages for the requested page? Should it return empty news feed or maybe throw the exception? This behaviour should be documented by tests but their names are meaningless and it is difficult to figure out what part of code each of the tests is actually testing.


### Solution:

Always give your tests meaningful names. Names that describe how the class behaves. In this case the tests could be named e.g.:
- returnsEntireNewsFeedWhenRequestedPageZeroAndThereAreAsManyMessagesAsPageSize
- returnsPageZeroCappedToPageSize
- returnsFullPageIfSecondPageIsRequestedAndThereAreMoreElements
- returnsLastPageWhenItIsNotFull
- returnsEmptyNewsFeedWhenNoMessageAreAvailableForRequestedPage


#### [Next page](https://github.com/Jarcionek/Bad-Practices-of-Testing/blob/master/src/java/presentation/_02_using_junit_parameterized/description.md)
