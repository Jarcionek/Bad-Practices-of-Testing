package presentation._07_asserting_on_the_elements_of_the_list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessagesSorter {

    public List<Message> sort(List<Message> messages) {
        List<Message> sortedMessages = new ArrayList<>(messages);

        Collections.sort(sortedMessages, (m1, m2) -> m2.getReceiver().compareTo(m1.getReceiver()));
        Collections.sort(sortedMessages, (m1, m2) -> m2.getSender().compareTo(m1.getSender()));
        Collections.sort(sortedMessages, (m1, m2) -> (int) (m2.getTimestamp() - m1.getTimestamp()));

        return sortedMessages;
    }

}
