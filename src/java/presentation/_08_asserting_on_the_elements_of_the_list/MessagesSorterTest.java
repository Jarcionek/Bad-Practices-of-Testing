package presentation._08_asserting_on_the_elements_of_the_list;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MessagesSorterTest {

    private MessagesSorter messagesSorter = new MessagesSorter();

    @Test
    public void sortsMessagesByTimestampThenBySenderThenByReceiver() {
        List<Message> unsortedMessages = asList(
                new Message(250, "John",     "Luke"),
                new Message(100, "Benjamin", "Oliver"),
                new Message(250, "John",     "George"),
                new Message(100, "Anthony",  "Samuel")
            );

        List<Message> sortedMessages = messagesSorter.sort(unsortedMessages);

        assertNotNull(sortedMessages);
        assertEquals(100, sortedMessages.get(0).getTimestamp());
        assertEquals("Anthony", sortedMessages.get(0).getSender());
        assertEquals("Samuel", sortedMessages.get(0).getReceiver());
        assertEquals(100, sortedMessages.get(1).getTimestamp());
        assertEquals("Benjamin", sortedMessages.get(1).getSender());
        assertEquals("Oliver", sortedMessages.get(1).getReceiver());
        assertEquals(4, sortedMessages.size());
        assertEquals(250, sortedMessages.get(2).getTimestamp());
        assertEquals("John", sortedMessages.get(2).getSender());
        assertEquals("George", sortedMessages.get(2).getReceiver());
        assertEquals(250, sortedMessages.get(3).getTimestamp());
        assertEquals("John", sortedMessages.get(3).getSender());
        assertEquals("Luke", sortedMessages.get(3).getReceiver());
    }

}