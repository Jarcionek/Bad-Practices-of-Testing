## Multiple assert statements to verify a list


### Test code:

```java
public class MessagesSorterTest {

    private final MessagesSorter messagesSorter = new MessagesSorter();

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
        assertEquals(4, sortedMessages.size());
        assertEquals(100, sortedMessages.get(0).getTimestamp());
        assertEquals("Anthony", sortedMessages.get(0).getSender());
        assertEquals("Samuel", sortedMessages.get(0).getReceiver());
        assertEquals(100, sortedMessages.get(1).getTimestamp());
        assertEquals("Benjamin", sortedMessages.get(1).getSender());
        assertEquals("Oliver", sortedMessages.get(1).getReceiver());
        assertEquals(250, sortedMessages.get(2).getTimestamp());
        assertEquals("John", sortedMessages.get(2).getSender());
        assertEquals("George", sortedMessages.get(2).getReceiver());
        assertEquals(250, sortedMessages.get(3).getTimestamp());
        assertEquals("John", sortedMessages.get(3).getSender());
        assertEquals("Luke", sortedMessages.get(3).getReceiver());
    }

}
```


### Problem:

Diagnostics. They are completely meaningless. Having seen a test fail you need to go to the test to check on which line it failed, because message will not tell you this. If you already know on which assertion statement it failed, you still have no idea why. There is no overall picture and it is not possible to deduce what particular parts of the implementation is faulty.

![alt text](https://github.com/Jarcionek/Bad-Practices-of-Testing/blob/master/src/java/presentation/_07_asserting_on_the_elements_of_the_list/before-console.png)

Also, writing all these assertions is large boiler plate. It is error prone (e.g. index) and contains a lot of duplication.


### Solution:

Use a tool which serialises two objects to xml/json and does string comparison on them, such as `sameBeanAs` from [shazamcrest](https://github.com/shazam/shazamcrest):

```java
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

        assertThat(sortedMessages,
                is(sameBeanAs(asList(messageOne, messageTwo, messageThree, messageFour))));
    }

}
```

The test is easy to understand, you can clearly see expected order of messages, no duplication, no boiler plate and good diagnostics:

![alt text](https://github.com/Jarcionek/Bad-Practices-of-Testing/blob/master/src/java/presentation/_07_asserting_on_the_elements_of_the_list/after-console.png)

![alt text](https://github.com/Jarcionek/Bad-Practices-of-Testing/blob/master/src/java/presentation/_07_asserting_on_the_elements_of_the_list/after-comparison.png)


#### [Next page](https://github.com/Jarcionek/Bad-Practices-of-Testing/blob/master/src/java/presentation/_09_fake_not_meeting_the_contract_of_the_interface/description.md)
