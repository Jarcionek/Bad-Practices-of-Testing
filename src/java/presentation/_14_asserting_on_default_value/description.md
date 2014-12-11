## Asserting on default value

### Test code:

```java
public class StatisticsReporterTest {

    private FlightsManager flightsManager = mock(FlightsManager.class);
    private DbStatisticsProxy dbStatisticsProxy = mock(DbStatisticsProxy.class);
    private int numberOfBookedAndNotUsedSeats = 0;

    private StatisticsReporter statisticsReporter = new StatisticsReporter(flightsManager, dbStatisticsProxy);

    @Test
    public void savesTheNumberOfCustomersWhoMissedTheirFlight() {
        when(flightsManager.getBookedUnusedSeats()).thenReturn(numberOfBookedAndNotUsedSeats);

        statisticsReporter.generateReport();

        verify(dbStatisticsProxy,
                times(1)).saveNumberOfPeopleWhoMissedTheirFlight(numberOfBookedAndNotUsedSeats);
    }

}
```


### Problem:

At first, this may look like a good test - meaningful name, one logical assertion, test is split into three parts (separated with empty line) - “given”, “when” and “then”. However, the problem here is that numberOfBookedAndNotUsedSeats is default value and it is quite unique in this scenario. Assuming that it is the only test for report generation, this value could be easily hardcoded in the implementation and not making calls to FlightsManager at all.

The problem is with what is the impact of such mistake, what will break and how easily it will be noticed. If you had 10 reports, each of them saying that exactly 6 people have missed their flight, you would probably become suspicious why is it always 6. But if each report says that no one missed their flight - maybe all people came long before departure to make sure they don’t miss their flight. Of course if having a few thousands reports it was always 0, you would probably start to doubt that the feature works. The problem is noticeable, but it may take much more time before someone becomes suspicious about the functionality of the program, if the value has special meaning in given context.

As another example, think of a “donate” function which allows to make anonymous donations (in what case user id will be -1). If out of first 20 donations all were anonymous, everything would look fine - maybe just there was no one brave enough to have their name published yet. But if the first and all 20 donations were made by the same user with id 637584 - it would stand out.


### Solution:

Avoid using values which have a special meaning in the tested context. Don’t use default values (0, false, null) or any other special values. Use a random value instead or something that stands out as incorrect, e.g. 123 or 123456.
