## Asserting on default value


### Domain:

Airlines have a system to create and store statistics about their flights, customers, income and so on. After each flight (after crew reports that all passengers have been checked out) the system calls ```StatisticsGenerator``` that takes from ```FlightManager``` all data required for the report and saves them to the database using ```DbStatisticsProxy```. One of the values the report contains is how many people bought a ticket but did not use it.


### Test code:

```java
public class StatisticsGeneratorTest {

    private static final int NUMBER_OF_BOOKED_AND_NOT_USED_SEATS = 0;

    private FlightManager flightManager = mock(FlightManager.class);
    private DbStatisticsProxy dbStatisticsProxy = mock(DbStatisticsProxy.class);

    private StatisticsGenerator statisticsGenerator
                                             = new StatisticsGenerator(flightManager, dbStatisticsProxy);

    @Test
    public void savesTheNumberOfCustomersWhoMissedTheirFlight() {
        when(flightManager.getBookedUnusedSeats()).thenReturn(NUMBER_OF_BOOKED_AND_NOT_USED_SEATS);

        statisticsGenerator.generateReport();

        verify(dbStatisticsProxy, times(1))
                .saveNumberOfPeopleWhoMissedTheirFlight(NUMBER_OF_BOOKED_AND_NOT_USED_SEATS);
    }

}
```


### Problem:

At first, this may look like a good test - meaningful name, one logical assertion, test is split into three parts (separated with empty line) - “given”, “when” and “then”. However, the problem here is that numberOfBookedAndNotUsedSeats is default value and it is quite unique in this scenario. Assuming that it is the only test for report generation, this value could be easily hardcoded in the implementation and not making calls to FlightsManager at all.

The problem is with what is the impact of such mistake, what will break and how easily it will be noticed. If you had 10 reports, each of them saying that exactly 6 people have missed their flight, you would probably become suspicious why is it always 6. But if each report says that no one missed their flight - maybe all people came long before departure to make sure they don’t miss their flight. Of course if having a few thousands reports it was always 0, you would probably start to doubt that the feature works. The problem is noticeable, but it may take much more time before someone becomes suspicious about the functionality of the program, if the value has special meaning in given context.

As another example, think of a “donate” function which allows to make anonymous donations (in what case user id will be -1). If out of first 20 donations all were anonymous, everything would look fine - maybe just there was no one brave enough to have their name published yet. But if the first and all 20 donations were made by the same user with id 637584 - it would stand out.


### Solution:

Avoid using values which have a special meaning in the given context. Do not use default values (0, false, null) or any other special values. Use a random value instead or something that stands out as incorrect, e.g. 55555 or 123456.


#### [Next page](https://github.com/Jarcionek/Bad-Practices-of-Testing/blob/master/src/java/presentation/_15_non_distinct_test_data/description.md)
