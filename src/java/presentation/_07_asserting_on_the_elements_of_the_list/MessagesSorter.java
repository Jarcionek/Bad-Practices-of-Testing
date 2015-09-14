package presentation._07_asserting_on_the_elements_of_the_list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MessagesSorter {

    public List<Message> sort(List<Message> messages) {
        List<Message> sortedMessages = new ArrayList<>(messages);

        Collections.sort(sortedMessages, new Comparator<Message>() {
            @Override
            public int compare(Message m1, Message m2) {
                return m2.getReceiver().compareTo(m1.getReceiver());
            }
        });

        Collections.sort(sortedMessages, new Comparator<Message>() {
            @Override
            public int compare(Message m1, Message m2) {
                return m2.getSender().compareTo(m1.getSender());
            }
        });

        Collections.sort(sortedMessages, new Comparator<Message>() {
            @Override
            public int compare(Message m1, Message m2) {
                return (int) (m2.getTimestamp() - m1.getTimestamp());
            }
        });

        return sortedMessages;
    }

}
