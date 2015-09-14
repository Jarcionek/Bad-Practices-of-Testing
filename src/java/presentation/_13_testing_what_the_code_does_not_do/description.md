## Testing what the code does not do


### Test code:

```java
public class DataManagerTest {

    private DataSorter dataSorter = mock(DataSorter.class);

    private DataManager dataManager = new DataManager(dataSorter);

    @Test(expected = DataSorterException.class)
    public void testDataSorterException() {
        when(dataSorter.sort(any(Data.class))).thenThrow(new DataSorterException());

        dataManager.retrieveData();
    }

    // other tests

}
```


### Problem:

How can you make this test fail? Only by writing a try-catch that swallows the exception. When you write this test, it passes straight away. You have never seen it fail and it doesn’t drive you to write any code.


### Solution:

Useless test, delete it.


### Notes:

However, if there is another requirement (another test) saying that some exceptions should be swallowed, but you don’t want to have `DataSorterException` swallowed, you should leave this test. You should definitely rename it to e.g. `does not swallow DataSorterException`.


#### [Next page](https://github.com/Jarcionek/Bad-Practices-of-Testing/blob/master/src/java/presentation/_14_asserting_on_default_value/description.md)
