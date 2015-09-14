package presentation._07_asserting_on_the_elements_of_the_list;

import org.junit.Test;

import java.util.List;

import static com.shazam.shazamcrest.MatcherAssert.assertThat;
import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;

public class RefactoredMessagesSorterTest {

    private MessagesSorter messagesSorter = new MessagesSorter();

    @Test
    public void sortsMessagesByTimestampThenBySenderThenByReceiver() {
        Message messageOne = new Message(100, "Anthony", "Samuel");
        Message messageTwo = new Message(100, "Benjamin", "Oliver");
        Message messageThree = new Message(250, "John", "George");
        Message messageFour = new Message(250, "John", "Luke");

        List<Message> unsortedMessages = asList(
                messageTwo,
                messageFour,
                messageOne,
                messageThree
        );

        List<Message> sortedMessages = messagesSorter.sort(unsortedMessages);

        assertThat(sortedMessages, is(sameBeanAs(asList(messageOne, messageTwo, messageThree, messageFour))));
    }

}
