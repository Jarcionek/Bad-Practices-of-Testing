package presentation._01_meaningless_tests_names;

import com.google.common.collect.ImmutableList;

import java.util.List;

import static java.util.Collections.unmodifiableList;

public class NewsFeed {

    private final List<Message> messages;

    public NewsFeed(Message... messages) {
        this.messages = ImmutableList.of(messages);
    }

    public NewsFeed(List<Message> messages) {
        this.messages = unmodifiableList(messages);
    }

    public List<Message> getMessages() {
        return messages;
    }

}
