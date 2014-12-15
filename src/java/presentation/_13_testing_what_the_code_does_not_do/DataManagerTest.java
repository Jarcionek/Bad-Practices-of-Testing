package presentation._13_testing_what_the_code_does_not_do;

import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
